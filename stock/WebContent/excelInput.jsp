<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<% String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>

<body>
	<form action="<%=path %>/ReceiveFromServlet" enctype="multipart/form-data" method="post">
		选择上传的文件：<input type="file" name="file"><br>
		<input type="submit" value="提交">
	</form>
	
</body>
</html>