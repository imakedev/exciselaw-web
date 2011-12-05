
package com.excise.law.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.excise.law.service.LawService;

/**
 * Annotation-driven <em>MultiActionController</em> that handles all non-form
 * URL's.
 *
 * @author Chatchai Pimtun
 */
@Controller
public class LawController {

	private final LawService exciseLawService;
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(LawController.class);
	
	@Autowired
	public LawController(LawService exciseLawService) {
		this.exciseLawService = exciseLawService;
	}

	/**
	 * Custom handler for the welcome view.
	 * <p>
	 * Note that this handler relies on the RequestToViewNameTranslator to
	 * determine the logical view name based on the request URL: "/welcome.do"
	 * -&gt; "welcome".
	 */



	
	@RequestMapping("/index")
	public String index() {
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
		return "index";
	}
	
	@RequestMapping("/iFrame")
	public String iframeHandler() {
		return "iFrame";
	}
}
