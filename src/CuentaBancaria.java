
class CuentaBancaria {
    private String titular;
    private double saldo;
    private String numeroCuenta;
    private double ahorros;

    public CuentaBancaria(String titular, double saldo, String numeroCuenta) {
        this.titular = titular;
        this.saldo = saldo;
        this.numeroCuenta = numeroCuenta;
        this.ahorros = 0;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }


    public double getAhorros() {
        return ahorros;
    }

    public void depositarDinero(double monto) {
        this.saldo += monto;
    }

    public boolean retirarDinero(double monto) {
        if (saldo >= monto) {
            this.saldo -= monto;
        } else {
            System.out.println("DINERO INSUFICIENTE.");
        }
        return false;
    }
    public double getSaldo() {
        return saldo;
    }

    public void transferirAhorros(double monto) {
        if (saldo >= monto) {
            this.saldo -= monto;
            this.ahorros += monto;
        } else {
            System.out.println("DINERO INSUFICIENTE PARA LA TRANSFERENCIA.");
        }
    }

    @Override
    public String toString() {
        return "CuentaBancaria{" + "titular='" + titular + '\'' + ", saldo=" + saldo + ", ahorros=" + ahorros + ", numeroCuenta='" + numeroCuenta + '\'' + '}';
    }
}
