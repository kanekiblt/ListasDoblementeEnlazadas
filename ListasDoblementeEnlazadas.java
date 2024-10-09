
import java.util.Scanner;

public class Lista {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int primero = 3, fin = 12, dispo = 2;
        int[] num = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        int[] sig = {-1, 12, 4, 6, 5, 7, 10, 8, 9, 11, 1, 0, -1};
        int[] ant = {0, 10, 0, -1, 0, 0, 3, 0, 0, 0, 6, 0, 1};
        String[] dato = {" ", "L", " ", "B", " ", " ", "E", " ", " ", " ", "H", " ", "P"};
        int star_sig = 3, star_ant = 12;
        boolean salir = false;

        while (!salir) {
            System.out.println("\nMENU: \n [1] Recorrido SIGUIENTE \n [2] Recorrido ANTERIOR \n [3] INSERCION \n [4] ELIMINAR \n [5] SALIR\n");
            int opc = entrada.nextInt();

            switch (opc) {
                case 1: {
                    System.out.print("Recorrido SIGUIENTE: "); 
                    while (star_sig != -1) {
                        System.out.print(" " + dato[star_sig] + " ");
                        star_sig = sig[star_sig];
                    }
                    break;
                }
                case 2: {
                    System.out.print("Recorrido ANTERIOR: ");
                    while (star_ant != -1) {
                        System.out.print(" " + dato[star_ant] + " ");
                        star_ant = ant[star_ant];
                    }
                    break;
                }
                case 3: {
                    System.out.print("Recorrido SIGUIENTE: ");
                    while (star_sig != -1) {
                        System.out.print(" " + dato[star_sig] + " ");
                        star_sig = sig[star_sig];
                    }
                    System.out.print("\nRecorrido ANTERIOR: ");
                    while (star_ant != -1) {
                        System.out.print(" " + dato[star_ant] + " ");
                        star_ant = ant[star_ant];
                    }
                    System.out.print("\n VALOR: ");
                    String valor = entrada.next();
                    System.out.print(" LUGAR: ");
                    int lugar = entrada.nextInt();

                    if (dispo == -1) {
                        System.out.println("Lista Llena");
                    } else {
                        int nuevo = dispo;
                        dispo = sig[dispo];
                        dato[nuevo] = valor;

                        if (primero == fin && dato[primero].equals("")) {
                            sig[nuevo] = sig[lugar];
                            ant[nuevo] = ant[lugar];
                        } else {
                            if (primero == fin || sig[lugar] == -1) {
                                int auxsig = sig[lugar];
                                sig[lugar] = nuevo;
                                sig[nuevo] = auxsig;
                                ant[nuevo] = lugar;
                                fin = nuevo;
                            } else {
                                if (dato[lugar].compareTo(valor) > 0) {
                                    int auxsig = sig[lugar];
                                    int auxant = ant[sig[lugar]];
                                    sig[nuevo] = auxant;
                                    ant[nuevo] = ant[lugar];
                                    sig[lugar] = auxsig;
                                    ant[lugar] = nuevo;
                                    ant[sig[lugar]] = lugar;
                                    primero = nuevo;
                                } else {
                                    int auxsig = sig[lugar];
                                    int auxant = ant[sig[lugar]];
                                    ant[sig[lugar]] = nuevo;
                                    sig[lugar] = nuevo;
                                    sig[nuevo] = auxsig;
                                    ant[nuevo] = auxant;
                                }
                            }
                        }
                    }
                    star_sig = num[primero];
                    star_ant = num[fin];
                    mostrarDatos(dato, sig, ant);
                    break;
                }
                case 4: { 
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
                    break;
                }
                case 5: {
                    System.out.println("ADIOS!!!");
                    salir = true;
                    break;
                }
            }
        }
    }

    public static void mostrarDatos(String[] dato, int[] sig, int[] ant) {
        System.out.println("   |DATO|SIG|ANT");
        for (int i = 0; i <= 12; i++) {
            if (i < 10) {
                System.out.print(" " + i + " | " + dato[i] + " | " + sig[i] + " | " + ant[i] + "\n");
            } else {
                System.out.print(i + " | " + dato[i] + " | " + sig[i] + " | " + ant[i] + "\n");
            }
        }
    }
}
