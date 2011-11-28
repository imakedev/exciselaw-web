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
import com.exciselaw.law.domain.TsJudgement;
import com.exciselaw.law.domain.TsJudgementAttach;
import com.exciselaw.law.domain.TsJudgementStatue;
import com.exciselaw.law.form.JudgementForm;
import com.exciselaw.law.service.ExciseLawService;
import com.exciselaw.law.utils.DateUtils;

@Controller
@SessionAttributes({"judgementForm"})
public class JudgementController {
	
	private final ExciseLawService exciseLawService;
	private final String SCREEN_ID19 = "19";
	private final String SCREEN_ID20 = "20";
	private final String SCREEN_ID21 = "21";
	private final String SCREEN_ID22 = "22";
	private final BigDecimal JUDGEMENT_TYPE1 = BigDecimal.valueOf(1);
	private final BigDecimal JUDGEMENT_GROUP2 = BigDecimal.valueOf(2);
	private final BigDecimal JUDGEMENT_GROUP3 = BigDecimal.valueOf(3);
	private final BigDecimal JUDGEMENT_GROUP4 = BigDecimal.valueOf(4);
	private final String STATUS_INACTIVE = "I";
	private final long FILESIZE = 5000000;
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(JudgementController.class);
	private static ResourceBundle resources = ResourceBundle.getBundle( "file" , Locale.ENGLISH );
	
	private TmUserinfo userInfo = null;
		
	private static final String[] JUDGEMENTDISPLAYTYPE_VALUE_BOXES = new String[]{"1","0"};
	private static final String[] JUDGEMENTDISPLAYTYPE_LABEL_BOXES = new String[]{"แสดงผล","ไม่แสดงผล"};
	
	private static List<Display> judgementDisplayTypeList = new ArrayList<Display>(2);
	
	static{
		for(int i=0;i<JUDGEMENTDISPLAYTYPE_VALUE_BOXES.length;i++){
			Display display=new Display(JUDGEMENTDISPLAYTYPE_VALUE_BOXES[i], JUDGEMENTDISPLAYTYPE_LABEL_BOXES[i]);
			judgementDisplayTypeList.add(display);
		}
	}
	
	@Autowired
	public JudgementController(ExciseLawService exciseLawService) {
		this.exciseLawService = exciseLawService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
		dataBinder.setDisallowedFields("id");
	}
	
	@RequestMapping("/judgement/constitutionalCourt")
	public String constitutionalCourt(ServletRequest request, ServletResponse response,
				@RequestParam(value="action",required = false) String action,
				@RequestParam(value="judgementId",required = false) String judgementId, Model model) {
		JudgementForm judgementForm = new JudgementForm();
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		userInfo = (TmUserinfo) session.getAttribute("userInfo");
		String forword  ="judgement/constitutionalCourtList";
		if(action!=null){
			if(action.equals("add")){			
				logger.info( "JudgementController>>constitutionalCourt add " );	
				judgementForm = new JudgementForm();
				model.addAttribute("msStatutes", exciseLawService.listMsStatutes(null));
				forword="judgement/constitutionalCourtDetail";
			}else if(action.equals("edit")){
				logger.info( "JudgementController>>constitutionalCourt edit " ); 
				TsJudgement tsJudgement = exciseLawService.findTsJudgementByjudgementId(Long.valueOf(judgementId));
				if(tsJudgement.getCreatedDate()!=null){
					judgementForm.setCreatedDate(DateUtils.getStringDateByDateTh(tsJudgement.getCreatedDate().getTime()));
				}	
				if(tsJudgement.getJudgementDate()!=null){
					judgementForm.setJudgementDate(DateUtils.getStringDateByDateTh(tsJudgement.getJudgementDate().getTime()));
				}	
				if(tsJudgement.getPronouncementDate()!=null){
					judgementForm.setPronouncementDate(DateUtils.getStringDateByDateTh(tsJudgement.getPronouncementDate().getTime()));
				}	
				
				List<TsJudgementStatue> tsJudgementStatueList = exciseLawService.findTsJudgementStatueByJudgementId(BigDecimal.valueOf(Long.valueOf(judgementId)));
				List<String> tsJudgementStatuesList = new ArrayList<String>();
				if(tsJudgementStatueList != null && tsJudgementStatueList.size()>0){
					for (TsJudgementStatue tsJudgementStatue : tsJudgementStatueList) {
						String itemId = tsJudgementStatue.getStatueId()+"";
						tsJudgementStatuesList.add(itemId);
					}
				}
				
				String[] tsJudgementStatues = new String[tsJudgementStatuesList.size()];
				judgementForm.setMsStatutesBoxes(tsJudgementStatuesList.toArray(tsJudgementStatues));
				model.addAttribute("msStatutes", exciseLawService.listMsStatutes(null));
				model.addAttribute("tsJudgementAttachList", exciseLawService.findTsJudgementAttachListByJudgementId(Long.parseLong(judgementId)));
				judgementForm.setJudgement(tsJudgement);
				forword="judgement/constitutionalCourtDetail";
			}
		} 
		setActionList(judgementForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID19));
		judgementForm.setAction(action);
		model.addAttribute("judgementDisplayTypeList", judgementDisplayTypeList);
		model.addAttribute("judgementForm", judgementForm); 
		return forword;		 
	}
	
	@RequestMapping(value="/judgement/constitutionalCourt",method = RequestMethod.POST)
	public String processConstitutionalCourt(ServletRequest request, ServletResponse response, 
				@RequestParam(value="action",required = false) String action,
				@ModelAttribute("judgementForm") JudgementForm judgementForm,
				BindingResult result,
				Model model, SessionStatus status) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		userInfo = (TmUserinfo) session.getAttribute("userInfo");
		String forword  ="judgement/constitutionalCourtList";
		TsJudgement judgement = judgementForm.getJudgement();
		DateTime nowTh = new DateTime(new Date().getTime());

		if(judgementForm.getCreatedDate()!=null && judgementForm.getCreatedDate().length()>0){
			judgement.setCreatedDate(DateUtils.getDateByStringDateTh(judgementForm.getCreatedDate()));
		}
		if(judgementForm.getJudgementDate()!=null && judgementForm.getJudgementDate().length()>0){
			judgement.setJudgementDate(DateUtils.getDateByStringDateTh(judgementForm.getJudgementDate()));
		}
		if(judgementForm.getPronouncementDate()!=null && judgementForm.getPronouncementDate().length()>0){
			judgement.setPronouncementDate(DateUtils.getDateByStringDateTh(judgementForm.getPronouncementDate()));
		}
		
		if(action.equals("doSave")){
			logger.info( "JudgementController>>news.POST doSave " );
			judgement.setJudgementType(JUDGEMENT_TYPE1);
			judgement.setCreatedBy(userInfo.getUserId());
			judgement.setCreatedDate(nowTh.toDate());
			judgement.setUpdatedBy(userInfo.getUserId());
			judgement.setUpdatedDate(nowTh.toDate());

			Long judgementId = exciseLawService.saveTsJudgement(judgement);
			
			String [] msStatutesBoxes = judgementForm.getMsStatutesBoxes();
			if(msStatutesBoxes!=null && msStatutesBoxes.length>0){
				TsJudgementStatue tsJudgementStatue = null;
				for(String statueId : msStatutesBoxes){
					tsJudgementStatue = new TsJudgementStatue();
					tsJudgementStatue.setJudgementId(BigDecimal.valueOf(judgementId));
					tsJudgementStatue.setStatueId(BigDecimal.valueOf(Long.valueOf(statueId)));
					exciseLawService.saveTsJudgementStatue(tsJudgementStatue);
				}
			}	

			MultipartFile[] files = judgementForm.getFileData();
			if(files != null && files.length>0){
	            for(MultipartFile file : files){
	            	if(file.getOriginalFilename() !=null && file.getSize() > 0){
	            		if(file.getSize() < FILESIZE){
	            			TsJudgementAttach tsJudgementAttach = saveTsJudgementAttachFile(file, judgementId.longValue(), userInfo.getUserId(), nowTh.toDate());
							exciseLawService.saveTsJudgementAttach(tsJudgementAttach);
	            		}else{
	            			judgementForm.setIsSusses("2");//TODO
	            			break;
	            		}
	            	}
	            }	
			}
			model.addAttribute("msStatutes", exciseLawService.listMsStatutes(null));
			judgementForm.setIsSusses("1");
			forword="judgement/constitutionalCourtDetail";
		}else if(action.equals("doEdit")){		
			logger.info( "JudgementController>>news.POST doEdit " );
			judgement.setUpdatedBy(userInfo.getUserId());
			judgement.setUpdatedDate(nowTh.toDate());
			exciseLawService.updateTsJudgement(judgement);
			
			List<TsJudgementStatue> tsJudgementStatueList = exciseLawService.findTsJudgementStatueByJudgementId(BigDecimal.valueOf(judgement.getJudgementId()));
			if(tsJudgementStatueList != null && tsJudgementStatueList.size()>0){
				for(TsJudgementStatue judgementStatue : tsJudgementStatueList){
					exciseLawService.deleteTsJudgementStatue(judgementStatue);
				}
			}			

			String [] msStatutesBoxes = judgementForm.getMsStatutesBoxes();
			if(msStatutesBoxes!=null && msStatutesBoxes.length>0){
				TsJudgementStatue tsJudgementStatue = null;
				for(String statueId : msStatutesBoxes){
					tsJudgementStatue = new TsJudgementStatue();
					tsJudgementStatue.setJudgementId(BigDecimal.valueOf(judgement.getJudgementId()));
					tsJudgementStatue.setStatueId(BigDecimal.valueOf(Long.valueOf(statueId)));
					exciseLawService.saveTsJudgementStatue(tsJudgementStatue);
				}
			}	

			String [] delAttachFile = judgementForm.getDeleteFileBoxes();
			if(delAttachFile!=null && delAttachFile.length>0){
				TsJudgementAttach tsJudgementAttach = null;
				for(String attachId : delAttachFile){
					tsJudgementAttach = new TsJudgementAttach();
					tsJudgementAttach.setJudgementAttachId(Long.valueOf(attachId));
					exciseLawService.deleteTsJudgementAttach(tsJudgementAttach);
				}
			}

			MultipartFile[] files = judgementForm.getFileData();
			if(files != null && files.length>0){
	            for(MultipartFile file : files){
	            	if(file.getOriginalFilename() !=null && file.getSize() > 0){
	            		if(file.getSize() < FILESIZE){
	            			TsJudgementAttach tsJudgementAttach = saveTsJudgementAttachFile(file, judgement.getJudgementId(), userInfo.getUserId(), nowTh.toDate());
							exciseLawService.saveTsJudgementAttach(tsJudgementAttach);
	            		}else{
	            			judgementForm.setIsSusses("2");//TODO
	            			break;
	            		}
	            	}
	            }	
			}				
			model.addAttribute("msStatutes", exciseLawService.listMsStatutes(null));
			model.addAttribute("tsJudgementAttachList", exciseLawService.findTsJudgementAttachListByJudgementId(judgement.getJudgementId()));
			judgementForm.setIsSusses("1"); 
			forword="judgement/constitutionalCourtDetail";
		}else if(action.equals("doDelete")){			
			logger.info( "JudgementController>>news.POST doDelete " );
			judgement.setJudgementStatus(STATUS_INACTIVE);
			exciseLawService.deleteTsJudgement(judgement);
			judgementForm = new JudgementForm();
			judgementForm.setAction(action);
			judgementForm.setIsSusses("1");
			forword="judgement/constitutionalCourtList";
		}
		setActionList(judgementForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID19));
		model.addAttribute("judgementDisplayTypeList", judgementDisplayTypeList);
		model.addAttribute("judgementForm",judgementForm);
		return forword;		 
	}
	
	@RequestMapping("/judgement/justiceCourt")
	public String justiceCourt(ServletRequest request, ServletResponse response,
				@RequestParam(value="action",required = false) String action,
				@RequestParam(value="judgementId",required = false) String judgementId, Model model) {
		JudgementForm judgementForm = new JudgementForm();
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		userInfo = (TmUserinfo) session.getAttribute("userInfo");
		String forword  ="judgement/justiceCourtList";
		if(action!=null){
			if(action.equals("add")){			
				logger.info( "JudgementController>>constitutionalCourt add " );	
				judgementForm = new JudgementForm();
				model.addAttribute("msStatutes", exciseLawService.listMsStatutes(null));
				forword="judgement/justiceCourtDetail";
			}else if(action.equals("edit")){
				logger.info( "JudgementController>>constitutionalCourt edit " ); 
				TsJudgement tsJudgement = exciseLawService.findTsJudgementByjudgementId(Long.valueOf(judgementId));
				if(tsJudgement.getCreatedDate()!=null){
					judgementForm.setCreatedDate(DateUtils.getStringDateByDateTh(tsJudgement.getCreatedDate().getTime()));
				}	
				if(tsJudgement.getJudgementDate()!=null){
					judgementForm.setJudgementDate(DateUtils.getStringDateByDateTh(tsJudgement.getJudgementDate().getTime()));
				}	
				if(tsJudgement.getPronouncementDate()!=null){
					judgementForm.setPronouncementDate(DateUtils.getStringDateByDateTh(tsJudgement.getPronouncementDate().getTime()));
				}	
				
				List<TsJudgementStatue> tsJudgementStatueList = exciseLawService.findTsJudgementStatueByJudgementId(BigDecimal.valueOf(Long.valueOf(judgementId)));
				List<String> tsJudgementStatuesList = new ArrayList<String>();
				if(tsJudgementStatueList != null && tsJudgementStatueList.size()>0){
					for (TsJudgementStatue tsJudgementStatue : tsJudgementStatueList) {
						String itemId = tsJudgementStatue.getStatueId()+"";
						tsJudgementStatuesList.add(itemId);
					}
				}
				
				String[] tsJudgementStatues = new String[tsJudgementStatuesList.size()];
				judgementForm.setMsStatutesBoxes(tsJudgementStatuesList.toArray(tsJudgementStatues));
				model.addAttribute("msStatutes", exciseLawService.listMsStatutes(null));
				model.addAttribute("tsJudgementAttachList", exciseLawService.findTsJudgementAttachListByJudgementId(Long.parseLong(judgementId)));
				judgementForm.setJudgement(tsJudgement);
				forword="judgement/justiceCourtDetail";
			}
		} 
		setActionList(judgementForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID20));
		judgementForm.setAction(action);
		model.addAttribute("judgementDisplayTypeList", judgementDisplayTypeList);
		model.addAttribute("judgementTypeList", exciseLawService.findTmJudgementTypeByGroup(JUDGEMENT_GROUP2));
		model.addAttribute("judgementForm", judgementForm); 
		return forword;		 
	}
	
	@RequestMapping(value="/judgement/justiceCourt",method = RequestMethod.POST)
	public String processJusticeCourt(ServletRequest request, ServletResponse response, 
				@RequestParam(value="action",required = false) String action,
				@ModelAttribute("judgementForm") JudgementForm judgementForm,
				BindingResult result,
				Model model, SessionStatus status) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		userInfo = (TmUserinfo) session.getAttribute("userInfo");
		String forword  ="judgement/justiceCourtList";
		TsJudgement judgement = judgementForm.getJudgement();
		DateTime nowTh = new DateTime(new Date().getTime());

		if(judgementForm.getCreatedDate()!=null && judgementForm.getCreatedDate().length()>0){
			judgement.setCreatedDate(DateUtils.getDateByStringDateTh(judgementForm.getCreatedDate()));
		}
		if(judgementForm.getJudgementDate()!=null && judgementForm.getJudgementDate().length()>0){
			judgement.setJudgementDate(DateUtils.getDateByStringDateTh(judgementForm.getJudgementDate()));
		}
		if(judgementForm.getPronouncementDate()!=null && judgementForm.getPronouncementDate().length()>0){
			judgement.setPronouncementDate(DateUtils.getDateByStringDateTh(judgementForm.getPronouncementDate()));
		}
		
		if(action.equals("doSave")){
			logger.info( "JudgementController>>news.POST doSave " );
			judgement.setCreatedBy(userInfo.getUserId());
			judgement.setCreatedDate(nowTh.toDate());
			judgement.setUpdatedBy(userInfo.getUserId());
			judgement.setUpdatedDate(nowTh.toDate());

			Long judgementId = exciseLawService.saveTsJudgement(judgement);
			
			String [] msStatutesBoxes = judgementForm.getMsStatutesBoxes();
			if(msStatutesBoxes!=null && msStatutesBoxes.length>0){
				TsJudgementStatue tsJudgementStatue = null;
				for(String statueId : msStatutesBoxes){
					tsJudgementStatue = new TsJudgementStatue();
					tsJudgementStatue.setJudgementId(BigDecimal.valueOf(judgementId));
					tsJudgementStatue.setStatueId(BigDecimal.valueOf(Long.valueOf(statueId)));
					exciseLawService.saveTsJudgementStatue(tsJudgementStatue);
				}
			}	

			MultipartFile[] files = judgementForm.getFileData();
			if(files != null && files.length>0){
	            for(MultipartFile file : files){
	            	if(file.getOriginalFilename() !=null && file.getSize() > 0){
	            		if(file.getSize() < FILESIZE){
	            			TsJudgementAttach tsJudgementAttach = saveTsJudgementAttachFile(file, judgementId.longValue(), userInfo.getUserId(), nowTh.toDate());
							exciseLawService.saveTsJudgementAttach(tsJudgementAttach);
	            		}else{
	            			judgementForm.setIsSusses("2");//TODO
	            			break;
	            		}
	            	}
	            }	
			}
			model.addAttribute("msStatutes", exciseLawService.listMsStatutes(null));
			judgementForm.setIsSusses("1");
			forword="judgement/justiceCourtDetail";
		}else if(action.equals("doEdit")){		
			logger.info( "JudgementController>>news.POST doEdit " );
			judgement.setUpdatedBy(userInfo.getUserId());
			judgement.setUpdatedDate(nowTh.toDate());
			exciseLawService.updateTsJudgement(judgement);
			
			List<TsJudgementStatue> tsJudgementStatueList = exciseLawService.findTsJudgementStatueByJudgementId(BigDecimal.valueOf(judgement.getJudgementId()));
			if(tsJudgementStatueList != null && tsJudgementStatueList.size()>0){
				for(TsJudgementStatue judgementStatue : tsJudgementStatueList){
					exciseLawService.deleteTsJudgementStatue(judgementStatue);
				}
			}			

			String [] msStatutesBoxes = judgementForm.getMsStatutesBoxes();
			if(msStatutesBoxes!=null && msStatutesBoxes.length>0){
				TsJudgementStatue tsJudgementStatue = null;
				for(String statueId : msStatutesBoxes){
					tsJudgementStatue = new TsJudgementStatue();
					tsJudgementStatue.setJudgementId(BigDecimal.valueOf(judgement.getJudgementId()));
					tsJudgementStatue.setStatueId(BigDecimal.valueOf(Long.valueOf(statueId)));
					exciseLawService.saveTsJudgementStatue(tsJudgementStatue);
				}
			}	

			String [] delAttachFile = judgementForm.getDeleteFileBoxes();
			if(delAttachFile!=null && delAttachFile.length>0){
				TsJudgementAttach tsJudgementAttach = null;
				for(String attachId : delAttachFile){
					tsJudgementAttach = new TsJudgementAttach();
					tsJudgementAttach.setJudgementAttachId(Long.valueOf(attachId));
					exciseLawService.deleteTsJudgementAttach(tsJudgementAttach);
				}
			}

			MultipartFile[] files = judgementForm.getFileData();
			if(files != null && files.length>0){
	            for(MultipartFile file : files){
	            	if(file.getOriginalFilename() !=null && file.getSize() > 0){
	            		if(file.getSize() < FILESIZE){
	            			TsJudgementAttach tsJudgementAttach = saveTsJudgementAttachFile(file, judgement.getJudgementId(), userInfo.getUserId(), nowTh.toDate());
							exciseLawService.saveTsJudgementAttach(tsJudgementAttach);
	            		}else{
	            			judgementForm.setIsSusses("2");//TODO
	            			break;
	            		}
	            	}
	            }	
			}				
			model.addAttribute("msStatutes", exciseLawService.listMsStatutes(null));
			model.addAttribute("tsJudgementAttachList", exciseLawService.findTsJudgementAttachListByJudgementId(judgement.getJudgementId()));
			judgementForm.setIsSusses("1"); 
			forword="judgement/justiceCourtDetail";
		}else if(action.equals("doDelete")){			
			logger.info( "JudgementController>>news.POST doDelete " );
			judgement.setJudgementStatus(STATUS_INACTIVE);
			exciseLawService.deleteTsJudgement(judgement);
			judgementForm = new JudgementForm();
			judgementForm.setAction(action);
			judgementForm.setIsSusses("1");
			forword="judgement/justiceCourtList";
		}
		setActionList(judgementForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID20));
		model.addAttribute("judgementDisplayTypeList", judgementDisplayTypeList);
		model.addAttribute("judgementTypeList", exciseLawService.findTmJudgementTypeByGroup(JUDGEMENT_GROUP2));
		model.addAttribute("judgementForm",judgementForm);
		return forword;		 
	}
	
	@RequestMapping("/judgement/administrativeCourt")
	public String administrativeCourt(ServletRequest request, ServletResponse response,
				@RequestParam(value="action",required = false) String action,
				@RequestParam(value="judgementId",required = false) String judgementId, Model model) {
		JudgementForm judgementForm = new JudgementForm();
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		userInfo = (TmUserinfo) session.getAttribute("userInfo");
		String forword  ="judgement/administrativeCourtList";
		if(action!=null){
			if(action.equals("add")){			
				logger.info( "JudgementController>>administrativeCourt add " );	
				judgementForm = new JudgementForm();
				model.addAttribute("msStatutes", exciseLawService.listMsStatutes(null));
				forword="judgement/administrativeCourtDetail";
			}else if(action.equals("edit")){
				logger.info( "JudgementController>>administrativeCourt edit " ); 
				TsJudgement tsJudgement = exciseLawService.findTsJudgementByjudgementId(Long.valueOf(judgementId));
				if(tsJudgement.getCreatedDate()!=null){
					judgementForm.setCreatedDate(DateUtils.getStringDateByDateTh(tsJudgement.getCreatedDate().getTime()));
				}	
				if(tsJudgement.getJudgementDate()!=null){
					judgementForm.setJudgementDate(DateUtils.getStringDateByDateTh(tsJudgement.getJudgementDate().getTime()));
				}	
				if(tsJudgement.getPronouncementDate()!=null){
					judgementForm.setPronouncementDate(DateUtils.getStringDateByDateTh(tsJudgement.getPronouncementDate().getTime()));
				}	
				
				List<TsJudgementStatue> tsJudgementStatueList = exciseLawService.findTsJudgementStatueByJudgementId(BigDecimal.valueOf(Long.valueOf(judgementId)));
				List<String> tsJudgementStatuesList = new ArrayList<String>();
				if(tsJudgementStatueList != null && tsJudgementStatueList.size()>0){
					for (TsJudgementStatue tsJudgementStatue : tsJudgementStatueList) {
						String itemId = tsJudgementStatue.getStatueId()+"";
						tsJudgementStatuesList.add(itemId);
					}
				}
				
				String[] tsJudgementStatues = new String[tsJudgementStatuesList.size()];
				judgementForm.setMsStatutesBoxes(tsJudgementStatuesList.toArray(tsJudgementStatues));
				model.addAttribute("tsJudgementAttachList", exciseLawService.findTsJudgementAttachListByJudgementId(Long.parseLong(judgementId)));
				model.addAttribute("msStatutes", exciseLawService.listMsStatutes(null));
				judgementForm.setJudgement(tsJudgement);
				forword="judgement/administrativeCourtDetail";
			}
		} 
		setActionList(judgementForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID21));
		judgementForm.setAction(action);
		model.addAttribute("judgementDisplayTypeList", judgementDisplayTypeList);
		model.addAttribute("judgementTypeList", exciseLawService.findTmJudgementTypeByGroup(JUDGEMENT_GROUP3));
		model.addAttribute("judgementForm", judgementForm); 
		return forword;		 
	}
	
	@RequestMapping(value="/judgement/administrativeCourt",method = RequestMethod.POST)
	public String processAdministrativeCourt(ServletRequest request, ServletResponse response, 
				@RequestParam(value="action",required = false) String action,
				@ModelAttribute("judgementForm") JudgementForm judgementForm,
				BindingResult result,
				Model model, SessionStatus status) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		userInfo = (TmUserinfo) session.getAttribute("userInfo");
		String forword  ="judgement/administrativeCourtList";
		TsJudgement judgement = judgementForm.getJudgement();
		DateTime nowTh = new DateTime(new Date().getTime());

		if(judgementForm.getCreatedDate()!=null && judgementForm.getCreatedDate().length()>0){
			judgement.setCreatedDate(DateUtils.getDateByStringDateTh(judgementForm.getCreatedDate()));
		}
		if(judgementForm.getJudgementDate()!=null && judgementForm.getJudgementDate().length()>0){
			judgement.setJudgementDate(DateUtils.getDateByStringDateTh(judgementForm.getJudgementDate()));
		}
		if(judgementForm.getPronouncementDate()!=null && judgementForm.getPronouncementDate().length()>0){
			judgement.setPronouncementDate(DateUtils.getDateByStringDateTh(judgementForm.getPronouncementDate()));
		}
		
		if(action.equals("doSave")){
			logger.info( "JudgementController>>administrativeCourt.POST doSave " );
			judgement.setCreatedBy(userInfo.getUserId());
			judgement.setCreatedDate(nowTh.toDate());
			judgement.setUpdatedBy(userInfo.getUserId());
			judgement.setUpdatedDate(nowTh.toDate());

			Long judgementId = exciseLawService.saveTsJudgement(judgement);
			
			String [] msStatutesBoxes = judgementForm.getMsStatutesBoxes();
			if(msStatutesBoxes!=null && msStatutesBoxes.length>0){
				TsJudgementStatue tsJudgementStatue = null;
				for(String statueId : msStatutesBoxes){
					tsJudgementStatue = new TsJudgementStatue();
					tsJudgementStatue.setJudgementId(BigDecimal.valueOf(judgementId));
					tsJudgementStatue.setStatueId(BigDecimal.valueOf(Long.valueOf(statueId)));
					exciseLawService.saveTsJudgementStatue(tsJudgementStatue);
				}
			}	

			MultipartFile[] files = judgementForm.getFileData();
			if(files != null && files.length>0){
	            for(MultipartFile file : files){
	            	if(file.getOriginalFilename() !=null && file.getSize() > 0){
	            		if(file.getSize() < FILESIZE){
	            			TsJudgementAttach tsJudgementAttach = saveTsJudgementAttachFile(file, judgementId.longValue(), userInfo.getUserId(), nowTh.toDate());
							exciseLawService.saveTsJudgementAttach(tsJudgementAttach);
	            		}else{
	            			judgementForm.setIsSusses("2");//TODO
	            			break;
	            		}
	            	}
	            }	
			}
			judgementForm.setIsSusses("1");
			model.addAttribute("msStatutes", exciseLawService.listMsStatutes(null));
			forword="judgement/administrativeCourtDetail";
		}else if(action.equals("doEdit")){		
			logger.info( "JudgementController>>administrativeCourt.POST doEdit " );
			judgement.setUpdatedBy(userInfo.getUserId());
			judgement.setUpdatedDate(nowTh.toDate());
			exciseLawService.updateTsJudgement(judgement);
			
			List<TsJudgementStatue> tsJudgementStatueList = exciseLawService.findTsJudgementStatueByJudgementId(BigDecimal.valueOf(judgement.getJudgementId()));
			if(tsJudgementStatueList != null && tsJudgementStatueList.size()>0){
				for(TsJudgementStatue judgementStatue : tsJudgementStatueList){
					exciseLawService.deleteTsJudgementStatue(judgementStatue);
				}
			}			

			String [] msStatutesBoxes = judgementForm.getMsStatutesBoxes();
			if(msStatutesBoxes!=null && msStatutesBoxes.length>0){
				TsJudgementStatue tsJudgementStatue = null;
				for(String statueId : msStatutesBoxes){
					tsJudgementStatue = new TsJudgementStatue();
					tsJudgementStatue.setJudgementId(BigDecimal.valueOf(judgement.getJudgementId()));
					tsJudgementStatue.setStatueId(BigDecimal.valueOf(Long.valueOf(statueId)));
					exciseLawService.saveTsJudgementStatue(tsJudgementStatue);
				}
			}	

			String [] delAttachFile = judgementForm.getDeleteFileBoxes();
			if(delAttachFile!=null && delAttachFile.length>0){
				TsJudgementAttach tsJudgementAttach = null;
				for(String attachId : delAttachFile){
					tsJudgementAttach = new TsJudgementAttach();
					tsJudgementAttach.setJudgementAttachId(Long.valueOf(attachId));
					exciseLawService.deleteTsJudgementAttach(tsJudgementAttach);
				}
			}

			MultipartFile[] files = judgementForm.getFileData();
			if(files != null && files.length>0){
	            for(MultipartFile file : files){
	            	if(file.getOriginalFilename() !=null && file.getSize() > 0){
	            		if(file.getSize() < FILESIZE){
	            			TsJudgementAttach tsJudgementAttach = saveTsJudgementAttachFile(file, judgement.getJudgementId(), userInfo.getUserId(), nowTh.toDate());
							exciseLawService.saveTsJudgementAttach(tsJudgementAttach);
	            		}else{
	            			judgementForm.setIsSusses("2");//TODO
	            			break;
	            		}
	            	}
	            }	
			}				
			model.addAttribute("tsJudgementAttachList", exciseLawService.findTsJudgementAttachListByJudgementId(judgement.getJudgementId()));
			model.addAttribute("msStatutes", exciseLawService.listMsStatutes(null));
			judgementForm.setIsSusses("1"); 
			forword="judgement/administrativeCourtDetail";
		}else if(action.equals("doDelete")){			
			logger.info( "JudgementController>>administrativeCourt.POST doDelete " );
			judgement.setJudgementStatus(STATUS_INACTIVE);
			exciseLawService.deleteTsJudgement(judgement);
			judgementForm = new JudgementForm();
			judgementForm.setAction(action);
			judgementForm.setIsSusses("1");
			forword="judgement/administrativeCourtList";
		}
		setActionList(judgementForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID21));
		model.addAttribute("judgementDisplayTypeList", judgementDisplayTypeList);
		model.addAttribute("judgementTypeList", exciseLawService.findTmJudgementTypeByGroup(JUDGEMENT_GROUP3));
		model.addAttribute("judgementForm",judgementForm);
		return forword;		 
	}
	
	@RequestMapping("/judgement/otherCourt")
	public String otherCourt(ServletRequest request, ServletResponse response,
				@RequestParam(value="action",required = false) String action,
				@RequestParam(value="judgementId",required = false) String judgementId, Model model) {
		JudgementForm judgementForm = new JudgementForm();
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		userInfo = (TmUserinfo) session.getAttribute("userInfo");
		String forword  ="judgement/otherCourtList";
		if(action!=null){
			if(action.equals("add")){			
				logger.info( "JudgementController>>otherCourt add " );	
				judgementForm = new JudgementForm();
				forword="judgement/otherCourtDetail";
			}else if(action.equals("edit")){
				logger.info( "JudgementController>>otherCourt edit " ); 
				TsJudgement tsJudgement = exciseLawService.findTsJudgementByjudgementId(Long.valueOf(judgementId));
				if(tsJudgement.getCreatedDate()!=null){
					judgementForm.setCreatedDate(DateUtils.getStringDateByDateTh(tsJudgement.getCreatedDate().getTime()));
				}	
				if(tsJudgement.getJudgementDate()!=null){
					judgementForm.setJudgementDate(DateUtils.getStringDateByDateTh(tsJudgement.getJudgementDate().getTime()));
				}	
				if(tsJudgement.getPronouncementDate()!=null){
					judgementForm.setPronouncementDate(DateUtils.getStringDateByDateTh(tsJudgement.getPronouncementDate().getTime()));
				}	
				
				List<TsJudgementStatue> tsJudgementStatueList = exciseLawService.findTsJudgementStatueByJudgementId(BigDecimal.valueOf(Long.valueOf(judgementId)));
				List<String> tsJudgementStatuesList = new ArrayList<String>();
				if(tsJudgementStatueList != null && tsJudgementStatueList.size()>0){
					for (TsJudgementStatue tsJudgementStatue : tsJudgementStatueList) {
						String itemId = tsJudgementStatue.getStatueId()+"";
						tsJudgementStatuesList.add(itemId);
					}
				}
				
				String[] tsJudgementStatues = new String[tsJudgementStatuesList.size()];
				judgementForm.setMsStatutesBoxes(tsJudgementStatuesList.toArray(tsJudgementStatues));
				model.addAttribute("tsJudgementAttachList", exciseLawService.findTsJudgementAttachListByJudgementId(Long.parseLong(judgementId)));
				judgementForm.setJudgement(tsJudgement);
				forword="judgement/otherCourtDetail";
			}
		} 
		setActionList(judgementForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID22));
		judgementForm.setAction(action);
		model.addAttribute("judgementDisplayTypeList", judgementDisplayTypeList);
		model.addAttribute("msStatutes", exciseLawService.listMsStatutes(null));
		model.addAttribute("judgementTypeList", exciseLawService.findTmJudgementTypeByGroup(JUDGEMENT_GROUP4));
		model.addAttribute("judgementForm", judgementForm); 
		return forword;		 
	}
	
	@RequestMapping(value="/judgement/otherCourt",method = RequestMethod.POST)
	public String processOtherCourt(ServletRequest request, ServletResponse response, 
				@RequestParam(value="action",required = false) String action,
				@ModelAttribute("judgementForm") JudgementForm judgementForm,
				BindingResult result,
				Model model, SessionStatus status) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		userInfo = (TmUserinfo) session.getAttribute("userInfo");
		String forword  ="judgement/otherCourtList";
		TsJudgement judgement = judgementForm.getJudgement();
		DateTime nowTh = new DateTime(new Date().getTime());

		if(judgementForm.getCreatedDate()!=null && judgementForm.getCreatedDate().length()>0){
			judgement.setCreatedDate(DateUtils.getDateByStringDateTh(judgementForm.getCreatedDate()));
		}
		if(judgementForm.getJudgementDate()!=null && judgementForm.getJudgementDate().length()>0){
			judgement.setJudgementDate(DateUtils.getDateByStringDateTh(judgementForm.getJudgementDate()));
		}
		if(judgementForm.getPronouncementDate()!=null && judgementForm.getPronouncementDate().length()>0){
			judgement.setPronouncementDate(DateUtils.getDateByStringDateTh(judgementForm.getPronouncementDate()));
		}
		
		if(action.equals("doSave")){
			logger.info( "JudgementController>>otherCourt.POST doSave " );
			judgement.setCreatedBy(userInfo.getUserId());
			judgement.setCreatedDate(nowTh.toDate());
			judgement.setUpdatedBy(userInfo.getUserId());
			judgement.setUpdatedDate(nowTh.toDate());

			Long judgementId = exciseLawService.saveTsJudgement(judgement);
			
			String [] msStatutesBoxes = judgementForm.getMsStatutesBoxes();
			if(msStatutesBoxes!=null && msStatutesBoxes.length>0){
				TsJudgementStatue tsJudgementStatue = null;
				for(String statueId : msStatutesBoxes){
					tsJudgementStatue = new TsJudgementStatue();
					tsJudgementStatue.setJudgementId(BigDecimal.valueOf(judgementId));
					tsJudgementStatue.setStatueId(BigDecimal.valueOf(Long.valueOf(statueId)));
					exciseLawService.saveTsJudgementStatue(tsJudgementStatue);
				}
			}	

			MultipartFile[] files = judgementForm.getFileData();
			if(files != null && files.length>0){
	            for(MultipartFile file : files){
	            	if(file.getOriginalFilename() !=null && file.getSize() > 0){
	            		if(file.getSize() < FILESIZE){
	            			TsJudgementAttach tsJudgementAttach = saveTsJudgementAttachFile(file, judgementId.longValue(), userInfo.getUserId(), nowTh.toDate());
							exciseLawService.saveTsJudgementAttach(tsJudgementAttach);
	            		}else{
	            			judgementForm.setIsSusses("2");//TODO
	            			break;
	            		}
	            	}
	            }	
			}
			model.addAttribute("msStatutes", exciseLawService.listMsStatutes(null));
			judgementForm.setIsSusses("1");
			forword="judgement/otherCourtDetail";
		}else if(action.equals("doEdit")){		
			logger.info( "JudgementController>>otherCourt.POST doEdit " );
			judgement.setUpdatedBy(userInfo.getUserId());
			judgement.setUpdatedDate(nowTh.toDate());
			exciseLawService.updateTsJudgement(judgement);
			
			List<TsJudgementStatue> tsJudgementStatueList = exciseLawService.findTsJudgementStatueByJudgementId(BigDecimal.valueOf(judgement.getJudgementId()));
			if(tsJudgementStatueList != null && tsJudgementStatueList.size()>0){
				for(TsJudgementStatue judgementStatue : tsJudgementStatueList){
					exciseLawService.deleteTsJudgementStatue(judgementStatue);
				}
			}			

			String [] msStatutesBoxes = judgementForm.getMsStatutesBoxes();
			if(msStatutesBoxes!=null && msStatutesBoxes.length>0){
				TsJudgementStatue tsJudgementStatue = null;
				for(String statueId : msStatutesBoxes){
					tsJudgementStatue = new TsJudgementStatue();
					tsJudgementStatue.setJudgementId(BigDecimal.valueOf(judgement.getJudgementId()));
					tsJudgementStatue.setStatueId(BigDecimal.valueOf(Long.valueOf(statueId)));
					exciseLawService.saveTsJudgementStatue(tsJudgementStatue);
				}
			}	

			String [] delAttachFile = judgementForm.getDeleteFileBoxes();
			if(delAttachFile!=null && delAttachFile.length>0){
				TsJudgementAttach tsJudgementAttach = null;
				for(String attachId : delAttachFile){
					tsJudgementAttach = new TsJudgementAttach();
					tsJudgementAttach.setJudgementAttachId(Long.valueOf(attachId));
					exciseLawService.deleteTsJudgementAttach(tsJudgementAttach);
				}
			}

			MultipartFile[] files = judgementForm.getFileData();
			if(files != null && files.length>0){
	            for(MultipartFile file : files){
	            	if(file.getOriginalFilename() !=null && file.getSize() > 0){
	            		if(file.getSize() < FILESIZE){
	            			TsJudgementAttach tsJudgementAttach = saveTsJudgementAttachFile(file, judgement.getJudgementId(), userInfo.getUserId(), nowTh.toDate());
							exciseLawService.saveTsJudgementAttach(tsJudgementAttach);
	            		}else{
	            			judgementForm.setIsSusses("2");//TODO
	            			break;
	            		}
	            	}
	            }	
			}				
			model.addAttribute("tsJudgementAttachList", exciseLawService.findTsJudgementAttachListByJudgementId(judgement.getJudgementId()));
			model.addAttribute("msStatutes", exciseLawService.listMsStatutes(null));
			judgementForm.setIsSusses("1"); 
			forword="judgement/otherCourtDetail";
		}else if(action.equals("doDelete")){			
			logger.info( "JudgementController>>otherCourt.POST doDelete " );
			judgement.setJudgementStatus(STATUS_INACTIVE);
			exciseLawService.deleteTsJudgement(judgement);
			judgementForm = new JudgementForm();
			judgementForm.setAction(action);
			judgementForm.setIsSusses("1");
			forword="judgement/otherCourtList";
		}
		setActionList(judgementForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID22));
		model.addAttribute("judgementDisplayTypeList", judgementDisplayTypeList);
		model.addAttribute("judgementTypeList", exciseLawService.findTmJudgementTypeByGroup(JUDGEMENT_GROUP3));
		model.addAttribute("judgementForm",judgementForm);
		return forword;		 
	}
	
	private void setActionList(JudgementForm judgementForm, String actionList){
		if(actionList.indexOf("ADD") != -1){
			judgementForm.setAdd("true");
		}
		if(actionList.indexOf("EDIT") != -1){
			judgementForm.setEdit("true");
		}
		if(actionList.indexOf("DELETE") != -1){
			judgementForm.setDelete("true");
		}
		if(actionList.indexOf("VIEW") != -1){
			judgementForm.setView("true");
		}		
	}
	
	private TsJudgementAttach saveTsJudgementAttachFile(MultipartFile fileUpload, long judgementId, String userId, Date today){
		TsJudgementAttach tsJudgementAttach = new TsJudgementAttach();
		FileOutputStream fos = null;
		if (fileUpload.getSize() > 0) {
            try {
		        byte[] bytes = fileUpload.getBytes();		 

            	String filePath = resources.getString("judgement_path")+"/"+judgementId;
            	File fileDir = new File(filePath);
            	if(!fileDir.exists()){
            		fileDir.mkdirs();
            	}
            	File file = new File(fileDir, fileUpload.getOriginalFilename());
		        fos = new FileOutputStream(file);
		        fos.write(bytes);
		        fos.flush();
		        fos.close();
		        
		        tsJudgementAttach.setFileName(fileUpload.getOriginalFilename());
		        tsJudgementAttach.setFilePath(filePath);
		        tsJudgementAttach.setJudgementId(BigDecimal.valueOf(judgementId));
		        tsJudgementAttach.setCreatedBy(userInfo.getUserId());
		        tsJudgementAttach.setUpdatedBy(userInfo.getUserId());
		        tsJudgementAttach.setCreatedDate(today);
		        tsJudgementAttach.setUpdatedDate(today);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try { fos.close(); } catch (IOException e) { e.printStackTrace(); }
			}
	    }
		return tsJudgementAttach;
	}
}
