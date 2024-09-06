package br.com.criptomoedas.model;

import java.time.LocalDateTime;

public class Transacao extends Conta {
    //atributos
    private int idTransacao;
    private int idMoeda;
    private LocalDateTime dataHora;
    private String tipoTransacao;
    private int quantidadeMoeda;
    private double precoMoeda;



    public Transacao(int idCarteira, int idUsuario, double saldoTotal, int numeroConta, int idTransacao, int idMoeda, LocalDateTime dataHora, String tipoTransacao, int quantidadeMoeda, double precoMoeda) {
        super(idCarteira, idUsuario, saldoTotal, numeroConta);
        this.idTransacao = idTransacao;
        this.idMoeda = idMoeda;
        this.dataHora = dataHora;
        this.tipoTransacao = tipoTransacao;
        this.quantidadeMoeda = quantidadeMoeda;
        this.precoMoeda = precoMoeda;
    }




    //getter and setter

    public int getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(int idTransacao) {
        this.idTransacao = idTransacao;
    }

    public int getIdMoeda() {
        return idMoeda;
    }

    public void setIdMoeda(int idMoeda) {
        this.idMoeda = idMoeda;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(String tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }

    public double getQuantidadeMoeda() {
        return quantidadeMoeda;
    }

    public void setQuantidadeMoeda(int quantidadeMoeda) {
        this.quantidadeMoeda = quantidadeMoeda;
    }

    public double getPrecoMoeda() {
        return precoMoeda;
    }

    public void setPrecoMoeda(double precoMoeda) {
        this.precoMoeda = precoMoeda;
    }
}
