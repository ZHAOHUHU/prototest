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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import shenzhen.teamway.tms9000.portal.domain.Role;
import shenzhen.teamway.tms9000.portal.domain.User;
import shenzhen.teamway.tms9000.portal.service.MenuService;
import shenzhen.teamway.tms9000.portal.service.RoleService;
import shenzhen.teamway.tms9000.portal.util.ConstantUtils;
import shenzhen.teamway.tms9000.portal.util.PageBean;

@Controller
public class RoleController {

	public static Logger logger = LoggerFactory.getLogger(RoleController.class);
	@Autowired
	RoleService roleService;

	@Autowired
	MenuService menuService;

	@RequestMapping("/role/intiRoleList.htm")
	public ModelAndView initRoleList(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("role/roleList");
		User account = (User)request.getSession().getAttribute(ConstantUtils.SESSION_USER_);
		Role r = roleService.getRoleByUserId(account.getId());
		view.addObject("role", r);
		return view;
	}

	/**
	 * 角色列表
	 * 
	 * @param reponse
	 * @param role
	 * @param page
	 */
	@RequestMapping("/role/roleList.htm")
	public void roleList(HttpServletRequest request,HttpServletResponse reponse, Role role, int page,int rows) {
		logger.info("------- begin roleList ----------");

		PrintWriter pw = null;
		try {
			PageBean pageBean = new PageBean();
			pageBean.setPageSize(rows);
			pageBean.setPage(page);
			User account = (User)request.getSession().getAttribute(ConstantUtils.SESSION_USER_);
			Role r = roleService.getRoleByUserId(account.getId());
			//系统管理员可以看到所有角色
//			if(r.getRoleId() == ConstantUtils.SYS_ROLE){
//				role.setMerchantId(null);
//			}else if(r.getRoleId() == ConstantUtils.MERCHANT_ROLE){
//				//商户管理员可以看到自己商户的角色
//				role.setMerchantId(account.getMerchant_id());
//			}else{
//				//其它用户只能看到自己的角色
//				role.setMerchantId(account.getMerchant_id());
//				role.setRoleId(r.getRoleId());
//			}
			//role.setMerchantId(account.getMerchant_id());
			PageBean pageInfo = roleService.roleList(role, pageBean);
			String json = JSON.toJSONString(pageInfo);
			reponse.setCharacterEncoding("UTF-8");
			pw = reponse.getWriter();
			pw.write(json);
		} catch (IOException e) {
			logger.error("------- error roleList ----------", e);
		} finally {
			pw.close();
		}
		logger.info("------- end roleList ----------");
	}
	
	/**
	 * 角色下拉列表
	 * 
	 * @param reponse
	 * @param role
	 * @param page
	 */
	@RequestMapping("/role/roleListAll.htm")
	public void roleListAll(HttpServletRequest request, HttpServletResponse reponse,Role role) {
		PrintWriter pw = null;
		try {
			User account = (User)request.getSession().getAttribute(ConstantUtils.SESSION_USER_);
//			int merchantId = account.getMerchant_id();
//			role.setMerchantId(merchantId);
			List<Role> all = roleService.getRoles(role);
			String json = JSON.toJSONString(all);
			reponse.setCharacterEncoding("UTF-8");
			pw = reponse.getWriter();
			pw.write(json);
		} catch (IOException e) {
			logger.error("------- error roleList ----------", e);
		} finally {
			pw.close();
		}
	}

	/**
	 * 新增角色
	 * 
	 * @param role
	 * @param response
	 */
	@RequestMapping(value = "/role/addRole.htm", method = RequestMethod.POST)
	public void addRole(Role role, String menuList,HttpServletRequest request, HttpServletResponse response) {
		logger.info("------- begin addRole ----------");
		PrintWriter pw = null;
		try {
			User account = (User)request.getSession().getAttribute(ConstantUtils.SESSION_USER_);
//			role.setMerchantId(account.getMerchant_id());
//			role.setRoleType(Integer.valueOf(account.getUser_type()));
			int flag = roleService.addRole(role, menuList);
			Map<String, Integer> resultMap = new HashMap<String, Integer>();
			resultMap.put("flag", flag);
			String json = JSON.toJSONString(resultMap);
			response.setCharacterEncoding("UTF-8");
			pw = response.getWriter();
			pw.write(json);
		} catch (IOException e) {
			logger.error("----- error addRole ------", e);
		} finally {
			pw.close();
		}
		logger.info("------- end addRole ----------");
	}

	/**
	 * 加载角色信息
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping("/role/initRoleInfo.htm")
	public void initRoleInfo(Role role, HttpServletResponse response) {
		logger.info("------- begin initRoleInfo ----------");
		PrintWriter pw = null;
		try {
			Role roleInfo = roleService.getRoleById(role.getId());
			String json = JSON.toJSONString(roleInfo);
			response.setCharacterEncoding("UTF-8");
			pw = response.getWriter();
			pw.write(json);
		} catch (IOException e) {
			logger.error("----- error initRoleInfo ------", e);
		} finally {
			pw.close();
		}
		logger.info("------- end initRoleInfo ----------");
	}
	
	/**
	 * 修改角色信息
	 * @param role
	 * @param menuList
	 * @param response
	 */
	@RequestMapping("/role/updateRole.htm")
	public void updateRoleInfo(Role role , String menuList , HttpServletResponse response){
		logger.info("------- begin updateRoleInfo ----------");
		PrintWriter pw = null;
		try {
			int flag  = roleService.updateRoleInfo(role,menuList);
			String json = JSON.toJSONString(flag);
			response.setCharacterEncoding("UTF-8");
			pw = response.getWriter();
			pw.write(json);
		} catch (IOException e) {
			logger.error("----- error updateRoleInfo ------", e);
		} finally {
			pw.close();
		}
		logger.info("------- end updateRoleInfo ----------");
	}
	/**
	 * 删除角色信息
	 * @param role
	 * @param response
	 */
	@RequestMapping(value="/role/deleteRole.htm",method=RequestMethod.POST)
	public void deleteRole(Role role , HttpServletResponse response){
		logger.info("------- begin deleteRole ----------");
		PrintWriter pw = null;
		try {
			int flag  = roleService.deleteRole(role.getId());
			String json = JSON.toJSONString(flag);
			response.setCharacterEncoding("UTF-8");
			pw = response.getWriter();
			pw.write(json);
		} catch (IOException e) {
			logger.error("----- error deleteRole ------", e);
		} finally {
			pw.close();
		}
		logger.info("------- end deleteRole ----------");
		
		
	}
}
