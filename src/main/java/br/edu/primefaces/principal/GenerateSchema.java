/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.primefaces.principal;

import br.edu.primefaces.model.Pessoa;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author renato
 */
public class GenerateSchema {

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("Pessoa_PU");

        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        Pessoa p = new Pessoa();
        p.setNome("Renato alves e dona Creuza alves!!!!!");
        manager.persist(p);
        manager.getTransaction().commit();
        System.out.println("Pessoa:" + p.getId() + "==" + p.getNome());

        Query query = manager.createNamedQuery("Pessoa.findALL");
        List<Pessoa> pessoas = query.getResultList();

        for (Pessoa p1 : pessoas) {

            System.out.println("Pessoa:" + p1.getId()+"_"+p1.getNome());
        }
    }
}
