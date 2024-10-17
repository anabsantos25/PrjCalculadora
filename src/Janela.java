import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Janela extends JFrame {
    private JTextField campoValor1;
    private JTextField campoValor2;
    private JTextField campoResultado;
    private Calculadora calculadora;

    public Janela() {
        calculadora = new Calculadora();

        setTitle("Calculadora Básica");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        // Criação dos componentes
        JLabel labelValor1 = new JLabel("Valor 1:");
        campoValor1 = new JTextField();

        JLabel labelValor2 = new JLabel("Valor 2:");
        campoValor2 = new JTextField();

        JLabel labelResultado = new JLabel("Resultado:");
        campoResultado = new JTextField();
        campoResultado.setEditable(false);  // campo de resultado não editável

        // Botões de operações
        JButton botaoSoma = new JButton("Somar");
        JButton botaoSubtracao = new JButton("Subtrair");
        JButton botaoMultiplicacao = new JButton("Multiplicar");
        JButton botaoDivisao = new JButton("Dividir");

        // Ações dos botões
        botaoSoma.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calcularSoma();
            }
        });

        botaoSubtracao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calcularSubtracao();
            }
        });

        botaoMultiplicacao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calcularMultiplicacao();
            }
        });

        botaoDivisao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calcularDivisao();
            }
        });

        // Adicionando componentes à janela
        add(labelValor1);
        add(campoValor1);
        add(labelValor2);
        add(campoValor2);
        add(labelResultado);
        add(campoResultado);
        add(botaoSoma);
        add(botaoSubtracao);
        add(botaoMultiplicacao);
        add(botaoDivisao);

        setVisible(true);
    }

    // Métodos de cálculo
    private void calcularSoma() {
        double valor1 = Double.parseDouble(campoValor1.getText());
        double valor2 = Double.parseDouble(campoValor2.getText());
        double resultado = calculadora.soma(valor1, valor2);
        campoResultado.setText(String.valueOf(resultado));
    }

    private void calcularSubtracao() {
        double valor1 = Double.parseDouble(campoValor1.getText());
        double valor2 = Double.parseDouble(campoValor2.getText());
        double resultado = calculadora.subtracao(valor1, valor2);
        campoResultado.setText(String.valueOf(resultado));
    }

    private void calcularMultiplicacao() {
        double valor1 = Double.parseDouble(campoValor1.getText());
        double valor2 = Double.parseDouble(campoValor2.getText());
        double resultado = calculadora.multiplicacao(valor1, valor2);
        campoResultado.setText(String.valueOf(resultado));
    }

    private void calcularDivisao() {
        try {
            double valor1 = Double.parseDouble(campoValor1.getText());
            double valor2 = Double.parseDouble(campoValor2.getText());
            double resultado = calculadora.divisao(valor1, valor2);
            campoResultado.setText(String.valueOf(resultado));
        } catch (ArithmeticException e) {
            campoResultado.setText("0");
            JOptionPane.showMessageDialog(this, "Erro: Não é possível dividir por zero.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new Janela();
    }
}
