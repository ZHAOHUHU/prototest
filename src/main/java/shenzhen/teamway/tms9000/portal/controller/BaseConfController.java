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

//
}
