package com.exciselaw.law.ajax;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.exciselaw.law.domain.ConsultDocFilter;
import com.exciselaw.law.domain.MsLegalitem;
import com.exciselaw.law.domain.MsMatrgroup;
import com.exciselaw.law.domain.MsStatute;
import com.exciselaw.law.domain.MsStatutesub;
import com.exciselaw.law.domain.MsSubmatr;
import com.exciselaw.law.domain.NewsFilter;
import com.exciselaw.law.domain.StMatr;
import com.exciselaw.law.domain.StStatutesub;
import com.exciselaw.law.domain.TmArticleGroup;
import com.exciselaw.law.domain.TmArticleSection;
import com.exciselaw.law.domain.TmConsultDoc;
import com.exciselaw.law.domain.TmLawType;
import com.exciselaw.law.domain.TmStatue;
import com.exciselaw.law.domain.TsArticle;
import com.exciselaw.law.domain.TsDiscussPublished;
import com.exciselaw.law.domain.TsDocResolution;
import com.exciselaw.law.domain.TsExArticleCompleted;
import com.exciselaw.law.domain.TsExArticleHeader;
import com.exciselaw.law.domain.TsJudgement;
import com.exciselaw.law.domain.TsLaw;
import com.exciselaw.law.domain.TsMasterWord;
import com.exciselaw.law.domain.TsNews;
import com.exciselaw.law.domain.TsRelStatute;
import com.exciselaw.law.domain.TsRelStatutePK;
import com.exciselaw.law.domain.TsStatuteLegalitem;
import com.exciselaw.law.domain.TsStatuteLegalitemPK;
import com.exciselaw.law.domain.TsStatutesubLegalitem;
import com.exciselaw.law.domain.TsStatutesubLegalitemPK;
import com.exciselaw.law.domain.TsTariff;
import com.exciselaw.law.service.ExciseLawService;
import com.exciselaw.law.utils.Paging;

 
public class ExciseLawAjax {
	
	private final ExciseLawService exciseLawService;
	
	public ExciseLawAjax() { 
		WebContext ctx = WebContextFactory.get(); 

		ServletContext servletContext = ctx.getServletContext();
    	WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
    	exciseLawService = (ExciseLawService)wac.getBean("exciseLawService");
	}

	public String checkLogin(String userId, String password){
		return userId +" ท่านได้รับสิทธิในการเข้าสู่ระบบ";
	}
	
	public String test(String args){
		return "reply from server:"+args;
	}
	public List getMsMatrgroups(MsMatrgroup msMatrgroup, Paging paging){
		return exciseLawService.searchMsMatrgroup(msMatrgroup, paging);
	}
	public List getMsSubmatrs(MsSubmatr msSubmatr, Paging paging){
		return exciseLawService.searchMsSubmatr(msSubmatr,  paging);
	}
	public List getMsStatutes(MsStatute msStatute, Paging paging){
		return exciseLawService.searchMsStatute(msStatute, paging);
	}
	public List getStMatrs(StMatr stMatr, Paging paging){
		return exciseLawService.searchStMatr(stMatr, paging);
	}
	public List getStStatutesubs(StStatutesub stStatutesub, Paging paging,Long productId){
		return exciseLawService.searchStStatutesub(stStatutesub, paging,productId);
	}
	
	/*public List getMsStatutes(MsStatute msStatute, Paging paging){
		return exciseLawService.searchMsStatute(new MsStatute(), paging);
	}*/
	
	public int setTsStatuteLegalitem(Long legalitemId,Long statuteId,String action){
		TsStatuteLegalitem tsStatuteLegalitem =new TsStatuteLegalitem();
		TsStatuteLegalitemPK pk = new TsStatuteLegalitemPK();
		MsStatute msStatute =new MsStatute();
		MsLegalitem msLegalitem =new MsLegalitem();
		msStatute.setStatuteId(statuteId);
		msLegalitem.setLegalitemId(legalitemId);
		pk.setMsStatute(msStatute);
		pk.setMsLegalitem(msLegalitem);
		tsStatuteLegalitem.setId(pk);
		if(action.equals("check")){
			exciseLawService.saveTsStatuteLegalitem(tsStatuteLegalitem);
		}else if(action.equals("uncheck")){
			exciseLawService.deleteTsStatuteLegalitem(tsStatuteLegalitem); 
		} 
		return 0;
	}
	public int setTsStatutesubLegalitem(Long legalitemId,Long stStatutesubId,String action){
		TsStatutesubLegalitem tsStatutesubLegalitem =new TsStatutesubLegalitem();
		TsStatutesubLegalitemPK pk = new TsStatutesubLegalitemPK(); 
		StStatutesub StStatutesub =new StStatutesub(); 
		MsLegalitem msLegalitem =new MsLegalitem();
		StStatutesub.setStStatutesubId(stStatutesubId); 
		msLegalitem.setLegalitemId(legalitemId);
		pk.setStStatutesub(StStatutesub);
		pk.setMsLegalitem(msLegalitem);		
		tsStatutesubLegalitem.setId(pk);
		if(action.equals("check")){
			exciseLawService.saveTsStatutesubLegalitem(tsStatutesubLegalitem);
		}else if(action.equals("uncheck")){
			exciseLawService.deleteTsStatutesubLegalitem(tsStatutesubLegalitem); 
		} 
		return 0;
	}
	public int setTsRelStatute(Long statuteId,Long stStatutesubId,String action){
		TsRelStatute tsRelStatute =new TsRelStatute();
		TsRelStatutePK pk = new TsRelStatutePK(); 
		StStatutesub stStatutesub =new StStatutesub(); 
		MsStatute msStatute =new MsStatute(); 
		stStatutesub.setStStatutesubId(stStatutesubId); 
		msStatute.setStatuteId(statuteId);
		pk.setStStatutesub(stStatutesub);
		pk.setMsStatute(msStatute);		
		tsRelStatute.setId(pk);
		if(action.equals("check")){
			exciseLawService.saveTsRelStatute(tsRelStatute);
		}else if(action.equals("uncheck")){
			exciseLawService.deleteTsRelStatute(tsRelStatute); 
		} 
		return 0;
	}
	//model.addAttribute("msMatrgroups",exciseLawService.searchMsMatrgroup(msMatrgroup,new Paging()).get(0));

	//------------------------------ START LAW -------------------------------------------- //
	
	public List<TmStatue> getTmStatueList(){
		return exciseLawService.getTmStatueList();
	}
	
	public List<TmLawType> getTmLawTypeList(){
		return exciseLawService.getTmLawTypeList();
	}

	public List<TmLawType> getTmLawTypeListByPriority0(){
		return exciseLawService.getTmLawTypeListByPriority0();
	}

	public List searchStatueList(TmStatue instance, Paging paging){
		return exciseLawService.searchStatueList(instance, paging);
	}
	
	public String checkStatueOrder(String statueOrder){
		return exciseLawService.checkStatueOrder(new BigDecimal(statueOrder));
	}
	
	public String findMaxStatueOrder(){
		return exciseLawService.findMaxStatueOrder().toString();
	}
	
	public List searchLawTypeList(TmLawType instance, Paging paging){
		return exciseLawService.searchLawTypeList(instance, paging);
	}
	
	public String checkLawTypeOrder(String lawTypeOrder){
		return exciseLawService.checkLawTypeOrder(new BigDecimal(lawTypeOrder));
	}
	
	public String findMaxLawTypeOrder(){
		return exciseLawService.findMaxLawTypeOrder().toString();
	}
	
	public List searchArticleGroupList(TmArticleGroup instance, Paging paging){
		return exciseLawService.searchArticleGroupList(instance, paging);
	}
	
	public List searchArticleSectionList(TmArticleSection instance, Paging paging){
		return exciseLawService.searchArticleSectionList(instance, paging);
	}
	
	public List getExArticleHeaderListByLawTypeId(String lawTypeId){
		return exciseLawService.getExArticleHeaderListByLawTypeId(new BigDecimal(lawTypeId));
	}
	
	public List searchExArticleHeaderList(TsExArticleHeader instance, Paging paging){
		return exciseLawService.searchExArticleHeadList(instance, paging);
	}
	
	public List searchArticleList(TsArticle instance, String exciseArticleType,Paging paging){
		return exciseLawService.searchArticleList(instance, new BigDecimal(exciseArticleType), paging);
	}
	
	public List<TsExArticleHeader> findTsExArticleHeaderListByStatueIdLawTypeId(String statueId, String lawTypeId){
		return exciseLawService.findTsExArticleHeaderListByStatueIdLawTypeId(new BigDecimal(statueId), new BigDecimal(lawTypeId));
	}
	
	public TsExArticleHeader findTsExArticleHeaderByArticleHeaderId(String articleHeaderId){
		return exciseLawService.findTsExArticleHeaderByArticleHeaderId(Long.valueOf(articleHeaderId));
	}
	
	public TsExArticleCompleted findTsExArticleCompletedByStatueId(String statueId){
		return exciseLawService.findTsExArticleCompletedByStatueId(new BigDecimal(statueId));
	}
	
	public List searchArticleCompletedList(TsExArticleCompleted instance, Paging paging){
		return exciseLawService.searchArticleCompletedList(instance, paging);
	}
	
	public List searchTariffList(TsTariff instance, Paging paging){
		return exciseLawService.searchTariffList(instance, paging);
	}
	
	public List searchLawList(TsLaw instance, String lawExciseStatus, Paging paging){
		return exciseLawService.searchLawList(instance, new BigDecimal(lawExciseStatus), paging);
	}
	//------------------------------ FINISH LAW ------------------------------------------- //
	
	//------------------------------ START MANAGEMENT ------------------------------------- //
	public List searchRole(String roleName, Paging paging){
		return exciseLawService.searchRoleList(roleName, paging);
	}
	
	public List searchScreenList(String screenName, Paging paging){
		return exciseLawService.searchScreenList(screenName, paging);
	}
	
	public List searchVConsultDocList(ConsultDocFilter consultDocFilter, Paging paging){
		return exciseLawService.searchVConsultDocList(consultDocFilter, paging);
	}	
	
	public List searchMasterWord(String masterWord, Paging paging){
		return exciseLawService.searchMasterWord(masterWord, paging);
	}	
	
	public List searchThesaurus(BigDecimal masterWordId, String masterWordThesaurus, Paging paging){
		return exciseLawService.searchThesaurus(masterWordId, masterWordThesaurus, paging);
	}	

	//------------------------------ FINISH MANAGEMENT ------------------------------------ //
	
	//------------------------------ START NEWS ------------------------------------------- //
	public List searchNewsList(NewsFilter newsFilter, Paging paging){
		return exciseLawService.searchNewsList(newsFilter, paging);
	}
	
	public TsNews findTsNewsByNewsId(String newsId){
		return exciseLawService.findTsNewsByNewsId(Long.valueOf(newsId));	
	}
	//------------------------------ FINISH NEWS ------------------------------------------ //

	//------------------------------ START CONSULTDOC ------------------------------------- //
	public List searchVConsultDocList(ConsultDocFilter consultDocFilter, String flowId, Paging paging){
		return exciseLawService.searchVConsultDocList(consultDocFilter, Long.valueOf(flowId), paging);
	}	
	
	public List searchVConsultDocNewList(ConsultDocFilter consultDocFilter, Paging paging){
		long flowId = 0;
		if(consultDocFilter.getStatus().equals("")){
			flowId = 1;
		}
		return exciseLawService.searchVConsultDocList(consultDocFilter, flowId, paging);
	}		
	
	public List searchVConsultDocLawList(ConsultDocFilter consultDocFilter, Paging paging){
		long flowId = 0;
		if(consultDocFilter.getStatus().equals("")){
			flowId = 2;
		}
		return exciseLawService.searchVConsultDocList(consultDocFilter, flowId, paging);
	}	
	
	public List searchVConsultDocSectionList(ConsultDocFilter consultDocFilter, Paging paging){
		long flowId = 0;
		if(consultDocFilter.getStatus().equals("")){
			flowId = 3;
		}
		return exciseLawService.searchVConsultDocList(consultDocFilter, flowId, paging);
	}
	
	public List searchVConsultDocLawyerList(ConsultDocFilter consultDocFilter, Paging paging){
		long flowId = 0;
		if(consultDocFilter.getStatus().equals("")){
			flowId = 4;
		}
		return exciseLawService.searchVConsultDocList(consultDocFilter, flowId, paging);
	}
	
	public List searchVConsultDocProfessionalList(ConsultDocFilter consultDocFilter, Paging paging){
		long flowId = 0;
		if(consultDocFilter.getStatus().equals("")){
			flowId = 6;
		}
		return exciseLawService.searchVConsultDocList(consultDocFilter, flowId, paging);
	}
	
	public List searchVConsultDocAnswerList(ConsultDocFilter consultDocFilter, Paging paging){
		long flowId = 0;
		if(consultDocFilter.getStatus().equals("")){
			flowId = 8;
		}
		return exciseLawService.searchVConsultDocList(consultDocFilter, flowId, paging);
	}
	
	public TsDocResolution findTsDocResolution(String restId){
		return exciseLawService.findTsDocResolution(Long.valueOf(restId));
	}
//	public List searchVConsultDocDirectorList(ConsultDocFilter consultDocFilter, Paging paging){
//		long flowId = 0;
//		if(consultDocFilter.getStatus().equals("")){
//			flowId = 8;
//		}
//		return exciseLawService.searchVConsultDocList(consultDocFilter, flowId, paging);
//	}
//	
//	public List searchVConsultDocSecretaryList(ConsultDocFilter consultDocFilter, Paging paging){
//		long flowId = 0;
//		if(consultDocFilter.getStatus().equals("")){
//			flowId = 11;
//		}
//		return exciseLawService.searchVConsultDocList(consultDocFilter, flowId, paging);
//	}
	//------------------------------ FINISH CONSULTDOC ------------------------------------ //
	
	//------------------------------ START CONSULTPUBLISH --------------------------------- //	
	public List searchConsultPublishList(TsDiscussPublished consultPublish, Paging paging){
		return exciseLawService.searchConsultPublishList(consultPublish, paging);
	}
	
	public List<MsStatutesub> listMsStatutesubs(){
		return exciseLawService.listMsStatutesubs();
	}
	
	public List<MsStatute> listMsStatutes(){
		return exciseLawService.listMsStatutes(null);
	}
	
	public List<StStatutesub> getStStatutesubList(String statuteId, String stStatutesubId){
		return exciseLawService.getStStatutesubList(Long.valueOf(statuteId), Long.valueOf(stStatutesubId));
	}

	public StStatutesub findStStatutesubById(String stStatutesubId){
		return exciseLawService.findStStatutesubById(Long.valueOf(stStatutesubId));
	}
	
	public List<StMatr> findStMatrListByStatuteId(String statuteId){
		return exciseLawService.findStMatrListByStatuteId(Long.valueOf(statuteId));		
	}
	
	public StMatr findStMatrById(String stMatrId) {
		return exciseLawService.findStMatrById(Long.valueOf(stMatrId));
	}	

	public List<TmConsultDoc> getConsultDocComplete() {
		return exciseLawService.getConsultDocComplete();
	}
	
	public TsDocResolution findLastTsDocResolution(String docId){
		return exciseLawService.findLastTsDocResolution(Long.valueOf(docId));
	}
	//------------------------------ FINISH CONSULTPUBLISH--------------------------------- //
	//------------------------------ START JUDGEMENT -------------------------------------- //
	public List searchConstitutionalCourt(TsJudgement tsJudgement, Paging paging){
		return exciseLawService.searchJudgementList(tsJudgement, paging);
	}
	
	public List searchJusticeCourt(TsJudgement tsJudgement, Paging paging){
		return exciseLawService.searchJusticeCourtList(tsJudgement, paging);
	}
	
	public List searchAdministrativeCourtList(TsJudgement tsJudgement, Paging paging){
		return exciseLawService.searchAdministrativeCourtList(tsJudgement, paging);
	}
	
	public List searchOtherCourtList(TsJudgement tsJudgement, Paging paging){
		return exciseLawService.searchOtherCourtList(tsJudgement, paging);
	}
	//------------------------------ FINISH JUDGEMENT ------------------------------------- //
}
