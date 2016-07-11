package br.com.resource.catalogoconhecimento.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.resource.catalogoconhecimento.bean.CargoBean;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;

@Repository
public class CargoDAO extends GenericDAOImpl<CargoBean, Integer> {

	public List<CargoBean> listar() {
		TypedQuery<CargoBean> query = entityManager
				.createQuery("SELECT c FROM CargoBean AS c WHERE c.ativo = 'S' order by c.nome asc", CargoBean.class);
		List<CargoBean> listaCargo = query.getResultList();
		return listaCargo;
	}

	public CargoBean obterPorId(int id) throws BusinessException {
		TypedQuery<CargoBean> query = entityManager
				.createQuery("SELECT c FROM CargoBean AS c WHERE c.id = :id AND c.ativo = 'S'", CargoBean.class);
		CargoBean cargobean = query.setParameter("id", id).getResultList().get(0);
		return cargobean;
	}

	public CargoBean obterPorNome(String nome) throws BusinessException {
		TypedQuery<CargoBean> query = entityManager
				.createQuery("SELECT c FROM CargoBean AS c WHERE c.nome = :nome AND c.ativo = 'S'", CargoBean.class);
		CargoBean cargobean = query.setParameter("nome", nome).getResultList().get(0);
		return cargobean;
	}
}
