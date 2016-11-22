package br.com.devmedia.agenda.service;

import java.util.List;

import br.com.devmedia.agenda.model.entidade.Contato;
import br.com.devmedia.agenda.model.entidade.Grupo;
import br.com.devmedia.agenda.model.entidade.Telefone;

public interface AgendaFacade {

	/**
	 * Método responsável por recuperar os grupos para preencher o combobox
	 *
	 * @author DevMedia - http://www.devmedia.com.br/
	 *
	 * @return
	 */
	List<Grupo> buscarTodosGrupos();

	/**
	 * Método responsável por recuperar todos os contatos para listar na tela inicial do CRUD
	 *
	 * @author DevMedia - http://www.devmedia.com.br/
	 *
	 * @return
	 */
	List<Contato> buscarTodosContatos();

	/**
	 * Método responsável por Salvar o contato e suas dependencias
	 *
	 * @author DevMedia - http://www.devmedia.com.br/
	 *
	 * @param entidade
	 */
	void salvarContato(Contato entidade);

	/**
	 * Método responsável por excluir o telefone associado a um determinado contato
	 *
	 * @author DevMedia - http://www.devmedia.com.br/
	 *
	 * @param telefone
	 */
	void excluirTelefone(Telefone telefone);

	/**
	 * Método responsável por excluir o contato e suas dependencias
	 *
	 * @author DevMedia - http://www.devmedia.com.br/
	 *
	 * @param contato
	 */
	void excluirContato(Contato contato);

	void flush();

	void clear();
}
