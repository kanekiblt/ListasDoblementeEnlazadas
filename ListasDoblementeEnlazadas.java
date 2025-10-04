package ListaDoblementeEnlazadas;
/*
 * Nombre: Boyer Espinola Antony
 * Codugo: 0202314008
 * Tema: Lista Doblemente Enlazada
 * Fecha: 26/09/2023
 */



import java.util.Scanner;

public class listaDoblementeEnlazada {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        int primero = 2, fin = 4, dispo = 1;
        int[] sig = {3, -1, 5, 0, -1, 4, -1, 6};
        int[] ant = {-1, 0, -1, 6, -1, -1, -1, 2};
        String[] dato = {" ", " ", "B", "G", "E", " ", " ", "C"};

        int star_sig = primero;
        int star_ant = fin;
        boolean salir = false;

        String menus = String.format("""
                |-------------------------------|
                |            MENU               |
                |-------------------------------|
                | 1. Recorrido SIGUIENTE        |
                | 2. Recorrido ANTERIOR         |
                | 3. INSERCION                  |
                | 4. ELIMINAR                   |
                | 5. SALIR                      |
                |-------------------------------|
                """);

        while (!salir) {
            System.out.println(menus);
            System.out.print("Elige una opción: ");
            String opcion = entrada.next();
            if (!opcion.matches("\\d+")) {
                System.out.println("⚠ Error: Debes ingresar un número válido.");
                continue;
            }
            int opc = Integer.parseInt(opcion);

            switch (opc) {
                case 1 -> {
                    System.out.print("Recorrido SIGUIENTE: ");
                    int actual = star_sig;
                    while (actual != -1) {
                        System.out.print(" " + dato[actual] + " ");
                        actual = sig[actual];
                    }
                    System.out.println();
                }
                case 2 -> {
                    System.out.print("Recorrido ANTERIOR: ");
                    int actual = star_ant;
                    while (actual != -1) {
                        System.out.print(" " + dato[actual] + " ");
                        actual = ant[actual];
                    }
                    System.out.println();
                }
                case 3 -> {
                    System.out.print("Valor a insertar: ");
                    String valor = entrada.next();
                    System.out.print("Posición (índice donde insertar después): ");
                    int lugar = entrada.nextInt();
                    if (dispo == -1) {
                        System.out.println("Lista Llena");
                    } else {
                        int nuevo = dispo;
                        dispo = sig[dispo];
                        dato[nuevo] = valor;
                        if (sig[lugar] == -1) {
                            sig[lugar] = nuevo;
                            ant[nuevo] = lugar;
                            sig[nuevo] = -1;
                            fin = nuevo;
                        } else {
                            int siguiente = sig[lugar];
                            sig[lugar] = nuevo;
                            ant[nuevo] = lugar;
                            sig[nuevo] = siguiente;
                            ant[siguiente] = nuevo;
                        }
                    }
                    mostrarDatos(dato, sig, ant);
                }
                case 4 -> {
                    System.out.print("Ingrese el valor a eliminar: ");
                    String valorEliminar = entrada.next();
                    boolean encontrado = false;
                    for (int i = 0; i < dato.length; i++) {
                        if (dato[i].equals(valorEliminar)) {
                            if (sig[i] != -1) {
                                ant[sig[i]] = ant[i];
                            }
                            if (ant[i] != -1) {
                                sig[ant[i]] = sig[i];
                            }
                            dato[i] = " ";
                            sig[i] = dispo;
                            dispo = i;
                            encontrado = true;
                            break;
                        }
                    }
                    if (encontrado) {
                        System.out.println("El valor '" + valorEliminar + "' ha sido eliminado.");
                    } else {
                        System.out.println("Valor no encontrado.");
                    }
                    mostrarDatos(dato, sig, ant);
                }
                case 5 -> {
                    System.out.println("ADIOS!!!");
                    salir = true;
                }
                default -> System.out.println("⚠ Opción inválida.");
            }
        }
    }

    public static void mostrarDatos(String[] dato, int[] sig, int[] ant) {
        System.out.println("   |DATO|SIG|ANT");
        for (int i = 0; i < dato.length; i++) {
            System.out.printf("%2d | %2s | %2d | %2d\n", i, dato[i], sig[i], ant[i]);
        }
    }
}
