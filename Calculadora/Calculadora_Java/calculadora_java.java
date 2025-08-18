import java.util.Scanner;

public class calculadora_java {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            double num1, num2;
            char operacion;
            System.out.println("Bienvenido a la calculadora simple en Java");
            // Pedimos el primer número
            System.out.print("Ingresa el primer número: ");
            num1 = sc.nextDouble();
            // Pedimos la operación
            System.out.print("Ingresa la operación (+, -, *, /): ");
            operacion = sc.next().charAt(0);
            // Pedimos el segundo número
            System.out.print("Ingresa el segundo número: ");
            num2 = sc.nextDouble();
            double resultado;
             // Calculamos según la operación
            switch (operacion) {
                case '+' -> resultado = num1 + num2;
                case '-' -> resultado = num1 - num2;
                case '*' -> resultado = num1 * num2;
                case '/' -> {
                    if (num2 != 0) {
                        resultado = num1 / num2;
                    } else {
                        System.out.println("Error: No se puede dividir entre cero.");
                        sc.close();
                        return;
                    }
                }
                default -> {
                    System.out.println("Operación no válida.");
                    sc.close();
                    return;
                }
            }
System.out.println("El resultado es: " + resultado);
        }
    }
}