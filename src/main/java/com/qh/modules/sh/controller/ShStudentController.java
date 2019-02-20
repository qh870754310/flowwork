package com.qh.modules.sh.controller;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.qh.modules.common.controller.BaseController;
import com.qh.modules.common.utils.*;
import com.qh.modules.sh.entity.ShStudentEntity;
import com.qh.modules.sh.service.ShStudentService;
import com.qh.modules.sys.service.CodeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;


/**
 * 
 * 
 * @author qh
 * @email 870754310@qq.com
 * @date 2018-07-20 13:22:00
 */
@RestController
@RequestMapping("shstudent")
public class ShStudentController extends BaseController{
	@Autowired
	private ShStudentService shStudentService;

	@Autowired
	private CodeService codeService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("shstudent:list")
	public Result list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ShStudentEntity> shStudentList = shStudentService.queryList(query);
        setVO(shStudentList);
		/*shStudentList.stream().forEach(s -> {
			if (!StringUtils.isEmpty(s.getSex())) {
				s.setSex_code(codeService.queryObject(s.getSex()).getName());
			}
			if (!StringUtils.isEmpty(s.getNation())) {
				s.setNation_code(codeService.queryObject(s.getNation()).getName());
			}
			if (!StringUtils.isEmpty(s.getSourceCountry())) {
				s.setSourceCountry_code(codeService.queryObject(s.getSourceCountry()).getName());
			}
			if (!StringUtils.isEmpty(s.getPoliticalStatus())) {
				s.setPolitical_code(codeService.queryObject(s.getPoliticalStatus()).getName());
			}
			if (!StringUtils.isEmpty(s.getCertificateType())) {
				s.setCertificate_code(codeService.queryObject(s.getCertificateType()).getName());
			}
			if (!StringUtils.isEmpty(s.getHealthCondition())) {
				s.setHealth_code(codeService.queryObject(s.getHealthCondition()).getName());
			}
			if (!StringUtils.isEmpty(s.getMaritalStatus())) {
				s.setMarital_code(codeService.queryObject(s.getMaritalStatus()).getName());
			}
			if (!StringUtils.isEmpty(s.getBloodType())) {
				s.setBlood_code(codeService.queryObject(s.getBloodType()).getName());
			}
			if (!StringUtils.isEmpty(s.getEmigrantcode())) {
				s.setEmigrantcode_code(codeService.queryObject(s.getEmigrantcode()).getName());
			}
			if (!StringUtils.isEmpty(s.getHouseholdRegistrationType())) {
				s.setHouseholdRegistrationType_code(codeService.queryObject(s.getHouseholdRegistrationType()).getName());
			}
		});*/
		int total = shStudentService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(shStudentList, total, query.getLimit(), query.getPage());
		
		return Result.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("shstudent:info")
	public Result info(@PathVariable("id") String id){
		ShStudentEntity shStudent = shStudentService.queryObject(id);
		
		return Result.ok().put("shStudent", shStudent);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("shstudent:save")
	public Result save(@RequestBody ShStudentEntity shStudent){
		shStudentService.save(shStudent);
		return Result.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("shstudent:update")
	public Result update(@RequestBody ShStudentEntity shStudent){
		shStudentService.update(shStudent);
		
		return Result.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("shstudent:delete")
	public Result delete(@RequestBody String[] ids){
		shStudentService.deleteBatch(ids);
		
		return Result.ok();
	}

	/**
	 * 导出
	 * @return
	 */
	@RequestMapping("/import")
	@RequiresPermissions("shstudent:import")
	public void importExcel(HttpServletResponse response) {
		try {
			List<String[]> columNames = new ArrayList<String[]>();
			columNames.add(new String[]{"学号", "姓名", "学籍号", "姓名拼音", "性别", "学生类别", "学生身份", "院系", "现在年级", "专业", "班级", "曾用名", "婚姻状况", "出生日期", "身份证件类型",
					"身份证件号", "考生号", "准考证号", "生源地", "籍贯", "身高(cm)", "体重(kg)", "民族", "政治面貌", "来源国别", "户口所在地", "港澳台侨", "入学前户口性质", "健康状况", "血型",
					"一卡通号", "手机卡号", "开户银行", "银行卡号", "开户银行2", "银行卡号2", "录取通知书邮递编号", "报考省市", "入学前单位", "入学日期", "入学年级", "入学院系", "入学专业", "入学方式",
			"培养方式", "学生来源", "校区", "就读学位", "就读学历", "学制", "预计毕业年份", "实际毕业时间", "通讯地址", "通讯邮编", "电子邮箱", "其他联系方式", "手机号", "手机号2", "MSN号", "QQ号", "个人主页",
					"家庭地址", "家庭邮编", "家庭电话", "家庭电子邮箱", "注册学年", "注册学期", "在校", "在籍", "特长", "高考成绩", "备注", "军训信息", "毕业类型", "毕业证书编号", "考区"});
			List<String[]> fieldNames = new ArrayList<String[]>();
			fieldNames.add(new String[]{"studentNo", "name", "studentStatus", "spellname", "sex_code", "student_type_code", "student_identity_code", "department_code", "grade", "profession_code", "class_code", "usedname", "marital_code", "birthday", "certificate_code",
            "idCard", "candidateNo", "ticketNo", "localInstitution", "nativePlace", "height", "weight", "nation_code", "political_code", "sourceCountry_code", "domicilePlace", "emigrantcode_code", "householdRegistrationType_code", "Health_code", "blood_code",
            "metroCard", "mobileno", "bankAccountF", "bankCard",  "bankAccountS", "bankAccount", "postalCodes", "bkss", "rxqdw", "rxrq", "rxnj", "rxyx_code", "rxzy_code", "rxfs_code",
					"pyfs_code", "xsly", "xq_code", "jdxw_code", "jdxl_code", "xz", "yjbynf", "sjbysj", "postaladdress", "postalcode", "email", "otherWay", "homePhone", "mobile", "msn", "qq", "homepage",
					"homeAddress", "homePostal", "homePhone", "familyEmail", "zcxn_code", "zcxq_code", "zx_code", "zj_code", "speciality", "gkcj", "remark", "jxxx", "bylx_code", "byzsbh", "kq"});
			LinkedHashMap<String, List<?>> map = new LinkedHashMap<String, List<?>>();
            List<ShStudentEntity> studentData = shStudentService.getStudentData();
            setVO(studentData);
            map.put("学生基本信息", studentData);
			ExcelUtil.ExcelExportData setInfo = new ExcelUtil.ExcelExportData();
			setInfo.setDataMap(map);
			setInfo.setFieldNames(fieldNames);
			setInfo.setTitles(new String[]{"学生基本信息"});
			setInfo.setColumnNames(columNames);
			// 将需要导出的数据输出到文件
			String fileName = "学生基本信息";
			OutputStream out = response.getOutputStream();
			response.reset(); //清空response
			// 设置response的Header
			response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1") + ".xls");
			response.setContentType("application/x-download");
	//		ByteArrayOutputStream outputStream = ExcelUtil.export2Stream(setInfo);
			ExcelUtil.exportExcel(fileName, setInfo, out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setVO(List<ShStudentEntity> studentData) {
        studentData.stream().forEach(s -> {
            if (!StringUtils.isEmpty(s.getSex())) {
                s.setSex_code(codeService.queryObject(s.getSex()).getName());
            }
            if (!StringUtils.isEmpty(s.getNation())) {
                s.setNation_code(codeService.queryObject(s.getNation()).getName());
            }
            if (!StringUtils.isEmpty(s.getSourceCountry())) {
                s.setSourceCountry_code(codeService.queryObject(s.getSourceCountry()).getName());
            }
            if (!StringUtils.isEmpty(s.getPoliticalStatus())) {
                s.setPolitical_code(codeService.queryObject(s.getPoliticalStatus()).getName());
            }
            if (!StringUtils.isEmpty(s.getCertificateType())) {
                s.setCertificate_code(codeService.queryObject(s.getCertificateType()).getName());
            }
            if (!StringUtils.isEmpty(s.getHealthCondition())) {
                s.setHealth_code(codeService.queryObject(s.getHealthCondition()).getName());
            }
            if (!StringUtils.isEmpty(s.getMaritalStatus())) {
                s.setMarital_code(codeService.queryObject(s.getMaritalStatus()).getName());
            }
            if (!StringUtils.isEmpty(s.getHealthCondition())) {
            	s.setHealth_code(codeService.queryObject(s.getHealthCondition()).getName());
			}
            if (!StringUtils.isEmpty(s.getBloodType())) {
                s.setBlood_code(codeService.queryObject(s.getBloodType()).getName());
            }
            if (!StringUtils.isEmpty(s.getEmigrantcode())) {
                s.setEmigrantcode_code(codeService.queryObject(s.getEmigrantcode()).getName());
            }
            if (!StringUtils.isEmpty(s.getHouseholdRegistrationType())) {
                s.setHouseholdRegistrationType_code(codeService.queryObject(s.getHouseholdRegistrationType()).getName());
            }

            if (!StringUtils.isEmpty(s.getStudentCategory())) {
            	s.setStudent_type_code(codeService.queryObject(s.getStudentCategory()).getName());
			}
			if (!StringUtils.isEmpty(s.getStudentIdentity())) {
            	s.setStudent_identity_code(codeService.queryObject(s.getStudentIdentity()).getName());
			}

			if (!StringUtils.isEmpty(s.getDepartment())) {
            	s.setDepartment_code(codeService.queryObject(s.getDepartment()).getName());
			}
			if (!StringUtils.isEmpty(s.getProfession())) {
            	s.setProfession_code(codeService.queryObject(s.getProfession()).getName());
			}
			if (!StringUtils.isEmpty(s.getStuClass())) {
            	s.setClass_code(codeService.queryObject(s.getStuClass()).getName());
			}

			if (!StringUtils.isEmpty(s.getRxyx())) {
            	s.setRxyx_code(codeService.queryObject(s.getRxyx()).getName());
			}

			if (!StringUtils.isEmpty(s.getRxzy())) {
            	s.setRxzy_code(codeService.queryObject(s.getRxzy()).getName());
			}

			if (!StringUtils.isEmpty(s.getRxfs())) {
            	s.setRxfs_code(codeService.queryObject(s.getRxfs()).getName());
			}

			if (!StringUtils.isEmpty(s.getPyfs())) {
            	s.setPyfs_code(codeService.queryObject(s.getPyfs()).getName());
			}
			if (!StringUtils.isEmpty(s.getXq())) {
            	s.setXq_code(codeService.queryObject(s.getXq()).getName());
			}
			if (!StringUtils.isEmpty(s.getJdxl())) {
            	s.setJdxl_code(codeService.queryObject(s.getJdxl()).getName());
			}
			if (!StringUtils.isEmpty(s.getJdxw())) {
            	s.setJdxw_code(codeService.queryObject(s.getJdxw()).getName());
			}
			if (!StringUtils.isEmpty(s.getZcxn())) {
            	s.setZcxn_code(codeService.queryObject(s.getZcxn()).getName());
			}
			if (!StringUtils.isEmpty(s.getZcxq())) {
            	s.setZcxq_code(codeService.queryObject(s.getZcxq()).getName());
			}
			if (!StringUtils.isEmpty(s.getBylx())) {
            	s.setBylx_code(codeService.queryObject(s.getBylx()).getName());
			}
			if (!StringUtils.isEmpty(s.getZj())) {
            	if (s.getZj()) {
					s.setZj_code("是");
				} else {
					s.setZj_code("否");
				}
			}
			if (!StringUtils.isEmpty(s.getZx())) {
				if (s.getZx()) {
					s.setZx_code("是");
				} else {
					s.setZx_code("否");
				}
			}
        });
    }
	
}
