package br.com.resource.catalogoconhecimento.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.resource.catalogoconhecimento.bean.NegocioBean;
import br.com.resource.catalogoconhecimento.bean.ProjetoBean;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;

@Repository
public class NegocioDAO extends GenericDAOImpl<NegocioBean, Integer> {

	public List<NegocioBean> listar() {
		TypedQuery<NegocioBean> query = entityManager.createQuery(
				"SELECT n FROM NegocioBean AS n WHERE n.ativo = 'S' order by n.areaAtuacao asc", NegocioBean.class);
		List<NegocioBean> listaNegocio = query.getResultList();
		return listaNegocio;
	}

	public void reativar(NegocioBean negocioBean) throws BusinessException {
		try {
			TypedQuery<NegocioBean> query = entityManager.createQuery(
					"UPDATE NegocioBean AS n SET n.ativo = 'S' WHERE n.areaAtuacao = :areaAtuacao", NegocioBean.class);
			query.setParameter("areaAtuacao", negocioBean.getAreaAtuacao()).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public NegocioBean obterNomeDesativado(NegocioBean negocioBean) throws BusinessException {
		TypedQuery<NegocioBean> query = entityManager.createQuery(
				"SELECT n FROM NegocioBean AS n WHERE n.areaAtuacao = :areaAtuacao AND ativo = 'n'", NegocioBean.class);
		query.setParameter("areaAtuacao", negocioBean.getAreaAtuacao());
		List<NegocioBean> negocioBeanDesativado = query.getResultList();
		if (negocioBeanDesativado.isEmpty()) {
			return null;
		} else {
			return negocioBeanDesativado.get(0);
		}
	}

	public NegocioBean obterPorNome(String nome) throws BusinessException {
		TypedQuery<NegocioBean> query = entityManager.createQuery(
				"SELECT n FROM NegocioBean AS n WHERE n.areaAtuacao = :areaAtuacao AND n.ativo = 'S'",
				NegocioBean.class);
		query.setParameter("areaAtuacao", nome);
		List<NegocioBean> listaNegocio = query.getResultList();
		if (listaNegocio.isEmpty()) {
			return null;
		} else {
			return listaNegocio.get(0);
		}
	}

	public List<NegocioBean> obterPorProjeto(ProjetoBean projeto) throws BusinessException {
		TypedQuery<NegocioBean> query = entityManager
				.createQuery("SELECT n FROM ProjetoBean p JOIN p.listaNegocio n WHERE p.id = :id", NegocioBean.class);
		query.setParameter("id", projeto.getId());
		return query.getResultList();
	}

	public boolean verificarPorProjeto(int id) throws BusinessException {
		TypedQuery<ProjetoBean> query = entityManager.createQuery(
				"SELECT n FROM ProjetoBean AS p join p.listaNegocio AS n WHERE p.id = :id", ProjetoBean.class);
		query.setParameter("id", id);
		List<ProjetoBean> equipes = query.getResultList();
		if (equipes.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	public List<NegocioBean> listarPorFuncionario(int id) throws BusinessException {
		TypedQuery<NegocioBean> query = entityManager.createQuery(
				"SELECT n FROM FuncionarioBean AS f JOIN f.listaNegocio AS n WHERE f.id = :id AND f.ativo = 'S'",
				NegocioBean.class);
		query.setParameter("id", id);
		return query.getResultList();
	}
}
