<%
    String datos = request.getParameter("datos");
%>

<html>
  <head>
  	<title>WEB Server Tomcat</title>
  	<link rel="stylesheet" type="text/css" href="styles/styles.css">
  </head>
    <body class="body2">
        <hr>
        <table border=1><%= datos %></table>
    </body>
</html>