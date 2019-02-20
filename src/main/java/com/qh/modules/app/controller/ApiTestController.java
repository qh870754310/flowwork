package com.qh.modules.app.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qh.modules.app.annotation.CurrentUser;
import com.qh.modules.app.annotation.LoginRequired;
import com.qh.modules.common.utils.DbUtil;
import com.qh.modules.common.utils.Result;
import com.qh.modules.common.utils.StringUtils;
import com.qh.modules.common.utils.Utils;
import com.qh.modules.sh.entity.*;
import com.qh.modules.sh.service.ShFamilyMemberService;
import com.qh.modules.sh.service.ShNoticeService;
import com.qh.modules.sh.service.ShResourcesUploadService;
import com.qh.modules.sh.service.ShStudentService;
import com.qh.modules.sh.utils.FilterUtil;
import com.qh.modules.sys.entity.CodeEntity;
import com.qh.modules.sys.entity.UserEntity;
import com.qh.modules.sys.service.CodeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 类ApiTestController的功能描述:
 * app测试接口
 *
 * Created by Administrator on 2018/5/28.
 */
@Controller
@RequestMapping("/app")
public class ApiTestController {

    @Autowired
    private ShResourcesUploadService shResourcesUploadService;

    @Autowired
    private ShStudentService shStudentService;

    @Autowired
    private ShNoticeService shNoticeService;

    @Autowired
    private CodeService codeService;

    @Autowired
    private ShFamilyMemberService shFamilyMemberService;

    /**
     * 获取用户信息
     *
     * @param user
     * @return
     */
    @LoginRequired
    @GetMapping("userInfo")
    public Result userInfo(@CurrentUser UserEntity user) {
        return Result.ok().put("user", user);
    }

    /**
     * 获取用户ID
     * @param userId
     * @return
     */
    @LoginRequired
    @GetMapping("userId")
    public Result userInfo(@RequestAttribute("userId") String userId) {
        return Result.ok().put("userId", userId);
    }

    /**
     * 忽略Token验证测试
     *
     * @return
     */
    @GetMapping("notToken")
    public Result notToken() {
        return Result.ok().put("msg", "无需token也能访问。。。");
    }

    @LoginRequired
    @GetMapping(value = "/getImagesByType", produces = {"application/json;charset=utf-8"})
	@ResponseBody
    public Result getImagesByType(HttpServletRequest request, String type) {
        String address = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
        List<ShResourcesUploadEntity> shResourcesUploadList = shResourcesUploadService.getImagesByType(type);
        shResourcesUploadList.stream().forEach(ShResourcesUploadEntity -> ShResourcesUploadEntity.setPath(address + ShResourcesUploadEntity.getPath()));
        Map<String, Object> map = new HashMap<>();
        map.put("shResourcesUploadList", shResourcesUploadList);
        return  Result.ok().put("shResourcesUploadList", shResourcesUploadList);
    }

    /**
     * 查询通知公告列表
     * @param params
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getNotices", produces = {"application/json;charset=utf-8"})
    public Result getNotices(@RequestBody String params) {
        Map map = new HashMap();
        JSONObject text = JSON.parseObject(params);
        Integer offset = text.getInteger("offset");
        Integer limit = text.getInteger("limit");
        String sidx = text.getString("sidx");
        String order = text.getString("order");
        map.put("offset", offset);
        map.put("limit", limit);
        map.put("sidx", sidx);
        map.put("order", order);
        List<ShNoticeEntity> shNoticeEntities = shNoticeService.queryList(map);
        map.clear();
        map.put("shNoticeEntities", shNoticeEntities);
        return Result.ok(map);
    }

    /**
     * 绑定用户
     *
     * @param request  HttpServletRequest对象
     * @param response HttpServletResponse对象
     * @param params
     */
    @RequestMapping(value = "/wechat/bind", produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public Result bindUser(HttpServletRequest request, HttpServletResponse response, @RequestBody String params) {
        JSONObject text = JSON.parseObject(params);
        String openId = text.getString("openId");
        String idCard = text.getString("idCard");
        ShStudentEntity shStudentEntity = shStudentService.queryObjectByCardNo(idCard);
        Map<String, Object> mp = new HashMap<>();
        if (shStudentEntity == null) { //用户不存在
            mp.put("state", false);
            mp.put("errorCode", 1);
        } else {
            Map map = shStudentService.queryObjectByOpenId(openId);
            if (map == null) {
                try {
                    shStudentService.addBind(shStudentEntity.getId(), openId);
                    mp.put("state", true);
                } catch (Exception e) {
                    mp.put("state", false);
                    mp.put("errorCode", 2);
                }
            } else {
                mp.put("state", false);
                mp.put("errorCode", 3);
            }
        }
        return Result.ok(mp);
    }

    /**
     * 查询通知公告详细信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/info/{id}")
    @ResponseBody
    public Result info(@PathVariable("id") String id){
        ShNoticeEntity shNotice = shNoticeService.queryObject(id);
        return Result.ok().put("shNotice", shNotice);
    }

    @RequestMapping("/getImagesByTypes/{type}")
    @ResponseBody
    public Result getImagesByTypes(HttpServletRequest request, @PathVariable("type") String type) {
        String address = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
        List<ShResourcesUploadEntity> shResourcesUploadList = shResourcesUploadService.getImagesByType(type);
        shResourcesUploadList.stream().forEach(ShResourcesUploadEntity -> ShResourcesUploadEntity.setPath(address + ShResourcesUploadEntity.getPath()));
        Map<String, Object> map = new HashMap<>();
        map.put("shResourcesUploadList", shResourcesUploadList);
        return  Result.ok().put("shResourcesUploadList", shResourcesUploadList);
    }

    /**
     * 获取数据字典
     * @param mark
     * @return
     */
 //   @CrossOrigin(origins = {"http://183.195.133.44:81", "null"})
    @RequestMapping("/getCodeByMark/{mark}")
    @ResponseBody
    public Result getCodeByMark(@PathVariable("mark") String mark) {
        List<CodeEntity> codeEntitys =  codeService.queryChildsByMark(mark).stream().sorted(Comparator.comparing(CodeEntity::getValue)).collect(Collectors.toList());
        return  Result.ok().put("codeEntitys", codeEntitys);
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public Result save(@RequestBody ShFamilyMemberEntity shFamilyMember){
        shFamilyMemberService.save(shFamilyMember);
        return Result.ok();
    }

    /**
     * 根据身份证号码本地库中是否存在用户信息
     * @param idCard
     * @return
     */
    @RequestMapping("/getLocalStudentInfoByCard/{idCard}")
    @ResponseBody
    public Result getLocalStudentInfoByCard(@PathVariable("idCard") String idCard) {
        ShStudentEntity shStudentEntity = shStudentService.getLocalStudentInfoByCard(idCard);
        if (!org.springframework.util.StringUtils.isEmpty(shStudentEntity)) {
            return Result.ok().put("shStudentEntity", shStudentEntity).put("flag", 1);
        } else {
            return Result.ok().put("flag", -1);
        }
    }

    /**
     * 根据身份证号码查询学生信息
     *
     * @param IdCard 身份证号码
     * @return
     */
    @RequestMapping("/getStudentInfoByCard/{IdCard}")
    @ResponseBody
    public Result getStudentInfoByCard(@PathVariable("IdCard") String IdCard) {
  //      ShStudentEntity shStudent = shStudentService.getStudentInfoByCard(IdCard);
        ShStudentEntity shStudent = shStudentService.findStudentByCardId(IdCard);
        return Result.ok().put("shStudent", shStudent);
    }

    /**
     * 根据学生id查询信息
     * @param id
     * @return
     */
    @RequestMapping("/getStudentInfoById/{id}")
    @ResponseBody
    public Result getStudentInfoById(@PathVariable("id") String id) {
        ShStudentEntity shStudentEntity = shStudentService.queryObject(id);
        return Result.ok().put("shStudent", shStudentEntity);
    }

    @RequestMapping("/getTest/{IdCard}")
    @ResponseBody
    public Result getTest(@PathVariable("IdCard") String IdCard) {
        Map<String, String> resultMap = shStudentService.getTest(IdCard);
        return Result.ok().put("shStudent", resultMap);
    }

    /**
     * 学生基本信息保存
     *
     * @param shStudent
     * @return
     */
    @RequestMapping(value = "/shStudentSave", method = RequestMethod.POST)
    @ResponseBody
    public Result saveStudent(@RequestBody ShStudentEntity shStudent) {
        String uuid = Utils.uuid();
        shStudentService.saveLocal(shStudent, uuid);
        ShStudentEntity shStudentEntity = shStudentService.queryObject(uuid);
        return Result.ok().put("uuid", uuid).put("shStudentEntity", shStudentEntity);
    }

    /**
     * 保存学生联系方式
     * @param shStudent
     * @return
     */
    @RequestMapping(value = "/shStudentUpdate", method = RequestMethod.POST)
    @ResponseBody
    public Result updateStudent(@RequestBody ShStudentEntity shStudent) {
        shStudentService.update(shStudent);
        ShStudentEntity shStudentEntity = shStudentService.queryObject(shStudent.getId());
        return Result.ok().put("uuid", shStudent.getId()).put("shStudentEntity", shStudentEntity);
    }

    /**
     * 根据学生id查询其家庭成员列表
     *
     * @param idCard 学生身份证
     * @return
     */
    @RequestMapping("/getFamilyMemberByIdCard/{idCard}")
    @ResponseBody
    public Result getFamilyMemberByIdCard(@PathVariable("idCard") String idCard) {
        List<ShFamilyMemberEntity> shFamilyMemberEntities = shFamilyMemberService.getFamilyMemberByIdCard(idCard);
        FilterUtil.filter_ShFamilyMemberEntity(shFamilyMemberEntities);
        return Result.ok().put("shFamilyMemberEntities", shFamilyMemberEntities);
    }

    /**
     * 保存家庭成员
     * @param shFamilyMemberEntity
     * @return
     */
    @RequestMapping(value = "/saveFamilyMember", method = RequestMethod.POST)
    @ResponseBody
    public Result saveFamilyMember(@RequestBody ShFamilyMemberEntity shFamilyMemberEntity) {
        shFamilyMemberService.save(shFamilyMemberEntity);
        return Result.ok();
    }

    /**
     * 更新家庭成员信息
     * @param shFamilyMemberEntity
     * @return
     */
    @RequestMapping(value = "/updateFamilyMember", method = RequestMethod.POST)
    @ResponseBody
    public Result updateFamilyMember(@RequestBody ShFamilyMemberEntity shFamilyMemberEntity) {
        shFamilyMemberService.update(shFamilyMemberEntity);
        return Result.ok();
    }

    /**
     * 根据家庭成员id获取详细信息
     * @param id
     * @return
     */
    @RequestMapping("/getFamilyMemberById/{id}")
    @ResponseBody
    public Result getFamilyMemberById(@PathVariable("id") String id) {
        ShFamilyMemberEntity shFamilyMember = shFamilyMemberService.queryObject(id);
        return Result.ok().put("shFamilyMember", shFamilyMember);
    }

    /**
     * 根据考生号号码查询报到信息
     * @param candidateNo
     * @return
     */
    @RequestMapping(value = "/getStatusInfoByCandidateNo/{candidateNo}")
    @ResponseBody
    public Result getStatusInfoByCandidateNo(@PathVariable("candidateNo") String candidateNo) {
        Guidevt guidevt = shStudentService.getStatusInfoByCandidateNo(candidateNo);
        return Result.ok().put("guidevt", guidevt);
    }

}
