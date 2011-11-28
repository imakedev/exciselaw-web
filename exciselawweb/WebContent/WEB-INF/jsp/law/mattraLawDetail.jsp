<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<html>
<head>
<title></title>
<link href="<%=request.getContextPath() %>/css/law.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath() %>/Calendar/calendarTLE.js"></script> 
<script	type="text/javascript" src="<%=request.getContextPath() %>/Calendar/calendar-setup.js"></script> 
<script	type="text/javascript" src="<%=request.getContextPath() %>/Calendar/lang/calendar-th.js"></script> 
<script	type="text/javascript" src="<%=request.getContextPath() %>/TabContent/tabcontent.js"></script>
<style type="text/css">
	@import url("<%=request.getContextPath() %>/Calendar/calendar-win2k-cold-1.css");
</style>
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
		if(document.getElementById("mattraLawType").value == ""){
			msg += "- กรุณาเลือกกฎหมาย\n";
		}	
		if(document.getElementById("statueId").value == ""){
			msg += "- กรุณาเลือกพระราชบัญญัติ\n";
		}	
		if(document.getElementById("articleGroupId").value == ""){
			msg += "- กรุณาเลือกหมวด\n";
		}	
		if(document.getElementById("mattraLawName").value == ""){
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
function openPopup(url, toolbar, menubar, locationbar, resize, scrollbars, statusbar, left, top, width, height)
{
   var popupwindow = this.open(url, '', 'toolbar=' + toolbar + ',menubar=' + menubar + ',location=' + locationbar + ',scrollbars=' + scrollbars + ',resizable=' + resize + ',status=' + statusbar + ',left=' + left + ',top=' + top + ',width=' + width + ',height=' + height);
}
</script>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<spring:url value="/law/mattraLaw.do" var="formAction"></spring:url>
<form name="masterLawForm"  id="form1" method="post" >
<table width="100%" border="0" cellpadding="0" cellspacing="0">	
	<tr>
		<td height="30" class="head2"><strong>กฎหมายสรรพสามิต >> มาตรากฏหมาย >> </strong><font color="#000000">รายละเอียดข้อมูล</font></td>
		<td align="right">
			
				<INPUT TYPE="button" value="ย้อนกลับ" style="width: 80px" onClick="javascript:window.location='<spring:url value="/law/mattraLaw.do?action=search" htmlEscape="true" />'" >
			
			
					<INPUT TYPE="submit" value="บันทึกข้อมูล" style="width: 80px" onClick="return callfunc('doEdit')" />	
				
					<INPUT TYPE="submit" value="ลบข้อมูล" style="width: 80px" onClick="return callfunc('doDelete')" />
				
			
				
		</td>
	</tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0">			
	<tr><td height='5'>&nbsp;</td></tr>
	<tr>
		<td width="20%" class="text"><div align="right">ชื่อพ.ร.บ./พ.ร.ก./พ.ร.ฎ.&nbsp;</div></td>
		<td width="25%" class="text">
			<input type="text" name="tmArticleSection.mattraLawName" id="mattraLawName" cssClass="text" cssStyle="width: 250px" maxlength="200"/>&nbsp;
			<INPUT TYPE="button" value="ค้นหา" style="width: 50px" onClick="openPopup('../lawlist.jsp','no','no','no','yes','yes','yes','150','70','1000','440')" />
		</td>
		<td width="15%" class="text"><div align="right">ลำดับที่&nbsp;</div></td>                       
		<td width="40%">&nbsp;
			<input type="text" name="tmArticleSection.governmentGazetteName" id="governmentGazetteName" cssClass="text" cssStyle="width: 150px" />
		</td>
	</tr>
	<tr>
		<td width="20%" class="text"><div align="right">พระราชบัญญัติ&nbsp;</div></td>
		<td width="25%" class="text">
			<select name="tmArticleSection.statueId" id="statueId" cssStyle="width: 250px" cssClass="dropdown">
			    <option value="">- - - Please Select - - -</option>
			    <option value="1">พระราชบัญญัติภาษีสรรพสามิต พ.ศ.2527</option>
			    <option value="2">พระราชบัญญัติสุรา พ.ศ.2493</option>
			</select>
		</td>
		<td width="15%" class="text"><div align="right">ประเภทกฎหมาย&nbsp;</div></td>                       
		<td width="40%">&nbsp;
			<select name="tmArticleSection.statueId" id="statueId" cssStyle="width: 250px" cssClass="dropdown">
			    <option value="">- - - Please Select - - -</option>
			    <option value="1">พระราชบัญญัติ</option>
			    <option value="2">พระราชกำหนด</option>
			    <option value="-">พระราชกฤษฎีกา</option>
			</select>
		</td>
	</tr>
	<tr>
		<td width="20%" class="text"><div align="right">หมวด (ถ้ามี)&nbsp;</div></td>
		<td width="25%" class="text">			
			<select name="tmArticleSection.statueId" id="statueId" cssStyle="width: 250px" cssClass="dropdown">
			    <option value="">- - - Please Select - - -</option>
			    <option value="1">หมวดทั่วไป</option>
			    <option value="2">ส่วนที่</option>
			</select>
		</td>
		<td width="15%" class="text"><div align="right">ส่วนที (ถ้ามี)&nbsp;</div></td>                       
		<td width="40%">&nbsp;
			<select name="tmArticleSection.statueId" id="statueId" cssStyle="width: 250px" cssClass="dropdown">
			    <option value="">- - - Please Select - - -</option>
			    <option value="1">/1</option>
			    <option value="2">/2</option>
			</select>		
		</td>
	</tr>
	<tr>
		<td width="20%" class="text"><div align="right">เลขมาตรา&nbsp;</div></td>
		<td width="25%" class="text">			
			<input type="text" name="tmArticleSection.mattraLawName" id="mattraLawName" cssClass="text" cssStyle="width: 250px" maxlength="200"/>
		</td>
		<td width="15%" class="text"><div align="right">แทรกต่อจากมาตรา&nbsp;</div></td>                       
		<td width="40%">&nbsp;
			<select name="tmArticleSection.statueId" id="statueId" cssStyle="width: 250px" cssClass="dropdown">
			    <option value="">- - - Please Select - - -</option>
			    <option value="1">/1</option>
			    <option value="2">/2</option>
			</select>		
		</td>
	</tr>
	<tr>
		<td class="text" valign='top'><div align="right">เนื้อความ&nbsp;</div></td>
		<td colspan="3">
			<textarea name="tmArticleSection.consultDetail" rows="10" cols="80"></textarea>			 
			<script type="text/javascript">		
				CKEDITOR.replace( 'tmArticleSection.consultDetail',
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
			<textarea name="tmArticleSection.detail" rows="10" cols="80"></textarea>			 
			<script type="text/javascript">		
				CKEDITOR.replace( 'tmArticleSection.detail',
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
			<input type="text" name="tmArticleSection.remark" size="50"></textarea>	
		</td>
	</tr>
	<tr>
		<td class="text"><div align="right"><font color='#0000CC'>ผู้นำเข้า&nbsp;</font></div></td>
		<td class="text" colspan="3">
			<input type="text" name="tmArticleSection.createdBy" id="createdBy" cssClass="text" size="20" disabled="true"/>
		</td>
	</tr>
	<tr>
		<td class="text"><div align="right"><font color='#0000CC'>วันที่นำเข้า&nbsp;</font></div></td>
		<td class="text" colspan="3">
			<input type="text" name="createdDate" id="createdDate" cssClass="text" size="10" disabled="true"/>
		</td>
	</tr>
</table>
</form>
<script>
function statusMessage(msg,mode){
	alert(msg);
	if(mode=='doSave'){
		document.getElementById("mattraLawType").value="";
		document.getElementById("statueId").value="";
		document.getElementById("articleGroupId").value="";
		document.getElementById("articleGroupName").value="";
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
