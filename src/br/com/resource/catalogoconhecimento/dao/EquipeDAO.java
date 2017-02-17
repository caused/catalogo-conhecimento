
package br.com.resource.catalogoconhecimento.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.resource.catalogoconhecimento.bean.EquipeBean;
import br.com.resource.catalogoconhecimento.bean.EquipeFuncionarioBean;
import br.com.resource.catalogoconhecimento.bean.ProjetoBean;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;

@Repository
public class EquipeDAO extends GenericDAOImpl<EquipeBean, Integer> {

	// SELECIONAR DADOS NA TABELA DE EQUIPE
	public List<EquipeBean> listar() {
		TypedQuery<EquipeBean> query = entityManager.createQuery("SELECT e FROM EquipeBean AS e WHERE e.ativo = 'S'",
				EquipeBean.class);
		List<EquipeBean> listaEquipe = query.getResultList();
		return listaEquipe;
	}

	// SELECIONAR DADOS NA TABELA DE EQUIPE PELO ID
	public EquipeBean obterPorId(int id) {
		TypedQuery<EquipeBean> query = entityManager
				.createQuery("SELECT e FROM EquipeBean as e WHERE e.id = :id AND e.ativo = 'S'", EquipeBean.class);
		query.setParameter("id", id);
		List<EquipeBean> listaEquipe = query.getResultList();
		if (listaEquipe.isEmpty()) {
			return null;
		} else {
			return listaEquipe.get(0);
		}
	}

	// SELECIONAR DADOS NA TABELA DE EQUIPE PELO NOME
	public EquipeBean obterPorNome(String nome) throws BusinessException {
		TypedQuery<EquipeBean> query = entityManager
				.createQuery("SELECT e FROM EquipeBean AS e WHERE e.nome = :nome AND e.ativo = 'S'", EquipeBean.class);
		query.setParameter("nome", nome);
		List<EquipeBean> listaEquipe = query.getResultList();
		if (listaEquipe.isEmpty()) {
			return null;
		} else {
			return listaEquipe.get(0);
		}
	}

	// DELETAR DADOS NA TABELA POR EQUIPE
	public void removerPorEquipe(int idEquipe, int idFuncionario) throws BusinessException {
		Query query = entityManager.createQuery(
				"DELETE FROM EquipeFuncionarioBean AS e WHERE e.idEquipe = :idEquipe AND e.idFuncionario = :idFuncionario");
		query.setParameter("idEquipe", idEquipe);
		query.setParameter("idFuncionario", idFuncionario);
		query.executeUpdate();
	}

	// SELECIONAR DADOS NA TABELA POR PROJETO
	public List<EquipeBean> obterPorProjeto(ProjetoBean projeto) throws BusinessException {
		TypedQuery<EquipeBean> query = entityManager.createQuery(
				"SELECT e FROM ProjetoBean AS p JOIN p.listaEquipe AS e WHERE p.id = :id AND e.ativo = 'S' AND p.ativo = 'S'",
				EquipeBean.class);
		query.setParameter("id", projeto.getId());
		return query.getResultList();
	}

	public List<EquipeBean> obterPorFuncionario(int idFuncionario) throws BusinessException {
		TypedQuery<EquipeBean> query = entityManager.createQuery(
				"SELECT e FROM FuncionarioBean AS f JOIN f.equipes AS e WHERE f.id = :id", EquipeBean.class);
		query.setParameter("id", idFuncionario);
		return query.getResultList();
	}

	public boolean verificarPorFuncionarios(int id) throws BusinessException {
		TypedQuery<EquipeFuncionarioBean> query = entityManager.createQuery(
				"SELECT e FROM EquipeFuncionarioBean e WHERE e.idEquipe = :id", EquipeFuncionarioBean.class);
		query.setParameter("id", id);

		List<EquipeFuncionarioBean> lista = query.getResultList();

		if (lista.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

}
