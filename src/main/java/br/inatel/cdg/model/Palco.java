/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.inatel.cdg.model;

/**
 * Classe com os atributos de Palco que extends a classe Entidade
 *
 * @author Francielly
 */
public class Palco extends Entidade {

    private String nome;

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
}
