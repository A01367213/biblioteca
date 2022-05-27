	var xhr;
	
	
	function obtenerResultadoJson()
	{	
		var libros;
		var datos="";
		
		if(xhr.readyState == 4)
		{
			//document.getElementById("transaccion").innerHTML = xhr.responseText;
			
			libros = eval("("+xhr.responseText+")");
			//clientes = JSON.parse(xhr.responseText); //soporta Json3
			
			document.getElementById("operacion").innerHTML = xhr.responseText;
			//document.getElementById("transaccion").innerHTML = clientes;
			
			datos = "<table border=1>";
			for(i=0; i<libros.length; i++)
			{
				//datos = datos + clientes[i].nocta+"   "+clientes[i].nombre+"   "+clientes[i].nombre+"   "+clientes[i].saldo+"<br/>";
				datos = datos +"<tr><td>"+libros[i].titulo+"</td><td>"+libros[i].autor+"</td><td>"+libros[i].editorial+"</td></tr>";
			}
			datos = datos + "</table>";
			
			document.getElementById("operacion").innerHTML = datos;
			
		}
	}
	
	function establecerConexionConsultar(urlString)
	{
		xhr.open("get",urlString,true);
		//xhr.onreadystatechange=obtenerResultado;
		xhr.onreadystatechange=obtenerResultadoJson;
		xhr.send(null);
	}
	
	function obtenerResultado()
	{	
		if(xhr.readyState == 4)
		{
			document.getElementById("operacion").innerHTML = xhr.responseText;
		}
	}
	
	function establecerConexion(urlString)
	{
		xhr.open("get",urlString,true);
		xhr.onreadystatechange=obtenerResultado;
		xhr.send(null);
	}
	
	function consultarEdit()
	{
		// 1. Obtener el valor del Tipo de Cuenta del TextField correspondiente
		var edit = document.getElementById("editorial").value;
		
		// 2. Preparar el URL String
		var urlString = "../biblioteca/libro?bConsultarEdit=consultar&editorial="+edit;
		
		// 3. Crear el objeto de conexion
		xhr = new XMLHttpRequest();
		
		// 4. Establecer conexion y ejecutar transaccion
		establecerConexion(urlString);
	}

	function consultarTit()
	{
		// 1. Obtener el valor del Tipo de Cuenta del TextField correspondiente
		var tit = document.getElementById("titulo").value;
		
		// 2. Preparar el URL String
		var urlString = "../biblioteca/libro?bConsultarTit=consultar&titulo="+tit;
		
		// 3. Crear el objeto de conexion
		xhr = new XMLHttpRequest();
		
		// 4. Establecer conexion y ejecutar transaccion
		establecerConexion(urlString);
	}
	
	function consultar()
	{
		
		// 1. Preparar el URL String
		var urlString = "../biblioteca/libro?bConsultar=consultar";
		
		// 1. Crear el objeto de conexion
		xhr = new XMLHttpRequest();
		
		// 3. Establecer conexion y ejecutar transaccion
		establecerConexionConsultar(urlString);
	}
