<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/includes.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>PDA : DEPOSIT PROTECTION AGENCY</title>
<style type="text/css" title="currentStyle">
			@import "<%=request.getContextPath() %>/media/css/demo_page.css";
			@import "<%=request.getContextPath() %>/media/css/demo_table.css";
		</style>
		<style type="text/css">
				.paging_aoe_paging {
				font-family: Tahoma, "MS Sans Serif", "Microsoft Sans Serif";
	font-size: 12px;
	color: #333333;
	width: 400px;
	height: 22px;
	line-height: 22px;
}

.paging_aoe_paging span.paginate_button,
 	.paging_aoe_paging span.paginate_active {
	border: 1px solid #aaa;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	padding: 2px 5px;
	margin: 0 3px;
	cursor: pointer;
	*cursor: hand;
}

.paging_aoe_paging span.paginate_button {
	background-color: #ddd;
}

.paging_aoe_paging span.paginate_button:hover {
	background-color: #ccc;
}

.paging_aoe_paging span.paginate_active {
	background-color: #99B3FF;
}
</style>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath() %>/media/js/jquery.js"></script>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath() %>/media/js/jquery.dataTables.js"></script>
<script type="text/javascript"
        src='<%=request.getContextPath() %>/dwr/interface/ExciseLawAjax.js'> 
</script>
<script type="text/javascript"
        src='<%=request.getContextPath() %>/dwr/engine.js'> 
</script>
<script type="text/javascript"
        src='<%=request.getContextPath() %>/dwr/util.js'>
</script>
<script language="JavaScript" type="text/JavaScript">
var _path='<%=request.getContextPath()%>';
// aoe paging
$.fn.dataTableExt.oPagination.aoe_paging= {
/*
 * Function: oPagination.full_numbers.fnInit
 * Purpose:  Initalise dom elements required for pagination with a list of the pages
 * Returns:  -
 * Inputs:   object:oSettings - dataTables settings object
*           node:nPaging - the DIV which contains this pagination control
 *           function:fnCallbackDraw - draw function which must be called on update
 */
"fnInit": function ( oSettings, nPaging, fnCallbackDraw )
{
	var nFirst = document.createElement( 'span' );
	var nPrevious = document.createElement( 'span' );
	var nList = document.createElement( 'span' );
	var nNext = document.createElement( 'span' );
	var nLast = document.createElement( 'span' );
	
	nFirst.innerHTML = oSettings.oLanguage.oPaginate.sFirst;
	nPrevious.innerHTML = oSettings.oLanguage.oPaginate.sPrevious;
	nNext.innerHTML = oSettings.oLanguage.oPaginate.sNext;
	nLast.innerHTML = oSettings.oLanguage.oPaginate.sLast;
	
	var oClasses = oSettings.oClasses;
//	alert("init oClasses="+oClasses.sPageButton+" "+oClasses.sPageFirst)
	//alert(oSettings.sTableId)
	nFirst.className = oClasses.sPageButton+" "+oClasses.sPageFirst;
	nPrevious.className = oClasses.sPageButton+" "+oClasses.sPagePrevious;
	nNext.className= oClasses.sPageButton+" "+oClasses.sPageNext;
	nLast.className = oClasses.sPageButton+" "+oClasses.sPageLast;
	//alert("init nFirst.className="+nFirst.className)
	nPaging.appendChild( nFirst );
	nPaging.appendChild( nPrevious );
	nPaging.appendChild( nList );
	nPaging.appendChild( nNext );
	nPaging.appendChild( nLast );
	
	$(nFirst).bind( 'click.DT', function () {
		if ( oSettings.oApi._fnPageChange( oSettings, "first" ) )
		{
			//alert("nFirst");
			fnCallbackDraw( oSettings );
		}
	} );
	
	$(nPrevious).bind( 'click.DT', function() {
		if ( oSettings.oApi._fnPageChange( oSettings, "previous" ) )
		{
			//alert("nPrevious");
			fnCallbackDraw( oSettings );
		}
	} );
	
	$(nNext).bind( 'click.DT', function() {
		if ( oSettings.oApi._fnPageChange( oSettings, "next" ) )
		{
			//alert("nNext");
			fnCallbackDraw( oSettings );
		}
	} );
	
	$(nLast).bind( 'click.DT', function() {
		if ( oSettings.oApi._fnPageChange( oSettings, "last" ) )
		{
			//alert("nLast");
			fnCallbackDraw( oSettings );
		}
	} );
	
	/* Take the brutal approach to cancelling text selection */
	$('span', nPaging)
		.bind( 'mousedown.DT', function () { return false; } )
		.bind( 'selectstart.DT', function () { return false; } );
	
	/* ID the first elements only */
	if ( oSettings.sTableId !== '' && typeof oSettings.aanFeatures.p == "undefined" )
	{
		nPaging.setAttribute( 'id', oSettings.sTableId+'_paginate' );
		nFirst.setAttribute( 'id', oSettings.sTableId+'_first' );
		nPrevious.setAttribute( 'id', oSettings.sTableId+'_previous' );
		nNext.setAttribute( 'id', oSettings.sTableId+'_next' );
		nLast.setAttribute( 'id', oSettings.sTableId+'_last' );
	}
},

/*
 * Function: oPagination.full_numbers.fnUpdate
 * Purpose:  Update the list of page buttons shows
 * Returns:  -
 * Inputs:   object:oSettings - dataTables settings object
 *           function:fnCallbackDraw - draw function to call on page change
 */
"fnUpdate": function ( oSettings, fnCallbackDraw )
{
	if ( !oSettings.aanFeatures.p )
	{
		return;
	}
	
	var iPageCount = $.fn.dataTableExt.oPagination.iFullNumbersShowPages;
	var iPageCountHalf = Math.floor(iPageCount / 2);
	var iPages = Math.ceil((oSettings.fnRecordsDisplay()) / oSettings._iDisplayLength);
	var iCurrentPage = Math.ceil(oSettings._iDisplayStart / oSettings._iDisplayLength) + 1;
	var sList = "";
	var iStartButton, iEndButton, i, iLen;
	var oClasses = oSettings.oClasses;
	//alert(oClasses)
	/* Pages calculation */
	if (iPages < iPageCount)
	{
		iStartButton = 1;
		iEndButton = iPages;
	}
	else
	{
		if (iCurrentPage <= iPageCountHalf)
		{
			iStartButton = 1;
			iEndButton = iPageCount;
		}
		else
		{
			if (iCurrentPage >= (iPages - iPageCountHalf))
			{
				iStartButton = iPages - iPageCount + 1;
				iEndButton = iPages;
			}
			else
			{
				iStartButton = iCurrentPage - Math.ceil(iPageCount / 2) + 1;
				iEndButton = iStartButton + iPageCount - 1;
			}
		}
	}
	//alert(oClasses.sPageButton+"xxxaoe")
	/* Build the dynamic list */
	for ( i=iStartButton ; i<=iEndButton ; i++ )
	{
		if ( iCurrentPage != i )
		{
			sList += '<span class="'+oClasses.sPageButton+'">'+i+'</span>';
		}
		else
		{
			sList += '<span class="'+oClasses.sPageButtonActive+'">'+i+'</span>';
		}
	}
	
	/* Loop over each instance of the pager */
	var an = oSettings.aanFeatures.p;
	var anButtons, anStatic, nPaginateList;
	var fnClick = function() {
		/* Use the information in the element to jump to the required page */
		var iTarget = (this.innerHTML * 1) - 1;
		oSettings._iDisplayStart = iTarget * oSettings._iDisplayLength;
		//alert("click page="+iTarget)
		fnCallbackDraw( oSettings );
		return false;
	};
	var fnFalse = function () { return false; };
	
	for ( i=0, iLen=an.length ; i<iLen ; i++ )
	{
		if ( an[i].childNodes.length === 0 )
		{
			continue;
		}
		
		/* Build up the dynamic list forst - html and listeners */
		var qjPaginateList = $('span:eq(2)', an[i]);
		qjPaginateList.html( sList );
		$('span', qjPaginateList).bind( 'click.DT', fnClick ).bind( 'mousedown.DT', fnFalse )
			.bind( 'selectstart.DT', fnFalse );
		
		/* Update the 'premanent botton's classes */
		anButtons = an[i].getElementsByTagName('span');
		anStatic = [
			anButtons[0], anButtons[1], 
			anButtons[anButtons.length-2], anButtons[anButtons.length-1]
		];
		$(anStatic).removeClass( oClasses.sPageButton+" "+oClasses.sPageButtonActive+" "+oClasses.sPageButtonStaticDisabled );
		if ( iCurrentPage == 1 )
		{
			anStatic[0].className += " "+oClasses.sPageButtonStaticDisabled;
			anStatic[1].className += " "+oClasses.sPageButtonStaticDisabled;
		}
		else
		{
			anStatic[0].className += " "+oClasses.sPageButton;
			anStatic[1].className += " "+oClasses.sPageButton;
		}
		
		if ( iPages === 0 || iCurrentPage == iPages || oSettings._iDisplayLength == -1 )
		{
			anStatic[2].className += " "+oClasses.sPageButtonStaticDisabled;
			anStatic[3].className += " "+oClasses.sPageButtonStaticDisabled;
		}
		else
		{
			anStatic[2].className += " "+oClasses.sPageButton;
			anStatic[3].className += " "+oClasses.sPageButton;
		}
	}
}
};
// end aoe paging
$.fn.dataTableExt.oApi.fnProcessingIndicator = function ( oSettings, onoff )
{
	if( typeof(onoff) == 'undefined' )
	{
		onoff=true;
	}
	this.oApi._fnProcessingDisplay( oSettings, onoff );
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
function doEdit(aDesc, aCode)
{
	var sWhere = document.form1.txtWhere.value;
	var intCurr = document.form1.txtCurr.value;
	var strSortCol = document.form1.txtSortCol.value;
	var strSortOrd = document.form1.txtSortAsc.value;
  	window.open("input.jsp?ST_STATUTESUB_ID="+ aCode + "&txtWhere="+ sWhere +"&txtCurr="+ intCurr + "&txtSortCol=" + strSortCol + "&txtSortAsc=" + strSortOrd,"wa");
	//window.open("MasterProvideDetail.jsp?LIQTYPE=" + LIQTYPE + "&TOBAC_NO="+ aCode + "&txtWhere="+ sWhere +"&txtCurr="+ intCurr + "&txtSortCol=" + strSortCol + "&txtSortAsc=" + strSortOrd,"wa");
}
function choose(id,typeid)
{
	var ID = id;
	var ID1 = typeid;
	alert(ID);
	alert(ID1);
	document.form1.action = "input.jsp?sWhere='"+ID+"'&sWhere1='"+ID1+"'";
	document.form1.submit();
}


function search(check){
	/*
	var s1 = document.form1.LEGAL_TYPE_ID.value;//ประเภทกฏหมาย
	var sd1=document.form1.LEGAL_TYPE_ID;
	var a1=sd1.options[sd1.selectedIndex].value;


	var s2 = document.form1.STATUTE_ID.value;//ประเภทพระราชบัญญัต
	var sd2=document.form1.STATUTE_ID;
	var a2=sd2.options[sd2.selectedIndex].value;

	 
	var s3 = document.form1.LEGAL_ITEM_ID.value;//ประเภทสินค้า
	var sd3=document.form1.LEGAL_ITEM_ID;
	var a3=sd3.options[sd3.selectedIndex].value;

	
	var a4 = document.form1.LEGAL_HEADER.value;//ชื่อเรื่อง
	var a5 = document.form1.LEGAL_KEYWORD.value;//คำช่วยค้น
*/
	
	document.form1.submit();
	//document.form1.submit();
}

function addnew(){
	var TYPE = document.form1.TYPE.value;
	if(TYPE == "1"){
  		window.open("LawDetail3.jsp","wa");
	}
	if(TYPE == "2"){
		window.open("LawOther.jsp","wa");
	}
}

function doNavigate(pstrWhere, pintTot)
{
  var strTmp;
  var intPg;     
  strTmp = document.form1.txtCurr.value;
  intPg = parseInt(strTmp);
  if (isNaN(intPg)) intPg = 1; 
  if ((pstrWhere == 'F' || pstrWhere == 'P') && intPg == 1)
  {
    alert("You are already viewing first page!");
    return;
  }
  else if ((pstrWhere == 'N' || pstrWhere == 'L') && intPg == pintTot)
  {
    alert("You are already viewing last page!");
    return;
  }
  if (pstrWhere == 'F')
    intPg = 1;
  else if (pstrWhere == 'P')
    intPg = intPg - 1;
  else if (pstrWhere == 'N')
    intPg = intPg + 1;
  else if (pstrWhere == 'L')
    intPg = pintTot; 
  if (intPg < 1) intPg = 1;
  if (intPg > pintTot) intPg = pintTot;
  document.form1.txtPre.value = "";
  document.form1.txtCurr.value = intPg;
  document.form1.submit();
}
function doSort(pstrFld, pstrOrd)
{
  document.form1.txtSortCol.value = pstrFld;
  document.form1.txtSortAsc.value = pstrOrd;
  document.form1.submit();
}
function doEditExt(_id)
{ 

	window.location=_path+'/statutesub/input.do?action=edit&stStatutesubId='+_id;
	//alert(_id);
	 
	} 
function callfunc(functype)
{	 
	//statutesubId statuteId msLegalitem stStatutesub.article stStatutesub.keyword
	
	var page_length = document.getElementsByName('example_length');
	//alert(page_length[0].value)
	var msStatutesub={statutesubId:document.getElementById("statutesubId").value};
	var msStatute={statuteId:document.getElementById("statuteId").value};
	//alert(document.getElementById("stStatutesub.keyword").value)
	var stStatutesub={
			article:document.getElementById("stStatutesub.article").value,
			keyword:document.getElementById("stStatutesub.keyword").value,
			msStatutesub:msStatutesub,
			msStatute:msStatute
	};
	//var pageSize=_pageSize!=null?_pageSize:10;
	var paging={
			//pageSize:page_length[0].value
			pageSize:51
	} 
	oTable.fnClearTable();
	oTable.fnProcessingIndicator();      // On
	
	ExciseLawAjax.getStStatutesubs(stStatutesub, paging,null,{
		 callback:function(dataFromServer){
            if(dataFromServer!=null && dataFromServer.length>0){ 
				 var stStatutesubList=dataFromServer[0]; 
				 //alert("dataFormServer="+stStatutesubList)
				 for(var i=0;i<stStatutesubList.length;i++){
					//if(i==0){
						oTable.fnAddData([(stStatutesubList[i].msStatutesub!=null && stStatutesubList[i].msStatutesub.name!=null)?stStatutesubList[i].msStatutesub.name:"",
						                   stStatutesubList[i].article,
						                   (stStatutesubList[i].status!=null && stStatutesubList[i].status=='1')?"มีผลบังคับใช้":"ไม่มีผลบังคับใช้",
						                   "<span style=\"cursor: pointer;\" title=\"ดูรายละเอียดข้อมูล\"  onclick=\"return doEditExt("+stStatutesubList[i].stStatutesubId+")\"><img  border=\"0\" src=\""+_path+"/images/icon_15.gif\"/></span>"
						                   ]);
				//	}
						//alert(stStatutesubList[i].msStatutesub.name);
					 /* 
					 
					*/
				 }
				 	  		 
				 //document.getElementById('myDiv').innerHTML=dataFromServer;
				 oTable.fnProcessingIndicator(false); // Off
				var page_element= document.getElementById('example_paginate');
				 /*
				<span class="first paginate_button" id="example_first">First</span><span class="previous paginate_button" 
					id="example_previous">Previous</span><span><span class="paginate_active">1</span><span class="paginate_button">2</span><span 
					class="paginate_button">3</span><span class="paginate_button">4</span><span class="paginate_button">5</span></span><span 
					class="next paginate_button" id="example_next">Next</span><span class="last paginate_button" id="example_last">Last</span>
				 alert(page_element.innerHTML)
				 */
            }
		 }
	});
	
	//document.getElementById("action").value = functype;
	//return true;
	return false;
}
</script>
<link href="<%=request.getContextPath() %>/css/sura.css" rel="stylesheet" type="text/css">
<%--
	int intCurr = 1;
	int intSortOrd = 0;
	String strTmp = null;
	String strSQL = null;
	String strSortCol = null;
	String strSortOrd = "ASC";
	boolean blnSortAsc = true; 
	
	String sql = "";
	Connection conn = null;
	request.setCharacterEncoding("TIS-620");
	response.setCharacterEncoding("TIS-620");
	response.setHeader("Cache-Control", "no-cache"); 
	DataBaseControl mycontrol = new DataBaseControl();
	StringControl myStrControl=new StringControl();
	VariableControl myVarControl =new VariableControl();
	DateControl myDateControl=new DateControl();
	ObjectControl myObjControl=new ObjectControl();

	String STATUTE_ID = "";
	String LEGAL_ITEM_ID = "";
	String LEGAL_TYPE_ID = "";
	String LEGAL_HEADER = "";
	String LEGAL_KEYWORD = "";
	LEGAL_TYPE_ID = myStrControl.gNullToBlank(request.getParameter("LEGAL_TYPE_ID"));
	if(LEGAL_TYPE_ID.equals("NONE")){LEGAL_TYPE_ID = "";}
	STATUTE_ID = myStrControl.gNullToBlank(request.getParameter("STATUTE_ID"));
	if(STATUTE_ID.equals("NONE")){STATUTE_ID = "";}
	LEGAL_ITEM_ID = myStrControl.gNullToBlank(request.getParameter("LEGAL_ITEM_ID"));
	if(LEGAL_ITEM_ID.equals("NONE")){LEGAL_ITEM_ID = "";}
	LEGAL_HEADER = myStrControl.gNullToBlank(request.getParameter("LEGAL_HEADER"));
	LEGAL_KEYWORD = myStrControl.gNullToBlank(request.getParameter("LEGAL_KEYWORD"));
	
	String sWhere ="";
	String sSql = "";
	

		sSql = " SELECT ST.ST_STATUTESUB_ID AS ST_STATUTESUB_ID,ST.STATUTESUB_ID AS STATUTESUB_ID,MS.NAME AS NAME,decode(ST.STATUS ,null,1) AS STATUS,ST.ARTICLE AS ARTICLE ";
		sSql = sSql + " FROM ST_STATUTESUB ST";
		sSql = sSql + " left join MS_STATUTESUB ms on  ms.STATUTESUB_ID=st.STATUTESUB_ID  ";
		strSortCol = request.getParameter("txtSortCol");
		strSortOrd = request.getParameter("txtSortAsc");
		strTmp = request.getParameter("txtCurr");


		//ประเภทพระราชบัญัติ
		if (STATUTE_ID.length()>0){
			if (sWhere!=""){
				sWhere = sWhere + " AND ";
			}else{sWhere = sWhere + " WHERE ";}
			sWhere=sWhere + " STATUTE_ID = "+ STATUTE_ID;
		}
		
		//ประเภทสินค้า
		if (LEGAL_ITEM_ID.length()>0){
			if (sWhere!=""){
				sWhere=sWhere + " AND ";
			}else{sWhere = sWhere + " WHERE ";}
			sWhere = sWhere + " THLEGAL.LEGAL_ITEM_ID = "+ LEGAL_ITEM_ID;
		}
		
		//ชื่อเรื่อง
		if (LEGAL_HEADER.length()>0){
			if (sWhere!=""){
				sWhere=sWhere + " AND ";
			}else{sWhere = sWhere + " WHERE ";}
			sWhere = sWhere + " LEGAL_HEADER_TH LIKE '%"+ LEGAL_HEADER +"%' ";
		}
		
		//คำค้น
		if (LEGAL_KEYWORD.length()>0){
			if (sWhere!=""){
				sWhere=sWhere + " AND ";
			}else{sWhere = sWhere + " WHERE ";}
			sWhere = sWhere + " LEGAL_KEYWORD LIKE '%"+ LEGAL_KEYWORD +"%' ";
		}
		
		if(LEGAL_TYPE_ID.length()>0)
		{
			if (sWhere.equals("")){
				sWhere=sWhere + " WHERE ";
			}else{sWhere = sWhere + " AND ";}
			sWhere = sWhere + " THLEGAL.LEGAL_TYPE_ID ='"+ LEGAL_TYPE_ID+"'";
		}
		
		sSql = sSql + sWhere;
		sSql = sSql + " ORDER BY ST_STATUTESUB_ID ASC";
		//out.println(LEGAL_TYPE_ID);
		//out.println(sSql);
try
	{
	  if (strTmp != null)
	  intCurr = Integer.parseInt(strTmp);
	}
	catch (NumberFormatException NFEx)
	{
	}
	
	if (strSortCol == null) strSortCol = "LIQR";
	if (strSortOrd == null) strSortOrd = "ASC";
	blnSortAsc = (strSortOrd.equals("ASC"));
	Class.forName(VariableControl.gDB_Driver); 			
	conn= mycontrol.gConnectDB();	
	
--%>
</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="MM_preloadImages('../images/tool_01b.jpg','../images/tool_02b.jpg','../images/tool_03b.jpg','../images/tool_05b.jpg')">
<spring:url value="/statutesub/input.do" var="formAction">
</spring:url><form:form modelAttribute="stStatutesubForm"  id="form1" method="post" action="${fn:escapeXml(formAction)}" ><form:hidden path="action" id="action"/> <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td valign="top">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="25" class="text">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td height="30" class="head2"><strong>ข้อมูลกฎหมาย</strong> <font color="#FF6600">กฎหมายสรรพสามิต</font><font color="#000000"> ค้นหาข้อมูล </font></td>
								<td align="right">
								<INPUT TYPE="button" value="เพิ่มข้อมูล" style="width: 80px" onClick="javascript:window.location='<spring:url value="/statutesub/input.do" htmlEscape="true" />'" >
								<INPUT TYPE="submit" value="ค้นหา" style="width: 80px" onClick="return callfunc('doSearch')" >		
								<%--
								<INPUT TYPE="Reset" value="เคลียร์" style="width: 80px" >
								 --%>								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr><td height='5'>&nbsp;</td></tr>
				<tr align='center'>
					<td align='center'>
						<table width="100%" border="0" cellspacing="0" cellpadding="2">
							<tr>
								<td valign="top" align='right' class="text">ประเภทกฎหมาย</td>
								<td align="left">
								 <form:select path="stStatutesub.msStatutesub.statutesubId" id="statutesubId"  cssStyle="width: 300px" cssClass="dropdown" > 
							     				  <form:option value="0" label="- - - Please Select - - -"/> 
    											  <form:options items="${msStatutesubs}" itemValue="statutesubId" itemLabel="name"/>    												
    							 </form:select>
									<%--		
									sql = "SELECT cast(STATUTESUB_ID as varchar2(4)) || '/' || cast(STATUTESUB_GROUP as varchar2(4)) as aFieldCode,NAME as aFieldDesc";
									sql = sql + " FROM MS_STATUTESUB ORDER BY STATUTESUB_ORDER";
              																
						//out.println(myObjControl.gIntitialCombo("","","","","",sql,"LEGAL_TYPE_ID",0,"",""));
						out.println(myObjControl.gIntitialCombo("","","","","",sql,"LEGAL_TYPE_ID",0,"TYPESELECT",LEGAL_TYPE_ID));
					--%>
					</td>
						</tr>

							<tr>
								<td valign="top" align='right' class="text">ประเภทพระราชบัญญัติ</td>
								<td align="left">
								 <form:select path="stStatutesub.msStatute.statuteId" id="statuteId"  cssStyle="width: 300px" cssClass="dropdown" > 
							     				  <form:option value="0" label="- - - Please Select - - -"/> 
    											  <form:options items="${msStatutes}" itemValue="statuteId" itemLabel="name"/>    												
    							 </form:select>
										<%--					
										sql = "SELECT  STATUTE_ID as aFieldCode ,NAME as aFieldDesc";
										sql = sql + " FROM MS_STATUTE ORDER BY STATUTE_ORDER";														
						out.println(myObjControl.gIntitialCombo("","","","","",sql,"STATUTE_ID",0,"STATUTESELECT",STATUTE_ID));
					--%>
								</td>
							</tr>
		
						
							<tr>
								<td valign="top" align='right' class="text">ประเภทสินค้า</td>
								<td align="left"align="left">
								<form:select path="productId" id="productId"  cssStyle="width: 300px" cssClass="dropdown" > 
							     				  <form:option value="0" label="- - - Please Select - - -"/> 
    											  <form:options items="${msLegalitemProducts}" itemValue="legalitemId" itemLabel="name"/>    												
    							 </form:select>
				<%--					 
              			sql = "SELECT LEGALITEM_ID as aFieldCode,NAME as aFieldDesc";
						sql = sql + " FROM MS_LEGALITEM ";																
						out.println(myObjControl.gIntitialCombo("","","","","",sql,"LEGAL_ITEM_ID",0,"LEGALSELECT",LEGAL_ITEM_ID));
					--%>
								</td>
							</tr>
							<tr>
								<td valign="top" align='right' class="text">ชื่อเรื่อง</td>
								<td align="left">
									<form:input path="stStatutesub.article" class="field" size="30" />
								<%--									<input name="LEGAL_HEADER" type="text" class="field" size="30" />
								 --%>	
								</td>
							</tr>
							<tr>
								<td valign="top" align='right' class="text">คำช่วยค้น(Keyword)</td>
								<td align="left">
									<form:input path="stStatutesub.keyword" class="field" size="30" />
									<%--
									 			<input name="LEGAL_KEYWORD" type="text" class="field" size="30" />
									 --%>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
					 <table cellpadding="0" cellspacing="0" border="0" class="display" id="example">
	<thead>
		<tr>
			<th width="15%" name="th_type" class="Ghead" align="left">ประเภทกฎหมาย</th>
			<th width="60%" name="th_type" class="Ghead" align="left">ชื่อเรื่อง</th>
			<th width="20%" name="th_type" class="Ghead" align="left">สถานะการบังคับใช้</th>  
			<th width="5%" name="th_type" class="Ghead" align="left">&nbsp;</th>
		</tr>
	</thead>
	<tbody  class="text">
		
	</tbody>
	 
</table>
		<%--
					<font size="2"> 
			<grd:dbgrid id="tblStat" name="tblStat" width="100" pageSize="10" 
			    currentPage="" border="0" cellSpacing="1" cellPadding="2" 
			    dataMember="" dataSource="" cssClass="text">
			 <grd:gridpager imgFirst="images/First.gif" imgPrevious="images/Previous.gif" 
			      imgNext="images/Next.gif" imgLast="images/Last.gif"/>
			 <grd:gridsorter sortColumn="" sortAscending=""/>
			 
			 <grd:textcolumn dataField="NAME"  headerText="ประเภทกฎหมาย"  cssClass="Ghead" HAlign="left" 
			  width="10"    sortable="true"/>
			     
			 <grd:textcolumn dataField="ARTICLE"  headerText="ชื่อเรื่อง" cssClass="Ghead"  HAlign="left" 
      			    width="26"  sortable="true"/>	
			 <grd:decodecolumn dataField="STATUS" headerText="สถานะการบังคับใช้" cssClass="Ghead"  HAlign="left" width="10" 
      			  decodeValues="1,0,null" displayValues="มีผลบังคับใช้,ไม่มีผลบังคับใช้,ไม่มีผลบังคับใช้" valueSeperator=","/>		
			
			<grd:imagecolumn headerText="" width="5" HAlign="center" cssClass="Ghead"  
			      imageSrc="images/icon_15.gif"
			      linkUrl="javascript:doEdit('{NAME}', '{ST_STATUTESUB_ID}')"
			      imageBorder="0" imageWidth="16" imageHeight="16" 
			      alterText="ดูรายละเอียดข้อมูล"/>	
			 
			</grd:dbgrid> 
	 --%>					
			
					
					</td>
				</tr> 
				<tr>
					<td height="50" class="text-field">
					<input TYPE="hidden" NAME="txtWhere" VALUE=""/>
<input TYPE="hidden" NAME="txtCurr" VALUE="">
<input TYPE="hidden" NAME="txtSortCol" VALUE="">
<input TYPE="hidden" NAME="txtSortAsc" VALUE="">
<input TYPE="hidden" NAME="txtPre" VALUE=""/>
					 
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</form:form>
<script type="text/javascript">
$(document).ready(function() {
	oTable = $('#example').dataTable( {
		//"aaSorting": [ [0,'asc'], [1,'asc'] ],
		"bProcessing": true,
		//"bServerSide": true,
		"bFilter": false,
		// "sAjaxSource": '/examples/examples_support/aoe_source.txt',
		//"sPaginationType": "scrolling",
		//"sPaginationType": "full_numbers",
		"sPaginationType": "aoe_paging",
		"aoColumnDefs": [ 
		     			{ "bSortable": false, "aTargets": [ 0,1,2,3 ] }
		     		]
	/*	
		"oLanguage": {
			"sInfo": "x"
			}
		     		*/
	//	"fnSortListener"
	/*	"oLanguage": {
				"sInfo": "Got a total of _TOTAL_ entries to show (_START_ to _END_)"
				}
		*/
	} );
	callfunc('doSearch');
});
</script>

</body>
</html>