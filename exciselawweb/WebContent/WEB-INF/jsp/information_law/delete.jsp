<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title></title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="robots" content="noindex, nofollow" />
		<link href="../sample.css" rel="stylesheet" type="text/css" />
		<link rel="shortcut icon" href="../fckeditor.gif"
				type="image/x-icon" />	
	</head>
	<%--
	    response.setHeader("Cache-Control", "no-cache"); 
		DataBaseControl mycontrol = new DataBaseControl();
		StringControl myStrControl=new StringControl();
		DateControl myDateControl=new DateControl();
		ObjectControl myObjControl=new ObjectControl();
		request.setCharacterEncoding("TIS-620");
	    response.setCharacterEncoding("TIS-620");
		Connection con = null;
		Class.forName(VariableControl.gDB_Driver); 		
		String sfunc = request.getParameter("FUNC");
		con = mycontrol.gConnectDB();
   		
		Connection conn = null;
		Class.forName(VariableControl.gDB_Driver); 			
		
		conn = mycontrol.gConnectDB();
		String sSql = "";
		String sql = "";
		String Sql ="";
		String Sql1 ="";
		Statement state = conn.createStatement();
		ResultSet rs = null;
		String ID = myStrControl.gNullToBlank(request.getParameter("ST_STATUTESUB_ID"));
		String LEGAL_TYPE_ID_ = "";
		String LEGAL_TYPE_ID = myStrControl.gNullToBlank(request.getParameter("LEGAL_TYPE_ID"));
		String [] TYPE_ID_LEGAL = LEGAL_TYPE_ID.split("/");
		LEGAL_TYPE_ID = TYPE_ID_LEGAL[0];
		
		//Enumeration<String> params = (Enumeration<String>) request.getParameterNames();
	--%>
	<body>
	<form name="form1">
			<%--
				
			if(request.getParameter("TYPE").equals("LAW"))
			{
				Statement state1 = conn.createStatement();
				sSql = "DELETE FROM TS_REL_STATUTE WHERE ST_STATUTE_ID = "+ID;
				ResultSet rs2 = state1.executeQuery(sSql);
				
				sSql = "DELETE FROM TS_STATUTESUB_LEGALITEM WHERE ST_STATUTESUB_ID = "+ID;
				rs2 = state1.executeQuery(sSql);
				sSql = "DELETE FROM ST_STATUTESUB WHERE ST_STATUTESUB_ID = "+ID;
				rs2 = state1.executeQuery(sSql); 
				out.println("<script language='Javascript' type='text/Javascript'>");
				out.println("alert('ลบข้อมูลเรียบร้อยแล้ว')");
				out.println("window.location='input.jsp';");
				out.println("</script>");
			}
			else if(request.getParameter("TYPE").equals("LAWOTHER"))
			{
				Statement state1 = conn.createStatement();
				sSql = "DELETE FROM TDLEGAL_OTHER_ATTACH WHERE LEGAL_OTHER_ID = "+ID;
				ResultSet rs2 = state1.executeQuery(sSql);
				
				//sSql = "DELETE FROM TDLEGAL_STATUTE WHERE LEGAL_ID = "+ID+" AND LEGAL_TYPE_ID = " +LEGAL_TYPE_ID;
				//rs2 = state1.executeQuery(sSql);
				
				sSql = "DELETE FROM THLEGAL_OTHER WHERE LEGAL_OTHER_ID = "+ID;
				rs2 = state1.executeQuery(sSql);
				out.println("<script language='Javascript' type='text/Javascript'>");
				out.println("alert('ลบข้อมูลเรียบร้อยแล้ว')");
				out.println("window.location='LawOther.jsp';");
				out.println("</script>");
			}
			else if(request.getParameter("TYPE").equals("NEWS"))
			{
				String NEWS_ID = myStrControl.gNullToBlank(request.getParameter("NEWS_ID"));
				Statement state1 = conn.createStatement();
				sSql = "DELETE FROM TDNEWS_ATTACH WHERE NEWS_ID = "+NEWS_ID;
				ResultSet rs2 = state1.executeQuery(sSql);
				
				//sSql = "DELETE FROM TDLEGAL_STATUTE WHERE LEGAL_ID = "+ID+" AND LEGAL_TYPE_ID = " +LEGAL_TYPE_ID;
				//rs2 = state1.executeQuery(sSql);
				
				sSql = "DELETE FROM THNEWS WHERE NEWS_ID = "+NEWS_ID;
				rs2 = state1.executeQuery(sSql);
				out.println("<script language='Javascript' type='text/Javascript'>");
				out.println("alert('ลบข้อมูลเรียบร้อยแล้ว')");
				out.println("window.location='NewsDetail.jsp';");
				out.println("</script>");
			}
			--%>
			
		</form>
	</body>
</html>
