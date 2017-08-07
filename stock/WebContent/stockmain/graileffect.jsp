<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<% String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />  
	<title>股票统计界面</title>
 	<script src="<%= path%>/jquery/jquery-1.11.3.min.js"></script>
 	<script src="<%= path%>/bootstrap/js/bootstrap.min.js"></script>
 	<script src="<%= path%>/bootstrap/js/npm.js"></script>
 	<script type="text/javascript" src="<%= path%>/dist/calendar-jquery.min.js"></script>
 	<script type="text/javascript" src="../commonjs/validator.js"></script>
 	<link href="<%= path%>/bootstrap/css/bootstrap.min.css" rel="stylesheet">
 	<link href="<%= path%>/stock/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">
	<link href="../css/maincss/main.css" rel="stylesheet">
	<link href="../dist/calendar.min.css" rel="stylesheet">
	
</head>
<script type="text/javascript">
	function getCalendar(){
		$(".calendar2").Calendar({toolbar:true});
	     $(".calendar2").Calendar();
	}

	function getGrailEffect(){
		var redioValue = $("input[name='inlineCheckbox']:checked").val();
		var calendarValue = $("#getCalendarId").val();
		var num = $("#numId").val();
		var betweenTime = $("#betweenTimeId").val();
		var showStr = "前 "+num+" 条股票的赚钱效应:";
		if(!isInt(num) || num > 100){
			alert("请输入数字且值要小于100");
			return;
		}
		if(!isInt(betweenTime) || betweenTime > 30){
			alert("间隔时间必须是整数且数值小于30");
			return;
		}
		if(redioValue == "1"){
			inlineCheckbox1Value = "1";
			showStr = "涨幅"+showStr;
		}
		if(redioValue == "0"){
			inlineCheckbox2Value = '1';
			showStr = "跌幅"+showStr;
		}
		var url = '<%=path%>'+"/grailEffectServlet?calendarValue=" + calendarValue + "&num="+num+"&redioValue="+redioValue+"&betweenTime="+betweenTime;
		$.ajax({
		   type: "POST",
		   url: url,
		   data: "",
		   success: function(msg){
		     var jsonObj = eval("("+msg+")");
		     if(jsonObj.ifSuccess == "1"){
		    	 caseList(jsonObj.mainList,jsonObj.effectRaise,showStr)
		     }else{
		    	 alert(jsonObj.messsage);
		     }
		   },
		   error:function(){
			   alert("请求异常");
		   }
		});
	}
	
	function caseList(caseMainList,effectRaise,showStr){
		$("#caseListId").empty();
		//$("#caseListId").before("<p>"+showStr+effectRaise+"</p>");
		$("#caseListId").append("<caption>"+showStr+effectRaise+"</caption>"+"<thead><tr>"
				+"<th>股票时间</th>"
				+"<th>股票代码</th>"
				+"<th>股票名称</th>"
				+"<th>昨收</th>"
				+"<th>现收</th>"
				+"<th>涨幅</th>"
				+"<th>昨日涨幅</th>"
				+"</tr></thead>");
		
		if(caseMainList.length > 0){
			$("#caseListId").append("<tbody>");
			for(var i = 0 ; i < caseMainList.length ; i++){
				if(caseMainList[i].colorType == "1"){
					$("#caseListId").append("<tr class='danger'>"
							+"<td>"+caseMainList[i].stockTime+"</td>"
							+"<td>"+caseMainList[i].stockCode+"</td>"
							+"<td>"+caseMainList[i].stockName+"</td>"
							+"<td>"+caseMainList[i].stockPrice+"</td>"
							+"<td>"+caseMainList[i].currentPrice+"</td>"
							+"<td>"+caseMainList[i].stockIncrease+"</td>"
							+"<td>"+caseMainList[i].yesterdayStockIncrease+"</td>"
							+"</tr>"
							);
				}else{
					$("#caseListId").append("<tr class='success'>"
							+"<td>"+caseMainList[i].stockTime+"</td>"
							+"<td>"+caseMainList[i].stockCode+"</td>"
							+"<td>"+caseMainList[i].stockName+"</td>"
							+"<td>"+caseMainList[i].stockPrice+"</td>"
							+"<td>"+caseMainList[i].currentPrice+"</td>"
							+"<td>"+caseMainList[i].stockIncrease+"</td>"
							+"<td>"+caseMainList[i].yesterdayStockIncrease+"</td>"
							+"</tr>"
							);
					
				}
				
			}
			$("#caseListId").append("</tbody>")
		}	
		
	}
	
</script>
<body>
	<div class="mainheader"></div>

	<!-- 导航栏 -->
	<div class="container navigation" style="background-color: #6A6AFF ">
		<div class="row">
			<div class="col nav">
				<ul class="nav nav-tabs">
					<li class="dropdown">
						<a class="acolor" href="#" class="dropdown-toggle" data-toggle="dropdown">导入导出<b class="caret"></b>
						</a>
						<ul class="dropdown-menu">
							<li><a class="acolor" href="<%= path%>/stockmain/main.jsp" id="stockLeadingIn">股票导入</a></li>
							<li><a class="acolor" href="#" id="stockLeadingOut">股票导出</a></li>
							<li><a class="acolor" hre f="<%= path%>/stockmain/graileffect.jsp" id="stockStatistics">股票统计</a></li>
						</ul>
					</li>
					<li>
						 <a class="acolor" href="#" id="stockBoardStatistics">连板统计</a>
					</li>
					<li class="disabled">
						 <a class="acolor" href="#">其他</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<div class="container main"  style="background-color: #F0F0F0; font-size: 20px;">
		 <div class="panel panel-default">
		     <div class="panel-heading" >查询N条数据的赚钱效应</div>
    		 <div class="panel-body">
		        <form class="form-inline" role="form" id="formId" name="formId" action="" enctype="multipart/form-data" method="post">
				 选择时间： <input type="text" id="getCalendarId" class="calendar2" value="" onclick="getCalendar();"/>
				  &nbsp;&nbsp;&nbsp;&nbsp;
				  <div class="checkbox">
				    <label class="checkbox-inline">
				     	数据条数：<input type="text" id="numId" value="20" style="width:80px">
				    </label>
				    <label class="checkbox-inline">
				     	时间间隔：<input type="text" id="betweenTimeId" value="1" style="width:80px">
				    </label>
				    <label class="checkbox-inline">
						<input type="radio" name="inlineCheckbox" id="inlineCheckbox1" value="1" checked> 涨幅
					</label>
					<label class="checkbox-inline">
						<input type="radio" name="inlineCheckbox" id="inlineCheckbox1"  value="0"> 跌幅
					</label>
				  </div>
				  &nbsp;&nbsp;&nbsp;&nbsp;
				  <button type="button" class="btn btn-default" onclick="getGrailEffect();">提交</button>
				</form>
		    </div>
		</div>
		<div>
			<table id="caseListId" class="table"></table>
		</div>
	</div>
	
</body>
</html>