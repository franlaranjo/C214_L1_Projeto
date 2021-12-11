/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.inatel.cdg.DAO;

import br.inatel.cdg.model.Evento;
import br.inatel.cdg.model.Palco;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Classe que implementa o CRUD do Evento
 *
 * @author Francielly
 */
public class EventoDAO extends ConexaoMySql implements DAO<Evento> {

    /**
     * Método que cria Evento no BD
     *
     * @param evento
     * @return evento
     */
    @Override
    public Evento criar(Evento evento) {
        try {
            this.conectar();
            String pSQL = "";
            pSQL += "INSERT INTO Evento";
            pSQL += "   (";
            pSQL += "       nome,";
            pSQL += "       data,";
            pSQL += "       palco_idPalco";
            pSQL += "   )";
            pSQL += "   VALUES (?,?,?);";
            // Cria a lista de argumentos do SQL de inserção
            List<Object> argumentos = Arrays.asList(evento.getNome(), evento.getData(), evento.getPalco().getId());
            int id = this.insertSQL(pSQL, argumentos);
            evento.setId(id);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Erro ao Salvar no Banco!\n" + ex.toString());
        } finally {
            this.fecharConexao();
            return evento;
        }
    }

    /**
     * Método que lê Evento no BD
     *
     * @param pesquisa
     * @return eventoList
     */
    @Override
    public List<Evento> ler(String pesquisa) {
        List<Evento> eventoList = new ArrayList<Evento>();
        try {
            this.conectar();
            String pSQL = "";
            pSQL += "SELECT ";
            pSQL += "   *";
            pSQL += "   FROM Evento";
            pSQL += "   JOIN Palco ON Evento.palco_idPalco = Palco.idPalco";
            pSQL += "   WHERE";
            pSQL += "   LOWER(Evento.nome) LIKE LOWER(?);";
            List<Object> argumentos = Arrays.asList("%" + pesquisa + "%");
            this.executarSQL(pSQL, argumentos);
            while (this.getResultSet().next()) {
                Evento evento = new Evento();
                evento.setId(this.getResultSet().getInt(1));
                evento.setNome(this.getResultSet().getString(2));
                evento.setData(this.getResultSet().getDate(3).toLocalDate());
                Palco palco = new Palco();
                palco.setId(this.getResultSet().getInt(5));
                palco.setNome(this.getResultSet().getString(6));
                evento.setPalco(palco);
                eventoList.add(evento);
            }
        } catch (Exception ex) {
            System.out.println("Erro ao Ler no Banco!\n" + ex.toString());
        } finally {
            this.fecharConexao();
            return eventoList;
        }
    }

    /**
     * Método que lê Evento no BD
     *
     * @param id
     * @return evento
     */
    @Override
    public Evento lerPorId(int id) {
        Evento evento = null;
        try {
            this.conectar();
            String pSQL = "";
            pSQL += "SELECT ";
            pSQL += "   *";
            pSQL += "   FROM Evento";
            pSQL += "   JOIN Palco ON Evento.palco_idPalco = Palco.idPalco";
            pSQL += "   WHERE idEvento = ?;";
            List<Object> argumentos = Arrays.asList(id);
            this.executarSQL(pSQL, argumentos);
            if (this.getResultSet().next()) {
                evento = new Evento();
                evento.setId(this.getResultSet().getInt(1));
                evento.setNome(this.getResultSet().getString(2));
                evento.setData(this.getResultSet().getDate(3).toLocalDate());
                Palco palco = new Palco();
                palco.setId(this.getResultSet().getInt(5));
                palco.setNome(this.getResultSet().getString(6));
                evento.setPalco(palco);
            }
        } catch (Exception ex) {
            System.out.println("Erro ao Ler no Banco!\n" + ex.toString());
        } finally {
            this.fecharConexao();
            return evento;
        }
    }

    /**
     * Método que atualiza Evento no BD
     *
     * @param evento
     * @return boolean
     */
    @Override
    public boolean atualizar(Evento evento) {
        try {
            this.conectar();
            String pSQL = "";
            pSQL += "UPDATE Evento SET";
            pSQL += "   nome=?,";
            pSQL += "   data=?,";
            pSQL += "   palco_idPalco=?";
            pSQL += "   WHERE";
            pSQL += "       idEvento = ?;";
            List<Object> argumentos = Arrays.asList(evento.getNome(), evento.getData(), evento.getPalco().getId(), evento.getId());
            return this.executarUpdateDeleteSQL(pSQL, argumentos);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.fecharConexao();
        }
    }

    /**
     * Método que exclui Evento no BD
     *
     * @param id
     * @return boolean
     */
    @Override
    public boolean excluir(int id) {
        try {
            this.conectar();
            String pSQL = "DELETE FROM Evento WHERE idEvento =?;";
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
