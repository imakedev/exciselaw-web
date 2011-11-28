<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<html>
<head>
<title>ระบบกฏหมายสรรพสามิต</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.4.2.js" ></script>
<script type="text/javascript" src='<%=request.getContextPath() %>/dwr/interface/ExciseLawAjax.js'></script>
<script type="text/javascript" src='<%=request.getContextPath() %>/dwr/engine.js'></script>
<script type="text/javascript" src='<%=request.getContextPath() %>/dwr/util.js'></script>
<script language="JavaScript" type="text/JavaScript">
var _path = '<%=request.getContextPath()%>';
function submitPage(){
	var userId = document.getElementById("userId").value;
	var password = document.getElementById("password").value;
	if(userId == undefined || userId == ''){
		alert('กรุณากรอกชื่อผู้ใช้');
		return;
	}
	if(password == undefined || password == ''){
		alert('กรุณากรอกรหัสผ่าน');
		return;
	}
	document.forms["login_form"].submit();
	/*
	ExciseLawAjax.checkLogin(user_id, password,{
		callback:function(dataFromServer){
			alert(dataFromServer);
			window.location=_path+'/index.do';
		}
	});
	return false;
	*/
}

</script>
</head>

<body background="images/login_bg.jpg" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<form name="login_form" id="login_form" method="post" action="index.do">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td align="center" valign="middle">
			<table width="550" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td height="330" background="images/login.gif">
						<table width="100%" height="180" border="0" cellpadding="0" cellspacing="0">
							<tr> 
								<td>&nbsp;</td>
								<td height="100" colspan='2'>&nbsp;</td>
							</tr>
							<tr> 
								<td>&nbsp;</td>
								<td valign="top"> 
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr height="50" > 
											<td width="102" align="right"><font color="gray"><span class="text">ชื่อผู้ใช้ :</span></font></td>
											<td width="316"><input type="text" name="userId" id="userId" style="width:400px;" onkeyup="if (event.keyCode == 13){submitPage();};"> </td>
										</tr>
										<tr height="50" > 
											<td width="102" align="right"><font color="gray"><span class="text">รหัสผ่าน :</span></font></td>
											<td width="316"><input type="password" name="password" id="password" style="width:400px;" onkeyup="if (event.keyCode == 13){submitPage();};"></td>
										</tr>
									</table>
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr> 
											<td align="center" colspan="2" height="50">
												<a id="login-button" href="#" onclick="submitPage();"><img src="<%=request.getContextPath()%>/images/login_btn.gif" border="0" style="height : 32px; width : 80px;"/></a>
											</td>
										</tr>
									</table>
								</td>
								<td>&nbsp;</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</form>
<%
String result = "";
if(request.getSession().getAttribute("result")!=null){ 
	result = (String)request.getSession().getAttribute("result");
}
%>
<script type="text/javascript">
<% if(result.equals("false")){ %>
	alert('ชื่อผู้ใช้ หรือ รหัสผ่าน ไม่ถูกต้อง');
<% } %>	
</script>
</body>
</html>
