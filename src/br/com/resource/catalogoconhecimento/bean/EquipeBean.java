package br.com.resource.catalogoconhecimento.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Equipe")
public class EquipeBean {

	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	@Column(name = "idEquipe", unique = true, nullable = false)
	private int id;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "EquipeFuncionario", joinColumns = {
			@JoinColumn(name = "idFuncionario", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "idEquipe", nullable = false, updatable = false) })
	private List<FuncionarioBean> listaFuncionario;

	@Column(name = "observacao")
	private String observacao;

	@Column(name = "nome")
	private String nome;

	@Column(name = "ativo")
	private char ativo;

	public char getAtivo() {
		return ativo;
	}

	public void setAtivo(char ativo) {
		this.ativo = ativo;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
