package br.com.duckchain.model;

import java.time.LocalDateTime;

public class Transacao {
    //atributos
    private int id;
    private int idConta;
    private LocalDateTime dataHora;
    private String tipoTransacao;
    private double valor;
    private String descricao;
    private int idUsuario;
    private int idMoeda;

    public Transacao(int id, int idConta, LocalDateTime dataHora, String tipoTransacao, double valor, String descricao, int idUsuario, int idMoeda) {
        this.id = id;
        this.idConta = idConta;
        this.dataHora = dataHora;
        this.tipoTransacao = tipoTransacao;
        this.valor = valor;
        this.descricao = descricao;
        this.idUsuario = idUsuario;
        this.idMoeda = idMoeda;
    }

    public Transacao(int idConta, int idUsuario, double saldoTotal, int numeroConta, int idTransacao, int idMoeda, LocalDateTime dataHora, String tipoTransacao, int quantidadeMoeda, double precoMoeda) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdConta() {
        return idConta;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdMoeda() {
        return idMoeda;
    }

    public void setIdMoeda(int idMoeda) {
        this.idMoeda = idMoeda;
    }

    public String getIdTransacao() {
        return null;
    }
}