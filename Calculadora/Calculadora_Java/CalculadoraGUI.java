import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CalculadoraGUI extends JFrame implements ActionListener {
    private final JTextField pantalla;
    private String operador1 = "";
    private String operador2 = "";
    private String operacion = "";

    public CalculadoraGUI() {
        // Configuración de la ventana
        setTitle("Calculadora");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Pantalla
        pantalla = new JTextField();
        pantalla.setEditable(false);
        pantalla.setHorizontalAlignment(JTextField.RIGHT);
        pantalla.setFont(new Font("Arial", Font.BOLD, 24));
        add(pantalla, BorderLayout.NORTH);

        // Panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(4, 4, 5, 5));

        String[] botones = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };

        for (String text : botones) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.BOLD, 20));
            btn.addActionListener(e -> actionPerformed(e));
            panelBotones.add(btn);
        }

        add(panelBotones, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if ("0123456789".contains(comando)) {
            pantalla.setText(pantalla.getText() + comando);
        } else if ("/*-+".contains(comando)) {
            operador1 = pantalla.getText();
            operacion = comando;
            pantalla.setText("");
        } else if (comando.equals("=")) {
            operador2 = pantalla.getText();
            try {
                double num1 = Double.parseDouble(operador1);
                double num2 = Double.parseDouble(operador2);
                double resultado = 0;

                switch (operacion) {
                    case "+" -> resultado = num1 + num2;
                    case "-" -> resultado = num1 - num2;
                    case "*" -> resultado = num1 * num2;
                    case "/" -> {
                        if (num2 != 0) resultado = num1 / num2;
                        else {
                            JOptionPane.showMessageDialog(this, "Error: División entre cero");
                            pantalla.setText("");
                            return;
                        }
                    }
                }

                pantalla.setText(String.valueOf(resultado));
            } catch (NumberFormatException ex) {
                pantalla.setText("");
            }
        } else if (comando.equals("C")) {
            pantalla.setText("");
            operador1 = "";
            operador2 = "";
            operacion = "";
        }
    }

    public static void main(String[] args) {
        CalculadoraGUI calculadora = new CalculadoraGUI();
    }
}