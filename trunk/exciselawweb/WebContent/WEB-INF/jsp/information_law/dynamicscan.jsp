<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>  
<%-- 
	String sSql="?";
	int sValue=0;
	Connection conn = null;	
	Connection connIN = null;	
	DataBaseControl myDBControl=new DataBaseControl();
	VariableControl myVarControl =new VariableControl();
	StringControl myStrControl=new StringControl();
	DateControl myDateControl=new DateControl();
	ObjectControl myObjControl=new ObjectControl();
	FileUpload myFtpControl =new FileUpload();
	UtilityControl myUtilControl =new UtilityControl();

	request.setCharacterEncoding("TIS-620");
	response.setCharacterEncoding("TIS-620");
	String FILE_DATE_ATTACH=myDateControl.gGetDateNow("yyyymmdd");
	String FILE_TIME_ATTACH=myDateControl.gGetDateNow("hh:mm");	
	
	String no=request.getParameter("no");
	
	String sFileName=request.getParameter("FileName");
	
	String PAGE_SCAN=request.getParameter("PAGE_SCAN");
	String TYPE_SCAN=request.getParameter("TYPE_SCAN");
				
	--%>
 <HTML>
<HEAD>
<link href="css/scan.css" rel="stylesheet" type="text/css">
	<OBJECT CLASSID = "clsid:5220cb21-c88d-11cf-b347-00aa00a28331" VIEWASTEXT>
		<PARAM NAME="LPKPath" VALUE="DynamicWebTwain.lpk">
	</OBJECT>
<TITLE></TITLE>
</center>
<SCRIPT language="javascript">
var iLeft;
var iTop;
var iRight;
var iBottom;
var ViewMode = 1;
function pageonload() {
	iLeft = 0;
    iTop = 0;
    iRight = 0;
    iBottom = 0;
		            
    frmScanView.DynamicWebTwain1.MouseShape = true;
    frmScanView.DynamicWebTwain1.MaxImagesInBuffer = 4;
    frmScanView.DynamicWebTwain1.SetViewMode(1,3);
    frmScanView.DynamicWebTwain2.MaxImagesInBuffer = 1;
    frmScanView.DynamicWebTwain2.IfFitWindow = true;
    frmScanView.DynamicWebTwain2.MouseShape = false;
    frmScanView.DynamicWebTwain2.SetViewMode(-1,-1);
    UpdateImage(false);
}

function btnUploadAll_onclick(aType){
	SaveAllImage(aType);
}

function btnScan_onclick(){
	frmScanView.DynamicWebTwain1.MaxImagesInBuffer = 10;
	frmScanView.DynamicWebTwain1.IfFeederEnabled = true; 
	frmScanView.DynamicWebTwain1.XferCount = -1;
	frmScanView.DynamicWebTwain1.IfAutoFeed = true;
	frmScanView.DynamicWebTwain1.AcquireImage();
}

function btnUpload_onclick(aType){
	SaveAllImage(aType);
	//SaveImage(aType);
}

function SaveAllImage(aType){
	var strActionPage;
	var strHostIP;
	var sFileName="";
	var sno="";//รหัสรายการ
	var stype="";//ประเภท 1=กฏหมาย,2=คำพิพากษา,3=ข่าว,4=ข้อหารือ
	var sFileServerName="";
	var sFolder="";
	var CurrentPathName = unescape(location.pathname);	// get current PathName in plain ASCII	
	var CurrentPath = CurrentPathName.substring(0, CurrentPathName.lastIndexOf("/") + 1);			
	var sSysName="";
	var sTotalPage="";
	var sLastScan="";
	var iLastScan=0;
	var sItemNo="";
	var iLoop=0;
	var iScanQty=0;
	var iStart=0;
	var iStop=0;
	
	sno=frmScanView.txtno.value;
	stype=frmScanView.txttype.value;
	sFolder=frmScanView.txtfolder.value;

	sPath="/lawapp/"+sFolder+"/";
	sTotalPage=frmScanView.txtScanQty.value;
	iScanQty=parseInt(sTotalPage);
	sLastScan=frmScanView.txtlastno.value;
	iLastScan=parseInt(sLastScan);
	
	if (iLastScan == 0 ){
		iStart=1;
	}
	else
	{
		iStart=iLastScan+1;
	}
	
	iStop=iLastScan+iScanQty;
		
	if (aType=='all'){
		//for(i=0;i<iScanQty;i++){
		for(i=iStart;i<=iStop;i++){
			//sItemNo=i+1;
			frmScanView.DynamicWebTwain1.CurrentImageIndexInBuffer = i;
			UpdateImage(true);
			
			//sFileName=stype+"_"+sno+"_"+sItemNo+ ".TIF";
			sFileName=stype+"_"+sno+"_"+i+ ".TIF";
			sFileServerName=sPath+sFileName;
			sSysName="law";
			frmScanView.DynamicWebTwain2.FTPUserName = "root";
			frmScanView.DynamicWebTwain2.FTPPassword = "root";
			frmScanView.DynamicWebTwain2.FTPUpload("192.168.3.73", 0,sFileServerName);
		}

		if (iScanQty > 0){
			document.location.href = "dynamicscan_trans.jsp?sys="+stype+"&no="+sno+"&scqty="+iScanQty;
		}
	}else{
		//sItemNo=parseInt(sTotalPage)+1;
		//sFileName=stype+"_"+sno+"_"+sItemNo+ ".TIF";
		sFileName=stype+"_"+sno+"_"+iStart+ ".TIF";
		sFileServerName=sPath+sFileName;
		iScanQty=1;
				
		frmScanView.DynamicWebTwain2.FTPUserName = "root";
		frmScanView.DynamicWebTwain2.FTPPassword = "root";
		frmScanView.DynamicWebTwain2.FTPUpload("192.168.3.73", 0,sFileServerName);
		
		document.location.href = "dynamicscan_trans.jsp?sys="+stype+"&no="+sno+"&scqty="+iScanQty;
	}
}

function SaveImage_(){
	var strActionPage;
	var strHostIP;
	var sFileName="";
	var sLiqNo="";
	var sLiqDocAttachNo="";
	var sFileServerName="";
	var sFolder="";
	var sSubFolder="";
	var CurrentPathName = unescape(location.pathname);	// get current PathName in plain ASCII	
	var CurrentPath = CurrentPathName.substring(0, CurrentPathName.lastIndexOf("/") + 1);			
	var sSysName="";
	var sTotalPage="";
	var sItemNo="";
	var iLiqDocAttachNo=0;
	
	sTotalPage=frmScanView.textitemno.value;
	sPageNo=frmScanView.txtPageNo.value;
			
	sItemNo=parseInt(sTotalPage)+1;
	sLiqNo=frmScanView.LIQ_NO.value;
	sPageNo=frmScanView.txtPageNo.value;
	sFolder=frmScanView.txtfolder.value;
	sSubFolder=frmScanView.txtsubfolder.value;
	sPath="/lawapp/"+sFolder+"/"+sSubFolder+"/";
	sFileName=sLiqDocAttachNo+"_"+sLiqNo+"_"+sItemNo+ ".TIF";
	sFileServerName=sPath+sFileName;

	sSysName="label";
			
	frmScanView.DynamicWebTwain1.FTPUserName = "root";
	frmScanView.DynamicWebTwain1.FTPPassword = "root";
	frmScanView.DynamicWebTwain1.FTPUpload("192.168.3.73", 0,sFileServerName);
	
	if (frmScanView.DynamicWebTwain1.ErrorCode != 0) {		//Failed to upload image
		alert(frmScanView.DynamicWebTwain1.ErrorString);
	}else{
		document.location.href = "dynamicscan_trans.jsp?fn="+sFileName+"&lqno="+sLiqNo +"&lqatno="+sLiqDocAttachNo+"&sys="+sSysName+"&scqty=1";
	}
}
		
function DynamicWebTwain1_OnPostTransfer() {
	//frmScanView.DynamicWebTwain1.CloseSource();
	frmScanView.txtScanQty.value = frmScanView.DynamicWebTwain1.HowManyImagesInBuffer;
	frmScanView.txtcurrentimage.value = frmScanView.DynamicWebTwain1.CurrentImageIndexInBuffer+1;
	UpdateImage(true);
	//SaveImage();
}

function DynamicWebTwain1_OnPostAllTransfers() {
	//frmScanView.DynamicWebTwain1.CloseSource();

	//frmScanView.txtScanQty.value = frmScanView.DynamicWebTwain1.HowManyImagesInBuffer;
	//frmScanView.txtScanQty.value = frmScanView.DynamicWebTwain1.CurrentImageIndexInBuffer+1;
	//SaveImage();
}

function UpdateImage(flag){
	frmScanView.txtScanQty.value = frmScanView.DynamicWebTwain1.HowManyImagesInBuffer;
	frmScanView.txtcurrentimage.value = frmScanView.DynamicWebTwain1.CurrentImageIndexInBuffer+1;
    if(frmScanView.DynamicWebTwain1.HowManyImagesInBuffer == 0){
		frmScanView.DynamicWebTwain2.RemoveAllImages();
		return;
	}
			
    if(flag == true){
  		frmScanView.DynamicWebTwain1.CopyToClipboard(frmScanView.DynamicWebTwain1.CurrentImageIndexInBuffer);
   		frmScanView.DynamicWebTwain2.RemoveAllImages();
   		frmScanView.DynamicWebTwain2.LoadDibFromClipboard();
    }else{
      	frmScanView.DynamicWebTwain1.CopyToClipboard(frmScanView.DynamicWebTwain1.CurrentImageIndexInBuffer);
       	frmScanView.DynamicWebTwain2.RemoveAllImages();
       	frmScanView.DynamicWebTwain2.LoadDibFromClipboard();
    }
}

function btnRemoveAllImages_onclick() {
	//CheckIfImagesInBuffer();
	frmScanView.txtPageNo.value = "0";
	frmScanView.txtScanQty.value = "";
	frmScanView.DynamicWebTwain1.RemoveAllImages();
	frmScanView.DynamicWebTwain2.RemoveAllImages();
	UpdateImage(true);
}

function btnRemoveCurrentImage_onclick() {
	//CheckIfImagesInBuffer();
	frmScanView.DynamicWebTwain1.RemoveImage(frmScanView.DynamicWebTwain1.CurrentImageIndexInBuffer);
	if (frmScanView.DynamicWebTwain1.HowManyImagesInBuffer == 0){
		frmScanView.txtScanQty.value = frmScanView.DynamicWebTwain1.HowManyImagesInBuffer;
		frmScanView.txtcurrentimage.value = "";
		frmScanView.DynamicWebTwain2.RemoveAllImages();
    	UpdateImage(true);
    	return;
	}
	else{
		frmScanView.txtScanQty.value = frmScanView.DynamicWebTwain1.HowManyImagesInBuffer;
		frmScanView.txtcurrentimage.value = frmScanView.DynamicWebTwain1.CurrentImageIndexInBuffer+1;
	}
	UpdateImage(true);
}

function DynamicWebTwain1_OnMouseClick(index) {
	frmScanView.txtcurrentimage.value = index+1;
	UpdateImage(true);
}

function DynamicWebTwain2_OnImageAreaSelected(index,left,top,right,bottom) {
	iLeft = left;
	iTop = top;
	iRight = right;
	iBottom = bottom;
}

function btnCrop_onclick() {
	if (iLeft == 0 && iRight == 0 && iTop == 0 && iBottom == 0){

	}else{
		frmScanView.DynamicWebTwain1.Crop(frmScanView.DynamicWebTwain1.CurrentImageIndexInBuffer, iLeft, iTop, iRight, iBottom);
		iLeft = 0;
		iTop = 0;
		iRight = 0;
		iBottom = 0;
	}
	UpdateImage(true);
}

function btnRotateRight_onclick() {
	//CheckIfImagesInBuffer();
	frmScanView.DynamicWebTwain1.RotateRight(frmScanView.DynamicWebTwain1.CurrentImageIndexInBuffer);
	frmScanView.DynamicWebTwain2.RotateRight(frmScanView.DynamicWebTwain2.CurrentImageIndexInBuffer);
}

function btnRotateLeft_onclick() {
	//CheckIfImagesInBuffer();
	frmScanView.DynamicWebTwain1.RotateLeft(frmScanView.DynamicWebTwain1.CurrentImageIndexInBuffer);
	frmScanView.DynamicWebTwain2.RotateLeft(frmScanView.DynamicWebTwain2.CurrentImageIndexInBuffer);
}

function btnZoomIn_onclick() {
	//CheckIfImagesInBuffer();
	
	if (ViewMode == 1) {
		frmScanView.DynamicWebTwain2.SetViewMode(-1,-1);
		ViewMode = 0;
	}

	frmScanView.DynamicWebTwain2.Zoom = frmScanView.DynamicWebTwain2.Zoom*1.1;
	//alert(frmScan.DynamicWebTwain2.Zoom);
}

function btnZoomOut_onclick() {
	//CheckIfImagesInBuffer();
	if (ViewMode == 1) {
		frmScanView.DynamicWebTwain2.SetViewMode(-1,-1);
		ViewMode = 0;
	}
	frmScanView.DynamicWebTwain2.Zoom = frmScanView.DynamicWebTwain2.Zoom*0.9;
	//alert(frmScan.DynamicWebTwain2.Zoom);
}

function btnFit_onclick(){
	frmScanView.DynamicWebTwain2.IfFitWindow = true;
}

</SCRIPT>
		<script language=javascript for=DynamicWebTwain1 event=OnPostTransfer>
		<!--
			DynamicWebTwain1_OnPostTransfer();
		//-->
		</script>
		<script language=javascript for=DynamicWebTwain1 event=OnPostAllTransfers>
		<!--
		 	DynamicWebTwain1_OnPostAllTransfers();
		//-->
		</script>
	    <script language="javascript" type="text/javascript" event="OnMouseClick(index)" for="DynamicWebTwain1">
		<!--
			DynamicWebTwain1_OnMouseClick(index);
			//-->
	    </script>
	         <script language="javascript" type="text/javascript" event="OnImageAreaSelected(index,left,top,right,bottom)" for="DynamicWebTwain2">
		<!--
			DynamicWebTwain2_OnImageAreaSelected(index,left,top,right,bottom);
		//-->
    </script>
	    

</HEAD>
<p>
<form name="frmScanView" method="post" action="" id="form1">
    <BR>
	<input type='hidden' name='txtno' class='textinput' value="<%--=request.getParameter("no") --%>" >
	<input type='hidden' name='txttype' class='textinput' value="<%--=request.getParameter("type") --%>" >
	<input type='hidden' name='txtImagePath' class='textinput' value="<%--=myVarControl.gImagePath --%>" >
	<input type='hidden' name='txtPageNo' class='textinput' value="" >
	<input type='hidden' name='txtTotalPage' class='textinput' value="" >
	<input type='hidden' name='txtCond' class='textinput' value="<? echo $prmCond ?>" >
	
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;จำนวนภาพ&nbsp;<input type='text' name='txtScanQty' class="input_std"  style="WIDTH: 30px"  value="" readonly>&nbsp;ภาพ
	
	<input type='hidden' name='txtName' class='textinput' value="">
	<input type='hidden' name='txtMaxImage' class='textinput' value="">
	<input type='hidden' name='txtFileName' class="input_std"  style="WIDTH: 30px"  value="" readonly>
	<select name='SCAN' class='dropdown' style='width:180'>
	<option SELECTED value='H'>High Resolution
	<option value='M'>Medium Resolution
	<option value='L'>Low Resolution
	</select>
	<BR>
	  <INPUT TYPE="radio" NAME="radios" VALUE="radio1" CHECKED>
            สแกนเอกสารใหม่
            <BR>
            <INPUT TYPE="radio" NAME="radios" VALUE="radio2">
             สแกนแก้ไข
            <BR>
            <INPUT TYPE="radio" NAME="radios" VALUE="radio3">
	 สแกนต่อท้าย
            <BR>
			 <INPUT TYPE="radio" NAME="radios" VALUE="radio4">
            สแกนแทรกเอกสาร    
        
<%--
	Class.forName(VariableControl.gDB_Driver); 		
    Connection conn2 = null;
    StringControl  myStr = new StringControl();
    DataBaseControl myDB = new DataBaseControl();
    DateControl myDate=new DateControl();
    ObjectControl myObj=new ObjectControl();
	conn= myDB.gConnectDB();
        	
	conn2= myDBControl.gConnectDB();		
	Statement stmt = conn2.createStatement();
	String sFolder="";
	sFolder=myDBControl.gGetDesc("PATH_NO","PATH_NAME","SYSPATH",request.getParameter("pathno"),false,"");					
	out.println("<input type='hidden' name='txtfolder'  value="+ sFolder +" >");
	
	//หาลำดับที่ล่าสุดของภาพที่สแกน
	
	int iImageCout=0;
	String sno=request.getParameter("no");
	String stype=request.getParameter("type");
	Statement stmt2 = conn.createStatement();
	conn2= myDBControl.gConnectDB();		
	
	if (stype.equals("l1")){
		sSql = "SELECT COUNT(*) AS iCount ";
		sSql = sSql + " FROM TDLEGAL_ATTACH ";
		sSql = sSql + " WHERE FILE_TYPE = 'I'";
		sSql = sSql + " AND LEGAL_ID ="+sno;
		sSql = sSql + " GROUP BY LEGAL_ID";
	}else{
		sSql = "SELECT COUNT(*) AS iCount ";
		sSql = sSql + " FROM TDCOURT_ATTACH ";
		sSql = sSql + " WHERE FILE_TYPE = 'I'";
		sSql = sSql + " AND COURT_ID ="+sno;
		sSql = sSql + " GROUP BY COURT_ID";		
	}
	ResultSet rs1 = stmt2.executeQuery(sSql);
	while(rs1.next()) {
		iImageCout=rs1.getInt("iCount");	
	}
	out.println("<input type='hidden' name='txtlastno'  value="+ iImageCout +" >");
	
--%>
        
        <%--
        /*
								try	
								{	
																
										String sDesc ="";
										String sDELETE="";
										
										String sBG="#FFFFFF";
										String sImg="";
										String sFiletmp="";
										int iCount=1;
										String sDateNow=myDateControl.gGetDateNow("yyyymmdd");
										String sYearMont=sDateNow.substring(0,6);
										Class.forName(VariableControl.gDB_Driver); 			
										conn= myDBControl.gConnectDB();		
										Statement stmt = conn.createStatement();
										String sFolder="";
										String sSubFolder="";
										sFolder=myDBControl.gGetDesc("LIQ_DOC_ATTACH_NO","LIQ_DOC_PATH","MTLIQDOCATTACH",request.getParameter("LIQ_DOC_ATTACH_NO"),false,"");					
										out.println("<input type='hidden' name='txtfolder'  value="+ sFolder +" >");
										out.println("<input type='hidden' name='txtsubfolder'  value="+ sYearMont +" >");
										
										
										//รูปที่สแกน
										sSql= " SELECT COUNT(TDLABEL_LIQ_ATTACH_IMAGE.LIQ_NO) AS ICOUNT,MTLIQDOCATTACH.LIQ_DOC_ATTACH_DESC,MTLIQDOCATTACH.LIQ_DOC_ATTACH_NO";
										sSql=sSql + " FROM TDLABEL_LIQ_ATTACH_IMAGE";
										sSql=sSql + " LEFT JOIN MTLIQDOCATTACH ON TDLABEL_LIQ_ATTACH_IMAGE.LIQ_DOC_ATTACH_NO=MTLIQDOCATTACH.LIQ_DOC_ATTACH_NO";
										sSql=sSql + " WHERE TDLABEL_LIQ_ATTACH_IMAGE.LIQ_NO ='" + request.getParameter("LIQ_NO") + "'";
										if (LIQ_DOC_ATTACH_NO!="?"){sSql=sSql + " AND TDLABEL_LIQ_ATTACH_IMAGE.LIQ_DOC_ATTACH_NO="+ request.getParameter("LIQ_DOC_ATTACH_NO");}										
										sSql=sSql + " GROUP BY TDLABEL_LIQ_ATTACH_IMAGE.LIQ_DOC_ATTACH_NO,MTLIQDOCATTACH.LIQ_DOC_ATTACH_DESC,MTLIQDOCATTACH.LIQ_DOC_ATTACH_NO";
										sSql=sSql + " ORDER BY TDLABEL_LIQ_ATTACH_IMAGE.LIQ_DOC_ATTACH_NO";		
										ResultSet rs = stmt.executeQuery(sSql); 						
										while(rs.next()) 
										{	
											//if (iCount % 2==0){sBG="#FFFFFF";}else{sBG="#edeaea";}
											sDesc=rs.getString("LIQ_DOC_ATTACH_DESC");
											sValue=rs.getInt("ICOUNT");	
											
											out.println("ที่หน้า");
											
											out.println("<select name='PAGE_SCAN' class='dropdown' style='width:50'>");
											for (int i=1;i<=sValue;i++){
	
												out.println("<option value='"+ i +"'>"+ i +"");
											}
											out.println("</select>");
											out.println("/");
											out.println("<input type='text' name='txtPAGE_SCAN' style='width:30' style='text-align:right' class='textinput' READONLY value="+ sValue +" >");
											out.println("ภาพ");
													
											//out.println("<tr><td height=\"25\" align=\"left\" bgcolor=\""+ sBG  +"\" class=\"text\"><a href=\"#\" onclick=\"PopupIMAGE('"+ rs.getString("LIQ_DOC_ATTACH_NO") +"','"+ LIQ_NO +"');\" >"+ sDesc +" จำนวน  <B>"+ sValue +" </B> ภาพ "+ sImg +"</a></td></tr>");

											iCount+=1;
										}
								}catch(SQLException e) { 
									out.println(e.getMessage());
								}catch(ClassNotFoundException e){ 
									out.println(e.getMessage());
								}finally{ 
									//Clean up resources, close the connection. 
									if(conn != null){ 
										try{ 
											conn.close(); 
										}catch (Exception ignored) {} 
									} 
								}	
								*/
							--%>
               <BR>

	
	<input type='hidden' name='PAGE_SELECT' class='textinput' value="<%--=request.getParameter("PAGE_SCAN") --%>">
	<input type='hidden' name='textitemno' class='textinput' value="<%--=sValue --%>">
	<input type='hidden' name='txtcurrentimage' class='textinput' value="">
	<!-- <INPUT id="button1" class="button" onClick="WithDialog" type="button" size="30" value="Start Scan" name="button1">
	<INPUT id="button1" class="button" onClick="javascript:window.close()" type="button" size="30" value="Close" name="button1"> -->
</P>
<BODY onLoad="pageonload();"  bgcolor="#99B2CC">
<table cellspacing="0" cellpadding="0" width="100%" bgcolor="#ffffff" border="0">
<tr height="400">
<td valign ="top" width="25%" height ="300" align ="center" bgcolor ="f0f0f0">
<object classid="clsid:FFC6F181-A5CF-4EC4-A441-093D7134FBF2" id="DynamicWebTwain1" 
			width="50%" 
			height="550"
			CodeBase = "DynamicWebTwain.cab#version=6,0">
			<param name="_cx" value="847">
	   		<param name="_cy" value="847">
	  		<param name="JpgQuality" value="80">
	  		<param name="Manufacturer" value="DynamSoft Corporation">
	  		<param name="ProductFamily" value="Dynamic Web TWAIN">
	  		<param name="ProductName" value="Dynamic Web TWAIN">
	  		<param name="VersionInfo" value="Dynamic Web TWAIN 6.0">
	  		<param name="TransferMode" value="0">
	  		<param name="BorderStyle" value="0">
	  		<param name="FTPUserName" value>
	  		<param name="FTPPassword" value>
	  		<param name="FTPPort" value="21">
	  		<param name="HTTPUserName" value>
	  		<param name="HTTPPassword" value>
	  		<param name="HTTPPort" value="80">
	  		<param name="ProxyServer" value>
	  		<param name="IfDisableSourceAfterAcquire" value="0">
	  		<param name="IfShowUI" value="-1">
	  		<param name="IfModalUI" value="-1">
	  		<param name="IfTiffMultiPage" value="0">
	  		<param name="IfThrowException" value="0">
	  		<param name="MaxImagesInBuffer" value="1">
	  		<param name="TIFFCompressionType" value="0">
	  		<param name="IfFitWindow" value="-1">
		</object></td>
<td valign="top" align="middle" width="66%">
<object classid="clsid:FFC6F181-A5CF-4EC4-A441-093D7134FBF2" id="DynamicWebTwain2" 
			width="750" 
			height="550"
			CodeBase = "DynamicWebTwain.cab#version=6,0">
	   		<param name="_cx" value="847">
	   		<param name="_cy" value="847">
	  		<param name="JpgQuality" value="80">
	  		<param name="Manufacturer" value="DynamSoft Corporation">
	  		<param name="ProductFamily" value="Dynamic Web TWAIN">
	  		<param name="ProductName" value="Dynamic Web TWAIN">
	  		<param name="VersionInfo" value="Dynamic Web TWAIN 6.0">
	  		<param name="TransferMode" value="0">
	  		<param name="BorderStyle" value="0">
	  		<param name="FTPUserName" value>
	  		<param name="FTPPassword" value>
	  		<param name="FTPPort" value="21">
	  		<param name="HTTPUserName" value>
	  		<param name="HTTPPassword" value>
	  		<param name="HTTPPort" value="80">
	  		<param name="ProxyServer" value>
	  		<param name="IfDisableSourceAfterAcquire" value="0">
	  		<param name="IfShowUI" value="-1">
	  		<param name="IfModalUI" value="-1">
	  		<param name="IfTiffMultiPage" value="0">
	  		<param name="IfThrowException" value="0">
	  		<param name="MaxImagesInBuffer" value="1">
	  		<param name="TIFFCompressionType" value="0">
	  		<param name="IfFitWindow" value="-1">
		</object></td>
<td valign="top" align="middle" width="25%"><table width="232" border="0">
  <tr>
    <td><table width="200" border="1">
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="170" border="0">
      <tr>
        <td>
        	<input id="btnScan" onClick="return btnScan_onclick()" type="button" value="แสกนเอกสาร">
        	</td>
        </tr>
      <tr>
        <td>
        	<input id="btnFit" onClick="return btnFit_onclick()" type="button" value="ภาพพอดีกับหน้าจอ" />
        	</td>
        </tr>
      <tr>
        <td>
        	<input id="btnRotateLeft" onClick="return btnRotateLeft_onclick()" type="button" value="หมุนภาพไปทางซ้าย">
        	</td>
        </tr>
      <tr>
        <td>
        	<input id="btnRotateRight" onClick="return btnRotateRight_onclick()" type="button" value="หมุนภาพไปทางขวา">
        	</td>
        </tr>
      <tr>
        <td>
        	<input id="btnZoomIn" onClick="return btnZoomIn_onclick()" type="button" value=" ขยายภาพ ">
        	</td>
        </tr>
      <tr>
        <td>
        	<input id="btnZoomOut" onClick="return btnZoomOut_onclick()" type="button" value=" ย่อภาพ ">
        	</td>
        </tr>
      <tr>
        <td>
        	<input id="btnRemoveAllImages" onClick="return btnRemoveAllImages_onclick()" type="button" value=" ลบภาพออกทั้งหมดจากหน้าจอ "></td>
        </tr>
      <tr>
        <td>
        	<input type="button" id="btnRemoveCurrentImages" onClick="return btnRemoveCurrentImages_onclick()" value=" ลบภาพปัจุบันออกจากหน้าจอ "></td>
        </tr>
      <tr>
        <td>
        	<input onClick="return btnUpload_onclick('current')" type="button" value="บันทึกภาพปัจจุบัน">
        	</td>
        </tr>
      <tr>
        <td>
        	<input onClick="return btnUploadAll_onclick('all')" type="button" value="บันทึกภาพทั้งหมด">
        </td>
        </tr>
      
      <tr>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        </tr>
      <tr>
        <td>&nbsp;</td>
        </tr>
    </table></td>
  </tr>
</table></td>
</tr>
</table>
<center>
	<P>
		
</P>
</form>
</BODY>
</HTML>