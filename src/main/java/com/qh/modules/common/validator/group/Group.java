package com.qh.modules.common.validator.group;

import javax.validation.GroupSequence;

/**
 * 类Group的功能描述:
 * 定义校验顺序，如果AddGroup组失败，则UpdateGroup组不会再校验
 *
 * Created by Administrator on 2018/5/13.
 */
@GroupSequence({AddGroup.class, UpdateGroup.class})
public interface Group {

}
