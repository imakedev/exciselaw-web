package com.excise.law.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.excise.law.form.ExciseLawForm;
import com.excise.law.service.LawService;

/**
 * @author Chatchai Pimtun
 */
@Controller
@SessionAttributes({"exciseLawForm"}) 
public class TestController {
	private final LawService exciseLawService;
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(TestController.class);
	
	@Autowired
	public TestController(LawService exciseLawService) {
		this.exciseLawService = exciseLawService;
	}

	/**
	 * Custom handler for the welcome view.
	 * <p>
	 * Note that this handler relies on the RequestToViewNameTranslator to
	 * determine the logical view name based on the request URL: "/welcome.do"
	 * -&gt; "welcome".
	 */



	
	@RequestMapping("/law/completedtest")
	public String completedtest() {
//		System.out.println("userId : "+userId+" password : "+password);
//		TmUserinfo userInfo = exciseLawService.getUserpassword(userId, password);
//		if(userInfo != null){
//			HashMap<String, String> screenMap = exciseLawService.getTmScreenMapByRoleId(userInfo.getRoleId());
//			HttpServletRequest httpRequest = (HttpServletRequest)request;
//			httpRequest.getSession().setAttribute("screenMap", screenMap);
//			httpRequest.getSession().setAttribute("userInfo", userInfo);
//			return "index";
//		}else{
//			HttpServletRequest httpRequest = (HttpServletRequest)request;
//			httpRequest.getSession().setAttribute("result", "false");
//			return "login";
//		}
		return "law/exArticleCompletedDetail";
	}
	
	@RequestMapping("/law/articletest")
	public String articletest(Model model) {
		ExciseLawForm exciseLawForm = new ExciseLawForm(); ;
		model.addAttribute("exciseLawForm", exciseLawForm); 
		return "law/articleDetail";
	}
	@RequestMapping("/law/lawtest")
	public String lawtest(Model model) {
		ExciseLawForm exciseLawForm = new ExciseLawForm(); ;
		model.addAttribute("exciseLawForm", exciseLawForm);  
		return "law/lawDetail";
	}
	
}
