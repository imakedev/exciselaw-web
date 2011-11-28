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
	<link rel="shortcut icon" href="../fckeditor.gif" type="image/x-icon" />	
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
	String sSql ="";
	Statement state = conn.createStatement();
	ResultSet rs = null;

--%>
	<body>
	<form name="form1">
		<table width="100%" border="0" cellspacing="0">
			<tr >
				<td nowrap="nowrap">&nbsp;&nbsp;</td>
				<td>&nbsp;&nbsp;</td>
			</tr>
	<%--
	
	System.out.println("System TYPE>>"+request.getParameter("TYPE"));
		if(request.getParameter("TYPE").equals("LAW")){
			String ST_STATUTESUB_ID = myStrControl.gNullToBlank(request.getParameter("ST_STATUTESUB_ID"));
			String MAIN_STATUTE_ID = myStrControl.gNullToBlank(request.getParameter("MAIN_STATUTE_ID"));
			String STATUTESUB_ID=myStrControl.gNullToBlank(request.getParameter("STATUTESUB_ID"));
			//String CHILD_STATUTE_ID = myStrControl.gNullToBlank(request.getParameter("CHILD_STATUTE_ID"));
			String [] CHILD_STATUTE = request.getParameterValues("CHILD_STATUTE_ID");
			String ST_ORDER=myStrControl.gNullToBlank(request.getParameter("ST_ORDER"));
			String STATUS = myStrControl.gNullToBlank(request.getParameter("STATUS"));
			String DISPLAY = myStrControl.gNullToBlank(request.getParameter("DISPLAY"));
			String ANNOUNCE_DATE = myStrControl.gNullToBlank(request.getParameter("ANNOUNCE_DATE"));
			//if (ANNOUNCE_DATE!=""){ANNOUNCE_DATE=myStrControl.gConvertDate(ANNOUNCE_DATE);}
			String ANNOUNCE_BOOK = myStrControl.gNullToBlank(request.getParameter("ANNOUNCE_BOOK"));
			String ANNOUNCE_PART = myStrControl.gNullToBlank(request.getParameter("ANNOUNCE_PART"));
			String ANNOUNCE_NO = myStrControl.gNullToBlank(request.getParameter("ANNOUNCE_NO"));
			String ARTICLE = myStrControl.gNullToBlank(request.getParameter("ARTICLE"));
			String BOOK_NO = myStrControl.gNullToBlank(request.getParameter("BOOK_NO"));
			String BOOK_YEAR = myStrControl.gNullToBlank(request.getParameter("BOOK_YEAR"));
			String ARTICLE_ENG = myStrControl.gNullToBlank(request.getParameter("ARTICLE_ENG"));
			String HIGHTLIGHT = myStrControl.gNullToBlank(request.getParameter("HIGHTLIGHT"));
			String KEYWORD = myStrControl.gNullToBlank(request.getParameter("KEYWORD"));
			String INTRO = myStrControl.gNullToBlank(request.getParameter("INTRO"));
			String DETAIL = myStrControl.gNullToBlank(request.getParameter("DETAIL"));
			String REMARK = myStrControl.gNullToBlank(request.getParameter("REMARK"));
			String TABLE_ATTACH = myStrControl.gNullToBlank(request.getParameter("TABLE_ATTACH"));
			String USER_ID = myStrControl.gNullToBlank(request.getParameter("USER_ID"));
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");			
			String UPDATE_TIME = dateFormat.format(new Date());
			String [] LEGAL_ITEM_ID = request.getParameterValues("LEGAL_ITEM_ID");
			
							    
			/*	
				Statement stmt = conn.createStatement();
				Sql = " SELECT LEGAL_ID FROM THLEGAL WHERE LEGAL_ID ='"+LEGAL_ID+"' AND LEGAL_TYPE_ID='"+LEGAL_TYPE_ID+"'";
				rs = stmt.executeQuery(Sql);
				if(rs.next())
				{	}
				else*/
				
				//Save Header
				if(ST_STATUTESUB_ID.equals("")){
					ST_STATUTESUB_ID = mycontrol.gGetmax("ST_STATUTESUB","ST_STATUTESUB_ID","").toString();
					sSql = "INSERT INTO ST_STATUTESUB(ST_STATUTESUB_ID,STATUTE_ID,STATUTESUB_ID) values('"+ST_STATUTESUB_ID+"','"+MAIN_STATUTE_ID+"','"+STATUTESUB_ID+"')";
					System.out.println("INSERT 1>>"+sSql);
					mycontrol.gExecuteQuery(sSql);
				}
				/*
				//Save RichText Box
				PreparedStatement pstmt = null;

				String query = "UPDATE THLEGAL SET LEGAL_HEADER_TH = ?,LEGAL_HEADER_EN1 = ?,LEGAL_ARTICLE = ?, LEGAL_DETAIL = ?, LEGAL_TABLE = ?  WHERE LEGAL_ID =? AND LEGAL_TYPE_ID=? ";
			    pstmt = conn.prepareStatement(query); 
			    pstmt.setString(1, request.getParameter("LEGAL_HEADER_TH")); 
			    pstmt.setString(2, request.getParameter("LEGAL_HEADER_EN")); 
			    pstmt.setString(3, request.getParameter("LEGAL_ARTICLE"));
			    pstmt.setString(4, request.getParameter("LEGAL_DETAIL"));
			    pstmt.setString(5, request.getParameter("LEGAL_TABLE"));
			    pstmt.setString(6, LEGAL_ID);
			    pstmt.setString(7, LEGAL_TYPE_ID);
			    pstmt.executeUpdate(); 
*/
			    //Update Data
				String sDateNow=myDateControl.gGetDateNow("yyyymmdd");
			    
				sSql = "UPDATE ST_STATUTESUB SET ";
				sSql = sSql + " STATUTE_ID = '"+MAIN_STATUTE_ID+"',";
				sSql = sSql + " STATUTESUB_ID = '"+STATUTESUB_ID+"',";
				sSql = sSql + " ST_ORDER = '" + ST_ORDER +"',";
				sSql = sSql + " STATUS = '" + STATUS +"',";
				sSql = sSql + " DISPLAY = '"+DISPLAY+"',";
				sSql = sSql + " ANNOUNCE_DATE =TO_DATE('"+ANNOUNCE_DATE+"','DD/MM/YYYY') ,";
				sSql = sSql + " ANNOUNCE_BOOK = '"+ANNOUNCE_BOOK+"',";
				sSql = sSql + " ANNOUNCE_PART = '"+ANNOUNCE_PART+"',";
				sSql = sSql + " ANNOUNCE_NO = '"+ANNOUNCE_NO+"',";
				sSql = sSql + " ARTICLE = '"+ARTICLE+"',";
				sSql = sSql + " BOOK_NO = '"+BOOK_NO+"',";
				sSql = sSql + " BOOK_YEAR = '"+BOOK_YEAR+"',";
				sSql = sSql + " ARTICLE_ENG = '"+ARTICLE_ENG+"',";
				sSql = sSql + " HIGHTLIGHT = '"+HIGHTLIGHT+"',";
				sSql = sSql + " KEYWORD = '"+KEYWORD+"',";
				sSql = sSql + " INTRO = '"+INTRO+"',";
				sSql = sSql + " DETAIL = '"+DETAIL+"',";
				sSql = sSql + " REMARK = '"+REMARK+"',";
				sSql = sSql + " TABLE_ATTACH = '"+TABLE_ATTACH+"',";
				sSql = sSql + " UPDATE_TIME = TO_DATE('"+UPDATE_TIME+"','YYYY/MM/DD  HH24:MI:SS'),";
				sSql = sSql + " UPDATE_USER = "+session.getAttribute("ID");
				sSql = sSql + " WHERE ST_STATUTESUB_ID = " + ST_STATUTESUB_ID; 
				System.out.println("INSERT 4>>"+sSql);
				mycontrol.gExecuteQuery(sSql);
				
				//System.out.println(sSql);
				
				//Save Item		
				sSql = " DELETE FROM TS_STATUTESUB_LEGALITEM ";
				sSql = sSql + " WHERE ST_STATUTESUB_ID = "+ST_STATUTESUB_ID; 
				mycontrol.gExecuteQuery(sSql);
				if (LEGAL_ITEM_ID != null) {//ครบถ้วน
					for (int i = 0; i < LEGAL_ITEM_ID.length; i++){
						sSql=" INSERT INTO TS_STATUTESUB_LEGALITEM (LEGAL_REL_ID,ST_STATUTESUB_ID,LEGALITEM_ID)";
						sSql=sSql + " values((select decode( max(LEGAL_REL_ID),null,1,max(LEGAL_REL_ID)+1)as id_v from TS_STATUTESUB_LEGALITEM),'"+ST_STATUTESUB_ID+"','"+LEGAL_ITEM_ID[i]+"')";	
						System.out.println("INSERT 2>>"+sSql);
						mycontrol.gExecuteQuery(sSql);
					}
				}
				
				//Save Item
			    sSql = " DELETE FROM TS_REL_STATUTE ";
			    sSql = sSql + " WHERE ST_STATUTE_ID = "+ST_STATUTESUB_ID; 
				mycontrol.gExecuteQuery(sSql);
				if (CHILD_STATUTE != null) {//ครบถ้วน
					for (int i = 0; i < CHILD_STATUTE.length; i++){
						sSql=" INSERT INTO TS_REL_STATUTE(ST_STATUTE_ID,STATUTE_ID) ";
						 sSql=sSql + " values('"+ST_STATUTESUB_ID+"','"+CHILD_STATUTE[i]+"')";	
						 System.out.println("INSERT 3>>"+sSql);
						 mycontrol.gExecuteQuery(sSql);
					}
				}
		
				out.println("<script language='Javascript' type='text/Javascript'>");
				out.println("alert('บันทึกข้อมูลเรียบร้อยแล้ว')");
				out.println("window.location='input.jsp';");
				out.println("</script>");
		}
		else if(request.getParameter("TYPE").equals("LAWOTHER"))
		{
			String LEGAL_OTHER_ID = myStrControl.gNullToBlank(request.getParameter("LEGAL_OTHER_ID"));
			//String LEGAL_OTHER_TYPE = myStrControl.gNullToBlank(request.getParameter("LEGAL_OTHER_TYPE"));
			String LEGAL_OTHER_DISPLAY = myStrControl.gNullToBlank(request.getParameter("LEGAL_OTHER_DISPLAY"));
			String LEGAL_OTHER_STATUS = myStrControl.gNullToBlank(request.getParameter("LEGAL_OTHER_STATUS"));
			String LEGAL_OTHER_NAME = myStrControl.gNullToBlank(request.getParameter("LEGAL_OTHER_NAME"));
			String LEGAL_OTHER_KEYWORD = myStrControl.gNullToBlank(request.getParameter("LEGAL_OTHER_KEYWORD"));
			String LEGAL_OTHER_HIGHTLIGHT = myStrControl.gNullToBlank(request.getParameter("LEGAL_OTHER_HIGHTLIGHT"));
			String LEGAL_OTHER_PUBLIC_DATE = myStrControl.gNullToBlank(request.getParameter("LEGAL_OTHER_PUBLIC_DATE"));
			if (LEGAL_OTHER_PUBLIC_DATE!=""){LEGAL_OTHER_PUBLIC_DATE=myStrControl.gConvertDate(LEGAL_OTHER_PUBLIC_DATE);}
			String LEGAL_OTHER_SIGN_DATE = myStrControl.gNullToBlank(request.getParameter("LEGAL_OTHER_SIGN_DATE"));
			if (LEGAL_OTHER_SIGN_DATE!=""){LEGAL_OTHER_SIGN_DATE=myStrControl.gConvertDate(LEGAL_OTHER_SIGN_DATE);}
			String LEGAL_OTHER_DETAIL = myStrControl.gNullToBlank(request.getParameter("LEGAL_OTHER_DETAIL"));
			String LEGAL_OTHER_SIGN = myStrControl.gNullToBlank(request.getParameter("LEGAL_OTHER_SIGN"));
			String LEGAL_OTHER_POSITION = myStrControl.gNullToBlank(request.getParameter("LEGAL_OTHER_POSITION"));
			
			if(LEGAL_OTHER_ID.equals("")){
				LEGAL_OTHER_ID = mycontrol.gGetmax("THLEGAL_OTHER","LEGAL_OTHER_ID","").toString();	
				sSql = "INSERT INTO THLEGAL_OTHER(LEGAL_OTHER_ID) values('"+LEGAL_OTHER_ID+"')";
				mycontrol.gExecuteQuery(sSql);
				
			}
			
			PreparedStatement pstmt = null;

			String query = "UPDATE THLEGAL_OTHER SET LEGAL_OTHER_NAME = ?,LEGAL_OTHER_DETAIL = ? WHERE LEGAL_OTHER_ID =? ";
		    pstmt = conn.prepareStatement(query); 
		    pstmt.setString(1, request.getParameter("LEGAL_OTHER_NAME")); 
		    pstmt.setString(2, request.getParameter("LEGAL_OTHER_DETAIL")); 
		    pstmt.setString(3, LEGAL_OTHER_ID);
		    pstmt.executeUpdate(); 
			
		    sSql = " UPDATE THLEGAL_OTHER SET ";
		    sSql = sSql + " LEGAL_OTHER_DISPLAY ='"+LEGAL_OTHER_DISPLAY+"',";
		    sSql = sSql + " LEGAL_OTHER_STATUS ='"+LEGAL_OTHER_STATUS+"',";
		    sSql = sSql + " LEGAL_OTHER_KEYWORD ='"+LEGAL_OTHER_KEYWORD+"',";
		    sSql = sSql + " LEGAL_OTHER_HIGHTLIGHT ='"+LEGAL_OTHER_HIGHTLIGHT+"',";
		    sSql = sSql + " LEGAL_OTHER_PUBLIC_DATE ='" + LEGAL_OTHER_PUBLIC_DATE +"',";
		    sSql = sSql + " LEGAL_OTHER_SIGN_DATE ='" + LEGAL_OTHER_SIGN_DATE +"',";
		    sSql = sSql + " LEGAL_OTHER_SIGN ='"+LEGAL_OTHER_SIGN+"',";
		    sSql = sSql + " LEGAL_OTHER_POSITION ='"+LEGAL_OTHER_POSITION+"'";
			sSql = sSql + " WHERE LEGAL_OTHER_ID = '" + LEGAL_OTHER_ID + "'";
			mycontrol.gExecuteQuery(sSql);
		
			
			out.println("<script language='Javascript' type='text/Javascript'>");
			out.println("alert('บันทึกข้อมูลเรียบร้อยแล้ว')");
			out.println("window.location = 'LawOther.jsp?sWhere="+LEGAL_OTHER_ID+"','wa';");
			out.println("</script>");
			
		}
		else if(request.getParameter("TYPE").equals("NEWS"))
		{
			String NEWS_ID = myStrControl.gNullToBlank(request.getParameter("NEWS_ID"));
			String NEWS_STATUS = myStrControl.gNullToBlank(request.getParameter("NEWS_STATUS"));
			String NEWS_HEADER = myStrControl.gNullToBlank(request.getParameter("NEWS_HEADER"));
			String NEWS_HEADER_PICTURE = myStrControl.gNullToBlank(request.getParameter("NEWS_HEADER_PICTURE"));
			String NEWS_DATE = myStrControl.gNullToBlank(request.getParameter("NEWS_DATE"));
			if (NEWS_DATE!=""){NEWS_DATE=myStrControl.gConvertDate(NEWS_DATE);}
			String NEWS_START_DATE = myStrControl.gNullToBlank(request.getParameter("NEWS_START_DATE"));
			if (NEWS_START_DATE!=""){NEWS_START_DATE=myStrControl.gConvertDate(NEWS_START_DATE);}
			String NEWS_END_DATE = myStrControl.gNullToBlank(request.getParameter("NEWS_END_DATE"));
			if (NEWS_END_DATE!=""){NEWS_END_DATE=myStrControl.gConvertDate(NEWS_END_DATE);}
			String NEWS_DETAIL = myStrControl.gNullToBlank(request.getParameter("NEWS_DETAIL"));
			String USER_ID = myStrControl.gNullToBlank(request.getParameter("USER_ID"));
			String UP_DATE = myDateControl.gGetDateNow("yyyymmdd");
			
			if(NEWS_ID.equals("")){
				NEWS_ID = mycontrol.gGetmax("THNEWS","NEWS_ID","").toString();	
				sSql = "INSERT INTO THNEWS(NEWS_ID,CREATE_DATE) values('"+NEWS_ID+"','"+UP_DATE+"')";
				mycontrol.gExecuteQuery(sSql);
			}
			
			PreparedStatement pstmt = null;
			
			String query = "UPDATE THNEWS SET NEWS_DETAIL = ? WHERE NEWS_ID =? ";
		    pstmt = conn.prepareStatement(query); 
		    pstmt.setString(1, request.getParameter("NEWS_DETAIL")); 
		    pstmt.setString(2, NEWS_ID);
		    pstmt.executeUpdate(); 

		    sSql = " UPDATE THNEWS SET ";
		    sSql = sSql + " NEWS_STATUS ='"+NEWS_STATUS+"',";
		    sSql = sSql + " NEWS_HEADER ='"+NEWS_HEADER+"',";
		    sSql = sSql + " NEWS_DATE ='"+NEWS_DATE+"',";
		    sSql = sSql + " NEWS_START_DATE ='"+NEWS_START_DATE+"',";
		    sSql = sSql + " NEWS_END_DATE ='"+NEWS_END_DATE+"',";
		    sSql = sSql + " NEWS_HEADER_PICTURE ='"+NEWS_HEADER_PICTURE+"',";
		    sSql = sSql + " USER_ID ='" + USER_ID +"',";
		    sSql = sSql + " UP_DATE ='" + UP_DATE +"'";
			sSql = sSql + " WHERE NEWS_ID = '" + NEWS_ID + "'";
			mycontrol.gExecuteQuery(sSql);
	
			out.println("<script language='Javascript' type='text/Javascript'>");
			out.println("alert('บันทึกข้อมูลเรียบร้อยแล้ว')");  
			out.println("window.location = 'NewsDetail.jsp?sWhere="+NEWS_ID+"','wa';");
			out.println("</script>");
			
		}
			--%>
			<tr>
				<td nowrap="nowrap"><b></b></td>
				<td width="100%"></td>
			</tr>

		</table>
		
		</form>
	</body>
</html>
