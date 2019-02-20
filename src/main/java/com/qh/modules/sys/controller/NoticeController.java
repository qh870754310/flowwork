package com.qh.modules.sys.controller;

import com.qh.modules.common.annotation.SysLog;
import com.qh.modules.common.controller.BaseController;
import com.qh.modules.common.page.Page;
import com.qh.modules.common.utils.DateUtils;
import com.qh.modules.common.utils.Result;
import com.qh.modules.common.utils.Utils;
import com.qh.modules.sys.entity.NoticeEntity;
import com.qh.modules.sys.service.NoticeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 通知
 *
 * Created by Administrator on 2018/5/3.
 */
@Controller
@RequestMapping("sys/notice")
public class NoticeController extends BaseController {

    @Autowired
    private NoticeService noticeService;

    /**
     * 我的通知列表
     *
     * @param model
     * @param noticeEntity
     * @return
     */
    @RequestMapping("/myList")
    @RequiresPermissions("sys:notice:all")
    public String list(Model model, NoticeEntity noticeEntity, HttpServletRequest request) {
        int pageNum = Utils.parseInt(request.getParameter("pageNum"), 1);
        Page<NoticeEntity> page = noticeService.findMyNoticePage(noticeEntity, pageNum);
        model.addAttribute("page", page);
        model.addAttribute("notice", noticeEntity);
        return "notice/noticeList";
    }

    /**
     * 查阅
     *
     * @param id
     * @return
     */
    @RequestMapping("/showNotice")
    @RequiresPermissions("sys:notice:all")
    @ResponseBody
    @SysLog("查阅通知")
    public Result showNotice(String id) {
        NoticeEntity notice = noticeService.queryObject(id);
        return Result.ok().put("notice", notice).put("time",  DateUtils.format(notice.getReleaseTime(),"yyyy-MM-dd hh:mm"));
    }

    /**
     * 保存
     * @param notice
     * @return
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:notice:all")
    @SysLog("保存通知")
    public Result save(@RequestBody NoticeEntity notice) {
        noticeService.save(notice);
        return Result.ok();
    }

    /**
     * 修改
     * @param notice
     * @return
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:notice:all")
    @SysLog("修改通知")
    public Result update(@RequestBody NoticeEntity notice) {
        noticeService.update(notice);
        return Result.ok();
    }

    /**
     * 删除
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:notice:all")
    @SysLog("删除通知")
    public Result delete(@RequestBody String[] ids) {
        noticeService.deleteBatch(ids);
        return Result.ok();
    }
}
