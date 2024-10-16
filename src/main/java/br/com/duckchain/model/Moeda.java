package br.com.duckchain.model;

public class Moeda {
    //atributos
    private int idMoeda;
    private String nome;
    private double cotacaoAtual;
    private double variacao24H;
    private double volume24H;

    //Construtor


    public Moeda(int idMoeda, String nome, double cotacaoAtual, double variacao24H, double volume24H) {
        this.idMoeda = idMoeda;
        this.nome = nome;
        this.cotacaoAtual = cotacaoAtual;
        this.variacao24H = variacao24H;
        this.volume24H = volume24H;
    }

    //Getter and setter

    public int getIdMoeda() {
        return idMoeda;
    }

    public void setIdMoeda(int idMoeda) {
        this.idMoeda = idMoeda;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getCotacaoAtual() {
        return cotacaoAtual;
    }

    public void setCotacaoAtual(double cotacaoAtual) {
        this.cotacaoAtual = cotacaoAtual;
    }

    public double getVariacao24H() {
        return variacao24H;
    }

    public void setVariacao24H(double variacao24H) {
        this.variacao24H = variacao24H;
    }

    public double getVolume24H() {
        return volume24H;
    }

    public void setVolume24H(double volume24H) {
        this.volume24H = volume24H;
    }
}
