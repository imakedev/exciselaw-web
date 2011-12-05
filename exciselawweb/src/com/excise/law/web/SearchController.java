package com.excise.law.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import com.excise.law.domain.Display;
import com.excise.law.domain.TmUserinfo;
import com.excise.law.form.LawForm;
import com.excise.law.form.SearchForm;
import com.excise.law.service.LawService;

@Controller
@SessionAttributes({"searchForm"})
public class SearchController {
	private final LawService lawService;
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(SearchController.class);
//	private static ResourceBundle resources = ResourceBundle.getBundle( "file" , Locale.ENGLISH );
	
	private TmUserinfo userInfo = null;
	
	private static final String[] NEWSDISPLAYTYPE_VALUE_BOXES = new String[]{"1","0"};
	private static final String[] NEWSDISPLAYTYPE_LABEL_BOXES = new String[]{"แสดงผล","ไม่แสดงผล"};
	
	private static List<Display> newsDisplayTypeList = new ArrayList<Display>(2);
	
	static{
		for(int i=0;i<NEWSDISPLAYTYPE_VALUE_BOXES.length;i++){
			Display display=new Display(NEWSDISPLAYTYPE_VALUE_BOXES[i],NEWSDISPLAYTYPE_LABEL_BOXES[i]);
			newsDisplayTypeList.add(display);
		}
	}
	
	@Autowired
	public SearchController(LawService lawService) {
		this.lawService = lawService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
		dataBinder.setDisallowedFields("id");
	}
	
	@RequestMapping("link/siteMap")
	public String otherLink() {
		String forword  ="link/siteMap";
		return forword;		 
	}
	
	@RequestMapping("/search/simpleSearch")
	public String simpleSearch(ServletRequest request, ServletResponse response,
				@RequestParam(value="action",required = false) String action, Model model) {
		SearchForm searchForm = new SearchForm();
		String forword  ="search/simpleSearchList";
		if(action!=null && action.equals("edit")){
			forword="search/simpleSearchDetail";
		}
		model.addAttribute("statueList", lawService.getTmStatueList());
		model.addAttribute("lawTypeList", lawService.getTmLawTypeList());
		model.addAttribute("searchForm", searchForm);
		return forword;		 
	}
	
	@RequestMapping("/search/formSearch")
	public String formSearch(ServletRequest request, ServletResponse response,
				@RequestParam(value="action",required = false) String action, Model model) {
		SearchForm searchForm = new SearchForm();
		String forword  ="search/formSearchList";
		if(action!=null && action.equals("edit")){
			forword="search/formSearchDetail";
		}
//		lawService.getMetaDataFromTable();
		model.addAttribute("statueList", lawService.getTmStatueList());
		model.addAttribute("lawTypeList", lawService.getTmLawTypeList());
		model.addAttribute("searchForm", searchForm);
		return forword;		 
	}
	
	@RequestMapping("/search/advanceSearch")
	public String advanceSearch(ServletRequest request, ServletResponse response,
				@RequestParam(value="action",required = false) String action, Model model) {
		SearchForm searchForm = new SearchForm();
		String forword  ="search/advanceSearchList";
		if(action!=null && action.equals("edit")){
			forword="search/advanceSearchDetail";
		}
		model.addAttribute("statueList", lawService.getTmStatueList());
		model.addAttribute("lawTypeList", lawService.getTmLawTypeList());
		model.addAttribute("searchForm", searchForm);
		return forword;		 
	}
	
	@RequestMapping("/search/fullTextSearch")
	public String fullTextSearch(ServletRequest request, ServletResponse response,
				@RequestParam(value="action",required = false) String action,
				@RequestParam(value="newsId",required = false) String newsId, Model model) {
		SearchForm searchForm = new SearchForm();
		String forword  ="search/fullTextSearchList";
		if(action!=null && action.equals("edit")){
			forword="search/fullTextSearchDetail";
		}
		model.addAttribute("statueList", lawService.getTmStatueList());
		model.addAttribute("lawTypeList", lawService.getTmLawTypeList());
		model.addAttribute("searchForm", searchForm);
		return forword;		 
	}

	@RequestMapping("/search/synonymSearch")
	public String synonymSearch(ServletRequest request, ServletResponse response,
				@RequestParam(value="action",required = false) String action,
				@RequestParam(value="newsId",required = false) String newsId, Model model) {
		SearchForm searchForm = new SearchForm();
		String forword  ="search/synonymSearchList";
		if(action!=null && action.equals("edit")){
			forword="search/synonymSearchDetail";
		}
		model.addAttribute("statueList", lawService.getTmStatueList());
		model.addAttribute("lawTypeList", lawService.getTmLawTypeList());
		model.addAttribute("masterWordList", lawService.getTsMasterWordList());
		model.addAttribute("searchForm", searchForm);
		return forword;		 
	}
}
