/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.inatel.cdg.DAO;

import br.inatel.cdg.model.Artista;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Classe que implementa o CRUD do Artista
 *
 * @author Francielly
 */
public class ArtistaDAO extends ConexaoMySql implements DAO<Artista> {

    /**
     * Método que insere Artista no BD
     *
     * @param artista
     * @return Artista
     */
    @Override
    public Artista criar(Artista artista) {
        int id = 0;
        try {
            this.conectar();
            String pSQL = "";
            pSQL += "INSERT INTO Artista";
            pSQL += "   (";
            pSQL += "       nome,";
            pSQL += "       generoMusical";
            pSQL += "   )";
            pSQL += "   VALUES (?,?);";
            // Cria a lista de argumentos do SQL de inserção
            List<Object> argumentos = Arrays.asList(artista.getNome(), artista.getGeneroMusical());
            id = this.insertSQL(pSQL, argumentos);
            artista.setId(id);
        } catch (Exception ex) {
            System.out.println("Erro ao Salvar no Banco!\n" + ex.toString());
        } finally {
            this.fecharConexao();
            return artista;
        }
    }

    /**
     * Método que lê Artista no BD
     *
     * @param pesquisa
     * @return ArtistaList
     */
    @Override
    public List<Artista> ler(String pesquisa) {
        List<Artista> artistaList = new ArrayList<Artista>();
        try {
            this.conectar();
            String pSQL = "";
            pSQL += "SELECT * FROM Artista WHERE LOWER(nome) LIKE LOWER(?);";
            List<Object> argumentos = Arrays.asList("%" + pesquisa + "%");
            this.executarSQL(pSQL, argumentos);
            while (this.getResultSet().next()) {
                Artista artista = new Artista();
                artista.setId(this.getResultSet().getInt(1));
                artista.setNome(this.getResultSet().getString(2));
                artista.setGeneroMusical(this.getResultSet().getString(3));
                artistaList.add(artista);
            }
        } catch (Exception ex) {
            System.out.println("Erro ao Ler no Banco!\n" + ex.toString());

        } finally {
            this.fecharConexao();
            return artistaList;
        }
    }

    /**
     * Método que lê Artista no BD
     *
     * @param id
     * @return Artista
     */
    @Override
    public Artista lerPorId(int id) {
        Artista artista = null;
        try {
            this.conectar();
            String pSQL = "SELECT * FROM Artista WHERE idArtista = ?;";
            List<Object> argumentos = Arrays.asList(id);
            this.executarSQL(pSQL, argumentos);
            if (this.getResultSet().next()) {
                artista = new Artista();
                artista.setId(this.getResultSet().getInt(1));
                artista.setNome(this.getResultSet().getString(2));
                artista.setGeneroMusical(this.getResultSet().getString(3));
            }
        } catch (Exception ex) {
            System.out.println("Erro ao Ler no Banco!\n" + ex.toString());
        } finally {
            this.fecharConexao();
            return artista;
        }
    }

    /**
     * Método que Atualiza Artista no BD
     *
     * @param artista
     * @return boolean
     */
    @Override
    public boolean atualizar(Artista artista) {
        try {
            this.conectar();
            String pSQL = "";
            pSQL += "UPDATE Artista SET";
            pSQL += "   nome=?,";
            pSQL += "   generoMusical=?";
            pSQL += "   WHERE";
            pSQL += "       idArtista=?;";
            List<Object> argumentos = Arrays.asList(artista.getNome(), artista.getGeneroMusical(), artista.getId());
            return this.executarUpdateDeleteSQL(pSQL, argumentos);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.fecharConexao();
        }
    }

    /**
     * Método que Exclui Artista no BD
     *
     * @param id
     * @return boolean
     */
    @Override
    public boolean excluir(int id) {
        try {
            this.conectar();
            String pSQL = "DELETE FROM Artista WHERE idArtista =?;";
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
