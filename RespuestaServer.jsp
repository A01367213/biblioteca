<%
    String datos = request.getParameter("datos");
%>

<html>
  <head><title>WEB Server Tomcat</title></head>
    <body>
        <h1>BD Banco</h1><p>
        <hr>
        <h2>Datos Capturados: <%= datos %></h2>
    </body>
</html>