package trabalhoSistemaBancario;

public class Sistema {
    public void iniciar() {
        Banco banco = new Banco();

        Funcionario carlos = new Funcionario(new Conta(0, "Carlos"), new Conta(0, "Investimento Carlos"), "Carlos", banco);
        Funcionario pedro = new Funcionario(new Conta(0, "Pedro"), new Conta(0, "Investimento Pedro"), "Pedro", banco);
        Funcionario andre = new Funcionario(new Conta(0, "Andre"), new Conta(0, "Investimento Andre"), "Andre", banco);
        Funcionario joao = new Funcionario(new Conta(0, "João"), new Conta(0, "Investimento João"), "João", banco);

        Loja loja1 = new Loja(new Conta(0, "Loja 1"), new Funcionario[] { carlos, pedro }, banco);
        Loja loja2 = new Loja(new Conta(0, "Loja 2"), new Funcionario[] { andre, joao }, banco);

        Loja[] lojas = { loja1, loja2 };

        String[] nomesClientes = { "Hugo", "Matheus", "Felix", "Martinez", "Bidon", "Coronado", "Carrillo", "Romero", "Yuri", "ClienteExtra" };

        Cliente[] clientes = new Cliente[nomesClientes.length];
        for (int i = 0; i < nomesClientes.length; i++) {
            Conta contaCliente = new Conta(2000, nomesClientes[i]);
            clientes[i] = new Cliente(contaCliente, lojas, banco, nomesClientes[i]);
            clientes[i].start();
        }

        carlos.start();
        pedro.start();
        andre.start();
        joao.start();

        try {
            for (Cliente cliente : clientes) {
                cliente.join();
            }

            carlos.join();
            pedro.join();
            andre.join();
            joao.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
