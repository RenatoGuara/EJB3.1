/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.primefaces.DAO;

import br.edu.primefaces.model.Pessoa;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author renato
 */

@Stateless
public class PessoaRepository {

    @PersistenceContext
    private EntityManager manager;

    public boolean gravar(Pessoa pessoa) {
        boolean sucesso = false;
        try {
            manager.merge(pessoa);
            sucesso = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sucesso;
    }

    public Pessoa selecionar(Long codigo) {
        Pessoa pessoa = null;
        try {
            pessoa = manager.find(Pessoa.class, codigo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pessoa;
    }

    public boolean remover(Pessoa pessoa) {
        boolean sucesso = false;
        try {
            pessoa = manager.find(Pessoa.class, pessoa.getId());
            manager.remove(pessoa);
            sucesso = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sucesso;
    }

    public List<Pessoa> listarPessoa() {
        List<Pessoa> pessoas = null;
        try {
            Query query = manager.createQuery("Select p from Pessoa p");
            pessoas = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pessoas;
    }

}
