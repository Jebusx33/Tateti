package Logica;

import java.awt.Color;
import java.awt.Font;
//import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Jesus Arias
 */
public class Tateti extends JFrame implements ActionListener {

    JButton iniciar;
    JButton reset;
    JButton tablero[][];
    String Jugador1, Jugador2;
    int turno = -1;
    JLabel mensaje;
    JLabel puntO;
    JLabel puntX;
    Color colorB;
    private final JTextField puntosO;
    private final JTextField puntosX;
    private int countX;
    private int countO;
    private String numeroO;

    public Tateti() {
        this.setLayout(null);
        //JTextField Y JLabel para los puntos de O    
        puntO = new JLabel("Puntos O");
        puntO.setBounds(80, 15, 60, 30);
        this.add(puntO);
        puntosO = new JTextField("");
        puntosO.setBounds(80, 40, 42, 30);
        this.add(puntosO);
        //JTextField Y JLabel para los puntos de X
        puntX = new JLabel("Puntos X");
        puntX.setBounds(270, 15, 60, 30);
        this.add(puntX);
        puntosX = new JTextField("");
        puntosX.setBounds(280, 40, 42, 30);
        this.add(puntosX);
        //Etiqueta bienvenido e indica de quien es el turno
        mensaje = new JLabel("Bien venido a Tateti");
        mensaje.setBounds(150, 40, 310, 30);
        this.add(mensaje);
        //JButton Resetea el juego pero dejalos puntos del JTextField  
        reset = new JButton("Reset");
        reset.setBounds(210, 325, 110, 30);
        reset.addActionListener(this);
        this.add(reset);
        //JButton inicia un juego nuevo y limpia los puntos del JTextField  
        iniciar = new JButton("Juego Nuevo");
        iniciar.setBounds(80, 325, 130, 30);
        iniciar.addActionListener(this);
        this.add(iniciar);
        tablero = new JButton[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = new JButton();
                tablero[i][j].setBounds((i + 1) * 80, (j + 1) * 80, 80, 80);
                this.add(tablero[i][j]);
                tablero[i][j].addActionListener(this);
                //Accion reset para el Jbotton 
                reset.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        resetActionPerformed(evt);
                    }

                    private void resetActionPerformed(ActionEvent evt) {
                        reset();
                        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
            }
        }
        colorB = tablero[0][0].getBackground();
    }

    public static void main(String[] args) {
        //Crea la ventana
        Tateti ventana = new Tateti();
        ventana.setDefaultCloseOperation(3);
        ventana.setSize(400, 400);
        ventana.setLocationRelativeTo(null);
        ventana.setResizable(false);
        ventana.setTitle("Tateti");
        ventana.setVisible(true);
    }

    //Accion para que seleccione los turnos y si hay alguna X,O
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == iniciar) {
            turno = 0;
            JOptionPane.showMessageDialog(this, "Iniciar el Juego");
            // Jugador1=JOptionPane.showInputDialog(this,"Nombre del Jugador 1");
            Jugador1 = "O";
            //Jugador2=JOptionPane.showInputDialog(this,"Nombre del Jugador 2");
            Jugador2 = "X";
            mensaje.setText("Turno del Jugador " + Jugador1);
            limpiar();
        } else {
            JButton boton = (JButton) e.getSource();
            Font fuente = new Font("Dialog", Font.BOLD, 34);
            boton.setFont(fuente);
            if (turno == 0) {
                if (boton.getText().equals("")) {
                    boton.setBackground(Color.blue);
                    boton.setText("O");
                    boton.setEnabled(false);
                    turno = 1;
                    mensaje.setText("Turno del jugaror " + Jugador2);
                }
            } else {
                if (turno == 1) {
                    if (boton.getText().equals("")) {
                        boton.setBackground(Color.red);
                        boton.setText("X");
                        boton.setEnabled(false);
                        turno = 0;
                        mensaje.setText("Turno del jugaror " + Jugador1);
                    }
                }
            }
            verificar();
        }
    }

    //Verifica si hay Tateti
    public void verificar() {
        int ganador = 0;
        for (int i = 0; i < 3; i++) {
            if (tablero[0][i].getText().equals("O") && tablero[1][i].getText().equals("O")
                    && tablero[2][i].getText().equals("O")) {
                ganador = 1;
            }
            if (tablero[i][0].getText().equals("O") && tablero[i][1].getText().equals("O")
                    && tablero[i][2].getText().equals("O")) {
                ganador = 1;
            }
        }
        if (tablero[0][0].getText().equals("O") && tablero[1][1].getText().equals("O")
                && tablero[2][2].getText().equals("O")) {
            ganador = 1;
        }
        if (tablero[0][2].getText().equals("O") && tablero[1][1].getText().equals("O")
                && tablero[2][0].getText().equals("O")) {
            ganador = 1;
        }
        for (int i = 0; i < 3; i++) {
            if (tablero[0][i].getText().equals("X") && tablero[1][i].getText().equals("X")
                    && tablero[2][i].getText().equals("X")) {
                ganador = 2;
            }
            if (tablero[i][0].getText().equals("X") && tablero[i][1].getText().equals("X")
                    && tablero[i][2].getText().equals("X")) {
                ganador = 2;
            }
        }
        if (tablero[0][0].getText().equals("X") && tablero[1][1].getText().equals("X")
                && tablero[2][2].getText().equals("X")) {
            ganador = 2;
        }
        if (tablero[0][2].getText().equals("X") && tablero[1][1].getText().equals("X")
                && tablero[2][0].getText().equals("X")) {
            ganador = 2;
        }
        //Indica quien gano y acumuladorde puntos
        if (ganador == 1) {
            JOptionPane.showMessageDialog(this, "Gano " + Jugador1);
            bloquear();
            countO++;
            String numeroO = String.valueOf(countO);
            puntosO.setText(numeroO);
        }
        if (ganador == 2) {
            JOptionPane.showMessageDialog(this, "Gano " + Jugador2);
            bloquear();
            countX++;
            String numeroX = String.valueOf(countX);
            puntosX.setText(numeroX);
        }
    }

    //Bloquea los cuadros cuando se forme el Tateti
    public void bloquear() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j].setEnabled(false);
            }
        }
    }
//resete el tablero pero deja los puntos

    public void reset() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j].setEnabled(true);
                tablero[i][j].setText("");
                tablero[i][j].setBackground(colorB);
            }
        }
    }

    public void limpiar() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j].setEnabled(true);
                tablero[i][j].setText("");
                tablero[i][j].setBackground(colorB);
                countO = 0;
                countX = 0;
                puntosO.setText("");
                puntosX.setText("");
            }
        }
    }
}
