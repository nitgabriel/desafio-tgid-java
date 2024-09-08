package br.nitgabriel.tgid.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Empresa {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String cnpj;

    private String nome;

    private Double saldo;

    private Double taxaSistema;

    public Empresa() {
    }

    public Empresa(Long id, String cnpj) {
        this.id = id;
        this.cnpj = cnpj;
    }

    public Empresa(Long id, String cnpj, String nome, Double saldo) {
        this.id = id;
        this.cnpj = cnpj;
        this.nome = nome;
        this.saldo = saldo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Double getTaxaSistema() {
        return taxaSistema;
    }

    public void setTaxaSistema(Double taxaSistema) {
        this.taxaSistema = taxaSistema;
    }
}