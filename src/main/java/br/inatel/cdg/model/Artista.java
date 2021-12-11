/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.inatel.cdg.model;

/**
 * Classe com os atributos de Artista que extends a classe Entidade
 *
 * @author Francielly
 */
public class Artista extends Entidade {

    private String nome;
    private String generoMusical;

    /**
     * @return String
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
     * @return string
     */

    public String getGeneroMusical() {
        return generoMusical;
    }

    /**
     *
     * @param generoMusical
     */
    public void setGeneroMusical(String generoMusical) {
        this.generoMusical = generoMusical;
    }

}
