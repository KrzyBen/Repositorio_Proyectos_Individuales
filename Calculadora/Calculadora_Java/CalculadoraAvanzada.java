import java.awt.*;
import java.awt.event.*;
import java.util.StringTokenizer;
import javax.swing.*;

public class CalculadoraAvanzada extends JFrame implements ActionListener {
    private final JTextField pantalla;
    private final StringBuilder entrada = new StringBuilder();
    private boolean resultadoMostrado = false; 
    private final JComboBox<String> historial; // <- historial de operaciones

    public CalculadoraAvanzada() {
        setTitle("Calculadora Avanzada");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Pantalla
        pantalla = new JTextField();
        pantalla.setEditable(false);
        pantalla.setHorizontalAlignment(JTextField.RIGHT);
        pantalla.setFont(new Font("Arial", Font.BOLD, 24));
        add(pantalla, BorderLayout.NORTH);

        // Historial (debajo de la pantalla)
        historial = new JComboBox<>();
        historial.setFont(new Font("Arial", Font.PLAIN, 14));
        historial.setEditable(false);
        add(historial, BorderLayout.SOUTH);

        // Panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(5, 4, 5, 5));

        String[] botones = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C", "←"
        };

        for (String text : botones) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.BOLD, 20));
            btn.addActionListener(this);
            panelBotones.add(btn);
        }

        add(panelBotones, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        switch (comando) {
            case "C" -> {
                entrada.setLength(0);
                pantalla.setText("");
                resultadoMostrado = false;
            }

            case "←" -> {
                if (entrada.length() > 0) {
                    entrada.deleteCharAt(entrada.length() - 1);
                    pantalla.setText(entrada.toString());
                }
                resultadoMostrado = false;
            }

            case "=" -> {
                try {
                    double resultado = evaluarExpresion(entrada.toString());
                    String resultadoStr = formatoNumero(resultado);

                    // Mostrar en pantalla
                    pantalla.setText(resultadoStr);

                    // Guardar en historial
                    historial.addItem(entrada.toString() + " = " + resultadoStr);

                    // Preparar nueva operación
                    entrada.setLength(0);
                    entrada.append(resultadoStr);
                    resultadoMostrado = true;
                } catch (Exception ex) {
                    pantalla.setText("Error");
                    entrada.setLength(0);
                    resultadoMostrado = false;
                }
            }

            default -> {
                if (resultadoMostrado && "0123456789.".contains(comando)) {
                    entrada.setLength(0);
                    pantalla.setText("");
                    resultadoMostrado = false;
                }
                if (resultadoMostrado && "+-*/".contains(comando)) {
                    resultadoMostrado = false;
                }

                entrada.append(comando);
                pantalla.setText(entrada.toString());
            }
        }
    }

    // Evaluación básica izquierda a derecha (sin prioridad de operadores)
    private double evaluarExpresion(String expresion) {
        StringTokenizer tokens = new StringTokenizer(expresion, "+-*/", true);
        double resultado = Double.parseDouble(tokens.nextToken());

        while (tokens.hasMoreTokens()) {
            String operador = tokens.nextToken();
            double numero = Double.parseDouble(tokens.nextToken());

            switch (operador) {
                case "+" -> resultado += numero;
                case "-" -> resultado -= numero;
                case "*" -> resultado *= numero;
                case "/" -> {
                    if (numero == 0) throw new ArithmeticException("División por cero");
                    resultado /= numero;
                }
            }
        }
        return resultado;
    }

    // Formato bonito: sin ".0" si es entero
    private String formatoNumero(double num) {
        if (num == (long) num) {
            return String.valueOf((long) num);
        } else {
            return String.valueOf(num);
        }
    }

    public static void main(String[] args) {
        new CalculadoraAvanzada();
    }
}