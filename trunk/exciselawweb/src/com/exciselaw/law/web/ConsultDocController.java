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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import com.exciselaw.law.domain.ConsultDocFilter;
import com.exciselaw.law.domain.Display;
import com.exciselaw.law.domain.TmConsultDoc;
import com.exciselaw.law.domain.TmUserinfo;
import com.exciselaw.law.domain.TsDocAttach;
import com.exciselaw.law.domain.TsDocResolution;
import com.exciselaw.law.domain.TsDocState;
import com.exciselaw.law.domain.TsDocStatue;
import com.exciselaw.law.domain.VConsultDoc;
import com.exciselaw.law.domain.VConsultDocList;
import com.exciselaw.law.form.ConsultDocForm;
import com.exciselaw.law.service.ExciseLawService;
import com.exciselaw.law.utils.DateUtils;
import com.exciselaw.law.utils.Paging;

@Controller
@SessionAttributes({"consultDocForm"})
public class ConsultDocController {
	private final ExciseLawService exciseLawService;
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ConsultDocController.class);
	private static ResourceBundle resources = ResourceBundle.getBundle( "file" , Locale.ENGLISH );
	private final String INTERNAL_OFFICE = "I";
	private final String EXTERNAL_OFFICE = "E";
	private final String STATUS_INACTIVE = "I";
	private final String SCREEN_ID1 = "1";
	private final String SCREEN_ID2 = "2";
	private final String SCREEN_ID3 = "3";
	private final String SCREEN_ID4 = "4";
	private final String SCREEN_ID5 = "5";
//	private final String SCREEN_ID6 = "6";
//	private final String SCREEN_ID7 = "7";
	private final String SCREEN_ID8 = "8";
//	private final long FILESIZE = 5000000;
	private final long FLOW_STEP1 = 1;
	private final long FLOW_STEP2 = 2;
	private final long FLOW_STEP3 = 3;
	private final long FLOW_STEP4 = 4;
	private final long FLOW_STEP5 = 5;
	private final long FLOW_STEP6 = 6;
	private final long FLOW_STEP7 = 7;
	private final long FLOW_STEP8 = 8;
	private final long FLOW_STEP9 = 9;
	private final long FLOW_STEP10 = 10;
//	private final long FLOW_STEP11 = 11;
	private final long STATUS_AGREE = 1;
	private final long STATUS_NOTAGREE = 2;
	private final long STATUS_CALLBACK = 3;
	private final long STATUS_SENDBACK = 5;
	private final long STATUS_REJECT = 7;
	
	private TmUserinfo userInfo = null;
	
	private final String ACTION_ADD = "ADD";
	private final String ACTION_EDIT = "EDIT";
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
	public ConsultDocController(ExciseLawService exciseLawService) {
		this.exciseLawService = exciseLawService;
	}
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
//		logger.info( "ConsultDocController>>setAllowedFields" );
		dataBinder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
		dataBinder.setDisallowedFields("id");
	}

	@RequestMapping("/consult_doc/consultDocNew")
	public String consultDocNew(ServletRequest request, ServletResponse response,
				@RequestParam(value="action",required = false) String action,
				@RequestParam(value="docId",required = false) String docId, Model model) {
		ConsultDocForm consultDocForm = new ConsultDocForm();
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		userInfo = (TmUserinfo) session.getAttribute("userInfo");
		String forword  ="consult_doc/consultDocNewList";
		if(action!=null){
			if(action.equals("add")){			
				logger.info( "ConsultDocController>>consultDocNew.POST add " );	
				consultDocForm = new ConsultDocForm();
				forword="consult_doc/consultDocNewDetail";
			}else if(action.equals("edit")){
				logger.info( "ConsultDocController>>consultDocNew edit " ); 
				List list = exciseLawService.findVConsultDocByDocId(BigDecimal.valueOf(Long.valueOf((docId))));
				TmConsultDoc tmConsultDoc = (TmConsultDoc) list.get(0);
				TsDocState tsDocState = (TsDocState) list.get(1);	
				if(tsDocState.getFlowId()==null){
					VConsultDocList docList = exciseLawService.findVConsultDocListByDocId(Long.valueOf(docId));
					tsDocState.setFlowId(docList.getFlowId());
				}

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
				if(INTERNAL_OFFICE.equals(tmConsultDoc.getDocType())){
					consultDocForm.setInternalOfficeId(tmConsultDoc.getConsultOrg());
				}else if(EXTERNAL_OFFICE.equals(tmConsultDoc.getDocType())){
					consultDocForm.setExternalOfficeId(tmConsultDoc.getConsultOrg());
				}
				model.addAttribute("tsDocAttachList", exciseLawService.findTsDocAttachListByDocId(Long.parseLong(docId)));	
				consultDocForm.setTsDocState(tsDocState);
				consultDocForm.setTmConsultDoc(tmConsultDoc);
				forword="consult_doc/consultDocNewDetail";
			}
		} 
		setActionList(consultDocForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID1));
		consultDocForm.setAction(action);
		model.addAttribute("statusList", statusList);
		model.addAttribute("securityLevelList", securityLevelList);
		model.addAttribute("departmentTypeList", departmentTypeList);
		model.addAttribute("internalOfficeList", exciseLawService.getInternalOfficeList());
		model.addAttribute("externalOfficeList", exciseLawService.getExternalOfficeList());
		model.addAttribute("consultDocForm", consultDocForm); 
		return forword;		 
	}
	
	@RequestMapping(value="/consult_doc/consultDocNew",method = RequestMethod.POST)
	public String processConsultDocNew(ServletRequest request, ServletResponse response, 
				@RequestParam(value="action",required = false) String action,
				@ModelAttribute("consultDocForm") ConsultDocForm consultDocForm,
				BindingResult result,
				Model model, SessionStatus status) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		userInfo = (TmUserinfo) session.getAttribute("userInfo");
		String forword  ="consult_doc/consultDocNewList";
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
		
		if(action.equals("doSave")){
			logger.info( "ConsultDocController>>consultDocNew.POST doSave " );
			
			tmConsultDoc.setCreatedBy(userInfo.getUserId());
			tmConsultDoc.setUpdatedBy(userInfo.getUserId());
			tmConsultDoc.setCreatedDate(nowTh.toDate());
			tmConsultDoc.setUpdatedDate(nowTh.toDate());
			Long docId = exciseLawService.saveTmConsultDoc(tmConsultDoc);
			
			TsDocState tsDocState = setTsDocState(null ,docId, FLOW_STEP1, STATUS_AGREE, userInfo.getCardNo(), userInfo.getUserId(), nowTh.toDate(), ACTION_ADD);
			exciseLawService.saveTsDocState(tsDocState);
			
			MultipartFile[] files = consultDocForm.getFileData();
			if(files != null && files.length>0){
	            for(MultipartFile file : files){
	            	if(file.getOriginalFilename() !=null && file.getSize() > 0){
		            	TsDocAttach tsDocAttach = saveTsDocAttachFile(file, docId.longValue(), userInfo.getUserId(), nowTh.toDate());
						exciseLawService.saveTsDocAttach(tsDocAttach);
	            	}
	            }	
			}
			model.addAttribute("tsDocAttachList", exciseLawService.findTsDocAttachListByDocId(tmConsultDoc.getDocId()));
			consultDocForm.setIsSusses("1");
			forword="consult_doc/consultDocNewDetail";
		}else if(action.equals("doEdit")){		
			logger.info( "ConsultDocController>>consultDocNew.POST doEdit " );
			
			tmConsultDoc.setUpdatedBy(userInfo.getUserId());
			tmConsultDoc.setUpdatedDate(nowTh.toDate());
			exciseLawService.updateTmConsultDoc(tmConsultDoc);
			
			String [] delAttachFile = consultDocForm.getDeleteFileBoxes();
			if(delAttachFile!=null && delAttachFile.length>0){
				TsDocAttach tsDocAttach = null;
				for(String attachId : delAttachFile){
					tsDocAttach = new TsDocAttach();
					tsDocAttach.setAttachId(Long.valueOf(attachId));
					exciseLawService.deleteDocAttach(tsDocAttach);
				}
			}

			MultipartFile[] files = consultDocForm.getFileData();
			if(files != null && files.length>0){
	            for(MultipartFile file : files){
	            	if(file.getOriginalFilename() != null && file.getSize() > 0){
		            	TsDocAttach tsDocAttach = saveTsDocAttachFile(file, tmConsultDoc.getDocId() , userInfo.getUserId(), nowTh.toDate());
						exciseLawService.saveTsDocAttach(tsDocAttach);
	            	}
	            }
			}
			
			model.addAttribute("tsDocAttachList", exciseLawService.findTsDocAttachListByDocId(tmConsultDoc.getDocId()));
			consultDocForm.setIsSusses("1"); 
			forword="consult_doc/consultDocNewDetail";
		}else if(action.equals("doSend")){			
			logger.info( "ConsultDocController>>consultDocNew.POST doSend " );
			TmConsultDoc consultDoc = exciseLawService.findTmConsultDocById(tmConsultDoc.getDocId());
			
			if(consultDoc != null){
				
				String [] delAttachFile = consultDocForm.getDeleteFileBoxes();
				if(delAttachFile!=null && delAttachFile.length>0){
					TsDocAttach tsDocAttach = null;
					for(String attachId : delAttachFile){
						tsDocAttach = new TsDocAttach();
						tsDocAttach.setAttachId(Long.valueOf(attachId));
						exciseLawService.deleteDocAttach(tsDocAttach);
					}
				}

				MultipartFile[] files = consultDocForm.getFileData();
				if(files != null && files.length>0){
		            for(MultipartFile file : files){
		            	if(file.getOriginalFilename() != null && file.getSize() > 0){
			            	TsDocAttach tsDocAttach = saveTsDocAttachFile(file, consultDoc.getDocId() , userInfo.getUserId(), nowTh.toDate());
							exciseLawService.saveTsDocAttach(tsDocAttach);
		            	}
		            }
				}
				TsDocState tsDocState = setTsDocState(null, tmConsultDoc.getDocId(), FLOW_STEP2, STATUS_AGREE, userInfo.getCardNo(), userInfo.getUserId(), nowTh.toDate(), ACTION_ADD);
				tsDocState.setPersonIncharge(userInfo.getCardNo());
				exciseLawService.saveTsDocState(tsDocState);
			}else{
				tmConsultDoc.setCreatedBy(userInfo.getUserId());
				tmConsultDoc.setUpdatedBy(userInfo.getUserId());
				tmConsultDoc.setCreatedDate(nowTh.toDate());
				tmConsultDoc.setUpdatedDate(nowTh.toDate());
				Long docId = exciseLawService.saveTmConsultDoc(tmConsultDoc);

				MultipartFile[] files = consultDocForm.getFileData();
				if(files != null && files.length>0){
		            for(MultipartFile file : files){
		            	if(file.getOriginalFilename() != null && file.getSize() > 0){
			            	TsDocAttach tsDocAttach = saveTsDocAttachFile(file, docId.longValue() , userInfo.getUserId(), nowTh.toDate());
							exciseLawService.saveTsDocAttach(tsDocAttach);
		            	}
		            }
				}

				TsDocState tsDocState = setTsDocState(null ,docId, FLOW_STEP1, STATUS_AGREE, userInfo.getCardNo(), userInfo.getUserId(), nowTh.toDate(), ACTION_ADD);
				exciseLawService.saveTsDocState(tsDocState);
				
				tsDocState = setTsDocState(null, tmConsultDoc.getDocId(), FLOW_STEP2, STATUS_AGREE, userInfo.getCardNo(), userInfo.getUserId(), nowTh.toDate(), ACTION_ADD);
				exciseLawService.saveTsDocState(tsDocState);
			}
			model.addAttribute("tsDocAttachList", exciseLawService.findTsDocAttachListByDocId(tmConsultDoc.getDocId()));
			consultDocForm.setIsSusses("1");
			consultDocForm.getTsDocState().setFlowId(BigDecimal.valueOf(FLOW_STEP2));
			forword="consult_doc/consultDocNewDetail";
		}else if(action.equals("doDelete")){			
			logger.info( "ConsultDocController>>consultDocNew.POST doDelete " );
			tmConsultDoc.setStatus(STATUS_INACTIVE);
			exciseLawService.updateTmConsultDoc(tmConsultDoc);
			consultDocForm = new ConsultDocForm();
			consultDocForm.setAction(action);
			consultDocForm.setIsSusses("1");
			forword="consult_doc/consultDocNewDetail";
		}else if(action.equals("doCallback")){
			logger.info( "ConsultDocController>>consultDocNew.POST doCallback " );
			TsDocState tsDocState = setTsDocState(null, tmConsultDoc.getDocId(), FLOW_STEP1, STATUS_CALLBACK, userInfo.getCardNo(), userInfo.getUserId(), nowTh.toDate(), ACTION_ADD);
			exciseLawService.saveTsDocState(tsDocState);
			model.addAttribute("tsDocAttachList", exciseLawService.findTsDocAttachListByDocId(tmConsultDoc.getDocId()));
			consultDocForm.setIsSusses("1");
			forword="consult_doc/consultDocNewDetail";
		}	
		setActionList(consultDocForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID1));
		model.addAttribute("statusList", statusList);
		model.addAttribute("securityLevelList", securityLevelList);
		model.addAttribute("departmentTypeList", departmentTypeList);
		model.addAttribute("internalOfficeList", exciseLawService.getInternalOfficeList());
		model.addAttribute("externalOfficeList", exciseLawService.getExternalOfficeList());
		model.addAttribute("consultDocForm",consultDocForm);
		return forword;		 
	}	
	
	@RequestMapping("/consult_doc/consultDocLaw")
	public String consultDocLaw(ServletRequest request, ServletResponse response, 
				@RequestParam(value="action",required = false) String action,
				@RequestParam(value="docId",required = false) String docId, Model model) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		userInfo = (TmUserinfo) session.getAttribute("userInfo");
		ConsultDocForm consultDocForm = new ConsultDocForm();
		String forword  ="consult_doc/consultDocLawList";
		if(action!=null){
			if(action.equals("edit")){
				logger.info( "ConsultDocController>>consultDocLaw edit " ); 
				List list = exciseLawService.findVConsultDocByDocId(BigDecimal.valueOf(Long.valueOf(docId)));
				TmConsultDoc tmConsultDoc = (TmConsultDoc) list.get(0);
				TsDocState tsDocState = (TsDocState) list.get(1);	
				
				if(tsDocState.getFlowId()==null){
					VConsultDocList docList = exciseLawService.findVConsultDocListByDocId(Long.valueOf(docId));
					tsDocState.setFlowId(docList.getFlowId());
				}
				tsDocState.setDocComment("");

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
				consultDocForm.setTmConsultDoc(tmConsultDoc);
				consultDocForm.setTsDocState(tsDocState);
				model.addAttribute("organizationList", exciseLawService.listOrganization(null));	
				model.addAttribute("tsDocAttachList", exciseLawService.findTsDocAttachListByDocId(Long.parseLong(docId)));
				model.addAttribute("vDocHistoryList", exciseLawService.findVDocHistory(Long.parseLong(docId)));

				VConsultDocList doc = exciseLawService.findVConsultDocListByDocId(tmConsultDoc.getDocId());
				if(doc.getFlowId().intValue() >= FLOW_STEP7){
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
					TsDocResolution tsDocResolution = exciseLawService.findLastTsDocResolution(Long.parseLong(docId));
		    		model.addAttribute("msStatutes", exciseLawService.listMsStatutes(null));
		    		String orgId = "12344";//TODO		
					model.addAttribute("lawyerList", exciseLawService.getLawyerList(orgId));			
					model.addAttribute("professionalList", exciseLawService.getProfessionalList());	
					model.addAttribute("docCommentList", exciseLawService.findDocCommentList(tmConsultDoc.getDocId()));	
					model.addAttribute("tsDocResolutionHistoryList", exciseLawService.getTsDocResolutionHistory(Long.parseLong(docId)));
					consultDocForm.setTsDocResolution(tsDocResolution);				
		    		model.addAttribute("msStatutes", exciseLawService.listMsStatutes(null));
					model.addAttribute("organizationList", exciseLawService.listOrganization(null));
					model.addAttribute("tsDocAttachList", exciseLawService.findTsDocAttachListByDocId(tmConsultDoc.getDocId()));
					model.addAttribute("vDocHistoryList", exciseLawService.findVDocHistory(tmConsultDoc.getDocId()));	
					model.addAttribute("docCommentList", exciseLawService.findDocCommentList(tmConsultDoc.getDocId()));			
				}
				forword="consult_doc/consultDocLawDetail";
			}
		} 
		setActionList(consultDocForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID2));
		consultDocForm.setAction(action);
		model.addAttribute("statusList", statusList);
		model.addAttribute("securityLevelList", securityLevelList);	
		model.addAttribute("departmentTypeList", departmentTypeList);
		model.addAttribute("internalOfficeList", exciseLawService.getInternalOfficeList());
		model.addAttribute("externalOfficeList", exciseLawService.getExternalOfficeList());
		model.addAttribute("consultDocForm", consultDocForm); 
		return forword;		 
	}
	
	@RequestMapping(value="/consult_doc/consultDocLaw",method = RequestMethod.POST)
	public String processConsultDocLaw(ServletRequest request, ServletResponse response, 
				@RequestParam(value="action",required = false) String action,
				@ModelAttribute("consultDocForm") ConsultDocForm consultDocForm,
				BindingResult result,
				Model model, SessionStatus status) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		userInfo = (TmUserinfo) session.getAttribute("userInfo");
		String forword  ="consult_doc/consultDocLawList";
		TmConsultDoc tmConsultDoc = consultDocForm.getTmConsultDoc();
		TsDocState tsDocState = consultDocForm.getTsDocState();
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
		if(action.equals("doEdit")){	
			logger.info( "ConsultDocController>>consultDocLaw.POST doEdit "); 
			
			tmConsultDoc.setUpdatedBy(userInfo.getUserId());
			tmConsultDoc.setUpdatedDate(nowTh.toDate());
			exciseLawService.updateTmConsultDoc(tmConsultDoc);		
			
			VConsultDoc doc = exciseLawService.findVConsultDocByDocId(tmConsultDoc.getDocId());
			VConsultDocList docList = exciseLawService.findVConsultDocListByDocId(tmConsultDoc.getDocId());
			
			BigDecimal flowId = doc.getFlowId();
			if(flowId == null){
				flowId = docList.getFlowId();
			}
			if(flowId.intValue() == FLOW_STEP2 || flowId.intValue() == FLOW_STEP7 || flowId.intValue() == FLOW_STEP9){
				if(doc.getStateId() == docList.getStateId()){
					tsDocState = setTsDocState(tsDocState, tmConsultDoc.getDocId(), 0, 0, userInfo.getCardNo(), userInfo.getUserId(), nowTh.toDate(), ACTION_ADD);
					exciseLawService.saveTsDocState(tsDocState);
				}else{
					tsDocState = setTsDocState(tsDocState, tmConsultDoc.getDocId(), 0, 0, userInfo.getCardNo(), userInfo.getUserId(), nowTh.toDate(), ACTION_EDIT);
					exciseLawService.updateTsDocState(tsDocState);
				}
			}
			
			String [] delAttachFile = consultDocForm.getDeleteFileBoxes();
			if(delAttachFile!=null && delAttachFile.length>0){
				TsDocAttach tsDocAttach = null;
				for(String attachId:delAttachFile){
					tsDocAttach = new TsDocAttach();
					tsDocAttach.setAttachId(Long.valueOf(attachId));
					tsDocAttach.setStatus(STATUS_INACTIVE);
					exciseLawService.saveTsDocAttach(tsDocAttach);
				}
			}

			MultipartFile[] files = consultDocForm.getFileData();
			if(files != null && files.length>0){
	            for(MultipartFile file : files){
	            	if(file.getOriginalFilename() != null && file.getSize() > 0){
			            	TsDocAttach tsDocAttach = saveTsDocAttachFile(file, tmConsultDoc.getDocId() , userInfo.getUserId(), nowTh.toDate());
							exciseLawService.saveTsDocAttach(tsDocAttach);
	            	}
	            }
			}

			model.addAttribute("organizationList", exciseLawService.listOrganization(null));	
			model.addAttribute("tsDocAttachList", exciseLawService.findTsDocAttachListByDocId(tmConsultDoc.getDocId()));
			model.addAttribute("vDocHistoryList", exciseLawService.findVDocHistory(tmConsultDoc.getDocId()));	
			consultDocForm.getTsDocState().setFlowId(flowId);
			consultDocForm.setIsSusses("1"); 
			forword="consult_doc/consultDocLawDetail";
		}else if(action.equals("doSendSection")){				

			tmConsultDoc.setUpdatedBy(userInfo.getUserId());
			tmConsultDoc.setUpdatedDate(nowTh.toDate());
			exciseLawService.updateTmConsultDoc(tmConsultDoc);	
			
			VConsultDoc doc = exciseLawService.findVConsultDocByDocId(tmConsultDoc.getDocId());
			VConsultDocList docList = exciseLawService.findVConsultDocListByDocId(tmConsultDoc.getDocId());
			
			BigDecimal flowId = doc.getFlowId();
			if(flowId == null){
				flowId = docList.getFlowId();
			}
			
			if(flowId.intValue() == FLOW_STEP2){
				if(doc.getStateId() == docList.getStateId()){
					tsDocState = setTsDocState(tsDocState, tmConsultDoc.getDocId(), FLOW_STEP3, STATUS_AGREE, userInfo.getCardNo(), userInfo.getUserId(), nowTh.toDate(), ACTION_ADD);
					exciseLawService.saveTsDocState(tsDocState);
				}else{
					tsDocState = setTsDocState(tsDocState, tmConsultDoc.getDocId(), FLOW_STEP3, STATUS_AGREE, userInfo.getCardNo(), userInfo.getUserId(), nowTh.toDate(), ACTION_EDIT);
					exciseLawService.updateTsDocState(tsDocState);
				}
			}else if(flowId.intValue() == FLOW_STEP7){
				if(doc.getStateId() == docList.getStateId()){
					tsDocState = setTsDocState(tsDocState, tmConsultDoc.getDocId(), FLOW_STEP3, STATUS_AGREE, userInfo.getCardNo(), userInfo.getUserId(), nowTh.toDate(), ACTION_EDIT);
					exciseLawService.saveTsDocState(tsDocState);
				}else{
					tsDocState = setTsDocState(tsDocState, tmConsultDoc.getDocId(), FLOW_STEP3, STATUS_AGREE, userInfo.getCardNo(), userInfo.getUserId(), nowTh.toDate(), ACTION_EDIT);
					exciseLawService.updateTsDocState(tsDocState);
				}
			}
			
			String [] delAttachFile = consultDocForm.getDeleteFileBoxes();
			if(delAttachFile!=null && delAttachFile.length>0){
				TsDocAttach tsDocAttach = null;
				for(String attachId:delAttachFile){
					tsDocAttach = new TsDocAttach();
					tsDocAttach.setAttachId(Long.valueOf(attachId));
					tsDocAttach.setStatus(STATUS_INACTIVE);
					exciseLawService.saveTsDocAttach(tsDocAttach);
				}
			}

			MultipartFile[] files = consultDocForm.getFileData();
			if(files != null && files.length>0){
	            for(MultipartFile file : files){
	            	if(file.getOriginalFilename() != null && file.getSize() > 0){
			            	TsDocAttach tsDocAttach = saveTsDocAttachFile(file, tmConsultDoc.getDocId() , userInfo.getUserId(), nowTh.toDate());
							exciseLawService.saveTsDocAttach(tsDocAttach);
	            	}
	            }
			}

			model.addAttribute("organizationList", exciseLawService.listOrganization(null));	
			model.addAttribute("tsDocAttachList", exciseLawService.findTsDocAttachListByDocId(tmConsultDoc.getDocId()));
			model.addAttribute("vDocHistoryList", exciseLawService.findVDocHistory(tmConsultDoc.getDocId()));
			consultDocForm.setIsSusses("1");
			forword="consult_doc/consultDocLawDetail";
		}else if(action.equals("doSend")){				

			tmConsultDoc.setUpdatedBy(userInfo.getUserId());
			tmConsultDoc.setUpdatedDate(nowTh.toDate());
			exciseLawService.updateTmConsultDoc(tmConsultDoc);	
			
			VConsultDoc doc = exciseLawService.findVConsultDocByDocId(tmConsultDoc.getDocId());
			VConsultDocList docList = exciseLawService.findVConsultDocListByDocId(tmConsultDoc.getDocId());
			
			BigDecimal flowId = doc.getFlowId();
			if(flowId == null){
				flowId = docList.getFlowId();
			}
			
			if(flowId.intValue() == FLOW_STEP7){
				if(doc.getStateId() == docList.getStateId()){
					tsDocState = setTsDocState(tsDocState, tmConsultDoc.getDocId(), FLOW_STEP8, STATUS_AGREE, userInfo.getCardNo(), userInfo.getUserId(), nowTh.toDate(), ACTION_EDIT);
					exciseLawService.saveTsDocState(tsDocState);
				}else{
					tsDocState = setTsDocState(tsDocState, tmConsultDoc.getDocId(), FLOW_STEP8, STATUS_AGREE, userInfo.getCardNo(), userInfo.getUserId(), nowTh.toDate(), ACTION_EDIT);
					exciseLawService.updateTsDocState(tsDocState);
				}
			}else if(flowId.intValue() == FLOW_STEP9){
				if(doc.getStateId() == docList.getStateId()){
					tsDocState = setTsDocState(tsDocState, tmConsultDoc.getDocId(), FLOW_STEP10, STATUS_AGREE, userInfo.getCardNo(), userInfo.getUserId(), nowTh.toDate(), ACTION_ADD);
					exciseLawService.saveTsDocState(tsDocState);
				}else{
					tsDocState = setTsDocState(tsDocState, tmConsultDoc.getDocId(), FLOW_STEP10, STATUS_AGREE, userInfo.getCardNo(), userInfo.getUserId(), nowTh.toDate(), ACTION_EDIT);
					exciseLawService.updateTsDocState(tsDocState);
				}
			}
			
			String [] delAttachFile = consultDocForm.getDeleteFileBoxes();
			if(delAttachFile!=null && delAttachFile.length>0){
				TsDocAttach tsDocAttach = null;
				for(String attachId:delAttachFile){
					tsDocAttach = new TsDocAttach();
					tsDocAttach.setAttachId(Long.valueOf(attachId));
					tsDocAttach.setStatus(STATUS_INACTIVE);
					exciseLawService.saveTsDocAttach(tsDocAttach);
				}
			}

			MultipartFile[] files = consultDocForm.getFileData();
			if(files != null && files.length>0){
	            for(MultipartFile file : files){
	            	if(file.getOriginalFilename() != null && file.getSize() > 0){
			            	TsDocAttach tsDocAttach = saveTsDocAttachFile(file, tmConsultDoc.getDocId() , userInfo.getUserId(), nowTh.toDate());
							exciseLawService.saveTsDocAttach(tsDocAttach);
	            	}
	            }
			}

			model.addAttribute("organizationList", exciseLawService.listOrganization(null));	
			model.addAttribute("tsDocAttachList", exciseLawService.findTsDocAttachListByDocId(tmConsultDoc.getDocId()));
			model.addAttribute("vDocHistoryList", exciseLawService.findVDocHistory(tmConsultDoc.getDocId()));	
			consultDocForm.setIsSusses("1");
			forword="consult_doc/consultDocLawDetail";
		}else if(action.equals("doCallback")){
			logger.info( "ConsultDocController>>consultDocLaw.POST doCallback " );
			tsDocState = setTsDocState(null, tmConsultDoc.getDocId(), FLOW_STEP2, STATUS_CALLBACK, userInfo.getCardNo(), userInfo.getUserId(), nowTh.toDate(), ACTION_ADD);
			exciseLawService.saveTsDocState(tsDocState);
			model.addAttribute("organizationList", exciseLawService.listOrganization(null));	
			model.addAttribute("tsDocAttachList", exciseLawService.findTsDocAttachListByDocId(tmConsultDoc.getDocId()));
			model.addAttribute("vDocHistoryList", exciseLawService.findVDocHistory(tmConsultDoc.getDocId()));	
			consultDocForm.setIsSusses("1");
			forword="consult_doc/consultDocLawDetail";
		}
		setActionList(consultDocForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID2));
		model.addAttribute("statusList", statusList);
		model.addAttribute("securityLevelList", securityLevelList);
		model.addAttribute("departmentTypeList", departmentTypeList);
		model.addAttribute("internalOfficeList", exciseLawService.getInternalOfficeList());
		model.addAttribute("externalOfficeList", exciseLawService.getExternalOfficeList());
		model.addAttribute("consultDocForm", consultDocForm); 
		return forword;		 
	}	

	@RequestMapping("/consult_doc/consultDocSection")
	public String consultDocSection(ServletRequest request, ServletResponse response, 
				@RequestParam(value="action",required = false) String action,
				@RequestParam(value="docId",required = false) String docId, Model model) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		userInfo = (TmUserinfo) session.getAttribute("userInfo");
		ConsultDocForm consultDocForm = new ConsultDocForm();
		String orgId = "12344";//TODO
		String forword  ="consult_doc/consultDocSectionList";
		if(action!=null){
			if(action.equals("edit")){
				logger.info( "ConsultDocController>>consultDocSection edit " ); 
				List list = exciseLawService.findVConsultDocByDocId(BigDecimal.valueOf(Long.valueOf(docId)));
				TmConsultDoc tmConsultDoc = (TmConsultDoc) list.get(0);
				TsDocState tsDocState = (TsDocState) list.get(1);	
				if(tsDocState.getFlowId()==null){
					VConsultDocList docList = exciseLawService.findVConsultDocListByDocId(Long.valueOf(docId));
					tsDocState.setFlowId(docList.getFlowId());
				}
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
				consultDocForm.setTmConsultDoc(tmConsultDoc);
				consultDocForm.setTsDocState(tsDocState);
				consultDocForm.setTsDocResolution(tsDocResolution);
				model.addAttribute("securityLevelList", securityLevelList);	
				model.addAttribute("organizationList", exciseLawService.listOrganization(null));	
				model.addAttribute("lawyerList", exciseLawService.getLawyerList(orgId));
				model.addAttribute("tsDocAttachList", exciseLawService.findTsDocAttachListByDocId(Long.parseLong(docId)));	
				model.addAttribute("vDocHistoryList", exciseLawService.findVDocHistory(Long.parseLong(docId)));
				model.addAttribute("docCommentList", exciseLawService.findDocCommentList(tmConsultDoc.getDocId()));	

				VConsultDocList doc = exciseLawService.findVConsultDocListByDocId(tmConsultDoc.getDocId());
				if(doc.getFlowId().longValue() >= FLOW_STEP5){

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
					tsDocState.setFlowId(doc.getFlowId());
		    		model.addAttribute("msStatutes", exciseLawService.listMsStatutes(null));					
					model.addAttribute("professionalList", exciseLawService.getProfessionalList());	
					model.addAttribute("tsDocResolutionHistoryList", exciseLawService.getTsDocResolutionHistory(Long.parseLong(docId)));		
				}
				forword="consult_doc/consultDocSectionDetail";
			}
		}
		setActionList(consultDocForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID3));
		consultDocForm.setAction(action);
		model.addAttribute("statusList", statusList);
		model.addAttribute("securityLevelList", securityLevelList);	
		model.addAttribute("departmentTypeList", departmentTypeList);
		model.addAttribute("internalOfficeList", exciseLawService.getInternalOfficeList());
		model.addAttribute("externalOfficeList", exciseLawService.getExternalOfficeList());
		model.addAttribute("consultDocForm", consultDocForm); 
		return forword;		 
	}
	
	@RequestMapping(value="/consult_doc/consultDocSection",method = RequestMethod.POST)
	public String processConsultDocSection(ServletRequest request, ServletResponse response, 
				@RequestParam(value="action",required = false) String action,
				@ModelAttribute("consultDocForm") ConsultDocForm consultDocForm,
				BindingResult result,
				Model model, SessionStatus status) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		userInfo = (TmUserinfo) session.getAttribute("userInfo");
		String forword  ="consult_doc/consultDocSectionList";
		String orgId = "12344";//TODO
		TmConsultDoc tmConsultDoc = consultDocForm.getTmConsultDoc();
		TsDocState tsDocState = consultDocForm.getTsDocState();
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

		if(action.equals("doEdit")){	
			logger.info( "ConsultDocController>>consultDocSection.POST doEdit "); 
			
			tmConsultDoc.setUpdatedBy(userInfo.getUserId());
			tmConsultDoc.setUpdatedDate(nowTh.toDate());
			exciseLawService.updateTmConsultDoc(tmConsultDoc);					

			VConsultDoc doc = exciseLawService.findVConsultDocByDocId(tmConsultDoc.getDocId());
			VConsultDocList docList = exciseLawService.findVConsultDocListByDocId(tmConsultDoc.getDocId());
			
			BigDecimal flowId = doc.getFlowId();
			if(flowId == null){
				flowId = docList.getFlowId();
			}
			if(tsDocState.getAssignLawyer() != null && !"".equals(tsDocState.getAssignLawyer()))
				tsDocState.setAssignLawyer(tsDocState.getAssignLawyer().trim());
			if(flowId != null && (flowId.intValue() == FLOW_STEP3 ||  flowId.intValue() == FLOW_STEP5)){
				if(tsDocState.getAssignProf() == null || "".equals(tsDocState.getAssignProf())){
					tsDocState.setAssignProf("");
				}
				if(doc.getStateId() == docList.getStateId()){
					tsDocState = setTsDocState(tsDocState, tmConsultDoc.getDocId(), 0, 0, userInfo.getCardNo(), userInfo.getUserId(), nowTh.toDate(), ACTION_ADD);
					exciseLawService.saveTsDocState(tsDocState);
				}else{
					tsDocState = setTsDocState(tsDocState, tmConsultDoc.getDocId(), 0, 0, userInfo.getCardNo(), userInfo.getUserId(), nowTh.toDate(), ACTION_EDIT);
					exciseLawService.updateTsDocState(tsDocState);
				}
			}
									
			String [] delAttachFile = consultDocForm.getDeleteFileBoxes();
			if(delAttachFile!=null && delAttachFile.length>0){
				TsDocAttach tsDocAttach = null;
				for(String attachId:delAttachFile){
					tsDocAttach = new TsDocAttach();
					tsDocAttach.setAttachId(Long.valueOf(attachId));
					exciseLawService.deleteDocAttach(tsDocAttach);
				}
			}
			
			MultipartFile[] files = consultDocForm.getFileData();
			if(files != null && files.length>0){
	            for(MultipartFile file : files){
	            	if(file.getOriginalFilename() != null && file.getSize() > 0){
		            	TsDocAttach tsDocAttach = saveTsDocAttachFile(file, tmConsultDoc.getDocId() , userInfo.getUserId(), nowTh.toDate());
						exciseLawService.saveTsDocAttach(tsDocAttach);
	            	}
	            }
			}

			model.addAttribute("organizationList", exciseLawService.listOrganization(null));	
			model.addAttribute("lawyerList", exciseLawService.getLawyerList(orgId));
			model.addAttribute("tsDocAttachList", exciseLawService.findTsDocAttachListByDocId(tmConsultDoc.getDocId()));	
			model.addAttribute("vDocHistoryList", exciseLawService.findVDocHistory(tmConsultDoc.getDocId()));	
			model.addAttribute("docCommentList", exciseLawService.findDocCommentList(tmConsultDoc.getDocId()));	

			if(doc.getFlowId() != null && doc.getFlowId().longValue() >= 5){
				tsDocState.setFlowId(doc.getFlowId());			
				model.addAttribute("professionalList", exciseLawService.getProfessionalList());					
			}
			consultDocForm.setIsSusses("1"); 
			consultDocForm.getTsDocState().setFlowId(flowId);
			forword="consult_doc/consultDocSectionDetail";
		}else if(action.equals("doSend")){		
			
			tmConsultDoc.setUpdatedBy(userInfo.getUserId());
			tmConsultDoc.setUpdatedDate(nowTh.toDate());
			exciseLawService.updateTmConsultDoc(tmConsultDoc);	
			
			VConsultDoc doc = exciseLawService.findVConsultDocByDocId(tmConsultDoc.getDocId());
			VConsultDocList docList = exciseLawService.findVConsultDocListByDocId(tmConsultDoc.getDocId());
			
			BigDecimal flowId = doc.getFlowId();
			if(flowId == null){
				flowId = docList.getFlowId();
			}
			if(flowId.intValue() == FLOW_STEP3){
				if(doc.getStateId() == docList.getStateId()){
					tsDocState = setTsDocState(tsDocState, tmConsultDoc.getDocId(), FLOW_STEP4, STATUS_AGREE, userInfo.getCardNo(), userInfo.getUserId(), nowTh.toDate(), ACTION_ADD);
					exciseLawService.saveTsDocState(tsDocState);
				}else{
					tsDocState = setTsDocState(tsDocState, tmConsultDoc.getDocId(), FLOW_STEP4, STATUS_AGREE, userInfo.getCardNo(), userInfo.getUserId(), nowTh.toDate(), ACTION_EDIT);
					exciseLawService.updateTsDocState(tsDocState);
				}
			}else if(flowId.intValue() == FLOW_STEP5){
				if(doc.getStateId() == docList.getStateId()){
					tsDocState = setTsDocState(tsDocState, tmConsultDoc.getDocId(), FLOW_STEP6, STATUS_AGREE, userInfo.getCardNo(), userInfo.getUserId(), nowTh.toDate(), ACTION_ADD);
					exciseLawService.saveTsDocState(tsDocState);
				}else{
					tsDocState = setTsDocState(tsDocState, tmConsultDoc.getDocId(), FLOW_STEP6, STATUS_AGREE, userInfo.getCardNo(), userInfo.getUserId(), nowTh.toDate(), ACTION_EDIT);
					exciseLawService.updateTsDocState(tsDocState);
				}
			}			
			
			String [] delAttachFile = consultDocForm.getDeleteFileBoxes();
			if(delAttachFile!=null && delAttachFile.length>0){
				TsDocAttach tsDocAttach = null;
				for(String attachId:delAttachFile){
					tsDocAttach = new TsDocAttach();
					tsDocAttach.setAttachId(Long.valueOf(attachId));
					exciseLawService.deleteDocAttach(tsDocAttach);
				}
			}
			
			MultipartFile[] files = consultDocForm.getFileData();
			if(files != null && files.length>0){
	            for(MultipartFile file : files){
	            	if(file.getOriginalFilename() != null && file.getSize() > 0){
		            	TsDocAttach tsDocAttach = saveTsDocAttachFile(file, tmConsultDoc.getDocId() , userInfo.getUserId(), nowTh.toDate());
						exciseLawService.saveTsDocAttach(tsDocAttach);
	            	}
	            }
			}
			
			model.addAttribute("organizationList", exciseLawService.listOrganization(null));	
			model.addAttribute("lawyerList", exciseLawService.getLawyerList(orgId));			
			model.addAttribute("professionalList", exciseLawService.getProfessionalList());	
			model.addAttribute("tsDocAttachList", exciseLawService.findTsDocAttachListByDocId(tmConsultDoc.getDocId()));
			model.addAttribute("vDocHistoryList", exciseLawService.findVDocHistory(tmConsultDoc.getDocId()));	
			model.addAttribute("docCommentList", exciseLawService.findDocCommentList(tmConsultDoc.getDocId()));	
			consultDocForm.setIsSusses("1");
			forword="consult_doc/consultDocSectionDetail";
		}else if(action.equals("doReject")){	
			logger.info( "ConsultDocController>>consultDocSection.POST doReject "); 
			
			tmConsultDoc.setUpdatedBy(userInfo.getUserId());
			tmConsultDoc.setUpdatedDate(nowTh.toDate());
			exciseLawService.updateTmConsultDoc(tmConsultDoc);			
			
			VConsultDoc doc = exciseLawService.findVConsultDocByDocId(tmConsultDoc.getDocId());
			VConsultDocList docList = exciseLawService.findVConsultDocListByDocId(tmConsultDoc.getDocId());
			if(tsDocState.getAssignProf() == null || "".equals(tsDocState.getAssignProf())){
				tsDocState.setAssignProf("");
			}
			if(doc.getFlowId() != null && doc.getFlowId().intValue() == FLOW_STEP5){
				if(doc.getStateId() == docList.getStateId()){
					tsDocState = setTsDocState(tsDocState, tmConsultDoc.getDocId(), FLOW_STEP4, STATUS_NOTAGREE, userInfo.getCardNo(), userInfo.getUserId(), nowTh.toDate(), ACTION_ADD);
					exciseLawService.saveTsDocState(tsDocState);
				}else{
					tsDocState = setTsDocState(tsDocState, tmConsultDoc.getDocId(), FLOW_STEP4, STATUS_NOTAGREE, userInfo.getCardNo(), userInfo.getUserId(), nowTh.toDate(), ACTION_EDIT);
					exciseLawService.updateTsDocState(tsDocState);
				}
			}else if(docList.getFlowId() != null && docList.getFlowId().intValue() == FLOW_STEP5){
				tsDocState = setTsDocState(tsDocState, tmConsultDoc.getDocId(), FLOW_STEP4, STATUS_NOTAGREE, userInfo.getCardNo(), userInfo.getUserId(), nowTh.toDate(), ACTION_EDIT);
				exciseLawService.updateTsDocState(tsDocState);
			}			
						
			String [] delAttachFile = consultDocForm.getDeleteFileBoxes();
			if(delAttachFile!=null && delAttachFile.length>0){
				TsDocAttach tsDocAttach = null;
				for(String attachId:delAttachFile){
					tsDocAttach = new TsDocAttach();
					tsDocAttach.setAttachId(Long.valueOf(attachId));
					exciseLawService.deleteDocAttach(tsDocAttach);
				}
			}

			MultipartFile[] files = consultDocForm.getFileData();
			if(files != null && files.length>0){
	            for(MultipartFile file : files){
	            	if(file.getOriginalFilename() != null && file.getSize() > 0){
		            	TsDocAttach tsDocAttach = saveTsDocAttachFile(file, tmConsultDoc.getDocId() , userInfo.getUserId(), nowTh.toDate());
						exciseLawService.saveTsDocAttach(tsDocAttach);
	            	}
	            }
			}

			model.addAttribute("organizationList", exciseLawService.listOrganization(null));	
			model.addAttribute("lawyerList", exciseLawService.getLawyerList(orgId));	
			model.addAttribute("professionalList", exciseLawService.getProfessionalList());	
			model.addAttribute("tsDocAttachList", exciseLawService.findTsDocAttachListByDocId(tmConsultDoc.getDocId()));	
			model.addAttribute("vDocHistoryList", exciseLawService.findVDocHistory(tmConsultDoc.getDocId()));	
			model.addAttribute("docCommentList", exciseLawService.findDocCommentList(tmConsultDoc.getDocId()));	
			consultDocForm.setIsSusses("1"); 
			forword="consult_doc/consultDocSectionDetail";
		}
		setActionList(consultDocForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID3));
		model.addAttribute("statusList", statusList);
		model.addAttribute("securityLevelList", securityLevelList);
		model.addAttribute("departmentTypeList", departmentTypeList);
		model.addAttribute("internalOfficeList", exciseLawService.getInternalOfficeList());
		model.addAttribute("externalOfficeList", exciseLawService.getExternalOfficeList());
		model.addAttribute("consultDocForm", consultDocForm); 
		return forword;		 
	}	

	@RequestMapping("/consult_doc/consultDocLawyer")
	public String consultDocLawyer(ServletRequest request, ServletResponse response, 
				@RequestParam(value="action",required = false) String action,
				@RequestParam(value="docId",required = false) String docId, Model model) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		userInfo = (TmUserinfo) session.getAttribute("userInfo");
		ConsultDocForm consultDocForm = new ConsultDocForm();
		String orgId = "12344";//TODO
		String forword  ="consult_doc/consultDocLawyerList";
		if(action!=null){
			if(action.equals("edit")){
				logger.info( "ConsultDocController>>consultDocLawyer edit " ); 
				List list = exciseLawService.findVConsultDocByDocId(BigDecimal.valueOf(Long.valueOf(docId)));
				TmConsultDoc tmConsultDoc = (TmConsultDoc) list.get(0);
				TsDocState tsDocState = (TsDocState) list.get(1);	
				if(tsDocState.getFlowId()==null){
					VConsultDocList docList = exciseLawService.findVConsultDocListByDocId(Long.valueOf(docId));
					tsDocState.setFlowId(docList.getFlowId());
				}
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
				model.addAttribute("tsDocAttachList", exciseLawService.findTsDocAttachListByDocId(Long.parseLong(docId)));	
				model.addAttribute("vDocHistoryList", exciseLawService.findVDocHistory(Long.parseLong(docId)));	
				model.addAttribute("docCommentList", exciseLawService.findDocCommentList(tmConsultDoc.getDocId()));	
				forword="consult_doc/consultDocLawyerDetail";
			}
		}
		setActionList(consultDocForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID4));
		consultDocForm.setAction(action);
		model.addAttribute("statusList", statusList);
		model.addAttribute("securityLevelList", securityLevelList);
		model.addAttribute("departmentTypeList", departmentTypeList);
		model.addAttribute("internalOfficeList", exciseLawService.getInternalOfficeList());
		model.addAttribute("externalOfficeList", exciseLawService.getExternalOfficeList());
		model.addAttribute("consultDocForm", consultDocForm); 
		return forword;		 
	}
	
	@RequestMapping(value="/consult_doc/consultDocLawyer",method = RequestMethod.POST)
	public String processconsultDocLawyer(ServletRequest request, ServletResponse response, 
				@RequestParam(value="action",required = false) String action,
				@ModelAttribute("consultDocForm") ConsultDocForm consultDocForm,
				BindingResult result,
				Model model, SessionStatus status) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		userInfo = (TmUserinfo) session.getAttribute("userInfo");
		String forword  ="consult_doc/consultDocLawyerList";
		String orgId = "12344";
		TmConsultDoc tmConsultDoc = consultDocForm.getTmConsultDoc();
		TsDocState tsDocState = consultDocForm.getTsDocState();
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

		if(action.equals("doEdit")){	
			logger.info( "ConsultDocController>>consultDocLawyer.POST doEdit "); 
			tmConsultDoc.setUpdatedBy(userInfo.getUserId());
			tmConsultDoc.setUpdatedDate(nowTh.toDate());
			exciseLawService.updateTmConsultDoc(tmConsultDoc);		
			
			VConsultDoc doc = exciseLawService.findVConsultDocByDocId(tmConsultDoc.getDocId());
			VConsultDocList docList = exciseLawService.findVConsultDocListByDocId(tmConsultDoc.getDocId());
			
			BigDecimal flowId = doc.getFlowId();
			if(flowId == null){
				flowId = docList.getFlowId();
			}

			List<TsDocStatue> tsDocStatueList = exciseLawService.findTsDocStatueBydocId(BigDecimal.valueOf(tmConsultDoc.getDocId()));
			if(tsDocStatueList != null && tsDocStatueList.size()>0){
				for(TsDocStatue docStatue : tsDocStatueList){
					exciseLawService.deleteTsDocStatue(docStatue);
				}
			}
			
			String [] msStatutesBoxes = consultDocForm.getMsStatutesBoxes();
			if(msStatutesBoxes!=null && msStatutesBoxes.length>0){
				TsDocStatue tsDocStatue = null;
				for(String statueId : msStatutesBoxes){
					tsDocStatue = new TsDocStatue();
					tsDocStatue.setDocId(BigDecimal.valueOf(tmConsultDoc.getDocId()));
					tsDocStatue.setStatueId(BigDecimal.valueOf(Long.valueOf(statueId)));
					exciseLawService.saveTsDocStatue(tsDocStatue);
				}
			}			
			
			String [] delAttachFile = consultDocForm.getDeleteFileBoxes();
			if(delAttachFile!=null && delAttachFile.length>0){
				TsDocAttach tsDocAttach = null;
				for(String attachId : delAttachFile){
					tsDocAttach = new TsDocAttach();
					tsDocAttach.setAttachId(Long.valueOf(attachId));
					exciseLawService.deleteDocAttach(tsDocAttach);
				}
			}
			
			MultipartFile[] files = consultDocForm.getFileData();
			if(files != null && files.length>0){
	            for(MultipartFile file : files){
	            	if(file.getOriginalFilename() != null && file.getSize() > 0){
		            	TsDocAttach tsDocAttach = saveTsDocAttachFile(file, tmConsultDoc.getDocId() , userInfo.getUserId(), nowTh.toDate());
						exciseLawService.saveTsDocAttach(tsDocAttach);
	            	}
	            }
			}

			TsDocResolution tsDocResolution = consultDocForm.getTsDocResolution();
			tsDocResolution.setDocId(tmConsultDoc.getDocId());					
			tsDocResolution.setStateId(tsDocState.getStateId());					
			tsDocResolution.setCreatedBy(userInfo.getUserId());
			tsDocResolution.setUpdatedBy(userInfo.getUserId());
			tsDocResolution.setCreatedDate(nowTh.toDate());
			tsDocResolution.setUpdatedDate(nowTh.toDate());
			exciseLawService.saveTsDocResolution(tsDocResolution);

    		model.addAttribute("msStatutes", exciseLawService.listMsStatutes(null));
			model.addAttribute("organizationList", exciseLawService.listOrganization(null));	
			model.addAttribute("lawyerList", exciseLawService.getLawyerList(orgId));
			model.addAttribute("tsDocAttachList", exciseLawService.findTsDocAttachListByDocId(tmConsultDoc.getDocId()));
			model.addAttribute("vDocHistoryList", exciseLawService.findVDocHistory(tmConsultDoc.getDocId()));
			model.addAttribute("docCommentList", exciseLawService.findDocCommentList(tmConsultDoc.getDocId()));	
			consultDocForm.getTsDocState().setFlowId(flowId);		
			consultDocForm.setIsSusses("1"); 
			forword="consult_doc/consultDocLawyerDetail";
		}else if(action.equals("doSend")){
			logger.info( "ConsultDocController>>consultDocLawyer.POST doSend "); 
			
			tmConsultDoc.setUpdatedBy(userInfo.getUserId());
			tmConsultDoc.setUpdatedDate(nowTh.toDate());
			exciseLawService.updateTmConsultDoc(tmConsultDoc);		
						
			String [] msStatutesBoxes = consultDocForm.getMsStatutesBoxes();
			if(msStatutesBoxes!=null && msStatutesBoxes.length>0){
				TsDocStatue tsDocStatue = null;
				List<TsDocStatue> tsDocStatueList = exciseLawService.findTsDocStatueBydocId(BigDecimal.valueOf(tmConsultDoc.getDocId()));
				if(tsDocStatueList != null && tsDocStatueList.size()>0){
					for(TsDocStatue docStatue : tsDocStatueList){
						exciseLawService.deleteTsDocStatue(docStatue);
					}
				}
				for(String statueId : msStatutesBoxes){
					tsDocStatue = new TsDocStatue();
					tsDocStatue.setDocId(BigDecimal.valueOf(tmConsultDoc.getDocId()));
					tsDocStatue.setStatueId(BigDecimal.valueOf(Long.valueOf(statueId)));
					exciseLawService.saveTsDocStatue(tsDocStatue);
				}
			}		
			
			String [] delAttachFile = consultDocForm.getDeleteFileBoxes();
			if(delAttachFile!=null && delAttachFile.length>0){
				TsDocAttach tsDocAttach = null;
				for(String attachId : delAttachFile){
					tsDocAttach = new TsDocAttach();
					tsDocAttach.setAttachId(Long.valueOf(attachId));
					exciseLawService.deleteDocAttach(tsDocAttach);
				}
			}

			MultipartFile[] files = consultDocForm.getFileData();
			if(files != null && files.length>0){
	            for(MultipartFile file : files){
	            	if(file.getOriginalFilename() != null && file.getSize() > 0){
		            	TsDocAttach tsDocAttach = saveTsDocAttachFile(file, tmConsultDoc.getDocId(), userInfo.getUserId(), nowTh.toDate());
						exciseLawService.saveTsDocAttach(tsDocAttach);
	            	}
	            }
			}

			TsDocResolution tsDocResolution = consultDocForm.getTsDocResolution();
			tsDocResolution.setDocId(tmConsultDoc.getDocId());					
			tsDocResolution.setStateId(tsDocState.getStateId());					
			tsDocResolution.setCreatedBy(userInfo.getUserId());
			tsDocResolution.setUpdatedBy(userInfo.getUserId());
			tsDocResolution.setCreatedDate(nowTh.toDate());
			tsDocResolution.setUpdatedDate(nowTh.toDate());
			exciseLawService.saveTsDocResolution(tsDocResolution);
			
			tsDocState = setTsDocState(tsDocState, tmConsultDoc.getDocId(), FLOW_STEP5, STATUS_AGREE, "", userInfo.getUserId(), nowTh.toDate(), ACTION_ADD);

			if(tsDocState.getAssignProf() == null || "".equals(tsDocState.getAssignProf())){
				tsDocState.setAssignProf(null);
			}
			tsDocState.setDocComment(null);
			exciseLawService.saveTsDocState(tsDocState);
    		model.addAttribute("msStatutes", exciseLawService.listMsStatutes(null));
			model.addAttribute("organizationList", exciseLawService.listOrganization(null));	
			model.addAttribute("lawyerList", exciseLawService.getLawyerList(orgId));
			model.addAttribute("tsDocAttachList", exciseLawService.findTsDocAttachListByDocId(tmConsultDoc.getDocId()));
			model.addAttribute("vDocHistoryList", exciseLawService.findVDocHistory(tmConsultDoc.getDocId()));	
			model.addAttribute("docCommentList", exciseLawService.findDocCommentList(tmConsultDoc.getDocId()));	
			consultDocForm.setIsSusses("1");
			forword="consult_doc/consultDocLawyerDetail";
		}else if(action.equals("doSendback")){
			logger.info( "ConsultDocController>>consultDocLawyer.POST doSendback " );
			tmConsultDoc.setUpdatedBy(userInfo.getUserId());
			tmConsultDoc.setUpdatedDate(nowTh.toDate());
			exciseLawService.updateTmConsultDoc(tmConsultDoc);	
			tsDocState = setTsDocState(null, tmConsultDoc.getDocId(), FLOW_STEP3, STATUS_SENDBACK, "", userInfo.getUserId(), nowTh.toDate(), ACTION_ADD);
			exciseLawService.saveTsDocState(tsDocState);
			model.addAttribute("tsDocAttachList", exciseLawService.findTsDocAttachListByDocId(tmConsultDoc.getDocId()));	
			model.addAttribute("docCommentList", exciseLawService.findDocCommentList(tmConsultDoc.getDocId()));	
			consultDocForm.setIsSusses("1");
			forword="consult_doc/consultDocLawyerDetail";
		}else if(action.equals("doReject")){
			logger.info( "ConsultDocController>>consultDocLawyer.POST doReject " );
			tmConsultDoc.setStatus(STATUS_INACTIVE);
			tmConsultDoc.setUpdatedBy(userInfo.getUserId());
			tmConsultDoc.setUpdatedDate(nowTh.toDate());
			exciseLawService.updateTmConsultDoc(tmConsultDoc);	

			tsDocState = setTsDocState(null, tmConsultDoc.getDocId(), FLOW_STEP3, STATUS_REJECT, "", userInfo.getUserId(), nowTh.toDate(), ACTION_ADD);
			exciseLawService.saveTsDocState(tsDocState);
			model.addAttribute("tsDocAttachList", exciseLawService.findTsDocAttachListByDocId(tmConsultDoc.getDocId()));
			model.addAttribute("docCommentList", exciseLawService.findDocCommentList(tmConsultDoc.getDocId()));		
			consultDocForm.setIsSusses("1");
			forword="consult_doc/consultDocLawyerDetail";
		}
		setActionList(consultDocForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID4));
		model.addAttribute("statusList", statusList);
		model.addAttribute("securityLevelList", securityLevelList);
		model.addAttribute("departmentTypeList", departmentTypeList);
		model.addAttribute("internalOfficeList", exciseLawService.getInternalOfficeList());
		model.addAttribute("externalOfficeList", exciseLawService.getExternalOfficeList());
		model.addAttribute("consultDocForm", consultDocForm); 
		return forword;		 
	}	

	@RequestMapping("/consult_doc/consultDocProfessional")
	public String consultDocProfessional(ServletRequest request, ServletResponse response, 
				@RequestParam(value="action",required = false) String action,
				@RequestParam(value="docId",required = false) String docId, Model model) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		userInfo = (TmUserinfo) session.getAttribute("userInfo");
		ConsultDocForm consultDocForm = new ConsultDocForm();
		String orgId = "12344";//TODO
		String forword  ="consult_doc/consultDocProfessionalList";
		if(action!=null){
			if(action.equals("edit")){
				logger.info( "ConsultDocController>>consultDocProfessional edit " ); 
				List list = exciseLawService.findVConsultDocByDocId(BigDecimal.valueOf(Long.valueOf(docId)));
				TmConsultDoc tmConsultDoc = (TmConsultDoc) list.get(0);
				TsDocState tsDocState = (TsDocState) list.get(1);	
				if(tsDocState.getFlowId()==null){
					VConsultDocList docList = exciseLawService.findVConsultDocListByDocId(Long.valueOf(docId));
					tsDocState.setFlowId(docList.getFlowId());
				}
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
				forword="consult_doc/consultDocProfessionalDetail";
			}
		} else {
			logger.info( "ConsultDocController>>consultDocLaw search " ); 
			model.addAttribute("tmConsultDocProf",exciseLawService.searchVConsultDocList(new ConsultDocFilter(), 6, new Paging()).get(0));
			forword="consult_doc/consultDocProfessionalList";
		}
		setActionList(consultDocForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID5));
		consultDocForm.setAction(action);
		model.addAttribute("statusList", statusList);
		model.addAttribute("securityLevelList", securityLevelList);
		model.addAttribute("departmentTypeList", departmentTypeList);
		model.addAttribute("internalOfficeList", exciseLawService.getInternalOfficeList());
		model.addAttribute("externalOfficeList", exciseLawService.getExternalOfficeList());
		model.addAttribute("consultDocForm", consultDocForm); 
		return forword;		 
	}
	
	@RequestMapping(value="/consult_doc/consultDocProfessional",method = RequestMethod.POST)
	public String processConsultDocProf(ServletRequest request, ServletResponse response, 
				@RequestParam(value="action",required = false) String action,
				@ModelAttribute("consultDocForm") ConsultDocForm consultDocForm,
				BindingResult result,
				Model model, SessionStatus status) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		userInfo = (TmUserinfo) session.getAttribute("userInfo");
		String forword  ="consult_doc/consultDocProfessionalList";
		String orgId = "12344";
		TmConsultDoc tmConsultDoc = consultDocForm.getTmConsultDoc();
		TsDocState tsDocState = consultDocForm.getTsDocState();
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

		if(action.equals("doEdit")){	
			logger.info( "ConsultDocController>>consultDocProfessional.POST doEdit "); 
			tmConsultDoc.setUpdatedBy(userInfo.getUserId());
			tmConsultDoc.setUpdatedDate(nowTh.toDate());
			exciseLawService.updateTmConsultDoc(tmConsultDoc);	
			
			VConsultDoc doc = exciseLawService.findVConsultDocByDocId(tmConsultDoc.getDocId());
			VConsultDocList docList = exciseLawService.findVConsultDocListByDocId(tmConsultDoc.getDocId());
			
			BigDecimal flowId = doc.getFlowId();
			if(flowId == null){
				flowId = docList.getFlowId();
			}
			
			if(doc.getStateId() == docList.getStateId()){
				tsDocState = setTsDocState(tsDocState, tmConsultDoc.getDocId(), 0, 0, userInfo.getCardNo(), userInfo.getUserId(), nowTh.toDate(), ACTION_ADD);
				exciseLawService.saveTsDocState(tsDocState);
			}else{
				tsDocState = setTsDocState(tsDocState, tmConsultDoc.getDocId(), 0, 0, userInfo.getCardNo(), userInfo.getUserId(), nowTh.toDate(), ACTION_EDIT);
				exciseLawService.updateTsDocState(tsDocState);
			}

			String [] delAttachFile = consultDocForm.getDeleteFileBoxes();
			if(delAttachFile!=null && delAttachFile.length>0){
				TsDocAttach tsDocAttach = null;
				for(String attachId:delAttachFile){
					tsDocAttach = new TsDocAttach();
					tsDocAttach.setAttachId(Long.valueOf(attachId));
					exciseLawService.deleteDocAttach(tsDocAttach);
				}
			}

			MultipartFile[] files = consultDocForm.getFileData();
			if(files != null && files.length>0){
	            for(MultipartFile file : files){
	            	if(file.getOriginalFilename() != null && file.getSize() > 0){
		            	TsDocAttach tsDocAttach = saveTsDocAttachFile(file, tmConsultDoc.getDocId() , userInfo.getUserId(), nowTh.toDate());
						exciseLawService.saveTsDocAttach(tsDocAttach);
	            	}
	            }
			}

    		model.addAttribute("msStatutes", exciseLawService.listMsStatutes(null));
			model.addAttribute("organizationList", exciseLawService.listOrganization(null));	
			model.addAttribute("lawyerList", exciseLawService.getLawyerList(orgId));	
			model.addAttribute("professionalList", exciseLawService.getProfessionalList());	
			model.addAttribute("tsDocAttachList", exciseLawService.findTsDocAttachListByDocId(tmConsultDoc.getDocId()));	
			model.addAttribute("vDocHistoryList", exciseLawService.findVDocHistory(tmConsultDoc.getDocId()));
			model.addAttribute("docCommentList", exciseLawService.findDocCommentList(tmConsultDoc.getDocId()));	
			model.addAttribute("tsDocResolutionHistoryList", exciseLawService.getTsDocResolutionHistory(tmConsultDoc.getDocId()));	
			consultDocForm.getTsDocState().setFlowId(flowId);
			consultDocForm.setIsSusses("1"); 
			forword="consult_doc/consultDocProfessionalDetail";
		}else if(action.equals("doSend")){
			logger.info( "ConsultDocController>>consultDocProfessional.POST doSend "); 
			tmConsultDoc.setUpdatedBy(userInfo.getUserId());
			tmConsultDoc.setUpdatedDate(nowTh.toDate());
			exciseLawService.updateTmConsultDoc(tmConsultDoc);	

			VConsultDoc doc = exciseLawService.findVConsultDocByDocId(tmConsultDoc.getDocId());
			VConsultDocList docList = exciseLawService.findVConsultDocListByDocId(tmConsultDoc.getDocId());
			
			if(doc.getStateId() == docList.getStateId()){
				tsDocState = setTsDocState(tsDocState, tmConsultDoc.getDocId(), FLOW_STEP7, STATUS_AGREE, userInfo.getCardNo(), userInfo.getUserId(), nowTh.toDate(), ACTION_ADD);
				exciseLawService.saveTsDocState(tsDocState);
			}else{
				tsDocState = setTsDocState(tsDocState, tmConsultDoc.getDocId(), FLOW_STEP7, STATUS_AGREE, userInfo.getCardNo(), userInfo.getUserId(), nowTh.toDate(), ACTION_EDIT);
				exciseLawService.updateTsDocState(tsDocState);
			}

			String [] delAttachFile = consultDocForm.getDeleteFileBoxes();
			if(delAttachFile!=null && delAttachFile.length>0){
				TsDocAttach tsDocAttach = null;
				for(String attachId:delAttachFile){
					tsDocAttach = new TsDocAttach();
					tsDocAttach.setAttachId(Long.valueOf(attachId));
					exciseLawService.deleteDocAttach(tsDocAttach);
				}
			}

			MultipartFile[] files = consultDocForm.getFileData();
			if(files != null && files.length>0){
	            for(MultipartFile file : files){
	            	if(file.getOriginalFilename() != null && file.getSize() > 0){
		            	TsDocAttach tsDocAttach = saveTsDocAttachFile(file, tmConsultDoc.getDocId() , userInfo.getUserId(), nowTh.toDate());
						exciseLawService.saveTsDocAttach(tsDocAttach);
	            	}
	            }
			}

    		model.addAttribute("msStatutes", exciseLawService.listMsStatutes(null));
			model.addAttribute("organizationList", exciseLawService.listOrganization(null));	
			model.addAttribute("lawyerList", exciseLawService.getLawyerList(orgId));	
			model.addAttribute("professionalList", exciseLawService.getProfessionalList());	
			model.addAttribute("tsDocAttachList", exciseLawService.findTsDocAttachListByDocId(tmConsultDoc.getDocId()));
			model.addAttribute("vDocHistoryList", exciseLawService.findVDocHistory(tmConsultDoc.getDocId()));
			model.addAttribute("docCommentList", exciseLawService.findDocCommentList(tmConsultDoc.getDocId()));	
			model.addAttribute("tsDocResolutionHistoryList", exciseLawService.getTsDocResolutionHistory(tmConsultDoc.getDocId()));		
			consultDocForm.setIsSusses("1");
			forword="consult_doc/consultDocProfessionalDetail";
		}else if(action.equals("doReject")){	
			logger.info( "ConsultDocController>>consultDocProfessional.POST doReject ");  
			tmConsultDoc.setUpdatedBy(userInfo.getUserId());
			tmConsultDoc.setUpdatedDate(nowTh.toDate());
			exciseLawService.updateTmConsultDoc(tmConsultDoc);	

			VConsultDoc doc = exciseLawService.findVConsultDocByDocId(tmConsultDoc.getDocId());
			VConsultDocList docList = exciseLawService.findVConsultDocListByDocId(tmConsultDoc.getDocId());
			
			if(doc.getStateId() == docList.getStateId()){
				tsDocState = setTsDocState(tsDocState, tmConsultDoc.getDocId(), FLOW_STEP5, STATUS_NOTAGREE, userInfo.getCardNo(), userInfo.getUserId(), nowTh.toDate(), ACTION_ADD);
				exciseLawService.saveTsDocState(tsDocState);
			}else{
				tsDocState = setTsDocState(tsDocState, tmConsultDoc.getDocId(), FLOW_STEP5, STATUS_NOTAGREE, userInfo.getCardNo(), userInfo.getUserId(), nowTh.toDate(), ACTION_EDIT);
				exciseLawService.updateTsDocState(tsDocState);
			}

			String [] delAttachFile = consultDocForm.getDeleteFileBoxes();
			if(delAttachFile!=null && delAttachFile.length>0){
				TsDocAttach tsDocAttach = null;
				for(String attachId:delAttachFile){
					tsDocAttach = new TsDocAttach();
					tsDocAttach.setAttachId(Long.valueOf(attachId));
					exciseLawService.deleteDocAttach(tsDocAttach);
				}
			}

			MultipartFile[] files = consultDocForm.getFileData();
			if(files != null && files.length>0){
	            for(MultipartFile file : files){
	            	if(file.getOriginalFilename() != null && file.getSize() > 0){
		            	TsDocAttach tsDocAttach = saveTsDocAttachFile(file, tmConsultDoc.getDocId() , userInfo.getUserId(), nowTh.toDate());
						exciseLawService.saveTsDocAttach(tsDocAttach);
	            	}
	            }
			}
			
    		model.addAttribute("msStatutes", exciseLawService.listMsStatutes(null));
			model.addAttribute("organizationList", exciseLawService.listOrganization(null));	
			model.addAttribute("lawyerList", exciseLawService.getLawyerList(orgId));	
			model.addAttribute("professionalList", exciseLawService.getProfessionalList());	
			model.addAttribute("tsDocAttachList", exciseLawService.findTsDocAttachListByDocId(tmConsultDoc.getDocId()));
			model.addAttribute("vDocHistoryList", exciseLawService.findVDocHistory(tmConsultDoc.getDocId()));
			model.addAttribute("docCommentList", exciseLawService.findDocCommentList(tmConsultDoc.getDocId()));	
			model.addAttribute("tsDocResolutionHistoryList", exciseLawService.getTsDocResolutionHistory(tmConsultDoc.getDocId()));		
			consultDocForm.setIsSusses("1");
			forword="consult_doc/consultDocProfessionalDetail";
		}
		setActionList(consultDocForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID5));
		model.addAttribute("statusList", statusList);
		model.addAttribute("securityLevelList", securityLevelList);
		model.addAttribute("departmentTypeList", departmentTypeList);
		model.addAttribute("internalOfficeList", exciseLawService.getInternalOfficeList());
		model.addAttribute("externalOfficeList", exciseLawService.getExternalOfficeList());
		model.addAttribute("consultDocForm", consultDocForm); 
		return forword;		 
	}		
		
	@RequestMapping("/consult_doc/consultDocAnswer")
	public String consultDocAnswer(ServletRequest request, ServletResponse response, 
				@RequestParam(value="action",required = false) String action,
				@RequestParam(value="docId",required = false) String docId, Model model) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		userInfo = (TmUserinfo) session.getAttribute("userInfo");
		ConsultDocForm consultDocForm = new ConsultDocForm();
		String forword  ="consult_doc/consultDocAnswerList";
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
				forword="consult_doc/consultDocAnswerDetail";
			}
		}
		setActionList(consultDocForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID8));
		consultDocForm.setAction(action);
		model.addAttribute("statusList", statusList);
		model.addAttribute("securityLevelList", securityLevelList);
		model.addAttribute("departmentTypeList", departmentTypeList);
		model.addAttribute("internalOfficeList", exciseLawService.getInternalOfficeList());
		model.addAttribute("externalOfficeList", exciseLawService.getExternalOfficeList());
		model.addAttribute("consultDocForm", consultDocForm); 
		return forword;		 
	}
	
	@RequestMapping(value="/consult_doc/consultDocAnswer",method = RequestMethod.POST)
	public String processconsultDocAnswer(ServletRequest request, ServletResponse response, 
				@RequestParam(value="action",required = false) String action,
				@ModelAttribute("consultDocForm") ConsultDocForm consultDocForm,
				BindingResult result,
				Model model, SessionStatus status) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		userInfo = (TmUserinfo) session.getAttribute("userInfo");
		String forword  ="consult_doc/consultDocAnswerList";
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

		if(action.equals("doEdit")){	
			logger.info( "ConsultDocController>>consultDocAnswer.POST doEdit "); 
			tmConsultDoc.setCompleted(BigDecimal.valueOf(1));
			tmConsultDoc.setUpdatedBy(userInfo.getUserId());
			tmConsultDoc.setUpdatedDate(nowTh.toDate());
			exciseLawService.updateTmConsultDoc(tmConsultDoc);			
						
			String [] delAttachFile = consultDocForm.getDeleteFileBoxes();
			if(delAttachFile!=null && delAttachFile.length>0){
				TsDocAttach tsDocAttach = null;
				for(String attachId:delAttachFile){
					tsDocAttach = new TsDocAttach();
					tsDocAttach.setAttachId(Long.valueOf(attachId));
					exciseLawService.deleteDocAttach(tsDocAttach);
				}
			}

			MultipartFile[] files = consultDocForm.getFileData();
			if(files != null && files.length>0){
	            for(MultipartFile file : files){
	            	if(file.getOriginalFilename() != null && file.getSize() > 0){
		            	TsDocAttach tsDocAttach = saveTsDocAttachFile(file, tmConsultDoc.getDocId() , userInfo.getUserId(), nowTh.toDate());
						exciseLawService.saveTsDocAttach(tsDocAttach);
	            	}
	            }
			}

    		model.addAttribute("msStatutes", exciseLawService.listMsStatutes(null));
			model.addAttribute("organizationList", exciseLawService.listOrganization(null));
			model.addAttribute("lawyerList", exciseLawService.getLawyerList(orgId));	
			model.addAttribute("professionalList", exciseLawService.getProfessionalList());	
			model.addAttribute("tsDocAttachList", exciseLawService.findTsDocAttachListByDocId(tmConsultDoc.getDocId()));
			model.addAttribute("vDocHistoryList", exciseLawService.findVDocHistory(tmConsultDoc.getDocId()));	
			model.addAttribute("docCommentList", exciseLawService.findDocCommentList(tmConsultDoc.getDocId()));			
			model.addAttribute("tsDocResolutionHistoryList", exciseLawService.getTsDocResolutionHistory(tmConsultDoc.getDocId()));
			consultDocForm.setIsSusses("1"); 
			forword="consult_doc/consultDocAnswerDetail";
		}
		setActionList(consultDocForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID8));
		model.addAttribute("statusList", statusList);
		model.addAttribute("securityLevelList", securityLevelList);
		model.addAttribute("departmentTypeList", departmentTypeList);
		model.addAttribute("internalOfficeList", exciseLawService.getInternalOfficeList());
		model.addAttribute("externalOfficeList", exciseLawService.getExternalOfficeList());
		model.addAttribute("consultDocForm", consultDocForm); 
		return forword;		 
	}	
	
	private void setActionList(ConsultDocForm consultDocForm, String actionList){
		if(actionList.indexOf("ADD") != -1){
			consultDocForm.setAdd("true");
		}
		if(actionList.indexOf("EDIT") != -1){
			consultDocForm.setEdit("true");
		}
		if(actionList.indexOf("DELETE") != -1){
			consultDocForm.setDelete("true");
		}
		if(actionList.indexOf("VIEW") != -1){
			consultDocForm.setView("true");
		}		
	}
	
	private TsDocState setTsDocState(TsDocState tsDocState, long docId, long FLOW_STEP, long STATUS, String cardNo, String userId, Date today, String action){
		if(tsDocState == null)
			tsDocState = new TsDocState();
		if(docId != 0)
			tsDocState.setDocId(BigDecimal.valueOf(docId));
		if(action.equals("ADD")){
			tsDocState.setStateId(0);
			tsDocState.setCreatedBy(userInfo.getUserId());
			tsDocState.setCreatedDate(today);
		}		
		if(FLOW_STEP != 0)
			tsDocState.setFlowId(BigDecimal.valueOf(FLOW_STEP));
		else
			tsDocState.setFlowId(null);
		if(STATUS != 0)		
			tsDocState.setStatusId(BigDecimal.valueOf(STATUS));
		else
			tsDocState.setStatusId(null);
		if(cardNo != null && !"".equals(cardNo)){
			tsDocState.setPersonIncharge(cardNo);
		}
		tsDocState.setUpdatedBy(userInfo.getUserId());
		tsDocState.setUpdatedDate(today);
		return tsDocState;
	}
	
	private TsDocAttach saveTsDocAttachFile(MultipartFile fileUpload, long docId, String userId, Date today){
		TsDocAttach tsDocAttach = new TsDocAttach();
		FileOutputStream fos = null;
		if (fileUpload.getSize() > 0) {
            try {
		        byte[] bytes = fileUpload.getBytes();		 

            	String filePath = resources.getString("consultdoc_path")+"/"+docId;
            	File fileDir = new File(filePath);
            	if(!fileDir.exists()){
            		fileDir.mkdirs();
            	}
            	File file = new File(fileDir, fileUpload.getOriginalFilename());
		        fos = new FileOutputStream(file);
		        fos.write(bytes);
		        fos.flush();
		        fos.close();
		        
		        tsDocAttach.setFileName(fileUpload.getOriginalFilename());
		        tsDocAttach.setFilePath(filePath);
		        tsDocAttach.setDocId(BigDecimal.valueOf(docId));
		        tsDocAttach.setCreatedBy(userInfo.getUserId());
		        tsDocAttach.setUpdatedBy(userInfo.getUserId());
		        tsDocAttach.setCreatedDate(today);
		        tsDocAttach.setUpdatedDate(today);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try { fos.close(); } catch (IOException e) { e.printStackTrace(); }
			}
	    }
		return tsDocAttach;
	}
	
//	@RequestMapping("/consult_doc/consultDocDirector")
//	public String consultDocDirector(ServletRequest request, ServletResponse response, 
//				@RequestParam(value="action",required = false) String action,
//				@RequestParam(value="docId",required = false) String docId, Model model) {
//		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//		HttpSession session = httpServletRequest.getSession();
//		userInfo = (TmUserinfo) session.getAttribute("userInfo");
//		String orgId = "12344";//TODO
//		ConsultDocForm consultDocForm = new ConsultDocForm();
//		String forword  ="consult_doc/consultDocDirectorList";
//		if(action!=null){
//			if(action.equals("edit")){
//				logger.info( "ConsultDocController>>consultDocDirector edit " ); 
//				List list = exciseLawService.findVConsultDocByDocId(BigDecimal.valueOf(Long.valueOf(docId)));
//				TmConsultDoc tmConsultDoc = (TmConsultDoc) list.get(0);
//				TsDocState tsDocState = (TsDocState) list.get(1);	
//				if(tsDocState.getFlowId()==null){
//					VConsultDocList docList = exciseLawService.findVConsultDocListByDocId(Long.valueOf(docId));
//					tsDocState.setFlowId(docList.getFlowId());
//				}
//				tsDocState.setDocComment("");
//				TsDocResolution tsDocResolution = exciseLawService.findLastTsDocResolution(Long.parseLong(docId));
//
//				if(tmConsultDoc.getDocRefDate()!=null){
//					consultDocForm.setDocRefDate(DateUtils.getStringDateByDateTh(tmConsultDoc.getDocRefDate().getTime()));
//				}
//				if(tmConsultDoc.getOfficeRecDate()!=null){
//					consultDocForm.setOfficeRecDate(DateUtils.getStringDateByDateTh(tmConsultDoc.getOfficeRecDate().getTime()));
//				}			
//				if(tmConsultDoc.getDocDate()!=null){
//					consultDocForm.setDocDate(DateUtils.getStringDateByDateTh(tmConsultDoc.getDocDate().getTime()));
//				}		
//				if(tmConsultDoc.getBureauRecDate()!=null){
//					consultDocForm.setBureauRecDate(DateUtils.getStringDateByDateTh(tmConsultDoc.getBureauRecDate().getTime()));
//				}
//				if(tmConsultDoc.getSectionRecDate()!=null){
//					consultDocForm.setSectionRecDate(DateUtils.getStringDateByDateTh(tmConsultDoc.getSectionRecDate().getTime()));
//				}
//				if(tmConsultDoc.getDocSendDate()!=null){
//					consultDocForm.setDocSendDate(DateUtils.getStringDateByDateTh(tmConsultDoc.getDocSendDate().getTime()));
//				}
//				if(tmConsultDoc.getBureauSendDate()!=null){
//					consultDocForm.setBureauSendDate(DateUtils.getStringDateByDateTh(tmConsultDoc.getBureauSendDate().getTime()));
//				}
//				if(tmConsultDoc.getSectionSendDate()!=null){
//					consultDocForm.setSectionSendDate(DateUtils.getStringDateByDateTh(tmConsultDoc.getSectionSendDate().getTime()));
//				}
//				if(tmConsultDoc.getCreatedDate()!=null){
//					consultDocForm.setCreatedDate(DateUtils.getStringDateByDateTh(tmConsultDoc.getCreatedDate().getTime()));
//				}
//				
//				if(tmConsultDoc.getDocType().equals("I")){
//					consultDocForm.setInternalOfficeId(tmConsultDoc.getConsultOrg());
//				}else if(tmConsultDoc.getDocType().equals("E")){
//					consultDocForm.setExternalOfficeId(tmConsultDoc.getConsultOrg());
//				}
//				
//				List<TsDocStatue> tsDocStatueList = exciseLawService.findTsDocStatueBydocId(BigDecimal.valueOf(tmConsultDoc.getDocId()));
//				List<String> tsDocStatuesList = new ArrayList<String>();
//				if(tsDocStatueList != null && tsDocStatueList.size()>0){
//					for (TsDocStatue tsDocStatue : tsDocStatueList) {
//						String itemId = tsDocStatue.getStatueId()+"";
//						tsDocStatuesList.add(itemId);
//					}
//				}
//				String[] tsDocStatues = new String[tsDocStatuesList.size()];
//				consultDocForm.setMsStatutesBoxes(tsDocStatuesList.toArray(tsDocStatues));
//				consultDocForm.setTmConsultDoc(tmConsultDoc);
//				consultDocForm.setTsDocState(tsDocState);
//				consultDocForm.setTsDocResolution(tsDocResolution);	
//	    		model.addAttribute("msStatutes", exciseLawService.listMsStatutes(null));
//				model.addAttribute("organizationList", exciseLawService.listOrganization(null));	
//				model.addAttribute("lawyerList", exciseLawService.getLawyerList(orgId));	
//				model.addAttribute("professionalList", exciseLawService.getProfessionalList());	
//				model.addAttribute("tsDocAttachList", exciseLawService.findTsDocAttachListByDocId(Long.parseLong(docId)));	
//				model.addAttribute("vDocHistoryList", exciseLawService.findVDocHistory(Long.parseLong(docId)));
//				model.addAttribute("docCommentList", exciseLawService.findDocCommentList(tmConsultDoc.getDocId()));	
//				model.addAttribute("tsDocResolutionHistoryList", exciseLawService.getTsDocResolutionHistory(Long.parseLong(docId)));	
//				forword="consult_doc/consultDocDirectorDetail";
//			}
//		}
//		setActionList(consultDocForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID7));
//		consultDocForm.setAction(action);
//		model.addAttribute("statusList", statusList);
//		model.addAttribute("securityLevelList", securityLevelList);
//		model.addAttribute("departmentTypeList", departmentTypeList);
//		model.addAttribute("internalOfficeList", exciseLawService.getInternalOfficeList());
//		model.addAttribute("externalOfficeList", exciseLawService.getExternalOfficeList());
//		model.addAttribute("consultDocForm", consultDocForm); 
//		return forword;		 
//	}
//	
//	@RequestMapping(value="/consult_doc/consultDocDirector",method = RequestMethod.POST)
//	public String processconsultDocDirector(ServletRequest request, ServletResponse response, 
//				@RequestParam(value="action",required = false) String action,
//				@ModelAttribute("consultDocForm") ConsultDocForm consultDocForm,
//				BindingResult result,
//				Model model, SessionStatus status) {
//		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//		String orgId = "12344";
//		HttpSession session = httpServletRequest.getSession();
//		userInfo = (TmUserinfo) session.getAttribute("userInfo");
//		String forword  ="consult_doc/consultDocDirectorList";
//		TmConsultDoc tmConsultDoc = consultDocForm.getTmConsultDoc();
//		TsDocState tsDocState = consultDocForm.getTsDocState();
//		DateTime nowTh = new DateTime(new Date().getTime());
//
//		if(consultDocForm.getDocRefDate()!=null && consultDocForm.getDocRefDate().length()>0){
//			tmConsultDoc.setDocRefDate(DateUtils.getDateByStringDateTh(consultDocForm.getDocRefDate()));
//		}
//		if(consultDocForm.getOfficeRecDate()!=null && consultDocForm.getOfficeRecDate().length()>0){
//			tmConsultDoc.setOfficeRecDate(DateUtils.getDateByStringDateTh(consultDocForm.getOfficeRecDate()));
//		}
//		if(consultDocForm.getDocDate()!=null && consultDocForm.getDocDate().length()>0){
//			tmConsultDoc.setDocDate(DateUtils.getDateByStringDateTh(consultDocForm.getDocDate()));
//		}
//		if(consultDocForm.getBureauRecDate()!=null && consultDocForm.getBureauRecDate().length()>0){
//			tmConsultDoc.setBureauRecDate(DateUtils.getDateByStringDateTh(consultDocForm.getBureauRecDate()));
//		}
//		if(consultDocForm.getSectionRecDate()!=null && consultDocForm.getSectionRecDate().length()>0){
//			tmConsultDoc.setSectionRecDate(DateUtils.getDateByStringDateTh(consultDocForm.getSectionRecDate()));
//		}
//		if(consultDocForm.getDocSendDate()!=null){
//			tmConsultDoc.setDocSendDate(DateUtils.getDateByStringDateTh(consultDocForm.getDocSendDate()));
//		}
//		if(consultDocForm.getBureauSendDate()!=null){
//			tmConsultDoc.setBureauSendDate(DateUtils.getDateByStringDateTh(consultDocForm.getBureauSendDate()));
//		}
//		if(consultDocForm.getSectionSendDate()!=null){
//			tmConsultDoc.setSectionSendDate(DateUtils.getDateByStringDateTh(consultDocForm.getSectionSendDate()));
//		}
//		if(consultDocForm.getCreatedDate()!=null && consultDocForm.getCreatedDate().length()>0){
//			tmConsultDoc.setCreatedDate(DateUtils.getDateByStringDateTh(consultDocForm.getCreatedDate()));
//		}
//
//		if(action.equals("doEdit")){	
//			logger.info( "ConsultDocController>>consultDocDirector.POST doEdit "); 
//			tmConsultDoc.setUpdatedBy(userInfo.getUserId());
//			tmConsultDoc.setUpdatedDate(nowTh.toDate());
//			exciseLawService.updateTmConsultDoc(tmConsultDoc);		
//
//			VConsultDoc doc = exciseLawService.findVConsultDocByDocId(tmConsultDoc.getDocId());
//			VConsultDocList docList = exciseLawService.findVConsultDocListByDocId(tmConsultDoc.getDocId());
//			
//			if(doc.getStateId() == docList.getStateId()){
//				tsDocState = setTsDocState(tsDocState, tmConsultDoc.getDocId(), 0, 0, userInfo.getCardNo(), userInfo.getUserId(), nowTh.toDate(), ACTION_ADD);
//				exciseLawService.saveTsDocState(tsDocState);
//			}else{
//				tsDocState = setTsDocState(tsDocState, tmConsultDoc.getDocId(), 0, 0, userInfo.getCardNo(), userInfo.getUserId(), nowTh.toDate(), ACTION_EDIT);
//				exciseLawService.updateTsDocState(tsDocState);
//			}	
//						
//			String [] delAttachFile = consultDocForm.getDeleteFileBoxes();
//			if(delAttachFile!=null && delAttachFile.length>0){
//				TsDocAttach tsDocAttach = null;
//				for(String attachId:delAttachFile){
//					tsDocAttach = new TsDocAttach();
//					tsDocAttach.setAttachId(Long.valueOf(attachId));
//					exciseLawService.deleteDocAttach(tsDocAttach);
//				}
//			}
//
//			MultipartFile[] files = consultDocForm.getFileData();
//			if(files != null && files.length>0){
//	            for(MultipartFile file : files){
//	            	if(file.getOriginalFilename() != null && file.getSize() > 0){
//		            	TsDocAttach tsDocAttach = saveTsDocAttachFile(file, tmConsultDoc.getDocId() , userInfo.getUserId(), nowTh.toDate());
//						exciseLawService.saveTsDocAttach(tsDocAttach);
//	            	}
//	            }
//			}
//
//    		model.addAttribute("msStatutes", exciseLawService.listMsStatutes(null));
//			model.addAttribute("organizationList", exciseLawService.listOrganization(null));	
//			model.addAttribute("lawyerList", exciseLawService.getLawyerList(orgId));	
//			model.addAttribute("professionalList", exciseLawService.getProfessionalList());	
//			model.addAttribute("tsDocAttachList", exciseLawService.findTsDocAttachListByDocId(tmConsultDoc.getDocId()));
//			model.addAttribute("vDocHistoryList", exciseLawService.findVDocHistory(tmConsultDoc.getDocId()));
//			model.addAttribute("docCommentList", exciseLawService.findDocCommentList(tmConsultDoc.getDocId()));	
//			model.addAttribute("tsDocResolutionHistoryList", exciseLawService.getTsDocResolutionHistory(tmConsultDoc.getDocId()));	
//			consultDocForm.setIsSusses("1"); 
//			forword="consult_doc/consultDocDirectorDetail";
//		}else if(action.equals("doSend")){
//			logger.info( "ConsultDocController>>consultDocDirectorDetail.POST doSend "); 
//			tmConsultDoc.setUpdatedBy(userInfo.getUserId());
//			tmConsultDoc.setUpdatedDate(nowTh.toDate());
//			exciseLawService.updateTmConsultDoc(tmConsultDoc);		
//
//			VConsultDoc doc = exciseLawService.findVConsultDocByDocId(tmConsultDoc.getDocId());
//			VConsultDocList docList = exciseLawService.findVConsultDocListByDocId(tmConsultDoc.getDocId());
//			
//			if(doc.getStateId() == docList.getStateId()){
//				if(doc.getDocType().equals("I")){
//					tsDocState = setTsDocState(tsDocState, tmConsultDoc.getDocId(), FLOW_STEP9, STATUS_AGREE, userInfo.getCardNo(), userInfo.getUserId(), nowTh.toDate(), ACTION_ADD);
//				}else if(doc.getDocType().equals("E") || doc.getDocType().equals("O")){
//					tsDocState = setTsDocState(tsDocState, tmConsultDoc.getDocId(), FLOW_STEP11, STATUS_AGREE, userInfo.getCardNo(), userInfo.getUserId(), nowTh.toDate(), ACTION_ADD);
//				}
//				exciseLawService.saveTsDocState(tsDocState);
//			}else{
//				if(doc.getDocType().equals("I")){
//					tsDocState = setTsDocState(tsDocState, tmConsultDoc.getDocId(), FLOW_STEP9, STATUS_AGREE, userInfo.getCardNo(), userInfo.getUserId(), nowTh.toDate(), ACTION_EDIT);
//				}else if(doc.getDocType().equals("E") || doc.getDocType().equals("O")){
//					tsDocState = setTsDocState(tsDocState, tmConsultDoc.getDocId(), FLOW_STEP11, STATUS_AGREE, userInfo.getCardNo(), userInfo.getUserId(), nowTh.toDate(), ACTION_EDIT);
//				}
//				exciseLawService.updateTsDocState(tsDocState);
//			}
//
//			String [] delAttachFile = consultDocForm.getDeleteFileBoxes();
//			if(delAttachFile!=null && delAttachFile.length>0){
//				TsDocAttach tsDocAttach = null;
//				for(String attachId:delAttachFile){
//					tsDocAttach = new TsDocAttach();
//					tsDocAttach.setAttachId(Long.valueOf(attachId));
//					exciseLawService.deleteDocAttach(tsDocAttach);
//				}
//			}
//
//			MultipartFile[] files = consultDocForm.getFileData();
//			if(files != null && files.length>0){
//	            for(MultipartFile file : files){
//	            	if(file.getOriginalFilename() != null && file.getSize() > 0){
//		            	TsDocAttach tsDocAttach = saveTsDocAttachFile(file, tmConsultDoc.getDocId() , userInfo.getUserId(), nowTh.toDate());
//						exciseLawService.saveTsDocAttach(tsDocAttach);
//	            	}
//	            }
//			}
//
//    		model.addAttribute("msStatutes", exciseLawService.listMsStatutes(null));
//			model.addAttribute("organizationList", exciseLawService.listOrganization(null));	
//			model.addAttribute("lawyerList", exciseLawService.getLawyerList(orgId));	
//			model.addAttribute("professionalList", exciseLawService.getProfessionalList());	
//			model.addAttribute("tsDocAttachList", exciseLawService.findTsDocAttachListByDocId(tmConsultDoc.getDocId()));
//			model.addAttribute("vDocHistoryList", exciseLawService.findVDocHistory(tmConsultDoc.getDocId()));
//			model.addAttribute("docCommentList", exciseLawService.findDocCommentList(tmConsultDoc.getDocId()));	
//			model.addAttribute("tsDocResolutionHistoryList", exciseLawService.getTsDocResolutionHistory(tmConsultDoc.getDocId()));
//			consultDocForm.setIsSusses("1");
//			forword="consult_doc/consultDocDirectorDetail";
//		}else if(action.equals("doReject")){	
//			logger.info( "ConsultDocController>>consultDocDirectorDetail.POST doReject ");  
//			tmConsultDoc.setUpdatedBy(userInfo.getUserId());
//			tmConsultDoc.setUpdatedDate(nowTh.toDate());
//			exciseLawService.updateTmConsultDoc(tmConsultDoc);	
//
//			VConsultDoc doc = exciseLawService.findVConsultDocByDocId(tmConsultDoc.getDocId());
//			VConsultDocList docList = exciseLawService.findVConsultDocListByDocId(tmConsultDoc.getDocId());
//			
//			if(doc.getStateId() == docList.getStateId()){
//				tsDocState = setTsDocState(tsDocState, tmConsultDoc.getDocId(), FLOW_STEP7, STATUS_NOTAGREE, userInfo.getCardNo(), userInfo.getUserId(), nowTh.toDate(), ACTION_ADD);
//				exciseLawService.saveTsDocState(tsDocState);
//			}else{
//				tsDocState = setTsDocState(tsDocState, tmConsultDoc.getDocId(), FLOW_STEP7, STATUS_NOTAGREE, userInfo.getCardNo(), userInfo.getUserId(), nowTh.toDate(), ACTION_EDIT);
//				exciseLawService.updateTsDocState(tsDocState);
//			}
//
//			String [] delAttachFile = consultDocForm.getDeleteFileBoxes();
//			if(delAttachFile!=null && delAttachFile.length>0){
//				TsDocAttach tsDocAttach = null;
//				for(String attachId:delAttachFile){
//					tsDocAttach = new TsDocAttach();
//					tsDocAttach.setAttachId(Long.valueOf(attachId));
//					exciseLawService.deleteDocAttach(tsDocAttach);
//				}
//			}
//
//			MultipartFile[] files = consultDocForm.getFileData();
//			if(files != null && files.length>0){
//	            for(MultipartFile file : files){
//	            	if(file.getOriginalFilename() != null && file.getSize() > 0){
//		            	TsDocAttach tsDocAttach = saveTsDocAttachFile(file, tmConsultDoc.getDocId() , userInfo.getUserId(), nowTh.toDate());
//						exciseLawService.saveTsDocAttach(tsDocAttach);
//	            	}
//	            }
//			}
//
//    		model.addAttribute("msStatutes", exciseLawService.listMsStatutes(null));
//			model.addAttribute("organizationList", exciseLawService.listOrganization(null));	
//			model.addAttribute("lawyerList", exciseLawService.getLawyerList(orgId));	
//			model.addAttribute("professionalList", exciseLawService.getProfessionalList());	
//			model.addAttribute("tsDocAttachList", exciseLawService.findTsDocAttachListByDocId(tmConsultDoc.getDocId()));
//			model.addAttribute("vDocHistoryList", exciseLawService.findVDocHistory(tmConsultDoc.getDocId()));
//			model.addAttribute("docCommentList", exciseLawService.findDocCommentList(tmConsultDoc.getDocId()));	
//			model.addAttribute("tsDocResolutionHistoryList", exciseLawService.getTsDocResolutionHistory(tmConsultDoc.getDocId()));
//			consultDocForm.setIsSusses("1");
//			forword="consult_doc/consultDocDirectorDetail";
//		}else if(action.equals("doCallback")){
//			logger.info( "ConsultDocController>>consultDocNew.POST doCallback " );
//			TsDocState docState = setTsDocState(null, tmConsultDoc.getDocId(), FLOW_STEP8, STATUS_CALLBACK, userInfo.getCardNo(), userInfo.getUserId(), nowTh.toDate(), ACTION_ADD);
//			exciseLawService.saveTsDocState(docState);
//    		model.addAttribute("msStatutes", exciseLawService.listMsStatutes(null));
//			model.addAttribute("organizationList", exciseLawService.listOrganization(null));	
//			model.addAttribute("lawyerList", exciseLawService.getLawyerList(orgId));	
//			model.addAttribute("professionalList", exciseLawService.getProfessionalList());	
//			model.addAttribute("tsDocAttachList", exciseLawService.findTsDocAttachListByDocId(tmConsultDoc.getDocId()));
//			model.addAttribute("vDocHistoryList", exciseLawService.findVDocHistory(tmConsultDoc.getDocId()));
//			model.addAttribute("docCommentList", exciseLawService.findDocCommentList(tmConsultDoc.getDocId()));	
//			model.addAttribute("tsDocResolutionHistoryList", exciseLawService.getTsDocResolutionHistory(tmConsultDoc.getDocId()));
//			consultDocForm.setIsSusses("1");
//			forword="consult_doc/consultDocDirectorDetail";
//		}
//		setActionList(consultDocForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID7));
//		model.addAttribute("statusList", statusList);
//		model.addAttribute("securityLevelList", securityLevelList);
//		model.addAttribute("departmentTypeList", departmentTypeList);
//		model.addAttribute("internalOfficeList", exciseLawService.getInternalOfficeList());
//		model.addAttribute("externalOfficeList", exciseLawService.getExternalOfficeList());
//		model.addAttribute("consultDocForm", consultDocForm); 
//		return forword;		 
//	}	
//	
//	@RequestMapping("/consult_doc/consultDocSecretary")
//	public String consultDocSecretary(ServletRequest request, ServletResponse response, 
//				@RequestParam(value="action",required = false) String action,
//				@RequestParam(value="docId",required = false) String docId, Model model) {
//		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//		HttpSession session = httpServletRequest.getSession();
//		userInfo = (TmUserinfo) session.getAttribute("userInfo");
//		String orgId = "12344";//TODO
//		ConsultDocForm consultDocForm = new ConsultDocForm();
//		String forword  ="consult_doc/consultDocSecretaryList";
//		if(action!=null){
//			if(action.equals("edit")){
//				logger.info( "ConsultDocController>>consultDocSecretary edit " ); 
//				List list = exciseLawService.findVConsultDocByDocId(BigDecimal.valueOf(Long.valueOf(docId)));
//				TmConsultDoc tmConsultDoc = (TmConsultDoc) list.get(0);
//				TsDocState tsDocState = (TsDocState) list.get(1);	
//				if(tsDocState.getFlowId()==null){
//					VConsultDocList docList = exciseLawService.findVConsultDocListByDocId(Long.valueOf(docId));
//					tsDocState.setFlowId(docList.getFlowId());
//				}
//
//				tsDocState.setDocComment("");
//				TsDocResolution tsDocResolution = exciseLawService.findLastTsDocResolution(Long.parseLong(docId));
//
//				if(tmConsultDoc.getDocRefDate()!=null){
//					consultDocForm.setDocRefDate(DateUtils.getStringDateByDateTh(tmConsultDoc.getDocRefDate().getTime()));
//				}
//				if(tmConsultDoc.getOfficeRecDate()!=null){
//					consultDocForm.setOfficeRecDate(DateUtils.getStringDateByDateTh(tmConsultDoc.getOfficeRecDate().getTime()));
//				}			
//				if(tmConsultDoc.getDocDate()!=null){
//					consultDocForm.setDocDate(DateUtils.getStringDateByDateTh(tmConsultDoc.getDocDate().getTime()));
//				}		
//				if(tmConsultDoc.getBureauRecDate()!=null){
//					consultDocForm.setBureauRecDate(DateUtils.getStringDateByDateTh(tmConsultDoc.getBureauRecDate().getTime()));
//				}
//				if(tmConsultDoc.getSectionRecDate()!=null){
//					consultDocForm.setSectionRecDate(DateUtils.getStringDateByDateTh(tmConsultDoc.getSectionRecDate().getTime()));
//				}
//				if(tmConsultDoc.getDocSendDate()!=null){
//					consultDocForm.setDocSendDate(DateUtils.getStringDateByDateTh(tmConsultDoc.getDocSendDate().getTime()));
//				}
//				if(tmConsultDoc.getBureauSendDate()!=null){
//					consultDocForm.setBureauSendDate(DateUtils.getStringDateByDateTh(tmConsultDoc.getBureauSendDate().getTime()));
//				}
//				if(tmConsultDoc.getSectionSendDate()!=null){
//					consultDocForm.setSectionSendDate(DateUtils.getStringDateByDateTh(tmConsultDoc.getSectionSendDate().getTime()));
//				}
//				if(tmConsultDoc.getCreatedDate()!=null){
//					consultDocForm.setCreatedDate(DateUtils.getStringDateByDateTh(tmConsultDoc.getCreatedDate().getTime()));
//				}
//				
//				if(tmConsultDoc.getDocType().equals("I")){
//					consultDocForm.setInternalOfficeId(tmConsultDoc.getConsultOrg());
//				}else if(tmConsultDoc.getDocType().equals("E")){
//					consultDocForm.setExternalOfficeId(tmConsultDoc.getConsultOrg());
//				}
//				
//				List<TsDocStatue> tsDocStatueList = exciseLawService.findTsDocStatueBydocId(BigDecimal.valueOf(tmConsultDoc.getDocId()));
//				List<String> tsDocStatuesList = new ArrayList<String>();
//				if(tsDocStatueList != null && tsDocStatueList.size()>0){
//					for (TsDocStatue tsDocStatue : tsDocStatueList) {
//						String itemId = tsDocStatue.getStatueId()+"";
//						tsDocStatuesList.add(itemId);
//					}
//				}
//				String[] tsDocStatues = new String[tsDocStatuesList.size()];
//				consultDocForm.setMsStatutesBoxes(tsDocStatuesList.toArray(tsDocStatues));
//				consultDocForm.setTmConsultDoc(tmConsultDoc);
//				consultDocForm.setTsDocState(tsDocState);
//				consultDocForm.setTsDocResolution(tsDocResolution);	
//	    		model.addAttribute("msStatutes", exciseLawService.listMsStatutes(null));
//				model.addAttribute("organizationList", exciseLawService.listOrganization(null));	
//				model.addAttribute("lawyerList", exciseLawService.getLawyerList(orgId));	
//				model.addAttribute("professionalList", exciseLawService.getProfessionalList());	
//				model.addAttribute("tsDocAttachList", exciseLawService.findTsDocAttachListByDocId(Long.parseLong(docId)));	
//				model.addAttribute("vDocHistoryList", exciseLawService.findVDocHistory(Long.parseLong(docId)));
//				model.addAttribute("docCommentList", exciseLawService.findDocCommentList(tmConsultDoc.getDocId()));	
//				model.addAttribute("tsDocResolutionHistoryList", exciseLawService.getTsDocResolutionHistory(Long.parseLong(docId)));	
//				forword="consult_doc/consultDocSecretaryDetail";
//			}
//		}
//		setActionList(consultDocForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID6));
//		consultDocForm.setAction(action);
//		model.addAttribute("statusList", statusList);
//		model.addAttribute("securityLevelList", securityLevelList);
//		model.addAttribute("departmentTypeList", departmentTypeList);
//		model.addAttribute("internalOfficeList", exciseLawService.getInternalOfficeList());
//		model.addAttribute("externalOfficeList", exciseLawService.getExternalOfficeList());
//		model.addAttribute("consultDocForm", consultDocForm); 
//		return forword;		 
//	}
//	
//	@RequestMapping(value="/consult_doc/consultDocSecretary",method = RequestMethod.POST)
//	public String processconsultSecretary(ServletRequest request, ServletResponse response, 
//				@RequestParam(value="action",required = false) String action,
//				@ModelAttribute("consultDocForm") ConsultDocForm consultDocForm,
//				BindingResult result,
//				Model model, SessionStatus status) {
//		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//		HttpSession session = httpServletRequest.getSession();
//		userInfo = (TmUserinfo) session.getAttribute("userInfo");
//		String forword  ="consult_doc/consultDocSecretaryList";
//		TmConsultDoc tmConsultDoc = consultDocForm.getTmConsultDoc();
//		TsDocState tsDocState = consultDocForm.getTsDocState();
//		DateTime nowTh = new DateTime(new Date().getTime());
//
//		if(consultDocForm.getDocRefDate()!=null && consultDocForm.getDocRefDate().length()>0){
//			tmConsultDoc.setDocRefDate(DateUtils.getDateByStringDateTh(consultDocForm.getDocRefDate()));
//		}
//		if(consultDocForm.getOfficeRecDate()!=null && consultDocForm.getOfficeRecDate().length()>0){
//			tmConsultDoc.setOfficeRecDate(DateUtils.getDateByStringDateTh(consultDocForm.getOfficeRecDate()));
//		}
//		if(consultDocForm.getDocDate()!=null && consultDocForm.getDocDate().length()>0){
//			tmConsultDoc.setDocDate(DateUtils.getDateByStringDateTh(consultDocForm.getDocDate()));
//		}
//		if(consultDocForm.getBureauRecDate()!=null && consultDocForm.getBureauRecDate().length()>0){
//			tmConsultDoc.setBureauRecDate(DateUtils.getDateByStringDateTh(consultDocForm.getBureauRecDate()));
//		}
//		if(consultDocForm.getSectionRecDate()!=null && consultDocForm.getSectionRecDate().length()>0){
//			tmConsultDoc.setSectionRecDate(DateUtils.getDateByStringDateTh(consultDocForm.getSectionRecDate()));
//		}
//		if(consultDocForm.getDocSendDate()!=null){
//			tmConsultDoc.setDocSendDate(DateUtils.getDateByStringDateTh(consultDocForm.getDocSendDate()));
//		}
//		if(consultDocForm.getBureauSendDate()!=null){
//			tmConsultDoc.setBureauSendDate(DateUtils.getDateByStringDateTh(consultDocForm.getBureauSendDate()));
//		}
//		if(consultDocForm.getSectionSendDate()!=null){
//			tmConsultDoc.setSectionSendDate(DateUtils.getDateByStringDateTh(consultDocForm.getSectionSendDate()));
//		}
//		if(consultDocForm.getCreatedDate()!=null && consultDocForm.getCreatedDate().length()>0){
//			tmConsultDoc.setCreatedDate(DateUtils.getDateByStringDateTh(consultDocForm.getCreatedDate()));
//		}
//
//		if(action.equals("doEdit")){	
//			logger.info( "ConsultDocController>>consultDocSecretary.POST doEdit "); 
//			tmConsultDoc.setUpdatedBy(userInfo.getUserId());
//			tmConsultDoc.setUpdatedDate(nowTh.toDate());
//			exciseLawService.updateTmConsultDoc(tmConsultDoc);		
//
//			VConsultDoc doc = exciseLawService.findVConsultDocByDocId(tmConsultDoc.getDocId());
//			VConsultDocList docList = exciseLawService.findVConsultDocListByDocId(tmConsultDoc.getDocId());
//			
//			if(doc.getStateId() == docList.getStateId()){
//				tsDocState = setTsDocState(tsDocState, tmConsultDoc.getDocId(), 0, 0, userInfo.getCardNo(), userInfo.getUserId(), nowTh.toDate(), ACTION_ADD);
//				exciseLawService.saveTsDocState(tsDocState);
//			}else{
//				tsDocState = setTsDocState(tsDocState, tmConsultDoc.getDocId(), 0, 0, userInfo.getCardNo(), userInfo.getUserId(), nowTh.toDate(), ACTION_EDIT);
//				exciseLawService.updateTsDocState(tsDocState);
//			}	
//						
//			String [] delAttachFile = consultDocForm.getDeleteFileBoxes();
//			if(delAttachFile!=null && delAttachFile.length>0){
//				TsDocAttach tsDocAttach = null;
//				for(String attachId:delAttachFile){
//					tsDocAttach = new TsDocAttach();
//					tsDocAttach.setAttachId(Long.valueOf(attachId));
//					exciseLawService.deleteDocAttach(tsDocAttach);
//				}
//			}
//
//			MultipartFile[] files = consultDocForm.getFileData();
//			if(files != null && files.length>0){
//	            for(MultipartFile file : files){
//	            	if(file.getOriginalFilename() != null && file.getSize() > 0){
//	            		if(file.getSize() < FILESIZE){
//			            	TsDocAttach tsDocAttach = saveTsDocAttachFile(file, tmConsultDoc.getDocId() , userInfo.getUserId(), nowTh.toDate());
//							exciseLawService.saveTsDocAttach(tsDocAttach);
//	            		}else{
//	            			consultDocForm.setIsSusses("2");//TODO
//	            			break;
//	            		}
//	            	}
//	            }
//			}
//
//			model.addAttribute("tsDocAttachList", exciseLawService.findTsDocAttachListByDocId(tmConsultDoc.getDocId()));
//			model.addAttribute("vDocHistoryList", exciseLawService.findVDocHistory(tmConsultDoc.getDocId()));		
//			consultDocForm.setIsSusses("1"); 
//			forword="consult_doc/consultDocSecretaryDetail";
//		}else if(action.equals("doSend")){
//			logger.info( "ConsultDocController>>consultDocSecretaryDetail.POST doSend "); 
//
//			VConsultDoc doc = exciseLawService.findVConsultDocByDocId(tmConsultDoc.getDocId());
//			VConsultDocList docList = exciseLawService.findVConsultDocListByDocId(tmConsultDoc.getDocId());
//			
//			if(doc.getStateId() == docList.getStateId()){
//				tsDocState = setTsDocState(tsDocState, tmConsultDoc.getDocId(), FLOW_STEP10, STATUS_AGREE, userInfo.getCardNo(), userInfo.getUserId(), nowTh.toDate(), ACTION_ADD);
//				exciseLawService.saveTsDocState(tsDocState);
//			}else{
//				tsDocState = setTsDocState(tsDocState, tmConsultDoc.getDocId(), FLOW_STEP10, STATUS_AGREE, userInfo.getCardNo(), userInfo.getUserId(), nowTh.toDate(), ACTION_EDIT);
//				exciseLawService.updateTsDocState(tsDocState);
//			}
//
//			String [] delAttachFile = consultDocForm.getDeleteFileBoxes();
//			if(delAttachFile!=null && delAttachFile.length>0){
//				TsDocAttach tsDocAttach = null;
//				for(String attachId:delAttachFile){
//					tsDocAttach = new TsDocAttach();
//					tsDocAttach.setAttachId(Long.valueOf(attachId));
//					exciseLawService.deleteDocAttach(tsDocAttach);
//				}
//			}
//
//			MultipartFile[] files = consultDocForm.getFileData();
//			if(files != null && files.length>0){
//	            for(MultipartFile file : files){
//	            	if(file.getOriginalFilename() != null && file.getSize() > 0){
//	            		if(file.getSize() < FILESIZE){
//			            	TsDocAttach tsDocAttach = saveTsDocAttachFile(file, tmConsultDoc.getDocId() , userInfo.getUserId(), nowTh.toDate());
//							exciseLawService.saveTsDocAttach(tsDocAttach);
//	            		}else{
//	            			consultDocForm.setIsSusses("2");//TODO
//	            			break;
//	            		}
//	            	}
//	            }
//			}
//			
//			model.addAttribute("tsDocAttachList", exciseLawService.findTsDocAttachListByDocId(tmConsultDoc.getDocId()));
//			model.addAttribute("vDocHistoryList", exciseLawService.findVDocHistory(tmConsultDoc.getDocId()));
//			consultDocForm.setIsSusses("1");
//			forword="consult_doc/consultDocSecretaryDetail";
//		}
//		setActionList(consultDocForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID6));
//		model.addAttribute("statusList", statusList);
//		model.addAttribute("securityLevelList", securityLevelList);
//		model.addAttribute("departmentTypeList", departmentTypeList);
//		model.addAttribute("internalOfficeList", exciseLawService.getInternalOfficeList());
//		model.addAttribute("externalOfficeList", exciseLawService.getExternalOfficeList());
//		model.addAttribute("consultDocForm", consultDocForm); 
//		return forword;		 
//	}	

}
