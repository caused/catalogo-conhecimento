package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.resource.catalogoconhecimento.bean.EquipeBean;
import br.com.resource.catalogoconhecimento.dao.EquipeDAO;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;
import br.com.resource.catalogoconhecimento.exceptions.ConsultaNulaException;
import br.com.resource.catalogoconhecimento.exceptions.NomeRepetidoException;
import br.com.resource.catalogoconhecimento.exceptions.TamanhoCampoException;
import br.com.resource.catalogoconhecimento.utils.ExceptionUtil;

@Component
public class EquipeBusiness {

	@Autowired
	private EquipeDAO equipeDAO;

	@Autowired
	private ProjetoBusiness projetoBusiness;

	// INSERIR NA BASE
	@Transactional
	public void adicionar(EquipeBean equipeBean) throws BusinessException {
		try {
			EquipeBean equipeigual = equipeDAO.obterPorNome(equipeBean.getNome().trim());
			if (!validarNome(equipeBean.getNome())) {
				throw new TamanhoCampoException("Por Favor, digite um nome válido");
			} else if (equipeigual != null && equipeigual.getId() != equipeBean.getId()) {
				throw new NomeRepetidoException("Esta equipe já consta na base de dados");
			} else if (equipeBean.getObservacao().length() > 500) {
				throw new TamanhoCampoException("Número limite de caracteres excedido (max 500)");
			} else {
				equipeDAO.adicionar(equipeBean);
			}
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	// DELETAR NA BASE
	@Transactional
	public void remover(EquipeBean equipe) throws BusinessException {
		try {
			equipeDAO.remover(equipe);
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	// ATUALIZAR NA BASE
	@Transactional
	public void alterar(EquipeBean equipe) throws BusinessException {
		try {
			EquipeBean equipeigual = equipeDAO.obterPorNome(equipe.getNome());
			if (!validarNome(equipe.getNome()) || equipe.getNome().equals("")) {
				throw new TamanhoCampoException("Por Favor, digite um nome válido");
			} else if (equipeigual != null && equipeigual.getId() != equipe.getId()) {
				throw new NomeRepetidoException("Este nome já consta na base de dados");
			} else if (equipe.getObservacao().length() > 500) {
				throw new TamanhoCampoException("Número limite de caracteres excedido (max 500)");
			} else {
				equipeDAO.alterar(equipe);
			}
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	// LISTAR NA BASE
	@Transactional
	public List<EquipeBean> listar() throws BusinessException {
		try {
			List<EquipeBean> listaEquipe = equipeDAO.listar();

			if (listaEquipe.isEmpty()) {
				throw new ConsultaNulaException("Não há equipes cadastradas");
			} else {
				return listaEquipe;
			}
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	// LISTAR POR ID NA BASE
	@Transactional
	public EquipeBean obterPorId(int id) throws BusinessException {
		try {
			return equipeDAO.obterPorId(id);
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	// LISTAR POR NOME NA BASE
	@Transactional
	public EquipeBean obterPorNome(String nome) throws ClassNotFoundException, SQLException {
		return equipeDAO.obterPorNome(nome);
	}

	// DELETAR POR EQUIPE NA BASE
	@Transactional
	public void removerPorEquipe(int idEquipe, int idFuncionario) throws BusinessException {
		try {
			equipeDAO.removerPorEquipe(idEquipe, idFuncionario);
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	@Transactional
	public List<EquipeBean> obterPorFuncionario(int idFuncionario) throws BusinessException {
		try {
			return equipeDAO.obterPorFuncionario(idFuncionario);
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	@Transactional
	public List<EquipeBean> obterPorProjeto(int idProjeto) throws BusinessException {
		try {
			return equipeDAO.obterPorProjeto(projetoBusiness.obterPorId(idProjeto));
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	public boolean validarNome(String nome) {
		return (nome.matches("[A-Za-zÀ-ú0-9+'\\-\\s]{1,50}"));
	}

}
