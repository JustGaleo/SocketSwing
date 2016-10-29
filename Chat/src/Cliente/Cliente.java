package Cliente;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class Cliente extends JFrame {

    private static final long serialVersionUID = 1583724102189855698L;

    JTextField pantalla, pantalla2, pantalla3;

    double resultado;

    String operacion;

    JPanel panelNumeros, panelOperaciones;

    boolean nuevaOperacion = true;

    public Cliente() {
        super();
        setSize(600, 400);
        setTitle("SOCKET CLIENTE");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(true);


        JPanel panel = (JPanel) this.getContentPane();
        panel.setLayout(new BorderLayout());

        pantalla = new JTextField("ip", 20);
        pantalla.setBorder(new EmptyBorder(4, 4, 4, 4));
        pantalla.setFont(new Font("Arial", Font.BOLD, 25));
        pantalla.setHorizontalAlignment(JTextField.LEFT);
        pantalla.setEditable(true);
        pantalla.setBackground(Color.WHITE);
        panel.add("North", pantalla);

        pantalla2 = new JTextField("Puerto:", 20);
        pantalla2.setBorder(new EmptyBorder(4, 4, 4, 4));
        pantalla2.setFont(new Font("Arial", Font.BOLD, 25));
        pantalla2.setHorizontalAlignment(JTextField.LEFT);
        pantalla2.setEditable(true);
        pantalla2.setBackground(Color.WHITE);
        panel.add("Center", pantalla2);
        
        pantalla3 = new JTextField("Usuario:", 20);
        pantalla3.setBorder(new EmptyBorder(4, 4, 4, 4));
        pantalla3.setFont(new Font("Arial", Font.BOLD, 25));
        pantalla3.setHorizontalAlignment(JTextField.LEFT);
        pantalla3.setEditable(false);
        pantalla3.setBackground(Color.WHITE);
        panel.add("South", pantalla3);
        
        panelOperaciones = new JPanel();
        panelOperaciones.setLayout(new GridLayout(6, 1));
        panelOperaciones.setBorder(new EmptyBorder(4, 4, 4, 4));
        nuevoBotonOperacion("conectar");

        panel.add("East", panelOperaciones);
        validate();
    }

    private void nuevoBotonOperacion(String operacion) {
        JButton btn = new JButton(operacion);
        btn.setForeground(Color.RED);

        btn.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent evt) {
                JButton btn = (JButton) evt.getSource();
                operacionPulsado(btn.getText());
            }
        });

        panelOperaciones.add(btn);
    }

    private void operacionPulsado(String tecla) {
        if (tecla.equals("conectar")) {
            calcularResultado();
        }

        nuevaOperacion = true;
    }


 
    private void calcularResultado() {
        if (operacion.equals("conectar")) {
            try {
                Socket sk = new Socket(pantalla.getText(), Integer.parseInt(pantalla2.getText()));
                BufferedReader entrada = new BufferedReader(
                        new InputStreamReader(sk.getInputStream()));
                PrintWriter salida = new PrintWriter(
                        new OutputStreamWriter(sk.getOutputStream()), true);
            
            } catch (Exception e) {
                 JOptionPane.showMessageDialog(null,"error");
            }
        }

      
        
    }
}
