<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<html>
<head>
<title></title>
<link href="<%=request.getContextPath() %>/css/law.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath() %>/ckeditor/ckeditor.js"></script>
<script src="<%=request.getContextPath() %>/ckeditor/sample.js" type="text/javascript"></script>
<link href="<%=request.getContextPath() %>/ckeditor/sample.css" rel="stylesheet" type="text/css" />
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
		if(document.getElementById("lawTypeId").value == ""){
			msg += "- กรุณาเลือกประเภทกฎหมาย\n";
		}	
		if(document.getElementById("articleHeaderName").value == ""){
			msg += "- กรุณากรอกชื่อกฎหมาย\n";
		}	
		if(CKEDITOR.instances["tsExArticleHeader.articleHeaderDetail"].getData() == ""){
			msg += "- กรุณากรอกรายละเอียด\n";
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
// Multi uploadfile
var fileId = 0;  
function addFile() {
    fileId++; 
    var html = '<input type="file" name="fileData" size="50">   <a href=" + fileId +  " onclick="javascript:removeElement(\'file-' + fileId +'\'); return false;"><img src="<%=request.getContextPath() %>/images/delete_page.png" width="20" border="0" height="20"></a>';
    addElement('mySpan', 'p', 'file-' + fileId, html);
}
function addElement(parentId, elementTag, elementId, html) { 
    var p = document.getElementById(parentId);
    var newElement = document.createElement(elementTag);
    newElement.setAttribute('id', elementId);
    newElement.innerHTML = html;
    p.appendChild(newElement);
}	
function removeElement(elementId) { 
    var element = document.getElementById(elementId);
    element.parentNode.removeChild(element);
}
</script>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<spring:url value="/law/exArticleHeader.do" var="formAction"></spring:url> 
<form:form modelAttribute="exciseLawForm"  id="form1" method="post" action="${fn:escapeXml(formAction)}" enctype="multipart/form-data">
<form:hidden path="action" id="action"/> 
<table width="100%" border="0" cellpadding="0" cellspacing="0">	
	<tr>
		<td height="30" class="head2"><strong>กฎหมายสรรพสามิต >> พ.ร.บ./พ.ร.ก./พ.ร.ฎ. >> </strong><font color="#000000">รายละเอียดข้อมูล</font></td>
		<td align="right">			
				<INPUT TYPE="button" value="ย้อนกลับ" style="width: 80px" onClick="javascript:window.location='<spring:url value="/law/exArticleHeader.do?action=search" htmlEscape="true" />'" >
			
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
		<td width="80%" class="text">
			<form:select path="tsExArticleHeader.statueId" id="statueId" cssStyle="width: 300px" cssClass="dropdown">
			    <form:option value="" label="- - - Please Select - - -"/> 
    			<form:options items="${statueList}" itemValue="statueId" itemLabel="statueName"/> 
			</form:select>
			ลำดับที่&nbsp;<form:input path="primaryKey" id="articleHeaderId" cssClass="text" cssStyle="width: 50px" readonly="true"/>
		</td>
	</tr>	
	<tr>
		<td width="20%" class="text" valign='top'><div align="right">ประเภทกฎหมาย &nbsp;</div></td>
		<td width="80%" class="text" align="left">
			<form:select path="tsExArticleHeader.lawTypeId" id="lawTypeId" cssStyle="width: 300px" cssClass="dropdown">
			    <form:option value="" label="- - - Please Select - - -"/> 
    			<form:options items="${lawTypeList}" itemValue="lawTypeId" itemLabel="lawTypeName"/> 
			</form:select>
		</td>
	</tr>
	<tr>
		<td width="20%" class="text"><div align="right">ชื่อกฎหมาย&nbsp;</div></td>
		<td width="80%" class="text">
			<form:input path="tsExArticleHeader.articleHeaderName" id="articleHeaderName" cssClass="text" cssStyle="width: 300px" maxlength="100"/>
		</td>
	</tr>
	<tr>
		<td class="text" valign='top'><div align="right">รายละเอียด&nbsp;</div></td>
		<td>
			<form:textarea path="tsExArticleHeader.articleHeaderDetail" rows="10" cols="80" />		 
			<script type="text/javascript">		
				CKEDITOR.replace( 'tsExArticleHeader.articleHeaderDetail',
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
		<td class="text" valign='top'><div align="right">ผู้รับสนองพระบรมราชโองการ&nbsp;</div></td>
		<td>
			<form:textarea path="tsExArticleHeader.countersigned" rows="10" cols="80" />			 
			<script type="text/javascript">		
				CKEDITOR.replace( 'tsExArticleHeader.countersigned',
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
		<td class="text" valign='top'><div align="right">ตารางแนบท้าย&nbsp;</div></td>
		<td>
			<form:textarea path="tsExArticleHeader.tableAttach" rows="10" cols="80" />			 
			<script type="text/javascript">		
				CKEDITOR.replace( 'tsExArticleHeader.tableAttach',
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
		<td>
			<form:textarea path="tsExArticleHeader.articleHeaderRemark" id="articleHeaderRemark" rows="10" cols="80"/>	
		</td>
	</tr>
	<tr>
		<td class="text"><div align="right">ประกาศในราชกิจจานุเบกษา ฉบับ&nbsp;</div></td>
		<td class="text">
			<form:input path="tsExArticleHeader.gazetteNo" id="gazetteNo" cssClass="text" cssStyle="width: 50px" maxlength="100"/>&nbsp;	
			เล่ม&nbsp;<form:input path="tsExArticleHeader.gazetteVol" id="gazetteVol" cssClass="text" cssStyle="width: 50px" maxlength="100"/>&nbsp;
			ตอนที่&nbsp;<form:input path="tsExArticleHeader.gazetteEpisode" id="gazetteEpisode" cssClass="text" cssStyle="width: 50px" maxlength="100"/>&nbsp;
			ลงวันที่&nbsp;<form:input path="tsExArticleHeader.gazetteDated" id="gazetteDated" cssClass="text" cssStyle="width: 100px" maxlength="100"/>
		</td>
	</tr>
	<tr>
		<td width="20%" class="text"><div align="right">เอกสารแนบ</div></td>
		<td width="80%" class="text">
			<input type="file" name="fileData" size="50"><img src="<%=request.getContextPath() %>/images/add_page.png" width="20" border="0" height="20" onClick="JavaScript:addFile();"> 
			<span id="mySpan"></span>
		</td>
	</tr>
	<tr>
		<td valign="top" align='right' class="Ghead">&nbsp;</td>
		<td align='left'>
			<input type="button" name="fileScan" value="แสกนเอกสาร">
		</td>
	</tr>
	<c:if test="${exciseLawForm.action=='edit' || exciseLawForm.action=='doEdit'}">
	<tr>
		<td>&nbsp;</td>
		<td >
			<table width="80%" border="0" cellpadding="1" cellspacing="0" bgcolor="#FFFFFF" class="table1">
				<thead>
					<tr>
						<td align="center" class="text" width="5%">&nbsp;</td>
						<td align="center" class="text" width="*"><strong>ชื่อเอกสาร</strong></td>
						<td align="center" class="text" width="10%"><strong>ดูเอกสาร</strong></td>		
						<td align="center" class="text" width="10%"><strong>ลบเอกสาร</strong></td>								
					</tr >
				</thead>
				<tbody  class="text">
					<c:forEach items="${tsExArticleHeaderAttachList}" var="exArticleHeaderAttach" varStatus="loop">  
 					<tr>
 						<td align='right' class='text'>
 						<c:set var="type" value="${fn:substring(exArticleHeaderAttach.fileName, fn:indexOf(exArticleHeaderAttach.fileName,'.')+1, fn:length(exArticleHeaderAttach.fileName))}"></c:set>
 						<c:if test="${type == 'xls' || type == 'xlsx' || type == 'doc' || type == 'docx'}">
 							<img src="<%=request.getContextPath() %>/images/MSWord.png" width="26" height="26">
 						</c:if>
 						<c:if test="${type == 'png' || type == 'jpg' || type == 'gif'}">
 							<img src="<%=request.getContextPath() %>/images/ACDsee.png" width="26" height="26">
 						</c:if>
 						<c:if test="${type == 'pdf'}">
 							<img src="<%=request.getContextPath() %>/images/Acrobat.png" width="26" height="26">
 						</c:if>
 						</td>
						<td align='left' class='text'>${exArticleHeaderAttach.fileName}</td>
						<td align="center">
							<a href="<spring:url value="/servlets.FileDownloadServlet?module=news&attachId=${exArticleHeaderAttach.articleHeadAttachId}"/>">
								<img border="0" src="<%=request.getContextPath() %>/images/icon_15.gif">
							</a>
						</td>						
						<td align="center">
							<form:checkbox path="deleteFileBoxes" value="${exArticleHeaderAttach.articleHeadAttachId}"/>
						</td>
					</tr>
					</c:forEach>					
				</tbody>
			</table>
		</td>
	</tr>
	<tr>
		<td class="text"><div align="right"><font color='#0000CC'>ผู้นำเข้า&nbsp;</font></div></td>
		<td class="text">
			<form:input path="tsExArticleHeader.createdBy" id="createdBy" cssClass="text" size="20" disabled="true"/>
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
		document.getElementById("statueId").value="";
		document.getElementById("articleHeaderId").value="";
		document.getElementById("lawTypeId").value="";
		document.getElementById("articleHeaderName").value="";
		document.getElementById("articleHeaderRemark").value="";
		document.getElementById("gazetteNo").value="";
		document.getElementById("gazetteVol").value="";
		document.getElementById("gazetteEpisode").value="";
		document.getElementById("gazetteDated").value="";
		CKEDITOR.instances["tsExArticleHeader.articleHeaderDetail"].setData("");
		CKEDITOR.instances["tsExArticleHeader.countersigned"].setData("");
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
