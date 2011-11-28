<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<html>
<head>
<title>PDA : DEPOSIT PROTECTION AGENCY</title>

<link href="<%=request.getContextPath() %>/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath() %>/css/sura.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath() %>/Calendar/calendarTLE.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/Calendar/calendar-setup.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/Calendar/lang/calendar-th.js"></script> 
<style type="text/css">
		
		@import url("<%=request.getContextPath() %>/Calendar/calendar-win2k-cold-1.css");
		
		</style>
	<script type="text/javascript" src="<%=request.getContextPath() %>/ckeditor/ckeditor.js"></script>
	<script src="<%=request.getContextPath() %>/ckeditor/sample.js" type="text/javascript"></script>
	<link href="<%=request.getContextPath() %>/ckeditor/sample.css" rel="stylesheet" type="text/css" />
</head>

<script language="javascript">
function beginchk(ip,ek) {//เริ่มต้นการรับพารามิเตอร์จากคีย์บอร์ด
	
	if((ek.keyCode>47&&ek.keyCode<58)||ek.keyCode==8||ek.keyCode==46||ek.keyCode==144||ek.keyCode==111||(ek.keyCode>95&&ek.keyCode<106)||(ek.keyCode>36&&ek.keyCode<41)){//อนุญาตให้พิมพ์ตัวเลข Delete Backspace Left Right Up Down
	
	if(ip.value.match("^([0-9]{2})/([0-9]{2})//$")){
		ip.value=ip.value.substring(0,6);
		return true;
	}else if(ip.value.match("^([0-9]{2})/([0-9]{2})$")&&ek.keyCode!=8){//ตรวจสอบพารามิเตอร์โดยเลือกใช้ Regular Expression
		ip.value=ip.value + "/";
		return true;
	}else if(ip.value.match("^([0-9]{2})//$")&&ek.keyCode!=8){
		ip.value=ip.value.substring(0,3);
		return true;
	}else if(ip.value.match("^([0-9]{2})$")&&ek.keyCode!=8){//ตรวจสอบพารามิเตอร์โดยเลือกใช้ Regular Expression
		ip.value=ip.value + "/";
		return true;
	}else if(ip.value.match("^([0-9]{2})/([0-9]{2})/([0-9]{4})$")){//ตรวจสอบพารามิเตอร์โดยเลือกใช้ Regular Expression
		isDate(ip.value);//ส่งพารามิเตอร์ไปตรวจสอบที่ฟังก์ชั่น isDate ว่ากรอกวันเดือนปีถูกต้องหรือไม่
		return true;
	}else if(ip.value.length>10){//เงื่อนไขนี้ตรวจสอบว่าห้ามกรอกข้อมูลเกินสิบหลักถ้าเกินให้ตัดตัวสุดท้ายทิ้ง
		ip.value=ip.value.substring(0,10);
		isDate(ip.value);
		return true;
	}
	
	}else{//แจ้งเตือนถ้าคีย์ข้อความที่ไม่ใช่ตัวเลข
		ip.value="" ;
		return true;
	}
	}
	
	//เริ่มต้นเช็คค่าวันที่ที่กรอกลงมา
	//Date Validation just copy and paste this cod
	
	var dtCh= "/";
	var minYear=1900;
	var maxYear=2100;
	var minYearA=1900+543;//ค่าตัวแปรที่สองเอาไว้โชว์ค่าพุทธศักราชไทยอะครับ ^^
	var maxYearA=2100+543;//ค่าตัวแปรที่สองเอาไว้โชว์ค่าพุทธศักราชไทยอะครับ ^^
	
	function isInteger(s)//ฟังก์ชั่นเช็คค่าตัวเลข
	{
	var i;
	for (i = 0; i < s.length; i++)
	{ 
	// Check that current character is number.
	var c = s.charAt(i);
	if (((c < "0") || (c > "9")))  return false;
	  }
	// All characters are numbers.
	return true;
	}
	
	function stripCharsInBag(s, bag)//เช็ครูปแบบ
	{
	var i;
	var returnString = "";
	// Search through string's characters one by one.
	// If character is not in bag, append to returnString.
	for (i = 0; i < s.length; i++)
	{ 
	var c = s.charAt(i);
	if (bag.indexOf(c) == -1) returnString += c;
	}
	return returnString;
	}
	
	function daysInFebruary (year)//เช็คค่าวันที่ 29 กุมภาพันธ์ ในแต่ละปี
	{
	// February has 29 days in any year evenly divisible by four,
	// EXCEPT for centurial years which are not also divisible by 400.
	return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
	}
	function DaysArray(n) //เช็ควันสุดท้ายของแต่ละเดือน
	{
	for (var i = 1; i <= n; i++) 
	{
	this[i] = 31
	if (i==4 || i==6 || i==9 || i==11) {this[i] = 30}
	if (i==2) {this[i] = 29}
	} 
	return this
	}
	
	function isDate(dtStr)//ฟังก์ชั่นหลักในการเช็คค่าวันที่ ถ้ากรอกวันเดือนปีผิดจะแจ้งเตือนผู้ใช้งาน
	{
	var daysInMonth = DaysArray(12)
	var pos1=dtStr.indexOf(dtCh)
	var pos2=dtStr.indexOf(dtCh,pos1+1)
	var strDay=dtStr.substring(0,pos1)
	var strMonth=dtStr.substring(pos1+1,pos2)
	var strYear=dtStr.substring(pos2+1)
	strYr=strYear
	
	if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1)//ตัดเลข0
	
	if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1)//ตัดเลข0
	
	for (var i = 1; i <= 3; i++) {
	
	if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1)//ตัดเลข0
	}
	day=parseInt(strDay)
	month=parseInt(strMonth)
	year=parseInt(strYr)
	year=year-543;
	
	if (pos1==-1 || pos2==-1)
	{
	
	//alert("รูปแบบวันที่ผิด : วัน/เดือน/ปี");
	ip.value="" ;
	return false;
	}
	
	if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month])
	{
		
	//alert("กรุณาใส่วันที่ให้ถูกต้อง");
	ip.value="" ;
	return false;
	}
	
	if (strMonth.length<1 || month<1 || month>12)
	{
		
	//alert("กรุณาใส่เดือนให้ถูกต้อง");
	ip.value="" ;
	return false;
	}
	
	if (strYear.length != 4 || year==0 || year<minYear || year>maxYear)
	{
		ip.value="" ;
	//alert("กรุณาใส่ปีให้ถูกต้อง"+minYearA+" and "+maxYearA);
	ip.value="" ;
	return false;
	}
	
	if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false)
	{
		ip.value="" ;
	//alert("กรุณาใส่รุปแบบวันที่ให้ถูกต้อง");
	ip.value="" ;
	return false;
	}
	return true
	}
	// control button
	function callfunc(functype)
	{	 
		//document.getElementById("FUNC").value = functype;
		 
		var msg = "";
		if(functype!="doDelete"){ 
			if(document.getElementById("SUBMATR_NAME").value  == "" || document.getElementById("SUBMATR_NAME").value  == " ")
			{
				msg += "- กรุณากรอกหมวดย่อย\n";
			}
			
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
		 
	}

	//-->
</script>
<%--
request.setCharacterEncoding("TIS-620");
response.setCharacterEncoding("TIS-620");
response.setHeader("Cache-Control", "no-cache"); 
	ObjectControl myObjControl = new ObjectControl();
	String sql = "";
	String SUBMATR_NAME = "";
	String SUBMATR_ORDER="";
	String SUBMATR_ID="";
	String editIf =""; 
	//Edit Value
	String VLE_SUBMATR_ID="";
	String VLE_SUBMATR_NAME="";
	String VLE_SUBMATR_ORDER=""; 
	 
	String checkedActive1="";
	String checkedActive2="";
	 
	Connection conn = null;
	DataBaseControl mycontrol = new DataBaseControl();
	StringControl myStrControl=new StringControl();
	Class.forName(VariableControl.gDB_Driver);
	conn = mycontrol.gConnectDB();
	Statement stmt = conn.createStatement();
	String FUNC = myStrControl.gNullToBlank(request.getParameter("FUNC"));
 
	SUBMATR_ID =myStrControl.gNullToBlank(request.getParameter("SUBMATR_ID"));  
	
	//out.println("FUNC>>"+MATRA_DETAIL);
	if(FUNC != null)
	{ 
	 
		SUBMATR_NAME = myStrControl.gNullToBlank(request.getParameter("SUBMATR_NAME"));
		SUBMATR_ORDER = myStrControl.gNullToBlank(request.getParameter("SUBMATR_ORDER")); 
		if(FUNC.equals("SaveAction")&& SUBMATR_ID.equals(""))
		{
			 
			stmt = conn.createStatement();
			SUBMATR_ID = mycontrol.gGetmax("MS_SUBMATR","SUBMATR_ID","").toString();		
		
			sql = " SELECT SUBMATR_ID FROM MS_SUBMATR WHERE NAME ='"+SUBMATR_NAME+"' and SUBMATR_ORDER ='"+SUBMATR_ORDER+"'";
			System.out.println("sqlcheck>>>"+sql);
			ResultSet rsIn = stmt.executeQuery(sql);
			int i=0;
			if(!rsIn.next())
			{
				System.out.println(" I  values >> "+i);
				sql =" INSERT INTO  MS_SUBMATR(SUBMATR_ID,NAME, SUBMATR_ORDER ) "+
				" VALUES ('"+SUBMATR_ID+"','"+SUBMATR_NAME+"' , '"+SUBMATR_ORDER+"')";

				System.out.println("sql>>"+sql);
			int rsIn2 = stmt.executeUpdate(sql);
				// mycontrol.gExecuteQuery(sql);
				  
			 if(rsIn2>0){
				
			 }
			}else{
				out.println("<script language=JavaScript>"+ 
				"alert('ข้อมูลซ้ำ');  </script>");
				
			}
				 
		i=i++;
				 
		}
		if(FUNC.equals("SaveAction")&& !SUBMATR_ID.equals("")){
			String sqlUp = "UPDATE MS_SUBMATR SET";
			
			sqlUp = sqlUp + " NAME ='"+SUBMATR_NAME+"',";
			sqlUp = sqlUp + " SUBMATR_ORDER ='"+SUBMATR_ORDER+"'";
			sqlUp = sqlUp + " WHERE SUBMATR_ID ='"+SUBMATR_ID+"'";
			System.out.println("sqlUp>>"+sqlUp);
			
			int rsUp = stmt.executeUpdate(sqlUp);
			if(rsUp>0){
				out.println("<script language=JavaScript>"+ 
				"alert('บันทึกข้อมูลเรียบร้อย'); </script>");
			}
		}
		if(FUNC.equals("deleteAction")&& !SUBMATR_ID.equals("")){
			
			String sqlDe = "DELETE FROM MS_SUBMATR WHERE SUBMATR_ID ="+SUBMATR_ID;
			
			int rsDe = stmt.executeUpdate(sqlDe);
			if(rsDe>0){
			out.println("<script language=JavaScript>"+ 
					"alert('ลบข้อมูลเรียบร้อย');</script>");
			}
		}
	}
	if(!SUBMATR_ID.equals("")){
		String sSqlS="";
		sSqlS = " SELECT * from MS_SUBMATR where SUBMATR_ID='"+SUBMATR_ID+"'";
		
		 System.out.println(sSqlS);
		ResultSet rst  = stmt.executeQuery(sSqlS);

		if (rst.next()) {
			VLE_SUBMATR_ID=rst.getString("SUBMATR_ID"); 
			VLE_SUBMATR_NAME=  myStrControl.gNullToBlank(rst.getString("NAME"));
			VLE_SUBMATR_ORDER =  myStrControl.gNullToBlank(rst.getString("SUBMATR_ORDER"));
					}
	} 
	
--%>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" >
<spring:url value="/information_law/SubMatr.do" var="formAction">
</spring:url>
<form:form modelAttribute="msSubmatrForm"  id="form1" method="post" action="${fn:escapeXml(formAction)}" >
<form:hidden path="action" id="action"/> <%-- 
<form name="form1"  id="form1"   method="post" action="SubMatr.jsp" >
--%>
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td valign="top">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="25" class="text">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td height="30" class="head2"><font color="#FF6600"> ข้อมูลกฎหมาย >> หมวดย่อย  >></font><font color="#000000"> รายละเอียดข้อมูล </font></td>
								<td align="right">
									<INPUT TYPE="button" value="ค้นหา" style="width: 80px" onClick="javascript:window.location='<spring:url value="/information_law/SubMatr.do?action=search" htmlEscape="true" />'" >
									<c:if test="${msSubmatrForm.action=='edit' || msSubmatrForm.action=='doEdit'}"> 
									<INPUT TYPE="submit" value="บันทึก" style="width: 80px" onClick="return callfunc('doEdit')" >		
									<INPUT	TYPE="submit" value="ลบข้อมูล" style="width: 80px" onClick="return callfunc('doDelete')" />									 </c:if>
									 <c:if test="${msSubmatrForm.action!='edit' && msSubmatrForm.action!='doEdit'}"> 
									<INPUT TYPE="submit" value="บันทึก" style="width: 80px" onClick="return callfunc('doSave')" >						
									<INPUT	TYPE="submit" value="ลบข้อมูล" style="width: 80px" onClick="return callfunc('doDelete')" 
										<c:if test="${msSubmatrForm.msSubmatr.submatrId==null}">
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
								<td valign="top" align='right' class="Ghead">หมวดย่อย</td>
								<td valign="top" align='left' class="text">
									<form:input path="msSubmatr.name" id="SUBMATR_NAME" cssClass="text" size="20"/> 								</td> 
							</tr>
							<tr>
								<td valign="top" align='right' class="Ghead">ลำดับที่</td>
								<td >
								<form:input path="msSubmatr.submatrOrder" id="SUBMATR_ORDER" cssClass="text" onkeyup="beginchk(this,event)" onkeypress="beginchk(this,event)" size="20"/>
								</td>
							</tr>
					 
							<tr>
							  <td class="Ghead">&nbsp;							    </td>
						            <td class="text">&nbsp;</td>
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
			document.getElementById("SUBMATR_NAME").value="";
			document.getElementById("SUBMATR_ORDER").value="";		 
		}
		
	}
</script>
<c:if test="${msSubmatrForm.isSusses=='1' && msSubmatrForm.action == 'doSave'}">
<script>
statusMessage("เพิ่มข้อมูลเรียบร้อยแล้ว\n",'doSave');
</script>
</c:if>
<c:if test="${msSubmatrForm.isSusses=='1' && msSubmatrForm.action == 'doEdit'}">
<script>
statusMessage("แก้ใขข้อมูลเรียบร้อยแล้ว\n",'doEdit');
</script>
</c:if>
<c:if test="${msSubmatrForm.isSusses=='1' && msSubmatrForm.action == 'doDelete'}">
<script>
statusMessage("ลบข้อมูลเรียบร้อยแล้ว\n",'doDelete');
</script>
</c:if></form:form>
</body>
</html>
