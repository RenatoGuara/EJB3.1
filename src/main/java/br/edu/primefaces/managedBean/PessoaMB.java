/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.primefaces.managedBean;

import br.edu.primefaces.DAO.PessoaRepository;
import br.edu.primefaces.model.Pessoa;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author renato
 */
@Named
@RequestScoped
public class PessoaMB implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private final PessoaRepository repository = new PessoaRepository();

    private Pessoa pessoa = new Pessoa();
    private List<Pessoa> pessoas;

    public void novo(Pessoa pessoa) {
        pessoa = new Pessoa();
    }

    public void gravar(Pessoa pessoa) {
        FacesContext context = FacesContext.getCurrentInstance();
        boolean resultado = repository.gravar(pessoa);

        if (resultado) {
            pessoa = new Pessoa();
            context.addMessage(null, new FacesMessage("Pessoa gravado com sucesso"+pessoa.getNome()));
        } else {
            context.addMessage(null, new FacesMessage("Falha ao gravar pessoa!!!"));
        }
    }

    public void selecionar(ActionEvent evento) {
        Long id = (Long) evento.getComponent().getAttributes().get("id");
        pessoa = repository.selecionar(pessoa.getId());
    }

    public void remover(Pessoa pessoa) {
        FacesContext context = FacesContext.getCurrentInstance();
        boolean resultado = repository.remover(pessoa);

        if (resultado) {
            pessoa = new Pessoa();
            context.addMessage(null, new FacesMessage("Pessoa removido com sucesso"));
        } else {
            context.addMessage(null, new FacesMessage("Falha ao remover pessoa!"));
        }
    }
    
    //Getters e Setters

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }
    
    
}
