<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

	<title>Custom Scrollbars Demo</title>

	<link rel="stylesheet" type="text/css" href="static/css/scroll/layout-default-latest.css" />
	<link rel="stylesheet" type="text/css" href="static/css/scroll/jquery.scrollpane.css" />

	<style type="text/css">
		.ui-layout-north{
			padding:		0 0 0;
		},
		.ui-layout-west ,
	/*	.ui-layout-east , */
		.ui-layout-center {
			padding:		0;
			}
			h3 ,
			p.footer {
				font-size:		1.2em;
				background:		#F6F6F6;
				border-bottom:	1px solid #BBB;
				padding:		5px 15px;
				/*padding:		0px 0px;*/
				margin:			0;
			}
			p.footer {
				font-size:		1em;
				border-top:		1px solid #BBB;
				border-bottom:	0;
			}
	/*	.ui-layout-east , */
			.ui-layout-content {
				padding:		0;
				overflow:		hidden !important; /* prevent scrollbars auto-added by Layout */
				}
				.scrolling-content {
					height:			100%;
			/*		padding:		0 15px 10px;*/ /* extra paddingTop being added by scrollpane? */
					padding:		0 0 0;
					padding-right:	0; /* for pseudo-scrollbar on right */
				}


		.jScrollPaneTrack {
			background: url(static/images/scroll/osx_track.gif) repeat-y;
		}
		.jScrollPaneDrag {
			background: url(static/images/scroll/osx_drag_middle.gif) repeat-y;
		}
		.jScrollPaneDragTop {
			background: url(static/images/scroll/osx_drag_top.gif) no-repeat;
			height: 6px;
		}
		.jScrollPaneDragBottom {
			background: url(static/images/scroll/osx_drag_bottom.gif) no-repeat;
			height: 7px;
		}
		a.jScrollArrowUp {
			height: 24px;
			background: url(static/images/scroll/osx_arrow_up.png) no-repeat 0 -30px;
		}
		a.jScrollArrowUp:hover {
			background-position: 0 0;
		}
		a.jScrollArrowDown {
			height: 24px;
			background: url(static/images/scroll/osx_arrow_down.png) no-repeat 0 -30px;
		}
		a.jScrollArrowDown:hover {
			background-position: 0 0;
		}

/* Component containers
----------------------------------*/
.ui-widget { font-family: Trebuchet MS, Tahoma, Verdana, Arial, sans-serif; font-size: 1.1em; }
.ui-widget .ui-widget { font-size: 1em; }
.ui-widget input, .ui-widget select, .ui-widget textarea, .ui-widget button { font-family: Trebuchet MS, Tahoma, Verdana, Arial, sans-serif; font-size: 1em; }
.ui-widget-content { border: 1px solid #dddddd; background: #eeeeee url(static/images/accordion/ui-bg_highlight-soft_100_eeeeee_1x100.png) 50% top repeat-x; color: #333333; }
.ui-widget-content a { color: #333333; }
.ui-widget-header { border: 1px solid #e78f08; background: #f6a828 url(static/images/accordion/ui-bg_gloss-wave_35_f6a828_500x100.png) 50% 50% repeat-x; color: #ffffff; font-weight: bold; }
.ui-widget-header a { color: #ffffff; }


/* Interaction states
----------------------------------*/
.ui-state-default, .ui-widget-content .ui-state-default, .ui-widget-header .ui-state-default { border: 1px solid #cccccc; background: #f6f6f6 url(static/images/accordion/ui-bg_glass_100_f6f6f6_1x400.png) 50% 50% repeat-x; font-weight: bold; color: #1c94c4; }
.ui-state-default a, .ui-state-default a:link, .ui-state-default a:visited { color: #1c94c4; text-decoration: none; }
.ui-state-hover, .ui-widget-content .ui-state-hover, .ui-widget-header .ui-state-hover, .ui-state-focus, .ui-widget-content .ui-state-focus, .ui-widget-header .ui-state-focus { border: 1px solid #fbcb09; background: #fdf5ce url(static/images/accordion/ui-bg_glass_100_fdf5ce_1x400.png) 50% 50% repeat-x; font-weight: bold; color: #c77405; }
.ui-state-hover a, .ui-state-hover a:hover { color: #c77405; text-decoration: none; }
.ui-state-active, .ui-widget-content .ui-state-active, .ui-widget-header .ui-state-active { border: 1px solid #fbd850; background: #ffffff url(static/images/accordion/ui-bg_glass_65_ffffff_1x400.png) 50% 50% repeat-x; font-weight: bold; color: #eb8f00; }
.ui-state-active a, .ui-state-active a:link, .ui-state-active a:visited { color: #eb8f00; text-decoration: none; }
.ui-widget :active { outline: none; }


/* Interaction Cues
----------------------------------*/
.ui-state-highlight, .ui-widget-content .ui-state-highlight, .ui-widget-header .ui-state-highlight  {border: 1px solid #fed22f; background: #ffe45c url(static/images/accordion/ui-bg_highlight-soft_75_ffe45c_1x100.png) 50% top repeat-x; color: #363636; }
.ui-state-highlight a, .ui-widget-content .ui-state-highlight a,.ui-widget-header .ui-state-highlight a { color: #363636; }
.ui-state-error, .ui-widget-content .ui-state-error, .ui-widget-header .ui-state-error {border: 1px solid #cd0a0a; background: #b81900 url(static/images/accordion/ui-bg_diagonals-thick_18_b81900_40x40.png) 50% 50% repeat; color: #ffffff; }
.ui-state-error a, .ui-widget-content .ui-state-error a, .ui-widget-header .ui-state-error a { color: #ffffff; }
.ui-state-error-text, .ui-widget-content .ui-state-error-text, .ui-widget-header .ui-state-error-text { color: #ffffff; }
.ui-priority-primary, .ui-widget-content .ui-priority-primary, .ui-widget-header .ui-priority-primary { font-weight: bold; }
.ui-priority-secondary, .ui-widget-content .ui-priority-secondary,  .ui-widget-header .ui-priority-secondary { opacity: .7; filter:Alpha(Opacity=70); font-weight: normal; }
.ui-state-disabled, .ui-widget-content .ui-state-disabled, .ui-widget-header .ui-state-disabled { opacity: .35; filter:Alpha(Opacity=35); background-image: none; }

/* Icons
----------------------------------*/

/* states and static/images/accordion */
.ui-icon { width: 16px; height: 16px; background-image: url(static/images/accordion/ui-icons_222222_256x240.png); }
.ui-widget-content .ui-icon {background-image: url(static/images/accordion/ui-icons_222222_256x240.png); }
.ui-widget-header .ui-icon {background-image: url(static/images/accordion/ui-icons_ffffff_256x240.png); }
.ui-state-default .ui-icon { background-image: url(static/images/accordion/ui-icons_ef8c08_256x240.png); }
.ui-state-hover .ui-icon, .ui-state-focus .ui-icon {background-image: url(static/images/accordion/ui-icons_ef8c08_256x240.png); }
.ui-state-active .ui-icon {background-image: url(static/images/accordion/ui-icons_ef8c08_256x240.png); }
.ui-state-highlight .ui-icon {background-image: url(static/images/accordion/ui-icons_228ef1_256x240.png); }
.ui-state-error .ui-icon, .ui-state-error-text .ui-icon {background-image: url(static/images/accordion/ui-icons_ffd27a_256x240.png); }

/* Overlays */
.ui-widget-overlay { background: #666666 url(static/images/accordion/ui-bg_diagonals-thick_20_666666_40x40.png) 50% 50% repeat; opacity: .50;filter:Alpha(Opacity=50); }
.ui-widget-shadow { margin: -5px 0 0 -5px; padding: 5px; background: #000000 url(static/images/accordion/ui-bg_flat_10_000000_40x100.png) 50% 50% repeat-x; opacity: .20;filter:Alpha(Opacity=20); -moz-border-radius: 5px; -webkit-border-radius: 5px; border-radius: 5px; }
	</style>
<script type="text/javascript" src="static/js/accordion/jquery-1.5.1.min.js"></script>
<!-- 
	<script type="text/javascript" src="static/js/scroll/jquery-latest.js"></script>
	 -->
	<script type="text/javascript" src="static/js/scroll/jquery-ui-latest.js"></script>
	<script type="text/javascript" src="static/js/scroll/jquery.layout-latest.js"></script>
	<script type="text/javascript" src="static/js/scroll/jquery.mousewheel.js"></script>
	<script type="text/javascript" src="static/js/scroll/jquery.scrollpane.js"></script>
	<script type="text/javascript" src="static/js/scroll/debug.js"></script>
	
	<script type="text/javascript" src="static/js/accordion/jquery-ui-1.8.11.custom.min.js"></script>
		<!-- 
	<link type="text/css" href="static/css/accordion/ui-lightness/jquery-ui-1.8.11.custom.css" rel="stylesheet" />
	 -->	
	<link type="text/css" href="static/css/accordion/ui-lightness/css_bk.css" rel="stylesheet" />		
	<!-- 
	<link rel="stylesheet" href="static/css/accordion/jquery.ui.all.css"/>
	 
	<script type="text/javascript" src="static/js/accordion/jquery.ui.core.js"></script>
	<script type="text/javascript" src="static/js/accordion/jquery.ui.widget.js"></script>

	<script src="static/js/accordion/jquery.ui.accordion.js"></script>
	<link rel="stylesheet" href="static/css/accordion/demos.css"/>
	  -->

	<script type="text/javascript">

	function initPaneScrollbar ( pane, $Pane ) {
		$Pane.find("div.scrolling-content:first")
			// re/init the pseudo-scrollbar
			.jScrollPane({
				scrollbarMargin:	15	// spacing between text and scrollbar
			,	scrollbarWidth:		15
			,	arrowSize:			16
			,	showArrows:			true
			})
			// REMOVE the *fixed width & height* just set on jScrollPaneContainer
			.parent(".jScrollPaneContainer").css({
				width:	'100%'
			,	height:	'100%'
			})
		;
	};

	var myLayout	;

	$(document).ready(function () {

		myLayout = $('body').layout({
		//	need to re-init the scrollbar whenever the pane resizes
			center__onresize:			initPaneScrollbar
		,	west__onresize:				initPaneScrollbar
	//	,	east__onresize:				initPaneScrollbar
		//	timing issue, so init scrolling AFTER init done (below)
		,	triggerEventsOnLoad:		false
		//	live-resizing on all panes - not required
		,	resizeWhileDragging:		true
		//	avoid re-initing scrollbars repeatedly while: resizeWhileDragging
		//	sizing is much smoother, but scrollbar.height and text-width don't update until 'done'
		,	triggerEventsWhileDragging:	false
		,	north__spacing_open:			0
		// enable state-managment for pane-size
		,	useStateCookie:				true
		});

		// NOW init all scrollbars
		initPaneScrollbar( 'west', myLayout.panes.west );
//		initPaneScrollbar( 'east', myLayout.panes.east );
		initPaneScrollbar( 'center', myLayout.panes.center );

		// accordion ;
		var icons = {
			header: "ui-icon-circle-arrow-e",
			headerSelected: "ui-icon-circle-arrow-s"
		};
//	$( "#accordion" ).accordion({
//	icons: icons
//	});
		$( "#accordion" ).accordion();
 	});
	 
	</script>

 
</head>
<body>

<div class="ui-layout-center">
	<h3>Center Header</h3>

	<div class="ui-layout-content">
		<div class="scrolling-content">

			<p>Center</p>
			<p>
				Now is the time for all good men to come to the aid of their country.
				Now is the time for all good men to come to the aid of their country.
				Now is the time for all good men to come to the aid of their country.
				Now is the time for all good men to come to the aid of their country.
			</p>
			<p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p>

			<p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p>
			<p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p>

			<p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p>
			<p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p>

			<p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p>
			<p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p>

			<p>
				Now is the time for all good men to come to the aid of their country.
				Now is the time for all good men to come to the aid of their country.
				Now is the time for all good men to come to the aid of their country.
				Now is the time for all good men to come to the aid of their country.
			</p>
		</div>
	</div>

	<p class="footer">Center Footer</p>
</div>

<div class="ui-layout-west">
	<h3>West</h3>

	<div class="ui-layout-content">

		<div class="scrolling-content">
		<!-- 
			<p>West</p>
			 --> 
<div id="accordion">
	<h2><a href="#"><img src="static/images/icon_01.gif" alt="" />บริหารโปรแกรม</a></h2>
	<div>
		<p>Mauris mauris ante,</p>
	</div>
	<h2><a href="#"><img src="static/images/icon_01.gif" alt="" />ข่าวประชาสัมพันธ์</a></h2>
	<div>
		<p>Sed non urna. Donec </p>

	</div>
	<h2><a href="#"><img src="static/images/icon_02.gif" alt="" />ข้อมูลกฎหมาย</a></h2>
	<div>
		<p>Nam enim risus, molestie</p>
		<ul>
			<li>List item one</li>
			<li>List item two</li>

			<li>List item three</li>
		</ul>
	</div>
	<h2><a href="#"><img src="static/images/icon_03.gif" alt="" />ข้อมูลคำพิพากษา/คำวินิจฉัย</a></h2>
	<div>
		<p>Cras dictum. Pellentesque</p>
	</div> 
	<h2><a href="#"><img src="static/images/icon_04.gif" alt="" />งานหนังสือตอบข้อหารือ</a></h2>
	<div>
		<p>Cras dictum. Pellentesque</p>
	</div>
	<h2><a href="#"><img src="static/images/icon_04.gif" alt="" />ระบบข้อหารือเผยแพร่</a></h2>
	<div>
		<p>Cras dictum. Pellentesque</p>
	</div>
</div>

 
			
			<!--   
			<p>
				Now is the time for all good men to come to the aid of their country.
			
				Now is the time for all good men to come to the aid of their country.
				Now is the time for all good men to come to the aid of their country.
				Now is the time for all good men to come to the aid of their country.
			</p>
			<p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p>

			<p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p>
			<p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p>

			<p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p>
			<p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p>

			<p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p>
			<p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p>
			-->
		</div>
	</div>
</div>
<!-- 
<div class="ui-layout-east">
	<div class="scrolling-content">
		<p>East</p>
		<p>
			Now is the time for all good men to come to the aid of their country.
			Now is the time for all good men to come to the aid of their country.
			Now is the time for all good men to come to the aid of their country.
			Now is the time for all good men to come to the aid of their country.
		</p>

		<p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p>
		<p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p>

		<p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p>
		<p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p>

		<p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p>
		<p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p>

		<p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p><p>...</p>
	</div>
</div>
 -->
<div class="ui-layout-north">
<table width="100%" border="0" cellspacing="0" cellpadding="0" >
				<tr    background="static/images/bg_top.jpg">
					<td width="100%"><img src="static/images/logo.jpg"  height="40"/></td>
					<td align="right">
					<%--
						<table width="305" border="0" cellspacing="0" cellpadding="0">
							<tr>
							
								<td width="57">
									 
								</td>
								<td width="71">
								 
								</td>
								<td width="45">
								 
								</td>
								<td width="76">
								 
								</td>
							
							    <td colspan="4" ><img src="static/images/logo.jpg" width="100%" height="40"/></td>
								<td width="51"><a href="logout.jsp"><img src="static/images/top_5a.jpg" name="Image6" width="51" height="40" border="0"/></a></td>
								<td align="right">&nbsp;</td>
							</tr>
						</table>
						--%>
						<a href="logout.jsp"><img src="static/images/top_5a.jpg" name="Image6" height="40" border="0"/></a>
					</td>
				</tr>
			</table>
</div>

<div class="ui-layout-south"> South </div>

</body>
</html>