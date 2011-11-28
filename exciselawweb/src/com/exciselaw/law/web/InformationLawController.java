package com.exciselaw.law.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

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

import com.exciselaw.law.domain.Display;
import com.exciselaw.law.domain.MsLegalitem;
import com.exciselaw.law.domain.MsMatrgroup;
import com.exciselaw.law.domain.MsStatute;
import com.exciselaw.law.domain.MsSubmatr;
import com.exciselaw.law.domain.StMatr;
import com.exciselaw.law.domain.StStatutesub;
import com.exciselaw.law.domain.Status;
import com.exciselaw.law.domain.TsRelStatute;
import com.exciselaw.law.domain.TsRelStatutePK;
import com.exciselaw.law.domain.TsStatuteLegalitem;
import com.exciselaw.law.domain.TsStatuteLegalitemPK;
import com.exciselaw.law.domain.TsStatutesubLegalitem;
import com.exciselaw.law.domain.TsStatutesubLegalitemPK;
import com.exciselaw.law.form.MsMatrgroupForm;
import com.exciselaw.law.form.MsStatuteForm;
import com.exciselaw.law.form.MsSubmatrForm;
import com.exciselaw.law.form.StMatrForm;
import com.exciselaw.law.form.StStatutesubForm;
import com.exciselaw.law.service.ExciseLawService;
import com.exciselaw.law.utils.Paging;

@Controller
@SessionAttributes({"msMatrgroupForm","msSubmatrForm","msStatuteForm","stMatrForm","stStatutesubForm"})
public class InformationLawController {
	private final ExciseLawService exciseLawService;
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(InformationLawController.class);
	private static final String[] STATUS_VALUE_BOXES = new String[]{"1","0"};
	private static final String[] STATUS_LABEL_BOXES = new String[]{"มีผลบังคับใช้","ไม่มีผลบังคับใช้"};
	private static final String[] DISPLAY_VALUE_BOXES = new String[]{"1","0"};
	private static final String[] DISPLAY_LABEL_BOXES = new String[]{"แสดงผล","ไม่แสดงผล"};
	private static List statusList=new ArrayList(2);
	private static List displayList=new ArrayList(2);
	static{
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
	public InformationLawController(ExciseLawService exciseLawService) {
		this.exciseLawService = exciseLawService;
	}
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		System.out.println( "InformationLawController>>setAllowedFields" );
		dataBinder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
		//dataBinder.setAllowedFields(INFORMATION_ALLOWED_FIELDS);
		dataBinder.setDisallowedFields("id");
	}
	@RequestMapping("/information_law/masterProvideDetail")
	public String masterProvideDetail(@RequestParam(value="action",required = false) String action
			,@RequestParam(value="statuteId",required = false) String statuteId,Model model) {
		logger.info( "InformationLawController>>masterProvideDetail" ); 
		MsStatuteForm msStatuteForm =new MsStatuteForm();
		model.addAttribute("msLegalitemProducts",exciseLawService.listMsLegalitems(Long.parseLong("1")));
		model.addAttribute("msLegalitemServices",exciseLawService.listMsLegalitems(Long.parseLong("2")));
		BigDecimal active = BigDecimal.valueOf(1l);
		String forword  ="information_law/masterProvideDetail";
		if(action!=null){
			if(action.equals("search")){
				model.addAttribute("msStatutes",exciseLawService.searchMsStatute(new MsStatute(),new Paging()).get(0));
				forword="information_law/masterProvide";
				//action = "search";
			}
			else if(action.equals("edit")){
				MsStatute  msStatute = exciseLawService.findMsStatuteById(Long.parseLong(statuteId));
				List<TsStatuteLegalitem> tsStatuteLegalitems=exciseLawService.searchTsStatuteLegalitemByStatuteId(msStatute.getStatuteId());
				List<String> msLegalitemProductsList = new ArrayList<String>();
				List<String> msLegalitemServicesList = new ArrayList<String>();
				if(tsStatuteLegalitems!=null && tsStatuteLegalitems.size()>0)
				{
					 for (TsStatuteLegalitem tsStatuteLegalitem : tsStatuteLegalitems) {
						 Long type = tsStatuteLegalitem.getId().getMsLegalitem().getMsLegalitemtype().getLegalitemtypeId();
						 String itemId=tsStatuteLegalitem.getId().getMsLegalitem().getLegalitemId()+"";
						 if(type.intValue()==1){// product
							 msLegalitemProductsList.add(itemId);
						 }else if(type.intValue()==2){ // service
							 msLegalitemServicesList.add(itemId);
						 }
					//	 System.out.println("type==>"+tsStatuteLegalitem.getId().getMsLegalitem().getMsLegalitemtype().getName());
					}
					 String[] msLegalitemProducts = new String[msLegalitemProductsList.size()];
					 String[] msLegalitemServices = new String[msLegalitemServicesList.size()];
					 msStatuteForm.setProductBoxes(msLegalitemProductsList.toArray(msLegalitemProducts));
					 msStatuteForm.setServiceBoxes(msLegalitemServicesList.toArray(msLegalitemServices));
				}
				 
				msStatuteForm.setMsStatute(msStatute);
				active = msStatute.getActive()!=null?msStatute.getActive():active;
				//action ="edit";
			}
		}
		msStatuteForm.getMsStatute().setActive(active);
		action = (action!=null && !action.equals(""))?action:"list";
		msStatuteForm.setAction(action); 
		model.addAttribute("msStatuteForm", msStatuteForm);
		//MS_STATUTE
		return forword;
	}
	@RequestMapping(value="/information_law/masterProvideDetail",method = RequestMethod.POST)
	public String processSubmitMasterProvideDetail(ServletRequest request, ServletResponse response,@RequestParam(value="action",required = false) String action,
			//@RequestParam("file") MultipartFile[] files,
			@RequestParam(value="file",required = false) MultipartFile[] files,
			@ModelAttribute("msStatuteForm") MsStatuteForm msStatuteForm,
			BindingResult result,
			Model model, SessionStatus status) {
		String forword="information_law/masterProvideDetail";
		MsStatute msStatute = msStatuteForm.getMsStatute();
		if(action.equals("doSave")){
			Long statuteId= exciseLawService.saveMsStatute(msStatute);
			String[] products=msStatuteForm.getProductBoxes();
			String[] services=msStatuteForm.getServiceBoxes();
			int size=products.length+services.length;
			List<TsStatuteLegalitem> tsStatuteLegalitems =new ArrayList<TsStatuteLegalitem>(size);
			for (int i = 0; i < services.length; i++) {
				TsStatuteLegalitem tsStatuteLegalitem =new TsStatuteLegalitem();
				TsStatuteLegalitemPK pk = new TsStatuteLegalitemPK();
				msStatute.setStatuteId(statuteId);
				MsLegalitem msLegalitem =new MsLegalitem();
				msLegalitem.setLegalitemId(Long.parseLong(services[i]));
				pk.setMsStatute(msStatute);
				pk.setMsLegalitem(msLegalitem);
				tsStatuteLegalitem.setId(pk);
				tsStatuteLegalitems.add(tsStatuteLegalitem);
			} 
			for (int i = 0; i < products.length; i++) {
				TsStatuteLegalitem tsStatuteLegalitem =new TsStatuteLegalitem();
				TsStatuteLegalitemPK pk = new TsStatuteLegalitemPK();
				msStatute.setStatuteId(statuteId);
				MsLegalitem msLegalitem =new MsLegalitem();
				msLegalitem.setLegalitemId(Long.parseLong(products[i]));
				pk.setMsStatute(msStatute);
				pk.setMsLegalitem(msLegalitem);
				tsStatuteLegalitem.setId(pk);
				tsStatuteLegalitems.add(tsStatuteLegalitem);
			} 
			 exciseLawService.saveTsStatuteLegalitem(tsStatuteLegalitems);
			 msStatuteForm.setIsSusses("1");
			 
		}else if(action.equals("doEdit")){			 
			 exciseLawService.updateMsStatute(msStatute);
			 msStatuteForm.setIsSusses("1"); 
		}else if(action.equals("doDelete")){
			 exciseLawService.deleteMsStatute(msStatute);
			 msStatuteForm = new MsStatuteForm();
			 msStatuteForm.setAction(action);
			 msStatuteForm.setIsSusses("1");
			 model.addAttribute("msStatuteForm",msStatuteForm);
		}
		else if(action.equals("doSearch")){
		//	msStatute msStatute =msStatuteForm.getmsStatute();
			System.out.println("msStatute.getName()="+msStatute.getName());
			model.addAttribute("msStatutes",exciseLawService.searchMsStatute(msStatute,new Paging()).get(0));
			forword="information_law/masterProvide";
		}
		model.addAttribute("msLegalitemProducts",exciseLawService.listMsLegalitems(Long.parseLong("1")));
		model.addAttribute("msLegalitemServices",exciseLawService.listMsLegalitems(Long.parseLong("2")));
		return forword;
		 
	}
	@RequestMapping("/Law/SectionList")
	public String sectionList(@RequestParam(value="action",required = false) String action
			,@RequestParam(value="stMatrId",required = false) String stMatrId,Model model) {
		StMatrForm stMatrForm =new StMatrForm();
		String forword  ="information_law/sectionList";
		model.addAttribute("msStatutes", exciseLawService.listMsStatutes(null));
		DateTime nowTh = new DateTime(new Date().getTime()).withChronology(BuddhistChronology.getInstance()); 
		stMatrForm.setUpdateTime(nowTh.getDayOfMonth()+"/"+nowTh.getMonthOfYear()+"/"+nowTh.getYear());
		List <MsMatrgroup> msMatrgroups =exciseLawService.listMsMatrgroups();
		if(msMatrgroups!=null && msMatrgroups.size()>0){
			for (MsMatrgroup msMatrgroup : msMatrgroups) {
				msMatrgroup.setName(msMatrgroup.getName()+" "+
						((msMatrgroup.getMsSubmatr()!=null && msMatrgroup.getMsSubmatr().getName()!=null)
						?msMatrgroup.getMsSubmatr().getName():""));
			}
		}
		model.addAttribute("msMatrgroups", msMatrgroups);
		if(action!=null){
			if(action.equals("search")){
				model.addAttribute("stMatrs",exciseLawService.searchStMatr(new StMatr(),new Paging()).get(0));
				forword="information_law/matrSearch";
			}
			else if(action.equals("edit")){
				StMatr  stMatr = exciseLawService.findStMatrById(Long.parseLong(stMatrId)); 
				stMatrForm.setStMatr(stMatr); 
			}
		} 
		action = (action!=null && !action.equals(""))?action:"list"; 
		stMatrForm.setAction(action);
		model.addAttribute("stMatrForm", stMatrForm); 
		return forword;
	}
	@RequestMapping(value="/Law/SectionList",method = RequestMethod.POST)
	public String processSubmitSectionList(ServletRequest request, ServletResponse response,@RequestParam(value="action",required = false) String action,
			//@RequestParam("file") MultipartFile[] files,
		//	@RequestParam(value="file",required = false) MultipartFile[] files,
			@ModelAttribute("stMatrForm") StMatrForm stMatrForm,
			BindingResult result,
			Model model, SessionStatus status) {
		model.addAttribute("msStatutes", exciseLawService.listMsStatutes(null));
		List <MsMatrgroup> msMatrgroups =exciseLawService.listMsMatrgroups();
		if(msMatrgroups!=null && msMatrgroups.size()>0){
			for (MsMatrgroup msMatrgroup : msMatrgroups) {
				msMatrgroup.setName(msMatrgroup.getName()+" "+
						((msMatrgroup.getMsSubmatr()!=null && msMatrgroup.getMsSubmatr().getName()!=null)
						?msMatrgroup.getMsSubmatr().getName():"" ));
			}
		}
		model.addAttribute("msMatrgroups", msMatrgroups);
		String forword="information_law/sectionList";
		StMatr stMatr = stMatrForm.getStMatr();
		DateTime nowTh = new DateTime(new Date().getTime()).withChronology(BuddhistChronology.getInstance());
		stMatr.setUpdateTime(nowTh.toDate());
		if(action.equals("doSave")){
		//	Long stMatrId
			exciseLawService.saveStMatr(stMatr); 
			stMatrForm.setIsSusses("1"); 
		}else if(action.equals("doEdit")){			 
			 exciseLawService.updateStMatr(stMatr);
			 stMatrForm.setIsSusses("1"); 
		}else if(action.equals("doDelete")){
			System.out.println(" xxxxxxxxxx doDelete getStMatrId==>"+stMatr.getStMatrId());
			 exciseLawService.deleteStMatr(stMatr);
			 stMatrForm = new StMatrForm();
			 stMatrForm.setAction(action);
			 stMatrForm.setIsSusses("1");
			 model.addAttribute("stMatrForm",stMatrForm);
		}
		else if(action.equals("doSearch")){  
			model.addAttribute("stMatrs",exciseLawService.searchStMatr(stMatr,new Paging()).get(0));
			forword="information_law/matrSearch";
		}
		return forword;
		 
	}
	
	@RequestMapping("/statutesub/input")
	public String stStatutesubList(@RequestParam(value="action",required = false) String action
			,@RequestParam(value="stStatutesubId",required = false) String stStatutesubId,Model model) {
		StStatutesubForm stStatutesubForm =new StStatutesubForm();
		String forword  ="information_law/input";
		List msStatutes=exciseLawService.listMsStatutes(null);
		model.addAttribute("msStatutes",msStatutes );
		model.addAttribute("msStatutesubs", exciseLawService.listMsStatutesubs()); 
		DateTime nowTh = new DateTime(new Date().getTime()).withChronology(BuddhistChronology.getInstance()); 
		stStatutesubForm.setUpdateTime(nowTh.getDayOfMonth()+"/"+nowTh.getMonthOfYear()+"/"+nowTh.getYear());
		List msStatuteBoxList = new ArrayList();
		if(msStatutes!=null){
			int msStatutes_size=msStatutes.size();			
			for (int i = 0; i < msStatutes_size; i++) {
				MsStatute msStatute= (MsStatute)msStatutes.get(i);
				if(msStatute.getActive()!=null && msStatute.getActive().intValue()==1){
					msStatuteBoxList.add(msStatute);
				}
			}
		}
		model.addAttribute("msStatuteBoxList",msStatuteBoxList);
		List msLegalitemList =exciseLawService.listMsLegalitems(null);
		List msLegalitemProducts =new ArrayList();
		List msLegalitemServices =new ArrayList();
		if(msLegalitemList!=null){
			int msLegalitem_size=msLegalitemList.size();			
			for (int i = 0; i < msLegalitem_size; i++) { 
				MsLegalitem msLegalitem= (MsLegalitem)msLegalitemList.get(i);
				if(msLegalitem.getMsLegalitemtype()!=null){
					if(msLegalitem.getMsLegalitemtype().getLegalitemtypeId()!=null){
						Long typeId=msLegalitem.getMsLegalitemtype().getLegalitemtypeId();
						if(typeId.intValue()==1)
							msLegalitemProducts.add(msLegalitem);
						else if(typeId.intValue()==2)
							msLegalitemServices.add(msLegalitem);
					}
				} 
			}
		}
		model.addAttribute("msLegalitemProducts",msLegalitemProducts);
		model.addAttribute("msLegalitemServices",msLegalitemServices);
		model.addAttribute("displayList",displayList);
		model.addAttribute("statusList",statusList);
	//	model.addAttribute("msLegalitemProducts",exciseLawService.listMsLegalitems(Long.parseLong("1")));
	//	model.addAttribute("msLegalitemServices",exciseLawService.listMsLegalitems(Long.parseLong("2")));
		String display="0";
		String status="0";
		/*
		List <MsMatrgroup> msMatrgroups =exciseLawService.listMsMatrgroups();
		if(msMatrgroups!=null && msMatrgroups.size()>0){
			for (MsMatrgroup msMatrgroup : msMatrgroups) {
				msMatrgroup.setName(msMatrgroup.getName()+" "+
						((msMatrgroup.getMsSubmatr()!=null && msMatrgroup.getMsSubmatr().getName()!=null)
						?msMatrgroup.getMsSubmatr().getName():""));
			}
		}
		model.addAttribute("msMatrgroups", msMatrgroups);*/
		if(action!=null){
			if(action.equals("search")){
				model.addAttribute("stStatutesubs",exciseLawService.searchStStatutesub(new StStatutesub(),new Paging(),null).get(0));
				forword="information_law/lawlist";
			}
			else if(action.equals("edit")){
				StStatutesub  stStatutesub = exciseLawService.findStStatutesubById(Long.parseLong(stStatutesubId)); 
				status=(stStatutesub.getStatus()!=null && stStatutesub.getStatus().length()>0)?stStatutesub.getStatus():"0";
				display=(stStatutesub.getDisplay()!=null && stStatutesub.getDisplay().length()>0)?stStatutesub.getDisplay():"0";
				stStatutesubForm.setStStatutesub(stStatutesub);
				List<TsStatutesubLegalitem> tsStatutesubLegalitems=exciseLawService.searchTsStatutesubLegalitemByStStatutesubId(stStatutesub.getStStatutesubId());
				List<TsRelStatute> tsRelStatutes=exciseLawService.searchTsRelStatuteByStStatutesubId(stStatutesub.getStStatutesubId());
				List<String> msLegalitemProductsList = new ArrayList<String>();
				List<String> msLegalitemServicesList = new ArrayList<String>();
				if(tsStatutesubLegalitems!=null && tsStatutesubLegalitems.size()>0)
				{
					 for (TsStatutesubLegalitem tsStatutesubLegalitem : tsStatutesubLegalitems) {
						 Long type = tsStatutesubLegalitem.getId().getMsLegalitem().getMsLegalitemtype().getLegalitemtypeId();
						 String itemId=tsStatutesubLegalitem.getId().getMsLegalitem().getLegalitemId()+"";
						 if(type.intValue()==1){// product
							 msLegalitemProductsList.add(itemId);
						 }else if(type.intValue()==2){ // service
							 msLegalitemServicesList.add(itemId);
						 }
					//	 System.out.println("type==>"+tsStatuteLegalitem.getId().getMsLegalitem().getMsLegalitemtype().getName());
					}
					 String[] msLegalitemProductsArray = new String[msLegalitemProductsList.size()];
					 String[] msLegalitemServicesArray = new String[msLegalitemServicesList.size()];
					 stStatutesubForm.setProductBoxes(msLegalitemProductsList.toArray(msLegalitemProductsArray));
					 stStatutesubForm.setServiceBoxes(msLegalitemServicesList.toArray(msLegalitemServicesArray));
				}
				List<String> tsRelStatutesList = new ArrayList<String>();
				if(tsRelStatutes!=null && tsRelStatutes.size()>0){
					for (TsRelStatute tsRelStatute : tsRelStatutes) {
						tsRelStatutesList.add(tsRelStatute.getId().getMsStatute().getStatuteId()+"");
					}
					 String[] tsRelStatutesArray = new String[tsRelStatutesList.size()];
					 stStatutesubForm.setMsStatuteBoxes(tsRelStatutesList.toArray(tsRelStatutesArray));
				}
				if(stStatutesub.getAnnounceDate()!=null){
					DateTime dtISO = new DateTime(stStatutesub.getAnnounceDate().getTime());
					stStatutesubForm.setAnnounceDate(dtISO.getDayOfMonth()+"/"+dtISO.getMonthOfYear()+"/"+dtISO.getYear());
				}
				 
			}
		} 
		action = (action!=null && !action.equals(""))?action:"list"; 
		stStatutesubForm.setAction(action);
		stStatutesubForm.getStStatutesub().setStatus(status);
		stStatutesubForm.getStStatutesub().setDisplay(display);
		model.addAttribute("stStatutesubForm", stStatutesubForm); 
		return forword;
	}
	@RequestMapping(value="/statutesub/input",method = RequestMethod.POST)
	public String processSubmitStStatutesub(ServletRequest request, ServletResponse response,@RequestParam(value="action",required = false) String action,
			//@RequestParam("file") MultipartFile[] files,
		//	@RequestParam(value="file",required = false) MultipartFile[] files,
			@ModelAttribute("stStatutesubForm") StStatutesubForm stStatutesubForm,
			BindingResult result,
			Model model, SessionStatus status) {
			List msStatutesList=exciseLawService.listMsStatutes(null);
			model.addAttribute("msStatutes",msStatutesList );
			model.addAttribute("msStatutesubs", exciseLawService.listMsStatutesubs());
			List msLegalitemList =exciseLawService.listMsLegalitems(null);
			List msLegalitemProducts =new ArrayList();
			List msLegalitemServices =new ArrayList();
			if(msLegalitemList!=null){
				int msLegalitem_size=msLegalitemList.size();			
				for (int i = 0; i < msLegalitem_size; i++) { 
					MsLegalitem msLegalitem= (MsLegalitem)msLegalitemList.get(i);
					if(msLegalitem.getMsLegalitemtype()!=null){
						if(msLegalitem.getMsLegalitemtype().getLegalitemtypeId()!=null){
							Long typeId=msLegalitem.getMsLegalitemtype().getLegalitemtypeId();
							if(typeId.intValue()==1)
								msLegalitemProducts.add(msLegalitem);
							else if(typeId.intValue()==2)
								msLegalitemServices.add(msLegalitem);
						}
					} 
				}
			}
			model.addAttribute("msLegalitemProducts",msLegalitemProducts);
			model.addAttribute("msLegalitemServices",msLegalitemServices);
		//	model.addAttribute("msLegalitemProducts",exciseLawService.listMsLegalitems(Long.parseLong("1"))); 
			List msStatuteBoxList = new ArrayList();
			if(msStatutesList!=null){
				int msStatutes_size=msStatutesList.size();			
				for (int i = 0; i < msStatutes_size; i++) {
					MsStatute msStatute= (MsStatute)msStatutesList.get(i);
					if(msStatute.getActive()!=null && msStatute.getActive().intValue()==1){
						msStatuteBoxList.add(msStatute);
					}
				}
			}
		model.addAttribute("msStatuteBoxList",msStatuteBoxList);
		model.addAttribute("displayList",displayList);
		model.addAttribute("statusList",statusList);
		model.addAttribute("stStatutesubForm", stStatutesubForm);
		String forword="information_law/input";
		StStatutesub stStatutesub = stStatutesubForm.getStStatutesub();
		DateTime nowTh = new DateTime(new Date().getTime()).withChronology(BuddhistChronology.getInstance()); 
		stStatutesub.setUpdateTime(nowTh.toDate());
		System.out.println("stStatutesubForm.getAnnounceDate()="+stStatutesubForm.getAnnounceDate());
		if(stStatutesubForm.getAnnounceDate()!=null && stStatutesubForm.getAnnounceDate().length()>0){
			//09/05/2554
			String[] day = stStatutesubForm.getAnnounceDate().split("/");
			// setup date object for midday on May Day 2004 (ISO year 2004)
			DateTime dtISO = new DateTime(Integer.parseInt(day[2]), Integer.parseInt(day[1]),Integer.parseInt(day[0])
					, 12, 0, 0, 0);
			//	DateTime dtISO = new DateTime(2004, 5, 1, 12, 0, 0, 0);

			// find out what the same instant is using the Buddhist Chronology
		//	DateTime dtBudd = dtISO.withChronology(BuddhistChronology.getInstance());
			stStatutesub.setAnnounceDate(dtISO.toDate());
		}
		if(action.equals("doSave")){
			//System.out.println(stStatutesub.getMsStatute());
			//System.out.println(stStatutesub.getMsStatutesub());
			Long stStatutesubId=exciseLawService.saveStStatutesub(stStatutesub); 
			System.out.println("stStatuteSubId="+stStatutesubId);
			String[] products=stStatutesubForm.getProductBoxes();
			String[] services=stStatutesubForm.getServiceBoxes();
			String[] msStatutes = stStatutesubForm.getMsStatuteBoxes();
			 
			int size=products.length+services.length;
			List<TsStatutesubLegalitem> tsStatutesubLegalitems =new ArrayList<TsStatutesubLegalitem>(size);
			for (int i = 0; i < services.length; i++) {
				TsStatutesubLegalitem tsStatutesubLegalitem =new TsStatutesubLegalitem();
				TsStatutesubLegalitemPK pk = new TsStatutesubLegalitemPK();
				stStatutesub.setStStatutesubId(stStatutesubId);
				MsLegalitem msLegalitem =new MsLegalitem();
				msLegalitem.setLegalitemId(Long.parseLong(services[i]));
				pk.setStStatutesub(stStatutesub);
				pk.setMsLegalitem(msLegalitem);
				tsStatutesubLegalitem.setId(pk);
				tsStatutesubLegalitems.add(tsStatutesubLegalitem);
			} 
			for (int i = 0; i < products.length; i++) {
				TsStatutesubLegalitem tsStatutesubLegalitem =new TsStatutesubLegalitem();
				TsStatutesubLegalitemPK pk = new TsStatutesubLegalitemPK();
				stStatutesub.setStStatutesubId(stStatutesubId);
				MsLegalitem msLegalitem =new MsLegalitem();
				msLegalitem.setLegalitemId(Long.parseLong(products[i]));
				pk.setStStatutesub(stStatutesub);
				pk.setMsLegalitem(msLegalitem);
				tsStatutesubLegalitem.setId(pk);
				tsStatutesubLegalitems.add(tsStatutesubLegalitem);
			} 
			List<TsRelStatute> tsRelStatutes =new ArrayList<TsRelStatute>(size);
			for (int i = 0; i < msStatutes.length; i++) {
				TsRelStatute tsRelStatute =new TsRelStatute();
				TsRelStatutePK pk = new TsRelStatutePK();
				stStatutesub.setStStatutesubId(stStatutesubId);
				MsStatute msStatute =new MsStatute();
				msStatute.setStatuteId(Long.parseLong(msStatutes[i]));
				pk.setStStatutesub(stStatutesub);
				pk.setMsStatute(msStatute);
				tsRelStatute.setId(pk);
				tsRelStatutes.add(tsRelStatute);
			}
			exciseLawService.saveTsRelStatute(tsRelStatutes);
			exciseLawService.saveTsStatutesubLegalitem(tsStatutesubLegalitems);
			stStatutesubForm.setIsSusses("1"); 
		}else if(action.equals("doEdit")){			 
			 exciseLawService.updateStStatutesub(stStatutesub);
			 stStatutesubForm.setIsSusses("1"); 
		}else if(action.equals("doDelete")){
			
			 exciseLawService.deleteStStatutesub(stStatutesub);
			 stStatutesubForm = new StStatutesubForm();
			 stStatutesubForm.setAction(action);
			 stStatutesubForm.setIsSusses("1");
			 model.addAttribute("stStatutesubForm",stStatutesubForm);
		}
		else if(action.equals("doSearch")){  
			Long productId = stStatutesubForm.getProductId();
			model.addAttribute("stStatutesubs",exciseLawService.searchStStatutesub(stStatutesub,new Paging(),productId).get(0));
			forword="information_law/lawlist";
		}
		return forword;
		 
	}
	@RequestMapping("/input")
	public String input() {
		logger.info( "InformationLawController>>input" );	 
		return "input";
	}	
	@ModelAttribute("msStatutes")
	public List<MsStatute> populateMsStatutes() {
		System.out.println( "InformationLawController>>populateMsStatutes" );
		return this. exciseLawService.listMsStatutes(null);
	}
	@ModelAttribute("msSubMatres")
	public List<MsSubmatr> populatemsSubMatres() {
		System.out.println( "InformationLawController>>populatemsSubMatres" );
		return this. exciseLawService.listMsSubmatrs();
	}
	
	@RequestMapping("/MATRGROUP/MatrGroup")
	public String matrGroup(@RequestParam(value="action",required = false) String action
			,@RequestParam(value="matrgroupId",required = false) String matrgroupId,Model model) {
		logger.info( "InformationLawController>>matrGroup" );	
		MsMatrgroupForm msMatrgroupForm =new MsMatrgroupForm();
		//action = (action!=null && !action.equals(""))?action:"list";
		String forword  ="information_law/matrGroup";
		if(action!=null){
			if(action.equals("search")){
				model.addAttribute("msMatrgroups",exciseLawService.searchMsMatrgroup(new MsMatrgroup(),new Paging()).get(0));
				forword="information_law/matrGroupList";
				//action = "search";
			}
			else if(action.equals("edit")){
				MsMatrgroup  msMatrgroup = exciseLawService.findMsMatrgroupById(Long.parseLong(matrgroupId));
				msMatrgroupForm.setMsMatrgroup(msMatrgroup);
				//action ="edit";
			}
		} 
		action = (action!=null && !action.equals(""))?action:"list";
		msMatrgroupForm.setAction(action); 
		model.addAttribute("msMatrgroupForm", msMatrgroupForm); 
		return forword;
		 
	}
	@RequestMapping(value="/MATRGROUP/MatrGroup",method = RequestMethod.POST)
	public String processSubmitMatrGroup(@RequestParam(value="action",required = false) String action,
			@ModelAttribute("msMatrgroupForm") MsMatrgroupForm msMatrgroupForm,
			BindingResult result,
			Model model, SessionStatus status) {
		String forword="information_law/matrGroup";
		MsMatrgroup msMatrgroup = msMatrgroupForm.getMsMatrgroup();
		if(action.equals("doSave")){
			 exciseLawService.saveMsMatrgroup(msMatrgroup);
			 msMatrgroupForm.setIsSusses("1");
			 System.out.println(""+msMatrgroup.getName());
			 System.out.println(""+msMatrgroup.getMsStatute().getStatuteId());
			 System.out.println(""+msMatrgroup.getMsSubmatr().getSubmatrId());
			 System.out.println(""+msMatrgroup.getMatrgroupOrder());
		}else if(action.equals("doEdit")){			 
			 exciseLawService.updateMsMatrgroup(msMatrgroup);
			 msMatrgroupForm.setIsSusses("1");
			 System.out.println(""+msMatrgroup.getName());
			 System.out.println(""+msMatrgroup.getMsStatute().getStatuteId());
			 System.out.println(""+msMatrgroup.getMsSubmatr().getSubmatrId());
			 System.out.println(""+msMatrgroup.getMatrgroupOrder());
		}else if(action.equals("doDelete")){
			 exciseLawService.deleteMsMatrgroup(msMatrgroup);
			 msMatrgroupForm = new MsMatrgroupForm();
			 msMatrgroupForm.setAction(action);
			 msMatrgroupForm.setIsSusses("1");
			 model.addAttribute("msMatrgroupForm",msMatrgroupForm);
		}
		else if(action.equals("doSearch")){
		//	MsMatrgroup msMatrgroup =msMatrgroupForm.getMsMatrgroup();
			System.out.println("msMatrgroup.getName()="+msMatrgroup.getName());
			model.addAttribute("msMatrgroups",exciseLawService.searchMsMatrgroup(msMatrgroup,new Paging()).get(0));
			forword="information_law/matrGroupList";
		}
		return forword;
		 
	}
	@RequestMapping("/information_law/SubMatr")
	public String subMatr(@RequestParam(value="action",required = false) String action
			,@RequestParam(value="submatrId",required = false) String submatrId,Model model) {
		logger.info( "InformationLawController>>subMatr" );	 
		MsSubmatrForm msSubmatrForm =new MsSubmatrForm();
		String forword  ="information_law/subMatr";
		if(action!=null){
			if(action.equals("search")){
				model.addAttribute("msSubmatrs",exciseLawService.searchMsSubmatr(new MsSubmatr(),new Paging()).get(0));
				forword="information_law/subMatrList";
			}
			else if(action.equals("edit")){
				MsSubmatr  msSubmatr = exciseLawService.findMsSubmatrById(Long.parseLong(submatrId));
				msSubmatrForm.setMsSubmatr(msSubmatr);
			}
		} 
		action = (action!=null && !action.equals(""))?action:"list";
		msSubmatrForm.setAction(action); 
		model.addAttribute("msSubmatrForm", msSubmatrForm); 
		return forword;
	} 
	@RequestMapping(value="/information_law/SubMatr",method = RequestMethod.POST)
	public String processSubmitSubMatr(@RequestParam(value="action",required = false) String action,
			@ModelAttribute("msSubmatrForm") MsSubmatrForm msSubmatrForm,
			BindingResult result,
			Model model, SessionStatus status) {
		String forword="information_law/subMatr";
		MsSubmatr msSubmatr = msSubmatrForm.getMsSubmatr();
		if(action.equals("doSave")){
			 exciseLawService.saveMsSubmatr(msSubmatr);
			 msSubmatrForm.setIsSusses("1");
		 
		}else if(action.equals("doEdit")){			 
			 exciseLawService.updateMsSubmatr(msSubmatr);
			 msSubmatrForm.setIsSusses("1"); 
		}else if(action.equals("doDelete")){
			 exciseLawService.deleteMsSubmatr(msSubmatr);
			 msSubmatrForm = new MsSubmatrForm();
			 msSubmatrForm.setAction(action);
			 msSubmatrForm.setIsSusses("1");
			 model.addAttribute("msSubmatrForm",msSubmatrForm);
		}
		else if(action.equals("doSearch")){
			model.addAttribute("msSubmatrs",exciseLawService.searchMsSubmatr(msSubmatr,new Paging()).get(0));
			forword="information_law/subMatrList";
		}
		return forword;
		 
	}
}
