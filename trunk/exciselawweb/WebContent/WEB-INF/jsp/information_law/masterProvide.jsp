<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/includes.jsp" %><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
//function doEdit(aDesc, aCode)
function doEdit(_id)
{
	window.location=_path+'/information_law/masterProvideDetail.do?action=edit&statuteId='+_id;
	/*
	var sWhere = document.form1.txtWhere.value;
	var intCurr = document.form1.txtCurr.value;
	var strSortCol = document.form1.txtSortCol.value;
	var strSortOrd = document.form1.txtSortAsc.value;
	var LIQTYPE = document.form1.LIQTYPE.value;
  	window.open("MasterProvideDetail.jsp?STATUTE_ID="+ aCode + "&txtWhere="+ sWhere +"&txtCurr="+ intCurr + "&txtSortCol=" + strSortCol + "&txtSortAsc=" + strSortOrd,"wa");
  	*/
	//window.open("MasterProvideDetail.jsp?LIQTYPE=" + LIQTYPE + "&TOBAC_NO="+ aCode + "&txtWhere="+ sWhere +"&txtCurr="+ intCurr + "&txtSortCol=" + strSortCol + "&txtSortAsc=" + strSortOrd,"wa");
}
function search(check)
{
	window.open("MasterProvide.jsp?FUNC="+check,"wa");
	document.form1.submit();
}
function callfunc(functype)
{	 
	 
	var msSubmatr={
			name:document.getElementById("msStatute.name").value 
	};
	var paging={
			
	}
	//alert(msSubmatr);
	//$(‘#selectList :selected’).text();
	oTable.fnClearTable();
	oTable.fnProcessingIndicator();      // On
	
	ExciseLawAjax.getMsStatutes(msSubmatr,paging,{
		 callback:function(dataFromServer){
            if(dataFromServer!=null && dataFromServer.length>0){
		//		 alert(dataFromServer)
				 var msSubmatrList=dataFromServer[0];
				 //msMatrGroupsList[i].msStatute!=null?msMatrGroupsList[i].msStatute.name:
				 for(var i=0;i<msSubmatrList.length;i++){
					 oTable.fnAddData([msSubmatrList[i].name,
							 msSubmatrList[i].statuteOrder!=null?msSubmatrList[i].statuteOrder+"":"",
							 (msSubmatrList[i].active!=null&&msSubmatrList[i].active==1)?"Active":"No Active",
		                   "<span style=\"cursor: pointer;\" title=\"ดูรายละเอียดข้อมูล\"  onclick=\"return doEdit("+msSubmatrList[i].statuteId+")\"><img  border=\"0\" src=\""+_path+"/images/icon_15.gif\"/></span>"
					                   ]);
				 }
				 	  		 
				 //document.getElementById('myDiv').innerHTML=dataFromServer;
				 oTable.fnProcessingIndicator(false); // Off
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
request.setCharacterEncoding("TIS-620");
response.setCharacterEncoding("TIS-620");
response.setHeader("Cache-Control", "no-cache"); 
	String sql ="";
	Connection conn =null;
	int intCurr = 1;
	int intSortOrd = 0;
	String strTmp = null;
	String strSQL = null;
	String strSortCol = null;
	String strSortOrd = "ASC";
	boolean blnSortAsc = true; 
	///
	DataBaseControl mycontrol = new DataBaseControl();
	StringControl myStrControl=new StringControl();
	VariableControl myVarControl =new VariableControl();
	DateControl myDateControl=new DateControl();
	ObjectControl myObjControl=new ObjectControl();

	String FUNC = "";
	FUNC = request.getParameter("FUNC");
	String sWhere;
	sWhere="";
	Class.forName(VariableControl.gDB_Driver); 		
	conn = mycontrol.gConnectDB();
	
	String SEARCH = myStrControl.gNullToBlank(request.getParameter("SEARCH"));
	
	sql = "SELECT STATUTE_ID,NAME,cast(ACTIVE as varchar(5)) as ACTIVE ,cast(STATUTE_ORDER as varchar(5)) as STATUTE_ORDER1 ";
	sql = sql + " FROM MS_STATUTE ";
	if(SEARCH.length() > 0){
		if(sWhere==""){sWhere=sWhere + " WHERE ";}
		sWhere=sWhere + " NAME LIKE '%"+ SEARCH +"%' ";
	}
	sql = sql + sWhere;
	sql = sql + " ORDER BY STATUTE_ORDER ASC";
	strSortCol = request.getParameter("txtSortCol");
	strSortOrd = request.getParameter("txtSortAsc");
	strTmp = request.getParameter("txtCurr");
	
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
	System.out.println("sql>>"+sql);
--%>
<title>Insert title here</title>
</head>
<body>
<%--
<form id="form1" name="form1" method="post" action="">
 --%>
<spring:url value="/information_law/masterProvideDetail.do" var="formAction">
</spring:url>
<form:form modelAttribute="msStatuteForm"  id="form1" method="post" action="${fn:escapeXml(formAction)}">
<form:hidden path="action" id="action"/>
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td valign="top">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="25" class="text">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td height="30" class="head2">กำหนดประเภทกฎหมาย  >> <font color="#FF6600">ประเภทพระราชบัญญัติ  >> </font><font color="#000000"> ค้นหาข้อมูล </font></td>
								<td align="right">
								<INPUT TYPE="button" value="เพิ่มข้อมูล" style="width: 80px" onClick="javascript:window.location='<spring:url value="/information_law/masterProvideDetail.do" htmlEscape="true" />'" >
									<INPUT TYPE="button"  value="ค้นหา" style="width: 80px"   onClick="return callfunc('doSearch')" >	
									<%-- 
									<INPUT TYPE="Reset" value="เคลียร์" style="width: 80px" >
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
								<td valign="top" align='right' class="Ghead">พระราชบัญญัติ</td>
								<td align="left"> 
									<form:input path="msStatute.name"   cssClass="field" size="30"/>
																  </td>
							</tr>
						</table>
					</td>
				</tr>
				<tr><td height='5'>&nbsp;</td></tr>
				<tr>

					<td>
					 <table cellpadding="0" cellspacing="0" border="0" class="display" id="example">
	<thead>
		<tr>
			<th width="25%" name="th_type" class="Ghead" align="left">พระราชบัญญัติ</th>
			<th width="25%" name="th_type" class="Ghead" align="left">ลำดับ</th>
			<th width="25%" name="th_type" class="Ghead" align="left">สถานะ</th>
			<th width="5%" name="th_type" class="Ghead" align="left">&nbsp;</th>
		</tr>
	</thead>
	<tbody  class="text">
		
	</tbody>
	 
</table>
<%--
				<table align="center"width="98%" border="0" cellspacing="2" cellpadding="0" >
					  <tr>
					  <td align="center" bgcolor="#999999">พระราชบัญญัติ</td>
					  <td align="center" bgcolor="#999999">ลำดับ</td>
					  <td align="center" bgcolor="#999999">สถานะ</td> 
					  <td align="center" bgcolor="#999999">x</td> 
					  </tr>
                        <c:forEach items="${msStatutes}" var="msStatute" varStatus="loop">
                        	<tr>
                        	 <td align="center" ><c:out value="${msStatute.name}"/></td>
					 		 <td align="center" ><c:out value="${msStatute.statuteOrder}"/></td>
					  		 <td align="center" ><c:out value="${msStatute.active}"/></td> 
					  		 <td align="center" >  
					  		 <spring:url value="/information_law/masterProvideDetail.do" var="editUrl">
              						<spring:param name="action" value="edit"/>
              						<spring:param name="statuteId" value="${msStatute.statuteId}"/>
          					  </spring:url>
					  		 <span style="cursor: pointer;" title="ดูรายละเอียดข้อมูล"  onclick='return doEdit("${msStatute.statuteId}")'><img  border="0" src='/images/icon_15.gif'></span> 
					  		 </td> 
                        	</tr>
                        </c:forEach>
                       </table>
            --%>					</td>
				</tr>
				<tr>
					<td height="50" class="text-field">&nbsp;
<input TYPE="hidden" NAME="txtWhere" VALUE="<%--=sWhere.replace("%","$") --%>">
<input TYPE="hidden" NAME="txtCurr" VALUE="<%--=intCurr--%>">
<input TYPE="hidden" NAME="txtSortCol" VALUE="<%--=strSortCol--%>">
<input TYPE="hidden" NAM
E="txtSortAsc" VALUE="<%--=strSortOrd--%>">
<input TYPE="hidden" NAME="txtPre" VALUE="<%--=request.getParameter("P")--%>">
<input TYPE="hidden" NAME="LIQTYPE" VALUE="<%--=request.getParameter("LIQTYPE")--%>">
<input TYPE="hidden" NAME="name" VALUE="<%--=request.getParameter("name")--%>">
<input TYPE="hidden" NAME="FUNC" VALUE="<%--=request.getParameter("FUNC")--%>">
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
		     			{ "bSortable": false, "aTargets": [ 0,1,2,3] }
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
