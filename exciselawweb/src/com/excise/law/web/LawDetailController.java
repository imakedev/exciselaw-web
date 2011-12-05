package com.excise.law.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import com.excise.law.domain.TmStatue;
import com.excise.law.form.LawForm;
import com.excise.law.service.LawService;

@Controller
@SessionAttributes({"lawForm"})
public class LawDetailController {
	private final LawService lawService;
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(LawDetailController.class);

	
	@Autowired
	public LawDetailController(LawService lawService) {
		this.lawService = lawService;
	}
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		System.out.println( "LawDetailController>>setAllowedFields" );
		dataBinder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
		dataBinder.setDisallowedFields("id");
	}
	
	@RequestMapping("/law/lawDisplay")
	public String lawDisplay(@RequestParam(value="action",required = false) String action,
			@RequestParam(value="statueId",required = false) String statueId,
			Model model) {
		System.out.println(  "LawDetailController>>lawDisplay" ); 
		LawForm lawForm = new LawForm();
		String forword  ="law/lawDetail";
		TmStatue statue = lawService.findTmStatueByStatueId(Long.valueOf(statueId));
		model.addAttribute("statueId", statue.getStatueId());
		model.addAttribute("statueName", statue.getStatueName());
		model.addAttribute("lawTypeList", lawService.getTmLawTypeList());
		model.addAttribute("lawForm", lawForm); 
		return forword;
	}
	
//	@RequestMapping(value="/law/lawDisplay",method = RequestMethod.POST)
//	public String processLawDisplay(ServletRequest request, ServletResponse response,
//			@RequestParam(value="action",required = false) String action,
//			@ModelAttribute("lawForm") LawForm form,
//			BindingResult result,
//			Model model, SessionStatus status) {
//		System.out.println(  "LawDetailController.POST>>lawDetail" ); 
//		
//		String forword="law/lawDetail";
//		return forword;
//		 
//	}
}
