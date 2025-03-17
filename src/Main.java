import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        int op;
        double monto;
        boolean estado=true; //para el while

        List<CuentaBancaria> lstBanco = new ArrayList<>();

        /*                                   AJUSTES REALIZADOS AL CODIGO
        * - CASE 1: SE ASEGURA QUE EL SALDO NO SEAN VALORES NEGATIVOS DEBEN SER >=0
        *
        * - CASE 3: SE AÑADIO UN SOUT PARA EL USUARIO EN CASO TAL QUE LA CUENTA NO SEA ENCONTRADA
        *
        * - CASE 4: EN VEZ DE USAR METODO (for) USE (stream) DECIDI USARLO PORQUE ES UNA FORMA MAS
        *           ACTUAL Y SE USA MENOS CODIGO, ES MAS SENCILLO DE ENTENDER AUNQUE EL (for) TAMBIEN
        *           ES BUENO PERO PARA RECORRER DE FORMA GENERAL EL (stream) ES MAS RAPIDO Y ESPECIFICO.
        *           - USE stream PARA BUSCAR LA CUENTA
        *           - SI LA ENCUENTRA EL USUARIO INDICA EL MONTO NO PUEDE SER NEGATIVO EN CASO DE NO
        *             ENCONTRAR LA CUENTA UN SOUT LE INFORMA AL USUARIO
        *
        * - CASE 5: USE DE IGUAL FORMA (stream) PARA ENCONTRAR LA CUENTA
        *           PARA LOS LIMITES DEL RETIRO CREE UN NUEVO ATRIBUTO (retirosRealizados) Y ESTE LO USE CON
        *           EL METODO (retirarDinero) CONTROLA QUE EL USUARIO NO PUEDA HACER MAS DE 2 RETIROS
        * */

do {

        System.out.println("""
                1. CREAR CLIENTE
                2. MOSTRAR TODO
                3. MOSTRAR INFORMACION DE UNA CUENTA ESPECIFICA
                4. DEPOSITAR DINERO
                5. RETIRAR DINERO
                6. MOSTRAR DATOS DESDE LA LISTA
                7. SALIR
                """);
        op= teclado.nextInt();
        switch (op) {
            case 1 -> {
                System.out.println("INGRESE NOMBRE TITULAR: ");
                String titular = teclado.next();

                double saldo;
                do {
                    System.out.println("INGRESE SALDO DE LA CUENTA (Debe ser mayor o igual a 0): ");
                    saldo = teclado.nextDouble();
                } while (saldo < 0);

                System.out.println("INGRESE NUMERO DE LA CUENTA: ");
                String numeroCuenta = teclado.next();

                CuentaBancaria nuevaCuenta = new CuentaBancaria(titular, saldo, numeroCuenta);
                lstBanco.add(nuevaCuenta);

                System.out.println("¡SU CUENTA HA SIDO CREADA!");
            }
            case 2 -> {
                for (CuentaBancaria cu : lstBanco) { //imprime cada usuario por separado
                    System.out.println(cu);
                }
                //System.out.println(lstBanco); lo imprime en fila
            }
            case 3 -> {
                System.out.println("INGRESE SU NUMERO DE CUENTA ");
                String nCuenta = teclado.next();

                CuentaBancaria cuenta = lstBanco.stream()
                        .filter(c -> c.getNumeroCuenta().equalsIgnoreCase(nCuenta))
                        .findFirst()
                        .orElse(null);

                if (cuenta != null) {
                    System.out.println(cuenta);
                } else {
                    System.out.println("CUENTA NO ENCONTRADA.");
                }
            }
            case 4 -> {
                System.out.println("DEPOSITAR DINERO");

                System.out.println("INGRESE SU NUMERO DE CUENTA: ");
                String nCuenta = teclado.next();

                // Buscar la cuenta
                CuentaBancaria cuenta = lstBanco.stream()
                        .filter(c -> c.getNumeroCuenta().equalsIgnoreCase(nCuenta))
                        .findFirst()
                        .orElse(null);

                if (cuenta != null) { // Si la cuenta existe

                    do {
                        System.out.println("CUANTO DINERO DESEA DEPOSITAR:");
                        monto = teclado.nextDouble();
                    } while (monto <= 0); // No permite montos negativos

                    cuenta.depositarDinero(monto);
                    System.out.println("DEPÓSITO EXITOSO. NUEVO SALDO: " + cuenta.getSaldo());
                } else {
                    System.out.println("CUENTA NO ENCONTRADA.");
                }
            }


            case 5 -> {
                System.out.println("RETIRAR DINERO");

                System.out.println("INGRESE SU NUMERO DE CUENTA: ");
                String nCuenta = teclado.next();

                CuentaBancaria cuenta = lstBanco.stream()
                        .filter(c -> c.getNumeroCuenta().equalsIgnoreCase(nCuenta))
                        .findFirst()
                        .orElse(null);

                if (cuenta != null) {

                    if (cuenta.getRetirosRealizados() >= 2) {
                        System.out.println("HA ALCANZADO EL LIMITE DE RETIROS");
                    } else {

                        do {
                            System.out.println("CUANTO DINERO DESEA RETIRAR:");
                            monto = teclado.nextDouble();
                        } while (monto <= 0);

                        cuenta.retirarDinero(monto);
                    }

                } else {
                    System.out.println("CUENTA NO ENCONTRADA.");
                }
            }

            case 6 -> {
                System.out.println("MOSTRAR DATOS DESDE LA CUENTA DESDE LA LISTA");
                for (CuentaBancaria cu : lstBanco) {
                    System.out.println(cu);
                }

            }
            case 7 -> {
                System.out.println("SALIR");
                estado = false;
            }
            default -> {
                System.out.println("LA OPCION NO ES VALIDA");
            }


        }



}while (estado);
    }
}



/*
do { //Creo un do-while para que me deje en el menu
        System.out.println("""
        1. DEPOSITAR DINERO
        2. RETIRAR DINERO
        3. MOSTRAR DATOS CUENTA
        4. MOSTRAR DATOS DESDE LA LISTA
        5. SALIR
         """);

        op= teclado.nextInt();

        switch (op){
            case 1 -> {
                System.out.println("DEPOSITAR");

                do {
                    System.out.println("CUANTO DINERO DESEA DEPOSITAR");
                    monto= teclado.nextDouble();
                }while (monto<=0);

                cl1.depositarDinero(monto);
            }
            case 2 ->{
                System.out.println("RETIRAR");
                do {
                    System.out.println("CUANTO DINERO DESEA RETIRAR");
                    monto= teclado.nextDouble();
                }while (monto<=0);
                cl1.retirarDinero(monto);
            }
            case 3 -> {
                System.out.println("MOSTRAR DATOS DE LA CUENTA");
                cl1.mostrarInfo();
            }
            case 4 ->{
                System.out.println("MOSTRAR DATOS DESDE LA CUENTA DESDE LA LISTA");
                for (CuentaBancaria cu : lstBanco){
                    System.out.println(cu);
                }

            }
            case 5 ->{
                System.out.println("SALIR");
                estado= false;
            }
            default -> {
                System.out.println("LA OPCION NO ES VALIDA");
            }


        }
}while (estado);

 */

