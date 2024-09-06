package br.com.criptomoedas.model;

import java.time.LocalDateTime;

public class Transferencia extends Conta {
    private int idTransferencia;
    private int idContaDestino;
    private LocalDateTime dataHora;
    private double valorTransferido;

    public Transferencia(int idCarteira, int idUsuario, double saldoTotal, int numeroConta, int transferencia, int idContaDestino, LocalDateTime dataHora, double valorTransferido) {
        super(idCarteira, idUsuario, saldoTotal, numeroConta);
        this.idTransferencia = transferencia;
        this.idContaDestino = idContaDestino;
        this.dataHora = dataHora;
        this.valorTransferido = valorTransferido;
    }

    //metodo

    public void transferir(Conta contaDestino, double valorDeposito) {
        if(super.getSaldoTotal() < 0 || super.getSaldoTotal() < valorDeposito){
            throw new IllegalArgumentException("Você não possui saldo suficiente para essa transação");
        }
        double saldoAtual = super.getSaldoTotal() - valorDeposito;
        super.setSaldoTotal(saldoAtual);

        double novoSaldo = contaDestino.getSaldoTotal() + valorDeposito;
        contaDestino.setSaldoTotal(novoSaldo);

    }



    //getter and setter


    public int getIdTransferencia() {
        return idTransferencia;
    }

    public void setIdTransferencia(int idTransferencia) {
        this.idTransferencia = idTransferencia;
    }

    public int getIdContaDestino() {
        return idContaDestino;
    }

    public void setIdContaDestino(int idContaDestino) {
        this.idContaDestino = idContaDestino;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public double getValorTransferido() {
        return valorTransferido;
    }

    public void setValorTransferido(double valorTransferido) {
        this.valorTransferido = valorTransferido;
    }
}
