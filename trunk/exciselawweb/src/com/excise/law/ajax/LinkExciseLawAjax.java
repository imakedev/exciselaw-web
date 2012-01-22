package com.excise.law.ajax;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.excise.law.domain.MsStatute;
import com.excise.law.domain.MsStatutesub;
import com.excise.law.domain.StMatr;
import com.excise.law.domain.StStatutesub;
import com.excise.law.domain.TmArticleGroup;
import com.excise.law.domain.TmArticleSection;
import com.excise.law.domain.TmLawType;
import com.excise.law.domain.TmStatue;
import com.excise.law.domain.TsArticle;
import com.excise.law.domain.TsExArticleCompleted;
import com.excise.law.domain.TsExArticleHeader;
import com.excise.law.domain.TsLaw;
import com.excise.law.domain.TsRel;
import com.excise.law.domain.TsRelDTO;
import com.excise.law.domain.TsRelKeyMap;
import com.excise.law.domain.TsRelMap;
import com.excise.law.domain.TsRelTableMap;
import com.excise.law.service.LinkExciseLawService;

public class LinkExciseLawAjax {
	private final LinkExciseLawService linkExciseLawService; 
	
	public LinkExciseLawAjax() { 
			WebContext ctx = WebContextFactory.get();
			
			ctx.getHttpServletRequest().getSession(true).getAttribute("aoe");
			ServletContext servletContext = ctx.getServletContext();
	    	WebApplicationContext wac = WebApplicationContextUtils.
	    	getRequiredWebApplicationContext(servletContext);
	    	linkExciseLawService = (LinkExciseLawService)wac.getBean("linkExciseLawService"); 
	}
	public List<TsRelTableMap> listRelTableMap(){
		return linkExciseLawService.listRelTableMap();
	}
	public Long saveRelKeyMap(TsRelKeyMap persistentInstance){
		return linkExciseLawService.saveRelKeyMap(persistentInstance);
	}	
	public int updateRelKeyMap(TsRelKeyMap transientInstance){
		return linkExciseLawService.updateRelKeyMap(transientInstance);
	}	
	public void deleteRelKeyMap(TsRelKeyMap persistentInstance){
		linkExciseLawService.deleteRelKeyMap(persistentInstance);
	}  
	public TsRelKeyMap getRelKeyMapById(Long rkmId){
		return linkExciseLawService.getRelKeyMapById(rkmId);
	} 
	
	
	public MsStatute getMsStatute(Long statuteId){
		return linkExciseLawService.getMsStatute(statuteId);
	}
	public List<StStatutesub> listStStatutesub(){
		/*WebContext ctx = WebContextFactory.get();
		HttpServletRequest req1=ctx.getHttpServletRequest();
		HttpSession session1=req1.getSession();
		Object obj=session1.getAttribute("aoe");
	//	HttpSession session1=req1.getSession();
		HttpSession session2=req1.getSession(true);
		
		Object obj2=session2.getAttribute("aoe");
		
		Object obj3=session2.getAttribute("aoe2");
		Object obj4=session2.getAttribute("aoe2");*/
		return linkExciseLawService.listStStatutesub();
	}
	
	// link 1
	public List<MsStatute> listMsStatute(){
		return linkExciseLawService.listMsStatute();
	}
	public List<StMatr> listStMatrByMsStatute(MsStatute msStatute){
		 
		return linkExciseLawService.listStMatrByMsStatute(msStatute);
	}
	public StMatr getStMatr(Long stMatrId){
		return linkExciseLawService.getStMatr(stMatrId);
	}
	// link 2
	public List<MsStatutesub> listMsStatutesub(){
		 
		return linkExciseLawService.listMsStatutesub();
	}
	public List<StStatutesub> listStStatutesubByMsStatutesub(MsStatutesub msStatutesub){
		 
		return linkExciseLawService.listStStatutesubByMsStatutesub(msStatutesub);
	}
	public StStatutesub getStStatutesub(Long stStatutesubId){
		return linkExciseLawService.getStStatutesub(stStatutesubId);
	}
	// link 
	public List listMsStatuteAndMsStatutesub(){
		 
		return linkExciseLawService.listMsStatuteAndMsStatutesub();
	} 

	public List<StStatutesub> listStStatutesub(MsStatute msStatute,MsStatutesub msStatutesub){
		 
		return linkExciseLawService.listStStatutesub(msStatute,msStatutesub);
	}
	public TsRelMap getRelMapById(Long rmId){
		return linkExciseLawService.getRelMapById(rmId);
	}
	
	public Long saveRelMap(TsRelMap persistentInstance){
		return linkExciseLawService.saveRelMap(persistentInstance);
	}	
	public int updateRelMap(TsRelMap transientInstance){
		return linkExciseLawService.updateRelMap(transientInstance);
	}	
	public void deleteRelMap(TsRelMap persistentInstance){
		linkExciseLawService.deleteRelMap(persistentInstance);
	} 
	
	public List<TsRelDTO> listTsRel(Long rmId){
		return linkExciseLawService.listTsRel(rmId);
	}
	public TsRel getTsRelById(Long trId){
		return linkExciseLawService.getTsRelById(trId);
	}
	public Long saveTsRel(TsRel persistentInstance){
		return linkExciseLawService.saveTsRel(persistentInstance);
	}
	public int updateTsRel(TsRel transientInstance){
		return linkExciseLawService.updateTsRel(transientInstance);
	}
	public List<TsRelDTO>  deleteTsRel(TsRel persistentInstance){
		return linkExciseLawService.deleteTsRel(persistentInstance);
	}
	
	// new Link
	public List initLink(){
		List list=new ArrayList();
		list.add(linkExciseLawService.listTmStatues());
		list.add(linkExciseLawService.listTmLawTypes());
		return list;
	}
	public List loadGroupSection(String type,String statueId, String lawTypeId){//type=1 compleate,2=article,3=ext
		List list=new ArrayList();
		if(type.equals("1")){
			list.add(linkExciseLawService.listTmArticleGroups());
			list.add(linkExciseLawService.listTmArticleSections());
			list.add(linkExciseLawService.listTsExArticleCompleteds(statueId,lawTypeId));
		}else if(type.equals("2")){
			list.add(linkExciseLawService.listTmArticleGroups());
			list.add(linkExciseLawService.listTmArticleSections());
			list.add(linkExciseLawService.listTsArticles(statueId,lawTypeId));
		}/*else if(type.equals("3")){
			return linkExciseLawService.listTsLaws(null,null);
		} */
		return list;
	}
	public List<TmStatue> listTmStatues(){
		return linkExciseLawService.listTmStatues();
	}
	public List<TmLawType> listTmLawTypes()    {
		return linkExciseLawService.listTmLawTypes();
	}	
	/*public List loadGroupSection(String type,String statueId, String lawTypeId){
		List list=new ArrayList();
		list.add(linkExciseLawService.listTmArticleGroups());
		list.add(linkExciseLawService.listTmArticleSections();
		return list;
	}*/
	public List<TmArticleGroup> listTmArticleGroups(){
			return linkExciseLawService.listTmArticleGroups();
	}
	public List<TmArticleSection> listTmArticleSections(){
			return linkExciseLawService.listTmArticleSections();
	}
	
	public List<TsExArticleCompleted> listTsExArticleCompleteds(String statueId, String lawTypeId){
			return linkExciseLawService.listTsExArticleCompleteds(statueId,lawTypeId);
	}
	public List<TsExArticleHeader> listTsExArticleHeader(String type,String statueId, String lawTypeId){
			return linkExciseLawService.listTsExArticleHeader(type,statueId, lawTypeId);
	}
	public List<TsLaw> listTsLaws(String statueId, String lawTypeId){
		   
			return linkExciseLawService.listTsLaws( statueId,  lawTypeId);
	}
	public List<TsRelDTO> listExistingLink(Long rmId){
		return linkExciseLawService.listExistingLink(rmId);
	}
	public TsLaw getTsLaw(Long lawId){
		return linkExciseLawService.getTsLaw(lawId);
	}
	public List<TsArticle> getTsArticleList(String groupId){
		return linkExciseLawService.getTsArticleList(groupId);
	}
	public TsExArticleCompleted getTsExArticleCompleted(Long articleCompletedId){
		return linkExciseLawService.getTsExArticleCompleted(articleCompletedId);
	}
	public TsArticle getTsArticle(Long articleId){
		return linkExciseLawService.getTsArticle(articleId);
	}
}
