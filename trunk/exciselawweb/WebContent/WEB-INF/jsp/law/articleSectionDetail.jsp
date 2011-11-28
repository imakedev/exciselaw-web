<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<html>
<head>
<title></title>
<link href="<%=request.getContextPath() %>/css/law.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/JavaScript">
var _path='<%=request.getContextPath() %>';
function MM_preloadImages() { //v3.0
	var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}
function MM_findObj(n, d) { //v4.01
	var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
	if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
	for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
	if(!x && d.getElementById) x=d.getElementById(n); return x;
}
function MM_swapImgRestore() { //v3.0
	var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}
function MM_swapImage() { //v3.0
	var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
	if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}
function MM_jumpMenu(targ,selObj,restore){ //v3.0
	eval(targ+".location='"+selObj.options[selObj.selectedIndex].value+"'");
	if (restore) selObj.selectedIndex=0;
}

function callfunc(functype)	{
	var msg = "";				
	if(functype!="doDelete"){	
		if(document.getElementById("articleSectionType").value == ""){
			msg += "- กรุณาเลือกกฎหมาย\n";
		}	
		if(document.getElementById("statueId").value == ""){
			msg += "- กรุณาเลือกพระราชบัญญัติ\n";
		}	
		if(document.getElementById("articleGroupId").value == ""){
			msg += "- กรุณาเลือกหมวด\n";
		}	
		if(document.getElementById("articleSectionName").value == ""){
			msg += "- กรุณากรอกหมวด\n";
		}	
	}else if(functype=="doDelete"){
		if(confirm('Do you want to delete')==false){
			return false;
		}
	}
	if(msg != ""){
		alert(msg); 
		return false;
	} 
	document.getElementById("action").value = functype;
	return true;
}
</script>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"> 
<spring:url value="/law/articleSection.do" var="formAction"></spring:url>
<form:form modelAttribute="masterLawForm" id="form1" method="post" action="${fn:escapeXml(formAction)}"> 
<form:hidden path="action" id="action"/> 
<form:hidden path="tmArticleSection.articleSectionId"/>
<form:hidden path="tmArticleSection.articleSectionOrder"/>
<table width="100%" border="0" cellpadding="0" cellspacing="0">	
	<tr>
		<td height="30" class="head2"><strong>ข้อมูลหลัก >> ส่วนที่ >> </strong><font color="#000000">รายละเอียดข้อมูล</font></td>
		<td align="right">
			<c:if test="${masterLawForm.view=='true'}">
				<INPUT TYPE="button" value="ย้อนกลับ" style="width: 80px" onClick="javascript:window.location='<spring:url value="/law/articleSection.do?action=search" htmlEscape="true" />'" >
			</c:if>
			<c:if test="${masterLawForm.action=='edit' || masterLawForm.action=='doEdit' || masterLawForm.action=='doDelete'}">
				<c:if test="${masterLawForm.edit=='true'}">
					<INPUT TYPE="submit" value="บันทึกข้อมูล" style="width: 80px" onClick="return callfunc('doEdit')" />	
				</c:if>
				<c:if test="${masterLawForm.delete=='true'}">
					<INPUT TYPE="submit" value="ลบข้อมูล" style="width: 80px" onClick="return callfunc('doDelete')" />
				</c:if>
			</c:if>
			<c:if test="${masterLawForm.action=='add' || masterLawForm.action=='doSave'}">
				<c:if test="${masterLawForm.add=='true'}">
					<INPUT TYPE="submit" value="บันทึกข้อมูล" style="width: 80px" onClick="return callfunc('doSave')" />		
				</c:if>
			</c:if>
		</td>
	</tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0">			
	<tr><td height='5'>&nbsp;</td></tr>
	<tr>
		<td width="20%" class="text" valign='top'><div align="right">กฎหมาย&nbsp;</div></td>
		<td width="80%" class="text" align="left">
			<form:select path="tmArticleSection.articleSectionType" id="articleSectionType" cssStyle="width: 200px" cssClass="dropdown">
			    <form:option value="" label="- - - Please Select - - -"/> 
    			<form:options items="${lawTypeList}" itemValue="id" itemLabel="name"/> 
			</form:select>
		</td>
	</tr>
	<tr>
		<td width="20%" class="text"><div align="right">พระราชบัญญัติ&nbsp;</div></td>
		<td width="80%" class="text">
			<form:select path="tmArticleSection.statueId" id="statueId" cssClass="dropdown">
			    <form:option value="" label="- - - Please Select - - -"/> 
    			<form:options items="${statueList}" itemValue="statueId" itemLabel="statueName"/> 
			</form:select>
		</td>
	</tr>	
	<tr>
		<td width="20%" class="text" valign='top'><div align="right">หมวด(ภายใต้พระราชบัญญัติ)&nbsp;</div></td>
		<td width="80%" class="text" align="left">
			<form:select path="tmArticleSection.articleGroupId" id="articleGroupId" cssClass="dropdown">
			    <form:option value="" label="- - - Please Select - - -"/> 
    			<form:options items="${articleGroupList}" itemValue="articleGroupId" itemLabel="articleGroupName"/> 
			</form:select>
		</td>
	</tr>
	<tr>
		<td width="20%" class="text"><div align="right">ส่วนที่&nbsp;</div></td>
		<td width="80%" class="text">
			<form:input path="tmArticleSection.articleSectionName" id="articleSectionName" cssClass="text" size="80" maxlength="200"/>
		</td>
	</tr>
	<c:if test="${masterLawForm.action=='edit' || masterLawForm.action=='doEdit'}">
	<tr>
		<td class="text"><div align="right"><font color='#0000CC'>ผู้นำเข้า&nbsp;</font></div></td>
		<td class="text">
			<form:input path="tmArticleSection.createdBy" id="createdBy" cssClass="text" size="20" disabled="true"/>
		</td>
	</tr>
	<tr>
		<td class="text"><div align="right"><font color='#0000CC'>วันที่นำเข้า&nbsp;</font></div></td>
		<td class="text">
			<form:input path="createdDate" id="createdDate" cssClass="text" size="10" disabled="true"/>
		</td>
	</tr>
	</c:if>
</table>
</form:form>
<script>
function statusMessage(msg,mode){
	alert(msg);
	if(mode=='doSave'){
		document.getElementById("articleSectionType").value="";
		document.getElementById("statueId").value="";
		document.getElementById("articleGroupId").value="";
		document.getElementById("articleSectionName").value="";
	}	
}
</script>
<c:if test="${masterLawForm.isSusses=='1' && masterLawForm.action == 'doSave'}">
<script>
statusMessage("บันทึกข้อมูลเรียบร้อย\n",'doSave');
</script>
</c:if>
<c:if test="${masterLawForm.isSusses=='1' && masterLawForm.action == 'doEdit'}">
<script>
statusMessage("แก้ใขข้อมูลเรียบร้อย\n",'doEdit');
</script>
</c:if>
<c:if test="${masterLawForm.isSusses=='1' && masterLawForm.action == 'doDelete'}">
<script>
statusMessage("ลบข้อมูลเรียบร้อย\n",'doDelete');
</script>
</c:if>
</body>
</html>
