package com.qh.modules.demo.callBack;

import com.qh.modules.activiti.dto.ProcessTaskDto;
import org.springframework.stereotype.Component;

/**
 * 类的功能描述：
 *
 * Created by Administrator on 2018/5/13.
 */
@Component
public class ActCallBack {

    public void leaveBack(ProcessTaskDto processTaskDto) {
        System.out.println("请假回调成功啦！！！！！！！");
    }
}
