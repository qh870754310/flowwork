package com.qh.modules.gen.utils;

import com.qh.FlowworkApplication;
import com.qh.modules.common.common.Constant;
import com.qh.modules.gen.service.SysGeneratorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * 类的功能描述.
 *
 * Created by Administrator on 2018/5/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FlowworkApplication.class)
public class GenLocalUtils {

    @Resource
    private SysGeneratorService generatorService;

    @Test
    public void generatorCode(){
        byte[] bytes=generatorService.generatorCode(new String[]{"sys_gentest"}, Constant.genType.local.getValue());
    }
}
