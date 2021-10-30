/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.inatel.cdg.DAO;

import br.inatel.cdg.model.Evento;
import br.inatel.cdg.model.Ingresso;
import br.inatel.cdg.model.Participante;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Classe que implementa o CRUD do Ingresso
 *
 * @author Francielly
 */
public class IngressoDAO extends ConexaoMySql implements DAO<Ingresso> {

    /**
     * Método que cria Ingresso no BD
     *
     * @param ingresso
     * @return ingresso
     */
    @Override
    public Ingresso criar(Ingresso ingresso) {
        int id = 0;
        try {
            this.conectar();
            String pSQL = "";
            pSQL += "INSERT INTO Ingresso ";
            pSQL += "   (";
            pSQL += "       categoria,";
            pSQL += "       Evento_idEvento,";
            pSQL += "       Participante_idParticipante";
            pSQL += "   )";
            pSQL += "   VALUES (?,?,?);";
            // Cria a lista de argumentos do SQL de inserção
            List<Object> argumentos = Arrays.asList(ingresso.getCategoria(), ingresso.getEvento().getId(), ingresso.getParticipante().getId());
            id = this.insertSQL(pSQL, argumentos);
            ingresso.setId(id);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Erro ao Salvar no Banco!\n" + ex.toString());
        } finally {
            this.fecharConexao();
            return ingresso;
        }
    }

    /**
     * Método que lê Ingresso no BD
     *
     * @param pesquisa
     * @return ingressoList
     */
    @Override
    public List<Ingresso> ler(String pesquisa) {
        List<Ingresso> ingressoList = new ArrayList<Ingresso>();
        try {
            this.conectar();
            String pSQL = "";
            pSQL += "SELECT ";
            pSQL += "  idIngresso,categoria,idEvento,evento.nome,idParticipante,participante.nome";
            pSQL += "   FROM ingresso";
            pSQL += "   JOIN Evento ON ingresso.Evento_idEvento = evento.idEvento";
            pSQL += "   JOIN Participante ON ingresso.Participante_idParticipante = participante.idParticipante";
            pSQL += "   WHERE LOWER(Participante.nome) LIKE LOWER(?);";
            List<Object> argumentos = Arrays.asList("%" + pesquisa + "%");
            this.executarSQL(pSQL, argumentos);
            while (this.getResultSet().next()) {
                Ingresso ingresso = new Ingresso();
                ingresso.setId(this.getResultSet().getInt(1));
                ingresso.setCategoria(this.getResultSet().getString(2));
                Evento evento = new Evento();
                evento.setId(this.getResultSet().getInt(3));
                evento.setNome(this.getResultSet().getString(4));
                Participante participante = new Participante();
                participante.setId(this.getResultSet().getInt(5));
                participante.setNome(this.getResultSet().getString(6));
                ingresso.setEvento(evento);
                ingresso.setParticipante(participante);
                ingressoList.add(ingresso);
            }
        } catch (Exception ex) {
            System.out.println("Erro ao Ler no Banco!\n" + ex.toString());
        } finally {
            this.fecharConexao();
            return ingressoList;
        }
    }

    /**
     * Método que lê Ingresso no BD
     *
     * @param id
     * @return ingresso
     */
    @Override
    public Ingresso lerPorId(int id) {
        Ingresso ingresso = null;
        try {
            this.conectar();
            String pSQL = "";
            pSQL += "SELECT ";
            pSQL += "  idIngresso,categoria,idEvento,evento.nome,idParticipante,participante.nome";
            pSQL += "   FROM ingresso";
            pSQL += "   JOIN Evento ON ingresso.Evento_idEvento = evento.idEvento";
            pSQL += "   JOIN Participante ON ingresso.Participante_idParticipante = participante.idParticipante";
            pSQL += "   WHERE idIngresso =  ?;";
            List<Object> argumentos = Arrays.asList(id);
            this.executarSQL(pSQL, argumentos);
            if (this.getResultSet().next()) {
                ingresso = new Ingresso();
                ingresso.setId(this.getResultSet().getInt(1));
                ingresso.setCategoria(this.getResultSet().getString(2));
                Evento evento = new Evento();
                evento.setId(this.getResultSet().getInt(3));
                evento.setNome(this.getResultSet().getString(4));
                Participante participante = new Participante();
                participante.setId(this.getResultSet().getInt(5));
                participante.setNome(this.getResultSet().getString(6));
                ingresso.setEvento(evento);
                ingresso.setParticipante(participante);
            }
        } catch (Exception ex) {
            System.out.println("Erro ao Ler no Banco!\n" + ex.toString());
        } finally {
            this.fecharConexao();
            return ingresso;
        }
    }

    /**
     * Método que atualiza Ingresso no BD
     *
     * @param ingresso
     * @return boolean
     */
    @Override
    public boolean atualizar(Ingresso ingresso) {
        try {
            this.conectar();
            String pSQL = "";
            pSQL += "UPDATE Ingresso SET";
            pSQL += "   categoria=?,";
            pSQL += "   Evento_idEvento=?,";
            pSQL += "   Participante_idParticipante=?";
            pSQL += "   WHERE";
            pSQL += "       idIngresso=?;";
            List<Object> argumentos = Arrays.asList(ingresso.getCategoria(), ingresso.getEvento().getId(), ingresso.getParticipante().getId(), ingresso.getId());
            return this.executarUpdateDeleteSQL(pSQL, argumentos);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.fecharConexao();
        }
    }

    /**
     * Método que exclui Ingresso no BD
     *
     * @param id
     * @return boolean
     */
    @Override
    public boolean excluir(int id) {
        try {
            this.conectar();
            String pSQL = "DELETE FROM Ingresso WHERE idIngresso =?;";
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
