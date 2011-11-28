package com.exciselaw.law.form;

import java.io.Serializable;

import com.exciselaw.law.domain.TmRole;
import com.exciselaw.law.domain.TmScreen;
import com.exciselaw.law.domain.TsMasterWord;
import com.exciselaw.law.domain.TsRoleScreen;
import com.exciselaw.law.domain.TsThesaurus;

public class ManagementForm extends CommonForm implements Serializable{

	private static final long serialVersionUID = 1L;

	private TmRole tmRole;
	private TmScreen tmScreen;
	private TsRoleScreen tsRoleScreen;
	private TsMasterWord tsMasterWord;
	private TsThesaurus tsThesaurus;

	private String roleName;
	private String screenName;

	private String createdDate;
	
	private String [] deleteFileBoxes;
	private String [] actionBoxes;
	
	public ManagementForm(){
	}

	public TmRole getTmRole() {
		return tmRole;
	}

	public void setTmRole(TmRole tmRole) {
		this.tmRole = tmRole;
	}


	public TsRoleScreen getTsRoleScreen() {
		return tsRoleScreen;
	}

	public void setTsRoleScreen(TsRoleScreen tsRoleScreen) {
		this.tsRoleScreen = tsRoleScreen;
	}

	public TmScreen getTmScreen() {
		return tmScreen;
	}

	public void setTmScreen(TmScreen tmScreen) {
		this.tmScreen = tmScreen;
	}

	public TsMasterWord getTsMasterWord() {
		return tsMasterWord;
	}

	public void setTsMasterWord(TsMasterWord tsMasterWord) {
		this.tsMasterWord = tsMasterWord;
	}

	public TsThesaurus getTsThesaurus() {
		return tsThesaurus;
	}

	public void setTsThesaurus(TsThesaurus tsThesaurus) {
		this.tsThesaurus = tsThesaurus;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String[] getActionBoxes() {
		return actionBoxes;
	}

	public void setActionBoxes(String[] actionBoxes) {
		this.actionBoxes = actionBoxes;
	}

	public String[] getDeleteFileBoxes() {
		return deleteFileBoxes;
	}

	public void setDeleteFileBoxes(String[] deleteFileBoxes) {
		this.deleteFileBoxes = deleteFileBoxes;
	}
}
