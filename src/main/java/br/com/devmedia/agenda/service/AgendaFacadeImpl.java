package br.com.devmedia.agenda.service;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.devmedia.agenda.model.dao.ContatoDao;
import br.com.devmedia.agenda.model.dao.GrupoDao;
import br.com.devmedia.agenda.model.dao.TelefoneDao;
import br.com.devmedia.agenda.model.entidade.Contato;
import br.com.devmedia.agenda.model.entidade.Grupo;
import br.com.devmedia.agenda.model.entidade.Telefone;
import br.com.devmedia.agenda.support.PersistenceManager;

/**
 * <p>
 * <b>Title:</b> AgendaFacadeImpl.java
 * </p>
 * 
 * <p>
 * <b>Description:</b> Ponto de integração entre o Front-End(JSF) e nossa camada de persistencia(JPA/Hibernate)
 * </p>
 * 
 * <p>
 * <b>Company: </b> DEVMEDIA
 * </p>
 * 
 * @author DevMedia - http://www.devmedia.com.br/
 * 
 * @version 1.0.0
 */
public class AgendaFacadeImpl implements AgendaFacade {

	private final ContatoDao contatoDao;

	private final GrupoDao grupoDao;

	private final TelefoneDao telefoneDao;

	public AgendaFacadeImpl() {

		this.contatoDao = new ContatoDao();

		this.telefoneDao = new TelefoneDao();

		this.grupoDao = new GrupoDao();

	}

	/**
	 * Descrição Padrão: <br>
	 * <br>
	 *
	 * {@inheritDoc}
	 *
	 * @see br.com.devmedia.agenda.service.AgendaFacade#buscarContatoPorID(java.lang.Long)
	 */
	@Override
	public Contato buscarContatoPorID(Long id) {

		if (id != null) {

			return this.contatoDao.selecionarPorId(id);
		}
		return null;
	}

	/**
	 * Descrição Padrão: <br>
	 * <br>
	 *
	 * {@inheritDoc}
	 *
	 * @see br.com.devmedia.agenda.service.AgendaFacade#buscarTodosGrupos()
	 */
	@Override
	public List<Grupo> buscarTodosGrupos() {

		return this.grupoDao.buscarTodos();

	}

	/**
	 * Descrição Padrão: <br>
	 * <br>
	 *
	 * {@inheritDoc}
	 *
	 * @see br.com.devmedia.agenda.service.AgendaFacade#buscarTodosContatos()
	 */
	@Override
	public List<Contato> buscarTodosContatos() {

		this.clear();

		return this.contatoDao.buscarTodos();
	}

	/**
	 * Descrição Padrão: <br>
	 * <br>
	 *
	 * {@inheritDoc}
	 *
	 * @see br.com.devmedia.agenda.service.AgendaFacade#salvarContato(br.com.devmedia.agenda.model.entidade.Contato)
	 */
	@Override
	public void salvarContato(final Contato contato) {

		try {

			this.beginTransaction();

			// Vinculando os contatos, pois a JPA não faz isso automaticamente
			if (contato.getTelefones() != null) {

				contato.getTelefones().stream().forEach(t -> t.setContato(contato));

			}

			this.contatoDao.salvar(contato);

			this.commitTransaction();

		} catch (final Exception e) {

			this.rollbackTransaction();

			throw e;
		}

	}

	/**
	 * Descrição Padrão: <br>
	 * <br>
	 *
	 * {@inheritDoc}
	 *
	 * @see br.com.devmedia.agenda.service.AgendaFacade#excluirTelefone(br.com.devmedia.agenda.model.entidade.Telefone)
	 */
	@Override
	public void excluirTelefone(final Telefone telefone) {

		try {
			if (telefone != null && telefone.getId() != null) {

				this.beginTransaction();

				final Telefone telefoneParaExclusao = this.telefoneDao.selecionarPorId(telefone.getId());

				this.telefoneDao.excluir(telefoneParaExclusao.getId());

				this.commitTransaction();

			}
		} catch (final Exception e) {

			this.rollbackTransaction();

			throw e;
		}

	}

	/**
	 * Descrição Padrão: <br>
	 * <br>
	 *
	 * {@inheritDoc}
	 *
	 * @see br.com.devmedia.agenda.service.AgendaFacade#excluirContato(br.com.devmedia.agenda.model.entidade.Contato)
	 */
	public void excluirContato(final Contato contato) {

		if (contato != null && contato.getId() != null) {
			try {

				this.beginTransaction();

				// Paso 1: Excluindo os telefones, poderia ser feito via cascade, porém desta forma ficou mais didático
				final Collection<Telefone> telefoneParaExclusao = this.telefoneDao.buscarPorContato(contato);

				telefoneParaExclusao.stream().forEach(t -> this.telefoneDao.excluir(t.getId()));

				// Passo Final: Excluindo Contato, o Endereço será excluído em cascada juntamente
				this.contatoDao.excluir(contato.getId());

				this.commitTransaction();
			} catch (Exception e) {

				this.rollbackTransaction();

				throw e;
			}
		}

	}

	/**
	 * Descrição Padrão: <br>
	 * <br>
	 *
	 * {@inheritDoc}
	 *
	 * @see br.com.devmedia.agenda.service.AgendaFacade#flush()
	 */
	@Override
	public void flush() {

		this.getEntityManager().flush();
	}

	/**
	 * Descrição Padrão: <br>
	 * <br>
	 *
	 * {@inheritDoc}
	 *
	 * @see br.com.devmedia.agenda.service.AgendaFacade#clear()
	 */
	@Override
	public void clear() {

		this.getEntityManager().clear();
	}

	private EntityManager getEntityManager() {

		return PersistenceManager.INSTANCE.getEntityManager();
	}

	private void beginTransaction() {

		this.getEntityManager().getTransaction().begin();
	}

	private void commitTransaction() {

		this.getEntityManager().getTransaction().commit();
	}

	private void rollbackTransaction() {

		this.getEntityManager().getTransaction().rollback();
	}

}
