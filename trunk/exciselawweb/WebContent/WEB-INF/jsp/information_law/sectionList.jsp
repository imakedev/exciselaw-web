<%@ page contentType="text/html; charset=utf-8" %>
<%--@ page language="java" contentType="text/html; charset=TIS-620" 
	pageEncoding="TIS-620"--%> 
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<html>
<head>
<title>PDA : DEPOSIT PROTECTION AGENCY</title>

<link href="<%=request.getContextPath() %>/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath() %>/css/sura.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.6.2.min.js"></script>  
<script type="text/javascript" src="<%=request.getContextPath() %>/Calendar/calendarTLE.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/Calendar/calendar-setup.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/Calendar/lang/calendar-th.js"></script> 
<script type="text/javascript"
        src='<%=request.getContextPath() %>/dwr/interface/LinkExciseLawAjax.js'> 
</script>
<script type="text/javascript"
        src='<%=request.getContextPath() %>/dwr/engine.js'> 
</script>
<script type="text/javascript"
        src='<%=request.getContextPath() %>/dwr/util.js'>
</script>
<style id="styles" type="text/css">

		.cke_button_myDialogCmd .cke_icon
		{
			display: none !important;
		}

		.cke_button_myDialogCmd .cke_label
		{
			display: inline !important;
		}

	</style>
<style type="text/css">
		
		@import url("<%=request.getContextPath() %>/Calendar/calendar-win2k-cold-1.css");
		
		</style>
	<script type="text/javascript" src="<%=request.getContextPath() %>/ckeditor/ckeditor.js"></script>
	<script src="<%=request.getContextPath() %>/ckeditor/sample.js" type="text/javascript"></script>
	<link href="<%=request.getContextPath() %>/ckeditor/sample.css" rel="stylesheet" type="text/css" />
</head>

<script language="javascript">
var localIndex=0;
var localElementArray=[];  
$(document).ready(function() {
	  // Handler for .ready() called.
		callAjax();
	}); 
function initLink(rmKey,rmTable){
	 // ST_MATR,ST_STATUTESUB
	if(rmTable=='ST_MATR'){
		initStMart(rmKey);
	}else if(rmTable=='ST_STATUTESUB'){
		initStStatute(rmKey);
	}
		
} 
function initStMart(rkmKey){
	 LinkExciseLawAjax.getStMatr(rkmKey,{
		 callback:function(data_StMatr_FromServer){
			 var statuteId=data_StMatr_FromServer.msStatute.statuteId;
			 $("select[name=linkMsStatueSubSelect]").val("1");
			 $("select[name=linkMsStatueSelect]").val(statuteId);
			 loadLink(rkmKey);	 
		 }
	 });
	
}
function initStStatute(rkmKey){
	 LinkExciseLawAjax.getStStatutesub(rkmKey,{
		 callback:function(data_StStatutesub_FromServer){
			 var statutesubId=data_StStatutesub_FromServer.msStatutesub.statutesubId;
			 var statuteId=data_StStatutesub_FromServer.msStatute.statuteId;
			 $("select[name=linkMsStatueSelect]").val(statuteId);
			 $("select[name=linkMsStatueSubSelect]").val(statutesubId);
			 loadLink(rkmKey);	 
		 }
	 });
	
}
function loadLink(rkmKey){ 
	//$("#linkTableSubElement").html("");
	var linkMsStatueSelectValue= $("select[name=linkMsStatueSelect]").val();
	var linkMsStatueSubSelectValue= $("select[name=linkMsStatueSubSelect]").val();
	//var idSplit=_value;//.split("|");
	//if(idSplit.length>1){
		if(linkMsStatueSubSelectValue=='0'){ 
			$("#linkTableElement").html(""); 
		}else if(linkMsStatueSubSelectValue=='1'){  
			var linkMsStatueSelectValue= $("select[name=linkMsStatueSelect]").val();
			var msStatute={statuteId:linkMsStatueSelectValue};
			 LinkExciseLawAjax.listStMatrByMsStatute(msStatute,{
				 callback:function(data_StMatrByMsStatute_FromServer){
						 var link_selectStr=""+
						"<select name=\"linkTableSelect\" style=\"border:1px solid #dcdcdc;background-color: #FFFFFF;\">"+
						"<option VALUE=\"0\">-- มาตรา --</option>";
						for(var i=0;i<data_StMatrByMsStatute_FromServer.length;i++){
							link_selectStr=link_selectStr+"<option value=\""+data_StMatrByMsStatute_FromServer[i].stMatrId+"\">"+data_StMatrByMsStatute_FromServer[i].matr+"</option>";
						}
						link_selectStr=link_selectStr+"</select>"; 
						$("#linkTableElement").html(link_selectStr);
						if(rkmKey!='0'){
							$("select[name=linkTableSelect]").val(rkmKey);
						}
				 }
			}); 
		}else { 
			var msStatutesub={statutesubId:linkMsStatueSubSelectValue};
			var msStatute={statuteId:linkMsStatueSelectValue};
			 LinkExciseLawAjax.listStStatutesub(msStatute,msStatutesub,{
				 callback:function(data_StStatutesubByMsStatutesub_FromServer){
						 var link_selectStr=""+
						"<select name=\"linkTableSelect\" style=\"border:1px solid #dcdcdc;background-color: #FFFFFF;\">"+
						"<option VALUE=\"0\">-- ข้อมูลกฏหมาย --</option>";
						for(var i=0;i<data_StStatutesubByMsStatutesub_FromServer.length;i++){
							link_selectStr=link_selectStr+"<option value=\""+data_StStatutesubByMsStatutesub_FromServer[i].stStatutesubId+"\">"+data_StStatutesubByMsStatutesub_FromServer[i].article+"</option>";
						}
						link_selectStr=link_selectStr+"</select>"; 
						$("#linkTableElement").html(link_selectStr);
					  if(rkmKey!='0'){
						$("select[name=linkTableSelect]").val(rkmKey);
						}
				 }
			});
		}
	/*  }else{
		$("#linkTableElement").html("");
		$("#linkTableSubElement").html("");
	} */
	
}
function callAjax(){
    LinkExciseLawAjax.listMsStatuteAndMsStatutesub({
		 callback:function(dataFromServer){
             if(dataFromServer!=null && dataFromServer.length>0){ 
				 var link_selectStr=""+
					"<select name=\"linkMsStatueSelect\" style=\"border:1px solid #dcdcdc;background-color: #FFFFFF;\"  onChange=\"loadLink('0')\" >"+
					"<option value=\"0\">-- เลือกพระราชบัญญัติ --</option>";
					for(var i=0;i<dataFromServer[0].length;i++){
						link_selectStr=link_selectStr+"<option value=\""+dataFromServer[0][i].statuteId+"\">"+dataFromServer[0][i].name+"</option>";
					}
					link_selectStr=link_selectStr+"</select><br/>";
					link_selectStr=link_selectStr+""+
					"<select name=\"linkMsStatueSubSelect\" style=\"border:1px solid #dcdcdc;background-color: #FFFFFF;\" onChange=\"loadLink('0')\">"+
					"<option value=\"0\">-- เลือกประเภทกฏหมาย --</option>";
					for(var i=0;i<dataFromServer[1].length;i++){
						link_selectStr=link_selectStr+"<option value=\""+dataFromServer[1][i].statutesubId+"\">"+dataFromServer[1][i].name+"</option>";
					}
					link_selectStr=link_selectStr+"</select>";
				 var editor = CKEDITOR.replace( 'stMatr.detail', 
						 {
						extraPlugins : 'uicolor',
						toolbar :
						[ ['Source', '-','Cut','Copy','PasteText','PasteFromWord','Preview','-', 'Bold', 'Italic', 'Underline', 'Strike', '-', 'Subscript', 'Superscript', '-','JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock','-','NumberedList', 'BulletedList','-','Indent','Outdent','-','Link','Unlink', '-','Anchor' ,'-', 'Excise Link'  ] ,'/', 
				 		['Styles','Format','Font','FontSize','-','TextColor','BGColor','Table']
						]
						,height: "90"});
						// Listen for the "pluginsLoaded" event, so we are sure that the
						// "dialog" plugin has been loaded and we are able to do our
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
				 editor.on( 'pluginsLoaded', function( ev )
							{ 
								// If our custom dialog has not been registered, do that now.
								if ( !CKEDITOR.dialog.exists( 'myDialog' ) )
								{
									 
									//CKEDITOR.dialog.add( 'myDialog', href );
									CKEDITOR.dialog.add( 'myDialog', function( api )
											{
												// CKEDITOR.dialog.definition
												var dialogDefinition =
												{
													title : 'ลิ้งค์ Dialog',
													minWidth : 390,
													minHeight : 130,
													contents : [
														{
															id : 'tab1',
															label : 'Label',
															title : 'Title',
															expand : true,
															padding : 0,
															elements :
															[
																{
																	id:'linkType',
																	type : 'html',
																	html : link_selectStr
																},
																{
																	id:'linkTable',
																	type : 'html',
																	html : '<span id="linkTableElement"></span>'
																},
																{
																	type : 'select',
																	id : 'linkTypeSelect',
																	label : 'เลือกการทำลิ้งค์',
																	items : [ [ 'Link' ], [ 'UnLink' ] ],
																	'default' : 'Link',
																	onChange : function( api ) {
																	// this = CKEDITOR.ui.dialog.select
																	//alert( 'Current value: ' + this.getValue() );
																	}
																}
																
															]
														}
													],
													buttons : [ CKEDITOR.dialog.okButton, CKEDITOR.dialog.cancelButton ],
													onOk : function() { 
											            var editor1 = CKEDITOR.instances["stMatr.detail"]; 
													var selection = editor1.getSelection();
													var text = selection.getNative();
													var ranges = selection.getRanges(); 
													var type = selection.getType(); 
													var isSave=true;
													var element_Id="";
													if(type==CKEDITOR.SELECTION_TEXT){  
														var xx=CKEDITOR.dom.selection(editor1.document); 
														var element = selection.getStartElement();  
														if(element.is('span')){ 
															element_Id=element.getId();
															isSave=false;
														}
													} 
													/*
													 * CKEDITOR.SELECTION_NONE (1): No selection. CKEDITOR.SELECTION_TEXT
													 * (2): Text is selected or collapsed selection.
													 * CKEDITOR.SELECTION_ELEMENT (3): A element selection.
													 * 
													 */
													 
													var data = editor1.getData(); 
															if ( ranges.length == 1  )
															{ 
																//var textNode = new CKEDITOR.dom.text( "<div>xxxxxxx</div>", editor1.document );
																var newElement	; 
																
																var linkSelect = this.getContentElement( 'tab1', 'linkTypeSelect' );
																if(linkSelect.getValue()=='Link'){
																	var id=$("select[name=linkMsStatueSubSelect]").val();
																	//var idSplit=$("select[name=linkSelect]").val().split("|");
																	var tableName="";
																	if(id=="1") {
																		tableName="ST_MATR";
																	}else {
																		tableName="ST_STATUTESUB";
																	}
																	//var relTableMap={rtmId:idSplit[0]};
																    var relMap={rmKey:$("select[name=linkTableSelect]").val(),rmName:text+"",rmTable:tableName};
																 
																	 if(isSave){
																		// alert("save="+tableName+",linkTableSelect="+$("select[name=linkTableSelect]").val()+",text="+text);
																		LinkExciseLawAjax.saveRelMap(relMap,{
																			 callback:function(dataFrom_saveRelKeyMap){
																				 if(dataFrom_saveRelKeyMap!=null){
																	            	 newElement=CKEDITOR.dom.element.createFromHtml( '<span id=\"'+dataFrom_saveRelKeyMap+'\" style="color:orange" class="exciseLawLink">'+text+'</span>' );
																	            	 ranges[0].deleteContents();
																					 ranges[0].insertNode(newElement);
																					 ranges[0].selectNodeContents( newElement ); 	
																	             }
																			 }
																		});
																	 }else{ // update
																		 //alert("update") 
																	     var relMapUpdate={rmId:element_Id,rmKey:$("select[name=linkTableSelect]").val(),rmName:text+"",rmTable:tableName};
																		 LinkExciseLawAjax.updateRelMap(relMapUpdate,{
																			 callback:function(dataFrom_updateRelKeyMap){
																				 if(dataFrom_updateRelKeyMap!=null){
																	            	 newElement=CKEDITOR.dom.element.createFromHtml( '<span id=\"'+element_Id+'\" style="color:orange" class="exciseLawLink">'+text+'</span>' );
																	            	 ranges[0].deleteContents();
																					 ranges[0].insertNode(newElement);
																					 ranges[0].selectNodeContents( newElement );  
																	             }
																			 }
																		});
																	 }
																}
																else{
																	 var relKeyMap={rmId:element_Id};
																	LinkExciseLawAjax.deleteRelMap(relKeyMap);
																	newElement=CKEDITOR.dom.element.createFromHtml( text );
																	ranges[0].deleteContents();
																	ranges[0].insertNode(newElement);
																	ranges[0].selectNodeContents( newElement ); 
																} 
																
															}
													}
												};

												return dialogDefinition;
											} );
								}

								// Register the command used to open the dialog.
								editor.addCommand( 'myDialogCmd',new CKEDITOR.dialogCommand( 'myDialog' ) ); 
								editor.ui.addButton( 'Excise Link',
									{
										label : 'สร้างลิ้งค์',//'My Dialog',
										command : 'myDialogCmd'
									} ); 
							}); 
			     editor.on( 'dialogShow', function( evt ){			    	
   		    	     var selectionShow = editor.getSelection();
					 var element = selectionShow.getStartElement();  
						if(!element.is('span')){  
							$("select[name=linkMsStatueSelect]").val("0");
							$("select[name=linkMsStatueSubSelect]").val("0");
							loadLink("0");
						}else{
							var id = element.getId();
							if(id.indexOf("local_")!=-1){
								
							}else{
								  LinkExciseLawAjax.getRelMapById(id,{
										 callback:function(data_tRelMap_FromServer){
										//  alert("data_tRelMap_FromServer="+data_tRelKeyMap_FromServer);
										   if(data_tRelMap_FromServer!=null){
								            	// var rmId= data_tRelMap_FromServer.relTableMap.rmId;
								            	 var rmTable =  data_tRelMap_FromServer.rmTable; // ST_MATR,ST_STATUTESUB
								            	 var rmKey=data_tRelMap_FromServer.rmKey;
								            	 initLink(rmKey,rmTable);
								            	// var linkSelectId=rtmId+((rtmId!=1)?"|"+rtmTableName:"");
								            	// $("select[name=linkSelect]").val(linkSelectId);
								            	// loadLink(linkSelectId,rkmKey+""); 
								             }
									}
								  });
							}
						}
			     });
				 editor.on( 'doubleclick', function( evt )
							{
								//alert("aoe doubleclick")
								var element =getSelectedSpan( editor ) || evt.data.element; 
								if ( !element.isReadOnly() )
									{
										if ( element.is( 'span' ) ){  
											var id = element.getId(); 
												evt.data.dialog = 'myDialog';	 
										}			 
									}
				    		}); 
             }
		 }
	});  
}
	// control button
	function callfunc(functype)
	{	 
		//document.getElementById("FUNC").value = functype;
	//	alert(functype);
		var msg = "";
		if(functype!="doDelete"){ 
			if(document.getElementById("statuteId").value  == "")
			{
				msg += "- กรุณาเลือกพระราชบัญญัติ\n";
			}
			
		}
		if(msg != "")
		{
			alert(msg); 
			return false;
		} 
		document.getElementById("action").value = functype;
		return true;
	}
	/*
	function callfunc(functype)
	{	 
		document.getElementById("FUNC").value = functype;	 
		
		 
		var msg = "";
		if(functype == 'SaveAction')
		{
			if(document.getElementById("STATUTE_ID").value == "NONE")
			{
				msg += "- กรุณาเลือกพระราชบัญญัติ\n";
				
			}
			if(msg != "")
			{
				alert(msg);
				document.getElementById("FUNC").value="";
			} 
				
			 
		}
		 
	}
	*/
	function ChangStatute(){
		document.getElementById("FUNC").value="ChooseAction";
		document.forms['form1'].submit();
		}
</script>
<%--
request.setCharacterEncoding("TIS-620");
response.setCharacterEncoding("TIS-620");
response.setHeader("Cache-Control", "no-cache"); 
	ObjectControl myObjControl = new ObjectControl();
	String sql = "";
	String STATUTE_ID = "";
	String id_value="";
	String editIf ="";
	//Edit Value
	String VLE_STATUTE_ID="";
	String VLE_MATRGROUP_ID="";
	String VLE_PART="";
	String VLE_MATRA_DETAIL="";
	String VLE_REMARK="";
	String VLE_ACTIVATE="";
	String VLE_DATE_IN="";
	String VLE_MATR="";
	String checkedActive1="";
	String checkedActive2="";
	FCKeditor fckEditor = new FCKeditor(request, "LEGAL_HEADER_TH");
	Connection conn = null;
	DataBaseControl mycontrol = new DataBaseControl();
	StringControl myStrControl=new StringControl();
	Class.forName(VariableControl.gDB_Driver);
	conn = mycontrol.gConnectDB();
	Statement stmt = conn.createStatement();
	String FUNC = myStrControl.gNullToBlank(request.getParameter("FUNC"));
	String MATRA_DETAIL =request.getParameter("MATRA_DETAIL");
	id_value =myStrControl.gNullToBlank(request.getParameter("id_value")); 
	String st_matr_id=request.getParameter("st_matr_id"); 
	
	//out.println("FUNC>>"+MATRA_DETAIL);
	if(FUNC != null)
	{
		//String LEGAL_MAIN_NO =null;
		String STATUTE_ID_VAL ="";
		String MATRGROUP_ID ="";
		String PART = "";
		String MATR ="";
		String REMARK="";
		String LEGALITEMID="";
		String ACTIVATE="1";
		String DEACTIVATE_DATE="";
		String DATE_IN="NULL";
		String MaxValue="";
		STATUTE_ID_VAL =myStrControl.gGetRequestToString( myStrControl.gNullToBlank(request.getParameter("STATUTE_ID")));
		MATRGROUP_ID = myStrControl.gNullToBlank(request.getParameter("MATRGROUP_ID"));
		PART = myStrControl.gNullToBlank(request.getParameter("PART"));
		MATR = myStrControl.gNullToBlank(request.getParameter("MATR"));
		REMARK = myStrControl.gNullToBlank(request.getParameter("REMARK"));
		LEGALITEMID = myStrControl.gNullToBlank(request.getParameter("LEGALITEMID"));
		ACTIVATE= myStrControl.gNullToBlank(request.getParameter("ACTIVATE"));
		DEACTIVATE_DATE=request.getParameter("DEACTIVATE_DATE");
		if(FUNC.equals("ChooseAction"))
		{
			
		VLE_STATUTE_ID=STATUTE_ID_VAL;
		VLE_MATRGROUP_ID= MATRGROUP_ID;
		VLE_PART=PART;
		VLE_MATRA_DETAIL =MATRA_DETAIL;
		VLE_REMARK =REMARK;
		VLE_ACTIVATE =ACTIVATE;
		VLE_MATR = MATR;
		}
		if(FUNC.equals("SaveAction"))
		{
			
			stmt = conn.createStatement();
		String SqlGetId=" SELECT CASE "+
					          " WHEN (MAX (st_matr_id) < 999990) "+
					             " THEN 999990 "+
					          " ELSE (MAX (st_matr_id) + 1) "+
					       " END AS resultset "+
	  					" FROM st_matr";
		ResultSet rsGetId = stmt.executeQuery(SqlGetId);
		rsGetId.next();
		MaxValue=rsGetId.getString("resultset");
		
			sql = " SELECT ST_MATR_ID FROM ST_MATR WHERE STATUTE_ID ='"+STATUTE_ID_VAL+"' AND MATR ='"+MATR+"' AND MATRGROUP_ID='"+MATRGROUP_ID+"' AND PART ='"+PART+"' ";
			System.out.println("sqlcheck>>>"+sql);
			ResultSet rsIn = stmt.executeQuery(sql);
			int i=0;
			if(!rsIn.next())
			{
				System.out.println(" I  values >> "+i);
				sql =" INSERT INTO  ST_MATR(ST_MATR_ID,STATUTE_ID,MATRGROUP_ID, PART, DETAIL, "+
						"REMARK, ACTIVATE,UPDATE_USER, UPDATE_TIME, MATR) "+
				" VALUES ('"+MaxValue+"','"+STATUTE_ID_VAL+"' , '"+MATRGROUP_ID+"','"+PART+"', '"+MATRA_DETAIL+"', '"+REMARK+"',"+
						" '"+ACTIVATE+"','"+session.getAttribute("ID")+"',CURRENT_DATE, '"+MATR+"')";

				System.out.println("sql>>"+sql);
			int rsIn2 = stmt.executeUpdate(sql);
				// mycontrol.gExecuteQuery(sql);
				  
			 if(rsIn2>0){
				out.println("<script language=JavaScript>"+ 
				"alert('บันทึกข้อมูลประเ ภทพระราชบัญญัติ เรียบร้อย'); </script>");
			 }
			}else{
				out.println("<script language=JavaScript>"+ 
				"alert('ข้อมูลซ้ำ');  </script>");
				
			}
				 
		i=i++;
				 
		}
		if(FUNC.equals("UpdateAction")){
			String sqlUp = "UPDATE ST_MATR SET";
			
			sqlUp = sqlUp + " STATUTE_ID ='"+STATUTE_ID_VAL+"',";
			sqlUp = sqlUp + " MATRGROUP_ID ='"+MATRGROUP_ID+"',";
			sqlUp = sqlUp + " PART ='"+PART+"',";
			sqlUp = sqlUp + " DETAIL ='"+MATRA_DETAIL.trim()+"',";
			sqlUp = sqlUp + " REMARK ='"+REMARK+"',"; 
			sqlUp = sqlUp + " UPDATE_USER ='"+session.getAttribute("ID")+"',";
			sqlUp = sqlUp + " UPDATE_TIME =CURRENT_DATE,";
			sqlUp = sqlUp + " MATR = '"+MATR.trim()+"'";
			sqlUp = sqlUp + " WHERE ST_MATR_ID ='"+st_matr_id+"'";
			System.out.println("sqlUp>>"+sqlUp);
			
			int rsUp = stmt.executeUpdate(sqlUp);
			if(rsUp>0){
			out.println("<script language=JavaScript>"+ 
					"alert('แก้ไขข้อมูลประเ ภทพระราชบัญญัติ เรียบร้อย');</script>");
			}
		}
	/*	if(FUNC.equals("del"))
		{
			STATUTE_ID =myStrControl.gNullToBlank(request.getParameter("STATUTE_ID"));
			sql = "DELETE FROM MTSTATUTE WHERE STATUTE_ID ='"+STATUTE_ID+"'";
			mycontrol.gExecuteQuery(sql);
			out.println("<script language=JavaScript>"+ 
					"alert('ลบข้อมูลประเ ภทพระราชบัญญัติ   เรียบร้อย');</script>");
		}
	*/
	}
	if(!id_value.equals("")){
		String sSqlS="";
		sSqlS = " SELECT ST_MATR_ID, STATUTE_ID,   "+
			   " PART, DETAIL, REMARK, MATRGROUP_ID, "+
			   " ACTIVATE,  UPDATE_USER,  "+
			   " TO_CHAR(UPDATE_TIME,'DD/MM/YYYY')as UPDATE_TIME, MATR from ST_MATR where ST_MATR_ID='"+id_value+"'";
		
		 System.out.println(sSqlS);
		ResultSet rst  = stmt.executeQuery(sSqlS);

		if (rst.next()) {
			VLE_STATUTE_ID=rst.getString("STATUTE_ID");
			VLE_MATRGROUP_ID= myStrControl.gNullToBlank(rst.getString("MATRGROUP_ID"));
			VLE_PART=  myStrControl.gNullToBlank(rst.getString("PART"));
			VLE_MATRA_DETAIL =  myStrControl.gNullToBlank(rst.getString("DETAIL"));
			VLE_REMARK = myStrControl.gNullToBlank(rst.getString("REMARK"));
			VLE_ACTIVATE =rst.getString("ACTIVATE");
			//VLE_DATE_IN = myStrControl.gNullToBlank(rst.getString("DEACTIVATE_DATE"));
			VLE_MATR = myStrControl.gNullToBlank(rst.getString("MATR"));
					}
	} 
--%>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" >
<spring:url value="/Law/SectionList.do" var="formAction">
</spring:url>
<form:form modelAttribute="stMatrForm"  id="form1" method="post" action="${fn:escapeXml(formAction)}" >
<form:hidden path="action" id="action"/> 
<%--
<form name="form1"  id="form1"   method="post" action="SectionList.jsp" >
 
<input TYPE="hidden" NAME="FUNC"  id="FUNC" VALUE="">
<input type="hidden" name="st_matr_id" id="st_matr_id" value="">
--%>
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td valign="top">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="25" class="text">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td height="30" class="head2"><font color="#FF6600"> ข้อมูลกฎหมาย >> มาตรา  >></font><font color="#000000"> รายละเอียดข้อมูล </font></td>
								<td align="right">
									 <c:if test="${stMatrForm.action=='edit' || stMatrForm.action=='doEdit'}">
									<%--if(FUNC.trim().equals("Update")){ --%>
									<INPUT TYPE="submit" value="บันทึกข้อมูล" style="width: 80px" onClick="return callfunc('doEdit')" >	
									<INPUT TYPE="submit" value="ลบข้อมูล" style="width: 80px" onClick="return callfunc('doDelete')" />
									</c:if>								
									<%--}else{ --%>
									<c:if test="${stMatrForm.action!='edit' && stMatrForm.action!='doEdit'}">
									<INPUT TYPE="submit" value="บันทึกข้อมูล" style="width: 80px" onClick="return callfunc('doSave')" >
									<INPUT	TYPE="submit" value="ลบข้อมูล" style="width: 80px" onClick="return callfunc('doDelete')" 
										<c:if test="${stMatrForm.stMatr.stMatrId==null}">
										   disabled="disabled"
									    </c:if>
									 />
									 </c:if> 
									<INPUT TYPE="button" value="ค้นหาข้อมูล" style="width: 80px" onClick="javascript:window.location='<spring:url value="/Law/SectionList.do?action=search" htmlEscape="true" />'" >
									<%--
									<INPUT TYPE="Reset" value="เคลียร์" style="width: 80px" onClick="javascript:callfunc('ResetAction')" >
									 --%>
								 </td>
							</tr>
						</table>
					</td>
				</tr>
				<tr><td height='5'>&nbsp;</td></tr>
				<tr align='center'>
					<td align='center'>
						<table width="100%" border="0" cellspacing="0" cellpadding="2" >
							<tr>
								<td valign="top" align='right' class="Ghead">ชื่อพระราชบัญญัติ</td>
								<td> <%--  cssClass="dropdown"  --%>
								 <form:select path="stMatr.msStatute.statuteId" id="statuteId"  cssStyle="width: 300px" cssClass="dropdown" > 
							     				  <form:option value="0" label="- - - Please Select - - -"/> 
    											  <form:options items="${msStatutes}" itemValue="statuteId" itemLabel="name"/>    												
    							 </form:select>
									 <%--
			sql = "SELECT  STATUTE_ID as aFieldCode,  NAME as aFieldDesc ";
			sql = sql + " FROM MS_STATUTE order by STATUTE_ORDER  ";
			//System.out.println("sql>>>"+sql);
			out.println(myObjControl.gIntitialCombo("", "", "", "", "", sql,"STATUTE_ID", 300, "ChangStatute()",VLE_STATUTE_ID));
		--%>								</td>
							</tr>
							
							<tr>
								<td valign="top" align='right' class="Ghead">หมวด</td>
								<td valign="top" align='left' class="text">
								 <form:select path="stMatr.msMatrgroup.matrgroupId" id="matrgroupId" cssStyle="width: 300px" cssClass="dropdown" > 
							     				  <form:option value="0" label="- - - Please Select - - -"/> 
    											  <form:options items="${msMatrgroups}" itemValue="matrgroupId" itemLabel="name"/>    												
    							 </form:select>
								<%--
			String sql2 = " SELECT   a.matrgroup_id AS aFieldCode, a.NAME||' '||c.NAME  AS aFieldDesc  "+
									" FROM ms_matrgroup a,  ms_submatr c  "+
								   " WHERE  a.submatr_id = c.submatr_id ";
							sql2=sql2+"	    AND 1 = 1  ";
							if(!VLE_STATUTE_ID.equals("")){
								if(!VLE_STATUTE_ID.equals("NONE")){
									sql2=sql2+"	and a.STATUTE_ID='"+VLE_STATUTE_ID+"'";
								}
							}
							sql2=sql2+"ORDER BY a.matrgroup_order ASC  ";  
			out.println(myObjControl.gIntitialCombo("", "", "", "", "", sql2,"MATRGROUP_ID", 150, " ",VLE_MATRGROUP_ID));
		--%>           
									</td>
							</tr>
							<tr>
								<td valign="top" align='right' class="Ghead">ส่วน</td>
								<td valign="top" align='left' class="text">
								<form:input path="stMatr.part"  cssClass="field-color" size="20"/>
								<%--
									<input name="PART" type="text" class="field-color" id="PART"  size="20" value=""/>								</td>
							     --%>
							</tr>
							<tr>
								<td valign="top" align='right' class="Ghead">มาตรา</td>
								<td>
								<form:input path="stMatr.matr"  cssClass="field-color" size="20"/> 
								<%--
								<input name="MATR" type="text" class="field-color" id="MATR"  size="20" value="" />
								 --%>
								 </td>
							</tr>
							
							<tr>
							<td valign="top" align='right' class="Ghead">&nbsp;เนื้อหา</td>
							  <td class="text">
							  <form:textarea path="stMatr.detail"   cols="60" rows="10"/>
							  <%-- 
		<script type="text/javascript">		
			CKEDITOR.replace( 'stMatr.detail',
				{
					extraPlugins : 'uicolor',
					toolbar :
						[ ['Source', '-','Cut','Copy','PasteText','PasteFromWord','Preview','-', 'Bold', 'Italic', 'Underline', 'Strike', '-', 'Subscript', 'Superscript', '-','JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock','-','NumberedList', 'BulletedList','-','Indent','Outdent','-','Link','Unlink', '-', 'MyButton' ] ,'/', 
							 ['Styles','Format','Font','FontSize','-','TextColor','BGColor','Table']
						]

				});
		</script>
		--%>
		</td>
							</tr>
							<%-- if(FUNC.trim().equals("Update")){--%>
							<c:if test="${stMatrForm.action=='edit' || stMatrForm.action=='doEdit'}">
							<tr>
								<td valign="top" class="Ghead" align="right" >เนื้อหาเดิม </td>
								<td colspan="7"><textarea name="OLD_DETAIL" id="OLD_DETAIL" cols="134" rows="7" id="OLD_DETAIL"  >
								 <%--=VLE_MATRA_DETAIL  --%>
								 <c:out value="${stMatrForm.stMatr.detail}" escapeXml="false"></c:out>
								
								 </textarea>
								 <script type="text/javascript">		
			CKEDITOR.replace( 'OLD_DETAIL',
				{
					extraPlugins : 'uicolor',
					toolbar :
						[  
						]

				});
		</script>
								 </td>
							</tr>
							</c:if>
							<%--} --%>
							<tr>
								<td valign="top" class="Ghead"><div align="right">หมายเหตุ</div></td>
								<td colspan="7">
								<form:textarea path="stMatr.remark"   cols="134" rows="7"/>
								<%--
								<textarea name="REMARK" cols="134" rows="7" id="REMARK">
												</textarea>
							--%>
								</td>
							</tr>
							
						
							<tr>
								<td class="text">&nbsp;</td>
								<td class="text">&nbsp;</td>
							</tr>
		<tr>
								<td class="text">&nbsp;</td>
								<td class="text">&nbsp;</td>
							</tr>
							<tr>
								<td class="Ghead"><div align="right">ผู้นำเข้า</div></td>
								<td class="text">Admin</td>
							</tr>
							<tr>
								<td class="Ghead"><div align="right">วันที่นำเข้า</div></td>
								<td class="text"><c:out value="${stMatrForm.updateTime}"/></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<script language ="javascript"  >
 
var checkflag = "false";
var checkflagUser = "false";
function checkall(el_collection){
 if (checkflag == "false") {
if(el_collection.length == null){
el_collection.checked=true;
 checkflag = "true";
     return "Un";
}
for (c=0;c<el_collection.length;c++){
 el_collection[c].checked=true;
 }
    checkflag = "true";
     return "Un";
     
  }else{
if(el_collection.length == null){
el_collection.checked=false;
  checkflag = "false";
  return "All"; 
}
 for (c=0;c<el_collection.length;c++){
  el_collection[c].checked=false; 
 }
 checkflag = "false";
  return "All"; 
  }
}  

function ChangStatus(value) {

	var ele_N = document.getElementById("displaY_name");
		var ele = document.getElementById("displaY_name2");
		if( value=="1") {
	    		ele_N.style.display = "none";
				ele.style.display = "none";
				document.getElementById("DEACTIVATE_DATE").value  = "";
	  	}
		else {
			ele_N.style.display = "block";
			ele.style.display = "block";
		}
	} 
</SCRIPT>
<script>
	function statusMessage(msg,mode){
		alert(msg);
		if(mode=='doSave'){
			document.getElementById("stMatr.remark").value="";		 
			document.getElementById("statuteId").value="0";
			document.getElementById("matrgroupId").value="0";
			document.getElementById("stMatr.part").value="";
			document.getElementById("stMatr.matr").value="";
			document.getElementById("stMatr.detail").value="";
		}
		
	}
</script>
<c:if test="${stMatrForm.isSusses=='1' && stMatrForm.action == 'doSave'}">
<script>
statusMessage("เพิ่มข้อมูลเรียบร้อยแล้ว\n",'doSave');
</script>
</c:if>
<c:if test="${stMatrForm.isSusses=='1' && stMatrForm.action == 'doEdit'}">
<script>
statusMessage("แก้ใขข้อมูลเรียบร้อยแล้ว\n",'doEdit');
</script>
</c:if>
<c:if test="${stMatrForm.isSusses=='1' && stMatrForm.action == 'doDelete'}">
<script>
statusMessage("ลบข้อมูลเรียบร้อยแล้ว\n",'doDelete');
</script>
</c:if>
</form:form>
</body>
</html>
