/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.inatel.cdg.model;

import java.time.LocalDate;

/**
 * Classe com os atributos de Evento que extends a classe Entidade
 *
 * @author Francielly
 */
public class Evento extends Entidade {

    private String nome;
    private LocalDate data;
    private Palco palco;

    /**
     *
     * @return palco
     */
    public Palco getPalco() {
        return palco;
    }

    /**
     *
     * @param palco
     */
    public void setPalco(Palco palco) {
        this.palco = palco;
    }

    /**
     *
     * @return string
     */
    public String getNome() {
        return nome;
    }

    /**
     *
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     *
     * @return localdate
     */
    public LocalDate getData() {
        return data;
    }

    /**
     *
     * @param data
     */
    public void setData(LocalDate data) {
        this.data = data;
    }
}
