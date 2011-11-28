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
import com.exciselaw.law.form.NewsForm;
import com.exciselaw.law.service.ExciseLawService;
import com.exciselaw.law.utils.DateUtils;

@Controller
@SessionAttributes({"newsForm"})
public class NewsController {
	private final ExciseLawService exciseLawService;
	private final String STATUS_INACTIVE = "I";
	private final String SCREEN_ID10 = "10";
	private final long FILESIZE = 5000000;
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(NewsController.class);
	private static ResourceBundle resources = ResourceBundle.getBundle( "file" , Locale.ENGLISH );
	
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
	public NewsController(ExciseLawService exciseLawService) {
		this.exciseLawService = exciseLawService;
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
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		userInfo = (TmUserinfo) session.getAttribute("userInfo");
		String forword  ="news/newsList";
		if(action!=null){
			if(action.equals("add")){			
				logger.info( "NewsController>>news.POST add " );	
				newsForm = new NewsForm();
				forword="news/newsDetail";
			}else if(action.equals("edit")){
				logger.info( "NewsController>>news.POST edit " ); 
				TsNews tsNews = exciseLawService.findTsNewsByNewsId(Long.valueOf(newsId));				
				
				if(tsNews.getCreatedDate()!=null){
					newsForm.setCreatedDate(DateUtils.getStringDateByDateTh(tsNews.getCreatedDate().getTime()));
				}			
				if(tsNews.getNewsStartDate()!=null){
					newsForm.setNewsStartDate(DateUtils.getStringDateByDateTh(tsNews.getNewsStartDate().getTime()));
				}		
				if(tsNews.getNewsEndDate()!=null){
					newsForm.setNewsEndDate(DateUtils.getStringDateByDateTh(tsNews.getNewsEndDate().getTime()));
				}

				model.addAttribute("tsNewsAttachList", exciseLawService.findTsNewsAttachListByNewsId(Long.parseLong(newsId)));	
				newsForm.setTsNews(tsNews);
				forword="news/newsDetail";
			}
		} 
		setActionList(newsForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID10));
		newsForm.setAction(action);
		model.addAttribute("newsDisplayTypeList", newsDisplayTypeList);
		model.addAttribute("newsForm", newsForm); 
		return forword;		 
	}
	
	@RequestMapping(value="/news/news",method = RequestMethod.POST)
	public String processConsultDocNew(ServletRequest request, ServletResponse response, 
				@RequestParam(value="action",required = false) String action,
				@ModelAttribute("newsForm") NewsForm newsForm,
				BindingResult result,
				Model model, SessionStatus status) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		userInfo = (TmUserinfo) session.getAttribute("userInfo");
		String forword  ="news/newsList";
		TsNews tsNews = newsForm.getTsNews();
		DateTime nowTh = new DateTime(new Date().getTime());
		

		if(newsForm.getNewsStartDate()!=null && newsForm.getNewsStartDate().length()>0){
			tsNews.setNewsStartDate(DateUtils.getDateByStringDateTh(newsForm.getNewsStartDate()));
		}
		if(newsForm.getNewsEndDate()!=null && newsForm.getNewsEndDate().length()>0){
			tsNews.setNewsEndDate(DateUtils.getDateByStringDateTh(newsForm.getNewsEndDate()));
		}
		
		if(action.equals("doSave")){
			logger.info( "NewsController>>news.POST doSave " );
			
			tsNews.setCreatedBy(userInfo.getUserId());
			tsNews.setUpdatedBy(userInfo.getUserId());
			tsNews.setCreatedDate(nowTh.toDate());
			tsNews.setUpdatedDate(nowTh.toDate());

			MultipartFile filePic = newsForm.getFilePic();
        	if(filePic.getOriginalFilename() !=null && filePic.getSize() > 0){
            	try {
					tsNews.setNewsImage(filePic.getBytes());
				} catch (IOException e) {
					e.printStackTrace();
				}
            	tsNews.setNewsImgName(filePic.getOriginalFilename());
        	}
			Long newsId = exciseLawService.saveTsNews(tsNews);
        	
			MultipartFile[] files = newsForm.getFileData();
			if(files != null && files.length>0){
	            for(MultipartFile file : files){
	            	if(file.getOriginalFilename() !=null && file.getSize() > 0){
	            		if(file.getSize() < FILESIZE){
			            	TsNewsAttach tsNewsAttach = saveTsNewsAttachFile(file, Long.valueOf(newsId), userInfo.getUserId(), nowTh.toDate());
							exciseLawService.saveTsNewsAttach(tsNewsAttach);
	            		}else{
	            			newsForm.setIsSusses("2");//TODO
	            			break;
	            		}
	            	}
	            }	
			}
			
			newsForm.setIsSusses("1");
			forword="news/newsDetail";
		}else if(action.equals("doEdit")){		
			logger.info( "NewsController>>news.POST doEdit " );
			
			tsNews.setUpdatedBy(userInfo.getUserId());
			tsNews.setUpdatedDate(nowTh.toDate());
			
			MultipartFile filePic = newsForm.getFilePic();
        	if(filePic.getOriginalFilename() !=null && filePic.getSize() > 0){
            	try {
					tsNews.setNewsImage(filePic.getBytes());
				} catch (IOException e) {
					e.printStackTrace();
				}
            	tsNews.setNewsImgName(filePic.getOriginalFilename());
        	}
        	exciseLawService.updateTsNews(tsNews);
			
			String [] delAttachFile = newsForm.getDeleteFileBoxes();
			if(delAttachFile!=null && delAttachFile.length>0){
				TsNewsAttach tsNewsAttach = null;
				for(String attachId : delAttachFile){
					tsNewsAttach = new TsNewsAttach();
					tsNewsAttach.setAttachId(Long.valueOf(attachId));
					exciseLawService.deleteTsNewsAttach(tsNewsAttach);
				}
			}
			
			MultipartFile[] files = newsForm.getFileData();
			if(files != null && files.length>0){
	            for(MultipartFile file : files){
	            	if(file.getOriginalFilename() !=null && file.getSize() > 0){
		            	TsNewsAttach tsNewsAttach = saveTsNewsAttachFile(file, tsNews.getNewsId(), userInfo.getUserId(), nowTh.toDate());
						exciseLawService.saveTsNewsAttach(tsNewsAttach);
	            	}
	            }	
			}
			model.addAttribute("tsNewsAttachList", exciseLawService.findTsNewsAttachListByNewsId(tsNews.getNewsId()));
			newsForm.setIsSusses("1"); 
			forword="news/newsDetail";
		}else if(action.equals("doDelete")){			
			logger.info( "NewsController>>news.POST doDelete " );
			tsNews.setNewsStatus(STATUS_INACTIVE);
			exciseLawService.updateTsNews(tsNews);
			newsForm = new NewsForm();
			newsForm.setAction(action);
			newsForm.setIsSusses("1");
			forword="news/newsDetail";
		}
		setActionList(newsForm, exciseLawService.getActionListByRoleIdScreenId(userInfo.getRoleId(), SCREEN_ID10));
		model.addAttribute("newsDisplayTypeList", newsDisplayTypeList);
		model.addAttribute("newsForm",newsForm);
		return forword;		 
	}

//	private String setNewsPicFile(MultipartFile filePicUpload, long newsId){
//    	String filePath = resources.getString("news_path")+"/"+newsId;
//		FileOutputStream fos = null;
//		if (filePicUpload.getSize() > 0) {
//	        try {
//		        byte[] bytes = filePicUpload.getBytes();		 
//	
//	        	File fileDir = new File(filePath);
//	        	if(!fileDir.exists()){
//	        		fileDir.mkdirs();
//	        	}
//	        	File file = new File(fileDir, filePicUpload.getOriginalFilename());
//		        fos = new FileOutputStream(file);
//		        fos.write(bytes);
//		        fos.flush();
//		        fos.close();
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			} finally {
//				try { fos.close(); } catch (IOException e) { e.printStackTrace(); }
//			}
//	    }
//		return filePath;
//	}	
	
	private TsNewsAttach saveTsNewsAttachFile(MultipartFile fileUpload, long newsId, String userId, Date today){
		TsNewsAttach tsNewsAttach = new TsNewsAttach();
		FileOutputStream fos = null;
		if (fileUpload.getSize() > 0) {
            try {
		        byte[] bytes = fileUpload.getBytes();		 

            	String filePath = resources.getString("news_path")+"/"+newsId;
            	File fileDir = new File(filePath);
            	if(!fileDir.exists()){
            		fileDir.mkdirs();
            	}
            	File file = new File(fileDir, fileUpload.getOriginalFilename());
		        fos = new FileOutputStream(file);
		        fos.write(bytes);
		        fos.flush();
		        fos.close();
		        
		        tsNewsAttach.setAttachFileName(fileUpload.getOriginalFilename());
		        tsNewsAttach.setAttachFilePath(filePath);
		        tsNewsAttach.setNewsId(BigDecimal.valueOf(newsId));
		        tsNewsAttach.setCreatedBy(userInfo.getUserId());
		        tsNewsAttach.setCreatedDate(today);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try { fos.close(); } catch (IOException e) { e.printStackTrace(); }
			}
	    }
		return tsNewsAttach;
	}
	
	private void setActionList(NewsForm newsForm, String actionList){
		if(actionList.indexOf("ADD") != -1){
			newsForm.setAdd("true");
		}
		if(actionList.indexOf("EDIT") != -1){
			newsForm.setEdit("true");
		}
		if(actionList.indexOf("DELETE") != -1){
			newsForm.setDelete("true");
		}
		if(actionList.indexOf("VIEW") != -1){
			newsForm.setView("true");
		}		
	}
}
