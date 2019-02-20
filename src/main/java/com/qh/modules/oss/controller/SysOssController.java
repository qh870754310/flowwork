package com.qh.modules.oss.controller;

import com.alibaba.fastjson.JSON;
import com.qh.modules.common.common.Constant;
import com.qh.modules.common.common.RRException;
import com.qh.modules.common.utils.PageUtils;
import com.qh.modules.common.utils.Query;
import com.qh.modules.common.utils.Result;
import com.qh.modules.common.utils.Utils;
import com.qh.modules.common.validator.ValidatorUtils;
import com.qh.modules.common.validator.group.AliyunGroup;
import com.qh.modules.common.validator.group.QcloudGroup;
import com.qh.modules.common.validator.group.QiniuGroup;
import com.qh.modules.oss.cloud.CloudStorageConfig;
import com.qh.modules.oss.cloud.OSSFactory;
import com.qh.modules.oss.entity.SysOssEntity;
import com.qh.modules.oss.service.SysOssService;
import com.qh.modules.sys.service.SysConfigService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 类SysOssController的功能描述:
 * 文件上传
 *
 * Created by Administrator on 2018/5/14.
 */
@RestController
@RequestMapping("/sys/oss")
public class SysOssController {

    @Autowired
    private SysOssService sysOssService;
    @Autowired
    private SysConfigService sysConfigService;

    private static final String KEY = Constant.CLOUD_STORAGE_CONFIG_KEY;

    /**
     * 列表
     *
     * @param params
     * @return
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:oss:all")
    public Result list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<SysOssEntity> sysOssList = sysOssService.queryList(query);
        int total = sysOssService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(sysOssList, total, query.getLimit(), query.getPage());
        return Result.ok().put("page", pageUtil);
    }

    /**
     * 云存储配置信息
     *
     * @return
     */
    @RequestMapping("/config")
    @RequiresPermissions("sys:oss:all")
    public Result config() {
        CloudStorageConfig config = sysConfigService.getConfigObject(KEY, CloudStorageConfig.class);
        return Result.ok().put("config", config);
    }

    /**
     * 保存云存储配置信息
     *
     * @param config
     * @return
     */
    @RequestMapping("/saveConfig")
    @RequiresPermissions("sys:oss:all")
    public Result saveConfig(@RequestBody CloudStorageConfig config) {
        //校验类型
        ValidatorUtils.validateEntity(config);
        if (config.getType() == Constant.CloudService.QINIU.getValue()) {
            //校验七牛数据
            ValidatorUtils.validateEntity(config, QiniuGroup.class);
        } else if (config.getType() == Constant.CloudService.ALIYUN.getValue()) {
            //校验阿里云数据
            ValidatorUtils.validateEntity(config, AliyunGroup.class);
        } else if (config.getType() == Constant.CloudService.QCLOUD.getValue()) {
            //校验腾讯云数据
            ValidatorUtils.validateEntity(QcloudGroup.class);
        }

        sysConfigService.updateValueByKey(KEY, JSON.toJSONString(config));
        return Result.ok();
    }

    /**
     * 上传文件
     *
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping("/upload")
    @RequiresPermissions("sys:oss:all")
    public Result update(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new RRException("上传文件不能为空");
        }
        //上传文件
        String url = OSSFactory.build().upload(file.getBytes());
        //保存文件信息
        SysOssEntity ossEntity = new SysOssEntity();
        ossEntity.setUrl(url);
        ossEntity.setCreateDate(new Date());
        sysOssService.save(ossEntity);
        return Result.ok().put("url", url);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:oss:all")
    public Result delete(@RequestBody Long[] ids) {
        sysOssService.deleteBatch(ids);
        return Result.ok();
    }
}
