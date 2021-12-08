package br.inatel.cdg.controller;

import br.inatel.cdg.DAO.*;
import br.inatel.cdg.model.*;
import br.inatel.cdg.utils.ConversorData;

import java.util.List;

public class Main {
    static Artista artista = new Artista();
    static ArtistaDAO artistaDAO = new ArtistaDAO();

    static MusicaDAO musicaDAO = new MusicaDAO();
    static Musica musica = new Musica();

    static ParticipanteDAO participanteDAO = new ParticipanteDAO();
    static Participante participante = new Participante();

    static PalcoDAO palcoDAO = new PalcoDAO();
    static Palco palco = new Palco();

    static ArtistaPalcoDAO artistaPalcoDAO = new ArtistaPalcoDAO();
    static ArtistaPalco artistaPalco = new ArtistaPalco();

    static EventoDAO eventoDAO = new EventoDAO();
    static Evento evento = new Evento();

    static IngressoDAO ingressoDAO = new IngressoDAO();
    static Ingresso ingresso = new Ingresso();

    public static void main(String[] args) {

        criarArtista();
        lerArtista();
        alterarArtista();

        criarMusica();
        alterarMusica();
        lerMusica();

        criarParticipante();
        alterarParticipante();
        lerParticipante();

        criarPalco();
        lerPalco();
        alterarPalco();

        criarArtistaPalco();
        lerArtistaPalco();
        alterarArtistaPalco();

        criarEvento();
        lerEvento();
        alterarEvento();

        criarIngresso();
        lerIngresso();
        alterarIngresso();
/*
        excluirIngresso();
        excluirEvento();
        excluirArtistaPalco();
        excluirPalco();
        excluirMusica();
        excluirArtista();
        excluirParticipante();
        */

    }

    //Artista
    public static void criarArtista() {
        artista.setNome("Sandy");
        artista.setGeneroMusical("Pop");
        artista = artistaDAO.criar(artista);
    }

    private static void lerArtista() {
        List<Artista> art = artistaDAO.ler("Sandy");
        System.out.println(art.toString());
    }

    private static void alterarArtista() {
        artista.setNome("Sandy Lea");
        artistaDAO.atualizar(artista);
        System.out.println(artista.toString());
    }

    private static void excluirArtista() {
        artistaDAO.excluir(artista.getId());
    }

    //musica
    public static void criarMusica() {
        musica.setNome("Turu turu");
        musica.setArtista(artista);

        musicaDAO.criar(musica);
    }

    public static void alterarMusica() {
        musica.setNome("turo turo turo");
        musicaDAO.atualizar(musica);
    }

    public static void lerMusica() {
        List<Musica> musicaList = musicaDAO.ler("turo");
        System.out.println(musicaList.toString());
    }

    public static void excluirMusica() {
        musicaDAO.excluir(musica.getId());
    }

    //participante
    public static void criarParticipante() {
        participante.setNome("Francielly");
        participante.setCPF("000.000.000-00");
        participante.setEmail("fran@gmail.com");
        participante.setTelefone("35 00000-0000");
        participante.setDataNascimento(ConversorData.converterStringParaLocalDate("01/01/2001"));

        participante = participanteDAO.criar(participante);
    }

    public static void alterarParticipante() {
        participante.setNome("Francielly Marianne");
        participanteDAO.atualizar(participante);
        System.out.println(participante.toString());
    }

    public static void lerParticipante() {
        List<Participante> participanteList = participanteDAO.ler("Francielly");
        System.out.println(participanteList.toString());
    }

    public static void excluirParticipante() {
        participanteDAO.excluir(participante.getId());
    }

    //palco
    public static void criarPalco() {
        palco.setNome("Palco Mundo");
        palcoDAO.criar(palco);
    }

    public static void alterarPalco() {
        palco.setNome("Palco 4 estações");
        palcoDAO.atualizar(palco);
        System.out.println(palco.toString());
    }

    public static void lerPalco() {
        List<Palco> palcoList = palcoDAO.ler("Palco");
        System.out.println(palcoList.toString());
    }

    public static void excluirPalco() {
        palcoDAO.excluir(palco.getId());
    }

    //artistaPalco
    public static void criarArtistaPalco() {
        artistaPalco.setArtista(artista);
        artistaPalco.setPalco(palco);
        artistaPalco.setHorarioArtista("19:00");
        artistaPalcoDAO.criar(artistaPalco);
    }

    public static void alterarArtistaPalco() {
        artistaPalco.setHorarioArtista("21:00");
        artistaPalcoDAO.atualizar(artistaPalco);
        System.out.println(artistaPalco);
    }

    public static void lerArtistaPalco() {
        List<ArtistaPalco> artistaPalcoList = artistaPalcoDAO.ler("Palco");
        System.out.println(artistaPalcoList);
    }

    public static void excluirArtistaPalco() {
        artistaPalcoDAO.excluir(artistaPalco.getId());
    }

    //Evento
    public static void criarEvento() {
        evento.setNome("Rock'n Rio");
        evento.setData(ConversorData.converterStringParaLocalDate("02/02/2022"));
        evento.setPalco(palco);
        eventoDAO.criar(evento);
    }

    public static void alterarEvento() {
        evento.setData(ConversorData.converterStringParaLocalDate("03/03/2023"));
        eventoDAO.atualizar(evento);
        System.out.println(evento.toString());
    }

    public static void lerEvento() {
        eventoDAO.ler("Rock");
        System.out.println(eventoDAO);
    }

    public static void excluirEvento() {
        eventoDAO.excluir(evento.getId());
    }

    //Ingresso
    public static void criarIngresso() {
        ingresso.setEvento(evento);
        ingresso.setParticipante(participante);
        ingresso.setCategoria("show");
        ingressoDAO.criar(ingresso);
    }

    public static void alterarIngresso() {
        ingresso.setCategoria("show de luzes");
        ingressoDAO.atualizar(ingresso);
        System.out.println(ingresso);
    }

    public static void lerIngresso() {
        List<Ingresso> ingressoList = ingressoDAO.ler("Francielly");
        System.out.println(ingressoList.toString());
    }

    public static void excluirIngresso() {
        ingressoDAO.excluir(ingresso.getId());
    }

}
