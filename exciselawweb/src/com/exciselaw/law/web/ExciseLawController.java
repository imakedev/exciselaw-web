
package com.exciselaw.law.web;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.exciselaw.law.domain.TmDepartment;
import com.exciselaw.law.domain.TmScreen;
import com.exciselaw.law.domain.TmUserinfo;
import com.exciselaw.law.domain.TsLoginLog;
import com.exciselaw.law.service.ExciseLawService;

/**
 * Annotation-driven <em>MultiActionController</em> that handles all non-form
 * URL's.
 *
 * @author Chatchai Pimtun
 */
@Controller
public class ExciseLawController {

	private final ExciseLawService exciseLawService;
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ExciseLawController.class);
	
	@Autowired
	public ExciseLawController(ExciseLawService exciseLawService) {
		this.exciseLawService = exciseLawService;
	}

	/**
	 * Custom handler for the welcome view.
	 * <p>
	 * Note that this handler relies on the RequestToViewNameTranslator to
	 * determine the logical view name based on the request URL: "/welcome.do"
	 * -&gt; "welcome".
	 */
	@RequestMapping("/")
	public String welcomeHandler() {
		return "welcome_bk";
	}

	@RequestMapping("/welcome2")
	public String welcome2Handler(ServletRequest request) {
		return "login";
	}
	
	@RequestMapping("/index")
	public String index(ServletRequest request,
			@RequestParam(value="userId",required = false) String userId,
			@RequestParam(value="password",required = false) String password) {
		System.out.println("userId : "+userId+" password : "+password);
		if(userId != null && !"".equals(userId) && password != null && !"".equals(password)){
			TmUserinfo userInfo = exciseLawService.getUserpassword(userId, password);
			if(userInfo != null){
				HashMap<String, String> screenMap = exciseLawService.getTmScreenMapByRoleId(userInfo.getRoleId());
				HttpServletRequest httpRequest = (HttpServletRequest)request;
				httpRequest.getSession().setAttribute("screenMap", screenMap);
				httpRequest.getSession().setAttribute("userInfo", userInfo);
				TsLoginLog tsLoginLog = new TsLoginLog();
				tsLoginLog.setUserId(userId);
				Calendar calendar = Calendar.getInstance();
				java.util.Date now = calendar.getTime();				
				tsLoginLog.setLoginDatetime(new java.sql.Timestamp(now.getTime()));
				exciseLawService.saveTsLoginLog(tsLoginLog);
				return "index";
			}else{
				HttpServletRequest httpRequest = (HttpServletRequest)request;
				httpRequest.getSession().setAttribute("result", "false");
				return "login";
			}
		}else{
			return "login";
		}
	}
	
	@RequestMapping("/logout")
	public String logout(ServletRequest request) {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		TmUserinfo userInfo = (TmUserinfo) httpRequest.getSession().getAttribute("userInfo");
		TsLoginLog tsLoginLog = exciseLawService.getTsLoginLogByUserId(userInfo.getUserId());
		Calendar calendar = Calendar.getInstance();
		java.util.Date now = calendar.getTime();			
		tsLoginLog.setLogoutDatetime(new java.sql.Timestamp(now.getTime()));
		exciseLawService.updateTsLoginLog(tsLoginLog);
		if(httpRequest.getSession().getAttribute("userInfo") != null)
			httpRequest.getSession().removeAttribute("userInfo");
		if(httpRequest.getSession().getAttribute("screenMap") != null)
			httpRequest.getSession().removeAttribute("screenMap");
		if(httpRequest.getSession().getAttribute("result") != null)
			httpRequest.getSession().removeAttribute("result");
		httpRequest.getSession().setAttribute("userId", userInfo.getUserId());
		return "logout";
	}
	
	@RequestMapping("/image")
	public String image(ServletRequest request,
			@RequestParam(value="newsId",required = false) String newsId) {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		httpRequest.getSession().setAttribute("newsId", newsId);
		return "image";
	}
	
	@RequestMapping("/iFrame")
	public String iframeHandler() {
		return "iFrame";
	}
	/**
	 * Custom handler for displaying vets.
	 *
	 * <p>Note that this handler returns a plain {@link ModelMap} object instead of
	 * a ModelAndView, thus leveraging convention-based model attribute names.
	 * It relies on the RequestToViewNameTranslator to determine the logical
	 * view name based on the request URL: "/vets.do" -&gt; "vets".
	 *
	 * @return a ModelMap with the model attributes for the view
	 */
	@RequestMapping("/vets")
	public ModelMap vetsHandler() {
	/*	Vets vets = new Vets();
		vets.getVetList().addAll(this.clinic.getVets());
		return new ModelMap(vets);
		*/
		return new ModelMap(null);
	}

	/**
	 * Custom handler for displaying an owner.
	 *
	 * @param ownerId the ID of the owner to display
	 * @return a ModelMap with the model attributes for the view
	 */
	@RequestMapping("/owners/{ownerId}")
	public ModelAndView ownerHandler(@PathVariable("ownerId") int ownerId) {
		ModelAndView mav = new ModelAndView("owners/show");
		 
	//	mav.addObject(this.clinic.loadOwner(ownerId));
		return mav;
	}

	/**
	 * Custom handler for displaying an list of visits.
	 *
	 * @param petId the ID of the pet whose visits to display
	 * @return a ModelMap with the model attributes for the view
	 */
	@RequestMapping(value="/owners/*/pets/{petId}/visits", method=RequestMethod.GET)
	public ModelAndView visitsHandler(@PathVariable int petId) {
		ModelAndView mav = new ModelAndView("visits");
		//mav.addObject("visits", this.clinic.loadPet(petId).getVisits());
		return mav;
	}
	/*
	 * @RequestMapping  // default render (action=list)
	public String listFAQs(Model model,@RequestParam(value="pageNo",required = false) String pageNoStr
			,@RequestParam(value="nfaqName",required = false) String nfaqName) {
	 
	 *
	 *
	 *@RequestMapping(params = "action=listFAQMessage")  // render phase
	public String listFAQMessage(@RequestParam("nfaqId") String nfaqId,@RequestParam(value="pageNo",required = false) String pageNoStr,
			@RequestParam(value="nfaqName",required = false) String nfaqName,
			@RequestParam(value="nfaqQuestion",required = false) String nfaqQuestion,Model model) {
	 *
	 *
	 *@RequestMapping(params = "action=AddEditViewFAQ")  // render phase
	public String viewFAQForm(@RequestParam("nfaqId") String nfaqId,@RequestParam("mode") String mode,Model model) {
	 *
	 *
	 *@RequestMapping(params = "action=AddEditViewFAQMessage")  // render phase
	public String viewFAQMessageForm(@RequestParam("nfaqMId") String nfaqMId,
			@RequestParam("nfaqId") String nfaqId,
			@RequestParam("mode") String mode,@RequestParam(value="nfaqName",required=false) String nfaqName,
			Model model) { 
			
	 *
	 *
	 *@RequestMapping(params = "action=deleteFAQ")  // action delete FAQ
	public void deleteFAQ(@RequestParam("nfaqId") String nfaqId,
			//@RequestParam(value="nfaqSiteId",required = false) String nfaqSiteId,
			ActionResponse response,ActionRequest request) { 
	 *
	 *
	 *@RequestMapping(params = "action=doSubmit") 
	public void doSubmit(ActionRequest request, ActionResponse response,
			@ModelAttribute("faqAdminform") th.or.ntc.faq.domain.FAQForm faqform,
			BindingResult result,
			Model model) { 
	 */

}
