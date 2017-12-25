package shenzhen.teamway.tms9000.portal.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import shenzhen.teamway.tms9000.portal.domain.RegionInfo;
import shenzhen.teamway.tms9000.portal.domain.User;
import shenzhen.teamway.tms9000.portal.enums.OrgTreeStateEnum;
import shenzhen.teamway.tms9000.portal.service.RegionInfoService;
import shenzhen.teamway.tms9000.portal.util.ConstantUtils;
import shenzhen.teamway.tms9000.portal.util.JsonMessage;
import shenzhen.teamway.tms9000.portal.util.JsonUtils;

@Controller
public class BaseConfController {

	public static Logger logger = LoggerFactory.getLogger(BaseConfController.class);
	
	@Autowired
	private RegionInfoService regionInfoService;
	
	@RequestMapping("/baseconf/initOrg.html")
	public ModelAndView initOrgManage() {
		logger.info("--------begin initOrg ----------");
		ModelAndView view = new ModelAndView("baseconf/orgManage");
		logger.info("--------end initOrg ----------");
		return view;
	}
	
	@RequestMapping("/baseconf/initOrgTree.html")
	public void getOrgTree(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
		logger.info("--------begin getOrgTree ----------");
		PrintWriter pw = null;
		List<Map<String,String>> treeList = new ArrayList<Map<String,String>>();
		try {
			List<RegionInfo> list = regionInfoService.selectAll();
			for(RegionInfo info : list) {
				Map<String,String> map = new HashMap<String,String>();
				map.put("id", String.valueOf(info.getId()));
				map.put("pId",String.valueOf( info.getpId()));
				map.put("name",info.getName());
				map.put("icon", info.getIcon());
				treeList.add(map);
			}
			String json = JSON.toJSONString(treeList);
			httpServletResponse.setCharacterEncoding("UTF-8");
			pw = httpServletResponse.getWriter();
			pw.print(json);
		} catch (IOException e) {
			e.printStackTrace();
			logger.info("----------getOrgTree error,error info:"+e.getMessage());
		}
		logger.info("--------end getOrgTree ----------");
	}
	
	@RequestMapping("/baseconf/delOrgTreeById.html")
	public void delOrgTreeById(HttpServletRequest request,HttpServletResponse response, RegionInfo info) {
		logger.info("--------begin delOrgTreeById ----------");
		User user = (User) request.getSession().getAttribute(ConstantUtils.SESSION_USER_);
		info.setUpdateTime(new Date());
		info.setLastUpdateBy(user.getUserName());
		int flag = regionInfoService.delOrgTreeById(info);
		JsonMessage jsonMessage = new JsonMessage();
		if(flag>0) {
			jsonMessage.setStatus(JsonUtils.SUCCESS);
			jsonMessage.setMessage("删除成功!");
			jsonMessage.setSuccess(JsonUtils.SUCCESS);
		}else {
			jsonMessage.setStatus(JsonUtils.FAIL);
			jsonMessage.setMessage("删除失败!");
			jsonMessage.setSuccess(JsonUtils.FAIL);
		}
		JsonUtils.writeObjectToJson(response, jsonMessage);
		logger.info("--------end delOrgTreeById ----------");
	}
	
	@RequestMapping("/baseconf/addOrgTreeNode.html")
	public  void addOrgTreeNode(HttpServletRequest request,HttpServletResponse response,RegionInfo info) {
		logger.info("--------begin addOrgTreeNode ----------");
		User user = (User) request.getSession().getAttribute(ConstantUtils.SESSION_USER_);
		info.setCreater(user.getUserName());
		info.setCreateTime(new Date());
		info.setState(OrgTreeStateEnum.NORMAL.getState());
		int flag = regionInfoService.addOrgTreeNode(info);
		JsonMessage jsonMessage = new JsonMessage();
		if(flag>0) {
			jsonMessage.setStatus(JsonUtils.SUCCESS);
			jsonMessage.setMessage("新增成功!");
			jsonMessage.setSuccess(JsonUtils.SUCCESS);
		}else {
			jsonMessage.setStatus(JsonUtils.FAIL);
			jsonMessage.setMessage("新增失败!");
			jsonMessage.setSuccess(JsonUtils.FAIL);
		}
		JsonUtils.writeObjectToJson(response, jsonMessage);
		logger.info("--------end addOrgTreeNode ----------");
	}
	
}
