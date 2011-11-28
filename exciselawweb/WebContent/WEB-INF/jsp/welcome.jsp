<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<title>ระบบกฏหมายสรรพสามิต</title>
<script type="text/javascript"
        src='<%=request.getContextPath() %>/dwr/interface/ExciseLawAjax.js'> 
</script>
<script type="text/javascript"
        src='<%=request.getContextPath() %>/dwr/engine.js'> 
</script>
<script type="text/javascript"
        src='<%=request.getContextPath() %>/dwr/util.js'>
</script>
<script type="text/javascript" src="static/ckeditor/ckeditor.js"></script>
<script language="JavaScript" type="text/JavaScript">
function callAjax(){
	var textValue= "aoe";//document.getElementById('aoee').value;
	//var textValue = this.getContentElement( 'tab1', 'input1' );
	ExciseLawAjax.test(textValue,{
		 callback:function(dataFromServer){
             if(dataFromServer!=null && dataFromServer.length>0){
				 alert(dataFromServer)
				 //document.getElementById('myDiv').innerHTML=dataFromServer;
             }
		 }
	});
}
<!--
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
//-->
</script>
 
<script language="JavaScript">
<!--
function resize_iframe()
{
	var height=window.innerWidth;//Firefox
	if (document.body.clientHeight)
	{
		height=document.body.clientHeight;//IE
	}
	document.getElementById("glu").style.height=parseInt(height-document.getElementById("glu").offsetTop-60)+"px";
}

window.onresize=resize_iframe; 
//-->
</script> 
<script type="text/javascript">
function change(id){ 
	ID = document.getElementById(id); 
    if(ID.style.display == "") 
    	ID.style.display = "none"; 
    else 
    	ID.style.display = ""; 
		ID.style.width="200px";
}

</script> 
 
<script type="text/javascript">
function MenuChange(id){ 
	id=parseInt(id);
	MenuMax=7; //  
	for(var a = 0; a < (MenuMax-1); a++){
		if(a == id) {
			document.getElementById('Menu'+a).style.display = ""; 
			document.getElementById('SubMenu'+a).style.display = ""; 
			document.getElementById('TdMenu'+a).style.backgroundImage ="url(static/images/bg_menu2.jpg)";  
			document.getElementById('TdMenu'+a).onmouseout = function(){
				this.style.backgroundImage ="url(static/images/bg_menu2.jpg)";  
			};
		}else { 
			document.getElementById("Menu"+a+"").style.display = "none"; 		
			document.getElementById('SubMenu'+a+"").style.display = "none"; 
			document.getElementById('TdMenu'+a+"").style.backgroundImage ="url(static/images/bg_menu1.jpg)";  
			document.getElementById('TdMenu'+a+"").onmouseover = function()
			
		{
			this.style.backgroundImage ="url(static/images/bg_menu2.jpg)";  
		};
		document.getElementById('TdMenu'+a+"").onmouseout = function()
		{
			this.style.backgroundImage ="url(static/images/bg_menu1.jpg)";  
		};
		}
		}
}
</script> 
<style type="text/css">
.border{
width:100%;
height:100%;

}
</style> 
<style> 
.Normal {background-image:url("static/images/bg_menu1.jpg");}
.Highlight {background-image:url("static/images/bg_menu2.jpg");}

</style> 

<link href="static/css/sura.css" rel="stylesheet" type="text/css">
<%--
DataBaseControl mycontrol = new DataBaseControl();
StringControl myStrControl=new StringControl();
VariableControl myVarControl =new VariableControl();
DateControl myDateControl=new DateControl();
ObjectControl myObjControl=new ObjectControl();

Connection conn =null;
conn = mycontrol.gConnectDB();
--%>
  <script src="static/js/jquery.js"></script>
  <script src="static/js/jquery.layout.js"></script>
  <script>
  var myLayout	;
    $(document).ready(function () {
    	myLayout = $('body').layout({
    		//	need to re-init the scrollbar whenever the pane resizes
    			center__onresize:			initPaneScrollbar
    		,	west__onresize:				initPaneScrollbar
    		,	east__onresize:				initPaneScrollbar
    		//	timing issue, so init scrolling AFTER init done (below)
    		,	triggerEventsOnLoad:		false
    		//	live-resizing on all panes - not required
    		,	resizeWhileDragging:		true
    		//	avoid re-initing scrollbars repeatedly while: resizeWhileDragging
    		//	sizing is much smoother, but scrollbar.height and text-width don't update until 'done'
    		,	triggerEventsWhileDragging:	false
    		// enable state-managment for pane-size
    		,	useStateCookie:				true
    		});

    		// NOW init all scrollbars
    		initPaneScrollbar( 'west', myLayout.panes.west );
    		initPaneScrollbar( 'east', myLayout.panes.east );
    		initPaneScrollbar( 'center', myLayout.panes.center );
        	// applyDefaultStyles: true 
        //	});
      });
  </script>
</head>

<body style="overflow:hidden" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="MM_preloadImages('static/images/top_1b.jpg','static/images/top_2b.jpg','static/images/top_3b.jpg','static/images/top_4b.jpg','static/images/top_5b.jpg','static/images/tool_01b.jpg','static/images/tool_02b.jpg','static/images/tool_03b.jpg','static/images/tool_04b.jpg','static/images/tool_05b.jpg')">
 <div class="ui-layout-center">Center</div>
  <div class="ui-layout-north">North</div>
  <div class="ui-layout-south">South</div>
  <div class="ui-layout-east">East</div>
  <div class="ui-layout-west">West</div>
</body>
</html> 