<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<html>
<head>
<title></title>
<link href="<%=request.getContextPath() %>/css/law.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/redmond/jquery-ui-1.8.16.custom.css" type="text/css">
<script type="text/javascript" language="javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath() %>/js/jquery-ui-1.8.16.custom.min.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath() %>/ckeditor/ckeditor.js"></script>
<script src="<%=request.getContextPath() %>/ckeditor/sample.js" type="text/javascript"></script>
<link href="<%=request.getContextPath() %>/ckeditor/sample.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src='<%=request.getContextPath() %>/dwr/interface/ExciseLawAjax.js'></script>
<script type="text/javascript" src='<%=request.getContextPath() %>/dwr/engine.js'></script>
<script type="text/javascript" src='<%=request.getContextPath() %>/dwr/util.js'></script>
<script language="JavaScript" type="text/JavaScript">
CKEDITOR.env.ie6Compat = true;
CKEDITOR.env.ie6=true;
var editor1;
var editor2;
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
function onClickOk(instance){
	var index = (instance=='tsExArticleCompleted.articleCompletedDetail')?"1":"2";
	   var editorObj = CKEDITOR.instances[instance]; 
		var selection=editorObj.getSelection();
		var text;
	    if(CKEDITOR.env.ie){
	    	text = selection.getNative().createRange().text;
	    }else{
	    	text = selection.getNative();
	    }
		var ranges = selection.getRanges(); 
		var type = selection.getType(); 
		var isSave=true;
		var element_Id="";
		if(type==CKEDITOR.SELECTION_TEXT){  
			var xx=CKEDITOR.dom.selection(editorObj.document); 
			var element = selection.getStartElement();  
			if(element.is('span')){ 
				element_Id=element.getId();
				isSave=false;
			}
		} 
		var data = editorObj.getData(); 
				if ( ranges.length == 1  )
				{ 
					var newElement	; 
					var linkSelect = $("select[name=linkTypeSelect_"+index+"]").val();// this.getContentElement( 'tab1', 'linkTypeSelect' );
					//linkTmStatueSelect linkTmLawTypeSelect linkGroupSelect linkSectionSelect linkMatraSelect linkNameSelect
					var tmStatueVal=$("select[name=linkTmStatueSelect_"+index+"]").val();
	                var tmLawTypeVal=$("select[name=linkTmLawTypeSelect_"+index+"]").find(':selected').val();
	                var linkGroupSelectVal='0';
	                var linkSectionSelectVal='0';
	                var linkMatraSelectVal='0';
	                var linkNameSelectVal='0';
					if($("select[name=linkGroupSelect_"+index+"]").length>0)
						linkGroupSelectVal=$("select[name=linkGroupSelect_"+index+"]").find(':selected').val();
					if($("select[name=linkSectionSelect_"+index+"]").length>0)
						linkSectionSelectVal=$("select[name=linkSectionSelect_"+index+"]").find(':selected').val();
					if($("select[name=linkMatraSelect_"+index+"]").length>0)
						linkMatraSelectVal=$("select[name=linkMatraSelect_"+index+"]").find(':selected').val();
					if($("select[name=linkNameSelect_"+index+"]").length>0)
						linkNameSelectVal=$("select[name=linkNameSelect_"+index+"]").find(':selected').val();
					if(linkSelect=='0'){
						var tableName="TM_STATUE";
					    var isMakeLink=false;																    
					    var trKey; 
					    var articleHeaderTable="";
					    if ($("select[name=linkNameSelect_"+index+"]").length > 0 && $("select[name=linkNameSelect_"+index+"]").val()!='0'
					    		&& tmStatueVal!='0' && tmLawTypeVal!='0'){
					    		isMakeLink=true;
					    		if(tmLawTypeVal=='1'){
					    			articleHeaderTable="TS_EX_ARTICLE_COMPLETED";
					    		}else if(tmLawTypeVal=='2' || tmLawTypeVal=='3' || tmLawTypeVal=='4'){
					    			articleHeaderTable="TS_ARTICLE";
					    		}else if(tmLawTypeVal!='0'){
					    			articleHeaderTable="TS_LAW";
					    		}
					    }
					    isSave=true;
					 if(isMakeLink){
						 if(isSave){
							var relMap={rmName:text+""};
					    	var tsRel={tsRelMap:relMap,trTableName:tableName,trKey:tmStatueVal,statueId:tmStatueVal,lawTypeId:tmLawTypeVal
					    			,articleGroupId:linkGroupSelectVal,articleSectionId:linkSectionSelectVal,trMatraRef:linkMatraSelectVal,
					    			articleHeaderId:linkNameSelectVal,articleHeaderTable:articleHeaderTable}; 
							LinkExciseLawAjax.saveTsRel(tsRel,{
								 callback:function(dataFrom_saveTsRel){
									 if(dataFrom_saveTsRel!=null){
						            	 newElement=CKEDITOR.dom.element.createFromHtml( '<span id=\"'+dataFrom_saveTsRel+'\" style="color:orange" class="exciseLawLink">'+text+'</span>' );
						            	 ranges[0].deleteContents();
										 ranges[0].insertNode(newElement);
										 ranges[0].selectNodeContents( newElement ); 	
										 $( "#dialog_link_"+index+"" ).dialog( "close" );
						             }
								 }
							});
					 }else{ // update
						 var tsRelUpdate={tsRelMap:relMapUpdate,trTableName:tableName,trKey:tmStatueVal,statueId:tmStatueVal,lawTypeId:tmLawTypeVal
					    			,articleGroupId:linkGroupSelectVal,articleSectionId:linkSectionSelectVal,trMatraRef:linkMatraSelectVal,
					    			articleHeaderId:linkNameSelectVal,articleHeaderTable:articleHeaderTable}; 
						    LinkExciseLawAjax.saveTsRel(tsRelUpdate,{
								 callback:function(dataFrom_saveTsRel){
									 if(dataFrom_saveTsRel!=null){
										 newElement=CKEDITOR.dom.element.createFromHtml( '<span id=\"'+element_Id+'\" style="color:orange" class="exciseLawLink">'+text+'</span>' );
						            	 ranges[0].deleteContents();
										 ranges[0].insertNode(newElement);
										 ranges[0].selectNodeContents( newElement ); 	
						             }
									 $( "#dialog_link_"+index+"" ).dialog( "close" );
								 }
							});	
					 }
					  }else{
						  alert("กรุณาเลือกข้อมูลให้ครบ !!!");
					  }
					}
					else{ // unLink
						 var relKeyMap={rmId:element_Id};
						LinkExciseLawAjax.deleteRelMap(relKeyMap);
						newElement=CKEDITOR.dom.element.createFromHtml( text );
						ranges[0].deleteContents();
						ranges[0].insertNode(newElement);
						ranges[0].selectNodeContents( newElement ); 
						$(  "#dialog_link_"+index+"" ).dialog( "close" );
					} 
					
     }
}
var getSelectedSpan = function( editor )
{
	try
	{
		var selection = editor.getSelection();
		if ( selection.getType() == CKEDITOR.SELECTION_ELEMENT )
		{
			var selectedElement = selection.getSelectedElement();
			if ( selectedElement.is( 'span' ) )
				return selectedElement;
		}

		var range = selection.getRanges( true )[ 0 ];
		range.shrink( CKEDITOR.SHRINK_TEXT );
		var root = range.getCommonAncestor();
		return root.getAscendant( 'span', true );
	}
	catch( e ) { return null; }
};	
function showdialog(trId,dialog_id,instance){
	var index = (instance=='tsExArticleCompleted.articleCompletedDetail')?"1":"2";
	$( "#"+dialog_id ).dialog({
		modal: true,
		show: 'clip' ,
		 width:  1051 ,
		 position: 'top' ,
		 height: 410,
		buttons: {
			Ok: function() {
				onClickOk(instance);
			},
	        Cancel: function() {
	        	$( this ).dialog( "close" );  
	        }
		  }
	});
	$('#tabs_'+index+'').tabs('select', 0);
	 LinkExciseLawAjax.initLink({
		 callback:function(data){
			 var str="";
			 if(data[0]!=null && data[0].length>0){
				str=str+"<table width='100%'  border='0'><tr><td align='right' width='20%'>ชื่อพระราชบัญญัติ</td>";
				str= str+"<td><select name=\"linkTmStatueSelect_"+index+"\" style=\"border:1px solid #dcdcdc;background-color: #FFFFFF;\" onChange=\"loadSubLink('"+index+"')\" >"+
					"<option value=\"0\">-- เลือกพระราชบัญญัติ --</option>";
					for(var i=0;i<data[0].length;i++){
						str=str+"<option value=\""+data[0][i].statueId+"\">"+data[0][i].statueName+"</option>";
					}
					str=str+"</select></td></tr>"; 
			 }
			 if(data[1]!=null && data[1].length>0){
					str= str+ "<tr><td align='right'>ประเภทกฏหมาย</td><td><select name=\"linkTmLawTypeSelect_"+index+"\" style=\"border:1px solid #dcdcdc;background-color: #FFFFFF;\"  onChange=\"loadSubLink('"+index+"')\" >"+
						"<option value=\"0\">-- เลือกประเภทกฏหมาย --</option>";
						for(var i=0;i<data[1].length;i++){
							str=str+"<option value=\""+data[1][i].lawTypeId+"\">"+data[1][i].lawTypeName+"</option>";
						}
						str=str+"</select></td></tr></table>"; 
				 }
			 $("#law_head_"+index+"").html(str);
		 }
	 });
	 if(trId!=null){
		 LinkExciseLawAjax.listExistingLink(trId,{
			 callback:function(data){
				 var str="";
				 if(data!=null && data.length>0){
					  var str="<table style=\"border:1px solid #ccc;\"><tr><td width=\"390\">ลิ้งค์ที่จับคู่ไว้</td></tr>";
					   for(var i=0;i<data.length;i++){
						   str=str+"<tr><td>"+(i+1)+". "+data[i].trTitle+"   <span onclick=\"deletLinkAndLoad('"+data[i].trId+"')\" style=\"cursor: pointer;color: red;\">x</span></td></tr>";											   
					   } 
					   str=str+"</table>";
				 }
				 $("#law_existLink_"+index+"").html(str);
			} 
		});
	 }
	 $("#law_select_Type_"+index+"").html("<table style=\"border:1px solid #ccc;\"><tr><td align='right'></td><td></td></tr><tr><td align='right'></td><td>"+
	 "<select name=\"linkTypeSelect_"+index+"\" style=\"border:1px solid #dcdcdc;background-color: #FFFFFF;\" >"+
		"<option value=\"0\">สร้างลิ้งค์</option><option value=\"1\">ลบลิ้งค์</option></select></td></tr></table>");
}
function loadSubLink(index){
	var tmStatueVal=$("select[name=linkTmStatueSelect_"+index+"]").val();
	var tmLawTypeVal=$("select[name=linkTmLawTypeSelect_"+index+"]").find(':selected').val();
	var valueSelect=tmLawTypeVal;
	var type="";
	if(valueSelect=='1'){
		type="1";
	}else if(valueSelect=='2' || valueSelect=='3' || valueSelect=='4'){
		type="2";
	}else if(valueSelect!='0'){
		type="3";
		$("#law_group_section_"+index+"").html("");
		LinkExciseLawAjax.listTsLaws(tmStatueVal,tmLawTypeVal,{
			callback:function(data){
				if(data!=null && data.length>0){
					var str="<table width='100%' border='0'><tr>";
					str= str+ "<td align='right'  width='20%'>ชื่อเรื่อง</td><td><select name=\"linkNameSelect_"+index+"\" style=\"border:1px solid #dcdcdc;background-color: #FFFFFF;\"  >"+
					"<option value=\"0\">-- เลือกชื่อเรื่อง --</option>";
					for(var i=0;i<data.length;i++){
						str=str+"<option value=\""+data[i].lawId+"\">"+data[i].lawTitleThai+"</option>";
					}
					str=str+"</select></td></tr><tr>";
					$("#law_content_"+index+"").html(str);
				}
			}
		});
	}
	if(type=='1' || type=='2'){
		LinkExciseLawAjax.loadGroupSection(type,tmStatueVal,tmLawTypeVal,{
			callback:function(data){
				if(data!=null && data.length>0){
					var str="<table width='100%' border='0'><tr>";
					str= str+ "<td align='right'  width='20%'>หมวด(ถ้ามี)</td><td><select name=\"linkGroupSelect_"+index+"\" style=\"border:1px solid #dcdcdc;background-color: #FFFFFF;\"  >"+
					"<option value=\"0\">-- เลือกหมวด --</option>";
					for(var i=0;i<data[0].length;i++){
						str=str+"<option value=\""+data[0][i].articleGroupId+"\">"+data[0][i].articleGroupName+"</option>";
					}
					str=str+"</select></td></tr><tr>"; 
				    str= str+ "<td align='right'>ส่วนที่(ถ้ามี)</td><td><select name=\"linkSectionSelect_"+index+"\" style=\"border:1px solid #dcdcdc;background-color: #FFFFFF;\"  >"+
					"<option value=\"0\">-- เลือกส่วนที่ --</option>";
					for(var i=0;i<data[1].length;i++){
						str=str+"<option value=\""+data[1][i].articleSectionId+"\">"+data[1][i].articleSectionName+"</option>";
					}
					str=str+"</select></td></tr><tr>";
					str= str+ "<td align='right'>เลขมาตรา</td><td><select name=\"linkMatraSelect_"+index+"\" style=\"border:1px solid #dcdcdc;background-color: #FFFFFF;\"  >"+
					"<option value=\"0\">-- เลือกเลขมาตรา --</option>";
					if(type=='1'){
						for(var i=0;i<data[2].length;i++){
							str=str+"<option value=\""+data[2][i].articleCompletedId+"\">"+data[2][i].articleCompletedNumber+"</option>";
						}
					}else{
						for(var i=0;i<data[2].length;i++){
							str=str+"<option value=\""+data[2][i].articleId+"\">"+data[2][i].articleNumber+"</option>";
						}
					} 
					str=str+"</select></td></tr><tr></table>"; 
					$("#law_group_section_"+index+"").html(str);
				}
			}
		});
		LinkExciseLawAjax.listTsExArticleHeader(type,tmStatueVal,tmLawTypeVal,{
			callback:function(data){
				if(data!=null && data.length>0){
					var str="<table width='100%' border='0'><tr>";
					str= str+ "<td align='right'  width='20%'>ชื่อเรื่อง</td><td><select name=\"linkNameSelect_"+index+"\" style=\"border:1px solid #dcdcdc;background-color: #FFFFFF;\"  >"+
					"<option value=\"0\">-- เลือกชื่อเรื่อง --</option>";
					for(var i=0;i<data.length;i++){
						str=str+"<option value=\""+data[i].articleHeaderId+"\">"+data[i].articleHeaderName+"</option>";
					}
					str=str+"</select></td></tr><tr></table>";
					$("#law_content_"+index+"").html(str);
				}
			}
		});
	}
	return false;
}
function showHideButton(type,instance){
	var index = (instance=='tsExArticleCompleted.articleCompletedDetail')?"1":"2";
	if(type=='0'){
		$( "#dialog_link_"+index+"" ).dialog("option","buttons",{ "Ok": function() { onClickOk(instance); },"Cancel": function() { $(this).dialog("close"); } }); 
	}else{
		$( "#dialog_link_"+index+"" ).dialog("option","buttons",{ "Cancel": function() { $(this).dialog("close"); } });
		 
	}
}
</script>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<div id="dialog_link_1" title="ลิ้งค์ Dialog" style="display: none;">
	<div id="tabs_1">
	<ul>
		<li><a href="#tabs-1" onclick="showHideButton('0','tsExArticleCompleted.articleCompletedDetail')">สร้างลิ้งค์</a></li>
		<li><a href="#tabs-2" onclick="showHideButton('1','tsExArticleCompleted.articleCompletedDetail')">ลิ้งค์ที่เชื่อมโยง</a></li>
		 
	</ul>
	<div id="tabs-1">
		<span id="law_head_1"></span><span id="law_group_section_1"></span><span id="law_content_1"></span><span id="law_select_Type_1"></span>
	</div>
	<div id="tabs-2">
		<span id="law_existLink_1"></span>
	</div>
</div>
</div>
<div id="dialog_link_2" title="ลิ้งค์ Dialog" style="display: none;">
	<div id="tabs_2">
	<ul>
		<li><a href="#tabs2-1" onclick="showHideButton('0','tsExArticleCompleted.articleCompletedRemark')">สร้างลิ้งค์</a></li>
		<li><a href="#tabs2-2" onclick="showHideButton('1','tsExArticleCompleted.articleCompletedRemark')">ลิ้งค์ที่เชื่อมโยง</a></li>
		 
	</ul>
	<div id="tabs2-1">
		<span id="law_head_2"></span><span id="law_group_section_2"></span><span id="law_content_2"></span><span id="law_select_Type_2"></span>
	</div>
	<div id="tabs2-2">
		<span id="law_existLink_2"></span>
	</div>
</div>
</div>
<spring:url value="/law/exArticleCompleted.do" var="formAction"></spring:url> 
<form:form modelAttribute="exciseLawForm"  id="form1" method="post" action="${fn:escapeXml(formAction)}" >
<form:hidden path="action" id="action"/> 
<table width="100%" border="0" cellpadding="0" cellspacing="0">	
	<tr>
		<td height="30" class="head2"><strong>กฎหมายสรรพสามิต >> พระราชบัญญัติฉบับสมบูรณ์ >> </strong><font color="#000000">รายละเอียดข้อมูล</font></td>
		<td align="right">
			
				<INPUT TYPE="button" value="ย้อนกลับ" style="width: 80px" onClick="javascript:window.location='<spring:url value="/law/exArticleCompleted.do?action=search" htmlEscape="true" />'" >
			
			<c:if test="${exciseLawForm.action=='edit' || exciseLawForm.action=='doEdit' || exciseLawForm.action=='doDelete'}">
			
					<INPUT TYPE="submit" value="บันทึกข้อมูล" style="width: 80px" onClick='return callfunc("doEdit")' />	
					<INPUT TYPE="submit" value="ลบข้อมูล" style="width: 80px" onClick='return callfunc("doDelete")' />
			
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
		<td colspan="4"><input type="button" value="สร้างลิ้งค์" onclick="showdialog(null,'dialog_link_1','tsExArticleCompleted.articleCompletedDetail')"/></td> 
	</tr>
	<tr>
		<td class="text" valign='top'><div align="right">เนื้อความ&nbsp;</div></td>
		<td colspan="3">
			<form:textarea path="tsExArticleCompleted.articleCompletedDetail" rows="10" cols="80" />			 
			<script type="text/javascript">		
			editor1=CKEDITOR.replace( 'tsExArticleCompleted.articleCompletedDetail',
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
			editor1.on( 'doubleclick', function( evt )
					{ 
						var element =getSelectedSpan( editor1 ) || evt.data.element; 
						if ( !element.isReadOnly() )
							{
								if ( element.is( 'span' ) ){  
									var id = element.getId();  
									showdialog(id,'dialog_link_1','tsExArticleCompleted.articleCompletedDetail');
								}			 
							}
		    		}); 
			</script>
		</td>
	</tr>
	<%-- 
	<tr>
		<td colspan="4"><input type="button" value="สร้างลิ้งค์" onclick="showdialog(null,'dialog_link_1','tsExArticleCompleted.articleCompletedRemark')"/></td> 
	</tr>
	--%>
	<tr>
		<td class="text" valign='top'><div align="right">หมายเหตุ&nbsp;</div></td>
		<td colspan="3">
			<form:textarea path="tsExArticleCompleted.articleCompletedRemark" rows="10" cols="80"/>			 
			<script type="text/javascript">		
			editor2=CKEDITOR.replace( 'tsExArticleCompleted.articleCompletedRemark',
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
			editor2.on( 'doubleclick', function( evt )
					{ 
						var element =getSelectedSpan( editor2 ) || evt.data.element; 
						if ( !element.isReadOnly() )
							{
								if ( element.is( 'span' ) ){  
									var id = element.getId();   
									showdialog(id,'dialog_link_2','tsExArticleCompleted.articleCompletedRemark'); 
								}			 
							}
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
