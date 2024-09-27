package trabalhoSistemaBancario;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Loja {
    private final Conta conta;
    private final Funcionario[] funcionarios;
    private final Banco banco;
    private final Lock lock = new ReentrantLock();
    private boolean[] funcionariosPagos;

    public Loja(Conta conta, Funcionario[] funcionarios, Banco banco) {
        this.conta = conta;
        this.funcionarios = funcionarios;
        this.banco = banco;
        this.funcionariosPagos = new boolean[funcionarios.length];
    }

    public Conta getConta() {
        return conta;
    }

    public void pagarFuncionarios() {
        lock.lock();
        try {
            for (int i = 0; i < funcionarios.length; i++) {
                if (conta.getSaldo() >= 1400 && !funcionariosPagos[i]) {
                	funcionariosPagos[i] = true;
                    banco.transferir(conta, funcionarios[i].getContaSalario(), 1400);
                    funcionarios[i].receberSalario(1400);
                    System.out.println("Funcionário " + funcionarios[i].getNome() + " foi pago com R$ 1400.");      
                    break;
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public void receberPagamento(double valor) {
        lock.lock();
        try {
            banco.transferir(null, conta, valor);
            System.out.println("Loja recebeu pagamento de R$ " + valor + ". Saldo atual: R$ " + conta.getSaldo());
            pagarFuncionarios();
        } finally {
            lock.unlock();
        }
    }

}
