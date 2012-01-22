<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<html>
<head>
<title></title>
<link href="<%=request.getContextPath() %>/css/law.css" rel="stylesheet" type="text/css">
<script type="text/javascript" language="javascript" src="<%=request.getContextPath() %>/media/js/jquery.js"></script>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath() %>/media/js/jquery.dataTables.js"></script>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath() %>/media/js/jquery.dateFormatTh-1.0.js"></script>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath() %>/media/js/jquery.number_format.js"></script>
<script type="text/javascript" src='<%=request.getContextPath() %>/dwr/interface/ExciseLawAjax.js'></script>
<script type="text/javascript" src='<%=request.getContextPath() %>/dwr/engine.js'></script>
<script type="text/javascript" src='<%=request.getContextPath() %>/dwr/util.js'></script>
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
/*  start add css new paging*/
.pagination {
	padding: 2px;
	float: right;
	font-family: Tahoma, Verdana;
	font-size: 12px;
}

.pagination ul {
	margin: 0;
	padding: 0;
	/*font-size: 16px;*/
	font-family: Tahoma, Verdana;
	font-size: 12px;
}

.pagination li {
	list-style-type: none;
	display: inline;
	padding-bottom: 1px;
	font-family: Tahoma, Verdana;
	font-size: 12px;
}

.pagination a,.pagination a:visited {
	padding: 0 5px;
	border: 1px solid #9aafe5;
	text-decoration: none;
	color: #2e6ab1;
	font-family: Tahoma, Verdana;
	font-size: 12px;
}

.pagination a:hover,.pagination a:active {
	border: 1px solid #2b66a5;
	color: #000;
	background-color: #FFFF80;
	font-family: Tahoma, Verdana;
	font-size: 12px;
}

.pagination a.currentpage {
	background-color: #2e6ab1;
	color: #FFF !important;
	border-color: #2b66a5;
	font-weight: bold;
	cursor: default;
	font-family: Tahoma, Verdana;
	font-size: 12px;
}

.pagination a.disablelink,.pagination a.disablelink:hover {
	background-color: white;
	cursor: default;
	color: #929292;
	border-color: #929292;
	font-weight: normal !important;
	font-family: Tahoma, Verdana;
	font-size: 12px;
}

.pagination a.prevnext {
	font-weight: bold;
	font-family: Tahoma, Verdana;
	font-size: 12px;
}
/* end add css new paging*/
</style>
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
function doNavigate(pstrWhere, pintTot){
	var strTmp;
	var intPg;     
	strTmp = document.form1.txtCurr.value;
	intPg = parseInt(strTmp);
	if (isNaN(intPg)) intPg = 1; 
	if ((pstrWhere == 'F' || pstrWhere == 'P') && intPg == 1){
	  alert("You are already viewing first page!");
	  return;
	}else if ((pstrWhere == 'N' || pstrWhere == 'L') && intPg == pintTot){
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
function doSort(pstrFld, pstrOrd){
	document.form1.txtSortCol.value = pstrFld;
	document.form1.txtSortAsc.value = pstrOrd;
	document.form1.submit();
}
function doEdit(id){ 
	window.location=_path+'/law/articleGroup.do?action=edit&articleGroupId='+id;
} function callfunc(_pageIndex){	
	// get pagesize
	var pageSize=$("#select_perpage").val() ;
	var articleGroup={ 
		articleGroupType:document.getElementById("articleGroupType").value,
		statueId:document.getElementById("statueId").value,
		articleGroupName:document.getElementById("articleGroupName").value
	};
	//set paging
	var paging={
			pageNo:_pageIndex,
			pageSize:pageSize
	} 
	oTable.fnClearTable();
	oTable.fnProcessingIndicator();      // On
	
	ExciseLawAjax.searchArticleGroupList(articleGroup , paging,{
		 callback:function(dataFromServer){
            if(dataFromServer!=null && dataFromServer.length>0){ 
				 var articleGroupList=dataFromServer[0]; 
				 for(var i=0;i<articleGroupList.length;i++){
					 oTable.fnAddData([articleGroupList[i].articleGroupType?articleGroupList[i].articleGroupType=='E'?"กฎหมายสรรพสามิต":"กฎหมายอื่น":"",
							 			articleGroupList[i].statueName?articleGroupList[i].statueName:"",
									 	articleGroupList[i].articleGroupName?articleGroupList[i].articleGroupName:"",
										articleGroupList[i].articleGroupOrder?articleGroupList[i].articleGroupOrder:"",
					                   	"<span style=\"cursor: pointer, align: center;\" title=\"ดูรายละเอียดข้อมูล\"  onclick=\"return doEdit("+articleGroupList[i].articleGroupId+")\"><img  border=\"0\" src=\""+_path+"/images/icon_15.gif\"/></span>"
					                   ]);
				 }
				 oTable.fnProcessingIndicator(false); // Off
				 // calculate page
				 var size=dataFromServer[1];
				 var pagingScript_recordCount=parseInt(size, 10);;//totalRecord;
					var pagingScript_recordPerPage=parseInt(pageSize,10);//pageSize;//RECORD_PERPAGE;
					       		
					var plus = pagingScript_recordCount%pagingScript_recordPerPage!=0?1:0;
					var pagingScript_pageCount=parseInt((pagingScript_recordCount/pagingScript_recordPerPage),10)+plus;
					var pageNo=parseInt(_pageIndex,10);
					var startIndex =pageNo-parseInt((pageBetween/2),10);
					var endIndex = pageNo+(parseInt((pageBetween/2),10)-1); 
					//alert("startIndex="+startIndex)
					var page_plus=pageBetween-((endIndex-startIndex)+1);
						endIndex=endIndex+page_plus;
						
				  if(startIndex<1){
							   startIndex = 1;
							   endIndex=pageBetween;
							}
						    
				   if(endIndex>pagingScript_pageCount){    		
						    endIndex = pagingScript_pageCount;
						    if((endIndex-pageBetween+1)>0)
						   	startIndex=endIndex-pageBetween+1;
				    } 
					       	
				   var havePrev=false;
					var haveNext=false;
				   var pagePrev=pageNo;
					var pageNext=pageNo;
					if(pageNo!=1){
				       		havePrev=true;
				       		pagePrev=pagePrev-1;
					}
				    if(pageNo!=pagingScript_pageCount){
				       		haveNext=true; 
				       		pageNext=pageNext+1;
				    }
				    havePrev=true;
				    haveNext=true;
				    
					var pageStr="<ul>";
			       if(havePrev)
			    	   pageStr=pageStr+"<li><a  onClick=\"callfunc('1')\"  class=\"prevnext\">« First</a></li>&nbsp;"+
			    	   					"<li><a  onClick=\"callfunc('"+pagePrev+"')\"  class=\"prevnext\">« Previous</a></li>&nbsp;";
				   for(var k=startIndex;k<=endIndex;k++ ){
					   if(k==pageNo) 
					  	 pageStr = pageStr+"<li><a class=\"currentpage\">"+k+"</a></li>&nbsp;";
					  else
						 pageStr = pageStr+"<li><a  onClick=\"callfunc('"+k+"')\">"+k+"</a></li>&nbsp;";
				    }
				   if(haveNext)
			    	   pageStr=pageStr+"<li><a  onClick=\"callfunc('"+pageNext+"')\"  class=\"prevnext\">Next »</a></li>&nbsp;"+
			    	   					"<li><a  onClick=\"callfunc('"+pagingScript_pageCount+"')\" class=\"prevnext\">Last »</a></li>";
				   pageStr=pageStr+"</ul>";
				   $("#entry_total").html("Totals "+size+" entries");
				   $("#entry_paging").html(pageStr);
            }
		 }
	});
	return false;
}
</script>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<spring:url value="/law/articleGroup.do" var="formAction"></spring:url>
<form:form modelAttribute="masterLawForm"  id="form1" method="post" action="${fn:escapeXml(formAction)}" ><form:hidden path="action" id="action"/> 
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td height="30" class="head2"><strong>ข้อมูลหลัก >> หมวด >> </strong><font color="#000000">ค้นหาข้อมูล </font></td>
		<td align="right">
			<c:if test="${masterLawForm.add=='true'}">
				<INPUT TYPE="button" value="เพิ่มข้อมูล" style="width: 80px" onClick="javascript:window.location='<spring:url value="/law/articleGroup.do?action=add" htmlEscape="true" />'" >
			</c:if>
			<c:if test="${masterLawForm.view=='true'}">
				<INPUT TYPE="submit" value="ค้นหา" style="width: 80px" onClick="return callfunc('1')" >
			</c:if>
		</td>
	</tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0">			
	<tr><td height='5'>&nbsp;</td></tr>
	<tr>
		<td>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">          
				<tr>
					<td width="38%" align="right" height="25" class="Ghead">กฎหมาย&nbsp;</td>
					<td width="62%" class="text">&nbsp;
						<form:select path="tmArticleGroup.articleGroupType" id="articleGroupType" cssStyle="width: 250px" cssClass="dropdown">
						    <form:option value="" label="- - - Please Select - - -"/> 
			    			<form:options items="${lawTypeList}" itemValue="id" itemLabel="name"/> 
						</form:select>
					</td>
				</tr>
				<tr>
					<td width="38%" align="right" height="25" class="Ghead">พระราชบัญญัติ&nbsp;</td>                        
					<td width="62%" height="25">&nbsp;
						<form:select path="tmArticleGroup.statueId" id="statueId" cssStyle="width: 250px" cssClass="dropdown">
						    <form:option value="" label="- - - Please Select - - -"/> 
			    			<form:options items="${statueList}" itemValue="statueId" itemLabel="statueName"/> 
						</form:select>
					</td>
				</tr>   
				<tr>
					<td width="38%" align="right" height="25" class="Ghead">หมวด(ภายใต้พระราชบัญญัติ)&nbsp;</td>
					<td width="62%" class="text">&nbsp;
						<form:input path="tmArticleGroup.articleGroupName" id="articleGroupName" cssClass="text" cssStyle="width: 250px" maxlength="200"/>
					</td>
				</tr>
			</table>
        </td>
	</tr>
	<tr><td height='5'>&nbsp;</td></tr>
	<!--  start tag add tr select perpage -->
	<tr>
		<td>
		<table cellpadding="0" cellspacing="0" border="0" class="display" id="example">
			<thead>
		       <tr>
				 <th width="100%" colspan="5" name="th_type" class="Ghead" align="left">
				Show 
				<select id="select_perpage" onchange="callfunc('1');">
				    <option value="10">10</option>
				    <option value="25">25</option>
				    <option value="50">50</option>
				    <option value="100">100</option>
				</select>entries
				</th>
		         </tr>
	        </thead> 
		</table>
		</td>
	</tr>
	<!--  end tag add tr select perpage  -->
	<tr>
		<td>
		<table cellpadding="0" cellspacing="0" border="0" class="display" id="example">
			<thead>
				<tr>
					<th width="12%" name="th_type" class="Ghead" align="left">กฎหมาย</th>
					<th width="22%" name="th_type" class="Ghead" align="left">พระราชบัญญัติ</th>
					<th width="*" name="th_type" class="Ghead" align="left">หมวด</th>
					<th width="14%" name="th_type" class="Ghead" align="left">ลำดับการแสดงผล</th>
					<th width="10%" name="th_type" class="Ghead" align="left">รายละเอียด</th>
				</tr>
			</thead>
			<tbody  class="text">
			</tbody>	 
		</table>
		</td>
	</tr>
	<!--  start tag add tr paging -->
	<tr>
		<td>
		<table cellpadding="0" cellspacing="0" border="0">
			<thead>
				<tr>
		            <th width="50%" colspan="2" name="th_type" class="Ghead" align="left"><span id="entry_total"></span></th>
			        <th width="50%" colspan="3" name="th_type" class="Ghead" align="right"><span id="entry_paging" class="pagination"></span></th> 
		        </tr>
			</thead> 
		</table>
		</td>
	</tr>
	<!--  end tag add tr paging -->
</table>
</form:form >
<script type="text/javascript">
$(document).ready(function() {
	oTable = $('#example').dataTable( {
		/* "bProcessing": true,
		"bFilter": false,
		"sPaginationType": "aoe_paging",
		"aoColumnDefs": [ 
		     				{ "bSortable": false, "aTargets": [4] }
		     			] */
		"bProcessing": true,
		"bFilter": false,
		"bPaginate":false,
		"bSort": false,
		"bInfo": false
	} );
	callfunc('1');
});
</script>
<script type="text/javascript">
function statusMessage(msg,mode){
	alert(msg);
}
</script>
<c:if test="${masterLawForm.isSusses=='1' && masterLawForm.action == 'doDelete'}">
<script>
statusMessage("ลบข้อมูลเรียบร้อย\n",'doDelete');
</script>
</c:if>
</body>
</html>
