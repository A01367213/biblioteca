import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.StringTokenizer;

public class LibroGUI extends JFrame implements ActionListener
{
    private JTextField tfTitulo, tfAutor, tfEditorial;
    private JButton    bCapturar, bConsultar, bConsultarEditorial, bConsultarTitulo, bSalir;
    private JTextArea  taDatos;
    private JPanel panel1, panel2;
    
    //private BiblioAD biblioad = new BiblioAD();
    private BiblioADjdbc biblioad = new BiblioADjdbc();
    
    public LibroGUI()
    {
        super("Biblioteca Tec");
        
        // 1. Crear objetos de los atributos
        tfTitulo    = new JTextField();
        tfAutor     = new JTextField();
        tfEditorial = new JTextField();
        bCapturar   = new JButton("Capturar datos");
        bConsultar  = new JButton("Consultar Libros");
        bConsultarEditorial = new JButton("Consultar Editorial");
        bConsultarTitulo = new JButton("Consultar Titulo");
        bSalir      = new JButton("Exit");
        taDatos     = new JTextArea(10,30);
        panel1      = new JPanel();
        panel2      = new JPanel();
        
        // 1.2 Adicionar addActionListener a los JButtons
        bCapturar.addActionListener(this);
        bConsultar.addActionListener(this);
        bConsultarEditorial.addActionListener(this);
        bConsultarTitulo.addActionListener(this);
        bSalir.addActionListener(this);
        
        // 2. Definir el Layout de los JPanels
        panel1.setLayout(new GridLayout(6,2));
        panel2.setLayout(new FlowLayout());
        
        // 3. Colocar los objetos en los JPanels
        panel1.add(new JLabel("TITULO: "));
        panel1.add(tfTitulo);
        panel1.add(new JLabel("AUTOR: "));
        panel1.add(tfAutor);
        panel1.add(new JLabel("EDITORIAL: "));
        panel1.add(tfEditorial);
        panel1.add(bCapturar);
        panel1.add(bConsultar);
        panel1.add(bConsultarEditorial);
        panel1.add(bConsultarTitulo);
        panel1.add(bSalir);
        
        panel2.add(panel1);
        panel2.add(new JScrollPane(taDatos));
        
        // 4. Adicionar los JPanels al JFrame
        add(panel2);
        
        // 5. Visualizar el JFrame
        setSize(400,400);
        setVisible(true);
    }
    
    private String obtenerDatos()
    {
        String datos="";
        
        String titulo    = tfTitulo.getText();
        String autor     = tfAutor.getText();
        String editorial = tfEditorial.getText();
        
        if(titulo.equals("") || autor.isEmpty() || editorial.isEmpty())
            datos = "VACIO";
        else
            datos = titulo+"_"+autor+"_"+editorial;
        
        return datos;
    }
    
    private void desplegar(String datos)
    {
        StringTokenizer st = new StringTokenizer(datos,"_");
        
        tfTitulo.setText(st.nextToken());
        tfAutor.setText(st.nextToken());
        tfEditorial.setText(st.nextToken());
    }
    
    public void actionPerformed(ActionEvent e)
    {
        String datos, resultado;
        
        if(e.getSource() == bCapturar)
        {
            // 1. Obtener los datos de los JTextFields
            datos = obtenerDatos();
            
            // 2. Capturar los datos a traves del objeto BiblioAD
            if(datos.equals("VACIO"))
                resultado = "Algun campo esta vacio...";
            else
                resultado = biblioad.capturar(datos);
            
            // 3. Desplegar el resultado de la transaccion
            
            taDatos.setText(resultado);
        }
        
        if(e.getSource() == bConsultar)
        {
            datos = biblioad.consultarLibros();
            
            taDatos.setText(datos);
        }
        
        
        if(e.getSource() == bConsultarEditorial)
        {
            String edit = tfEditorial.getText();
            
            datos = biblioad.consultarEditorial(edit.trim());
            
            taDatos.setText(datos);
        }
        
        if(e.getSource() == bConsultarTitulo)
        {
            String tit = tfTitulo.getText();
            
            datos = biblioad.consultarTitulo(tit);
            
            if(datos.equals("NOT_FOUND"))
                taDatos.setText("No se localizo el Libro: "+tit);
            else
            {
                taDatos.setText(datos);
                desplegar(datos);
            }
        }
        
        if(e.getSource() == bSalir)
        {
            System.exit(0);
        }
    }
    
    public static void main(String args[])
    {
        new LibroGUI();
    }
}

