package com.flying.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.flying.cms.pojo.UserInfo;
import com.flying.cms.service.UserService;
import com.flying.cms.utils.DateFormatUtils;

@RequestMapping("cms/user")
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取所有用户信息
     * 
     * @return
     */
    @RequestMapping(value = "/allUsers", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getAllUserInfo() {
        ModelAndView mv = new ModelAndView("userManage");
        List<UserInfo> list = this.userService.getAllUserInfo();
        for (UserInfo userInfo : list) {
            // 设置日期显示
            userInfo.setCurrentLoginTime4Display(DateFormatUtils.parseDateToString(userInfo
                    .getCurrentLoginTime()));
            // System.out.println("currentLoginTime4Display = " +
            // userInfo.getCurrentLoginTime4Display());
            userInfo.setLastLoginTime4Display(DateFormatUtils.parseDateToString(userInfo.getLastLoginTime()));
            userInfo.setRegistTime4Display(DateFormatUtils.parseDateToString(userInfo.getRegistTime()));
            userInfo.setTotalOnlineTime4Display(DateFormatUtils.processTotalOnlineTime(userInfo
                    .getTotalOnlineTime()));
        }
        mv.addObject("userList", list);
        System.out.println(list);
        return mv;
    }

    /**
     * 跳转到userDetail页面，并携带参数：用户id
     * 
     * @param id
     * @return
     */
    /*
     * @RequestMapping("/userDetails") public ModelAndView toUserDetails(@RequestParam("id") Long
     * id) { ModelAndView mv = new ModelAndView("userDetails"); mv.addObject("id", id); return mv; }
     */

    /**
     * 查询用户信息
     * 
     * @param id
     * @return
     */
    /*
     * @RequestMapping(value = "/checkUserDetail", method = RequestMethod.GET)
     * 
     * @ResponseBody public Map<String, Object> checkUserDetail(@RequestParam("id") Long id) {
     * Map<String, Object> map = new HashMap<String, Object>(); System.out.println("查询用户id为" + id +
     * "的详情"); UserInfo userInfo = this.userService.checkUserDetail(id); map.put("userInfo",
     * userInfo); map.put("result", "success"); return map; }
     */

    @RequestMapping(value = "/userDetails", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView checkUserDetail(@RequestParam("id") Long id) {
        ModelAndView mv = new ModelAndView("userDetails");
        System.out.println("查询用户id为" + id + "的详情");
        UserInfo userInfo = this.userService.checkUserDetail(id);
        // 设置日期显示
        userInfo.setCurrentLoginTime4Display(DateFormatUtils.parseDateToString(userInfo.getCurrentLoginTime()));
        userInfo.setLastLoginTime4Display(DateFormatUtils.parseDateToString(userInfo.getLastLoginTime()));
        userInfo.setRegistTime4Display(DateFormatUtils.parseDateToString(userInfo.getRegistTime()));
        userInfo.setTotalOnlineTime4Display(DateFormatUtils.processTotalOnlineTime(userInfo
                .getTotalOnlineTime()));
        mv.addObject("userInfo", userInfo);
        return mv;
    }

    /**
     * 查询用户文章列表
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/userEssay", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView toUserEssayList(@RequestParam("id") Long id) {
        ModelAndView mv = new ModelAndView("userEssayList");
        UserInfo userInfo = this.userService.queryEssayListByUserId(id);
        System.out.println("查询用户id为" + id + "的文章列表");
        /*
         * if(userInfo.getEssayList().size() > 0){ mv.addObject("essayList", null); } else {
         * System.out.println("用户文章列表：" + userInfo.getEssayList()); mv.addObject("essayList",
         * userInfo.getEssayList()); } return mv;
         */
        try {
            System.out.println("用户文章列表：" + userInfo.getEssayList());
            mv.addObject("essayList", userInfo.getEssayList());
        } catch (NullPointerException e) {
            mv.addObject("essayList", null);
        }
        return mv;
    }
}
