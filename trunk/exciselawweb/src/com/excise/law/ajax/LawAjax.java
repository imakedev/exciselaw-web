package com.excise.law.ajax;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletContext;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.excise.law.domain.MsStatutesub;
import com.excise.law.domain.StStatutesub;
import com.excise.law.domain.TmJudgementType;
import com.excise.law.domain.TsArticle;
import com.excise.law.domain.TsExArticleCompleted;
import com.excise.law.domain.TsLaw;
import com.excise.law.domain.TsMasterWord;
import com.excise.law.service.LawService;

 
public class LawAjax {
	private final LawService lawService;
	
	public LawAjax() { 
		WebContext ctx = WebContextFactory.get(); 

		ServletContext servletContext = ctx.getServletContext();
    	WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
//		HttpServletRequest request = ctx.getHttpServletRequest();
//		HttpSession session = request.getSession();
//		departmentMap = (HashMap<String, String>) session.getAttribute("departmentMap");
    	lawService = (LawService)wac.getBean("lawService");
	}

	public List<MsStatutesub> getStatuesubList(){
		List<MsStatutesub> list = lawService.getMsStatutesubList();
		return list;
	}

	public List<StStatutesub> getStStatutesubList(String statuteId, String statutesubId){
		List<StStatutesub> list = lawService.getStStatutesubList(Long.valueOf(statuteId), Long.valueOf(statutesubId));
		return list;
	}
	
	public List<TsExArticleCompleted> getTsArticleCompletedList(String statueId){
		List<TsExArticleCompleted> list = lawService.getTsArticleCompletedList(new BigDecimal(statueId));
		return list;
	}	
	
	public List<TsArticle> getTsArticleList(String statueId, String lawTypeId){
		List<TsArticle> list = lawService.getTsArticleList(new BigDecimal(statueId), new BigDecimal(lawTypeId));
		return list;
	}

	public List<TsLaw> getTsLawList(String statueId, String lawTypeId){
		List<TsLaw> list = lawService.getTsLawList(new BigDecimal(statueId), new BigDecimal(lawTypeId));
		return list;
	}
	
	public TsMasterWord findTsMasterWordBymasterWordId(String masterWordId){
		TsMasterWord masterWord = lawService.findTsMasterWordByMasterWordId(Long.valueOf(masterWordId));
		return masterWord;
	}
	
	public List<TmJudgementType> getJudgementTypeByJudgementGroup(String judgementGroup){
		List<TmJudgementType> list = lawService.getJudgementTypeByJudgementGroup(new BigDecimal(judgementGroup));
		return list;
	}
}
