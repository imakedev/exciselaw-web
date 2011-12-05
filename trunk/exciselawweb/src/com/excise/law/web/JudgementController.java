package com.excise.law.web;

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

import com.excise.law.domain.Display;
import com.excise.law.domain.TmUserinfo;
import com.excise.law.form.JudgementForm;
import com.excise.law.service.LawService;

@Controller
@SessionAttributes({"judgementForm"})
public class JudgementController {
	
	private final LawService lawService;
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
//	private static ResourceBundle resources = ResourceBundle.getBundle( "file" , Locale.ENGLISH );

		
	private static final String[] JUDGEMENTDISPLAYTYPE_VALUE_BOXES = new String[]{"1","0"};
	private static final String[] JUDGEMENTDISPLAYTYPE_LABEL_BOXES = new String[]{"à¹�à¸ªà¸”à¸‡à¸œà¸¥","à¹„à¸¡à¹ˆà¹�à¸ªà¸”à¸‡à¸œà¸¥"};
	
	private static List<Display> judgementDisplayTypeList = new ArrayList<Display>(2);
	
	static{
		for(int i=0;i<JUDGEMENTDISPLAYTYPE_VALUE_BOXES.length;i++){
			Display display=new Display(JUDGEMENTDISPLAYTYPE_VALUE_BOXES[i], JUDGEMENTDISPLAYTYPE_LABEL_BOXES[i]);
			judgementDisplayTypeList.add(display);
		}
	}
	
	@Autowired
	public JudgementController(LawService lawService) {
		this.lawService = lawService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
		dataBinder.setDisallowedFields("id");
	}
	
	@RequestMapping("/judgement/judgement")
	public String judgement(ServletRequest request, ServletResponse response,
				@RequestParam(value="action",required = false) String action,
				@RequestParam(value="judgementId",required = false) String judgementId, Model model) {
		JudgementForm judgementForm = new JudgementForm();
		String forword  ="judgement/judgementList";
		if(action!=null && action.equals("edit")){
			forword="judgement/judgementDetail";
		}
		
		model.addAttribute("judgementGroupList", lawService.getTmJudgementGroup());
		model.addAttribute("judgementForm",judgementForm);
		return forword;		 
	}
	
//	@RequestMapping(value="/judgement/constitutionalCourt",method = RequestMethod.POST)
//	public String processConstitutionalCourt(ServletRequest request, ServletResponse response, 
//				@RequestParam(value="action",required = false) String action,
//				@ModelAttribute("judgementForm") JudgementForm judgementForm,
//				BindingResult result,
//				Model model, SessionStatus status) {
//		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//		HttpSession session = httpServletRequest.getSession();
//		userInfo = (TmUserinfo) session.getAttribute("userInfo");
//		String forword  ="judgement/constitutionalCourtList";
//		TsJudgement judgement = judgementForm.getJudgement();
//		DateTime nowTh = new DateTime(new Date().getTime());
//
//		if(judgementForm.getCreatedDate()!=null && judgementForm.getCreatedDate().length()>0){
//			judgement.setCreatedDate(DateUtils.getDateByStringDateTh(judgementForm.getCreatedDate()));
//		}
//		if(judgementForm.getJudgementDate()!=null && judgementForm.getJudgementDate().length()>0){
//			judgement.setJudgementDate(DateUtils.getDateByStringDateTh(judgementForm.getJudgementDate()));
//		}
//		if(judgementForm.getPronouncementDate()!=null && judgementForm.getPronouncementDate().length()>0){
//			judgement.setPronouncementDate(DateUtils.getDateByStringDateTh(judgementForm.getPronouncementDate()));
//		}
//		
//		if(action.equals("doSave")){
//			logger.info( "JudgementController>>news.POST doSave " );
//			judgement.setJudgementType(JUDGEMENT_TYPE1);
//			judgement.setCreatedBy(userInfo.getUserId());
//			judgement.setCreatedDate(nowTh.toDate());
//			judgement.setUpdatedBy(userInfo.getUserId());
//			judgement.setUpdatedDate(nowTh.toDate());
//
//			Long judgementId = lawService.saveTsJudgement(judgement);
//			
//			String [] msStatutesBoxes = judgementForm.getMsStatutesBoxes();
//			if(msStatutesBoxes!=null && msStatutesBoxes.length>0){
//				TsJudgementStatue tsJudgementStatue = null;
//				for(String statueId : msStatutesBoxes){
//					tsJudgementStatue = new TsJudgementStatue();
//					tsJudgementStatue.setJudgementId(BigDecimal.valueOf(judgementId));
//					tsJudgementStatue.setStatueId(BigDecimal.valueOf(Long.valueOf(statueId)));
//					lawService.saveTsJudgementStatue(tsJudgementStatue);
//				}
//			}	
//
//			MultipartFile[] files = judgementForm.getFileData();
//			if(files != null && files.length>0){
//	            for(MultipartFile file : files){
//	            	if(file.getOriginalFilename() !=null && file.getSize() > 0){
//	            		if(file.getSize() < FILESIZE){
//	            			TsJudgementAttach tsJudgementAttach = saveTsJudgementAttachFile(file, judgementId.longValue(), userInfo.getUserId(), nowTh.toDate());
//							lawService.saveTsJudgementAttach(tsJudgementAttach);
//	            		}else{
//	            			judgementForm.setIsSusses("2");//TODO
//	            			break;
//	            		}
//	            	}
//	            }	
//			}
//			model.addAttribute("msStatutes", lawService.listMsStatutes(null));
//			judgementForm.setIsSusses("1");
//			forword="judgement/constitutionalCourtDetail";
//		}else if(action.equals("doEdit")){		
//			logger.info( "JudgementController>>news.POST doEdit " );
//			judgement.setUpdatedBy(userInfo.getUserId());
//			judgement.setUpdatedDate(nowTh.toDate());
//			lawService.updateTsJudgement(judgement);
//			
//			List<TsJudgementStatue> tsJudgementStatueList = lawService.findTsJudgementStatueByJudgementId(BigDecimal.valueOf(judgement.getJudgementId()));
//			if(tsJudgementStatueList != null && tsJudgementStatueList.size()>0){
//				for(TsJudgementStatue judgementStatue : tsJudgementStatueList){
//					lawService.deleteTsJudgementStatue(judgementStatue);
//				}
//			}			
//
//			String [] msStatutesBoxes = judgementForm.getMsStatutesBoxes();
//			if(msStatutesBoxes!=null && msStatutesBoxes.length>0){
//				TsJudgementStatue tsJudgementStatue = null;
//				for(String statueId : msStatutesBoxes){
//					tsJudgementStatue = new TsJudgementStatue();
//					tsJudgementStatue.setJudgementId(BigDecimal.valueOf(judgement.getJudgementId()));
//					tsJudgementStatue.setStatueId(BigDecimal.valueOf(Long.valueOf(statueId)));
//					lawService.saveTsJudgementStatue(tsJudgementStatue);
//				}
//			}	
//
//			String [] delAttachFile = judgementForm.getDeleteFileBoxes();
//			if(delAttachFile!=null && delAttachFile.length>0){
//				TsJudgementAttach tsJudgementAttach = null;
//				for(String attachId : delAttachFile){
//					tsJudgementAttach = new TsJudgementAttach();
//					tsJudgementAttach.setJudgementAttachId(Long.valueOf(attachId));
//					lawService.deleteTsJudgementAttach(tsJudgementAttach);
//				}
//			}
//
//			MultipartFile[] files = judgementForm.getFileData();
//			if(files != null && files.length>0){
//	            for(MultipartFile file : files){
//	            	if(file.getOriginalFilename() !=null && file.getSize() > 0){
//	            		if(file.getSize() < FILESIZE){
//	            			TsJudgementAttach tsJudgementAttach = saveTsJudgementAttachFile(file, judgement.getJudgementId(), userInfo.getUserId(), nowTh.toDate());
//							lawService.saveTsJudgementAttach(tsJudgementAttach);
//	            		}else{
//	            			judgementForm.setIsSusses("2");//TODO
//	            			break;
//	            		}
//	            	}
//	            }	
//			}				
//			model.addAttribute("msStatutes", lawService.listMsStatutes(null));
//			model.addAttribute("tsJudgementAttachList", lawService.findTsJudgementAttachListByJudgementId(judgement.getJudgementId()));
//			judgementForm.setIsSusses("1"); 
//			forword="judgement/constitutionalCourtDetail";
//		}else if(action.equals("doDelete")){			
//			logger.info( "JudgementController>>news.POST doDelete " );
//			judgement.setJudgementStatus(STATUS_INACTIVE);
//			lawService.deleteTsJudgement(judgement);
//			judgementForm = new JudgementForm();
//			judgementForm.setAction(action);
//			judgementForm.setIsSusses("1");
//			forword="judgement/constitutionalCourtList";
//		}
//		setActionList(judgementForm, lawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID19));
//		model.addAttribute("judgementDisplayTypeList", judgementDisplayTypeList);
//		model.addAttribute("judgementForm",judgementForm);
//		return forword;		 
//	}	
}
