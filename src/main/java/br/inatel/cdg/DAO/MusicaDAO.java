/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.inatel.cdg.DAO;

import br.inatel.cdg.model.Artista;
import br.inatel.cdg.model.Musica;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Classe que implementa o CRUD da Musica
 *
 * @author Francielly
 */
public class MusicaDAO extends ConexaoMySql implements DAO<Musica> {

    /**
     * Método que cria Musica no BD
     *
     * @param musica
     * @return musica
     */
    @Override
    public Musica criar(Musica musica) {

        try {
            this.conectar();
            String pSQL = "INSERT INTO Musica";
            pSQL += "   (";
            pSQL += "       nome,";
            pSQL += "       Artista_idArtista";
            pSQL += "   )";
            pSQL += "   VALUES (?,?)";
            // Cria a lista de argumentos do SQL de inserção
            List<Object> argumentos = Arrays.asList(musica.getNome(), musica.getArtista().getId());
            int id = this.insertSQL(pSQL, argumentos);
            musica.setId(id);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Erro ao Salvar no Banco!\n" + ex.toString());
        } finally {
            this.fecharConexao();
            return musica;
        }

    }

    /**
     * Método que lê Musica no BD
     *
     * @param pesquisa
     * @return musicaList
     */
    @Override
    public List<Musica> ler(String pesquisa) {
        List<Musica> musicaList = new ArrayList<Musica>();
        try {
            this.conectar();
            String pSQL = "";
            pSQL += "SELECT ";
            pSQL += " idMusica, musica.nome,idArtista, artista.nome ";
            pSQL += "  FROM Musica ";
            pSQL += " JOIN Artista ON Artista_idArtista = artista.idArtista ";
            pSQL += "    WHERE LOWER(musica.nome) LIKE LOWER(?);";
            List< Object> argumentos = Arrays.asList("%" + pesquisa + "%");
            this.executarSQL(pSQL, argumentos);
            while (this.getResultSet().next()) {
                Musica musica = new Musica();
                musica.setId(this.getResultSet().getInt(1));
                musica.setNome(this.getResultSet().getString(2));
                Artista artista = new Artista();
                artista.setId(this.getResultSet().getInt(3));
                artista.setNome(this.getResultSet().getString(4));
                musica.setArtista(artista);
                musicaList.add(musica);
            }
        } catch (Exception ex) {
            System.out.println("Erro ao Ler no Banco!\n" + ex.toString());
        } finally {
            this.fecharConexao();
            return musicaList;
        }
    }

    /**
     * Método que lê Musica no BD
     *
     * @param id
     * @return musica
     */
    @Override
    public Musica lerPorId(int id) {
        Musica musica = null;
        try {
            this.conectar();
            String pSQL = "";
            pSQL += "SELECT ";
            pSQL += " idMusica, musica.nome,idArtista, artista.nome ";
            pSQL += "  FROM Musica ";
            pSQL += " JOIN Artista ON Artista_idArtista = artista.idArtista ";
            pSQL += " WHERE idMusica = ?;";
            List<Object> argumentos = Arrays.asList(id);
            this.executarSQL(pSQL, argumentos);
            if (this.getResultSet().next()) {
                musica = new Musica();
                musica.setId(this.getResultSet().getInt(1));
                musica.setNome(this.getResultSet().getString(2));
                Artista artista = new Artista();
                artista.setId(this.getResultSet().getInt(3));
                artista.setNome(this.getResultSet().getString(4));
                musica.setArtista(artista);
            }
        } catch (Exception ex) {
            System.out.println("Erro ao Ler no Banco!\n" + ex.toString());
        } finally {
            this.fecharConexao();
            return musica;
        }
    }

    /**
     * Método que atualiza Musica no BD
     *
     * @param musica
     * @return boolean
     */
    @Override
    public boolean atualizar(Musica musica) {
        try {
            this.conectar();
            String pSQL = "";
            pSQL += "UPDATE Musica SET";
            pSQL += "   nome=?,";
            pSQL += "   Artista_idArtista=?";
            pSQL += "   WHERE";
            pSQL += "       idMusica=?;";
            List<Object> argumentos = Arrays.asList(musica.getNome(), musica.getArtista().getId(), musica.getId());
            return this.executarUpdateDeleteSQL(pSQL, argumentos);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.fecharConexao();
        }
    }

    /**
     * Método que exclui Musica no BD
     *
     * @param id
     * @return boolean
     */
    @Override
    public boolean excluir(int id) {
        try {
            this.conectar();
            String pSQL = "DELETE FROM Musica WHERE idMusica =?;";
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
