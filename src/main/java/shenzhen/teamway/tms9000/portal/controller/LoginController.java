
package shenzhen.teamway.tms9000.portal.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import shenzhen.teamway.tms9000.portal.domain.SysMenu;
import shenzhen.teamway.tms9000.portal.domain.User;
import shenzhen.teamway.tms9000.portal.service.UserService;
import shenzhen.teamway.tms9000.portal.util.ConstantUtils;


@Controller
public class LoginController {
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
    private UserService userService;

    @RequestMapping(value = {"/", "index.html"})
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = "/loginCheck.html")
    public String loginCheck(@RequestParam("userName") String userName, @RequestParam("password") String password, Model model) {
        boolean isValid = userService.hasMatchUser(userName, password);
        if (isValid) {
            model.addAttribute("message", "Hello, " + userName);
        }
        else {
            model.addAttribute("message", "user error.");
        }
        return "resultPage";
    }

	/**
	 * 用户登录
	 * 
	 * @return
	 */
	@RequestMapping("/protalLogin.html")
	public ModelAndView login(HttpServletRequest request ,User user) {
		Map<String,String> map= new HashMap<String,String>();
		try {
			if(request.getSession().getAttribute("validateCode") == null) {
				ModelAndView view = new ModelAndView("redirect:"+"/");
				map.put("msg", "长时间未操作，请重新登录");
				map.put("userName", user.getUserName());
				view.addAllObjects(map);
				return view;
			}
			
			if(!request.getSession().getAttribute("validateCode").equals(user.getVerifyCode().toUpperCase())) {
				ModelAndView view = new ModelAndView("redirect:"+"/");
				map.put("msg", "验证码错误");
				map.put("userName", user.getUserName());
				view.addAllObjects(map);
				return view;
			}
			
			User userInfo;
			userInfo = userService.getUserByName(user.getUserName());
			if (userInfo == null) {
				ModelAndView view = new ModelAndView("redirect:"+"/");
				map.put("msg", "用户名不存在");
				map.put("userName", user.getUserName());
				view.addAllObjects(map);
				return view;
			}
			
			String pwd = user.getPassword();
			if(!pwd.equals(userInfo.getPassword())) {
				ModelAndView view = new ModelAndView("redirect:"+"/");
				map.put("msg", "密码错误");
				map.put("userName", user.getUserName());
				view.addAllObjects(map);
				return view;
			}

			ModelAndView view = null;
			view = new ModelAndView("baseconf/orgManage");
			request.getSession().setAttribute(ConstantUtils.SESSION_USER_, userInfo);
			request.getSession().setAttribute("msg", "ss");
			return view;
		} catch (Exception e) {
			ModelAndView view = new ModelAndView("redirect:"+"/");
			map.put("msg", "连接超时");
			map.put("userName", user.getUserName());
			view.addAllObjects(map);
			logger.error("user login err:"+e.getMessage());
			return view;
		}
	}
    
	
	/**
	 * 页面转发请求
	 * @param page
	 * @return
	 */
	@RequestMapping("/redirect")
	public String redirect(String page){
		return page;
	}
	
	/**
	 * 退出
	 */
	@RequestMapping(value="/logout.html",method=RequestMethod.POST)
	public void logout(HttpServletRequest request , HttpServletResponse response){
		request.getSession().setAttribute(ConstantUtils.SESSION_USER_,null);
	}
    
	/**

	 */

	
	@RequestMapping(value="/test.html")
	public  String test() {
		return "baseconf/orgManage";
	}
}
