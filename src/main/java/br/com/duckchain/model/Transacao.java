package br.com.duckchain.model;

import java.time.LocalDateTime;

public class Transacao extends Conta {
    //atributos
    private int id;
    private int idConta;
    private LocalDateTime dataHora;
    private String tipoTransacao;
    private double valor;
    private String descricao;
    private int idUsuario;
    private int idMoeda;


}