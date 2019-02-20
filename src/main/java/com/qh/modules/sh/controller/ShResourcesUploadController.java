package com.qh.modules.sh.controller;

import com.qh.modules.common.controller.BaseController;
import com.qh.modules.common.utils.PageUtils;
import com.qh.modules.common.utils.Query;
import com.qh.modules.common.utils.Result;
import com.qh.modules.sh.entity.ShResourcesUploadEntity;
import com.qh.modules.sh.service.ShResourcesUploadService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;
import java.util.stream.Collectors;


/**
 * 文件管理
 * 
 * @author qh
 * @email 870754310@qq.com
 * @date 2018-07-03 09:58:17
 */
@RestController
@RequestMapping("shresourcesupload")
public class ShResourcesUploadController extends BaseController{

	//获取配置文件中文件的路径
	@Value("${web.upload-path}")
	private String ImagesPath;

	@Autowired
	private ShResourcesUploadService shResourcesUploadService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("shresourcesupload:list")
	public Result list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ShResourcesUploadEntity> shResourcesUploadList = shResourcesUploadService.queryList(query);
		shResourcesUploadList = shResourcesUploadList.stream().sorted(Comparator.comparing(ShResourcesUploadEntity::getType).thenComparing(ShResourcesUploadEntity::getOrder)).collect(Collectors.toList());
		int total = shResourcesUploadService.queryTotal(query);
		PageUtils pageUtil = new PageUtils(shResourcesUploadList, total, query.getLimit(), query.getPage());
		return Result.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("shresourcesupload:info")
	public Result info(HttpServletRequest request, @PathVariable("id") String id){
		String address = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
		ShResourcesUploadEntity shResourcesUpload = shResourcesUploadService.queryObject(id);
		return Result.ok().put("shResourcesUpload", shResourcesUpload).put("imageAdress", address);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("shresourcesupload:save")
	public Result save(@RequestBody ShResourcesUploadEntity shResourcesUpload){
		shResourcesUploadService.save(shResourcesUpload);
		return Result.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("shresourcesupload:update")
	public Result update(@RequestBody ShResourcesUploadEntity shResourcesUpload){
		shResourcesUploadService.update(shResourcesUpload);
		return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("shresourcesupload:delete")
	public Result delete(@RequestBody String[] ids){
		shResourcesUploadService.deleteBatch(ids);
		return Result.ok();
	}


	@RequestMapping("/upload")
	public Result upload(HttpServletRequest request) {
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
		return Result.ok().put("path", basePath + File.separator + savename).put("name", realname);
	}

	@PostMapping("/getImagesByType/{type}")
	public Result getImagesByType(@PathVariable("type") String type) {
		List<ShResourcesUploadEntity> shResourcesUploadList = shResourcesUploadService.getImagesByType(type);
		return  Result.ok().put("shResourcesUploadList",  shResourcesUploadList);
	}

}
