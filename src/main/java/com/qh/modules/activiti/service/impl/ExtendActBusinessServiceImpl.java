package com.qh.modules.activiti.service.impl;

import com.qh.modules.activiti.annotation.ActField;
import com.qh.modules.activiti.dao.ExtendActBusinessDao;
import com.qh.modules.activiti.entity.ExtendActBusinessEntity;
import com.qh.modules.activiti.service.ExtendActBusinessService;
import com.qh.modules.activiti.utils.AnnotationUtils;
import com.qh.modules.common.common.Constant;
import com.qh.modules.common.exception.MyException;
import com.qh.modules.common.utils.StringUtils;
import com.qh.modules.common.utils.UserUtils;
import com.qh.modules.common.utils.Utils;
import com.qh.modules.sys.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Administrator on 2018/4/26.
 */
@Service("extendActBusinessService")
public class ExtendActBusinessServiceImpl implements ExtendActBusinessService {

    @Autowired
    private ExtendActBusinessDao extendActBusinessDao;


    @Override
    public List<ExtendActBusinessEntity> queryBusTree() {
        return extendActBusinessDao.queryBusTree(Constant.ActBusType.GROUP.getValue(), Constant.ActBusType.BUS.getValue());
    }

    @Override
    public ExtendActBusinessEntity queryActBusByModelId(String modelId) {
        ExtendActBusinessEntity businessEntity = extendActBusinessDao.queryActBusByModelId(modelId);
        //业务实体类
        String classurl = businessEntity.getClassurl();
        List<Map<String, Object>> writes = new ArrayList<>(); //可写
        List<Map<String, Object>> judgs = new ArrayList<>();//可设置为条件
        Map<String, Object> temMap = new HashMap<>();
        temMap.put("value", "isAgree");
        temMap.put("name", "是否通过");
        judgs.add(temMap);
      //  writes.add(temMap);
        List<Map<String, Object>> mapList = AnnotationUtils.getActFieldByClazz(classurl);
        for (Map remap: mapList) {
            temMap = new HashMap<>();
            ActField actField = (ActField) remap.get("actField");
            String keyName = (String) remap.get("keyName");
            if (actField != null) {
                temMap.put("value", keyName);
                temMap.put("name", actField.name());
                writes.add(temMap);
                if (actField.isJudg()) {
                    temMap.put("allow", actField.isJudg());
                    judgs.add(temMap);
                }
            }
        }
        businessEntity.setJudgs(judgs);
        businessEntity.setWrites(writes);
        return businessEntity;
    }

    @Override
    public List<Map<String, Object>> queryCalBackByPid(String parentId) {
        return extendActBusinessDao.queryCalBackByPid(parentId);
    }

    @Override
    public List<ExtendActBusinessEntity> queryListByBean(ExtendActBusinessEntity businessEntity) {
        List<ExtendActBusinessEntity> busList = extendActBusinessDao.queryListByBean(businessEntity);
        UserEntity currentUser = UserUtils.getCurrentUser();
        if (busList == null || busList.size() < 1) {
            ExtendActBusinessEntity bus = new ExtendActBusinessEntity();
            bus.setId(Utils.uuid());
            bus.setName("业务树根目录");
            bus.setOpen("true");
            bus.setActKey("root");
            bus.setParentId(bus.getId());
            bus.setSort("1");
            bus.setType(Constant.ActBusType.ROOT.getValue());
            bus.setCreateTime(new Date());
            bus.setBaid(currentUser.getBaid());
            bus.setBapid(currentUser.getBapid());
            bus.setCreateId(currentUser.getId());
            bus.setRemark("业务树初始化");
            int count = extendActBusinessDao.save(bus);
            if (count > 0) {
                busList.add(bus);
            }
        }
        return busList;
    }

    @Override
    public int edit(ExtendActBusinessEntity businessEntity) {
        UserEntity currentUser = UserUtils.getCurrentUser();
        businessEntity.setBaid(currentUser.getBaid()); //未改
        businessEntity.setBapid(currentUser.getBapid());
        if (StringUtils.isEmpty(businessEntity.getId())) {
            //保存
            businessEntity.setCreateId(currentUser.getId());
            businessEntity.setCreateTime(new Date());
            businessEntity.setId(Utils.uuid());
            return extendActBusinessDao.save(businessEntity);
        } else {
            //更新
            businessEntity.setUpdateId(currentUser.getId());
            businessEntity.setUpdateTime(new Date());
            return extendActBusinessDao .update(businessEntity);
        }
    }

    @Override
    public ExtendActBusinessEntity queryObject(String id) {
        if (StringUtils.isEmpty(id)) {
            throw new MyException("节点id不能为空");
        }
        return extendActBusinessDao.queryObject(id);
    }

    @Override
    public int delete(String id) {
        return extendActBusinessDao.delete(id);
    }

    @Override
    public ExtendActBusinessEntity queryByActKey(String actKey) {
        return extendActBusinessDao.queryByActKey(actKey);
    }
}
