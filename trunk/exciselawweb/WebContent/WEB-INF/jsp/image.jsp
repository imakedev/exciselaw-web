<%@ page import="java.io.*,java.util.*" %>  
<%@page import="com.exciselaw.law.domain.*"%> 
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="com.exciselaw.law.service.ExciseLawService"%>

<%
	ServletContext servletContext = getServletConfig().getServletContext();//this.getServletContext();
	WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
	ExciseLawService exciseLawService = (ExciseLawService)wac.getBean("exciseLawService");
	String newsId =  (String)request.getSession().getAttribute("newsId");
	exciseLawService.findTsNewsByNewsId(Long.valueOf(newsId));	
	if(newsId != null && !"".equals(newsId)){ 
	  	try{ 
			TsNews tsNews = exciseLawService.findTsNewsByNewsId(Long.valueOf(newsId));		
			byte[] image = tsNews.getNewsImage();
	        OutputStream sos = response.getOutputStream();
	        sos.write(image);
	        sos.flush();	
	        sos.close();	
		}catch (Exception e){
	        e.printStackTrace();
		}
	}
%> 
