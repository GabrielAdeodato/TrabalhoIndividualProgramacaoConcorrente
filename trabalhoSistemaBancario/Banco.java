package trabalhoSistemaBancario;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Banco {
    private final Lock lock = new ReentrantLock();

    public void transferir(Conta origem, Conta destino, double valor) {
        lock.lock();
        try {
            if (origem == null || origem.sacar(valor)) {
                destino.depositar(valor);
                if (origem != null) {
                    System.out.println("Transferência de R$ " + valor + " de " + origem.getNome() + " para " + destino.getNome());
                } else {
                    System.out.println("Transferência de R$ " + valor + " para " + destino.getNome());
                }
            }
        } finally {
            lock.unlock();
        }
    }

}
