package com.qh.modules.sh.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.qh.modules.common.controller.BaseController;
import com.qh.modules.common.utils.StringUtils;
import com.qh.modules.sh.entity.ShFamilyMemberEntity;
import com.qh.modules.sh.entity.ShStudentEntity;
import com.qh.modules.sh.service.ShFamilyMemberService;
import com.qh.modules.sh.service.ShStudentService;
import com.qh.modules.sh.utils.FilterUtil;
import com.qh.modules.sys.service.CodeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.qh.modules.common.utils.PageUtils;
import com.qh.modules.common.utils.Query;
import com.qh.modules.common.utils.Result;


/**
 * 
 * 
 * @author qh
 * @email 870754310@qq.com
 * @date 2018-07-25 09:21:11
 */
@RestController
@RequestMapping("shfamilymember")
public class ShFamilyMemberController extends BaseController{
	@Autowired
	private ShFamilyMemberService shFamilyMemberService;
	@Autowired
	private ShStudentService shStudentService;

	@Autowired
	private CodeService codeService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("shfamilymember:list")
	public Result list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ShFamilyMemberEntity> shFamilyMemberList = shFamilyMemberService.queryList(query);
		FilterUtil.filter_ShFamilyMemberEntity(shFamilyMemberList);
		int total = shFamilyMemberService.queryTotal(query);
		PageUtils pageUtil = new PageUtils(shFamilyMemberList, total, query.getLimit(), query.getPage());
		return Result.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("shfamilymember:info")
	public Result info(@PathVariable("id") String id){
		ShFamilyMemberEntity shFamilyMember = shFamilyMemberService.queryObject(id);
		
		return Result.ok().put("shFamilyMember", shFamilyMember);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("shfamilymember:save")
	public Result save(@RequestBody ShFamilyMemberEntity shFamilyMember){
		shFamilyMemberService.save(shFamilyMember);
		
		return Result.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("shfamilymember:update")
	public Result update(@RequestBody ShFamilyMemberEntity shFamilyMember){
		shFamilyMemberService.update(shFamilyMember);
		
		return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("shfamilymember:delete")
	public Result delete(@RequestBody String[] ids){
		shFamilyMemberService.deleteBatch(ids);
		
		return Result.ok();
	}

	@RequestMapping("/getStudentList")
	public Result getStudentList() {
		Map<String, Object> map = new HashMap<>();
		List<ShStudentEntity>  list = shStudentService.queryList(map);
		return Result.ok().put("list", list);
	}
	
}
