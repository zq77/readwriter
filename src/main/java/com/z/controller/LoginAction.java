package com.z.controller;

import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.z.model.User;
import com.z.service.UserService;

@Controller("loginAction")
@RequestMapping("")
public class LoginAction {

    @Resource
    private UserService userService;
    
    @RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "/login.jsp";
	}

    @RequestMapping(value="/loginSubmit", method=RequestMethod.POST)
	public String loginSubmit(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userService.getUserByUsernameAndPassword(username, password);
        if(user == null) {
            return "/index.jsp";
        }
        request.getSession().setAttribute("currentUser", user);
        return "redirect:/user/list";
	}

    @RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
	    request.getSession().invalidate();
		return "redirect:/index.html";
	}

    @RequestMapping("/register")
	public String register() {
		return "/register.jsp";
	}

    @RequestMapping("/registerSubmit")
	public String registerSubmit(HttpServletRequest request) {
	    String username = request.getParameter("username");
	    String password = request.getParameter("password");
        if (!userService.checkUsername(username)) {
            return "/index.jsp";
        }
	    User user = new User();
	    user.setId(getUUID());
	    user.setUsername(username);
	    user.setPassword(password);
	    userService.save(user);
		return "redirect:/user/login";
	}

    /**
     * 得到32位的随机字符串
     * @return
     */
    private String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
