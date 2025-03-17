import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        ArrayList<CuentaBancaria> lstBanco = new ArrayList<>();
        boolean estado = true;
        int op;
        double monto;
        int depositosRealizados = 0;
        int retirosRealizados = 0;
        double totalRetirado = 0;

        //
        // ACTUALIZACIONES APLICACIÓN BANCARIA
        //
        // SE AÑADIERON LA SIGUIENTES OPCIONES:
        //
        // CUENTA DE AHORROS
        // TRANSFERENCIA A CUENTA DE AHORROS
        // VER CUENTA DE AHORROS
        // LIMITE DE DEPOSITO (10 POR SESIÓN)
        // LIMITE DE RETIRO (10 POR SESIÓN | 2.500.000 PESOS)
        // IMPUESTO 4 X 1000
        //

        do {
            System.out.println("""
                    
                    |======================================|
                    |     [💰]    MENÚ BANCARIO    [💰]    |
                    |======================================|
                    | 📋 - [1] CREAR CUENTA                |
                    | 📜 - [2] VER CUENTAS CREADAS         |
                    | 📄 - [3] VER INFORMACIÓN DE CUENTA   |
                    | 📬 - [4] DEPOSITAR DINERO            |
                    | 🧨 - [5] RETIRAR DINERO              |
                    | 🎳 - [6] TRANSFERIR A AHORROS        |
                    | 💻 - [7] VER AHORROS                 |
                    | 🔄 - [8] TRANSFERENCIA ENTRE CUENTAS |
                    |======================================|
                    | 🚫 - [9] SALIR DE LA APLICACIÓN      |
                    | 📝 - [10] INFORMACIÓN DEL MENÚ       |
                    |======================================|
                    
                    """);
            op = teclado.nextInt();
            teclado.nextLine();

            switch (op) {
                case 1 -> {
                    System.out.print("INGRESE NOMBRE DEL TITULAR: ");
                    String titular = teclado.nextLine();

                    System.out.print("INGRESE SU SALDO DE CUENTA: ");
                    double saldo = teclado.nextDouble();
                    teclado.nextLine();

                    System.out.print("INGRESE EL NÚMERO DE CUENTA: ");
                    String numeroCuenta = teclado.nextLine();

                    lstBanco.add(new CuentaBancaria(titular, saldo, numeroCuenta));
                    System.out.println("CUENTA CREADA ¡EXITOSAMENTE!...");
                } case 2 -> {
                    for (CuentaBancaria cu : lstBanco) {
                        System.out.println(cu);
                    }
                } case 3 -> {
                    System.out.print("INGRESE SU NÚMERO DE CUENTA: ");
                    String nCuenta = teclado.nextLine();
                    boolean encontrada = false;

                    for (CuentaBancaria cu : lstBanco) {
                        if (cu.getNumeroCuenta().equalsIgnoreCase(nCuenta)) {
                            System.out.println(cu);
                            encontrada = true;
                            break;
                        }
                    }

                    if (!encontrada) {
                        System.out.println("CUENTA NO ENCONTRADA...");
                    }
                } case 4 -> {
                    if (depositosRealizados >= 10) {
                        System.out.println("HA ALCANZADO EL LÍMITE DE DEPÓSITOS.");
                        break;
                    }
                    System.out.print("INGRESE SU NÚMERO DE CUENTA: ");
                    String depositar = teclado.nextLine();
                    for (CuentaBancaria cu : lstBanco) {
                        if (cu.getNumeroCuenta().equalsIgnoreCase(depositar)) {
                            do {
                                System.out.print("¿CUÁNTO DINERO DESEA DEPOSITAR?: ");
                                monto = teclado.nextDouble();
                                teclado.nextLine();

                                if (monto <= 0) {
                                    System.out.println("NO PUEDE DEPOSITAR 0 PESOS NI UN VALOR NEGATIVO. INTENTE NUEVAMENTE.");
                                }
                            } while (monto <= 0);

                            double impuesto = monto * 0.004;
                            cu.depositarDinero(monto - impuesto);
                            depositosRealizados++;
                            System.out.println("HA DEPOSITADO: " + monto + " | - 4x1000: " + impuesto);
                            break;
                        }
                    }
                } case 5 -> {
                    if (retirosRealizados >= 10 || totalRetirado >= 2500000) {
                        System.out.println("NO PUEDE REALIZAR MÁS RETIROS EN ESTA SESIÓN.");
                        break;
                    }
                    System.out.print("INGRESE SU NÚMERO DE CUENTA: ");
                    String retirar = teclado.nextLine();
                    for (CuentaBancaria cu : lstBanco) {
                        if (cu.getNumeroCuenta().equalsIgnoreCase(retirar)) {
                            do {
                                System.out.print("¿CUÁNTO DINERO DESEA RETIRAR?: ");
                                monto = teclado.nextDouble();
                                teclado.nextLine();

                                if (monto <= 0) {
                                    System.out.println("NO PUEDE RETIRAR 0 PESOS NI UN VALOR NEGATIVO. INTENTE NUEVAMENTE.");
                                }
                            } while (monto <= 0);

                            if (totalRetirado + monto > 2500000) {
                                System.out.println("EXCEDE EL LÍMITE DE RETIRO POR SESIÓN.");
                                break;
                            }

                            double impuesto = monto * 0.004;
                            cu.retirarDinero(monto + impuesto);
                            retirosRealizados++;
                            totalRetirado += monto;
                            System.out.println("HA RETIRADO: " + monto + " | - 4x1000: " + impuesto);
                            break;
                        }
                    }
                }
                case 6 -> {
                    System.out.print("INGRESE SU NÚMERO DE CUENTA: ");
                    String cuenta = teclado.nextLine();
                    for (CuentaBancaria cu : lstBanco) {
                        if (cu.getNumeroCuenta().equalsIgnoreCase(cuenta)) {
                            System.out.print("¿CUANTO DINERO DESEA TRANSFERIR A AHORROS? ");
                            monto = teclado.nextInt();
                            teclado.nextLine();
                            cu.transferirAhorros(monto);
                            System.out.println("TRANSFERENCIA EXITOSA A AHORROS: " + monto);
                            break;
                        }
                    }
                } case 7 -> {
                    System.out.print("INGRESE SU NÚMERO DE CUENTA: ");
                    String cuenta = teclado.nextLine();
                    for (CuentaBancaria cu : lstBanco) {
                        if (cu.getNumeroCuenta().equalsIgnoreCase(cuenta)) {
                            System.out.println("AHORROS DISPONIBLES: " + cu.getAhorros());
                            break;
                        }
                    }
                }case 8 -> {
                    System.out.print("INGRESE SU NÚMERO DE CUENTA ORIGEN: ");
                    String cuentaOrigen = teclado.nextLine();
                    System.out.print("INGRESE SU NÚMERO DE CUENTA DESTINO: ");
                    String cuentaDestino = teclado.nextLine();

                    CuentaBancaria c1 = null;
                    CuentaBancaria c2 = null;

                    for (CuentaBancaria cu : lstBanco) {
                        if (cu.getNumeroCuenta().equalsIgnoreCase(cuentaOrigen)) {
                            c1 = cu;
                        } else if (cu.getNumeroCuenta().equalsIgnoreCase(cuentaDestino)) {
                            c2 = cu;
                        }
                        if (c1 != null && c2 != null) break;
                    }

                    if (c1 == null || c2 == null) {
                        System.out.println("UNA DE LAS CUENTAS NO EXISTE.");
                        break;
                    }

                    System.out.print("INGRESE MONTO A TRANSFERIR: ");
                    monto = teclado.nextDouble();
                    teclado.nextLine();

                    if (monto <= 0 || !c1.retirarDinero(monto)) {
                        System.out.println("FONDOS INSUFICIENTES O MONTO INVÁLIDO.");
                        break;
                    }

                    c2.depositarDinero(monto);
                    System.out.println("TRANSFERENCIA EXITOSA: " + monto);
                } case 9 -> {
                    estado = false;
                    System.out.println("SALIENDO DEL SISTEMA...");
                } case 10 -> {
                    System.out.println("""
                            INFORMACIÓN DEL MENÚ
                            
                            CREADO POR:
                            JUAN DAVID RUGE GARZÓN
                            
                            OPCIONES:
                            CREACIÓN Y ADMON DE CUENTAS
                            RETIRO Y DEPOSITO
                            SERVICIO CUENTA DE AHORRO
                            TRANSFERENCIA ENTRE CUENTAS
                            
                            LIMITACIÓN DE RETIROS
                            IMPUESTO 4 X 1000
                            """);
                }
                default -> System.out.println("OPCIÓN ¡INVALIDA!");
            }
        } while (estado);
    }
}
