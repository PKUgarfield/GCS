<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import="com.garfield.model.ABCUser"%>

<%
	boolean isLogin = request.getSession().getAttribute("loginFlag")==null? 
			false : request.getSession().getAttribute("loginFlag").toString().equals("true");
	ABCUser user = isLogin ? (ABCUser)request.getSession().getAttribute("user") : null;
%>    
<!DOCTYPE html>
<html lang="en">

<head>
<title>AbcMint Exchange</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="css/fullcalendar.css" />
<link rel="stylesheet" href="css/maruti-style.css" />
<link rel="stylesheet" href="css/maruti-media.css" class="skin-color" />


<style type="text/css">
table {
	width: 90%;
	margin: 0;
	padding: 0;
	border-collapse: collapse;
	border-spacing: 0;
	margin: 0 auto;
}

td {
	vertical-align: middle;
	text-align: center;
}
</style>

</head>
<body>

	<!--Header-part-->
	<div id="header">
		<h1>
			<a href="http://www.mafiashare.net">AbcMint Exchange</a>
		</h1>
	</div>
	<!--close-Header-part-->


	<!--close-top-Header-menu-->
	<!--top-Header-menu-->

	<div id="user-nav" class="navbar navbar-inverse">
		<ul class="nav">
			<li class=""><a title="" href="#"><i class="icon icon-user"></i>
					<span class="text"><%=user.nickname %></span></a></li>
			<li class=" dropdown" id="menu-messages"><a href="#"
				data-toggle="dropdown" data-target="#menu-messages"
				class="dropdown-toggle"><i class="icon icon-envelope"></i> <span
					class="text">Messages</span> <span class="label label-important">5</span>
					<b class="caret"></b></a>
				<ul class="dropdown-menu">
					<li><a class="sAdd" title="" href="#">new message</a></li>
					<li><a class="sInbox" title="" href="#">inbox</a></li>
					<li><a class="sOutbox" title="" href="#">outbox</a></li>
					<li><a class="sTrash" title="" href="#">trash</a></li>
				</ul></li>
			<li class=""><a title="" href="#"><i class="icon icon-cog"></i>
					<span class="text">Settings</span></a></li>
			<li class=""><a title="" href="user.do?cmd=loginout"><i
					class="icon icon-share-alt"></i> <span class="text">Logout</span></a></li>
		</ul>
	</div>


	<div id="sidebar">
		<a href="#" class="visible-phone"><i class="icon icon-home"></i>
			Dashboard</a>
		<ul>
			<li class="active"><a href="index_login.jsp"><i
					class="icon icon-home"></i> <span>Dashboard</span></a></li>
			<li><a href="Transaction.jsp"><i class="icon icon-signal"></i> <span>transaction</span></a></li>

		</ul>
	</div>

	<div id="content">
		<div id="content-header">
			<div id="breadcrumb">
				<a href="index.html" title="Go to Home" class="tip-bottom"><i
					class="icon-home"></i> Home</a>
			</div>
		</div>
		<!-- 
  <div  class="quick-actions_homepage">
    <ul class="quick-actions">
          <li> <a href="#"> <i class="icon-dashboard"></i> My Dashboard </a> </li>
          <li> <a href="#"> <i class="icon-shopping-bag"></i> Shopping Cart</a> </li>
          <li> <a href="#"> <i class="icon-web"></i> Web Marketing </a> </li>
          <li> <a href="#"> <i class="icon-people"></i> Manage Users </a> </li>
          <li> <a href="#"> <i class="icon-calendar"></i> Manage Events </a> </li>
        </ul>
  </div> -->
		<div class="copyrights">
			Collect from <a href="http://www.cssmoban.com/">成交价</a>
		</div>
		<div class="container-fluid">

			<div class="row-fluid">
				<div class="span12">
					<div class="widget-box widget-plain">
						<div class="center">
						
						<div></div><button id="btc">BTC : </button> <span id="btc_value"></span></div>
						<div><button id="bch">BCH : </button> <span id="bch_value"></span></div>
							
								<form action="trade.do">
									价格<input type="text" name="price"></input>
									数量<input type="text" name="amount"></input>
									<input type="hidden" name="userid" value="<%=user.id %>"></input>
									<input type="hidden" name="market" value="BTCBCH"></input>
									<input type="hidden" name="type" value="1"></input>
									<input type="hidden" name="side" value="2"></input>
									<input type="hidden" name="cmd" value="limit"></input>
									<button type="submit">买入BTC</button>
								</form>
								
								<form action="trade.do">
									价格<input type="text" name="price"></input>
									数量<input type="text" name="amount"></input>
									<input type="hidden" name="userid" value="<%=user.id %>"></input>
									<input type="hidden" name="market" value="BTCBCH"></input>
									<input type="hidden" name="type" value="1"></input>
									<input type="hidden" name="side" value="1"></input>
									<input type="hidden" name="cmd" value="limit"></input>
									<button type="submit">卖出BTC</button>
								</form>

							<div><button id="my">MyOrders : </button> <span id="my_value"></span></div>
							
							<div><button id="sell">Market-sells : </button> <span id="sell_value"></span></div>
							<div><button id="buy">Market-buys : </button> <span id="buy_value"></span></div>
						</div>
					</div>
				</div>
			</div>
		</div>
			
	</div>				
		<div class="row-fluid">
			<div id="footer" class="span12">
				2012 &copy; Marutii Admin. More Templates <a
					href="http://www.cssmoban.com/" target="_blank" title="æ¨¡æ¿ä¹å®¶">æ¨¡æ¿ä¹å®¶</a>
				- Collect from <a href="http://www.cssmoban.com/" title="ç½é¡µæ¨¡æ¿"
					target="_blank">ç½é¡µæ¨¡æ¿</a>
			</div>
		</div>
</body>

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#btc").click(function() {
			$.ajax({
				type: "GET",
				url: "asset.do?",				
				async: true,
				dataType: "json",
				contentType: 'application/json;charset=utf-8',
				data: "cmd=bquery&userid=<%=user.id %>&asset=BTC",
				success: function(data) {
					$("#btc_value").html("avalible:" +data.result.BTC.available + " freeze:" + data.result.BTC.freeze);
				},
				error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("[error] " +XMLHttpRequest.status + " "+ XMLHttpRequest.readyState + " " + textStatus);
				}
			});
		})
		
		$("#bch").click(function() {
			$.ajax({
				type: "GET",
				url: "asset.do?",				
				async: true,
				dataType: "json",
				contentType: 'application/json;charset=utf-8',
				data: "cmd=bquery&userid=<%=user.id %>&asset=BCH",
				success: function(data) {
					$("#bch_value").html("avalible:" +data.result.BCH.available + " freeze:" + data.result.BCH.freeze);
				},
				error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("[error] " +XMLHttpRequest.status + " "+ XMLHttpRequest.readyState + " " + textStatus);
				}
			});
		})
		
		$("#my").click(function() {
			$.ajax({
				type: "GET",
				url: "trade.do?",				
				async: true,
				dataType: "text",
				contentType: 'application/json;charset=utf-8',
				data: "cmd=pending&userid=<%=user.id %>&market=BTCBCH&offset=0&limit=10",
				success: function(data) {
					$("#my_value").html(data);
				},
				error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("[error] " +XMLHttpRequest.status + " "+ XMLHttpRequest.readyState + " " + textStatus);
				}
			});
		})
		
		$("#sell").click(function() {
			$.ajax({
				type: "GET",
				url: "trade.do?",				
				async: true,
				dataType: "text",
				contentType: 'application/json;charset=utf-8',
				data: "cmd=book&side=1&market=BTCBCH&offset=0&limit=10",
				success: function(data) {
					$("#sell_value").html(data);
				},
				error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("[error] " +XMLHttpRequest.status + " "+ XMLHttpRequest.readyState + " " + textStatus);
				}
			});
		})
		
		$("#buy").click(function() {
			$.ajax({
				type: "GET",
				url: "trade.do?",				
				async: true,
				dataType: "text",
				contentType: 'application/json;charset=utf-8',
				data: "cmd=book&side=2&market=BTCBCH&offset=0&limit=10",
				success: function(data) {
					$("#buy_value").html(data);
				},
				error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("[error] " +XMLHttpRequest.status + " "+ XMLHttpRequest.readyState + " " + textStatus);
				}
			});
		})
	})
</script>
	
</html>
