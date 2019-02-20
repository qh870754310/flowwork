package com.qh.modules.activiti.service.impl;

import com.qh.modules.activiti.dao.ExtendActNodefieldDao;
import com.qh.modules.activiti.dao.ExtendActNodesetDao;
import com.qh.modules.activiti.dao.ExtendActNodeuserDao;
import com.qh.modules.activiti.entity.ExtendActNodefieldEntity;
import com.qh.modules.activiti.entity.ExtendActNodesetEntity;
import com.qh.modules.activiti.entity.ExtendActNodeuserEntity;
import com.qh.modules.activiti.service.ExtendActNodesetService;
import com.qh.modules.activiti.utils.ActUtils;
import com.qh.modules.common.common.Constant;
import com.qh.modules.common.exception.MyException;
import com.qh.modules.common.utils.StringUtils;
import com.qh.modules.common.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/28.
 */
@Service("extendActNodesetService")
public class ExtendActNodesetServiceImpl implements ExtendActNodesetService {

    @Autowired
    private ExtendActNodesetDao extendActNodesetDao;

    @Autowired
    private ExtendActNodeuserDao nodeuserDao;

    @Autowired
    private ExtendActNodefieldDao nodefieldDao;

    @Override
    public ExtendActNodesetEntity queryByNodeId(String nodeId) {
        return extendActNodesetDao.queryByNodeId(nodeId);
    }

    @Override
    @Transactional
    public ExtendActNodesetEntity saveNode(ExtendActNodesetEntity extendActNodesetEntity) throws IOException {
        if (StringUtils.isEmpty(extendActNodesetEntity.getModelId())) {
            throw new MyException("模型id不能为空!");
        }
        if (StringUtils.isEmpty(extendActNodesetEntity.getNodeId())) {
            throw new MyException("节点id不能为空!");
        }
        if (StringUtils.isEmpty(extendActNodesetEntity.getNodeType())) {
            throw new MyException("节点类型不能为空!");
        }
        //节点类型为审批节点
        if (extendActNodesetEntity.getNodeType().equals(Constant.NodeType.EXAMINE.getValue())) {
            //设置会签取消会签
            if (Constant.ActAction.MULIT.getValue().equals(extendActNodesetEntity.getNodeAction())) {
                try {
                    ActUtils.setMultiInstance(extendActNodesetEntity.getModelId(), extendActNodesetEntity.getNodeId());
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new MyException("设置会签失败!");
                }
            } else {
                try {
                    ActUtils.clearMultiInstance(extendActNodesetEntity.getModelId(), extendActNodesetEntity.getNodeId());
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new MyException("取消会签失败!");
                }
            }

            if (StringUtils.isEmpty(extendActNodesetEntity.getId())) {
                //保存节点信息
                extendActNodesetEntity.setId(Utils.uuid());
                extendActNodesetDao.save(extendActNodesetEntity);
            } else {
                //更新
                //保存节点信息
                extendActNodesetDao.update(extendActNodesetEntity);
                //保存审批用户 先根据nodeId删除节点相关的审批用户
                nodeuserDao.delByNodeId(extendActNodesetEntity.getNodeId());
            }
            //保存审批用户
            String[] userTypes = extendActNodesetEntity.getUserTypes();
            String[] userIds = extendActNodesetEntity.getUserIds();
            List<ExtendActNodeuserEntity> nodeUsers = new ArrayList<>();
            ExtendActNodeuserEntity nodeUser = null;
            if (userIds != null && userIds.length > 0) {
                for (int i = 0; i < userIds.length; i++) {
                    nodeUser = new ExtendActNodeuserEntity();
                    nodeUser.setId(userIds[i]);
                    nodeUser.setUserType(userTypes[i]);
                    nodeUser.setNodeId(extendActNodesetEntity.getNodeId());
                    nodeUsers.add(nodeUser);
                }
                nodeuserDao.saveBatch(nodeUsers);
            }
        }
        //分支条件连线
        if (extendActNodesetEntity.getNodeType().equals(Constant.NodeType.LINE.getValue())) {
            //保存
            if (StringUtils.isEmpty(extendActNodesetEntity.getNodeId())) {
                //保存节点信息
                extendActNodesetEntity.setId(Utils.uuid());
                extendActNodesetDao.save(extendActNodesetEntity);
            } else {
                //更新
                //保存节点信息
                extendActNodesetDao.update(extendActNodesetEntity);
                //根据nodeId删除所有节点对应的连线条件
                nodefieldDao.delByNodeId(extendActNodesetEntity.getNodeId());
            }
            //el条件 例如${day>3 && isagree==1}
            StringBuilder condition = new StringBuilder("${");
            if (extendActNodesetEntity.getJudgList() != null && extendActNodesetEntity.getJudgList().size() > 0) {
                List<ExtendActNodefieldEntity> judgList = new ArrayList<>();
                int sort = 0;
                for (ExtendActNodefieldEntity nodefield: extendActNodesetEntity.getJudgList()) {
                    if (StringUtils.isEmpty(nodefield.getFieldName()) || StringUtils.isEmpty(nodefield.getRule())) {
                        continue;
                    }
                    Map<String, Object> map = tranceCode(nodefield);
                    nodefield.setId(Utils.uuid());
                    if (!StringUtils.isEmpty(nodefield.getElOperator())) {
                        condition.append(" " + map.get("elOperator") + " ");
                    }
                    condition.append(nodefield.getFieldName()).append(map.get("rule")).append(nodefield.getFieldVal());
                    nodefield.setNodeId(extendActNodesetEntity.getNodeId());
                    nodefield.setId(Utils.uuid());
                    nodefield.setSort(sort + "");
                    sort++;
                    judgList.add(nodefield);
                }
                nodefieldDao.saveBatch(judgList);
            }
            String judg = condition.append("}").toString();
            //添加条件
            ActUtils.setSequenceFlowCondition(extendActNodesetEntity.getModelId(), extendActNodesetEntity.getNodeId(), judg);
        }
        //节点类型为结束
        if (extendActNodesetEntity.getNodeType().equals(Constant.NodeType.END.getValue())) {
            if (StringUtils.isEmpty(extendActNodesetEntity.getId())) {
                //保存节点信息
                extendActNodesetDao .save(extendActNodesetEntity);
            } else {
                //更新
                extendActNodesetDao.update(extendActNodesetEntity);
            }
        }
        return extendActNodesetEntity;
    }

    @Override
    public ExtendActNodesetEntity queryByNodeIdModelId(String nodeId, String modelId) {
        return extendActNodesetDao.queryByNodeIdModelId(nodeId, modelId);
    }

    /**
     * 将中文字符转换为el运算符
     *
     * @param extendActNodefieldEntity
     * @return
     */
    private Map<String, Object> tranceCode(ExtendActNodefieldEntity extendActNodefieldEntity) {
        Map<String, Object> map = new HashMap<>();
        if (extendActNodefieldEntity.getElOperator() != null) {
            switch (extendActNodefieldEntity.getElOperator()) {
                case "并且":
                    map.put("elOperator","&&");break;
                case "或者":
                    map.put("elOperator","||");break;
            }
        }
        if (extendActNodefieldEntity.getRule() != null) {
            switch (extendActNodefieldEntity.getRule()) {
                case "大于":
                    map.put("rule",">");break;
                case "大于等于":
                    map.put("rule",">=");break;
                case "小于":
                    map.put("rule","<");break;
                case "小于等于":
                    map.put("rule","<=");break;
                case "等于":
                    map.put("rule","==");break;
            }
        }
        return map;
    }
}
