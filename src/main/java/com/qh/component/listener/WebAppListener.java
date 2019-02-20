package com.qh.component.listener;

import com.qh.modules.common.cache.CodeCache;
import com.qh.modules.common.common.Constant;
import com.qh.modules.common.utils.RedisUtil;
import com.qh.modules.common.utils.StringUtils;
import com.qh.modules.sys.dao.CodeDao;
import com.qh.modules.sys.entity.CodeEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 当容器启动时获取当前容器的可请求地址，并把它放到缓存中
 *
 * Created by Administrator on 2018/5/9.
 */
@Service
public class WebAppListener implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(WebAppListener.class);

    @Autowired
    ApplicationContext webApplicationContext;

    @Autowired
    private CodeDao codeDao;

    @Autowired
    private RedisUtil redisUtils;

    /**
     * 实现EnvironmentAware接口，初始化系统数据。
     *
     * @param contextRefreshedEvent
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        codeCache();
    }

    /**
     * 缓存全部数据字典
     *
     */
    public void codeCache() {

        List<CodeEntity> allCode = codeDao.queryAllCode();
        Map<String, Map<String, Object>> allMap = new HashMap<>();
        if (allCode != null && allCode.size() > 0) {
            Map<String, Object> attrMap = null;
            for (CodeEntity code: allCode) {
                attrMap = new HashMap<>();
                attrMap.put("id", code.getId());
                attrMap.put("value",code.getValue());
                attrMap.put("childs",code.getChilds());
                attrMap.put("mark",code.getMark());
                attrMap.put("name",code.getName());
                allMap.put(code.getMark(),attrMap);
            }
        }

        for (String key:allMap.keySet()){
            //父字典
            Map<String, Object> parentMap = allMap.get(key);
            String childStr = (String) parentMap.get("childs");
            if(StringUtils.isEmpty(childStr)){
                continue;
            }
            String[] split = childStr.split(",");
            List<Map<String, Object>> childMaps = new ArrayList<>();

            for (String str:split){
                //子字典
                Map<String, Object> childMap = allMap.get(str);
                childMaps.add(childMap);
            }
            //将所有子字典设置到父级字典
            parentMap.put("childList",childMaps);
        }
        CodeCache.put(Constant.CODE_CACHE,allMap);
        try {
            redisUtils.setObject(Constant.CODE_CACHE,allMap);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
