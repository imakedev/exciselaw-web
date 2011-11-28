package com.exciselaw.law.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.joda.time.chrono.BuddhistChronology;
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
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import com.exciselaw.law.domain.Display;
import com.exciselaw.law.domain.TmConsultDoc;
import com.exciselaw.law.domain.TmRole;
import com.exciselaw.law.domain.TmScreen;
import com.exciselaw.law.domain.TmUserinfo;
import com.exciselaw.law.domain.TsDocResolution;
import com.exciselaw.law.domain.TsDocState;
import com.exciselaw.law.domain.TsDocStatue;
import com.exciselaw.law.domain.TsMasterWord;
import com.exciselaw.law.domain.TsRoleScreen;
import com.exciselaw.law.domain.TsThesaurus;
import com.exciselaw.law.form.ConsultDocForm;
import com.exciselaw.law.form.ManagementForm;
import com.exciselaw.law.service.ExciseLawService;
import com.exciselaw.law.utils.DateUtils;
import com.exciselaw.law.utils.Paging;

@Controller
@SessionAttributes({"managementForm", "consultDocForm"})
public class ManagementController {
	private final ExciseLawService exciseLawService;
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ManagementController.class);
	
	private TmUserinfo userInfo = null;
	
	private static final String[] DEPARTMENTTYPE_VALUE_BOXES = new String[]{"I","E","O"};
	private static final String[] DEPARTMENTTYPE_LABEL_BOXES = new String[]{"หน่วยงานภายใน(ส่วนกลาง)","หน่วยงานภายนอก(ส่วนภูมิภาค)","หน่วยงานภายนอก"};
	private static final String[] SECURITYLEVEL_VALUE_BOXES = new String[]{"1","2","3"};
	private static final String[] SECURITYLEVEL_LABEL_BOXES = new String[]{"ลับ","ลับมาก","ลับมากที่สุด"};
	private static final String[] STATUS_VALUE_BOXES = new String[]{"all","","0","1"};
	private static final String[] STATUS_LABEL_BOXES = new String[]{"--- ทั้งหมด ---","เรื่องที่ได้รับมอบหมาย","เรื่องระหว่างดำเนินการ","เรื่องเสร็จสิ้น"};

	private static List<Display> departmentTypeList = new ArrayList<Display>(3);
	private static List<Display> securityLevelList = new ArrayList<Display>(3);
	private static List<Display> statusList = new ArrayList<Display>(4);
	
	static{
		for(int i=0;i<DEPARTMENTTYPE_VALUE_BOXES.length;i++){
			Display display=new Display(DEPARTMENTTYPE_VALUE_BOXES[i],DEPARTMENTTYPE_LABEL_BOXES[i]);
			departmentTypeList.add(display);
		}
		for(int i=0;i<SECURITYLEVEL_VALUE_BOXES.length;i++){
			Display display=new Display(SECURITYLEVEL_VALUE_BOXES[i],SECURITYLEVEL_LABEL_BOXES[i]);
			securityLevelList.add(display);
		}	
		for(int i=0;i<STATUS_VALUE_BOXES.length;i++){
			Display display=new Display(STATUS_VALUE_BOXES[i],STATUS_LABEL_BOXES[i]);
			statusList.add(display);
		}
	}
	
	@Autowired
	public ManagementController(ExciseLawService exciseLawService) {
		this.exciseLawService = exciseLawService;
	}
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
		dataBinder.setDisallowedFields("id");
	}
	
	@RequestMapping("/management/role")
	public String role(ServletRequest request, ServletResponse response,
				@RequestParam(value="action",required = false) String action,
				@RequestParam(value="roleId",required = false) String roleId,
				Model model) {
		ManagementForm managementForm = new ManagementForm();
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		String forword  ="management/roleList";
		managementForm.setAction(action);
		model.addAttribute("managementForm", managementForm); 
		return forword;		 
	}
	
	@RequestMapping("/management/roleScreen")
	public String roleScreen(ServletRequest request, ServletResponse response,
				@RequestParam(value="action",required = false) String action,
				@RequestParam(value="roleId",required = false) String roleId,
				@RequestParam(value="screenId",required = false) String screenId, Model model) {
		ManagementForm managementForm = new ManagementForm();
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();

		String forword  ="management/roleScreenList";
		if(action!=null){
			if(action.equals("edit")){
				logger.info( "ManagementController>>role editScreen " ); 
				TmScreen tmScreen = exciseLawService.findTmScreenByScreenId(Long.valueOf(screenId));

				List<TsRoleScreen> tsRoleScreenList = exciseLawService.getTsRoleScreenByRole(roleId, BigDecimal.valueOf(Long.parseLong(screenId)));
				List<String> tsRoleScreensList = new ArrayList<String>();
				if(tsRoleScreenList != null && tsRoleScreenList.size()>0){
					for (TsRoleScreen tsDocStatue : tsRoleScreenList) {
						String itemId = tsDocStatue.getActionId()+"";
						tsRoleScreensList.add(itemId);
					}
				}
				String[] tsRoleScreens = new String[tsRoleScreensList.size()];
				managementForm.setActionBoxes(tsRoleScreensList.toArray(tsRoleScreens));

				managementForm.setTmScreen(tmScreen);
	    		model.addAttribute("actionList", exciseLawService.getActionListByScreenId(BigDecimal.valueOf(Long.parseLong(screenId))));
				forword="management/roleScreenDetail";
			}
		} 
		TmRole tmRole = exciseLawService.findTmRoleByRoleId(roleId);
		managementForm.setTmRole(tmRole);
		managementForm.setAction(action);
		model.addAttribute("managementForm", managementForm); 
		return forword;		 
	}
	
	@RequestMapping(value="/management/roleScreen",method = RequestMethod.POST)
	public String processRoleScreen(ServletRequest request, ServletResponse response, 
				@RequestParam(value="action",required = false) String action,
				@ModelAttribute("managementForm") ManagementForm managementForm,
				BindingResult result,
				Model model, SessionStatus status) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		TmUserinfo userInfo = (TmUserinfo)session.getAttribute("userInfo");
		String forword  ="/management/roleScreenList";
		TmRole tmRole = managementForm.getTmRole();
		TmScreen tmScreen = managementForm.getTmScreen();
		DateTime nowTh = new DateTime(new Date().getTime()).withChronology(BuddhistChronology.getInstance());
		
		if(action.equals("doEdit")){		
			logger.info( "ManagementController>>role.POST doEdit " );			

			List<TsRoleScreen> roleScreenList = exciseLawService.getTsRoleScreenByRole(tmRole.getRoleId(), BigDecimal.valueOf(tmScreen.getScreenId()));
			if(roleScreenList != null && roleScreenList.size()>0){
				for(TsRoleScreen rScreen : roleScreenList){
					exciseLawService.deleteTsRoleScreen(rScreen);
				}
			}
			
			String [] actionsBoxes = managementForm.getActionBoxes();
			if(actionsBoxes!=null && actionsBoxes.length>0){
				TsRoleScreen roleScreen = null;
				for(String actionId : actionsBoxes){
					roleScreen = new TsRoleScreen();
					roleScreen.setActionId(BigDecimal.valueOf(Long.valueOf(actionId)));
					roleScreen.setRoleId(tmRole.getRoleId());
					roleScreen.setScreenId(BigDecimal.valueOf(tmScreen.getScreenId()));
					roleScreen.setCreatedBy(userInfo.getUserId());
					roleScreen.setCreatedDate(nowTh.toDate());
					roleScreen.setUpdatedBy(userInfo.getUserId());
					roleScreen.setUpdatedDate(nowTh.toDate());
					exciseLawService.saveTsRoleScreen(roleScreen);
				}
			}	
    		model.addAttribute("actionList", exciseLawService.getActionListByScreenId(BigDecimal.valueOf(tmScreen.getScreenId())));
    		managementForm.setIsSusses("1");
			forword="/management/roleScreenDetail";
		}else if(action.equals("doDelete")){		
			logger.info( "ManagementController>>role.POST doDelete " );			

			List<TsRoleScreen> roleScreenList = exciseLawService.getTsRoleScreenByRole(tmRole.getRoleId(), BigDecimal.valueOf(tmScreen.getScreenId()));
			if(roleScreenList != null && roleScreenList.size()>0){
				for(TsRoleScreen rScreen : roleScreenList){
					exciseLawService.deleteTsRoleScreen(rScreen);
				}
			}
    		model.addAttribute("actionList", exciseLawService.getActionListByScreenId(BigDecimal.valueOf(tmScreen.getScreenId())));
    		managementForm.setAction(action);
    		managementForm.setIsSusses("1");
			forword="/management/roleScreenList";
		}else if(action.equals("doBack")){
			logger.info( "ManagementController>>role doBack " ); 
			forword="management/roleList";
		}else if(action.equals("doBackScreen")){
			logger.info( "ManagementController>>role doBackScreen " ); 
			forword="management/roleScreenList";
		}
		model.addAttribute("managementForm",managementForm);
		return forword;		 
	}
	
	@RequestMapping("/management/consultDocLock")
	public String consultDocAnswer(ServletRequest request, ServletResponse response, 
				@RequestParam(value="action",required = false) String action,
				@RequestParam(value="docId",required = false) String docId, Model model) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		userInfo = (TmUserinfo) session.getAttribute("userInfo");
		ConsultDocForm consultDocForm = new ConsultDocForm();
		String forword  ="management/consultDocLockList";
		String orgId = "12344";
		if(action!=null){
			if(action.equals("edit")){
				logger.info( "ConsultDocController>>consultDocAnswer edit " ); 
				List list = exciseLawService.findVConsultDocByDocId(new BigDecimal(docId));
				TmConsultDoc tmConsultDoc = (TmConsultDoc) list.get(0);
				TsDocState tsDocState = (TsDocState) list.get(1);
				tsDocState.setDocComment("");
				TsDocResolution tsDocResolution = exciseLawService.findLastTsDocResolution(Long.parseLong(docId));

				if(tmConsultDoc.getDocRefDate()!=null){
					consultDocForm.setDocRefDate(DateUtils.getStringDateByDateTh(tmConsultDoc.getDocRefDate().getTime()));
				}
				if(tmConsultDoc.getOfficeRecDate()!=null){
					consultDocForm.setOfficeRecDate(DateUtils.getStringDateByDateTh(tmConsultDoc.getOfficeRecDate().getTime()));
				}			
				if(tmConsultDoc.getDocDate()!=null){
					consultDocForm.setDocDate(DateUtils.getStringDateByDateTh(tmConsultDoc.getDocDate().getTime()));
				}		
				if(tmConsultDoc.getBureauRecDate()!=null){
					consultDocForm.setBureauRecDate(DateUtils.getStringDateByDateTh(tmConsultDoc.getBureauRecDate().getTime()));
				}
				if(tmConsultDoc.getSectionRecDate()!=null){
					consultDocForm.setSectionRecDate(DateUtils.getStringDateByDateTh(tmConsultDoc.getSectionRecDate().getTime()));
				}
				if(tmConsultDoc.getDocSendDate()!=null){
					consultDocForm.setDocSendDate(DateUtils.getStringDateByDateTh(tmConsultDoc.getDocSendDate().getTime()));
				}
				if(tmConsultDoc.getBureauSendDate()!=null){
					consultDocForm.setBureauSendDate(DateUtils.getStringDateByDateTh(tmConsultDoc.getBureauSendDate().getTime()));
				}
				if(tmConsultDoc.getSectionSendDate()!=null){
					consultDocForm.setSectionSendDate(DateUtils.getStringDateByDateTh(tmConsultDoc.getSectionSendDate().getTime()));
				}
				if(tmConsultDoc.getCreatedDate()!=null){
					consultDocForm.setCreatedDate(DateUtils.getStringDateByDateTh(tmConsultDoc.getCreatedDate().getTime()));
				}
				
				if(tmConsultDoc.getDocType().equals("I")){
					consultDocForm.setInternalOfficeId(tmConsultDoc.getConsultOrg());
				}else if(tmConsultDoc.getDocType().equals("E")){
					consultDocForm.setExternalOfficeId(tmConsultDoc.getConsultOrg());
				}

				List<TsDocStatue> tsDocStatueList = exciseLawService.findTsDocStatueBydocId(BigDecimal.valueOf(tmConsultDoc.getDocId()));
				List<String> tsDocStatuesList = new ArrayList<String>();
				if(tsDocStatueList != null && tsDocStatueList.size()>0){
					for (TsDocStatue tsDocStatue : tsDocStatueList) {
						String itemId = tsDocStatue.getStatueId()+"";
						tsDocStatuesList.add(itemId);
					}
				}
				String[] tsDocStatues = new String[tsDocStatuesList.size()];
				consultDocForm.setMsStatutesBoxes(tsDocStatuesList.toArray(tsDocStatues));
				consultDocForm.setTmConsultDoc(tmConsultDoc);
				consultDocForm.setTsDocState(tsDocState);
				consultDocForm.setTsDocResolution(tsDocResolution);	
	    		model.addAttribute("msStatutes", exciseLawService.listMsStatutes(null));
				model.addAttribute("organizationList", exciseLawService.listOrganization(null));	
				model.addAttribute("lawyerList", exciseLawService.getLawyerList(orgId));	
				model.addAttribute("professionalList", exciseLawService.getProfessionalList());	
				model.addAttribute("tsDocAttachList", exciseLawService.findTsDocAttachListByDocId(Long.parseLong(docId)));	
				model.addAttribute("vDocHistoryList", exciseLawService.findVDocHistory(Long.parseLong(docId)));
				model.addAttribute("docCommentList", exciseLawService.findDocCommentList(tmConsultDoc.getDocId()));	
				model.addAttribute("tsDocResolutionHistoryList", exciseLawService.getTsDocResolutionHistory(Long.parseLong(docId)));	
				forword="management/consultDocLockDetail";
			}
		}
//		setActionList(consultDocForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID8));
		consultDocForm.setAction(action);
		model.addAttribute("statusList", statusList);
		model.addAttribute("securityLevelList", securityLevelList);
		model.addAttribute("departmentTypeList", departmentTypeList);
		model.addAttribute("internalOfficeList", exciseLawService.getInternalOfficeList());
		model.addAttribute("externalOfficeList", exciseLawService.getExternalOfficeList());
		model.addAttribute("consultDocForm", consultDocForm); 
		return forword;		 
	}
	
	@RequestMapping(value="/management/consultDocLock",method = RequestMethod.POST)
	public String processconsultDocAnswer(ServletRequest request, ServletResponse response, 
				@RequestParam(value="action",required = false) String action,
				@ModelAttribute("consultDocForm") ConsultDocForm consultDocForm,
				BindingResult result,
				Model model, SessionStatus status) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		userInfo = (TmUserinfo) session.getAttribute("userInfo");
		String forword  ="management/consultDocLockList";
		String orgId = "12344";
		TmConsultDoc tmConsultDoc = consultDocForm.getTmConsultDoc();
		DateTime nowTh = new DateTime(new Date().getTime());

		if(consultDocForm.getDocRefDate()!=null && consultDocForm.getDocRefDate().length()>0){
			tmConsultDoc.setDocRefDate(DateUtils.getDateByStringDateTh(consultDocForm.getDocRefDate()));
		}
		if(consultDocForm.getOfficeRecDate()!=null && consultDocForm.getOfficeRecDate().length()>0){
			tmConsultDoc.setOfficeRecDate(DateUtils.getDateByStringDateTh(consultDocForm.getOfficeRecDate()));
		}
		if(consultDocForm.getDocDate()!=null && consultDocForm.getDocDate().length()>0){
			tmConsultDoc.setDocDate(DateUtils.getDateByStringDateTh(consultDocForm.getDocDate()));
		}
		if(consultDocForm.getBureauRecDate()!=null && consultDocForm.getBureauRecDate().length()>0){
			tmConsultDoc.setBureauRecDate(DateUtils.getDateByStringDateTh(consultDocForm.getBureauRecDate()));
		}
		if(consultDocForm.getSectionRecDate()!=null && consultDocForm.getSectionRecDate().length()>0){
			tmConsultDoc.setSectionRecDate(DateUtils.getDateByStringDateTh(consultDocForm.getSectionRecDate()));
		}
		if(consultDocForm.getDocSendDate()!=null){
			tmConsultDoc.setDocSendDate(DateUtils.getDateByStringDateTh(consultDocForm.getDocSendDate()));
		}
		if(consultDocForm.getBureauSendDate()!=null){
			tmConsultDoc.setBureauSendDate(DateUtils.getDateByStringDateTh(consultDocForm.getBureauSendDate()));
		}
		if(consultDocForm.getSectionSendDate()!=null){
			tmConsultDoc.setSectionSendDate(DateUtils.getDateByStringDateTh(consultDocForm.getSectionSendDate()));
		}
		if(consultDocForm.getCreatedDate()!=null && consultDocForm.getCreatedDate().length()>0){
			tmConsultDoc.setCreatedDate(DateUtils.getDateByStringDateTh(consultDocForm.getCreatedDate()));
		}

		if(action.equals("doLock")){	
			logger.info( "ConsultDocController>>consultDocAnswer.POST doEdit "); 
			tmConsultDoc.setLockStatus(BigDecimal.valueOf(1));
			tmConsultDoc.setUpdatedBy(userInfo.getUserId());
			tmConsultDoc.setUpdatedDate(nowTh.toDate());
			exciseLawService.updateTmConsultDoc(tmConsultDoc);	

    		model.addAttribute("msStatutes", exciseLawService.listMsStatutes(null));
			model.addAttribute("organizationList", exciseLawService.listOrganization(null));
			model.addAttribute("lawyerList", exciseLawService.getLawyerList(orgId));	
			model.addAttribute("professionalList", exciseLawService.getProfessionalList());	
			model.addAttribute("tsDocAttachList", exciseLawService.findTsDocAttachListByDocId(tmConsultDoc.getDocId()));
			model.addAttribute("vDocHistoryList", exciseLawService.findVDocHistory(tmConsultDoc.getDocId()));	
			model.addAttribute("docCommentList", exciseLawService.findDocCommentList(tmConsultDoc.getDocId()));			
			model.addAttribute("tsDocResolutionHistoryList", exciseLawService.getTsDocResolutionHistory(tmConsultDoc.getDocId()));
			consultDocForm.setIsSusses("1"); 
			forword="management/consultDocLockDetail";
		}
//		setActionList(consultDocForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID8));
		model.addAttribute("statusList", statusList);
		model.addAttribute("securityLevelList", securityLevelList);
		model.addAttribute("departmentTypeList", departmentTypeList);
		model.addAttribute("internalOfficeList", exciseLawService.getInternalOfficeList());
		model.addAttribute("externalOfficeList", exciseLawService.getExternalOfficeList());
		model.addAttribute("consultDocForm", consultDocForm); 
		return forword;		 
	}	

	@RequestMapping("/management/masterWord")
	public String masterWord(ServletRequest request, ServletResponse response,
				@RequestParam(value="action",required = false) String action,
				@RequestParam(value="masterWordId",required = false) String masterWordId,
				Model model) {
		ManagementForm managementForm = new ManagementForm();
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();

		String forword  ="management/masterWordList";
		if(action!=null){
			if(action.equals("addMasterWord")){
				logger.info( "ManagementController>>thesaurus addMasterWord " ); 
				managementForm = new ManagementForm();
				forword="management/masterWordDetail";
			}else if(action.equals("editMasterWord")){
				logger.info( "ManagementController>>thesaurus editMasterWord " ); 
				TsMasterWord tsMasterWord = exciseLawService.findTsMasterWordBymasterWordId(Long.valueOf(masterWordId));
				if(tsMasterWord.getCreatedDate()!=null){
					managementForm.setCreatedDate(DateUtils.getStringDateByDateTh(tsMasterWord.getCreatedDate().getTime()));
				}	
				managementForm.setTsMasterWord(tsMasterWord);
				forword="management/masterWordDetail";
			}
		} 
		managementForm.setAction(action);
		model.addAttribute("managementForm", managementForm); 
		return forword;		 
	}
	
	@RequestMapping(value="/management/masterWord",method = RequestMethod.POST)
	public String processMsterWord(ServletRequest request, ServletResponse response, 
				@RequestParam(value="action",required = false) String action,
				@ModelAttribute("managementForm") ManagementForm managementForm,
				BindingResult result,
				Model model, SessionStatus status) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		TmUserinfo userInfo = (TmUserinfo)session.getAttribute("userInfo");
		String forword  ="/management/masterWordList";
		TsMasterWord tsMasterWord = managementForm.getTsMasterWord();
		DateTime nowTh = new DateTime(new Date().getTime()).withChronology(BuddhistChronology.getInstance());
		
		if(action.equals("doSaveMasterWord")){
			logger.info( "ManagementController>>thesaurus doSaveMasterWord " );
			tsMasterWord.setCreatedBy(userInfo.getUserId());
			tsMasterWord.setCreatedDate(nowTh.toDate());
			tsMasterWord.setUpdatedBy(userInfo.getUserId());
			tsMasterWord.setUpdatedDate(nowTh.toDate());
			exciseLawService.saveTsMasterWord(tsMasterWord);
    		managementForm.setIsSusses("1");
			forword="management/masterWordDetail";
		}else if(action.equals("doEditMasterWord")){		
			logger.info( "ManagementController>>thesaurus doEditMasterWord " );			
			tsMasterWord.setUpdatedBy(userInfo.getUserId());
			tsMasterWord.setUpdatedDate(nowTh.toDate());		
			exciseLawService.updateTsMasterWord(tsMasterWord);
    		managementForm.setIsSusses("1");
			forword="management/masterWordDetail";
		}else if(action.equals("doDeleteMasterWord")){		
			logger.info( "ManagementController>>thesaurus tsMasterWord " );			
			exciseLawService.deleteTsMasterWord(tsMasterWord);//TODO
			managementForm = new ManagementForm();
			managementForm.setAction(action);
    		managementForm.setIsSusses("1");
			forword="/management/masterWordDetail";
		}
		model.addAttribute("managementForm",managementForm);
		return forword;		 
	}

	@RequestMapping("/management/thesaurus")
	public String thesaurus(ServletRequest request, ServletResponse response,
				@RequestParam(value="action",required = false) String action,
				@RequestParam(value="masterWordId",required = false) String masterWordId,
				@RequestParam(value="thesaurusId",required = false) String thesaurusId,
				Model model) {
		ManagementForm managementForm = new ManagementForm();
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();

		String forword  ="management/thesaurusList";
		if(action!=null){
			if(action.equals("addThesaurus")){
				logger.info( "ManagementController>>thesaurus addThesaurus " ); 
				managementForm = new ManagementForm();
				TsThesaurus tsThesaurus = new TsThesaurus();
				tsThesaurus.setMasterWordId(new BigDecimal(masterWordId));
				managementForm.setTsThesaurus(tsThesaurus);
				forword="management/thesaurusDetail";
			}else if(action.equals("editThesaurus")){
				logger.info( "ManagementController>>thesaurus editThesaurus " ); 
				TsThesaurus tsThesaurus = exciseLawService.findTsThesaurusByThesaurusId(Long.valueOf(thesaurusId));
				if(tsThesaurus.getCreatedDate()!=null){
					managementForm.setCreatedDate(DateUtils.getStringDateByDateTh(tsThesaurus.getCreatedDate().getTime()));
				}	
				managementForm.setTsThesaurus(tsThesaurus);
				forword="management/thesaurusDetail";
			}
		} 
		TsMasterWord tsMasterWord = exciseLawService.findTsMasterWordBymasterWordId(Long.valueOf(masterWordId));
		managementForm.setTsMasterWord(tsMasterWord);
		managementForm.setAction(action);
		model.addAttribute("managementForm", managementForm); 
		return forword;		 
	}
	
	@RequestMapping(value="/management/thesaurus",method = RequestMethod.POST)
	public String processThesaurus(ServletRequest request, ServletResponse response, 
				@RequestParam(value="action",required = false) String action,
				@ModelAttribute("managementForm") ManagementForm managementForm,
				BindingResult result,
				Model model, SessionStatus status) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		TmUserinfo userInfo = (TmUserinfo)session.getAttribute("userInfo");
		String forword  ="/management/thesaurusDList";
		TsThesaurus tsThesaurus = managementForm.getTsThesaurus();
		DateTime nowTh = new DateTime(new Date().getTime()).withChronology(BuddhistChronology.getInstance());
		
		if(action.equals("doBackMasterWord")){
			logger.info( "ManagementController>>thesaurus doBack " ); 
			forword="management/masterWordList";
		}else if(action.equals("doSaveThesaurus")){
			logger.info( "NewsController>>thesaurus doSaveThesaurus " );			
			tsThesaurus.setCreatedBy(userInfo.getUserId());
			tsThesaurus.setCreatedDate(nowTh.toDate());
			tsThesaurus.setUpdatedBy(userInfo.getUserId());
			tsThesaurus.setUpdatedDate(nowTh.toDate());
			exciseLawService.saveTsThesaurus(tsThesaurus);
    		managementForm.setIsSusses("1");
			forword="management/thesaurusDetail";
		}else if(action.equals("doEditThesaurus")){		
			logger.info( "ManagementController>>thesaurus doEditThesaurus " );			
			tsThesaurus.setUpdatedBy(userInfo.getUserId());
			tsThesaurus.setUpdatedDate(nowTh.toDate());		
			exciseLawService.updateTsThesaurus(tsThesaurus);
    		managementForm.setIsSusses("1");
			forword="management/thesaurusDetail";
		}else if(action.equals("doDeleteThesaurus")){		
			logger.info( "ManagementController>>thesaurus doDeleteThesaurus " );			
			exciseLawService.deleteTsThesaurus(tsThesaurus);
			tsThesaurus.setThesaurusId(0);
			tsThesaurus.setMasterWordThesaurus("");
			managementForm.setTsThesaurus(tsThesaurus);
			managementForm.setAction(action);
    		managementForm.setIsSusses("1");
			forword="/management/thesaurusList";
		}
		model.addAttribute("managementForm",managementForm);
		return forword;		 
	}
	
//	private void setActionList(ConsultDocForm consultDocForm, String actionList){
//		if(actionList.indexOf("ADD") != -1){
//			consultDocForm.setAdd("true");
//		}
//		if(actionList.indexOf("EDIT") != -1){
//			consultDocForm.setEdit("true");
//		}
//		if(actionList.indexOf("DELETE") != -1){
//			consultDocForm.setDelete("true");
//		}
//		if(actionList.indexOf("VIEW") != -1){
//			consultDocForm.setView("true");
//		}		
//	}
}
