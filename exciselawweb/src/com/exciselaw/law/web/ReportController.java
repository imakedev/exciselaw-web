package com.exciselaw.law.web;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import com.exciselaw.law.domain.Display;
import com.exciselaw.law.domain.TmUserinfo;
import com.exciselaw.law.domain.TsNews;
import com.exciselaw.law.domain.TsNewsAttach;
import com.exciselaw.law.form.ReportForm;
import com.exciselaw.law.service.ExciseLawService;
import com.exciselaw.law.utils.DateUtils;

@Controller
@SessionAttributes({"reportForm"})
public class ReportController {
	private final ExciseLawService exciseLawService;
//	private final String STATUS_INACTIVE = "I";
//	private final String SCREEN_ID10 = "10";
//	private final long FILESIZE = 5000000;
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ReportController.class);
//	private static ResourceBundle resources = ResourceBundle.getBundle( "file" , Locale.ENGLISH );
	
	private TmUserinfo userInfo = null;

	
	@Autowired
	public ReportController(ExciseLawService exciseLawService) {
		this.exciseLawService = exciseLawService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
		dataBinder.setDisallowedFields("id");
	}

	
	@RequestMapping("/report/articleReport")
	public String articleReport(ServletRequest request, ServletResponse response,
				@RequestParam(value="action",required = false) String action,
				@RequestParam(value="articleId",required = false) String logId, Model model) {
		ReportForm reportForm = new ReportForm();
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		userInfo = (TmUserinfo) session.getAttribute("userInfo");
		String forword  ="report/articleSearch";
		if(action!=null){
			if(action.equals("search")){			
				logger.info( "ReportController>>article search " );	
				reportForm = new ReportForm();
				forword="report/articleSearch";
			}
		} 
//		setActionList(reportForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID10));
		reportForm.setAction(action);
		model.addAttribute("reportForm", reportForm); 
		return forword;		 
	}
	
	@RequestMapping("/report/exArticleCompletedReport")
	public String exArticleCompletedReport(ServletRequest request, ServletResponse response,
				@RequestParam(value="action",required = false) String action,
				@RequestParam(value="articleCompletedId",required = false) String logId, Model model) {
		ReportForm reportForm = new ReportForm();
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		userInfo = (TmUserinfo) session.getAttribute("userInfo");
		String forword  ="report/exArticleCompletedSearch";
		if(action!=null){
			if(action.equals("search")){			
				logger.info( "ReportController>>exArticleCompleted search " );	
				reportForm = new ReportForm();
				forword="report/exArticleCompletedSearch";
			}
		} 
//		setActionList(reportForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID10));
		reportForm.setAction(action);
		model.addAttribute("reportForm", reportForm); 
		return forword;		 
	}
	
	@RequestMapping("/report/lawReport")
	public String lawReport(ServletRequest request, ServletResponse response,
				@RequestParam(value="action",required = false) String action,
				@RequestParam(value="articleCompletedId",required = false) String logId, Model model) {
		ReportForm reportForm = new ReportForm();
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		userInfo = (TmUserinfo) session.getAttribute("userInfo");
		String forword  ="report/lawSearch";
		if(action!=null){
			if(action.equals("search")){			
				logger.info( "ReportController>>exArticleCompleted search " );	
				reportForm = new ReportForm();
				forword="report/lawSearch";
			}
		} 
//		setActionList(reportForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID10));
		reportForm.setAction(action);
		model.addAttribute("reportForm", reportForm); 
		return forword;		 
	}
	
	@RequestMapping("/report/consultDocReport")
	public String consultDocReport(ServletRequest request, ServletResponse response,
				@RequestParam(value="action",required = false) String action,
				@RequestParam(value="logId",required = false) String logId, Model model) {
		ReportForm reportForm = new ReportForm();
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		userInfo = (TmUserinfo) session.getAttribute("userInfo");
		String forword  ="report/consultDocSearch";
		if(action!=null){
			if(action.equals("search")){			
				logger.info( "ReportController>>consultDocSearch search " );	
				reportForm = new ReportForm();
				forword="report/consultDocSearch";
			}
		} 
//		setActionList(reportForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID10));
		reportForm.setAction(action);
		model.addAttribute("reportForm", reportForm); 
		return forword;		 
	}
	
	@RequestMapping("/report/loginLogReport")
	public String loginLogReport(ServletRequest request, ServletResponse response,
				@RequestParam(value="action",required = false) String action,
				@RequestParam(value="logId",required = false) String logId, Model model) {
		ReportForm reportForm = new ReportForm();
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		userInfo = (TmUserinfo) session.getAttribute("userInfo");
		String forword  ="report/loginLog";
		if(action!=null){
			if(action.equals("search")){			
				logger.info( "ReportController>>loginLog search " );	
				reportForm = new ReportForm();
				forword="report/loginLog";
			}
		} 
//		setActionList(reportForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID10));
		reportForm.setAction(action);
		model.addAttribute("reportForm", reportForm); 
		return forword;		 
	}
	
	@RequestMapping("/report/loginLogDailyReport")
	public String loginLogDailyReport(ServletRequest request, ServletResponse response,
				@RequestParam(value="action",required = false) String action,
				@RequestParam(value="logId",required = false) String logId, Model model) {
		ReportForm reportForm = new ReportForm();
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		userInfo = (TmUserinfo) session.getAttribute("userInfo");
		String forword  ="report/loginLogDaily";
		if(action!=null){
			if(action.equals("search")){			
				logger.info( "ReportController>>loginLog search " );	
				reportForm = new ReportForm();
				forword="report/loginLogDaily";
			}
		} 
//		setActionList(reportForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID10));
		reportForm.setAction(action);
		model.addAttribute("reportForm", reportForm); 
		return forword;		 
	}
	
//	@RequestMapping(value="/report/loginLog",method = RequestMethod.POST)
//	public String processLoginLog(ServletRequest request, ServletResponse response, 
//				@RequestParam(value="action",required = false) String action,
//				@ModelAttribute("reportForm") ReportForm reportForm,
//				BindingResult result,
//				Model model, SessionStatus status) {
//		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//		HttpSession session = httpServletRequest.getSession();
//		userInfo = (TmUserinfo) session.getAttribute("userInfo");
//		String forword  ="loginLog/loginLogList";
//		TsNews tsNews = reportForm.getTsNews();
//		DateTime nowTh = new DateTime(new Date().getTime());
//		
//
//		if(reportForm.getNewsStartDate()!=null && reportForm.getNewsStartDate().length()>0){
//			tsNews.setNewsStartDate(DateUtils.getDateByStringDateTh(reportForm.getNewsStartDate()));
//		}
//		if(reportForm.getNewsEndDate()!=null && reportForm.getNewsEndDate().length()>0){
//			tsNews.setNewsEndDate(DateUtils.getDateByStringDateTh(reportForm.getNewsEndDate()));
//		}
//		
//		if(action.equals("doSave")){
//			logger.info( "ReportController>>loginLog.POST doSave " );
//			
//			tsNews.setCreatedBy(userInfo.getUserId());
//			tsNews.setUpdatedBy(userInfo.getUserId());
//			tsNews.setCreatedDate(nowTh.toDate());
//			tsNews.setUpdatedDate(nowTh.toDate());
//
//			MultipartFile filePic = reportForm.getFilePic();
//        	if(filePic.getOriginalFilename() !=null && filePic.getSize() > 0){
//            	try {
//					tsNews.setNewsImage(filePic.getBytes());
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//            	tsNews.setNewsImgName(filePic.getOriginalFilename());
//        	}
//			Long logId = exciseLawService.saveTsNews(tsNews);
//        	
//			MultipartFile[] files = reportForm.getFileData();
//			if(files != null && files.length>0){
//	            for(MultipartFile file : files){
//	            	if(file.getOriginalFilename() !=null && file.getSize() > 0){
//	            		if(file.getSize() < FILESIZE){
//			            	TsNewsAttach tsNewsAttach = saveTsNewsAttachFile(file, Long.valueOf(logId), userInfo.getUserId(), nowTh.toDate());
//							exciseLawService.saveTsNewsAttach(tsNewsAttach);
//	            		}else{
//	            			reportForm.setIsSusses("2");//TODO
//	            			break;
//	            		}
//	            	}
//	            }	
//			}
//			
//			reportForm.setIsSusses("1");
//			forword="loginLog/loginLogDetail";
//		}else if(action.equals("doEdit")){		
//			logger.info( "ReportController>>loginLog.POST doEdit " );
//			
//			tsNews.setUpdatedBy(userInfo.getUserId());
//			tsNews.setUpdatedDate(nowTh.toDate());
//			
//			MultipartFile filePic = reportForm.getFilePic();
//        	if(filePic.getOriginalFilename() !=null && filePic.getSize() > 0){
//            	try {
//					tsNews.setNewsImage(filePic.getBytes());
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//            	tsNews.setNewsImgName(filePic.getOriginalFilename());
//        	}
//        	exciseLawService.updateTsNews(tsNews);
//			
//			String [] delAttachFile = reportForm.getDeleteFileBoxes();
//			if(delAttachFile!=null && delAttachFile.length>0){
//				TsNewsAttach tsNewsAttach = null;
//				for(String attachId : delAttachFile){
//					tsNewsAttach = new TsNewsAttach();
//					tsNewsAttach.setAttachId(Long.valueOf(attachId));
//					exciseLawService.deleteTsNewsAttach(tsNewsAttach);
//				}
//			}
//			
//			MultipartFile[] files = reportForm.getFileData();
//			if(files != null && files.length>0){
//	            for(MultipartFile file : files){
//	            	if(file.getOriginalFilename() !=null && file.getSize() > 0){
//		            	TsNewsAttach tsNewsAttach = saveTsNewsAttachFile(file, tsNews.getNewsId(), userInfo.getUserId(), nowTh.toDate());
//						exciseLawService.saveTsNewsAttach(tsNewsAttach);
//	            	}
//	            }	
//			}
//			model.addAttribute("tsNewsAttachList", exciseLawService.findTsNewsAttachListByNewsId(tsNews.getNewsId()));
//			reportForm.setIsSusses("1"); 
//			forword="loginLog/loginLogDetail";
//		}else if(action.equals("doDelete")){			
//			logger.info( "ReportController>>loginLog.POST doDelete " );
//			tsNews.setNewsStatus(STATUS_INACTIVE);
//			exciseLawService.updateTsNews(tsNews);
//			reportForm = new ReportForm();
//			reportForm.setAction(action);
//			reportForm.setIsSusses("1");
//			forword="loginLog/loginLogDetail";
//		}
//		setActionList(reportForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID10));
//		model.addAttribute("loginLogDisplayTypeList", loginLogDisplayTypeList);
//		model.addAttribute("reportForm",reportForm);
//		return forword;		 
//	}
	
//	private void setActionList(ReportForm reportForm, String actionList){
//		if(actionList.indexOf("ADD") != -1){
//			reportForm.setAdd("true");
//		}
//		if(actionList.indexOf("EDIT") != -1){
//			reportForm.setEdit("true");
//		}
//		if(actionList.indexOf("DELETE") != -1){
//			reportForm.setDelete("true");
//		}
//		if(actionList.indexOf("VIEW") != -1){
//			reportForm.setView("true");
//		}		
//	}
}
