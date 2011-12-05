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
import com.excise.law.form.ConsultPublishForm;
import com.excise.law.service.LawService;

@Controller
@SessionAttributes({"consultPublishForm"})
public class ConsultPublishController {
	
	private final com.excise.law.service.LawService lawService;
	private final String SCREEN_ID23 = "23";
	private final String STATUS_INACTIVE = "I";
	private final long FILESIZE = 5000000;
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ConsultPublishController.class);
//	private static ResourceBundle resources = ResourceBundle.getBundle( "file" , Locale.ENGLISH );
	
	private static final String[] PUBLISHDISPLAYTYPE_VALUE_BOXES = new String[]{"1","0"};
	private static final String[] PUBLISHDISPLAYTYPE_LABEL_BOXES = new String[]{"แสดงผล","ไม่แสดงผล"};

	private static List<Display> publishDisplayTypeList = new ArrayList<Display>(2);
	
	static{
		for(int i=0;i<PUBLISHDISPLAYTYPE_VALUE_BOXES.length;i++){
			Display display=new Display(PUBLISHDISPLAYTYPE_VALUE_BOXES[i],PUBLISHDISPLAYTYPE_LABEL_BOXES[i]);
			publishDisplayTypeList.add(display);
		}
	}
	
	@Autowired
	public ConsultPublishController(LawService lawService) {
		this.lawService = lawService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
		dataBinder.setDisallowedFields("id");
	}
	
	@RequestMapping("/consult_publish/consultPublish")
	public String consultPublish(ServletRequest request, ServletResponse response,
				@RequestParam(value="action",required = false) String action,
				@RequestParam(value="publishedId",required = false) String publishedId, Model model) {
		ConsultPublishForm consultPublishForm = new ConsultPublishForm();
//		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//		HttpSession session = httpServletRequest.getSession();
//		userInfo = (TmUserinfo) session.getAttribute("userInfo");
		String forword  ="consult_publish/consultPublishList";
		if(action!=null && action.equals("edit")){
			forword="consult_publish/consultPublishDetail";
		}
		model.addAttribute("publishDisplayTypeList", publishDisplayTypeList);
		model.addAttribute("consultPublishForm",consultPublishForm);
		return forword;		 
	}
	
//	@RequestMapping(value="/consult_publish/consultPublish", method = RequestMethod.POST)
//	public String processConsultPublish(ServletRequest request, ServletResponse response, 
//				@RequestParam(value="action",required = false) String action,
//				@ModelAttribute("consultPublishForm") ConsultPublishForm consultPublishForm,
//				BindingResult result,
//				Model model, SessionStatus status) {
//		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//		HttpSession session = httpServletRequest.getSession();
//		userInfo = (TmUserinfo) session.getAttribute("userInfo");
//		String forword  ="consult_publish/consultPublishList";
//		TsDiscussPublished tsDiscussPublished = consultPublishForm.getTsDiscussPublished();
//		DateTime nowTh = new DateTime(new Date().getTime());
//
//		if(consultPublishForm.getCreatedDate()!=null && consultPublishForm.getCreatedDate().length()>0){
//			tsDiscussPublished.setCreatedDate(DateUtils.getDateByStringDateTh(consultPublishForm.getCreatedDate()));
//		}
//		
//		if(action.equals("doSave")){
//			logger.info( "ConsultDocController>>consultPublish.POST doSave " );
//			tsDiscussPublished.setCreatedBy(userInfo.getUserId());
//			tsDiscussPublished.setCreatedDate(nowTh.toDate());
//			tsDiscussPublished.setUpdatedBy(userInfo.getUserId());
//			tsDiscussPublished.setUpdatedDate(nowTh.toDate());
//
//			Long publishedId = lawService.saveTsDiscussPublished(tsDiscussPublished);
//			
//			String [] msStatutesBoxes = consultPublishForm.getMsStatutesBoxes();
//			if(msStatutesBoxes!=null && msStatutesBoxes.length>0){
//				TsPublishedStatue tsPublishedStatue = null;
//				for(String statueId : msStatutesBoxes){
//					tsPublishedStatue = new TsPublishedStatue();
//					tsPublishedStatue.setPublishedId(BigDecimal.valueOf(publishedId));
//					tsPublishedStatue.setStatueId(BigDecimal.valueOf(Long.valueOf(statueId)));
//					lawService.saveTsPublishedStatue(tsPublishedStatue);
//				}
//			}	
//			
//			String [] dutyGroupGoods = consultPublishForm.getDutyGroupGoodsBoxes();
//			if(dutyGroupGoods!=null && dutyGroupGoods.length>0){
//				TsPublishedGood tsPublishedGood = null;
//				for(String groupId : dutyGroupGoods){
//					tsPublishedGood = new TsPublishedGood();
//					tsPublishedGood.setPublishedId(BigDecimal.valueOf(publishedId));
//					tsPublishedGood.setDutyGoodsId(groupId);
//					lawService.saveTsPublishedGood(tsPublishedGood);
//				}
//			}	
//			
//			String [] dutyGroupServices = consultPublishForm.getDutyGroupServicesBoxes();
//			if(dutyGroupServices!=null && dutyGroupServices.length>0){
//				TsPublishedGood tsPublishedGood = null;
//				for(String groupId : dutyGroupServices){
//					tsPublishedGood = new TsPublishedGood();
//					tsPublishedGood.setPublishedId(BigDecimal.valueOf(publishedId));
//					tsPublishedGood.setDutyGoodsId(groupId);
//					lawService.saveTsPublishedGood(tsPublishedGood);
//				}
//			}
//			
//			MultipartFile[] files = consultPublishForm.getFileData();
//			if(files != null && files.length>0){
//	            for(MultipartFile file : files){
//	            	if(file.getOriginalFilename() !=null && file.getSize() > 0){
//	            		if(file.getSize() < FILESIZE){
//	            			TsPublishedAttach tsPublishedAttach = saveTsPublishedAttachFile(file, publishedId.longValue(), userInfo.getUserId(), nowTh.toDate());
//							lawService.saveTsPublishedAttach(tsPublishedAttach);
//	            		}else{
//	            			consultPublishForm.setIsSusses("2");//TODO
//	            			break;
//	            		}
//	            	}
//	            }	
//			}
//			
//			model.addAttribute("msStatutes", lawService.listMsStatutes(null));
//			model.addAttribute("dutyGroupGoods", lawService.listMviewDutyGroupGoods());
//			model.addAttribute("dutyGroupServices", lawService.listMviewDutyGroupServices());
//			consultPublishForm.setIsSusses("1");
//			forword="consult_publish/consultPublishDetail";
//		}else if(action.equals("doEdit")){		
//			logger.info( "ConsultDocController>>consultPublish.POST doEdit " );
//			tsDiscussPublished.setUpdatedBy(userInfo.getUserId());
//			tsDiscussPublished.setUpdatedDate(nowTh.toDate());
//			lawService.updateTsDiscussPublished(tsDiscussPublished);
//
//			List<TsPublishedStatue> tsPublishedStatueList = lawService.findTsPublishedStatueByPublishedId(BigDecimal.valueOf(tsDiscussPublished.getPublishedId()));
//			if(tsPublishedStatueList != null && tsPublishedStatueList.size()>0){
//				for(TsPublishedStatue publishedStatue : tsPublishedStatueList){
//					lawService.deleteTsPublishedStatue(publishedStatue);
//				}
//			}
//			
//			String [] msStatutesBoxes = consultPublishForm.getMsStatutesBoxes();
//			if(msStatutesBoxes!=null && msStatutesBoxes.length>0){
//				TsPublishedStatue tsPublishedStatue = null;
//				for(String statueId : msStatutesBoxes){
//					tsPublishedStatue = new TsPublishedStatue();
//					tsPublishedStatue.setPublishedId(BigDecimal.valueOf(tsDiscussPublished.getPublishedId()));
//					tsPublishedStatue.setStatueId(BigDecimal.valueOf(Long.valueOf(statueId)));
//					lawService.saveTsPublishedStatue(tsPublishedStatue);
//				}
//			}	
//
//			List<TsPublishedGood> tsPublishedGoodList = lawService.findTsPublishedGoodByPublishedId(BigDecimal.valueOf(tsDiscussPublished.getPublishedId()));
//			if(tsPublishedGoodList != null && tsPublishedGoodList.size()>0){
//				for(TsPublishedGood publishedGood : tsPublishedGoodList){
//					lawService.deleteTsPublishedGood(publishedGood);
//				}
//			}			
//
//			String [] dutyGroupGoods = consultPublishForm.getDutyGroupGoodsBoxes();
//			if(dutyGroupGoods!=null && dutyGroupGoods.length>0){
//				TsPublishedGood tsPublishedGood = null;
//				for(String groupId : dutyGroupGoods){
//					tsPublishedGood = new TsPublishedGood();
//					tsPublishedGood.setPublishedId(BigDecimal.valueOf(tsDiscussPublished.getPublishedId()));
//					tsPublishedGood.setDutyGoodsId(groupId);
//					lawService.saveTsPublishedGood(tsPublishedGood);
//				}
//			}	
//			
//			String [] dutyGroupServices = consultPublishForm.getDutyGroupServicesBoxes();
//			if(dutyGroupServices!=null && dutyGroupServices.length>0){
//				TsPublishedGood tsPublishedGood = null;
//				for(String groupId : dutyGroupServices){
//					tsPublishedGood = new TsPublishedGood();
//					tsPublishedGood.setPublishedId(BigDecimal.valueOf(tsDiscussPublished.getPublishedId()));
//					tsPublishedGood.setDutyGoodsId(groupId);
//					lawService.saveTsPublishedGood(tsPublishedGood);
//				}
//			}
//
//			String [] delAttachFile = consultPublishForm.getDeleteFileBoxes();
//			if(delAttachFile!=null && delAttachFile.length>0){
//				TsPublishedAttach tsPublishedAttach = null;
//				for(String attachId : delAttachFile){
//					tsPublishedAttach = new TsPublishedAttach();
//					tsPublishedAttach.setPublishedAttachId(Long.valueOf(attachId));
//					lawService.deleteTsPublishedAttach(tsPublishedAttach);
//				}
//			}
//			
//			MultipartFile[] files = consultPublishForm.getFileData();
//			if(files != null && files.length>0){
//	            for(MultipartFile file : files){
//	            	if(file.getOriginalFilename() !=null && file.getSize() > 0){
//	            		if(file.getSize() < FILESIZE){
//	            			TsPublishedAttach tsPublishedAttach = saveTsPublishedAttachFile(file, tsDiscussPublished.getPublishedId(), userInfo.getUserId(), nowTh.toDate());
//							lawService.saveTsPublishedAttach(tsPublishedAttach);
//	            		}else{
//	            			consultPublishForm.setIsSusses("2");//TODO
//	            			break;
//	            		}
//	            	}
//	            }	
//			}
//
//			model.addAttribute("msStatutes", lawService.listMsStatutes(null));
//			model.addAttribute("dutyGroupGoods", lawService.listMviewDutyGroupGoods());
//			model.addAttribute("dutyGroupServices", lawService.listMviewDutyGroupServices());
//			model.addAttribute("tsDiscussPublishedAttachList", lawService.findTsPublishedAttachListByPublishId(tsDiscussPublished.getPublishedId()));
//			consultPublishForm.setIsSusses("1"); 
//			forword="consult_publish/consultPublishDetail";
//		}else if(action.equals("doDelete")){			
//			logger.info( "ConsultDocController>>consultPublish.POST doDelete " );
//			tsDiscussPublished.setPublishedStatus(STATUS_INACTIVE);
//			lawService.updateTsDiscussPublished(tsDiscussPublished);
//			consultPublishForm = new ConsultPublishForm();
//			consultPublishForm.setAction(action);
//			consultPublishForm.setIsSusses("1");
//			forword="consult_publish/consultPublishList";
//		}
//		setActionList(consultPublishForm, lawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID23));
//		model.addAttribute("publishDisplayTypeList", publishDisplayTypeList);
//		model.addAttribute("consultPublishForm",consultPublishForm);
//		return forword;		 
//	}	
}
