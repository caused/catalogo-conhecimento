package br.com.resource.catalogoconhecimento.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;



@Entity
@Table(name = "Usuario")
public class UsuarioBean {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	@Column(name = "idUsuario", unique = true, nullable = false)
	public int idUsuario;
	
	 @ManyToOne
	 @Cascade({CascadeType.SAVE_UPDATE, CascadeType.MERGE})
     @JoinTable(name="usuarioPerfil",
               joinColumns={@JoinColumn(name="idUsuario",  
                referencedColumnName="idUsuario")},  
               inverseJoinColumns={@JoinColumn(name="idPerfil",   
                referencedColumnName="idPerfil")})  
	 private PerfilBean perfilBean;
	 
	
	public PerfilBean getPerfilBean() {
		return perfilBean;
	}

	public void setPerfilBean(PerfilBean perfilBean) {
		this.perfilBean = perfilBean;
	}

	
	@Column(name ="nomeUsuario")
	public String nome;
	
	@Column(name ="loginUsuario")
	public String login;
	
	@Column(name ="senhaUsuario")
	public String senha;
	
	@Column(name = "ativo")
	public char ativo;

	
	public char getAtivo() {
		return ativo;
	}

	public void setAtivo(char ativo) {
		this.ativo = ativo;
	}

	public int getId() {
		return idUsuario;
	}

	public void setId(int idUsuario) {
		this.idUsuario = idUsuario;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
