/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.inatel.cdg.DAO;

import br.inatel.cdg.model.Entidade;

import java.util.List;

/**
 * Interface DAO, contém declaração dos métodos (CRUD) que as classes que
 * fizerem a implementação deverão executar
 *
 * @author Francielly
 */
public interface DAO<E extends Entidade> {

    //Criação
    public E criar(E e);

    //Consulta
    public List<E> ler(String pesquisa);

    //Consulta
    public E lerPorId(int id);

    //Atualização
    public boolean atualizar(E e);

    //Exclusão
    public boolean excluir(int id);
}
