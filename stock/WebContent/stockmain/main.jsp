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
 	<script type="text/javascript" src="../dist/calendar-jquery.min.js"></script>
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
	//上传文件
	function inputExcelFile(){
		var actionExcelFile = '<%=path%>'+"/ReceiveFromServlet";    //设置新提交地址
		document.getElementById("formId").action=actionExcelFile;
        document.getElementById("formId").submit();
	}
	
	function getStockMess(){
		var calendarValue = $("#getCalendarId").val();
		var inlineCheckbox1Value = "0";
		var inlineCheckbox2Value = "0";
		var inlineCheckbox3Value = "0";
		var inlineCheckbox4Value = $("#inlineCheckbox4").val();
		var betweenTime = $("#betweenTimeId").val();
		if($("#inlineCheckbox1").is(":checked")){
			inlineCheckbox1Value = "1";
		}
		if($("#inlineCheckbox2").is(":checked")){
			inlineCheckbox2Value = '1';
		}
		if($("#inlineCheckbox3").is(":checked")){
			inlineCheckbox3Value = '1';
		}
		var url = '<%=path%>'+"/getStockMessList?calendarValue=" + calendarValue + "&inlineCheckbox1Value="+inlineCheckbox1Value+"&inlineCheckbox2Value="+inlineCheckbox2Value+"&inlineCheckbox3Value="+inlineCheckbox3Value+"&inlineCheckbox4Value="+inlineCheckbox4Value+"&betweenTime="+betweenTime;
		$.ajax({
		   type: "POST",
		   url: url,
		   data: "",
		   success: function(msg){
		     var jsonObj = eval("("+msg+")");
		     if(jsonObj.ifSuccess == "1"){
		    	 messList(jsonObj.allStockMess,jsonObj.realUpStockList,jsonObj.realDropStockList,inlineCheckbox4Value)
		     }else{
		    	 alert(jsonObj.messsage);
		     }
		   },
		   error:function(){
			   alert("请求异常");
		   }
		});
	}
	
	function messList(allStockMess,realUpStockList,realDropStockList,inlineCheckbox4Value){
		$("#allStockMessId").empty();
		$("#realUpStockId").empty();
		$("#realDropStockId").empty();
		if(allStockMess != null && allStockMess.length > 0){
			$("#allStockMessId").append("<caption> 大盘数据信息 </caption><thead><tr>"
					+"<th>涨停数</th>"
					+"<th>跌停数</th>"
					+"<th>股票数</th>"
					+"<th>停牌数</th>"
					+"<th>赚钱效应</th>"
					+"<th>封板率</th>"
					+"<th>第N条涨幅</th>"
					+"</tr></thead>");
			for(var i = 0 ; i < allStockMess.length ; i++){
				$("#allStockMessId").append("<tbody><tr>"
						+"<td>"+allStockMess[i].stockRiseNumber+allStockMess[i].stockRiseNumberFlag+"</td>"
						+"<td>"+allStockMess[i].stockDropNumber+allStockMess[i].stockDropNumberFlag+"</td>"
						+"<td>"+allStockMess[i].stockAllNumber+"</td>"
						+"<td>"+allStockMess[i].stockStopNumber+"</td>"
						+"<td>"+allStockMess[i].stockEarningEffect+allStockMess[i].stockEarningEffectFlag+"</td>"
						+"<td>"+allStockMess[i].stockSealingPlate+allStockMess[i].stockSealingPlateFlag+"</td>"
						+"<td>"+allStockMess[i].stockDropLastOf40+"</td>"
						+"</tr></tbody>"
						);
			}
			
		}
		if(realUpStockList != null && realUpStockList.length > 0){
			$("#realUpStockId").append("<caption> 真实涨停股票信息 </caption><thead><tr>"
					+"<th>股票代码</th>"
					+"<th>股票名称</th>"
					+"<th>收盘价格</th>"
					+"</tr></thead>");
			for(var i = 0 ; i < realUpStockList.length ; i++){
				$("#realUpStockId").append("<tbody><tr>"
						+"<td>"+realUpStockList[i].stockCode+"</td>"
						+"<td>"+realUpStockList[i].stockName+"</td>"
						+"<td>"+realUpStockList[i].currentPrice+"</td>"
						+"</tr></tbody>"
						);
			}
		}
		if(realDropStockList != null && realDropStockList.length > 0){
			$("#realDropStockId").append("<caption> 真实跌停股票信息 </caption><thead><tr>"
					+"<th>股票代码</th>"
					+"<th>股票名称</th>"
					+"<th>收盘价格</th>"
					+"</tr></thead>");
			for(var i = 0 ; i < realDropStockList.length ; i++){
				$("#realDropStockId").append("<tbody><tr>"
						+"<td>"+realDropStockList[i].stockCode+"</td>"
						+"<td>"+realDropStockList[i].stockName+"</td>"
						+"<td>"+realDropStockList[i].currentPrice+"</td>"
						+"</tr></tbody>"
						);
			}
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
							<li><a class="acolor" href="<%= path%>/stockmain/graileffect.jsp" id="stockStatistics">股票统计</a></li>
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
	<div class="container main"  style="background-color: #F0F0F0; font-size: 20px">
		<div class="panel panel-default">
		     <div class="panel-heading" >导入数据</div>
    		 <div class="panel-body">
		        <form class="form-inline" role="form" id="formId" name="formId" action="" enctype="multipart/form-data" method="post">
				  <div class="form-group">
				    <label class="sr-only" for="inputfile">文件输入</label>
				    <input type="file" id="file" name="file">
				  </div>
				  &nbsp;&nbsp;&nbsp;&nbsp;
				  <button type="button" class="btn btn-default" onclick="inputExcelFile();">上传</button>
				</form>
		    </div>
		    <div class="panel-heading" >统计数据</div>
		    <div class="panel-body">
		        <form class="form-inline" role="form" id="formId2" name="formId2">
				 选择时间： <input type="text" id="getCalendarId" class="calendar2" value="" onclick="getCalendar();" style="width:200px"/>
				  &nbsp;&nbsp;&nbsp;&nbsp;
				 <div class="checkbox">
				    <label class="checkbox-inline">
				     	<input type="checkbox" id="inlineCheckbox1" value="option1">大盘信息
				    </label>
				    <label class="checkbox-inline">
				     	间隔：<input type="text" id="betweenTimeId" value="1" style="width:40px">
				    </label>
				  	<label class="checkbox-inline">
				    	<input type="checkbox" id="inlineCheckbox2" value="option2">真实涨停
				    </label>
				    <label class="checkbox-inline">
				    	<input type="checkbox" id="inlineCheckbox3" value="option3">真实跌停
				    </label>
				    <label class="checkbox-inline">
				    	第N家涨幅:<input type="text" id="inlineCheckbox4" value="-40" style="width:50px">
				  	</label>
				  </div>
				  &nbsp;&nbsp;&nbsp;&nbsp;
				  <button type="button" class="btn btn-default" onclick="getStockMess();">提交</button>
				</form>
		    </div>
		</div>
		<div>
			<table id="allStockMessId" class="table"></table>
			<table id="realUpStockId" class="table"></table>
			<table id="realDropStockId" class="table"></table>
			
		</div>
	</div>
	
</body>
</html>