package com.exciselaw.law.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import com.exciselaw.law.service.ExciseLawService;

@Controller
public class SentencePronouncementController {
	private final ExciseLawService exciseLawService;
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(SentencePronouncementController.class);

	@Autowired
	public SentencePronouncementController(ExciseLawService exciseLawService) {
		this.exciseLawService = exciseLawService;
	}
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		System.out.println( "AddVisitForm>>setAllowedFields" );
		dataBinder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
		dataBinder.setDisallowedFields("id");
	}
	/*CourtDetail1.jsp
	CourtDetail2-1.jsp
	CourtDetail3-1.jsp
	CourtDetail4-2.jsp
	courtdetail5_form.jsp*/
}
