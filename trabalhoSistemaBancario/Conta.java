package trabalhoSistemaBancario;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Conta {
    private double saldo;
    private final String nome;
    private final Lock lock = new ReentrantLock();

    public Conta(double saldoInicial, String nome) {
        this.saldo = saldoInicial;
        this.nome = nome;
    }

    public void depositar(double valor) {
        lock.lock();
        try {
            saldo += valor;
        } finally {
            lock.unlock();
        }
    }

    public boolean sacar(double valor) {
        lock.lock();
        try {
            if (saldo >= valor) {
                saldo -= valor;
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    public double getSaldo() {
        lock.lock();
        try {
            return Math.round(saldo * 100.0) / 100.0;
        } finally {
            lock.unlock();
        }
    }

    public String getNome() {
        return nome;
    }
}
