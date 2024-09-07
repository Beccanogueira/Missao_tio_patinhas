package br.com.criptomoedas.model;

public abstract class Conta {
    //atributos
    private int idConta;
    private int idUsuario;
    private double saldoTotal;
    private int numeroConta;

    //Construtor


    public Conta(int idConta, int idUsuario, double saldoTotal, int numeroConta) {
        this.idConta = idConta;
        this.idUsuario = idUsuario;
        this.saldoTotal = saldoTotal;
        this.numeroConta = numeroConta;
    }

    public String solicitarTransferencia (Conta contaSolicitacao, double valorSolicitacao){
        if(valorSolicitacao <= 0) {
            throw new IllegalArgumentException("Valor inválido");
        }
        return "Foi solicitado o valor de " + valorSolicitacao + " reais para a conta " + contaSolicitacao.getNumeroConta();

    }

    public void addSaldo (double valorDeposito) {
       if(valorDeposito <= 0){
           throw new IllegalArgumentException("Valor depositado inválido");
        }
       double saldoAtual = getSaldoTotal() + valorDeposito;
       setSaldoTotal(saldoAtual);
    }

    public void removeSaldo (double valorTransacao) {
        if (valorTransacao <= 0 || getSaldoTotal() < saldoTotal ) {
            throw new IllegalArgumentException("Valor de transação inválido");
        }
        double saldoAtual = getSaldoTotal() - valorTransacao;
        setSaldoTotal(saldoAtual);
    }


    //getter and setter

    public int getIdConta() {
        return idConta;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public double getSaldoTotal() {
        return saldoTotal;
    }

    public void setSaldoTotal(double saldoTotal) {
        this.saldoTotal = saldoTotal;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }
}
