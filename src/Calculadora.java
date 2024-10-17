public class Calculadora {

    public double soma(double valor1, double valor2) {
        return valor1 + valor2;
    }

    public double subtracao(double valor1, double valor2) {
        return valor1 - valor2;
    }

    public double multiplicacao(double valor1, double valor2) {
        return valor1 * valor2;
    }

    public double divisao(double valor1, double valor2) throws ArithmeticException {
        if (valor2 == 0) {
            throw new ArithmeticException("Divisão por zero");
        }
        return valor1 / valor2;
    }
}
