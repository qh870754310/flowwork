package com.qh.modules.sys.controller;

import com.qh.modules.common.annotation.SysLog;
import com.qh.modules.common.common.Constant;
import com.qh.modules.common.controller.BaseController;
import com.qh.modules.common.utils.PageUtils;
import com.qh.modules.common.utils.Query;
import com.qh.modules.common.utils.Result;
import com.qh.modules.common.utils.StringUtils;
import com.qh.modules.sys.entity.OrganEntity;
import com.qh.modules.sys.service.OrganService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 组织机构
 *
 * Created by Administrator on 2018/5/10.
 */
@RestController
@RequestMapping("sys/organ")
public class OrganController extends BaseController {

    @Autowired
    private OrganService organService;

    /**
     * 列表
     * @param params
     * @return
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:organ:all")
    public Result list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<OrganEntity> organList = organService.queryList(query);
        int total = organService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(organList, total, query.getLimit(), query.getPage());
        return Result.ok().put("page", pageUtil);
    }

    /**
     * 信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:organ:all")
    @SysLog("查看组织")
    public Result info(@PathVariable("id") String id) {
        OrganEntity organ = organService.queryObject(id);
        return Result.ok().put("organ", organ);
    }

    /**
     * 保存
     *
     * @param organ
     * @return
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:organ:all")
    @SysLog("保存组织")
    public Result save(@RequestBody OrganEntity organ) {

        List<OrganEntity> organEntities = organService.queryListByCode(organ.getCode());
        if (organEntities != null && organEntities.size() > 0) {
            return Result.error("机构编号已经存在,请重新输入!");
        }
        String id = organService.save(organ);
        if (StringUtils.isEmpty(id)) {
            return Result.error("保存" + organ.getName() + "失败!");
        }
        OrganEntity organEntity = organService.queryObject(id);
        return Result.ok("保存"+organ.getName()+"成功!").put("organInfo", organEntity);
    }

    /**
     * 修改
     *
     * @param organ
     * @return
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:organ:all")
    @SysLog("修改组织")
    public Result update(@RequestBody OrganEntity organ) {
        int count = organService.update(organ);
        if (count < 1) {
            return Result.error("修改" + organ.getName() + "失败!");
        }
        organ = organService.queryObject(organ.getId());
        return Result.ok("修改" + organ.getName() + "成功!").put("organInfo", organ);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:organ:all")
    @SysLog("删除组织")
    public Result delete(@RequestBody String ids) {
        organService.updateIsdel(ids, Constant.YESNO.YES.getValue());
        return Result.ok("删除成功!");
    }

    /**
     * 查询组织机构树
     *
     * @return
     */
    @RequestMapping(value = "/organTree")
    @RequiresPermissions("sys:organ:all")
    public Result codeTree() {
        List<OrganEntity> organEntities = organService.queryListByBean(new OrganEntity());
        return Result.ok().put("organTree", organEntities);
    }
}
