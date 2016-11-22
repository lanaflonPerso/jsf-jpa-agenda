package br.com.devmedia.agenda.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import br.com.devmedia.agenda.model.entidade.Contato;
import br.com.devmedia.agenda.service.AgendaFacade;
import br.com.devmedia.agenda.service.AgendaFacadeImpl;
import br.com.devmedia.agenda.util.MensagemUtil;

@ManagedBean(name = "contatoListarController")
@ViewScoped
public class ContatoListarController {

	private AgendaFacade facade;

	private List<Contato> contatos;

	private Contato contatoSelecionado;

	@PostConstruct
	public void init() {

		this.facade = new AgendaFacadeImpl();

		setContatos(facade.buscarTodosContatos());
	}

	public void excluir() {

		try {

			if (contatoSelecionado != null) {

				this.facade.excluirContato(contatoSelecionado);

				this.contatos = this.facade.buscarTodosContatos();

				MensagemUtil.showInfo("Ação realizada com sucesso");
			} else {

				MensagemUtil.showErro("Não foi possível recuperar o contato para exclusão!");
			}

		} catch (Exception e) {

			MensagemUtil.showFatal(e.getMessage());
		}
	}

	/**
	 * Retorna o valor do atributo <code>contatos</code>
	 *
	 * @return <code>List<Contato></code>
	 */
	public List<Contato> getContatos() {

		return contatos;
	}

	/**
	 * Define o valor do atributo <code>contatos</code>.
	 *
	 * @param contatos
	 */
	public void setContatos(List<Contato> contatos) {

		this.contatos = contatos;
	}

	/**
	 * Retorna o valor do atributo <code>contatoSelecionado</code>
	 *
	 * @return <code>Contato</code>
	 */
	public Contato getContatoSelecionado() {

		return contatoSelecionado;
	}

	/**
	 * Define o valor do atributo <code>contatoSelecionado</code>.
	 *
	 * @param contatoSelecionado
	 */
	public void setContatoSelecionado(Contato contatoSelecionado) {

		this.contatoSelecionado = contatoSelecionado;
	}

}
