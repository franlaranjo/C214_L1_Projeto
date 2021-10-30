/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.inatel.cdg.model;

/**
 * Classe com os atributos de ArtistaPalco que extends a classe Entidade
 *
 * @author Francielly
 */
public class ArtistaPalco extends Entidade {

    private Artista artista;
    private Palco palco;
    private String horarioArtista;

    /**
     *
     * @return Artista
     */
    public Artista getArtista() {
        return artista;
    }

    /**
     *
     * @param idArtista
     */
    public void setArtista(Artista idArtista) {
        this.artista = idArtista;
    }

    /**
     *
     * @return Palco
     */
    public Palco getPalco() {
        return palco;
    }

    /**
     *
     * @param idPalco
     */
    public void setPalco(Palco idPalco) {
        this.palco = idPalco;
    }

    /**
     *
     * @return string
     */
    public String getHorarioArtista() {
        return horarioArtista;
    }

    /**
     *
     * @param horarioArtista
     */
    public void setHorarioArtista(String horarioArtista) {
        this.horarioArtista = horarioArtista;
    }
}
