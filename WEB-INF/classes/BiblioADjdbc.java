import java.io.*;
import java.sql.*;
import java.util.StringTokenizer;

import com.cedarsoftware.util.io.JsonWriter;

public class BiblioADjdbc
{
    private Connection conexion;
    private Statement  statement;
 
    private LibroDP librodp;
    
    public BiblioADjdbc()
    {
    	try
    	{
    		Class.forName("com.mysql.jdbc.Driver").newInstance();
    		//Class.forName("com.mysql.jc.jdbc.Driver").newInstance();
    		conexion = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca?user=root");
    		
    		System.out.println("Conexion exitosa a la BD ...");	
    	}
    	catch(ClassNotFoundException cnfe)
    	{
    		System.out.println("Error Class.forName(): "+cnfe);
    	}
    	catch(InstantiationException ie)
    	{
    		System.out.println("Error al crear la Instancia: "+ie);
    	}
    	catch(IllegalAccessException iae)
    	{
    		System.out.println("Error IllegalAccess: "+iae);
    	}
    	catch(SQLException sqle)
    	{
    			System.out.println("Error Sql Exception: "+sqle);
    	}
    	
    }
    
    public String capturar(String datos)
    {
        String respuesta;
        String strInsert, tit, aut, edit;
        StringTokenizer st;
        
        librodp = new LibroDP(datos);
        
      /*st = new StringTokenizer(datos,"_");
        tit = st.nextToken();
        aut = st.nextToken();
        edit = st.nextToken();
        */
        //strInsert = "INSERT INTO Libro VALUES('FISICA','RESNICK','CECSA')";
        //strInsert = "INSERT INTO Libro VALUES('"+tit+"','"+aut+"','"+edit+"')";
        
        strInsert = "INSERT INTO Libro VALUES("+librodp.toStringSql()+")";
        
        try
        {
            // 1. Abrir el archivo de datos
            //archivoOut = new PrintWriter(new FileWriter("Libros.txt",true));
        	statement = conexion.createStatement();
        	
            // 2. Capturar los datos en el archivo
            //archivoOut.println(datos);
            statement.executeUpdate(strInsert);
            
            // 3. Cerrar archivo
            //archivoOut.close();
            statement.close();
            
            respuesta = "Captura correcta... ";
            
            System.out.println(strInsert);
        }
        catch(SQLException ioe)
        {
            respuesta = "Error en captura: "+ioe;
            System.out.println("Error: "+ioe);
        }
        
        return respuesta;
    }
    
    public String consultarLibros()
    {
        String datos="";
        ResultSet tr;
        
        String query = "SELECT * FROM Libro";
        
        librodp = new LibroDP();
        
        try
        {
            // 1. Abrir el archivo para leer los datos
            //archivoIn = new BufferedReader(new FileReader("Libros.txt"));
        	statement = conexion.createStatement();
        	
        	// Ejecutar Query
        	tr = statement.executeQuery(query);
        	
            // 2. Procesar los datos
            /*while(archivoIn.ready())
            {
                datos = datos + archivoIn.readLine() + "\n";
            }*/

            datos = "[";
            
            while(tr.next())
            {
            	//datos = datos + tr.getString("titulo")+"*"+tr.getString(2)+"*"+tr.getString("editorial")+"\n";
            	librodp.setTitulo(tr.getString("titulo"));
            	librodp.setAutor(tr.getString(2));
            	librodp.setEditorial(tr.getString("editorial"));
            	
            	//datos = datos + librodp.toString() + "\n";
                datos = datos + JsonWriter.objectToJson(librodp) + ","; 
            }

            datos = datos.substring(0,datos.length()-1);
            datos = datos + "]";
            
            // 3. Cerrar el archivo
            //archivoIn.close();
            statement.close();
            
            System.out.println(query);
        }
        catch(SQLException ioe)
        {
            datos = "Error en consultar: "+ioe;
            System.out.println("Error: "+ioe);
        }
        
        return datos;
    }
    
    public String consultarEditorial(String edit)
    {
        String datos="";
        ResultSet tr;
        boolean encontrado=false;
        
        String query = "SELECT * FROM Libro WHERE editorial='"+edit+"'";
        
        librodp = new LibroDP();
        
        try
        {
            // 1. Abrir el archivo para leer los datos
            //archivoIn = new BufferedReader(new FileReader("Libros.txt"));
            statement = conexion.createStatement();
            
            // Ejecutar Query
            tr = statement.executeQuery(query);
            
            // 2. Procesar los datos
            /*while(archivoIn.ready())
             {
             datos = datos + archivoIn.readLine() + "\n";
             }*/
            
            while(tr.next())
            {
                //datos = datos + tr.getString("titulo")+"*"+tr.getString(2)+"*"+tr.getString("editorial")+"\n";
                librodp.setTitulo(tr.getString("titulo"));
                librodp.setAutor(tr.getString(2));
                librodp.setEditorial(tr.getString("editorial"));
                
                datos = datos + librodp.toStringHtml() + "\n";
                
                encontrado = true;
            }
            
            // 3. Cerrar el archivo
            //archivoIn.close();
            statement.close();
            
            if(!encontrado)
                datos = "NOT_FOUND";
            
            System.out.println(query);
        }
        catch(SQLException ioe)
        {
            datos = "Error en consultar: "+ioe;
            System.out.println("Error: "+ioe);
        }

        if (!encontrado) datos = "No se encontro la editorial "+edit;
        
        return datos;
    }
    
    public String consultarTitulo(String tit)
    {
        String datos="";
        ResultSet tr;
        boolean encontrado=false;
        
        String query = "SELECT * FROM Libro WHERE titulo='"+tit+"'";
        
        librodp = new LibroDP();
        
        try
        {
            // 1. Abrir el archivo para leer los datos
            //archivoIn = new BufferedReader(new FileReader("Libros.txt"));
            statement = conexion.createStatement();
            
            // Ejecutar Query
            tr = statement.executeQuery(query);
            
            // 2. Procesar los datos
            /*while(archivoIn.ready())
             {
             datos = datos + archivoIn.readLine() + "\n";
             }*/
            
            while(tr.next())
            {
                //datos = datos + tr.getString("titulo")+"*"+tr.getString(2)+"*"+tr.getString("editorial")+"\n";
                librodp.setTitulo(tr.getString("titulo"));
                librodp.setAutor(tr.getString(2));
                librodp.setEditorial(tr.getString("editorial"));
                
                datos = datos + librodp.toStringHtml() + "\n";
                
                encontrado = true;
            }
            
            // 3. Cerrar el archivo
            //archivoIn.close();
            statement.close();
            
            if(!encontrado)
                datos = "No se encontro el libro "+tit;
            
            System.out.println(query);
        }
        catch(SQLException ioe)
        {
            datos = "Error en consultar: "+ioe;
            System.out.println("Error: "+ioe);
        }
        
        return datos;
    }

}







