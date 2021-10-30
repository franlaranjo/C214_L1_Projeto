/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.inatel.cdg.DAO;

import br.inatel.cdg.model.Artista;
import br.inatel.cdg.model.ArtistaPalco;
import br.inatel.cdg.model.Palco;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Classe que implementa o CRUD do ArtistaPalco
 *
 * @author Francielly
 */
public class ArtistaPalcoDAO extends ConexaoMySql implements DAO<ArtistaPalco> {

    /**
     * Método que insere ArtistaPalco no BD
     *
     * @param artistaPalco
     * @return artistaPalco
     */
    @Override
    public ArtistaPalco criar(ArtistaPalco artistaPalco) {
        try {
            this.conectar();
            String pSQL = "";
            pSQL += "INSERT INTO ArtistaPalco";
            pSQL += "   (";
            pSQL += "       Artista_idArtista,";
            pSQL += "       Palco_idPalco,";
            pSQL += "       horarioArtista";
            pSQL += "   )";
            pSQL += "   VALUES (?,?,?);";
            List<Object> argumentos = Arrays.asList(artistaPalco.getArtista().getId(), artistaPalco.getPalco().getId(), artistaPalco.getHorarioArtista());
            int id = this.insertSQL(pSQL, argumentos);
            artistaPalco.setId(id);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Erro ao Salvar no Banco!\n" + ex.toString());
        } finally {
            this.fecharConexao();
            return artistaPalco;
        }
    }

    /**
     * Método que lê ArtistaPalco no BD
     *
     * @param pesquisa
     * @return artistaPalcoList
     */
    @Override
    public List<ArtistaPalco> ler(String pesquisa) {
        List<ArtistaPalco> artistaPalcoList = new ArrayList<ArtistaPalco>();
        try {
            this.conectar();
            String pSQL = "";
            pSQL += "SELECT ";
            pSQL += "   idArtistaPalco, idArtista, artista.nome, idPalco, palco.nome, horarioArtista";
            pSQL += "   FROM ArtistaPalco";
            pSQL += "   JOIN artista ON idArtista = artistapalco.Artista_idArtista";
            pSQL += "   JOIN palco ON idPalco = artistapalco.Palco_idPalco";
            pSQL += "   WHERE ";
            pSQL += "       LOWER(Palco.nome) LIKE LOWER(?)";
            List<Object> argumentos = Arrays.asList("%" + pesquisa + "%");
            this.executarSQL(pSQL, argumentos);
            while (this.getResultSet().next()) {
                ArtistaPalco artistaPalco = new ArtistaPalco();
                artistaPalco.setId(this.getResultSet().getInt(1));
                Artista artista = new Artista();
                artista.setId(this.getResultSet().getInt(2));
                artista.setNome(this.getResultSet().getString(3));
                Palco palco = new Palco();
                palco.setId(this.getResultSet().getInt(4));
                palco.setNome(this.getResultSet().getString(5));
                artistaPalco.setHorarioArtista(this.getResultSet().getString(6));
                artistaPalco.setArtista(artista);
                artistaPalco.setPalco(palco);
                artistaPalcoList.add(artistaPalco);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Erro ao Ler no Banco!\n" + ex.toString());
        } finally {
            this.fecharConexao();
            return artistaPalcoList;
        }
    }

    /**
     * Método que lê ArtistaPalco no BD
     *
     * @param id
     * @return artistaPalco
     */
    @Override
    public ArtistaPalco lerPorId(int id) {
        ArtistaPalco artistaPalco = null;
        try {
            this.conectar();
            String pSQL = "";
            pSQL += "SELECT ";
            pSQL += " idArtistaPalco, idArtista, artista.nome, idPalco, palco.nome, horarioArtista";
            pSQL += "   FROM ArtistaPalco";
            pSQL += "   JOIN artista ON idArtista = artistapalco.Artista_idArtista";
            pSQL += "   JOIN palco ON idPalco = artistapalco.Palco_idPalco";
            pSQL += "   WHERE";
            pSQL += " idArtistaPalco =  ?;";
            List<Object> argumentos = Arrays.asList(id);
            this.executarSQL(pSQL, argumentos);
            if (this.getResultSet().next()) {
                artistaPalco = new ArtistaPalco();
                artistaPalco.setId(this.getResultSet().getInt(1));
                Artista artista = new Artista();
                artista.setId(this.getResultSet().getInt(2));
                artista.setNome(this.getResultSet().getString(3));
                Palco palco = new Palco();
                palco.setId(this.getResultSet().getInt(4));
                palco.setNome(this.getResultSet().getString(5));
                artistaPalco.setHorarioArtista(this.getResultSet().getString(6));
                artistaPalco.setArtista(artista);
                artistaPalco.setPalco(palco);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Erro ao Ler no Banco!\n" + ex.toString());
        } finally {
            this.fecharConexao();
            return artistaPalco;
        }
    }

    /**
     * Método que Atualiza ArtistaPalco no BD
     *
     * @param artistaPalco
     * @return boolean
     */
    @Override
    public boolean atualizar(ArtistaPalco artistaPalco) {
        try {
            this.conectar();
            String pSQL = "";
            pSQL += "UPDATE ArtistaPalco SET";
            pSQL += "   Artista_idArtista=?,";
            pSQL += "   Palco_idPalco=?,";
            pSQL += "   horarioArtista=?";
            pSQL += "   WHERE";
            pSQL += "       idArtistaPalco=?;";
            List<Object> argumentos = Arrays.asList(artistaPalco.getArtista().getId(), artistaPalco.getPalco().getId(), artistaPalco.getHorarioArtista(), artistaPalco.getId());
            return this.executarUpdateDeleteSQL(pSQL, argumentos);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.fecharConexao();
        }
    }

    /**
     * Método que Exclui ArtistaPalco no BD
     *
     * @param id
     * @return boolean
     */
    @Override
    public boolean excluir(int id) {
        try {
            this.conectar();
            String pSQL = "DELETE FROM ArtistaPalco WHERE idArtistaPalco =?;";
            List<Object> argumentos = Arrays.asList(id);
            return this.executarUpdateDeleteSQL(pSQL, argumentos);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.fecharConexao();
        }
    }
}
