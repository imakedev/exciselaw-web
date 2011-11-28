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
import com.exciselaw.law.domain.TsDiscussPublished;
import com.exciselaw.law.domain.TsPublishedAttach;
import com.exciselaw.law.domain.TsPublishedGood;
import com.exciselaw.law.domain.TsPublishedStatue;
import com.exciselaw.law.form.ConsultPublishForm;
import com.exciselaw.law.service.ExciseLawService;
import com.exciselaw.law.utils.DateUtils;

@Controller
@SessionAttributes({"consultPublishForm"})
public class ConsultPublishController {
	
	private final ExciseLawService exciseLawService;
	private final String SCREEN_ID23 = "23";
	private final String STATUS_INACTIVE = "I";
	private final long FILESIZE = 5000000;
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ConsultPublishController.class);
	private static ResourceBundle resources = ResourceBundle.getBundle( "file" , Locale.ENGLISH );
	
	private TmUserinfo userInfo = null;
	
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
	public ConsultPublishController(ExciseLawService exciseLawService) {
		this.exciseLawService = exciseLawService;
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
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		userInfo = (TmUserinfo) session.getAttribute("userInfo");
		String forword  ="consult_publish/consultPublishList";
		if(action!=null){
			if(action.equals("add")){			
				logger.info( "ConsultDocController>>consultPublish add " );	
				consultPublishForm = new ConsultPublishForm();
				model.addAttribute("msStatutes", exciseLawService.listMsStatutes(null));
				model.addAttribute("dutyGroupGoods", exciseLawService.listMviewDutyGroupGoods());
				model.addAttribute("dutyGroupServices", exciseLawService.listMviewDutyGroupServices());
				forword="consult_publish/consultPublishDetail";
			}else if(action.equals("edit")){
				logger.info( "ConsultDocController>>consultPublish edit " ); 	
				TsDiscussPublished tsDiscussPublished = exciseLawService.findTsDiscussPublishedByPublishId(Long.valueOf(publishedId));

				if(tsDiscussPublished.getCreatedDate()!=null){
					consultPublishForm.setCreatedDate(DateUtils.getStringDateByDateTh(tsDiscussPublished.getCreatedDate().getTime()));
				}	
				
				if(tsDiscussPublished.getLawInvolved()!=null){
					consultPublishForm.setLawInvolved(tsDiscussPublished.getLawInvolved());
				}
				
				if(tsDiscussPublished.getPublishedDetails()!=null){
					consultPublishForm.setPublishedDetails(tsDiscussPublished.getPublishedDetails());
				}

				List<TsPublishedStatue> tsPublishedStatueList = exciseLawService.findTsPublishedStatueByPublishedId(BigDecimal.valueOf(Long.valueOf(publishedId)));
				List<String> tsPublishedStatuesList = new ArrayList<String>();
				if(tsPublishedStatueList != null && tsPublishedStatueList.size()>0){
					for (TsPublishedStatue tsPublishedStatue : tsPublishedStatueList) {
						String itemId = tsPublishedStatue.getStatueId()+"";
						tsPublishedStatuesList.add(itemId);
					}
				}
				String[] tsPublishedStatues = new String[tsPublishedStatuesList.size()];
				consultPublishForm.setMsStatutesBoxes(tsPublishedStatuesList.toArray(tsPublishedStatues));
				
				List<TsPublishedGood> tsPublishedGoodList = exciseLawService.findTsPublishedGoodByPublishedId(BigDecimal.valueOf(Long.valueOf(publishedId)));
				List<String> tsPublishedGoodsList = new ArrayList<String>();
				if(tsPublishedGoodList != null && tsPublishedGoodList.size()>0){
					for (TsPublishedGood tsPublishedGood : tsPublishedGoodList) {
						String itemId = tsPublishedGood.getDutyGoodsId()+"";
						tsPublishedGoodsList.add(itemId);
					}
				}
				String[] tsPublishedGoods = new String[tsPublishedGoodsList.size()];
				consultPublishForm.setDutyGroupGoodsBoxes(tsPublishedGoodsList.toArray(tsPublishedGoods));
				consultPublishForm.setDutyGroupServicesBoxes(tsPublishedGoodsList.toArray(tsPublishedGoods));

				model.addAttribute("msStatutes", exciseLawService.listMsStatutes(null));
				model.addAttribute("dutyGroupGoods", exciseLawService.listMviewDutyGroupGoods());
				model.addAttribute("dutyGroupServices", exciseLawService.listMviewDutyGroupServices());
				model.addAttribute("tsDiscussPublishedAttachList", exciseLawService.findTsPublishedAttachListByPublishId(Long.parseLong(publishedId)));
				consultPublishForm.setTsDiscussPublished(tsDiscussPublished);
				forword="consult_publish/consultPublishDetail";
			}
		} 
		setActionList(consultPublishForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID23));
		consultPublishForm.setAction(action);
		model.addAttribute("publishDisplayTypeList", publishDisplayTypeList);
		model.addAttribute("consultPublishForm", consultPublishForm); 
		return forword;		 
	}
	
	@RequestMapping(value="/consult_publish/consultPublish", method = RequestMethod.POST)
	public String processConsultPublish(ServletRequest request, ServletResponse response, 
				@RequestParam(value="action",required = false) String action,
				@ModelAttribute("consultPublishForm") ConsultPublishForm consultPublishForm,
				BindingResult result,
				Model model, SessionStatus status) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		userInfo = (TmUserinfo) session.getAttribute("userInfo");
		String forword  ="consult_publish/consultPublishList";
		TsDiscussPublished tsDiscussPublished = consultPublishForm.getTsDiscussPublished();
		DateTime nowTh = new DateTime(new Date().getTime());

		if(consultPublishForm.getCreatedDate()!=null && consultPublishForm.getCreatedDate().length()>0){
			tsDiscussPublished.setCreatedDate(DateUtils.getDateByStringDateTh(consultPublishForm.getCreatedDate()));
		}
		
		if(action.equals("doSave")){
			logger.info( "ConsultDocController>>consultPublish.POST doSave " );
			tsDiscussPublished.setCreatedBy(userInfo.getUserId());
			tsDiscussPublished.setCreatedDate(nowTh.toDate());
			tsDiscussPublished.setUpdatedBy(userInfo.getUserId());
			tsDiscussPublished.setUpdatedDate(nowTh.toDate());

			Long publishedId = exciseLawService.saveTsDiscussPublished(tsDiscussPublished);
			
			String [] msStatutesBoxes = consultPublishForm.getMsStatutesBoxes();
			if(msStatutesBoxes!=null && msStatutesBoxes.length>0){
				TsPublishedStatue tsPublishedStatue = null;
				for(String statueId : msStatutesBoxes){
					tsPublishedStatue = new TsPublishedStatue();
					tsPublishedStatue.setPublishedId(BigDecimal.valueOf(publishedId));
					tsPublishedStatue.setStatueId(BigDecimal.valueOf(Long.valueOf(statueId)));
					exciseLawService.saveTsPublishedStatue(tsPublishedStatue);
				}
			}	
			
			String [] dutyGroupGoods = consultPublishForm.getDutyGroupGoodsBoxes();
			if(dutyGroupGoods!=null && dutyGroupGoods.length>0){
				TsPublishedGood tsPublishedGood = null;
				for(String groupId : dutyGroupGoods){
					tsPublishedGood = new TsPublishedGood();
					tsPublishedGood.setPublishedId(BigDecimal.valueOf(publishedId));
					tsPublishedGood.setDutyGoodsId(groupId);
					exciseLawService.saveTsPublishedGood(tsPublishedGood);
				}
			}	
			
			String [] dutyGroupServices = consultPublishForm.getDutyGroupServicesBoxes();
			if(dutyGroupServices!=null && dutyGroupServices.length>0){
				TsPublishedGood tsPublishedGood = null;
				for(String groupId : dutyGroupServices){
					tsPublishedGood = new TsPublishedGood();
					tsPublishedGood.setPublishedId(BigDecimal.valueOf(publishedId));
					tsPublishedGood.setDutyGoodsId(groupId);
					exciseLawService.saveTsPublishedGood(tsPublishedGood);
				}
			}
			
			MultipartFile[] files = consultPublishForm.getFileData();
			if(files != null && files.length>0){
	            for(MultipartFile file : files){
	            	if(file.getOriginalFilename() !=null && file.getSize() > 0){
	            		if(file.getSize() < FILESIZE){
	            			TsPublishedAttach tsPublishedAttach = saveTsPublishedAttachFile(file, publishedId.longValue(), userInfo.getUserId(), nowTh.toDate());
							exciseLawService.saveTsPublishedAttach(tsPublishedAttach);
	            		}else{
	            			consultPublishForm.setIsSusses("2");//TODO
	            			break;
	            		}
	            	}
	            }	
			}
			
			model.addAttribute("msStatutes", exciseLawService.listMsStatutes(null));
			model.addAttribute("dutyGroupGoods", exciseLawService.listMviewDutyGroupGoods());
			model.addAttribute("dutyGroupServices", exciseLawService.listMviewDutyGroupServices());
			consultPublishForm.setIsSusses("1");
			forword="consult_publish/consultPublishDetail";
		}else if(action.equals("doEdit")){		
			logger.info( "ConsultDocController>>consultPublish.POST doEdit " );
			tsDiscussPublished.setUpdatedBy(userInfo.getUserId());
			tsDiscussPublished.setUpdatedDate(nowTh.toDate());
			exciseLawService.updateTsDiscussPublished(tsDiscussPublished);

			List<TsPublishedStatue> tsPublishedStatueList = exciseLawService.findTsPublishedStatueByPublishedId(BigDecimal.valueOf(tsDiscussPublished.getPublishedId()));
			if(tsPublishedStatueList != null && tsPublishedStatueList.size()>0){
				for(TsPublishedStatue publishedStatue : tsPublishedStatueList){
					exciseLawService.deleteTsPublishedStatue(publishedStatue);
				}
			}
			
			String [] msStatutesBoxes = consultPublishForm.getMsStatutesBoxes();
			if(msStatutesBoxes!=null && msStatutesBoxes.length>0){
				TsPublishedStatue tsPublishedStatue = null;
				for(String statueId : msStatutesBoxes){
					tsPublishedStatue = new TsPublishedStatue();
					tsPublishedStatue.setPublishedId(BigDecimal.valueOf(tsDiscussPublished.getPublishedId()));
					tsPublishedStatue.setStatueId(BigDecimal.valueOf(Long.valueOf(statueId)));
					exciseLawService.saveTsPublishedStatue(tsPublishedStatue);
				}
			}	

			List<TsPublishedGood> tsPublishedGoodList = exciseLawService.findTsPublishedGoodByPublishedId(BigDecimal.valueOf(tsDiscussPublished.getPublishedId()));
			if(tsPublishedGoodList != null && tsPublishedGoodList.size()>0){
				for(TsPublishedGood publishedGood : tsPublishedGoodList){
					exciseLawService.deleteTsPublishedGood(publishedGood);
				}
			}			

			String [] dutyGroupGoods = consultPublishForm.getDutyGroupGoodsBoxes();
			if(dutyGroupGoods!=null && dutyGroupGoods.length>0){
				TsPublishedGood tsPublishedGood = null;
				for(String groupId : dutyGroupGoods){
					tsPublishedGood = new TsPublishedGood();
					tsPublishedGood.setPublishedId(BigDecimal.valueOf(tsDiscussPublished.getPublishedId()));
					tsPublishedGood.setDutyGoodsId(groupId);
					exciseLawService.saveTsPublishedGood(tsPublishedGood);
				}
			}	
			
			String [] dutyGroupServices = consultPublishForm.getDutyGroupServicesBoxes();
			if(dutyGroupServices!=null && dutyGroupServices.length>0){
				TsPublishedGood tsPublishedGood = null;
				for(String groupId : dutyGroupServices){
					tsPublishedGood = new TsPublishedGood();
					tsPublishedGood.setPublishedId(BigDecimal.valueOf(tsDiscussPublished.getPublishedId()));
					tsPublishedGood.setDutyGoodsId(groupId);
					exciseLawService.saveTsPublishedGood(tsPublishedGood);
				}
			}

			String [] delAttachFile = consultPublishForm.getDeleteFileBoxes();
			if(delAttachFile!=null && delAttachFile.length>0){
				TsPublishedAttach tsPublishedAttach = null;
				for(String attachId : delAttachFile){
					tsPublishedAttach = new TsPublishedAttach();
					tsPublishedAttach.setPublishedAttachId(Long.valueOf(attachId));
					exciseLawService.deleteTsPublishedAttach(tsPublishedAttach);
				}
			}
			
			MultipartFile[] files = consultPublishForm.getFileData();
			if(files != null && files.length>0){
	            for(MultipartFile file : files){
	            	if(file.getOriginalFilename() !=null && file.getSize() > 0){
	            		if(file.getSize() < FILESIZE){
	            			TsPublishedAttach tsPublishedAttach = saveTsPublishedAttachFile(file, tsDiscussPublished.getPublishedId(), userInfo.getUserId(), nowTh.toDate());
							exciseLawService.saveTsPublishedAttach(tsPublishedAttach);
	            		}else{
	            			consultPublishForm.setIsSusses("2");//TODO
	            			break;
	            		}
	            	}
	            }	
			}

			model.addAttribute("msStatutes", exciseLawService.listMsStatutes(null));
			model.addAttribute("dutyGroupGoods", exciseLawService.listMviewDutyGroupGoods());
			model.addAttribute("dutyGroupServices", exciseLawService.listMviewDutyGroupServices());
			model.addAttribute("tsDiscussPublishedAttachList", exciseLawService.findTsPublishedAttachListByPublishId(tsDiscussPublished.getPublishedId()));
			consultPublishForm.setIsSusses("1"); 
			forword="consult_publish/consultPublishDetail";
		}else if(action.equals("doDelete")){			
			logger.info( "ConsultDocController>>consultPublish.POST doDelete " );
			tsDiscussPublished.setPublishedStatus(STATUS_INACTIVE);
			exciseLawService.updateTsDiscussPublished(tsDiscussPublished);
			consultPublishForm = new ConsultPublishForm();
			consultPublishForm.setAction(action);
			consultPublishForm.setIsSusses("1");
			forword="consult_publish/consultPublishList";
		}
		setActionList(consultPublishForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID23));
		model.addAttribute("publishDisplayTypeList", publishDisplayTypeList);
		model.addAttribute("consultPublishForm",consultPublishForm);
		return forword;		 
	}	
	
	private void setActionList(ConsultPublishForm consultPublishForm, String actionList){
		if(actionList.indexOf("ADD") != -1){
			consultPublishForm.setAdd("true");
		}
		if(actionList.indexOf("EDIT") != -1){
			consultPublishForm.setEdit("true");
		}
		if(actionList.indexOf("DELETE") != -1){
			consultPublishForm.setDelete("true");
		}
		if(actionList.indexOf("VIEW") != -1){
			consultPublishForm.setView("true");
		}		
	}
	
	private TsPublishedAttach saveTsPublishedAttachFile(MultipartFile fileUpload, long publishedId, String userId, Date today){
		TsPublishedAttach tsPublishedAttach = new TsPublishedAttach();
		FileOutputStream fos = null;
		if (fileUpload.getSize() > 0) {
            try {
		        byte[] bytes = fileUpload.getBytes();		 

            	String filePath = resources.getString("consultpublish_path")+"/"+publishedId;
            	File fileDir = new File(filePath);
            	if(!fileDir.exists()){
            		fileDir.mkdirs();
            	}
            	File file = new File(fileDir, fileUpload.getOriginalFilename());
		        fos = new FileOutputStream(file);
		        fos.write(bytes);
		        fos.flush();
		        fos.close();
		        
		        tsPublishedAttach.setFileName(fileUpload.getOriginalFilename());
		        tsPublishedAttach.setFilePath(filePath);
		        tsPublishedAttach.setDiscussPublishedId(BigDecimal.valueOf(publishedId));
		        tsPublishedAttach.setCreatedBy(userInfo.getUserId());
		        tsPublishedAttach.setUpdatedBy(userInfo.getUserId());
		        tsPublishedAttach.setCreatedDate(today);
		        tsPublishedAttach.setUpdatedDate(today);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try { fos.close(); } catch (IOException e) { e.printStackTrace(); }
			}
	    }
		return tsPublishedAttach;
	}
}
