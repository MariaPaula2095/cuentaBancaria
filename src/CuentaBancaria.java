public class CuentaBancaria {
    private String titular;
    private double saldo;
    private String numeroCuenta;
    private int retirosRealizados;

    //Metodos

    public CuentaBancaria() {
    }

    public CuentaBancaria(String titular, double saldo, String numeroCuenta) {
        this.titular = titular;
        this.saldo = saldo;
        this.numeroCuenta = numeroCuenta;
        this.retirosRealizados=0;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getRetirosRealizados() {return retirosRealizados;}

    public String getNumeroCuenta() {return numeroCuenta;}

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    @Override
    public String toString() {
        return "CuentaBancaria{" +
                "titular='" + titular + '\'' +
                ", saldo=" + saldo +
                ", numeroCuenta='" + numeroCuenta + '\'' +
                ", Retiros='" + retirosRealizados + '\'' +
                '}';
    }

    //Metodos propios
    public void depositarDinero(double monto){
        this.saldo += monto;
    }
    public boolean retirarDinero(double monto) {

        if (retirosRealizados >= 2) {
            System.out.println("NO PUEDE REALIZAR MAS DE 2 RETIROS.");
            return false;
        }

        if (saldo >= monto) {
            saldo -= monto;
            retirosRealizados++;
            System.out.println(" RETIRO EXITOSO. NUEVO SALDO: $" + saldo);
            return true;
        } else {
            System.out.println("FONDOS INSUFICIENTES");
            return false;
        }
    }


    public void mostrarInfo(){
        System.out.println("El nombre del titular es: "+ this.titular);
        System.out.println("El saldo de la cuenta es: "+ this.saldo);
        System.out.println("El numero de cuenta es: "+ this.numeroCuenta);
    }

}
