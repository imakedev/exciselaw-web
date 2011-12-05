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
import com.excise.law.form.NewsForm;
import com.excise.law.service.LawService;

@Controller
@SessionAttributes({"newsForm"})
public class NewsController {
	private final LawService lawService;
	private final String STATUS_INACTIVE = "I";
	private final String SCREEN_ID10 = "10";
	private final long FILESIZE = 5000000;
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(NewsController.class);
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
	public NewsController(LawService lawService) {
		this.lawService = lawService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
		dataBinder.setDisallowedFields("id");
	}
	
	@RequestMapping("/news/news")
	public String news(ServletRequest request, ServletResponse response,
				@RequestParam(value="action",required = false) String action,
				@RequestParam(value="newsId",required = false) String newsId, Model model) {
		NewsForm newsForm = new NewsForm();
		String forword  ="news/newsList";
		
		if(action!=null && action.equals("edit")){
			forword="news/newsDetail";
		}
		model.addAttribute("newsForm",newsForm);
		return forword;		 
	}
	
//	@RequestMapping(value="/news/news",method = RequestMethod.POST)
//	public String processConsultDocNew(ServletRequest request, ServletResponse response, 
//				@RequestParam(value="action",required = false) String action,
//				@ModelAttribute("newsForm") NewsForm newsForm,
//				BindingResult result,
//				Model model, SessionStatus status) {
//		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//		HttpSession session = httpServletRequest.getSession();
//		userInfo = (TmUserinfo) session.getAttribute("userInfo");
//		String forword  ="news/newsList";
//		TsNews tsNews = newsForm.getTsNews();
//		DateTime nowTh = new DateTime(new Date().getTime());
//		
//
//		if(newsForm.getNewsStartDate()!=null && newsForm.getNewsStartDate().length()>0){
//			tsNews.setNewsStartDate(DateUtils.getDateByStringDateTh(newsForm.getNewsStartDate()));
//		}
//		if(newsForm.getNewsEndDate()!=null && newsForm.getNewsEndDate().length()>0){
//			tsNews.setNewsEndDate(DateUtils.getDateByStringDateTh(newsForm.getNewsEndDate()));
//		}
//		
//		if(action.equals("doSave")){
//			logger.info( "NewsController>>news.POST doSave " );
//			
//			tsNews.setCreatedBy(userInfo.getUserId());
//			tsNews.setUpdatedBy(userInfo.getUserId());
//			tsNews.setCreatedDate(nowTh.toDate());
//			tsNews.setUpdatedDate(nowTh.toDate());
//
//			MultipartFile filePic = newsForm.getFilePic();
//        	if(filePic.getOriginalFilename() !=null && filePic.getSize() > 0){
//            	try {
//					tsNews.setNewsImage(filePic.getBytes());
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//            	tsNews.setNewsImgName(filePic.getOriginalFilename());
//        	}
//			Long newsId = lawService.saveTsNews(tsNews);
//        	
//			MultipartFile[] files = newsForm.getFileData();
//			if(files != null && files.length>0){
//	            for(MultipartFile file : files){
//	            	if(file.getOriginalFilename() !=null && file.getSize() > 0){
//	            		if(file.getSize() < FILESIZE){
//			            	TsNewsAttach tsNewsAttach = saveTsNewsAttachFile(file, Long.valueOf(newsId), userInfo.getUserId(), nowTh.toDate());
//							lawService.saveTsNewsAttach(tsNewsAttach);
//	            		}else{
//	            			newsForm.setIsSusses("2");//TODO
//	            			break;
//	            		}
//	            	}
//	            }	
//			}
//			
//			newsForm.setIsSusses("1");
//			forword="news/newsDetail";
//		}else if(action.equals("doEdit")){		
//			logger.info( "NewsController>>news.POST doEdit " );
//			
//			tsNews.setUpdatedBy(userInfo.getUserId());
//			tsNews.setUpdatedDate(nowTh.toDate());
//			
//			MultipartFile filePic = newsForm.getFilePic();
//        	if(filePic.getOriginalFilename() !=null && filePic.getSize() > 0){
//            	try {
//					tsNews.setNewsImage(filePic.getBytes());
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//            	tsNews.setNewsImgName(filePic.getOriginalFilename());
//        	}
//        	lawService.updateTsNews(tsNews);
//			
//			String [] delAttachFile = newsForm.getDeleteFileBoxes();
//			if(delAttachFile!=null && delAttachFile.length>0){
//				TsNewsAttach tsNewsAttach = null;
//				for(String attachId : delAttachFile){
//					tsNewsAttach = new TsNewsAttach();
//					tsNewsAttach.setAttachId(Long.valueOf(attachId));
//					lawService.deleteTsNewsAttach(tsNewsAttach);
//				}
//			}
//			
//			MultipartFile[] files = newsForm.getFileData();
//			if(files != null && files.length>0){
//	            for(MultipartFile file : files){
//	            	if(file.getOriginalFilename() !=null && file.getSize() > 0){
//		            	TsNewsAttach tsNewsAttach = saveTsNewsAttachFile(file, tsNews.getNewsId(), userInfo.getUserId(), nowTh.toDate());
//						lawService.saveTsNewsAttach(tsNewsAttach);
//	            	}
//	            }	
//			}
//			model.addAttribute("tsNewsAttachList", lawService.findTsNewsAttachListByNewsId(tsNews.getNewsId()));
//			newsForm.setIsSusses("1"); 
//			forword="news/newsDetail";
//		}else if(action.equals("doDelete")){			
//			logger.info( "NewsController>>news.POST doDelete " );
//			tsNews.setNewsStatus(STATUS_INACTIVE);
//			lawService.updateTsNews(tsNews);
//			newsForm = new NewsForm();
//			newsForm.setAction(action);
//			newsForm.setIsSusses("1");
//			forword="news/newsDetail";
//		}
//		setActionList(newsForm, lawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID10));
//		model.addAttribute("newsDisplayTypeList", newsDisplayTypeList);
//		model.addAttribute("newsForm",newsForm);
//		return forword;		 
//	}
}
