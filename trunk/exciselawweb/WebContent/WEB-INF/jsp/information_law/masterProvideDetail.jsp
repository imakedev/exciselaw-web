<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/includes.jsp" %><%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.io.File" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript"
        src='<%=request.getContextPath() %>/dwr/interface/ExciseLawAjax.js'> 
</script>
<script type="text/javascript"
        src='<%=request.getContextPath() %>/dwr/engine.js'> 
</script>
<script type="text/javascript"
        src='<%=request.getContextPath() %>/dwr/util.js'>
</script><script language="JavaScript" type="text/JavaScript">
function CallSearchResult(){
	var REQNO=document.form1.REQNO.value;
	var LIQR=document.form1.LIQR.value;
	var DTLL=document.form1.DTLL.value;
	var ADDR=document.form1.ADDR.value;
	var REGARD_DOC=document.form1.REGARD_DOC.value;
	
	window.open("AlcoholLarge_Sale_Search_Result.jsp?REQNO="+REQNO+"&LIQR="+LIQR+"&DTLL="+DTLL+"&ADDR="+ADDR+"&REGARD_DOC="+REGARD_DOC, "wa");
}
function doCBO()
{  
  document.form1.submit();
}
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
	function callfunc(functype)
	{	 
		//document.getElementById("FUNC").value = functype;
		 
		var msg = "";
		if(functype!="doDelete"){
			/*
			if(document.getElementById("statuteId").value == "0")
			{
				msg += "- กรุณาเลือกพระราชบัญญัติ\n";
			}
			if(document.getElementById("submatrId").value == "0")
			{
				msg += "- กรุณาเลือกหมดย่อย\n";
			}
			if(document.getElementById("MATRGROUP_NAME").value  == "" || document.getElementById("MATRGROUP_NAME").value  == " ")
			{
				msg += "- กรุณากรอกหมวด\n";
			}
			*/
		}
		if(msg != "")
		{
			alert(msg); 
			return false;
		} 
		document.getElementById("action").value = functype;
		return true;
		/*
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
		*/
		 
	}function callfunc2(functype)
{	var tagForm = document.getElementById("form1");
 //alert(functype);
	document.getElementById("FUNC").value = functype;	
	
	if (functype=='new'){
		
		document.getElementById("STATUTE_NAME").value="";
	//	document.form1.TOBAC_COMMET.value="";
		return false;
	}
	var msg = "";
	var  cvu = new Array();
	var  cvr = new Array();
	var mu=0;
	 var xcu = document.form1.LEGALITEMID; 
	if(functype == "save")
	{
	
		if(document.getElementById("STATUTE_NAME").value == "")
		{ 
			msg += "- กรุณาใส่ประเภทพระราชบัญญัติ\n";
		}
		if(msg != "")
		{
			alert(msg);
		}
		else
		{  
			 for (cu=0;cu< xcu.length ;cu++){ 
				  var c_checku =xcu[cu].checked;
 
			                if(c_checku == false ){
			                }else{
			                	 
					cvu[mu]=""+xcu[cu].value+""; 
					mu++;
					  }
				}	 
				document.getElementById("LEGALITEMID_TEXT").value=cvu;
			tagForm.submit();
			return true;	
		}
	}
	if(functype == 'del')
	{
	//	alert(document.getElementById("FUNC").value);
	//	alert(document.getElementById("STATUTE_ID").value);
	
	tagForm.submit();
		
		return true;
	}
}
function F_A(){
	window.location="MasterProvideDetail.jsp","wa";	
}
function F_Search(){
	document.form1.action  = "MasterProvide.jsp";
	document.form1.submit();
}
function setTsStatuteLegalitem(obj){
	//var action = document.getElementById('action');
	var statuteId = document.getElementById('msStatute.statuteId'); 
	var action = obj.checked?"check":"uncheck";
//	alert("action="+action)
	if(statuteId.value!=''){
		ExciseLawAjax.setTsStatuteLegalitem(obj.value,statuteId.value,action,{
			 callback:function(dataFromServer){
	             if(dataFromServer!=null && dataFromServer.length>0){
					 //alert(dataFromServer)
					 //document.getElementById('myDiv').innerHTML=dataFromServer;
	             }
			 }
		});
	}
/* 	alert(obj.value)
	alert(obj.checked) 
	alert("statuteId="+statuteId.value)
	alert("action="+action.value) */
	//
}
	

</script>
<%--  
request.setCharacterEncoding("TIS-620");
response.setCharacterEncoding("TIS-620");
response.setHeader("Cache-Control", "no-cache"); 
	String sql ="";
	Connection conn = null;
	DataBaseControl mycontrol = new DataBaseControl();
	StringControl myStrControl=new StringControl();
	VariableControl myVarControl =new VariableControl();
	DateControl myDateControl=new DateControl();
	ObjectControl myObjControl=new ObjectControl();
	String STATUTE_NAME ="";
	String UPUSER ="";
	String UPDT = "";
	String ACTIVE ="";
	String FUNC ="";
	String LEGALWHERE = "";
	String STATUTE_ID = "";
	String STATUTE_NAME_ ="";
	String ACTIVE_ =""; 
	String LEGALITEMID_VL;
	String CUNRRENT_ORDER="";
	String STATUTE_ORDER="";
	boolean  CK_IN=true;
	
	conn= mycontrol.gConnectDB();
	Statement stmt = conn.createStatement();
	ResultSet rs,rsedit  = null;
	System.out.println("aaaaa");
if(MultipartFormDataRequest.isMultipartFormData(request)){
	MultipartFormDataRequest mrequest = new MultipartFormDataRequest(request);
	 FUNC =mrequest.getParameter("FUNC"); 
	  LEGALWHERE = myStrControl.gNullToBlank(mrequest.getParameter("STATUTE_ID"));
	  LEGALITEMID_VL=myStrControl.gNullToBlank(mrequest.getParameter("LEGALITEMID_TEXT"));
	  STATUTE_ID = mycontrol.gGetmax("MTSTATUTE","STATUTE_ID","").toString();
	  
	
	if(LEGALWHERE != "")
	{
		STATUTE_ID = LEGALWHERE;
	}
	if(FUNC != null)
	{
		//String LEGAL_MAIN_NO =null;
	
		
		if(FUNC.equals("save"))
		{
			STATUTE_NAME =myStrControl.gGetRequestToString( myStrControl.gNullToBlank(mrequest.getParameter("STATUTE_NAME")));
			STATUTE_ORDER =myStrControl.gGetRequestToString( myStrControl.gNullToBlank(mrequest.getParameter("STATUTE_ORDER")));
			ACTIVE = myStrControl.gNullToBlank(mrequest.getParameter("ACTIVE"));
			UPDT = myStrControl.gNullToBlank(mrequest.getParameter("UPDT"));
			//out.println(LEGAL_MAIN_NO);
			stmt = conn.createStatement();
			
			if(LEGALWHERE.equals("")){
			String sqlCK = " SELECT STATUTE_ID FROM MS_STATUTE WHERE NAME ='"+STATUTE_NAME+"' OR  STATUTE_ORDER ='"+STATUTE_ORDER+"' ";
			System.out.println("check ID =>>"+sql);
			
			rs = stmt.executeQuery(sqlCK);
			int aaa=1;
			if(!rs.next())
			{ System.out.println("aaa>>"+aaa);
				STATUTE_ID = mycontrol.gGetmax("MS_STATUTE","STATUTE_ID","").toString();
				String sqlin = " INSERT INTO MS_STATUTE(STATUTE_ID,NAME,STATUTE_ORDER)VALUES('"+STATUTE_ID+"','"+STATUTE_NAME+"','"+STATUTE_ORDER+"')";
				System.out.println("sql In"+sqlin);
			int	checkIN1 =stmt.executeUpdate(sqlin);
				 
			}else{
				CK_IN=false;
				out.println("<script language=JavaScript>"+ 
				"alert('ข้อมูลซ้ำ');</script>");
				
			}}
			String p_id="AAAA";
			String filetype="";
			String pic="";
			if (MultipartFormDataRequest.isMultipartFormData(request)){
		 
			Hashtable files = mrequest.getFiles();
			UploadFile file =(UploadFile) files.get("filUpload[]");
			System.out.println("file.getData()>>"+file.getData());
			if(file.getData()!=null){
			String filename=file.getFileName().toLowerCase();
			filetype=filename.substring(filename.lastIndexOf("."),filename.length());
			//if((filetype.indexOf("gif")==-1)&&(filetype.indexOf("jpeg")==-1)&&(filetype.indexOf("jpg")==-1)){
			//errors.add("ตรวจสอบชนิดไฟล์รูปภาพ");
		    //	}
			 pic=p_id+filetype;
			}
			//ตรวจสอบว่ามีข้อผิดพลาดอยู่ใน errors หรือไม่ ถ้ามีให้แสดงข้อผิดพลาดออกมา
			 
			if(file.getData()!=null){

			 
			file.setFileName(pic);
			up.store(mrequest, "file");
			
			}}
			//   }
			try
			{
			
	 	if(!STATUTE_ID.equals("")){
	 		String statute_item[]  = LEGALITEMID_VL.split(","); 
					    String sqlCData="DELETE FROM TS_STATUTE_LEGALITEM WHERE   STATUTE_ID='"+STATUTE_ID+"'";

					 int   checkIN2 =stmt.executeUpdate(sqlCData);
						
						for(int i=0;i<statute_item.length;i++){
						String sqlItem="INSERT INTO TS_STATUTE_LEGALITEM (LEGAL_REL_ID,LEGALITEM_ID,STATUTE_ID) "+
						"VALUES ((select decode( max(LEGAL_REL_ID),null,1,max(LEGAL_REL_ID)+1)as id_v from ts_statute_legalitem)"+
								",'"+statute_item[i]+"','"+STATUTE_ID+"')";
						System.out.println("sqlItem>>"+sqlItem);
						int checkIN3 =stmt.executeUpdate(sqlItem);
						}
					} 
		}
			catch (SQLException e) {
		         e.printStackTrace();
		      
			} 
				sql = "UPDATE MS_STATUTE SET";
				sql = sql + " NAME ='"+STATUTE_NAME+"',";
				sql = sql + " STATUTE_ORDER ='"+STATUTE_ORDER+"',";
			//	sql = sql + " ATTACH_FILE ='"+token+"',";
				sql = sql + " ACTIVE ='"+ACTIVE+"'";
				sql = sql + " WHERE STATUTE_ID ='"+STATUTE_ID+"'";
				System.out.println("sql>>"+sql);
				int rsIn2 = stmt.executeUpdate(sql);
		if(rsIn2>1 && CK_IN==true){
				out.println("<script language=JavaScript>"+ 
						"alert('บันทึกข้อมูลประเ ภทพระราชบัญญัติ เรียบร้อย');</script>");
		}
		}
		if(FUNC.equals("del"))
		{
			String sqlitem =" DELETE FROM TS_STATUTE_LEGALITEM WHERE   STATUTE_ID='"+LEGALWHERE+"'  ";
			int rsIn2 =stmt.executeUpdate(sqlitem); 
			sql = "DELETE FROM MS_STATUTE WHERE STATUTE_ID ='"+LEGALWHERE+"'";
			
			System.out.println("Delete >>"+sql);
			  rsIn2 =stmt.executeUpdate(sql); 
		
			if(rsIn2>0 ){
				out.println("<script language=JavaScript>"+ 
					"alert('ลบข้อมูลประเ ภทพระราชบัญญัติ   เรียบร้อย');</script>");
			}
		}
	}
	////รับค่า

	}else{
		 LEGALWHERE = myStrControl.gNullToBlank(request.getParameter("STATUTE_ID"));
	}

	sql = "SELECT max(STATUTE_ORDER)+1 AS CUNRRENT_ORDER FROM MS_STATUTE ";
	rs = stmt.executeQuery(sql); 
	rs.next();
	CUNRRENT_ORDER= rs.getString("CUNRRENT_ORDER");
	rs.close();
System.out.println("LEGALWHERE>>"+LEGALWHERE);
if (LEGALWHERE!="")
{
	Class.forName(VariableControl.gDB_Driver); 			
	conn= mycontrol.gConnectDB();		
	stmt = conn.createStatement();
	sql = "SELECT * FROM MS_STATUTE WHERE STATUTE_ID ='"+LEGALWHERE+"'";
	rsedit = stmt.executeQuery(sql); 		
	while(rsedit.next()) 
	{
		STATUTE_NAME_ = myStrControl.gNullToBlank(rsedit.getString("NAME"));	
		ACTIVE_ = myStrControl.gNullToBlank(rsedit.getString("ACTIVE"));
		CUNRRENT_ORDER=myStrControl.gNullToBlank(rsedit.getString("STATUTE_ORDER"));
		
	}
	rsedit.close();
}
--%>

<link href="<%=request.getContextPath() %>/css/sura.css" rel="stylesheet" type="text/css"> 
<title>Insert title here</title>
<style type="text/css">
<!--
.style1 {font-family: Geneva, Arial, Helvetica, sans-serif}
-->
</style>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="MM_preloadImages('<%=request.getContextPath() %>/images/tool_01b.jpg','<%=request.getContextPath() %>/images/tool_02b.jpg','<%=request.getContextPath() %>/images/tool_03b.jpg','<%=request.getContextPath() %>/images/tool_05b.jpg')">
<spring:url value="/information_law/masterProvideDetail.do" var="formAction">
</spring:url>
<form:form modelAttribute="msStatuteForm"  id="form1" method="post" action="${fn:escapeXml(formAction)}" enctype="multipart/form-data"> 
<form:hidden path="action" id="action"/> 
<form:hidden path="msStatute.statuteId" /> 
<%-- <form id="form1" name="form1" method="post" action="MasterProvideDetail.jsp" enctype="multipart/form-data" >
--%>
<input TYPE="hidden" NAME="FUNC"  id="FUNC"   >
<input TYPE="hidden" NAME="STATUTE_ID" id="STATUTE_ID" VALUE="<%--=LEGALWHERE --%>">
<input TYPE="hidden" NAME="LEGALITEMID_TEXT" id="LEGALITEMID_TEXT" >

<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td valign="top">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="25" class="text">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td height="30" class="Ghead"><strong>กำหนดประเภทกฎหมาย</strong>>> <font color="#FF6600">ประเภทพระราชบัญญัติ</font></strong>>><font color="#000000"> ค้นหาข้อมูล </font></td>
								<td align="right">
								<%--
								<INPUT TYPE="button" value="บันทึกข้อมูล" style="width: 80px" onClick="javascript:callfunc('save');" /> 
								<INPUT TYPE="button" value="ลบข้อมูล" style="width: 80px" onClick="javascript:callfunc('del');" /> 
								<input type="button" name="cmdSearch" class="button" value="ค้นหาข้อมูล"style="width: 80px" onClick="F_Search();" />
			                    <input type="button" class="button" value="เคลียร์" onClick="F_A();" style="width: 80px" /> 
			                     --%>			                    
								<INPUT TYPE="button" value="ค้นหาข้อมูล" style="width: 80px" onClick="javascript:window.location='<spring:url value="/information_law/masterProvideDetail.do?action=search" htmlEscape="true" />'" >
									 <c:if test="${msStatuteForm.action=='edit' || msStatuteForm.action=='doEdit'}">
									<INPUT TYPE="submit" value="บันทึก" style="width: 80px" onClick="return callfunc('doEdit')" >		
									<INPUT	TYPE="submit" value="ลบข้อมูล" style="width: 80px" onClick="return callfunc('doDelete')" />									 </c:if>
									 <c:if test="${msStatuteForm.action!='edit' && msStatuteForm.action!='doEdit'}">
									<INPUT TYPE="submit" value="บันทึก" style="width: 80px" onClick="return callfunc('doSave')" >						
									<INPUT	TYPE="submit" value="ลบข้อมูล" style="width: 80px" onClick="return callfunc('doDelete')" 
										<c:if test="${msStatuteForm.msStatute.statuteId==null}">
										   disabled="disabled"
									    </c:if>
									 />
									 </c:if>
									 <%-- 
									<INPUT TYPE="Reset" value="เคลียร์" style="width: 80px" onClick="return callfunc('doReset')" >
									 --%>								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr><td height='5'>&nbsp;</td></tr>
				<tr align='center'>
					<td align='center'>
						<table width="100%" border="0" cellspacing="0" cellpadding="2" >
							<tr>
								<td valign="top" align='right' class="Ghead">พระราชบัญญัติ</td>
								<td align='left'>
								    <form:input path="msStatute.name" cssClass="field-color" size="50"/>
									&nbsp;&nbsp;&nbsp;<span class="Ghead">&nbsp;ลำดับ</span>&nbsp;									
									<form:input path="msStatute.statuteOrder" cssClass="field-color" size="5"/>
							</tr>
							<tr>
								<td valign="top" align='right' class="Ghead">เอกสารแนบ</td>
								<td align='left'>
									<input type="file" name="file" > <img src="<%=request.getContextPath() %>/images/add_page.png" width="20" border="0" height="20" onClick="JavaScript:addFile();"> 
									<span id="mySpan"></span>
								</td>
							</tr>
							
						
						  <tr><td class="Ghead" valign='top' align='right'>สินค้าและบริการตามพระราชบัญญัติ&nbsp;</td>
		<td align="left">
		<table>
			<tr>
				<td colspan="3" class="Ghead" align="left"><input type="checkbox" name="selectall" id="selectall" onClick="checkall()" />เลือกทั้งหมด</td>
			</tr>
			<tr>
				<td colspan="3" class="Ghead">สินค้า</td>
			</tr>
			<tr>
				<td width="auto"> 
				
					<%-- 
					<form:checkboxes items="${boxes}" itemValue="id" itemLabel="name"  path="tsStatuteLegalitemCheckBoxes"/>
				 	   <c:choose>
    	 					<c:when test="${loop.index mod 2 == 0}">
    						<c:set value="#cfcfcf" var="col0"/>
    						<c:set value="#e6e6e6" var="col1"/>
    						<c:set value="#cfcfcf" var="col2"/>
    						<c:set value="#e6e6e6" var="col3"/>
    						<c:set value="#cfcfcf" var="col4"/>     			
    	 				</c:when>
    	 				<c:otherwise>
    						<c:set value="#E5E5E5" var="col0"/>
    						<c:set value="#FFFFFF" var="col1"/>
    						<c:set value="#d8d8d8" var="col2"/>
    						<c:set value="#f0f0f0" var="col3"/>
    						<c:set value="#d8d8d8" var="col4"/>    			
    	 				</c:otherwise>   
					  </c:choose>
					 <c:out value="${aoe}"/> 
				   --%>
				   <table>
				   <%-- 
				    <tr>
				    --%>
				 <c:set value="" var="endCol"/>
				<c:forEach items="${msLegalitemProducts}" var="box" varStatus="loop">  
			 	    <c:set value="${loop.index mod 3}" var="endCol"/>
				 	<c:choose>
    	 				<c:when test="${loop.index mod 3 == 0}"> 
    	 				   <tr>
    						<td align="left" class="texthead">
							 	<form:checkbox path="productBoxes"   value="${box.legalitemId}" onclick="setTsStatuteLegalitem(this)" />	
								<c:out value="${box.name}"/>
								</td>				 
    	 				</c:when>
    	 				<c:when test="${loop.index mod 3 == 2}">  
    						<td align="left" class="texthead">
							 	<form:checkbox path="productBoxes"  value="${box.legalitemId}" onclick="setTsStatuteLegalitem(this)" />	
								<c:out value="${box.name}"/>
							</td>				 
						  </tr>
    	 				</c:when>
    	 				<c:otherwise>
    						<td align="left" class="texthead">
							 	<form:checkbox path="productBoxes"  value="${box.legalitemId}" onclick="setTsStatuteLegalitem(this)" />	
								<c:out value="${box.name}"/> 
							 </td> 
    	 				</c:otherwise>   
					  </c:choose>
				  
				</c:forEach>
				 <c:if test="${endCol!='2'}">
				  	</tr>
				 </c:if>
				  <%--
					 </tr>
				  --%>
				<%-- 
				<tr>
				<td>
				<input type="submit" value="aoe" />
				</td>
				</tr>
				--%> 
				</table>								<%--
				
				conn = mycontrol.gConnectDB();
				Statement state = conn.createStatement();
					out.println("<table><tr>");
					int row = 0;
					state = conn.createStatement();
					sql = "SELECT * FROM MS_LEGALITEM  WHERE LEGALITEMTYPE_ID = '1' ORDER BY LEGALITEM_ORDER ASC";
					ResultSet	rs1 = state.executeQuery(sql);
					while (rs1.next()) {
						String CHK_3 = "";
						row++;
						out.println("<td align='left'>");
						 Statement state1 = conn.createStatement();
					String	sSql = " SELECT LEGALITEM_ID FROM TS_STATUTE_LEGALITEM WHERE STATUTE_ID ='"
								+ LEGALWHERE + "' "; 
					System.out.println("sSql>>>>>>>>>>>>"+sSql);
						ResultSet rsItem = state1.executeQuery(sSql);
						while (rsItem.next()) {
							if (rsItem.getString("LEGALITEM_ID").equals(
									rs1.getString("LEGALITEM_ID"))) {
								CHK_3 = "checked='checked'";
							}
						} 
				--%>
				
				</td>
			</tr>
			<tr>
	<td colspan="3" class="Ghead">บริการ</td>
			</tr>
			<tr>
				<td align="left" width="auto">
			 <%--
				
				conn = mycontrol.gConnectDB();
				Statement state1 = conn.createStatement();
					out.println("<table><tr>");
					int row1 = 0;
					state = conn.createStatement();
					sql = "SELECT * FROM MS_LEGALITEM  WHERE LEGALITEMTYPE_ID = '2' ORDER BY LEGALITEM_ORDER ASC";
					ResultSet rs2 = state.executeQuery(sql);
					while (rs2.next()) {
						String CHK_4 = "";
						row1++;
						out.println("<td align='left'>");
						 Statement state2 = conn.createStatement();
						String sSql = " SELECT LEGALITEM_ID FROM TS_STATUTE_LEGALITEM WHERE STATUTE_ID ='"
							+ LEGALWHERE + "' ";
						ResultSet rsItem2 = state2.executeQuery(sSql);
						while (rsItem2.next()) {
							if (rsItem2.getString("LEGALITEM_ID").equals(
									rs2.getString("LEGALITEM_ID"))) {
								CHK_4 = "checked='checked'";
							}
						} 
				--%>
			<table>				<c:set value="" var="endCol2"/>
				<c:forEach items="${msLegalitemServices}" var="box" varStatus="loop">  
			 	    <c:set value="${loop.index mod 3}" var="endCol2"/>
				 	<c:choose>
    	 				<c:when test="${loop.index mod 3 == 0}"> 
    	 				   <tr>
    						<td align="left" class="texthead">
							 	<form:checkbox path="serviceBoxes"  value="${box.legalitemId}" onclick="setTsStatuteLegalitem(this)" />	
								<c:out value="${box.name}"/>
								</td>				 
    	 				</c:when>
    	 				<c:when test="${loop.index mod 3 == 2}">  
    						<td align="left" class="texthead">
							 	<form:checkbox path="serviceBoxes"  value="${box.legalitemId}" onclick="setTsStatuteLegalitem(this)" />	
								<c:out value="${box.name}"/>
							</td>				 
						  </tr>
    	 				</c:when>
    	 				<c:otherwise>
    						<td align="left" class="texthead">
							 	<form:checkbox path="serviceBoxes"  value="${box.legalitemId}" onclick="setTsStatuteLegalitem(this)" />	
								<c:out value="${box.name}"/> 
							 </td> 
    	 				</c:otherwise>   
					  </c:choose>
				  
				</c:forEach>
				 <c:if test="${endCol2!='2'}">
				  	</tr>
				 </c:if>
				  <%--
					 </tr>
				  --%>
				<%-- 
				<tr>
				<td>
				<input type="submit" value="aoe" />
				</td>
				</tr>
				--%> 
				</table>	
				 

				<%--
					out.println("</td>");
						if ((row1) % 3 == 0) {
							out.println("</tr>");
						} else {
							out.println("<td>");
						}
					}
					out.println("</tr></table>");
					
					
					
				//	if(VLE_ACTIVATE.trim().equals("0")){
				//		checkedActive1="";
				//		checkedActive2="checked";
				//	}else{
				//		checkedActive1="checked";
				//		checkedActive2="";
						
				//	}
				--%>
				</td>
			</tr>
		</table>
		</td></tr>
							<tr>
								<td valign="top" align='right' class="Ghead">สถานะ</td>
								<td class="text" align='left'>
								<%--
								String sChk_1="";
								String sChk_2="";
								if(ACTIVE_.trim().equals("0")){
									sChk_1="";
									sChk_2="checked";
								}else{
									sChk_1="checked";
									sChk_2="";
								}
								 --%> 
								 <form:radiobutton path="msStatute.active" value="1"/>ACTIVE
								 <form:radiobutton path="msStatute.active" value="0"/>NO ACTIVE
								</td>
							</tr>
							
						</table>
						
					</td>
				</tr>
				
			</table>
		</td>
	</tr>
</table>
</form:form>
</body>
<script language ="javascript">
 
var checkflag = "false";
var checkflagUser = "false";
function checkall(){
	checkallByElementName(document.getElementsByName('productBoxes'));
	checkallByElementName(document.getElementsByName('serviceBoxes')); 
}
function checkallByElementName(el_collection){
	var selectallValue= document.getElementById("selectall");
	var statuteId = document.getElementById('msStatute.statuteId'); 
	var isUpdate = statuteId.value!=''?true:false;
	//if(selectallValue.checked){
		for (var i=0;i<el_collection.length;i++){
			if(isUpdate){
	 			if(el_collection[i].checked !=selectallValue.checked){
	 				//alert("before="+el_collection[i].checked)
	 				el_collection[i].checked=selectallValue.checked;
	 				//alert("affter="+el_collection[i].checked)
	 				setTsStatuteLegalitem(el_collection[i]);
	 				//el_collection[i].checked=selectallValue.checked;
	 			}
	 		}
	 		el_collection[i].checked=selectallValue.checked;
	 		
	 	}
	//}
/*
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
  */
}  

</SCRIPT>
<script language="javascript">
// Multi uploadfile
var fileId = 0;  
function addElement(parentId, elementTag, elementId, html) { 
    var p = document.getElementById(parentId);
    var newElement = document.createElement(elementTag);
    newElement.setAttribute('id', elementId);
    newElement.innerHTML = html;
    p.appendChild(newElement);
}	
function addFile() {
    fileId++;  
    var html = '<input type="file" name="file" />  <a href=" + fileId +  " onclick="javascript:removeElement(\'file-' + fileId +'\'); return false;"><img src="<%=request.getContextPath() %>/images/delete_page.png" width="20" border="0" height="20"></</a>';
    addElement('mySpan', 'p', 'file-' + fileId, html);
}
function removeElement(elementId) { 
    var element = document.getElementById(elementId);
    element.parentNode.removeChild(element);
}
</script>
<script>
	function statusMessage(msg,mode){
		alert(msg);
		if(mode=='doSave'){ 
			document.getElementById("msStatute.statuteId").value="";
			document.getElementById("msStatute.name").value="";
			document.getElementById("msStatute.statuteOrder").value="";		 
			checkall();
		}
		
	}
</script>
<c:if test="${msStatuteForm.isSusses=='1' && msStatuteForm.action == 'doSave'}">
<script>
statusMessage("เพิ่มข้อมูลเรียบร้อยแล้ว\n",'doSave');
</script>
</c:if>
<c:if test="${msStatuteForm.isSusses=='1' && msStatuteForm.action == 'doEdit'}">
<script>
statusMessage("แก้ใขข้อมูลเรียบร้อยแล้ว\n",'doEdit');
</script>
</c:if>
<c:if test="${msStatuteForm.isSusses=='1' && msStatuteForm.action == 'doDelete'}">
<script>
statusMessage("ลบข้อมูลเรียบร้อยแล้ว\n",'doDelete');
</script>
</c:if>
</html>

