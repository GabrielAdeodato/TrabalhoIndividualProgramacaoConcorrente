package trabalhoSistemaBancario;

public class Funcionario extends Thread {
    private final Conta contaSalario;
    private final Conta contaInvestimento;
    private final String nome;
    private final Banco banco;

    public Funcionario(Conta contaSalario, Conta contaInvestimento, String nome, Banco banco) {
        this.contaSalario = contaSalario;
        this.contaInvestimento = contaInvestimento;
        this.nome = nome;
        this.banco = banco;
    }

    public Conta getContaSalario() {
        return contaSalario;
    }

    public void receberSalario(double salario) {
        banco.transferir(null, contaSalario, salario); 
        double investimento = Math.round(salario * 0.2 * 100.0) / 100.0;
        banco.transferir(contaSalario, contaInvestimento, investimento);
        System.out.println(nome + " recebeu um salário de R$ " + salario + " e investiu R$ " + investimento);
    }


    public String getNome() {
        return nome;
    }
}
