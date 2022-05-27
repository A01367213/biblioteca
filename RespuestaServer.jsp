<%
    String datos = request.getParameter("datos");
%>

<html>
  <head>
  	<title>WEB Server Tomcat</title>
  	<link rel="stylesheet" type="text/css" href="styles/styles.css">

  </head>
    <body>
        <hr>
        <h2>Datos Capturados: <%= datos %></h2>
        <image align='center' alt='IMAGEN' src='images/hori.jpg'></image>
    </body>
</html>