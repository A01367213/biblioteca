import javax.servlet.http.*;

import java.io.*;

public class ServletLibro extends HttpServlet{

	//Atributos
	private BiblioADjdbc biblio = new BiblioADjdbc();
	//Metodos

	private String obtenerDatos(HttpServletRequest request){
		String titulo, autor, editorial, datos;

		titulo = request.getParameter("titulo");
		autor = request.getParameter("autor");
		editorial = request.getParameter("editorial");

		datos = titulo+"_"+autor+"_"+editorial;

		return datos;
	}

	private void respuestaServer(HttpServletResponse response, String datosJson) throws IOException{
		PrintWriter salidaServer = response.getWriter();
		response.setContentType("text/plain");

		salidaServer.println(datosJson);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{

		String transaccion, datos, respuesta;

		// 1 Checar transacción
		
		//transaccion = request.getParameter("boton");

		// 2 Transacción Capturar:
		if (request.getParameter("bCapturar") != null){

			// 2.1 Obtener datos del URL String
			datos = obtenerDatos(request);

			// 2.2 Capturar datos en BD
			respuesta = biblio.capturar(datos);

			// 2.3 Enviar al server el resultado de la transaccion
			response.sendRedirect("RespuestaServer.jsp?datos="+respuesta);
		}

		if (request.getParameter("bConsultar") != null){
			
			//1 Consultar datos de la BD
			datos=biblio.consultarLibros();

			//2 Mostrar los datos
			//response.sendRedirect("RespuestaServer.jsp?datos="+datos);
			respuestaServer(response,datos);
		}

		if (request.getParameter("bConsultarEdit") != null){

			//Obtener tipo de cuenta a consultar
			String edit = request.getParameter("editorial");

			//Consultar cliente con ese tipo de cuenta
			datos=biblio.consultarEditorial(edit);

			//Mostrar datos
			response.sendRedirect("RespuestaServerTabla.jsp?datos="+datos);
		}

		if (request.getParameter("bConsultarTit") != null){
			String tit = request.getParameter("titulo");

			datos=biblio.consultarTitulo(tit);
			response.sendRedirect("RespuestaServerTabla.jsp?datos="+datos);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		doGet(request, response);
	}
}