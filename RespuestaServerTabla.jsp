<%
    String datos = request.getParameter("datos");
%>

<html>
  <head><title>WEB Server Tomcat</title></head>
    <body>
        <h1>BD Banco</h1><p>
        <hr>
        <table border=1><%= datos %></table>
    </body>
</html>