package com.excise.law.service;

import java.math.BigDecimal;
import java.util.List;

import com.excise.law.domain.MsStatute;
import com.excise.law.domain.MsStatutesub;
import com.excise.law.domain.StStatutesub;
import com.excise.law.domain.TmArticleGroup;
import com.excise.law.domain.TmArticleSection;
import com.excise.law.domain.TmJudgementType;
import com.excise.law.domain.TmLawType;
import com.excise.law.domain.TmStatue;
import com.excise.law.domain.TsArticle;
import com.excise.law.domain.TsExArticleCompleted;
import com.excise.law.domain.TsLaw;
import com.excise.law.domain.TsMasterWord;


/**
 * The high-level PetClinic business interface.
 *
 * <p>This is basically a data access object.
 * PetClinic doesn't have a dedicated business facade.
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Sam Brannen
 */
public interface LawService {

	/**
	 * Retrieve all <code>Vet</code>s from the data store.
	 * @return a <code>Collection</code> of <code>Vet</code>s
	 */
	public MsStatute getMsStatuteById(Long statuteId);
	public MsStatutesub getMsStatutesubById(Long statutesubId);
	public List<MsStatutesub> getMsStatutesubList();
	public List<StStatutesub> getStStatutesubList(Long statuteId, Long stStatutesubId);
	
	public List<TmStatue> getTmStatueList();	
	public TmStatue findTmStatueByStatueId(Long statueId);
	public List<TmLawType> getTmLawTypeList();	
	public List<TmLawType> getTmLawTypeListByPriority0();	
	public List<TmLawType> getTmLawTypeListByPriority1();
	public List<TmArticleGroup> getTmArticleGroupList();
	public List<TmArticleSection> getTmArticleSectionList();
	public List<TsExArticleCompleted> getTsArticleCompletedList(BigDecimal statueId);	
	public List<TsArticle> getTsArticleList(BigDecimal statueId, BigDecimal lawTypeId);	
	public List<TsLaw> getTsLawList(BigDecimal statueId, BigDecimal lawTypeId);	
	
	public void getMetaDataFromTable();	

	public List<TsMasterWord> getTsMasterWordList();
	public TsMasterWord findTsMasterWordByMasterWordId(Long masterWordId);

	public List<TmJudgementType> getTmJudgementGroup();
	public List<TmJudgementType> getJudgementTypeByJudgementGroup(BigDecimal judgementGroup);

}
