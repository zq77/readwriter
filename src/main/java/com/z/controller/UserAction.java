package com.z.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.z.model.User;
import com.z.service.UserService;
import com.z.utils.Page;
import com.z.utils.PageUtil;

@Controller("userAction")
@RequestMapping("/user")
public class UserAction {

    @Resource
    private UserService userService;

    @RequestMapping("/list")
	public String list(HttpServletRequest request) {
        //分页
        Page page = PageUtil.getPage(0, 10);

        List<User> userList = userService.getList(null, page);
        request.setAttribute("userList", userList);
		return "/list.jsp";
	}
}
