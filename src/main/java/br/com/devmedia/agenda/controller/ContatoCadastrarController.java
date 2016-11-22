package br.com.devmedia.agenda.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import br.com.devmedia.agenda.model.entidade.Contato;
import br.com.devmedia.agenda.model.entidade.Grupo;
import br.com.devmedia.agenda.model.entidade.Telefone;
import br.com.devmedia.agenda.service.AgendaFacade;
import br.com.devmedia.agenda.service.AgendaFacadeImpl;
import br.com.devmedia.agenda.util.MensagemUtil;

@ManagedBean(name = "contatoCadastrarController")
@ViewScoped
public class ContatoCadastrarController {

	private AgendaFacade facade;

	private List<Telefone> telefones;

	private List<Grupo> grupos;

	private Contato contato;

	@PostConstruct
	public void init() {

		this.facade = new AgendaFacadeImpl();

		this.grupos = this.facade.buscarTodosGrupos();

	}

	public void salvar() {

		MensagemUtil.show("Ação realizada com sucesso!!!");
	}

	public String novo() {

		if (this.contato != null) {

			MensagemUtil.show("Editando Contato: " + this.contato.toString());
		} else {

			MensagemUtil.show("Novo Contato");
		}

		return "contato-cadastrar";
	}

	/**
	 * Retorna o valor do atributo <code>telefones</code>
	 *
	 * @return <code>List<Telefone></code>
	 */
	public List<Telefone> getTelefones() {

		return telefones;
	}

	/**
	 * Define o valor do atributo <code>telefones</code>.
	 *
	 * @param telefones
	 */
	public void setTelefones(List<Telefone> telefones) {

		this.telefones = telefones;
	}

	/**
	 * Retorna o valor do atributo <code>grupos</code>
	 *
	 * @return <code>List<Grupo></code>
	 */
	public List<Grupo> getGrupos() {

		return grupos;
	}

	/**
	 * Define o valor do atributo <code>grupos</code>.
	 *
	 * @param grupos
	 */
	public void setGrupos(List<Grupo> grupos) {

		this.grupos = grupos;
	}

	/**
	 * Retorna o valor do atributo <code>contato</code>
	 *
	 * @return <code>Contato</code>
	 */
	public Contato getContato() {

		return contato;
	}

	/**
	 * Define o valor do atributo <code>contato</code>.
	 *
	 * @param contato
	 */
	public void setContato(Contato contato) {

		this.contato = contato;
	}

}
