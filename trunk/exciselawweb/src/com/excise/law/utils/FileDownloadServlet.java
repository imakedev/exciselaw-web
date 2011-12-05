package com.excise.law.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.excise.law.domain.TsDocAttach;
import com.excise.law.service.LawService;


public class FileDownloadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected static final int DEFAULT_BUFFER_SIZE = 1024 * 4;
    private static final String EXCEL_FILE = "xls";
    private static final String EXCELX_FILE = "xlsx";
    private static final String DOC_FILE = "docx";
    private static final String DOCX_FILE = "doc";
    private static final String PDF_FILE = "pdf";
    private static final String TXT_FILE = "txt";
    private static final String CSV_FILE = "csv";
    private static final String XML_FILE = "xml";
    public LawService exciseLawService;
    
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
		ServletContext servletContext = this.getServletContext();
    	WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
    	exciseLawService = (LawService)wac.getBean("exciseLawService");
        doProcess(request, response);
    }

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
		ServletContext servletContext = this.getServletContext();
    	WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
    	exciseLawService = (LawService)wac.getBean("exciseLawService");
        doProcess(request, response);
    }
    
    private void doProcess(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String filePath = (request.getParameter("filePath") != null)? (String)request.getParameter("filePath") : "c:\\";
        String fileName = (request.getParameter("fileName") != null)? (String)request.getParameter("fileName") : "book.xls";
        String attachId = (request.getParameter("attachId") != null)? (String)request.getParameter("attachId") : "c:\\";
        fileName = new String( fileName.getBytes( "TIS620" ), "ISO8859-1" );
        InputStream is = null;
        ByteArrayInputStream in = null;
        ServletOutputStream out = null;
        try {
        	TsDocAttach tsDocAttach;// = exciseLawService.findTsDocAttachByAttachId(Long.valueOf(attachId));
        	filePath = "";//tsDocAttach.getFilePath();
            fileName = "";//tsDocAttach.getFileName();
            File file = new File(filePath, fileName);
            is = new FileInputStream(file);
            long length = file.length();

            byte[] bytes = new byte[(int) length];
            // Read in the bytes
            int offset = 0;
            int numRead = 0;
            while ((offset < bytes.length) &&
                    ((numRead = is.read(bytes, offset, bytes.length - offset)) >= 0)) {
                offset += numRead;
            }

            byte[] buf = new byte[1024];

            in = new ByteArrayInputStream(bytes);
            out = response.getOutputStream();
            String header = "attachment; filename=\"" + fileName + "\"";
            header = new String( header.getBytes( "TIS620" ), "ISO8859-1" );
            response.setHeader( "Content-Disposition", header ); 
//            response.addHeader("Content-Disposition", "inline;attachment; filename=\"" + convertAsciiToSBMUnicode(file.getName()) + "\"");
//            response.addHeader("Content-Disposition", "inline;attachment; filename=\"" + fileName + "\"");
            response.setContentLength((int) length);           

            String fileType = file.getName().substring(file.getName().lastIndexOf(".") + 1);
            if (DOC_FILE.equals(fileType) || DOCX_FILE.equals(fileType)) {
            	response.setContentType("application/vnd.msword");
            } else if (EXCEL_FILE.equals(fileType) || EXCELX_FILE.equals(fileType)) {
            	response.setContentType("application/vnd.ms-excel");
            } else if (PDF_FILE.equals(fileType)) {
            	response.setContentType("application/pdf");
            } else if (CSV_FILE.equals(fileType)) {
            	response.setContentType("APPLICATION/OCTET-STREAM");  
            } else if (XML_FILE.equals(fileType)) {
            	response.setContentType("text/xml");  
            }  else if (TXT_FILE.equals(fileType)) {
            	response.setContentType("text/plain");
            }  
            while ((in != null) && ((length = in.read(buf)) != -1)) {
                out.write(buf, 0, (int) length);
            }
            out.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (is != null) {
                try { is.close(); } catch (IOException e) { e.printStackTrace(); }
            }
            if (in != null) {
                try { in.close(); } catch (IOException e) { e.printStackTrace(); }
            }
            if (out != null) {
                try { out.close(); } catch (IOException e) { e.printStackTrace(); }
            }
        }
    }
    
    public String convertSBMUnicodeToAscii(String strCharAll) {

    	if ( strCharAll == null || "".equals(strCharAll.trim()) ) {
    		return "";
    	}

    	StringBuffer strReturn = new StringBuffer();


    	String[] charAll = splitStringByPipe(strCharAll);

    	for (int i = 0 ; i < charAll.length ; i++ ) {
    	int hex = Integer.parseInt(charAll[i], 16 /* radix */);
    	char c = (char) hex;
    	strReturn.append(c);
    	}

    	return strReturn.toString();
    }
    
    public String convertAsciiToSBMUnicode(String str) {
    	StringBuffer ostr = new StringBuffer();

    	for(int i=0; i<str.length(); i++) {
	    	char ch = str.charAt(i);
	    	/*if ((ch >= 0x0020) && (ch <= 0x007e)) // Does the char need to be converted to unicode?
	    	{
	    	ostr.append(ch); // No.
	    	} else { */ // Yes.
	
	    	// ostr.append("\\u") ; // standard unicode format.
	    	ostr.append("|u") ; // SBM unicode format.
	    	String hex = Integer.toHexString(str.charAt(i) & 0xFFFF); // Get hex value of the char.
	    	for(int j=0; j<4-hex.length(); j++) // Prepend zeros because unicode requires 4 digits
	    	ostr.append("0");
	    	ostr.append(hex.toUpperCase()); // standard unicode format.
	    	//ostr.append(hex.toLowerCase(Locale.ENGLISH));
	    	// }
    	}
    	return (new String(ostr)); //Return the stringbuffer cast as a string.
    }
    
    public static String[] splitStringByPipe(String source){

    	if (source == null) {
    		return null;
    	}

    	StringTokenizer st = new StringTokenizer(source,"|");
    	String[] strArray = new String[st.countTokens()];
    	int count = 0;
    	while(st.hasMoreTokens()){
	    	strArray[count] = st.nextToken();
	    	count++;
    	}
    	return strArray;
    }


}
