/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.inatel.cdg.DAO;

import br.inatel.cdg.model.Participante;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Classe que implementa o CRUD do Participante
 *
 * @author Francielly
 */
public class ParticipanteDAO extends ConexaoMySql implements DAO<Participante> {

    /**
     * Método que cria Participante no BD
     *
     * @param participante
     * @return participante
     */
    @Override
    public Participante criar(Participante participante) {

        try {
            this.conectar();
            String pSQL = "";
            pSQL += "INSERT INTO Participante";
            pSQL += "   (";
            pSQL += "       nome,";
            pSQL += "       dataNasc,";
            pSQL += "       CPF,";
            pSQL += "       telefone,";
            pSQL += "       email";
            pSQL += "   )";
            pSQL += "   VALUES (?,?,?,?,?)";
            List<Object> argumentos = Arrays.asList(participante.getNome(), participante.getDataNascimento(), participante.getCPF(), participante.getTelefone(), participante.getEmail());
            int id = this.insertSQL(pSQL, argumentos);
            participante.setId(id);
        } catch (Exception ex) {
            System.out.println("Erro ao Salvar no Banco!\n" + ex.toString());
        } finally {
            this.fecharConexao();
            return participante;
        }
    }

    /**
     * Método que lê Participante no BD
     *
     * @param pesquisa
     * @return participanteList
     */
    @Override
    public List<Participante> ler(String pesquisa) {
        List<Participante> participanteList = new ArrayList<Participante>();
        try {
            this.conectar();
            String pSQL = "";
            pSQL += "SELECT * FROM Participante WHERE LOWER(nome) LIKE LOWER(?);";
            List<Object> argumentos = Arrays.asList("%" + pesquisa + "%");
            this.executarSQL(pSQL, argumentos);
            while (this.getResultSet().next()) {
                Participante participante = new Participante();
                participante.setId(this.getResultSet().getInt(1));
                participante.setNome(this.getResultSet().getString(2));
                participante.setDataNascimento(this.getResultSet().getDate(3).toLocalDate());
                participante.setCPF(this.getResultSet().getString(4));
                participante.setTelefone(this.getResultSet().getString(5));
                participante.setEmail(this.getResultSet().getString(6));
                participanteList.add(participante);
            }
        } catch (Exception ex) {
            System.out.println("Erro ao Ler no Banco!\n" + ex.toString());
        } finally {
            this.fecharConexao();
            return participanteList;
        }
    }

    /**
     * Método que lê Participante no BD
     *
     * @param id
     * @return participante
     */
    @Override
    public Participante lerPorId(int id) {
        Participante participante = null;
        try {
            this.conectar();
            String pSQL = "SELECT * FROM Participante WHERE idParticipante = ?;";
            List<Object> argumentos = Arrays.asList(id);
            this.executarSQL(pSQL, argumentos);
            if (this.getResultSet().next()) {
                participante = new Participante();
                participante.setId(this.getResultSet().getInt(1));
                participante.setNome(this.getResultSet().getString(2));
                participante.setDataNascimento(this.getResultSet().getDate(3).toLocalDate());
                participante.setCPF(this.getResultSet().getString(4));
                participante.setTelefone(this.getResultSet().getString(5));
                participante.setEmail(this.getResultSet().getString(6));
            }
        } catch (Exception ex) {
            System.out.println("Erro ao Ler no Banco!\n" + ex.toString());
        } finally {
            this.fecharConexao();
            return participante;
        }
    }

    /**
     * Método que atualiza Participante no BD
     *
     * @param participante
     * @return boolean
     */
    @Override
    public boolean atualizar(Participante participante) {
        try {
            this.conectar();
            String pSQL = "";
            pSQL += "UPDATE Participante SET";
            pSQL += "       nome=?,";
            pSQL += "       dataNasc=?,";
            pSQL += "       CPF=?,";
            pSQL += "       telefone=?,";
            pSQL += "       email=?";
            pSQL += "   WHERE";
            pSQL += "   idParticipante=?";
            List<Object> argumentos = Arrays.asList(participante.getNome(), participante.getDataNascimento(), participante.getCPF(), participante.getTelefone(), participante.getEmail(), participante.getId());
            return this.executarUpdateDeleteSQL(pSQL, argumentos);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.fecharConexao();
        }
    }

    /**
     * Método que Participante no BD
     *
     * @param id
     * @return boolean
     */
    @Override
    public boolean excluir(int id) {
        try {
            this.conectar();
            String pSQL = "DELETE FROM Participante WHERE idParticipante =?;";
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
