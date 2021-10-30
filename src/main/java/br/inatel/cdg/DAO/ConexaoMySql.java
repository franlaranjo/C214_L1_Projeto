/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.inatel.cdg.DAO;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.List;

/**
 * Classe que implementa as conexões com o BD
 *
 * @author Francielly
 */
public class ConexaoMySql {

    private boolean status = false;

    //Informações sobre a conexão
    private String mensagem = "";   //status
    private Connection con = null;  //conexao
    private Statement statement;
    private ResultSet resultSet; //resultado da consulta

    // Informações para conexão com o BD
    private String servidor = "localhost:3306";
    private String nomeDoBanco = "cdg";
    private String usuario = "root";
    private String senha = "root";

    public ConexaoMySql() {
    }

    public ConexaoMySql(String pServidor, String pNomeDoBanco, String pUsuario, String pSenha) {
        this.servidor = pServidor;
        this.nomeDoBanco = pNomeDoBanco;
        this.usuario = pUsuario;
        this.senha = pSenha;
    }

    /**
     * Inicia conexao com o banco
     *
     * @return Connection
     */
    public Connection conectar() {
        try {
            //Driver do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            //local do banco, nome do banco, usuario e senha
            String url = "jdbc:mysql://" + servidor + "/" + nomeDoBanco + "?useTimezone=true&serverTimezone=UTC&useSSL=false";
            this.setCon((Connection) DriverManager.getConnection(url, usuario, senha));
            //se ocorrer tudo bem, ou seja, se conectar a linha a seguir é executada
            this.status = true;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException | NoSuchMethodException | InvocationTargetException e) {
            System.out.println("Erro ao conectar no banco!\n" + e.getMessage());
        }
        return this.getCon();
    }

    /**
     * Executa consultas SQL
     *
     * @param pSQL
     * @return boolean
     */
    public boolean executarSQL(String pSQL) {
        try {
            //createStatement de con para criar o Statement
            this.setStatement(getCon().createStatement());
            // Definido o Statement, executamos a query no banco de dados
            this.setResultSet(getStatement().executeQuery(pSQL));
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Executa consultas SQL
     *
     * @param pSQL
     * @param argumentos
     * @return boolean
     */
    public boolean executarSQL(String pSQL, List<Object> argumentos) {
        try {
            // Crio o PreparedStatement
            PreparedStatement pS = getCon().prepareStatement(pSQL);
            // Atribuo os argumentos ao PreparedStatement
            for (int index = 0; index < argumentos.size(); index++) {
                pS.setObject(index + 1, argumentos.get(index));
            }
            // Executo o SQL
            this.setResultSet(pS.executeQuery());
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Executa atualizações SQL
     *
     * @param pSQL
     * @param argumentos
     * @return boolean
     */
    public boolean executarUpdateDeleteSQL(String pSQL, List<Object> argumentos) {
        try {
            // Crio o PreparedStatement
            PreparedStatement pS = getCon().prepareStatement(pSQL);
            // Atribuo os argumentos ao PreparedStatement
            for (int index = 0; index < argumentos.size(); index++) {
                pS.setObject(index + 1, argumentos.get(index));
            }
            // Executo o SQL
            pS.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Executa insert SQL
     *
     * @param pSQL
     * @param argumentos
     * @return int
     */
    public int insertSQL(String pSQL, List<Object> argumentos) {
        int id = 0;
        try {
            // Crio o PreparedStatement
            PreparedStatement pS = getCon().prepareStatement(pSQL);
            // Atribuo os argumentos ao PreparedStatement
            for (int index = 0; index < argumentos.size(); index++) {
                pS.setObject(index + 1, argumentos.get(index));
            }
            pS.executeUpdate();
            // Definido o PreparedStatement, executo o SQL no banco de dados
            // Depois do SQL ter sido executado, consulto o último id inserido
            this.setResultSet(pS.executeQuery("SELECT last_insert_id();"));
            // Recupera o último id inserido
            while (this.resultSet.next()) {
                id = this.resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            return id;
        }
    }

    /**
     * Encerra a conexão corrente
     *
     * @return boolean
     */
    public boolean fecharConexao() {
        try {
            if ((this.getResultSet() != null) && (this.statement != null)) {
                this.getResultSet().close();
                this.statement.close();
            }
            this.getCon().close();
            return true;
        } catch (SQLException e) {
            System.out.println("Fechando a conexão!\n" + e.getMessage());
        }
        return false;
    }

    /**
     * @return boolean
     */
    public boolean isStatus() {
        return this.status;
    }

    /**
     * @return string
     */
    public String getMensagem() {
        return mensagem;
    }

    /**
     * @return statement
     */
    public Statement getStatement() {
        return statement;
    }

    /**
     * @return resultSet
     */
    public ResultSet getResultSet() {
        return resultSet;
    }

    /**
     * @param mensagem
     */
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    /**
     * @return the con
     */
    public Connection getCon() {
        return con;
    }

    /**
     * @param con
     */
    public void setCon(Connection con) {
        this.con = con;
    }

    /**
     * @param statement
     */
    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    /**
     * @param resultSet
     */
    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    /**
     * @return string
     */
    public String getServidor() {
        return servidor;
    }

    /**
     * @param servidor
     */
    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    /**
     * @return string
     */
    public String getNomeDoBanco() {
        return nomeDoBanco;
    }

    /**
     * @param nomeDoBanco
     */
    public void setNomeDoBanco(String nomeDoBanco) {
        this.nomeDoBanco = nomeDoBanco;
    }

    /**
     * @return string
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return string
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }
}
