import java.util.Scanner;

public class Calculadora {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            boolean continuar = true;
            
            System.out.println("Bienvenido a la calculadora interactiva");
            
            while (continuar) {
                System.out.println("\nSelecciona la operación:");
                System.out.println("1. Suma (+)");
                System.out.println("2. Resta (-)");
                System.out.println("3. Multiplicación (*)");
                System.out.println("4. División (/)");
                System.out.println("5. Salir");
                System.out.print("Opción: ");
                int opcion = sc.nextInt();
                
                if (opcion == 5) {
                    System.out.println("Gracias por usar la calculadora. ¡Adiós!");
                    break;
                }
                
                System.out.print("Ingresa el primer número: ");
                double num1 = sc.nextDouble();
                System.out.print("Ingresa el segundo número: ");
                double num2 = sc.nextDouble();
                
                double resultado;
                
                switch (opcion) {
                    case 1 -> {
                        resultado = num1 + num2;
                        System.out.println("Resultado: " + resultado);
                    }
                    case 2 -> {
                        resultado = num1 - num2;
                        System.out.println("Resultado: " + resultado);
                    }
                    case 3 -> {
                        resultado = num1 * num2;
                        System.out.println("Resultado: " + resultado);
                    }
                    case 4 -> {
                        if (num2 != 0) {
                            resultado = num1 / num2;
                            System.out.println("Resultado: " + resultado);
                        } else {
                            System.out.println("Error: No se puede dividir entre cero.");
                        }
                    }
                    default -> System.out.println("Opción no válida. Intenta de nuevo.");
                }
            }
        }
    }
}