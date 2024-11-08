package br.com.duckchain.model;

import java.time.LocalDateTime;

public class Transferencia {

    private int id;
    private int idContaOrigem;
    private int idContaDestino;
    private double valor;
    private int idUsuario;
    private LocalDateTime dataTransferencia;

    public Transferencia(int id, int idContaOrigem, int idContaDestino, double valor, int idUsuario, LocalDateTime dataTransferencia) {
        this.id = id;
        this.idContaOrigem = idContaOrigem;
        this.idContaDestino = idContaDestino;
        this.valor = valor;
        this.idUsuario = idUsuario;
        this.dataTransferencia = dataTransferencia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdContaOrigem() {
        return idContaOrigem;
    }

    public void setIdContaOrigem(int idContaOrigem) {
        this.idContaOrigem = idContaOrigem;
    }

    public int getIdContaDestino() {
        return idContaDestino;
    }

    public void setIdContaDestino(int idContaDestino) {
        this.idContaDestino = idContaDestino;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public LocalDateTime getDataTransferencia() {
        return dataTransferencia;
    }

    public void setDataTransferencia(LocalDateTime dataTransferencia) {
        this.dataTransferencia = dataTransferencia;
    }

    public String getIdTransferencia() {
        return null;
    }
}