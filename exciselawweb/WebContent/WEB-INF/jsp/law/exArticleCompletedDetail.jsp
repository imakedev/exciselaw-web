<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<html>
<head>
<title></title>
<link href="<%=request.getContextPath() %>/css/law.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath() %>/ckeditor/ckeditor.js"></script>
<script src="<%=request.getContextPath() %>/ckeditor/sample.js" type="text/javascript"></script>
<link href="<%=request.getContextPath() %>/ckeditor/sample.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src='<%=request.getContextPath() %>/dwr/interface/ExciseLawAjax.js'></script>
<script type="text/javascript" src='<%=request.getContextPath() %>/dwr/engine.js'></script>
<script type="text/javascript" src='<%=request.getContextPath() %>/dwr/util.js'></script>
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
		if(document.getElementById("statueId").value == ""){
			msg += "- กรุณาเลือกพระราชบัญญัติ\n";
		}	
		if(document.getElementById("articleCompletedNumber").value == ""){
			msg += "- กรุณากรอกเลขมาตรา\n";
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

function checkStaute(statueId){
	if(statueId != ""){
		ExciseLawAjax.findTsExArticleCompletedByStatueId(statueId,{
			callback:function(data){
				if(data==null){
					alert("ไม่สามารถเพิ่มข้อมูลพระราชบัญญัติฉบับสมบูรณ์สำหรับพระราชบัญญัตินี้\n"+
							"กรุณาเพิ่มข้อมูลในหน้าข้อมูลมาตรากฎหมาย");
					document.getElementById("statueId").value = "";
				}
			}	
		});
	}


	
}
</script>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<spring:url value="/law/exArticleCompleted.do" var="formAction"></spring:url> 
<form:form modelAttribute="exciseLawForm"  id="form1" method="post" action="${fn:escapeXml(formAction)}" >
<form:hidden path="action" id="action"/> 
<table width="100%" border="0" cellpadding="0" cellspacing="0">	
	<tr>
		<td height="30" class="head2"><strong>กฎหมายสรรพสามิต >> พระราชบัญญัติฉบับสมบูรณ์ >> </strong><font color="#000000">รายละเอียดข้อมูล</font></td>
		<td align="right">
			
				<INPUT TYPE="button" value="ย้อนกลับ" style="width: 80px" onClick="javascript:window.location='<spring:url value="/law/exArticleCompleted.do?action=search" htmlEscape="true" />'" >
			
			<c:if test="${exciseLawForm.action=='edit' || exciseLawForm.action=='doEdit' || exciseLawForm.action=='doDelete'}">
			
					<INPUT TYPE="submit" value="บันทึกข้อมูล" style="width: 80px" onClick="return callfunc('doEdit')" />	
					<INPUT TYPE="submit" value="ลบข้อมูล" style="width: 80px" onClick="return callfunc('doDelete')" />
			
			</c:if>
			<c:if test="${exciseLawForm.action=='add' || exciseLawForm.action=='doSave'}">
				
					<INPUT TYPE="submit" value="บันทึกข้อมูล" style="width: 80px" onClick="return callfunc('doSave')" />	
				
			</c:if>				
		</td>
	</tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0">			
	<tr><td height='5'>&nbsp;</td></tr>
	<tr>
		<td width="20%" class="text"><div align="right">พระราชบัญญัติ&nbsp;</div></td>
		<td width="25%" class="text">
			<form:select path="tsExArticleCompleted.statueId" id="statueId" cssClass="dropdown" onchange="checkStaute(this.value)">
			    <form:option value="" label="- - - Please Select - - -"/> 
    			<form:options items="${statueList}" itemValue="statueId" itemLabel="statueName"/> 
			</form:select>
		</td>
		<td width="15%" class="text"><div align="right">ลำดับที่&nbsp;</div></td>                        
		<td width="40%" height="25">&nbsp;
			<form:input path="primaryKey" id="articleCompletedId" cssClass="text" cssStyle="width: 150px" maxlength="10" readonly="true"/>
		</td>
	</tr>
	<tr>
		<td width="20%" class="text"><div align="right">หมวด (ถ้ามี)&nbsp;</div></td>
		<td width="25%" class="text">	
			<form:select path="tsExArticleCompleted.articleGroupId" id="articleGroupId" cssClass="dropdown">
			    <form:option value="" label="- - - Please Select - - -"/> 
    			<form:options items="${articleGroupList}" itemValue="articleGroupId" itemLabel="articleGroupName"/> 
			</form:select>
		</td>
		<td width="15%" class="text"><div align="right">ส่วนที (ถ้ามี)&nbsp;</div></td>                       
		<td width="40%">&nbsp;
			<form:select path="tsExArticleCompleted.articleSectionId" id="articleSectionId" cssClass="dropdown">
			    <form:option value="" label="- - - Please Select - - -"/> 
    			<form:options items="${articleSectionList}" itemValue="articleSectionId" itemLabel="articleSectionName"/> 
			</form:select>
		</td>
	</tr>
	<tr>
		<td width="20%" class="text"><div align="right">เลขมาตรา&nbsp;</div></td>
		<td width="25%" class="text">			
			<form:input path="tsExArticleCompleted.articleCompletedNumber" id="articleCompletedNumber" cssClass="text" cssStyle="width: 250px" maxlength="100"/>
		</td>
		<td width="15%" class="text"><div align="right">แทรกต่อจากมาตรา&nbsp;</div></td>                       
		<td width="40%">&nbsp;
			<form:select path="articleOrder" id="articleOrder" cssClass="dropdown">
			    <form:option value="" label="- - - Please Select - - -"/> 
    			<form:options items="${articleCompletedOrderList}" itemValue="articleCompletedOrder" itemLabel="articleCompletedNumber"/> 
			</form:select>	
		</td>
	</tr>
	<tr>
		<td class="text" valign='top'><div align="right">เนื้อความ&nbsp;</div></td>
		<td colspan="3">
			<form:textarea path="tsExArticleCompleted.articleCompletedDetail" rows="10" cols="80" />			 
			<script type="text/javascript">		
				CKEDITOR.replace( 'tsExArticleCompleted.articleCompletedDetail',
				{
					extraPlugins : 'uicolor',
					toolbar :
						[ 	['Source','Templates','-','Cut','Copy','Paste','PasteText','PasteFromWord','Undo','Redo','-','Find','Replace','SelectAll'],'/',
							['Bold','Italic','Underline','Strike','Subscript','Superscript','RemoveFormat','-',
								'NumberedList','BulletedList','Outdent','Indent','JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock','-',
								'Link','Unlink','-','Table','HorizontalRule','Smiley','SpecialChar','PageBreak'],'/',
							['Styles','Format','Font','FontSize','-','TextColor','BGColor','-','Maximize']
						]
				});
			</script>
		</td>
	</tr>
	<tr>
		<td class="text" valign='top'><div align="right">หมายเหตุ&nbsp;</div></td>
		<td colspan="3">
			<form:textarea path="tsExArticleCompleted.articleCompletedRemark" rows="10" cols="80"/>			 
			<script type="text/javascript">		
				CKEDITOR.replace( 'tsExArticleCompleted.articleCompletedRemark',
				{
					extraPlugins : 'uicolor',
					toolbar :
						[ 	['Source','Templates','-','Cut','Copy','Paste','PasteText','PasteFromWord','Undo','Redo','-','Find','Replace','SelectAll'],'/',
							['Bold','Italic','Underline','Strike','Subscript','Superscript','RemoveFormat','-',
								'NumberedList','BulletedList','Outdent','Indent','JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock','-',
								'Link','Unlink','-','Table','HorizontalRule','Smiley','SpecialChar','PageBreak'],'/',
							['Styles','Format','Font','FontSize','-','TextColor','BGColor','-','Maximize']
						]
				});
			</script>
		</td>
	</tr>
	<tr>
		<td class="text" valign='top'><div align="right">คำช่วยค้น&nbsp;</div></td>
		<td colspan="3">
			<form:textarea path="tsExArticleCompleted.articleCompletedKeyword" id="articleCompletedKeyword" rows="5" cols="80"/>
		</td>
	</tr>
	<tr>
		<td class="text"><div align="right"><font color='#0000CC'>ผู้นำเข้า&nbsp;</font></div></td>
		<td class="text" colspan="3">
			<form:input path="tsExArticleCompleted.createdBy" id="createdBy" cssClass="text" size="20" disabled="true"/>
		</td>
	</tr>
	<tr>
		<td class="text"><div align="right"><font color='#0000CC'>วันที่นำเข้า&nbsp;</font></div></td>
		<td class="text" colspan="3">
			<form:input path="createdDate" id="createdDate" cssClass="text" size="10" disabled="true"/>
		</td>
	</tr>
</table>
</form:form>
<script>
function statusMessage(msg,mode){
	alert(msg);
	if(mode=='doSave'){
		document.getElementById("statueId").value="";
		document.getElementById("articleCompletedId").value="";
		document.getElementById("articleGroupId").value="";
		document.getElementById("articleSectionId").value="";
		document.getElementById("articleCompletedNumber").value="";
		document.getElementById("articleCompletedKeyword").value="";
		CKEDITOR.instances["tsExArticleCompleted.articleCompletedDetail"].setData("");
		CKEDITOR.instances["tsExArticleCompleted.articleCompletedRemark"].setData("");
	}	
}
</script>
<c:if test="${exciseLawForm.isSusses=='1' && exciseLawForm.action == 'doSave'}">
<script>
statusMessage("บันทึกข้อมูลเรียบร้อย\n",'doSave');
</script>
</c:if>
<c:if test="${exciseLawForm.isSusses=='1' && exciseLawForm.action == 'doEdit'}">
<script>
statusMessage("แก้ใขข้อมูลเรียบร้อย\n",'doEdit');
</script>
</c:if>
<c:if test="${exciseLawForm.isSusses=='1' && exciseLawForm.action == 'doDelete'}">
<script>
statusMessage("ลบข้อมูลเรียบร้อย\n",'doDelete');
</script>
</c:if>
</body>
</html>
