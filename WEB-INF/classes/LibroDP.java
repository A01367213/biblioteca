import java.util.*;

public class LibroDP
{
	private String titulo, autor, editorial;
	
	
	// Constructores
	public LibroDP()
	{
		this.titulo    = "";
		this.autor     = "";
		this.editorial = "";
	}
	
	public LibroDP(String datos)
	{
		StringTokenizer st = new StringTokenizer(datos,"_");
		
		this.titulo    = st.nextToken();
		this.autor     = st.nextToken();
		this.editorial = st.nextToken();
	}
	
	// Accesors (geter)
	public String getTitulo()
	{
		return this.titulo;
	}
	
	public String getAutor()
	{
		return this.autor;
	}
	
	public String getEditorial()
	{
		return this.editorial;
	}
	
	
	// Mutators (seter)
	public void setTitulo(String tit)
	{
		this.titulo = tit;
	}
	
	public void setAutor(String aut)
	{
		this.autor = aut;
	}
	
	public void setEditorial(String edit)
	{
		this.editorial = edit;
	}
	
	
	public String toString()
	{
		return this.titulo+"_"+this.autor+"_"+this.editorial;
	}
	
	public String toStringSql()
	{
		return "'"+this.titulo+"','"+this.autor+"','"+this.editorial+"'";
	}

	public String toStringHtml(){
		return "<tr><td>"+this.titulo+"</td><td>"+this.autor+"</td><td>"+this.editorial+"</td></tr>";
	}

	public String toStringJson()
	{
		return "{\"titulo\":\""+this.titulo+"\",\"autor\":\""+this.autor+"\",\"editorial\":\""+this.editorial+"\"}";
	}
}
