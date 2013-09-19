<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>ERROR 500</title>
</head>
<body>
	<h2>ERROR 500 - Error en servidor</h2>
	<%= request.getAttribute("javax.servlet.error.exception")%>
	<br />
	<%= request.getAttribute("javax.servlet.error.request_uri")%>
</body>
</html>