/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.inatel.cdg.model;

/**
 * Classe com os atributos de Ingresso que extends a classe Entidade
 *
 * @author Francielly
 */
public class Ingresso extends Entidade {

    private String categoria;
    private Evento evento;
    private Participante participante;

    /**
     *
     * @return string
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     *
     * @param categoria
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     *
     * @return evento
     */
    public Evento getEvento() {
        return evento;
    }

    /**
     *
     * @param evento
     */
    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    /**
     *
     * @return participante
     */
    public Participante getParticipante() {
        return participante;
    }

    /**
     *
     * @param participante
     */
    public void setParticipante(Participante participante) {
        this.participante = participante;
    }
}
