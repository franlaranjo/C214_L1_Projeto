/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.inatel.cdg.DAO;

import br.inatel.cdg.model.Palco;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Classe que implementa o CRUD do Palco
 *
 * @author Francielly
 */
public class PalcoDAO extends ConexaoMySql implements DAO<Palco> {

    /**
     * Método que cria Palco no BD
     *
     * @param palco
     * @return palco
     */
    @Override
    public Palco criar(Palco palco) {
        int id = 0;
        try {
            this.conectar();
            String pSQL = "";
            pSQL += "INSERT INTO Palco";
            pSQL += "   (";
            pSQL += "       nome";
            pSQL += "   )";
            pSQL += "   VALUES (?)";
            List<Object> argumentos = Arrays.asList(palco.getNome());
            id = this.insertSQL(pSQL, argumentos);
            palco.setId(id);
        } catch (Exception ex) {
            System.out.println("Erro ao Salvar no Banco!\n" + ex.toString());
        } finally {
            this.fecharConexao();
            return palco;
        }
    }

    /**
     * Método que lê Palco no BD
     *
     * @param pesquisa
     * @return palcoList
     */
    @Override
    public List<Palco> ler(String pesquisa) {
        List<Palco> palcoList = new ArrayList<Palco>();
        try {
            this.conectar();
            String pSQL = "";
            pSQL += "SELECT * FROM Palco WHERE LOWER(nome) LIKE LOWER(?);";
            List<Object> argumentos = Arrays.asList("%" + pesquisa + "%");
            this.executarSQL(pSQL, argumentos);
            while (this.getResultSet().next()) {
                Palco palco = new Palco();
                palco.setId(this.getResultSet().getInt(1));
                palco.setNome(this.getResultSet().getString(2));
                palcoList.add(palco);
            }
        } catch (Exception ex) {
            System.out.println("Erro ao Ler no Banco!\n" + ex.toString());
        } finally {
            this.fecharConexao();
            return palcoList;
        }
    }

    /**
     * Método que lê Palco no BD
     *
     * @param id
     * @return palco
     */
    @Override
    public Palco lerPorId(int id) {
        Palco palco = null;
        try {
            this.conectar();
            String pSQL = "SELECT * FROM Palco WHERE idPalco = ?;";
            List<Object> argumentos = Arrays.asList(id);
            this.executarSQL(pSQL, argumentos);
            if (this.getResultSet().next()) {
                palco = new Palco();
                palco.setId(this.getResultSet().getInt(1));
                palco.setNome(this.getResultSet().getString(2));
            }
        } catch (Exception ex) {
            System.out.println("Erro ao Ler no Banco!\n" + ex.toString());
        } finally {
            this.fecharConexao();
            return palco;
        }
    }

    /**
     * Método que atualiza Palco no BD
     *
     * @param palco
     * @return boolean
     */
    @Override
    public boolean atualizar(Palco palco) {
        try {
            this.conectar();
            String pSQL = "";
            pSQL += "UPDATE Palco SET";
            pSQL += "   nome=?";
            pSQL += "   WHERE";
            pSQL += "       idPalco=?;";
            List<Object> argumentos = Arrays.asList(palco.getNome(), palco.getId());
            return this.executarUpdateDeleteSQL(pSQL, argumentos);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.fecharConexao();
        }
    }

    /**
     * Método que Palco no BD
     *
     * @param id
     * @return boolean
     */
    @Override
    public boolean excluir(int id) {
        try {
            this.conectar();
            String pSQL = "DELETE FROM Palco WHERE idPalco =?;";
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
