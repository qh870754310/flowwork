package com.qh.modules.sh.controller;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import com.qh.modules.common.controller.BaseController;
import com.qh.modules.sh.entity.ShNoticeEntity;
import com.qh.modules.sh.service.ShNoticeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qh.modules.common.utils.PageUtils;
import com.qh.modules.common.utils.Query;
import com.qh.modules.common.utils.Result;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;


/**
 * 上海科技学院通知公告表
 * 
 * @author qh
 * @email 870754310@qq.com
 * @date 2018-07-02 14:30:01
 */
@RestController
@RequestMapping("shnotice")
public class ShNoticeController extends BaseController{
	@Autowired
	private ShNoticeService shNoticeService;

	@Value("${web.upload-path}")
	private String ImagesPath;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("shnotice:list")
	public Result list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ShNoticeEntity> shNoticeList = shNoticeService.queryList(query);
		int total = shNoticeService.queryTotal(query);
		PageUtils pageUtil = new PageUtils(shNoticeList, total, query.getLimit(), query.getPage());
		return Result.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("shnotice:info")
	public Result info(@PathVariable("id") String id){
		ShNoticeEntity shNotice = shNoticeService.queryObject(id);
		return Result.ok().put("shNotice", shNotice);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("shnotice:save")
	public Result save(@RequestBody ShNoticeEntity shNotice){
		shNoticeService.save(shNotice);
		return Result.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("shnotice:update")
	public Result update(@RequestBody ShNoticeEntity shNotice){
		shNoticeService.update(shNotice);
		return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("shnotice:delete")
	public Result delete(@RequestBody String[] ids){
		shNoticeService.deleteBatch(ids);
		return Result.ok();
	}

	@RequestMapping("/upload")
	public Result upload(HttpServletRequest request) {
		String address = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;//转换成多部分request
		String rootPath = this.ImagesPath;
		String basePath = UUID.randomUUID().toString().replaceAll("-", "");
		String realPath = rootPath + basePath;
		String savename = "";
		String realname = "";
		try {
			Map<String, MultipartFile> fileMap = multiRequest.getFileMap();
			for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
				MultipartFile file = entity.getValue();
				String name = file.getOriginalFilename(); //上传文件的真实名称
				String suffixName = name.substring(name.lastIndexOf(".")); //获取后缀名
				String hash = Integer.toHexString(new Random().nextInt()); //自定义随机数（字母+数字）作为文件名
				String fileName = hash + suffixName;
				File tempFile = new File(realPath, fileName);
				if (!tempFile.getParentFile().exists()) {
					tempFile.getParentFile().mkdir();
				}
				if (tempFile.exists()) {
					tempFile.delete();
				}
				tempFile.createNewFile();
				file.transferTo(tempFile);
				savename = tempFile.getName();
				realname = name;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("上传图片失败!");
		}
		return Result.ok().put("url", address + "/" + basePath + File.separator + savename);
	}
	
}
