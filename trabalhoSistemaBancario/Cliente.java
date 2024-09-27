package trabalhoSistemaBancario;

import java.util.Random;

public class Cliente extends Thread {
    private final Conta conta;
    private final Loja[] lojas;
    private final Banco banco;
    private final String nome;
    private final Random random = new Random();

    public Cliente(Conta conta, Loja[] lojas, Banco banco, String nome) {
        this.conta = conta;
        this.lojas = lojas;
        this.banco = banco;
        this.nome = nome;
    }

    @Override
    public void run() {
        for (Loja loja : lojas) {
            for (int i = 0; i < 2; i++) {
                double valorCompra = 200 + random.nextDouble() * 300;
                banco.transferir(conta, loja.getConta(), valorCompra);
                loja.receberPagamento(valorCompra);
                System.out.println(nome + " fez uma compra de R$ " + valorCompra + " na " + loja.getConta().getNome());
            }
        }
    }
}
