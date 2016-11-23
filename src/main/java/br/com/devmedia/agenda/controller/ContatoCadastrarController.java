package br.com.devmedia.agenda.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.devmedia.agenda.model.entidade.Contato;
import br.com.devmedia.agenda.model.entidade.Endereco;
import br.com.devmedia.agenda.model.entidade.Grupo;
import br.com.devmedia.agenda.model.entidade.Telefone;
import br.com.devmedia.agenda.service.AgendaFacade;
import br.com.devmedia.agenda.service.AgendaFacadeImpl;
import br.com.devmedia.agenda.util.IsNullUtil;
import br.com.devmedia.agenda.util.MensagemUtil;

@ManagedBean(name = "contatoCadastrarController")
@SessionScoped
public class ContatoCadastrarController implements Serializable {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = -8519093529481525831L;

	private AgendaFacade facade;

	private List<Grupo> grupos;

	private Contato contato;

	private Telefone telefoneSelecionado;

	private Grupo grupoSelecionado;

	@PostConstruct
	public void init() {

		this.facade = new AgendaFacadeImpl();

		this.grupos = this.facade.buscarTodosGrupos();

		if (this.contato == null) {

			this.contato = new Contato();

			this.contato.setEndereco(new Endereco());

			this.contato.setTelefones(new ArrayList<>());

			this.contato.setGrupos(new ArrayList<>());
		}

	}

	public void salvar() {

		try {

			this.tratarEndereco();

			this.tratarTelefones();

			this.tratarGrupos();

			this.validarCamposObrigatorios();

			this.facade.salvarContato(this.contato);

			this.novo();

			MensagemUtil.show("Ação realizada com sucesso!!!");

		} catch (Exception e) {

			MensagemUtil.showErro("Ops! Não foi possível completar sua ação. \n\t" + e.getMessage());
		}
	}

	private void validarCamposObrigatorios() {

		if (IsNullUtil.isNotNullOrEmpty(this.contato.getNome())) {

			throw new IllegalStateException("Nome é obrigatório!");
		}

		if (IsNullUtil.isNotNullOrEmpty(this.contato.getDataNascimento())) {

			throw new IllegalStateException("Data de nascimento é obrigatória!");
		}

		this.validarEndereco();

	}

	private void validarEndereco() {

		this.tratarEndereco();

		if (IsNullUtil.isNotNull(this.contato.getEndereco())) {

			if (IsNullUtil.isNullOrEmpty(this.contato.getEndereco().getDescricao())) {

				throw new IllegalStateException("Endereço: Descrição é obrigatória!");
			}
			
			if (IsNullUtil.isNullOrEmpty(this.contato.getEndereco().getNumero())) {

				throw new IllegalStateException("Endereço: Número é obrigatório!");
			}
			
			if (IsNullUtil.isNullOrEmpty(this.contato.getEndereco().getCep())) {

				throw new IllegalStateException("Endereço: Cep é obrigatório!");
			}
		}
	}

	private void tratarEndereco() {

		if (IsNullUtil.isNotNull(this.contato.getEndereco())) {
			if (IsNullUtil.isNullOrEmpty(this.contato.getEndereco().getDescricao())

					&& IsNullUtil.isNullOrEmpty(this.contato.getEndereco().getCep())

					&& IsNullUtil.isNullOrEmpty(this.contato.getEndereco().getNumero())) {

				this.contato.setEndereco(null);

			}
		}

	}

	private void tratarTelefones() {

		if (IsNullUtil.isNotNull(this.contato.getTelefones())) {

			this.contato.setTelefones(null);
		}

	}

	private void tratarGrupos() {

		if (IsNullUtil.isNotNull(this.contato.getGrupos())) {

			this.contato.setGrupos(null);
		}

	}

	public String novo() {

		this.telefoneSelecionado = new Telefone();

		this.grupoSelecionado = new Grupo();

		if (this.contato != null && this.contato.getId() != null) {

			this.contato = this.facade.buscarContatoPorID(this.contato.getId());

			if (this.contato.getEndereco() == null) {

				this.contato.setEndereco(new Endereco());
			}

			if (this.contato.getTelefones() == null) {

				this.contato.setTelefones(new ArrayList<>());
			}

			if (this.contato.getGrupos() == null) {

				this.contato.setGrupos(new ArrayList<>());
			}
		} else {

			this.contato = new Contato();

			this.contato.setEndereco(new Endereco());

			this.contato.setTelefones(new ArrayList<>());

			this.contato.setGrupos(new ArrayList<>());
		}

		return "contato-cadastrar";
	}

	public void adicionarTelefone() {

		try {
			if (this.telefoneSelecionado.getDdd() == null) {
				MensagemUtil.showErro("Informe o DDD do telefone.");
				return;
			}

			if (this.telefoneSelecionado.getNumero() == null) {
				MensagemUtil.showErro("Informe o número do telefone.");
				return;
			}

			this.contato.getTelefones().add(telefoneSelecionado);

			MensagemUtil.show("Ação realizada com sucesso!!!");
		} catch (Exception e) {

			MensagemUtil.showErro("Ops! Não foi possível completar sua ação. \n\t" + e.getMessage());
		}

	}

	public void adicionarGrupo() {

		try {
			if (this.grupoSelecionado == null) {
				MensagemUtil.showErro("Selecione o Grupo para esta ação.");
				return;
			}

			if (this.contato.getGrupos().stream().filter(g -> g.getNome().equalsIgnoreCase(this.grupoSelecionado.getNome())).count() > 0) {
				MensagemUtil.showErro("Este grupo já foi adicionado anteriormente.");
				return;

			}

			this.contato.getGrupos().add(this.grupoSelecionado);

			MensagemUtil.show("Ação realizada com sucesso!!!");
		} catch (Exception e) {

			MensagemUtil.showErro("Ops! Não foi possível completar sua ação. \n\t" + e.getMessage());
		}

	}

	public void excluirTelefone() {

		try {

			MensagemUtil.show("Ação realizada com sucesso!!!");
		} catch (Exception e) {

			MensagemUtil.showErro("Ops! Não foi possível completar sua ação. \n\t" + e.getMessage());
		}
	}

	public void excluirGrupo() {

		try {

			MensagemUtil.show("Ação realizada com sucesso!!!");
		} catch (Exception e) {

			MensagemUtil.showErro("Ops! Não foi possível completar sua ação. \n\t" + e.getMessage());
		}
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

	/**
	 * Retorna o valor do atributo <code>telefoneSelecionado</code>
	 *
	 * @return <code>Telefone</code>
	 */
	public Telefone getTelefoneSelecionado() {

		return telefoneSelecionado;
	}

	/**
	 * Define o valor do atributo <code>telefoneSelecionado</code>.
	 *
	 * @param telefoneSelecionado
	 */
	public void setTelefoneSelecionado(Telefone telefoneSelecionado) {

		this.telefoneSelecionado = telefoneSelecionado;
	}

	/**
	 * Retorna o valor do atributo <code>grupoSelecionado</code>
	 *
	 * @return <code>Grupo</code>
	 */
	public Grupo getGrupoSelecionado() {

		return grupoSelecionado;
	}

	/**
	 * Define o valor do atributo <code>grupoSelecionado</code>.
	 *
	 * @param grupoSelecionado
	 */
	public void setGrupoSelecionado(Grupo grupoSelecionado) {

		this.grupoSelecionado = grupoSelecionado;
	}

}
