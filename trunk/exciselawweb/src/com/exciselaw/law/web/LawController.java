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
import com.exciselaw.law.domain.Status;
import com.exciselaw.law.domain.TmArticleGroup;
import com.exciselaw.law.domain.TmArticleSection;
import com.exciselaw.law.domain.TmLawType;
import com.exciselaw.law.domain.TmStatue;
import com.exciselaw.law.domain.TmUserinfo;
import com.exciselaw.law.domain.TsArticle;
import com.exciselaw.law.domain.TsExArticleCompleted;
import com.exciselaw.law.domain.TsExArticleHeader;
import com.exciselaw.law.domain.TsExArticleHeaderAttach;
import com.exciselaw.law.domain.TsLaw;
import com.exciselaw.law.domain.TsLawAttach;
import com.exciselaw.law.domain.TsLawGood;
import com.exciselaw.law.domain.TsLawStatue;
import com.exciselaw.law.domain.TsStatueAttach;
import com.exciselaw.law.domain.TsStatueGood;
import com.exciselaw.law.domain.TsTariff;
import com.exciselaw.law.form.ExciseLawForm;
import com.exciselaw.law.form.MasterLawForm;
import com.exciselaw.law.service.ExciseLawService;
import com.exciselaw.law.utils.DateUtils;

@Controller
@SessionAttributes({"masterLawForm","exciseLawForm"})
public class LawController {
	
	private final ExciseLawService exciseLawService;
	private final String SCREEN_ID15 = "15";
	private final String SCREEN_ID16 = "16";
	private final String SCREEN_ID17 = "17";
	private final String SCREEN_ID18 = "18";
	private final String STATUS_INACTIVE = "I";
	private final long FILESIZE = 5000000;
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ConsultPublishController.class);
	private static ResourceBundle resources = ResourceBundle.getBundle( "file" , Locale.ENGLISH );
	
	private TmUserinfo userInfo = null;
	private BigDecimal otherLawType = new BigDecimal("0");
	private BigDecimal exciseLawType = new BigDecimal("1");
	private static final String[] LAW_TYPE_VALUE_BOXES = new String[]{"E","O"};
	private static final String[] LAW_TYPE_LABEL_BOXES = new String[]{"กฎหมายสรรพสามิต","กฎหมายอื่น"};
	private static final String[] STATUS_VALUE_BOXES = new String[]{"A","I"};
	private static final String[] STATUS_LABEL_BOXES = new String[]{"มีผลบังคับใช้","ไม่มีผลบังคับใช้"};
	private static final String[] DISPLAY_VALUE_BOXES = new String[]{"1","0"};
	private static final String[] DISPLAY_LABEL_BOXES = new String[]{"แสดงผล","ไม่แสดงผล"};
	private static List<Status> lawTypeList=new ArrayList<Status>(2);
	private static List<Status> statusList=new ArrayList<Status>(2);
	private static List<Display> displayList=new ArrayList<Display>(2);
	
	static{
		for(int i=0;i<LAW_TYPE_VALUE_BOXES.length;i++){
			Status status=new Status(LAW_TYPE_VALUE_BOXES[i],LAW_TYPE_LABEL_BOXES[i]);
			lawTypeList.add(status);
		}
		for(int i=0;i<STATUS_VALUE_BOXES.length;i++){
			Status status=new Status(STATUS_VALUE_BOXES[i],STATUS_LABEL_BOXES[i]);
			statusList.add(status);
		}
		for(int i=0;i<DISPLAY_VALUE_BOXES.length;i++){
			Display display=new Display(DISPLAY_VALUE_BOXES[i],DISPLAY_LABEL_BOXES[i]);
			displayList.add(display);
		}
	}
	
	@Autowired
	public LawController(ExciseLawService exciseLawService) {
		this.exciseLawService = exciseLawService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
//		System.out.println( "LawController>>setAllowedFields" );
		dataBinder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
		dataBinder.setDisallowedFields("id");
	}
	
	@RequestMapping("/law/statue")
	public String statue(ServletRequest request, ServletResponse response,
				@RequestParam(value="action",required = false) String action,
				@RequestParam(value="statueId",required = false) String statueId, Model model) {
		MasterLawForm masterLawForm = new MasterLawForm();
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		userInfo = (TmUserinfo) session.getAttribute("userInfo");
		String forword  ="/law/statueList";
		if(action!=null){
			if(action.equals("add")){			
				logger.info( "LawController>>statue add " );	
				masterLawForm = new MasterLawForm();
				model.addAttribute("dutyGroupGoods", exciseLawService.listMviewDutyGroupGoods());
				model.addAttribute("dutyGroupServices", exciseLawService.listMviewDutyGroupServices());
				forword="/law/statueDetail";
			}else if(action.equals("edit")){
				logger.info( "LawController>>statue edit " ); 	
				TmStatue tmStatue = exciseLawService.findTmStatueByStatueId(Long.valueOf(statueId));

				if(tmStatue.getCreatedDate()!=null){
					masterLawForm.setCreatedDate(DateUtils.getStringDateByDateTh(tmStatue.getCreatedDate().getTime()));
				}	
				
				List<TsStatueGood> tsStatueGoodList = exciseLawService.findTsStatueGoodByStatueId(BigDecimal.valueOf(Long.valueOf(statueId)));
				List<String> tsStatueGoodsList = new ArrayList<String>();
				if(tsStatueGoodList != null && tsStatueGoodList.size()>0){
					for (TsStatueGood tsStatueGood : tsStatueGoodList) {
						String itemId = tsStatueGood.getDutyGroupId()+"";
						tsStatueGoodsList.add(itemId);
					}
				}
				String[] tsStatueGoods = new String[tsStatueGoodsList.size()];
				masterLawForm.setDutyGroupGoodsBoxes(tsStatueGoodsList.toArray(tsStatueGoods));
				masterLawForm.setDutyGroupServicesBoxes(tsStatueGoodsList.toArray(tsStatueGoods));

				model.addAttribute("dutyGroupGoods", exciseLawService.listMviewDutyGroupGoods());
				model.addAttribute("dutyGroupServices", exciseLawService.listMviewDutyGroupServices());
				model.addAttribute("tsStatueAttachList", exciseLawService.findTsStatueAttachListByStatueId(Long.parseLong(statueId)));
				masterLawForm.setTmStatue(tmStatue);
				forword="/law/statueDetail";
			}
		} 
		setMasterLawActionList(masterLawForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID15));
		masterLawForm.setAction(action);
		model.addAttribute("lawTypeList", lawTypeList);
		model.addAttribute("statusList", statusList);
		model.addAttribute("masterLawForm", masterLawForm); 
		return forword;		 
	}
	
	@RequestMapping(value="/law/statue", method = RequestMethod.POST)
	public String processStatue(ServletRequest request, ServletResponse response, 
				@RequestParam(value="action",required = false) String action,
				@ModelAttribute("masterLawForm") MasterLawForm masterLawForm,
				BindingResult result,
				Model model, SessionStatus status) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		userInfo = (TmUserinfo) session.getAttribute("userInfo");
		String forword  ="/law/statueList";
		TmStatue tmStatue = masterLawForm.getTmStatue();
		DateTime nowTh = new DateTime(new Date().getTime());

		if(masterLawForm.getCreatedDate()!=null && masterLawForm.getCreatedDate().length()>0){
			tmStatue.setCreatedDate(DateUtils.getDateByStringDateTh(masterLawForm.getCreatedDate()));
		}
		
		if(action.equals("doSave")){
			logger.info( "LawController>>statue.POST doSave " );
			tmStatue.setCreatedBy(userInfo.getUserId());
			tmStatue.setCreatedDate(nowTh.toDate());
			tmStatue.setUpdatedBy(userInfo.getUserId());
			tmStatue.setUpdatedDate(nowTh.toDate());

			Long statueId = exciseLawService.saveTmStatue(tmStatue);
			
			String [] dutyGroupGoods = masterLawForm.getDutyGroupGoodsBoxes();
			if(dutyGroupGoods!=null && dutyGroupGoods.length>0){
				TsStatueGood tsStatueGood = null;
				for(String groupId : dutyGroupGoods){
					tsStatueGood = new TsStatueGood();
					tsStatueGood.setStatueId(BigDecimal.valueOf(statueId));
					tsStatueGood.setDutyGroupId(groupId);
					exciseLawService.saveTsStatueGood(tsStatueGood);
				}
			}	
			
			String [] dutyGroupServices = masterLawForm.getDutyGroupServicesBoxes();
			if(dutyGroupServices!=null && dutyGroupServices.length>0){
				TsStatueGood tsStatueGood = null;
				for(String groupId : dutyGroupServices){
					tsStatueGood = new TsStatueGood();
					tsStatueGood.setStatueId(BigDecimal.valueOf(statueId));
					tsStatueGood.setDutyGroupId(groupId);
					exciseLawService.saveTsStatueGood(tsStatueGood);
				}
			}
			
			MultipartFile[] files = masterLawForm.getFileData();
			if(files != null && files.length>0){
	            for(MultipartFile file : files){
	            	if(file.getOriginalFilename() !=null && file.getSize() > 0){
	            		if(file.getSize() < FILESIZE){
	            			TsStatueAttach tsStatueAttach = saveTsStatueAttachFile(file, statueId.longValue(), userInfo.getUserId(), nowTh.toDate());
							exciseLawService.saveTsStatueAttach(tsStatueAttach);
	            		}else{
	            			masterLawForm.setIsSusses("2");//TODO
	            			break;
	            		}
	            	}
	            }	
			}
			
			model.addAttribute("dutyGroupGoods", exciseLawService.listMviewDutyGroupGoods());
			model.addAttribute("dutyGroupServices", exciseLawService.listMviewDutyGroupServices());
			masterLawForm.setIsSusses("1");
			forword="/law/statueDetail";
		}else if(action.equals("doEdit")){		
			logger.info( "LawController>>statue.POST doEdit " );
			tmStatue.setUpdatedBy(userInfo.getUserId());
			tmStatue.setUpdatedDate(nowTh.toDate());
			exciseLawService.updateTmStatue(tmStatue);

			List<TsStatueGood> tsStatueGoodList = exciseLawService.findTsStatueGoodByStatueId(BigDecimal.valueOf(tmStatue.getStatueId()));
			if(tsStatueGoodList != null && tsStatueGoodList.size()>0){
				for(TsStatueGood publishedGood : tsStatueGoodList){
					exciseLawService.deleteTsStatueGood(publishedGood);
				}
			}			

			String [] dutyGroupGoods = masterLawForm.getDutyGroupGoodsBoxes();
			if(dutyGroupGoods!=null && dutyGroupGoods.length>0){
				TsStatueGood tsStatueGood = null;
				for(String groupId : dutyGroupGoods){
					tsStatueGood = new TsStatueGood();
					tsStatueGood.setStatueId(BigDecimal.valueOf(tmStatue.getStatueId()));
					tsStatueGood.setDutyGroupId(groupId);
					exciseLawService.saveTsStatueGood(tsStatueGood);
				}
			}	
			
			String [] dutyGroupServices = masterLawForm.getDutyGroupServicesBoxes();
			if(dutyGroupServices!=null && dutyGroupServices.length>0){
				TsStatueGood tsStatueGood = null;
				for(String groupId : dutyGroupServices){
					tsStatueGood = new TsStatueGood();
					tsStatueGood.setStatueId(BigDecimal.valueOf(tmStatue.getStatueId()));
					tsStatueGood.setDutyGroupId(groupId);
					exciseLawService.saveTsStatueGood(tsStatueGood);
				}
			}

			String [] delAttachFile = masterLawForm.getDeleteFileBoxes();
			if(delAttachFile!=null && delAttachFile.length>0){
				TsStatueAttach tsStatueAttach = null;
				for(String attachId : delAttachFile){
					tsStatueAttach = new TsStatueAttach();
					tsStatueAttach.setStatueAttachId(Long.valueOf(attachId));
					exciseLawService.deleteTsStatueAttach(tsStatueAttach);
				}
			}
			
			MultipartFile[] files = masterLawForm.getFileData();
			if(files != null && files.length>0){
	            for(MultipartFile file : files){
	            	if(file.getOriginalFilename() !=null && file.getSize() > 0){
	            		if(file.getSize() < FILESIZE){
	            			TsStatueAttach tsStatueAttach = saveTsStatueAttachFile(file, tmStatue.getStatueId(), userInfo.getUserId(), nowTh.toDate());
							exciseLawService.saveTsStatueAttach(tsStatueAttach);
	            		}else{
	            			masterLawForm.setIsSusses("2");//TODO
	            			break;
	            		}
	            	}
	            }	
			}

			model.addAttribute("dutyGroupGoods", exciseLawService.listMviewDutyGroupGoods());
			model.addAttribute("dutyGroupServices", exciseLawService.listMviewDutyGroupServices());
			model.addAttribute("tsStatueAttachList", exciseLawService.findTsStatueAttachListByStatueId(tmStatue.getStatueId()));
			masterLawForm.setIsSusses("1"); 
			forword="/law/statueDetail";
		}else if(action.equals("doDelete")){			
			logger.info( "LawController>>statue.POST doDelete " );
			tmStatue.setStatueStatus(STATUS_INACTIVE);
			exciseLawService.updateTmStatue(tmStatue);
			masterLawForm = new MasterLawForm();
			masterLawForm.setAction(action);
			masterLawForm.setIsSusses("1");
			forword="/law/statueList";
		}
		setMasterLawActionList(masterLawForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID15));
		model.addAttribute("lawTypeList", lawTypeList);
		model.addAttribute("statusList", statusList);
		model.addAttribute("masterLawForm",masterLawForm);
		return forword;		 
	}	
	
	@RequestMapping("/law/articleGroup")
	public String articleGroup(ServletRequest request, ServletResponse response,
				@RequestParam(value="action",required = false) String action,
				@RequestParam(value="articleGroupId",required = false) String articleGroupId, Model model) {
		MasterLawForm masterLawForm = new MasterLawForm();
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		userInfo = (TmUserinfo) session.getAttribute("userInfo");
		String forword  ="/law/articleGroupList";
		if(action!=null){
			if(action.equals("add")){			
				logger.info( "LawController>>articleGroup add " );	
				masterLawForm = new MasterLawForm();
				forword="/law/articleGroupDetail";
			}else if(action.equals("edit")){
				logger.info( "LawController>>articleGroup edit " ); 	
				TmArticleGroup tmArticleGroup = exciseLawService.findTmArticleGroupByArticleGroupId(Long.valueOf(articleGroupId));

				if(tmArticleGroup.getCreatedDate()!=null){
					masterLawForm.setCreatedDate(DateUtils.getStringDateByDateTh(tmArticleGroup.getCreatedDate().getTime()));
				}	
				masterLawForm.setTmArticleGroup(tmArticleGroup);
				forword="/law/articleGroupDetail";
			}
		} 
		setMasterLawActionList(masterLawForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID17));
		masterLawForm.setAction(action);
		model.addAttribute("lawTypeList", lawTypeList);
		model.addAttribute("statueList", exciseLawService.getTmStatueList());
		model.addAttribute("articleGroupOrderList", exciseLawService.getTmArticleGroupList());
		model.addAttribute("masterLawForm", masterLawForm); 
		return forword;		 
	}
	
	@RequestMapping(value="/law/articleGroup", method = RequestMethod.POST)
	public String processArticleGroup(ServletRequest request, ServletResponse response, 
				@RequestParam(value="action",required = false) String action,
				@ModelAttribute("masterLawForm") MasterLawForm masterLawForm,
				BindingResult result,
				Model model, SessionStatus status) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		userInfo = (TmUserinfo) session.getAttribute("userInfo");
		String forword  ="/law/articleGroupList";
		TmArticleGroup tmArticleGroup = masterLawForm.getTmArticleGroup();
		DateTime nowTh = new DateTime(new Date().getTime());

		if(masterLawForm.getCreatedDate()!=null && masterLawForm.getCreatedDate().length()>0){
			tmArticleGroup.setCreatedDate(DateUtils.getDateByStringDateTh(masterLawForm.getCreatedDate()));
		}
		
		if(action.equals("doSave")){
			logger.info( "LawController>>articleGroup.POST doSave " );
			String order = masterLawForm.getArticleGroupOrder();			
			
			if(order!=null &&!"".equals(order)){
				exciseLawService.callReorderArticleGroup(Integer.valueOf(order));
				tmArticleGroup.setArticleGroupOrder(new BigDecimal(order));
			}else{
				tmArticleGroup.setArticleGroupOrder(exciseLawService.findMaxArticleGroupOrder());
			}
			
			tmArticleGroup.setCreatedBy(userInfo.getUserId());
			tmArticleGroup.setCreatedDate(nowTh.toDate());
			tmArticleGroup.setUpdatedBy(userInfo.getUserId());
			tmArticleGroup.setUpdatedDate(nowTh.toDate());

			exciseLawService.saveTmArticleGroup(tmArticleGroup);
			masterLawForm.setIsSusses("1");
			forword="/law/articleGroupDetail";
		}else if(action.equals("doEdit")){		
			logger.info( "LawController>>articleGroup.POST doEdit " );

			String order = masterLawForm.getArticleGroupOrder();		
			if(order!=null &&!"".equals(order)){
				exciseLawService.callReorderArticleGroup(Integer.valueOf(order));
				tmArticleGroup.setArticleGroupOrder(new BigDecimal(order));
			}
			
			tmArticleGroup.setUpdatedBy(userInfo.getUserId());
			tmArticleGroup.setUpdatedDate(nowTh.toDate());
			
			exciseLawService.updateTmArticleGroup(tmArticleGroup);			
			masterLawForm.setIsSusses("1"); 
			forword="/law/articleGroupDetail";
		}else if(action.equals("doDelete")){			
			logger.info( "LawController>>articleGroup.POST doDelete " );
			tmArticleGroup.setArticleGroupStatus(STATUS_INACTIVE);
			exciseLawService.updateTmArticleGroup(tmArticleGroup);
			masterLawForm = new MasterLawForm();
			masterLawForm.setAction(action);
			masterLawForm.setIsSusses("1");
			forword="/law/articleGroupList";
		}
		setMasterLawActionList(masterLawForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID17));
		model.addAttribute("lawTypeList", lawTypeList);
		model.addAttribute("statueList", exciseLawService.getTmStatueList());
		model.addAttribute("articleGroupOrderList", exciseLawService.getTmArticleGroupList());
		model.addAttribute("masterLawForm",masterLawForm);
		return forword;		 
	}	
	
	@RequestMapping("/law/articleSection")
	public String articleSection(ServletRequest request, ServletResponse response,
				@RequestParam(value="action",required = false) String action,
				@RequestParam(value="articleSectionId",required = false) String articleSectionId, Model model) {
		MasterLawForm masterLawForm = new MasterLawForm();
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		userInfo = (TmUserinfo) session.getAttribute("userInfo");
		String forword  ="/law/articleSectionList";
		if(action!=null){
			if(action.equals("add")){			
				logger.info( "LawController>>articleSection add " );	
				masterLawForm = new MasterLawForm();
				forword="/law/articleSectionDetail";
			}else if(action.equals("edit")){
				logger.info( "LawController>>articleSection edit " ); 	
				TmArticleSection tmArticleSection = exciseLawService.findTmArticleSectionByArticleSectionId(Long.valueOf(articleSectionId));

				if(tmArticleSection.getCreatedDate()!=null){
					masterLawForm.setCreatedDate(DateUtils.getStringDateByDateTh(tmArticleSection.getCreatedDate().getTime()));
				}	
				masterLawForm.setTmArticleSection(tmArticleSection);
				forword="/law/articleSectionDetail";
			}
		} 
		setMasterLawActionList(masterLawForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID18));
		masterLawForm.setAction(action);
		model.addAttribute("lawTypeList", lawTypeList);
		model.addAttribute("statueList", exciseLawService.getTmStatueList());
		model.addAttribute("articleGroupList", exciseLawService.getTmArticleGroupList());
		model.addAttribute("masterLawForm", masterLawForm); 
		return forword;		 
	}	
	
	@RequestMapping(value="/law/articleSection", method = RequestMethod.POST)
	public String processArticleSection(ServletRequest request, ServletResponse response, 
				@RequestParam(value="action",required = false) String action,
				@ModelAttribute("masterLawForm") MasterLawForm masterLawForm,
				BindingResult result,
				Model model, SessionStatus status) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		userInfo = (TmUserinfo) session.getAttribute("userInfo");
		String forword  ="/law/articleSectionList";
		TmArticleSection tmArticleSection = masterLawForm.getTmArticleSection();
		DateTime nowTh = new DateTime(new Date().getTime());

		if(masterLawForm.getCreatedDate()!=null && masterLawForm.getCreatedDate().length()>0){
			tmArticleSection.setCreatedDate(DateUtils.getDateByStringDateTh(masterLawForm.getCreatedDate()));
		}
		
		if(action.equals("doSave")){
			logger.info( "LawController>>articleSection.POST doSave " );
			tmArticleSection.setArticleSectionOrder(exciseLawService.findMaxArticleSectionOrder());
			tmArticleSection.setCreatedBy(userInfo.getUserId());
			tmArticleSection.setCreatedDate(nowTh.toDate());
			tmArticleSection.setUpdatedBy(userInfo.getUserId());
			tmArticleSection.setUpdatedDate(nowTh.toDate());

			exciseLawService.saveTmArticleSection(tmArticleSection);
			masterLawForm.setIsSusses("1");
			forword="/law/articleSectionDetail";
		}else if(action.equals("doEdit")){		
			logger.info( "LawController>>articleSection.POST doEdit " );
			tmArticleSection.setUpdatedBy(userInfo.getUserId());
			tmArticleSection.setUpdatedDate(nowTh.toDate());
			
			exciseLawService.updateTmArticleSection(tmArticleSection);			
			masterLawForm.setIsSusses("1"); 
			forword="/law/articleSectionDetail";
		}else if(action.equals("doDelete")){			
			logger.info( "LawController>>articleSection.POST doDelete " );
			tmArticleSection.setArticleSectionStatus(STATUS_INACTIVE);
			exciseLawService.updateTmArticleSection(tmArticleSection);
			masterLawForm = new MasterLawForm();
			masterLawForm.setAction(action);
			masterLawForm.setIsSusses("1");
			forword="/law/articleSectionList";
		}
		setMasterLawActionList(masterLawForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID18));
		model.addAttribute("lawTypeList", lawTypeList);
		model.addAttribute("statueList", exciseLawService.getTmStatueList());
		model.addAttribute("articleGroupList", exciseLawService.getTmArticleGroupList());
		model.addAttribute("masterLawForm",masterLawForm);
		return forword;		 
	}	
	
	@RequestMapping("/law/lawType")
	public String lawType(ServletRequest request, ServletResponse response,
				@RequestParam(value="action",required = false) String action,
				@RequestParam(value="lawTypeId",required = false) String lawTypeId, Model model) {
		MasterLawForm masterLawForm = new MasterLawForm();
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		userInfo = (TmUserinfo) session.getAttribute("userInfo");
		String forword  ="/law/lawTypeList";
		if(action!=null){
			if(action.equals("add")){			
				logger.info( "LawController>>lawType add " );	
				masterLawForm = new MasterLawForm();
				forword="/law/lawTypeDetail";
			}else if(action.equals("edit")){
				logger.info( "LawController>>lawType edit " ); 	
				TmLawType tmLawType = exciseLawService.findTmLawTypeByLawTypeId(Long.valueOf(lawTypeId));

				if(tmLawType.getCreatedDate()!=null){
					masterLawForm.setCreatedDate(DateUtils.getStringDateByDateTh(tmLawType.getCreatedDate().getTime()));
				}	
				masterLawForm.setTmLawType(tmLawType);
				forword="/law/lawTypeDetail";
			}
		} 
		setMasterLawActionList(masterLawForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID16));
		masterLawForm.setAction(action);
		model.addAttribute("lawTypeList", lawTypeList);
		model.addAttribute("masterLawForm", masterLawForm); 
		return forword;		 
	}
	
	@RequestMapping(value="/law/lawType", method = RequestMethod.POST)
	public String processLawType(ServletRequest request, ServletResponse response, 
				@RequestParam(value="action",required = false) String action,
				@ModelAttribute("masterLawForm") MasterLawForm masterLawForm,
				BindingResult result,
				Model model, SessionStatus status) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		userInfo = (TmUserinfo) session.getAttribute("userInfo");
		String forword  ="/law/lawTypeList";
		TmLawType tmLawType = masterLawForm.getTmLawType();
		DateTime nowTh = new DateTime(new Date().getTime());

		if(masterLawForm.getCreatedDate()!=null && masterLawForm.getCreatedDate().length()>0){
			tmLawType.setCreatedDate(DateUtils.getDateByStringDateTh(masterLawForm.getCreatedDate()));
		}
		
		if(action.equals("doSave")){
			logger.info( "LawController>>LawType.POST doSave " );
			tmLawType.setLawTypePriority(new BigDecimal("1"));
			tmLawType.setCreatedBy(userInfo.getUserId());
			tmLawType.setCreatedDate(nowTh.toDate());
			tmLawType.setUpdatedBy(userInfo.getUserId());
			tmLawType.setUpdatedDate(nowTh.toDate());

			exciseLawService.saveTmLawType(tmLawType);
			masterLawForm.setIsSusses("1");
			forword="/law/lawTypeDetail";
		}else if(action.equals("doEdit")){		
			logger.info( "LawController>>LawType.POST doEdit " );
			tmLawType.setUpdatedBy(userInfo.getUserId());
			tmLawType.setUpdatedDate(nowTh.toDate());
			
			exciseLawService.updateTmLawType(tmLawType);			
			masterLawForm.setIsSusses("1"); 
			forword="/law/lawTypeDetail";
		}else if(action.equals("doDelete")){			
			logger.info( "LawController>>LawType.POST doDelete " );
			exciseLawService.deleteTmLawType(tmLawType);
			masterLawForm = new MasterLawForm();
			masterLawForm.setAction(action);
			masterLawForm.setIsSusses("1");
			forword="/law/lawTypeList";
		}
		setMasterLawActionList(masterLawForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID16));
		model.addAttribute("lawTypeList", lawTypeList);
		model.addAttribute("masterLawForm",masterLawForm);
		return forword;		 
	}	
	
	@RequestMapping("/law/exArticleHeader")
	public String exArticleHeader(ServletRequest request, ServletResponse response,
				@RequestParam(value="action",required = false) String action,
				@RequestParam(value="articleHeaderId",required = false) String articleHeaderId, Model model) {
		ExciseLawForm exciseLawForm = new ExciseLawForm();
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		userInfo = (TmUserinfo) session.getAttribute("userInfo");
		String forword  ="/law/exArticleHeaderList";
		if(action!=null){
			if(action.equals("add")){			
				logger.info( "LawController>>exArticleHeader add " );	
				exciseLawForm = new ExciseLawForm();
				forword="/law/exArticleHeaderDetail";
			}else if(action.equals("edit")){
				logger.info( "LawController>>exArticleHeader edit " ); 	
				TsExArticleHeader tsExArticleHeader = exciseLawService.findTsExArticleHeaderByArticleHeaderId(Long.valueOf(articleHeaderId));
				
				if(tsExArticleHeader.getArticleHeaderId()>0){
					exciseLawForm.setPrimaryKey(String.valueOf(tsExArticleHeader.getArticleHeaderId()));
				}
				if(tsExArticleHeader.getCreatedDate()!=null){
					exciseLawForm.setCreatedDate(DateUtils.getStringDateByDateTh(tsExArticleHeader.getCreatedDate().getTime()));
				}	
				
				model.addAttribute("tsExArticleHeaderAttachList", exciseLawService.findTsExArticleHeaderAttachListByArticleHeaderId(Long.valueOf(articleHeaderId)));
				exciseLawForm.setTsExArticleHeader(tsExArticleHeader);
				forword="/law/exArticleHeaderDetail";
			}
		} 
//		setExciseLawActionList(exciseLawForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID18));
		exciseLawForm.setAction(action);
		model.addAttribute("lawTypeList", exciseLawService.getTmLawTypeListByPriority0());
		model.addAttribute("statueList", exciseLawService.getTmStatueList());
		model.addAttribute("exciseLawForm", exciseLawForm); 
		return forword;		 
	}	
	
	@RequestMapping(value="/law/exArticleHeader", method = RequestMethod.POST)
	public String processExArticleHeader(ServletRequest request, ServletResponse response, 
				@RequestParam(value="action",required = false) String action,
				@ModelAttribute("exciseLawForm") ExciseLawForm exciseLawForm,
				BindingResult result,
				Model model, SessionStatus status) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		userInfo = (TmUserinfo) session.getAttribute("userInfo");
		String forword  ="/law/exArticleHeaderList";
		TsExArticleHeader tsExArticleHeader = exciseLawForm.getTsExArticleHeader();
		DateTime nowTh = new DateTime(new Date().getTime());

		if(exciseLawForm.getCreatedDate()!=null && exciseLawForm.getCreatedDate().length()>0){
			tsExArticleHeader.setCreatedDate(DateUtils.getDateByStringDateTh(exciseLawForm.getCreatedDate()));
		}
		
		if(action.equals("doSave")){
			logger.info( "LawController>>exArticleHeader.POST doSave " );
			tsExArticleHeader.setCreatedBy(userInfo.getUserId());
			tsExArticleHeader.setCreatedDate(nowTh.toDate());
			tsExArticleHeader.setUpdatedBy(userInfo.getUserId());
			tsExArticleHeader.setUpdatedDate(nowTh.toDate());

			Long articleHeaderId = exciseLawService.saveTsExArticleHeader(tsExArticleHeader);
			
			MultipartFile[] files = exciseLawForm.getFileData();
			if(files != null && files.length>0){
	            for(MultipartFile file : files){
	            	if(file.getOriginalFilename() !=null && file.getSize() > 0){
	            		if(file.getSize() < FILESIZE){
	            			TsStatueAttach tsStatueAttach = saveTsStatueAttachFile(file, articleHeaderId.longValue(), userInfo.getUserId(), nowTh.toDate());
							exciseLawService.saveTsStatueAttach(tsStatueAttach);
	            		}else{
	            			exciseLawForm.setIsSusses("2");//TODO
	            			break;
	            		}
	            	}
	            }	
			}			
			
			exciseLawForm.setIsSusses("1");
			forword="/law/exArticleHeaderDetail";
		}else if(action.equals("doEdit")){		
			logger.info( "LawController>>exArticleHeader.POST doEdit " );
			tsExArticleHeader.setUpdatedBy(userInfo.getUserId());
			tsExArticleHeader.setUpdatedDate(nowTh.toDate());
			
			exciseLawService.updateTsExArticleHeader(tsExArticleHeader);
			
			String [] delAttachFile = exciseLawForm.getDeleteFileBoxes();
			if(delAttachFile!=null && delAttachFile.length>0){
				TsExArticleHeaderAttach tsExArticleHeaderAttach = null;
				for(String attachId : delAttachFile){
					tsExArticleHeaderAttach = new TsExArticleHeaderAttach();
					tsExArticleHeaderAttach.setArticleHeadAttachId(Long.valueOf(attachId));
					exciseLawService.deleteTsExArticleHeadAttach(tsExArticleHeaderAttach);
				}
			}
			
			MultipartFile[] files = exciseLawForm.getFileData();
			if(files != null && files.length>0){
	            for(MultipartFile file : files){
	            	if(file.getOriginalFilename() !=null && file.getSize() > 0){
	            		if(file.getSize() < FILESIZE){
	            			TsExArticleHeaderAttach tsExArticleHeaderAttach = saveTsExArticleHeaderAttach(file, tsExArticleHeader.getArticleHeaderId(), userInfo.getUserId(), nowTh.toDate());
							exciseLawService.saveTsExArticleHeadAttach(tsExArticleHeaderAttach);
	            		}else{
	            			exciseLawForm.setIsSusses("2");//TODO
	            			break;
	            		}
	            	}
	            }	
			}
			
			model.addAttribute("tsExArticleHeaderAttachList", exciseLawService.findTsExArticleHeaderAttachListByArticleHeaderId(tsExArticleHeader.getArticleHeaderId()));
			exciseLawForm.setIsSusses("1"); 
			forword="/law/exArticleHeaderDetail";
		}else if(action.equals("doDelete")){			
			logger.info( "LawController>>exArticleHeader.POST doDelete " );
			exciseLawService.deleteTsExArticleHeader(tsExArticleHeader);
			exciseLawForm = new ExciseLawForm();
			exciseLawForm.setAction(action);
			exciseLawForm.setIsSusses("1");
			forword="/law/exArticleHeaderList";
		}
//		setExciseLawActionList(exciseLawForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID18));
		exciseLawForm.setAction(action);
		model.addAttribute("lawTypeList", exciseLawService.getTmLawTypeListByPriority0());
		model.addAttribute("statueList", exciseLawService.getTmStatueList());
		model.addAttribute("exciseLawForm", exciseLawForm); 
		return forword;		 
	}	
	
	@RequestMapping("/law/article")
	public String article(ServletRequest request, ServletResponse response,
				@RequestParam(value="action",required = false) String action,
				@RequestParam(value="articleId",required = false) String articleId, Model model) {
		ExciseLawForm exciseLawForm = new ExciseLawForm();
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		userInfo = (TmUserinfo) session.getAttribute("userInfo");
		String forword  ="/law/articleList";
		if(action!=null){
			if(action.equals("add")){			
				logger.info( "LawController>>article add " );	
				exciseLawForm = new ExciseLawForm();
				forword="/law/articleDetail";
			}else if(action.equals("edit")){
				logger.info( "LawController>>article edit " ); 	
				TsArticle tsArticle = exciseLawService.findTsArticleByArticleId(Long.valueOf(articleId));
				if(tsArticle.getArticleId()>0){
					exciseLawForm.setPrimaryKey(String.valueOf(tsArticle.getArticleId()));
				}
				if(tsArticle.getCreatedDate()!=null){
					exciseLawForm.setCreatedDate(DateUtils.getStringDateByDateTh(tsArticle.getCreatedDate().getTime()));
				}	
				exciseLawForm.setTsArticle(tsArticle);
				forword="/law/articleDetail";
			}
			model.addAttribute("articleHeaderList", exciseLawService.getTsExArticleHeaderList());
		} 
//		setExciseLawActionList(exciseLawForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID18));
		exciseLawForm.setAction(action);
		model.addAttribute("statueList", exciseLawService.getTmStatueList());
		model.addAttribute("lawTypeList", exciseLawService.getTmLawTypeListByPriority0());
		model.addAttribute("articleGroupList", exciseLawService.getTmArticleGroupList());
		model.addAttribute("articleSectionList", exciseLawService.getTmArticleSectionList());
		model.addAttribute("articleOrderList", exciseLawService.getTsArticleList(exciseLawType));
		model.addAttribute("exciseLawForm", exciseLawForm); 
		return forword;		 
	}	
	
	@RequestMapping(value="/law/article", method = RequestMethod.POST)
	public String processArticle(ServletRequest request, ServletResponse response, 
				@RequestParam(value="action",required = false) String action,
				@ModelAttribute("exciseLawForm") ExciseLawForm exciseLawForm,
				BindingResult result,
				Model model, SessionStatus status) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		userInfo = (TmUserinfo) session.getAttribute("userInfo");
		String forword  ="/law/articleList";
		TsArticle tsArticle = exciseLawForm.getTsArticle();
		DateTime nowTh = new DateTime(new Date().getTime());

		if(exciseLawForm.getCreatedDate()!=null && exciseLawForm.getCreatedDate().length()>0){
			tsArticle.setCreatedDate(DateUtils.getDateByStringDateTh(exciseLawForm.getCreatedDate()));
		}
		
		if(action.equals("doSave")){
			logger.info( "LawController>>article.POST doSave " );			

			String order = exciseLawForm.getArticleOrder();	
			if(order!=null &&!"".equals(order)){
				exciseLawService.callReorderArticle(Integer.valueOf(order), exciseLawType.intValue(), tsArticle.getStatueId().intValue(), tsArticle.getLawTypeId().intValue());
				tsArticle.setArticleOrder(new BigDecimal(order));
			}else{
				tsArticle.setArticleOrder(exciseLawService.findMaxArticleOrder(exciseLawType, tsArticle.getStatueId(), tsArticle.getLawTypeId()));
			}
			
			tsArticle.setExciseArticleType(exciseLawType);
			tsArticle.setCreatedBy(userInfo.getUserId());
			tsArticle.setCreatedDate(nowTh.toDate());
			tsArticle.setUpdatedBy(userInfo.getUserId());
			tsArticle.setUpdatedDate(nowTh.toDate());
			exciseLawService.saveTsArticle(tsArticle);
			
			TsExArticleCompleted articleCompleted = exciseLawService.findArticleCompletedByStatueIdArticleNumber(tsArticle.getStatueId(), tsArticle.getArticleNumber());
			if(articleCompleted == null){
				articleCompleted = new TsExArticleCompleted();
				articleCompleted.setArticleCompletedDetail(tsArticle.getArticleDetail());
				articleCompleted.setArticleCompletedKeyword(tsArticle.getArticleKeyword());
				articleCompleted.setArticleCompletedNumber(tsArticle.getArticleNumber());
				articleCompleted.setArticleCompletedOrder(tsArticle.getArticleOrder());
				articleCompleted.setArticleCompletedRemark(tsArticle.getArticleRemark());
				articleCompleted.setArticleHeaderId(tsArticle.getArticleHeaderId());
				articleCompleted.setArticleGroupId(tsArticle.getArticleGroupId());
				articleCompleted.setArticleSectionId(tsArticle.getArticleSectionId());
				articleCompleted.setStatueId(tsArticle.getStatueId());
				articleCompleted.setCreatedBy(userInfo.getUserId());
				articleCompleted.setCreatedDate(nowTh.toDate());
				articleCompleted.setUpdatedBy(userInfo.getUserId());
				articleCompleted.setUpdatedDate(nowTh.toDate());
				exciseLawService.saveTsExArticleCompleted(articleCompleted);
			}
			exciseLawForm.setIsSusses("1");
			forword="/law/articleDetail";
		}else if(action.equals("doEdit")){		
			logger.info( "LawController>>article.POST doEdit " );
			
			String order = exciseLawForm.getArticleOrder();	
			if(order!=null &&!"".equals(order)){
				exciseLawService.callReorderArticle(Integer.valueOf(order), exciseLawType.intValue(), tsArticle.getStatueId().intValue(), tsArticle.getLawTypeId().intValue());
				tsArticle.setArticleOrder(new BigDecimal(order));
			}
			tsArticle.setUpdatedBy(userInfo.getUserId());
			tsArticle.setUpdatedDate(nowTh.toDate());
			
			exciseLawService.updateTsArticle(tsArticle);			
			exciseLawForm.setIsSusses("1"); 
			forword="/law/articleDetail";
		}else if(action.equals("doDelete")){			
			logger.info( "LawController>>article.POST doDelete " );
			exciseLawService.deleteTsArticle(tsArticle);
			exciseLawForm = new ExciseLawForm();
			exciseLawForm.setAction(action);
			exciseLawForm.setIsSusses("1");
			forword="/law/articleList";
		}
//		setExciseLawActionList(exciseLawForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID18));
		exciseLawForm.setAction(action);
		model.addAttribute("statueList", exciseLawService.getTmStatueList());
		model.addAttribute("lawTypeList", exciseLawService.getTmLawTypeListByPriority0());
		model.addAttribute("articleGroupList", exciseLawService.getTmArticleGroupList());
		model.addAttribute("articleSectionList", exciseLawService.getTmArticleSectionList());
		model.addAttribute("articleHeaderList", exciseLawService.getTsExArticleHeaderList());
		model.addAttribute("articleOrderList", exciseLawService.getTsArticleList(exciseLawType));
		model.addAttribute("exciseLawForm",exciseLawForm);
		return forword;		 
	}	
	
	@RequestMapping("/law/exArticleCompleted")
	public String exArticleCompleted(ServletRequest request, ServletResponse response,
				@RequestParam(value="action",required = false) String action,
				@RequestParam(value="articleCompletedId",required = false) String articleCompletedId, Model model) {
		ExciseLawForm exciseLawForm = new ExciseLawForm();
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		userInfo = (TmUserinfo) session.getAttribute("userInfo");
		String forword  ="/law/exArticleCompletedList";
		if(action!=null){
			if(action.equals("add")){			
				logger.info( "LawController>>exArticleCompleted add " );	
				exciseLawForm = new ExciseLawForm();
				forword="/law/exArticleCompletedDetail";
			}else if(action.equals("edit")){
				logger.info( "LawController>>exArticleCompleted edit " ); 	
				TsExArticleCompleted tsExArticleCompleted = exciseLawService.findTsExArticleCompletedByArticleCompletedId(Long.valueOf(articleCompletedId));

				if(tsExArticleCompleted.getArticleCompletedId()>0){
					exciseLawForm.setPrimaryKey(String.valueOf(tsExArticleCompleted.getArticleCompletedId()));
				}
				if(tsExArticleCompleted.getCreatedDate()!=null){
					exciseLawForm.setCreatedDate(DateUtils.getStringDateByDateTh(tsExArticleCompleted.getCreatedDate().getTime()));
				}	
				exciseLawForm.setTsExArticleCompleted(tsExArticleCompleted);
				forword="/law/exArticleCompletedDetail";
			}
		} 
//		setExciseLawActionList(exciseLawForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID18));
		exciseLawForm.setAction(action);
		model.addAttribute("statueList", exciseLawService.getTmStatueList());
		model.addAttribute("articleGroupList", exciseLawService.getTmArticleGroupList());
		model.addAttribute("articleSectionList", exciseLawService.getTmArticleSectionList());
		model.addAttribute("articleCompletedOrderList", exciseLawService.getTsArticleCompletedList());
		model.addAttribute("exciseLawForm", exciseLawForm); 
		return forword;		 
	}	
	
	@RequestMapping(value="/law/exArticleCompleted", method = RequestMethod.POST)
	public String processExArticleCompleted(ServletRequest request, ServletResponse response, 
				@RequestParam(value="action",required = false) String action,
				@ModelAttribute("exciseLawForm") ExciseLawForm exciseLawForm,
				BindingResult result,
				Model model, SessionStatus status) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		userInfo = (TmUserinfo) session.getAttribute("userInfo");
		String forword  ="/law/exArticleCompletedList";
		TsExArticleCompleted tsExArticleCompleted = exciseLawForm.getTsExArticleCompleted();
		DateTime nowTh = new DateTime(new Date().getTime());

		if(exciseLawForm.getCreatedDate()!=null && exciseLawForm.getCreatedDate().length()>0){
			tsExArticleCompleted.setCreatedDate(DateUtils.getDateByStringDateTh(exciseLawForm.getCreatedDate()));
		}
		
		if(action.equals("doSave")){
			logger.info( "LawController>>exArticleCompleted.POST doSave " );		

			String order = exciseLawForm.getArticleOrder();	
			if(order!=null &&!"".equals(order)){
				exciseLawService.callReorderArticleCompleted(Integer.valueOf(order), tsExArticleCompleted.getStatueId().intValue());
				tsExArticleCompleted.setArticleCompletedOrder(new BigDecimal(order));
			}else{
				tsExArticleCompleted.setArticleCompletedOrder(exciseLawService.findMaxArticleCompletedOrder(tsExArticleCompleted.getStatueId()));
			}
			TsExArticleCompleted articleCompleted = exciseLawService.findTsExArticleCompletedByStatueId(tsExArticleCompleted.getStatueId());
			if(articleCompleted != null && articleCompleted.getArticleHeaderId()!=null)
				tsExArticleCompleted.setArticleHeaderId(articleCompleted.getArticleHeaderId());
			tsExArticleCompleted.setCreatedBy(userInfo.getUserId());
			tsExArticleCompleted.setCreatedDate(nowTh.toDate());
			tsExArticleCompleted.setUpdatedBy(userInfo.getUserId());
			tsExArticleCompleted.setUpdatedDate(nowTh.toDate());

			exciseLawService.saveTsExArticleCompleted(tsExArticleCompleted);
			exciseLawForm.setIsSusses("1");
			forword="/law/exArticleCompletedDetail";
		}else if(action.equals("doEdit")){		
			logger.info( "LawController>>exArticleCompleted.POST doEdit " );
			
			String order = exciseLawForm.getArticleOrder();	
			if(order!=null &&!"".equals(order)){
				exciseLawService.callReorderArticleCompleted(Integer.valueOf(order), tsExArticleCompleted.getStatueId().intValue());
				tsExArticleCompleted.setArticleCompletedOrder(new BigDecimal(order));         
			}
			tsExArticleCompleted.setUpdatedBy(userInfo.getUserId());
			tsExArticleCompleted.setUpdatedDate(nowTh.toDate());
			
			exciseLawService.updateTsExArticleCompleted(tsExArticleCompleted);			
			exciseLawForm.setIsSusses("1"); 
			forword="/law/exArticleCompletedDetail";
		}else if(action.equals("doDelete")){			
			logger.info( "LawController>>exArticleCompleted.POST doDelete " );
			exciseLawService.deleteTsExArticleCompleted(tsExArticleCompleted);
			exciseLawForm = new ExciseLawForm();
			exciseLawForm.setAction(action);
			exciseLawForm.setIsSusses("1");
			forword="/law/exArticleCompletedList";
		}
//		setExciseLawActionList(exciseLawForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID18));
		exciseLawForm.setAction(action);
		model.addAttribute("statueList", exciseLawService.getTmStatueList());
		model.addAttribute("articleGroupList", exciseLawService.getTmArticleGroupList());
		model.addAttribute("articleSectionList", exciseLawService.getTmArticleSectionList());
		model.addAttribute("articleCompletedOrderList", exciseLawService.getTsArticleCompletedList());
		model.addAttribute("exciseLawForm",exciseLawForm);
		return forword;		 
	}	
	
	@RequestMapping("/law/tariff")
	public String tariff(ServletRequest request, ServletResponse response,
				@RequestParam(value="action",required = false) String action,
				@RequestParam(value="tariffId",required = false) String tariffId, Model model) {
		ExciseLawForm exciseLawForm = new ExciseLawForm();
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		userInfo = (TmUserinfo) session.getAttribute("userInfo");
		String forword  ="/law/tariffList";
		if(action!=null){
			if(action.equals("add")){			
				logger.info( "LawController>>tariff add " );	
				exciseLawForm = new ExciseLawForm();
				forword="/law/tariffDetail";
			}else if(action.equals("edit")){
				logger.info( "LawController>>tariff edit " ); 	
				TsTariff tsTariff = exciseLawService.findTsTariffByTariffId(Long.valueOf(tariffId));

				if(tsTariff.getTariffId()>0){
					exciseLawForm.setPrimaryKey(String.valueOf(tsTariff.getTariffId()));
				}
				if(tsTariff.getCreatedDate()!=null){
					exciseLawForm.setCreatedDate(DateUtils.getStringDateByDateTh(tsTariff.getCreatedDate().getTime()));
				}	
				exciseLawForm.setTsTariff(tsTariff);
				forword="/law/tariffDetail";
			}
		} 
//		setExciseLawActionList(exciseLawForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID18));
		exciseLawForm.setAction(action);
		model.addAttribute("lawTypeList", exciseLawService.getTmLawTypeListByPriority0());//TODO
		model.addAttribute("statueList", exciseLawService.getTmStatueList());
		model.addAttribute("articleHeaderList", exciseLawService.getTsExArticleHeaderList());
		model.addAttribute("exciseLawForm", exciseLawForm); 
		return forword;		 
	}	
	
	@RequestMapping(value="/law/tariff", method = RequestMethod.POST)
	public String processTariff(ServletRequest request, ServletResponse response, 
				@RequestParam(value="action",required = false) String action,
				@ModelAttribute("exciseLawForm") ExciseLawForm exciseLawForm,
				BindingResult result,
				Model model, SessionStatus status) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		userInfo = (TmUserinfo) session.getAttribute("userInfo");
		String forword  ="/law/tariffList";
		TsTariff tsTariff = exciseLawForm.getTsTariff();
		DateTime nowTh = new DateTime(new Date().getTime());

		if(exciseLawForm.getCreatedDate()!=null && exciseLawForm.getCreatedDate().length()>0){
			tsTariff.setCreatedDate(DateUtils.getDateByStringDateTh(exciseLawForm.getCreatedDate()));
		}
		
		if(action.equals("doSave")){
			logger.info( "LawController>>tariff.POST doSave " );
			tsTariff.setCreatedBy(userInfo.getUserId());
			tsTariff.setCreatedDate(nowTh.toDate());
			tsTariff.setUpdatedBy(userInfo.getUserId());
			tsTariff.setUpdatedDate(nowTh.toDate());

			exciseLawService.saveTsTariff(tsTariff);
			exciseLawForm.setIsSusses("1");
			forword="/law/tariffDetail";
		}else if(action.equals("doEdit")){		
			logger.info( "LawController>>tariff.POST doEdit " );
			tsTariff.setUpdatedBy(userInfo.getUserId());
			tsTariff.setUpdatedDate(nowTh.toDate());
			
			exciseLawService.updateTsTariff(tsTariff);			
			exciseLawForm.setIsSusses("1"); 
			forword="/law/tariffDetail";
		}else if(action.equals("doDelete")){			
			logger.info( "LawController>>tariff.POST doDelete " );
			exciseLawService.deleteTsTariff(tsTariff);
			exciseLawForm = new ExciseLawForm();
			exciseLawForm.setAction(action);
			exciseLawForm.setIsSusses("1");
			forword="/law/tariffList";
		}
//		setExciseLawActionList(exciseLawForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID18));
		exciseLawForm.setAction(action);
		model.addAttribute("lawTypeList", exciseLawService.getTmLawTypeListByPriority0());//TODO
		model.addAttribute("statueList", exciseLawService.getTmStatueList());
		model.addAttribute("articleHeaderList", exciseLawService.getTsExArticleHeaderList());
		model.addAttribute("exciseLawForm",exciseLawForm);
		return forword;		 
	}	
	
	@RequestMapping("/law/law")
	public String law(ServletRequest request, ServletResponse response,
				@RequestParam(value="action",required = false) String action,
				@RequestParam(value="lawId",required = false) String lawId, Model model) {
		ExciseLawForm exciseLawForm = new ExciseLawForm();
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		userInfo = (TmUserinfo) session.getAttribute("userInfo");
		String forword  ="/law/lawList";
		if(action!=null){
			if(action.equals("add")){			
				logger.info( "LawController>>law add " );	
				exciseLawForm = new ExciseLawForm();	
				forword="/law/lawDetail";
			}else if(action.equals("edit")){
				logger.info( "LawController>>law edit " ); 	
				TsLaw tsLaw = exciseLawService.findTsLawByLawId(Long.valueOf(lawId));

				if(tsLaw.getLawId()>0){
					exciseLawForm.setPrimaryKey(String.valueOf(tsLaw.getLawId()));
				}
				if(tsLaw.getCreatedDate()!=null){
					exciseLawForm.setCreatedDate(DateUtils.getStringDateByDateTh(tsLaw.getCreatedDate().getTime()));
				}					
								
				List<TsLawStatue> tsLawStatueList = exciseLawService.findTsLawStatueListByLawId(BigDecimal.valueOf(Long.valueOf(lawId)));
				List<String> tsLawStatuesList = new ArrayList<String>();
				if(tsLawStatueList != null && tsLawStatueList.size()>0){
					for (TsLawStatue tsLawStatue : tsLawStatueList) {
						String itemId = tsLawStatue.getStatueId()+"";
						tsLawStatuesList.add(itemId);
					}
				}
				String[] tsLawStatues = new String[tsLawStatuesList.size()];
				exciseLawForm.setStatutesBoxes(tsLawStatuesList.toArray(tsLawStatues));

				List<TsLawGood> tsLawGoodList = exciseLawService.findTsLawGoodByLawId(BigDecimal.valueOf(Long.valueOf(lawId)));
				List<String> tsLawGoodsList = new ArrayList<String>();
				if(tsLawGoodList != null && tsLawGoodList.size()>0){
					for (TsLawGood tsLawGood : tsLawGoodList) {
						String itemId = tsLawGood.getDutyGoodsId()+"";
						tsLawGoodsList.add(itemId);
					}
				}
				String[] tsLawGoods = new String[tsLawGoodsList.size()];
				exciseLawForm.setDutyGroupGoodsBoxes(tsLawGoodsList.toArray(tsLawGoods));
				exciseLawForm.setDutyGroupServicesBoxes(tsLawGoodsList.toArray(tsLawGoods));

				model.addAttribute("lawAttachList", exciseLawService.findTsLawAttachListByLawId(tsLaw.getLawId()));		
				exciseLawForm.setTsLaw(tsLaw);
				forword="/law/lawDetail";
			}
		} 
//		setMasterLawActionList(exciseLawForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID18));
		exciseLawForm.setAction(action);
		model.addAttribute("statusList", statusList);
		model.addAttribute("displayList", displayList);	
		model.addAttribute("statutes", exciseLawService.getTmStatueList());
		model.addAttribute("dutyGroupGoods", exciseLawService.listMviewDutyGroupGoods());
		model.addAttribute("dutyGroupServices", exciseLawService.listMviewDutyGroupServices());
		model.addAttribute("lawTypeList", exciseLawService.getTmLawTypeListByPriority0());//TODO
		model.addAttribute("statueList", exciseLawService.getTmStatueList());
		model.addAttribute("exciseLawForm", exciseLawForm); 
		return forword;		 
	}	
	
	@RequestMapping(value="/law/law", method = RequestMethod.POST)
	public String processLaw(ServletRequest request, ServletResponse response, 
				@RequestParam(value="action",required = false) String action,
				@ModelAttribute("exciseLawForm") ExciseLawForm exciseLawForm,
				BindingResult result,
				Model model, SessionStatus status) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		userInfo = (TmUserinfo) session.getAttribute("userInfo");
		String forword  ="/law/lawList";
		TsLaw tsLaw = exciseLawForm.getTsLaw();
		DateTime nowTh = new DateTime(new Date().getTime());

		if(exciseLawForm.getCreatedDate()!=null && exciseLawForm.getCreatedDate().length()>0){
			tsLaw.setCreatedDate(DateUtils.getDateByStringDateTh(exciseLawForm.getCreatedDate()));
		}
		
		if(action.equals("doSave")){
			logger.info( "LawController>>law.POST doSave " );
			tsLaw.setLawExciseStatus(exciseLawType);
			tsLaw.setCreatedBy(userInfo.getUserId());
			tsLaw.setCreatedDate(nowTh.toDate());
			tsLaw.setUpdatedBy(userInfo.getUserId());
			tsLaw.setUpdatedDate(nowTh.toDate());

			Long lawId = exciseLawService.saveTsLaw(tsLaw);
			
			MultipartFile[] files = exciseLawForm.getFileData();
			if(files != null && files.length>0){
	            for(MultipartFile file : files){
	            	if(file.getOriginalFilename() !=null && file.getSize() > 0){
	            		if(file.getSize() < FILESIZE){
	            			TsLawAttach tsLawAttach = setTsLawAttach(file, lawId, userInfo.getUserId(), nowTh.toDate());
							exciseLawService.saveTsLawAttach(tsLawAttach);
	            		}else{
	            			exciseLawForm.setIsSusses("2");//TODO
	            			break;
	            		}
	            	}
	            }	
			}
			
			String [] statutes = exciseLawForm.getStatutesBoxes();
			if(statutes!=null && statutes.length>0){
				TsLawStatue tsLawStatue = null;
				for(String statuteId : statutes){
					tsLawStatue = new TsLawStatue();
					tsLawStatue.setLawId(BigDecimal.valueOf(lawId));
					tsLawStatue.setStatueId(BigDecimal.valueOf(Long.valueOf(statuteId)));
					exciseLawService.saveTsLawStatue(tsLawStatue);
				}
			}			

			String [] dutyGroupGoods = exciseLawForm.getDutyGroupGoodsBoxes();
			if(dutyGroupGoods!=null && dutyGroupGoods.length>0){
				TsLawGood tsLawGood = null;
				for(String groupId : dutyGroupGoods){
					tsLawGood = new TsLawGood();
					tsLawGood.setLawId(BigDecimal.valueOf(lawId));
					tsLawGood.setDutyGoodsId(groupId);
					exciseLawService.saveTsLawGood(tsLawGood);
				}
			}	
			
			String [] dutyGroupServices = exciseLawForm.getDutyGroupServicesBoxes();
			if(dutyGroupServices!=null && dutyGroupServices.length>0){
				TsLawGood tsLawGood = null;
				for(String groupId : dutyGroupServices){
					tsLawGood = new TsLawGood();
					tsLawGood.setLawId(BigDecimal.valueOf(lawId));
					tsLawGood.setDutyGoodsId(groupId);
					exciseLawService.saveTsLawGood(tsLawGood);
				}
			}
			
			exciseLawForm.setIsSusses("1");
			forword="/law/lawDetail";
		}else if(action.equals("doEdit")){		
			logger.info( "LawController>>law.POST doEdit " );
			tsLaw.setUpdatedBy(userInfo.getUserId());
			tsLaw.setUpdatedDate(nowTh.toDate());
			
			exciseLawService.updateTsLaw(tsLaw);	
			
			String [] delAttachFile = exciseLawForm.getDeleteFileBoxes();
			if(delAttachFile!=null && delAttachFile.length>0){
				TsLawAttach tsLawAttach = null;
				for(String attachId : delAttachFile){
					tsLawAttach = new TsLawAttach();
					tsLawAttach.setLawAttachId(Long.valueOf(attachId));
					exciseLawService.deleteTsLawAttach(tsLawAttach);
				}
			}
			
			MultipartFile[] files = exciseLawForm.getFileData();
			if(files != null && files.length>0){
	            for(MultipartFile file : files){
	            	if(file.getOriginalFilename() !=null && file.getSize() > 0){
	            		if(file.getSize() < FILESIZE){
	            			TsLawAttach tsLawAttach = setTsLawAttach(file, tsLaw.getLawId(), userInfo.getUserId(), nowTh.toDate());
							exciseLawService.saveTsLawAttach(tsLawAttach);
	            		}else{
	            			exciseLawForm.setIsSusses("2");//TODO
	            			break;
	            		}
	            	}
	            }	
			}

			List<TsLawStatue> tsLawStatueList = exciseLawService.findTsLawStatueListByLawId(BigDecimal.valueOf(tsLaw.getLawId()));
			if(tsLawStatueList != null && tsLawStatueList.size()>0){
				for(TsLawStatue tsLawStatue : tsLawStatueList){
					exciseLawService.deleteTsLawStatue(tsLawStatue);
				}
			}
			
			String [] statutes = exciseLawForm.getStatutesBoxes();
			if(statutes!=null && statutes.length>0){
				TsLawStatue tsLawStatue = null;
				for(String statuteId : statutes){
					tsLawStatue = new TsLawStatue();
					tsLawStatue.setLawId(BigDecimal.valueOf(tsLaw.getLawId()));
					tsLawStatue.setStatueId(BigDecimal.valueOf(Long.valueOf(statuteId)));
					exciseLawService.saveTsLawStatue(tsLawStatue);
				}
			}		

			List<TsLawGood> tsLawGoodList = exciseLawService.findTsLawGoodByLawId(BigDecimal.valueOf(tsLaw.getLawId()));
			if(tsLawGoodList != null && tsLawGoodList.size()>0){
				for(TsLawGood lawGood : tsLawGoodList){
					exciseLawService.deleteTsLawGood(lawGood);
				}
			}	

			String [] dutyGroupGoods = exciseLawForm.getDutyGroupGoodsBoxes();
			if(dutyGroupGoods!=null && dutyGroupGoods.length>0){
				TsLawGood tsLawGood = null;
				for(String groupId : dutyGroupGoods){
					tsLawGood = new TsLawGood();
					tsLawGood.setLawId(BigDecimal.valueOf(tsLaw.getLawId()));
					tsLawGood.setDutyGoodsId(groupId);
					exciseLawService.saveTsLawGood(tsLawGood);
				}
			}	
			
			String [] dutyGroupServices = exciseLawForm.getDutyGroupServicesBoxes();
			if(dutyGroupServices!=null && dutyGroupServices.length>0){
				TsLawGood tsLawGood = null;
				for(String groupId : dutyGroupServices){
					tsLawGood = new TsLawGood();
					tsLawGood.setLawId(BigDecimal.valueOf(tsLaw.getLawId()));
					tsLawGood.setDutyGoodsId(groupId);
					exciseLawService.saveTsLawGood(tsLawGood);
				}
			}

			model.addAttribute("lawAttachList", exciseLawService.findTsLawAttachListByLawId(tsLaw.getLawId()));	
			exciseLawForm.setIsSusses("1"); 
			forword="/law/lawDetail";
		}else if(action.equals("doDelete")){			
			logger.info( "LawController>>law.POST doDelete " );
			exciseLawService.deleteTsLaw(tsLaw);
			exciseLawForm = new ExciseLawForm();
			exciseLawForm.setAction(action);
			exciseLawForm.setIsSusses("1");
			forword="/law/lawList";
		}
//		setMasterLawActionList(exciseLawForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID18));
		exciseLawForm.setAction(action);
		model.addAttribute("statusList", statusList);
		model.addAttribute("displayList", displayList);	
		model.addAttribute("lawTypeList", exciseLawService.getTmLawTypeListByPriority0());//TODO
		model.addAttribute("statueList", exciseLawService.getTmStatueList());
		model.addAttribute("statutes", exciseLawService.getTmStatueList());
		model.addAttribute("dutyGroupGoods", exciseLawService.listMviewDutyGroupGoods());
		model.addAttribute("dutyGroupServices", exciseLawService.listMviewDutyGroupServices());
		model.addAttribute("exciseLawForm", exciseLawForm); 
		return forword;		 
	}	
	
	@RequestMapping("/law/otherArticle")
	public String otherArticle(ServletRequest request, ServletResponse response,
				@RequestParam(value="action",required = false) String action,
				@RequestParam(value="articleId",required = false) String articleId, Model model) {
		ExciseLawForm exciseLawForm = new ExciseLawForm();
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		userInfo = (TmUserinfo) session.getAttribute("userInfo");
		String forword  ="/law/otherArticleList";
		if(action!=null){
			if(action.equals("add")){			
				logger.info( "LawController>>article add " );	
				exciseLawForm = new ExciseLawForm();
				forword="/law/otherArticleDetail";
			}else if(action.equals("edit")){
				logger.info( "LawController>>article edit " ); 	
				TsArticle tsArticle = exciseLawService.findTsArticleByArticleId(Long.valueOf(articleId));

				if(tsArticle.getArticleId()>0){
					exciseLawForm.setPrimaryKey(String.valueOf(tsArticle.getArticleId()));
				}
				if(tsArticle.getCreatedDate()!=null){
					exciseLawForm.setCreatedDate(DateUtils.getStringDateByDateTh(tsArticle.getCreatedDate().getTime()));
				}	
				exciseLawForm.setTsArticle(tsArticle);
				forword="/law/otherArticleDetail";
			}
		} 
//		setExciseLawActionList(exciseLawForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID18));
		exciseLawForm.setAction(action);
		model.addAttribute("statueList", exciseLawService.getTmStatueList());
		model.addAttribute("articleGroupList", exciseLawService.getTmArticleGroupList());
		model.addAttribute("articleSectionList", exciseLawService.getTmArticleSectionList());
		model.addAttribute("articleOrderList", exciseLawService.getTsArticleList(otherLawType));
		model.addAttribute("exciseLawForm", exciseLawForm); 
		return forword;		 
	}	
	
	@RequestMapping(value="/law/otherArticle", method = RequestMethod.POST)
	public String processOtherArticle(ServletRequest request, ServletResponse response, 
				@RequestParam(value="action",required = false) String action,
				@ModelAttribute("exciseLawForm") ExciseLawForm exciseLawForm,
				BindingResult result,
				Model model, SessionStatus status) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		userInfo = (TmUserinfo) session.getAttribute("userInfo");
		String forword  ="/law/otherArticleList";
		TsArticle tsArticle = exciseLawForm.getTsArticle();
		DateTime nowTh = new DateTime(new Date().getTime());

		if(exciseLawForm.getCreatedDate()!=null && exciseLawForm.getCreatedDate().length()>0){
			tsArticle.setCreatedDate(DateUtils.getDateByStringDateTh(exciseLawForm.getCreatedDate()));
		}
		
		if(action.equals("doSave")){
			logger.info( "LawController>>article.POST doSave " );
			tsArticle.setArticleOrder(exciseLawService.findMaxArticleOrder(exciseLawType, tsArticle.getStatueId(), tsArticle.getLawTypeId()));
			tsArticle.setExciseArticleType(new BigDecimal("0"));
			tsArticle.setCreatedBy(userInfo.getUserId());
			tsArticle.setCreatedDate(nowTh.toDate());
			tsArticle.setUpdatedBy(userInfo.getUserId());
			tsArticle.setUpdatedDate(nowTh.toDate());

			exciseLawService.saveTsArticle(tsArticle);
			exciseLawForm.setIsSusses("1");
			forword="/law/otherArticleDetail";
		}else if(action.equals("doEdit")){		
			logger.info( "LawController>>article.POST doEdit " );
			tsArticle.setUpdatedBy(userInfo.getUserId());
			tsArticle.setUpdatedDate(nowTh.toDate());
			
			exciseLawService.updateTsArticle(tsArticle);			
			exciseLawForm.setIsSusses("1"); 
			forword="/law/otherArticleDetail";
		}else if(action.equals("doDelete")){			
			logger.info( "LawController>>article.POST doDelete " );
			exciseLawService.deleteTsArticle(tsArticle);
			exciseLawForm = new ExciseLawForm();
			exciseLawForm.setAction(action);
			exciseLawForm.setIsSusses("1");
			forword="/law/otherArticleList";
		}
//		setExciseLawActionList(exciseLawForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID18));
		exciseLawForm.setAction(action);
		model.addAttribute("statueList", exciseLawService.getTmStatueList());
		model.addAttribute("articleGroupList", exciseLawService.getTmArticleGroupList());
		model.addAttribute("articleSectionList", exciseLawService.getTmArticleSectionList());
		model.addAttribute("articleOrderList", exciseLawService.getTsArticleList(otherLawType));
		model.addAttribute("exciseLawForm",exciseLawForm);
		return forword;		 
	}	

	@RequestMapping("/law/otherLaw")
	public String otherLaw(ServletRequest request, ServletResponse response,
				@RequestParam(value="action",required = false) String action,
				@RequestParam(value="otherLawId",required = false) String otherLawId, Model model) {
		ExciseLawForm exciseLawForm = new ExciseLawForm();
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		userInfo = (TmUserinfo) session.getAttribute("userInfo");
		String forword  ="/law/otherLawList";
		if(action!=null){
			if(action.equals("add")){			
				logger.info( "LawController>>otherLaw add " );	
				exciseLawForm = new ExciseLawForm();
				forword="/law/otherLawDetail";
			}else if(action.equals("edit")){
				logger.info( "LawController>>otherLaw edit " ); 	
//				TmArticleSection tmArticleSection = exciseLawService.findTmArticleSectionByArticleSectionId(Long.valueOf(otherLawId));
//
//				if(tmArticleSection.getCreatedDate()!=null){
//					exciseLawForm.setCreatedDate(DateUtils.getStringDateByDateTh(tmArticleSection.getCreatedDate().getTime()));
//				}	
//				exciseLawForm.setTmArticleSection(tmArticleSection);
				forword="/law/otherLawDetail";
			}
		} 
//		setMasterLawActionList(exciseLawForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID18));
//		exciseLawForm.setAction(action);
//		model.addAttribute("lawTypeList", lawTypeList);
//		model.addAttribute("statueList", exciseLawService.getTmStatueList());
//		model.addAttribute("articleGroupList", exciseLawService.getTmArticleGroupList());
		model.addAttribute("exciseLawForm", exciseLawForm); 
		return forword;		 
	}	
	
	@RequestMapping(value="/law/otherLaw", method = RequestMethod.POST)
	public String processOtherLaw(ServletRequest request, ServletResponse response, 
				@RequestParam(value="action",required = false) String action,
				@ModelAttribute("exciseLawForm") ExciseLawForm exciseLawForm,
				BindingResult result,
				Model model, SessionStatus status) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		userInfo = (TmUserinfo) session.getAttribute("userInfo");
		String forword  ="/law/otherLawList";
//		TmArticleSection tmArticleSection = exciseLawForm.getTmArticleSection();
		DateTime nowTh = new DateTime(new Date().getTime());

//		if(exciseLawForm.getCreatedDate()!=null && exciseLawForm.getCreatedDate().length()>0){
//			tmArticleSection.setCreatedDate(DateUtils.getDateByStringDateTh(exciseLawForm.getCreatedDate()));
//		}
		
//		if(action.equals("doSave")){
//			logger.info( "LawController>>otherLaw.POST doSave " );
//			tmArticleSection.setArticleSectionOrder(exciseLawService.findMaxArticleSectionOrder());
//			tmArticleSection.setCreatedBy(userInfo.getUserId());
//			tmArticleSection.setCreatedDate(nowTh.toDate());
//			tmArticleSection.setUpdatedBy(userInfo.getUserId());
//			tmArticleSection.setUpdatedDate(nowTh.toDate());
//
//			exciseLawService.saveTmArticleSection(tmArticleSection);
//			exciseLawForm.setIsSusses("1");
//			forword="/law/otherLawDetail";
//		}else if(action.equals("doEdit")){		
//			logger.info( "LawController>>otherLaw.POST doEdit " );
//			tmArticleSection.setUpdatedBy(userInfo.getUserId());
//			tmArticleSection.setUpdatedDate(nowTh.toDate());
//			
//			exciseLawService.updateTmArticleSection(tmArticleSection);			
//			exciseLawForm.setIsSusses("1"); 
//			forword="/law/otherLawDetail";
//		}else if(action.equals("doDelete")){			
//			logger.info( "LawController>>otherLaw.POST doDelete " );
//			tmArticleSection.setArticleSectionStatus(STATUS_INACTIVE);
//			exciseLawService.updateTmArticleSection(tmArticleSection);
//			exciseLawForm = new ExciseLawForm();
//			exciseLawForm.setAction(action);
//			exciseLawForm.setIsSusses("1");
//			forword="/law/otherLawList";
//		}
//		setMasterLawActionList(exciseLawForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID18));
//		model.addAttribute("lawTypeList", lawTypeList);
//		model.addAttribute("statueList", exciseLawService.getTmStatueList());
//		model.addAttribute("articleGroupList", exciseLawService.getTmArticleGroupList());
		model.addAttribute("exciseLawForm",exciseLawForm);
		return forword;		 
	}	
		
	private void setMasterLawActionList(MasterLawForm masterLawForm, String actionList){
		if(actionList.indexOf("ADD") != -1){
			masterLawForm.setAdd("true");
		}
		if(actionList.indexOf("EDIT") != -1){
			masterLawForm.setEdit("true");
		}
		if(actionList.indexOf("DELETE") != -1){
			masterLawForm.setDelete("true");
		}
		if(actionList.indexOf("VIEW") != -1){
			masterLawForm.setView("true");
		}		
	}
		
	private void setExciseLawActionList(ExciseLawForm exciseLawForm, String actionList){
		if(actionList.indexOf("ADD") != -1){
			exciseLawForm.setAdd("true");
		}
		if(actionList.indexOf("EDIT") != -1){
			exciseLawForm.setEdit("true");
		}
		if(actionList.indexOf("DELETE") != -1){
			exciseLawForm.setDelete("true");
		}
		if(actionList.indexOf("VIEW") != -1){
			exciseLawForm.setView("true");
		}		
	}
	
	private TsStatueAttach saveTsStatueAttachFile(MultipartFile fileUpload, long statueId, String userId, Date today){
		TsStatueAttach tsStatueAttach = new TsStatueAttach();
		FileOutputStream fos = null;
		if (fileUpload.getSize() > 0) {
            try {
		        byte[] bytes = fileUpload.getBytes();		 

            	String filePath = resources.getString("statue_path")+"/"+statueId;
            	File fileDir = new File(filePath);
            	if(!fileDir.exists()){
            		fileDir.mkdirs();
            	}
            	File file = new File(fileDir, fileUpload.getOriginalFilename());
		        fos = new FileOutputStream(file);
		        fos.write(bytes);
		        fos.flush();
		        fos.close();
		        
		        tsStatueAttach.setFileName(fileUpload.getOriginalFilename());
		        tsStatueAttach.setFilePath(filePath);
		        tsStatueAttach.setStatueId(BigDecimal.valueOf(statueId));
		        tsStatueAttach.setCreatedBy(userInfo.getUserId());
		        tsStatueAttach.setUpdatedBy(userInfo.getUserId());
		        tsStatueAttach.setCreatedDate(today);
		        tsStatueAttach.setUpdatedDate(today);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try { fos.close(); } catch (IOException e) { e.printStackTrace(); }
			}
	    }
		return tsStatueAttach;
	}
	
	private TsExArticleHeaderAttach saveTsExArticleHeaderAttach(MultipartFile fileUpload, long articleHeaderId, String userId, Date today){
		TsExArticleHeaderAttach tsExArticleHeaderAttach = new TsExArticleHeaderAttach();
		FileOutputStream fos = null;
		if (fileUpload.getSize() > 0) {
            try {
		        byte[] bytes = fileUpload.getBytes();		 

            	String filePath = resources.getString("exarticleheader_path")+"/"+articleHeaderId;
            	File fileDir = new File(filePath);
            	if(!fileDir.exists()){
            		fileDir.mkdirs();
            	}
            	File file = new File(fileDir, fileUpload.getOriginalFilename());
		        fos = new FileOutputStream(file);
		        fos.write(bytes);
		        fos.flush();
		        fos.close();
		        
		        tsExArticleHeaderAttach.setFileName(fileUpload.getOriginalFilename());
		        tsExArticleHeaderAttach.setFilePath(filePath);
		        tsExArticleHeaderAttach.setArticleHeaderId(BigDecimal.valueOf(articleHeaderId));
		        tsExArticleHeaderAttach.setCreatedBy(userInfo.getUserId());
		        tsExArticleHeaderAttach.setUpdatedBy(userInfo.getUserId());
		        tsExArticleHeaderAttach.setCreatedDate(today);
		        tsExArticleHeaderAttach.setUpdatedDate(today);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try { fos.close(); } catch (IOException e) { e.printStackTrace(); }
			}
	    }
		return tsExArticleHeaderAttach;
	}
	
	private TsLawAttach setTsLawAttach(MultipartFile fileUpload, long lawId, String userId, Date today){
		TsLawAttach tsLawAttach = new TsLawAttach();
		FileOutputStream fos = null;
		if (fileUpload.getSize() > 0) {
            try {
		        byte[] bytes = fileUpload.getBytes();		 

            	String filePath = resources.getString("law_path")+"/"+lawId;
            	File fileDir = new File(filePath);
            	if(!fileDir.exists()){
            		fileDir.mkdirs();
            	}
            	File file = new File(fileDir, fileUpload.getOriginalFilename());
		        fos = new FileOutputStream(file);
		        fos.write(bytes);
		        fos.flush();
		        fos.close();
		        
		        tsLawAttach.setFileName(fileUpload.getOriginalFilename());
		        tsLawAttach.setFilePath(filePath);
		        tsLawAttach.setLawId(BigDecimal.valueOf(lawId));
		        tsLawAttach.setCreatedBy(userInfo.getUserId());
		        tsLawAttach.setUpdatedBy(userInfo.getUserId());
		        tsLawAttach.setCreatedDate(today);
		        tsLawAttach.setUpdatedDate(today);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try { fos.close(); } catch (IOException e) { e.printStackTrace(); }
			}
	    }
		return tsLawAttach;
	}
}
