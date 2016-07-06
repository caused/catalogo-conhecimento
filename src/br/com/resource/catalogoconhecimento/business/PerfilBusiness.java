package br.com.resource.catalogoconhecimento.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.resource.catalogoconhecimento.bean.PerfilBean;
import br.com.resource.catalogoconhecimento.dao.PerfilDAO;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;
import br.com.resource.catalogoconhecimento.exceptions.ConsultaNulaException;
import br.com.resource.catalogoconhecimento.exceptions.NomeRepetidoException;
import br.com.resource.catalogoconhecimento.exceptions.TamanhoCampoException;
import br.com.resource.catalogoconhecimento.utils.ExceptionUtil;

@Component
public class PerfilBusiness {

	@Autowired
	private PerfilDAO perfilDAO;

	@Transactional
	public void adicionar(PerfilBean perfilBean) throws BusinessException {
		try {
			if (perfilDAO.obterPorTipo(perfilBean.getTipo().trim()) != null) {
				throw new NomeRepetidoException("Este tipo j� consta na base de dados");
			} else if (!validarTipo(perfilBean.getTipo())) {
				throw new TamanhoCampoException("N�mero limite de caracteres excedido (min de 4 e max de 30)");
			} else {
				perfilDAO.adicionar(perfilBean);
			}
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	@Transactional
	public List<PerfilBean> listar() throws BusinessException {
		try {
			List<PerfilBean> listaPerfis = perfilDAO.listar();

			if (listaPerfis.isEmpty()) {
				throw new ConsultaNulaException("N�o h� perfis cadastrados");
			} else {
				return listaPerfis;
			}
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	@Transactional
	public void alterar(PerfilBean perfilBean) throws BusinessException {
		try {
			if (perfilDAO.obterPorTipo(perfilBean.getTipo().trim()) != null) {
				throw new NomeRepetidoException("Este tipo j� consta na base de dados");
			} else if (!validarTipo(perfilBean.getTipo())) {
				throw new TamanhoCampoException("N�mero limite de caracteres excedido (min de 4 e max de 30)");
			} else {
				perfilDAO.alterar(perfilBean);
			}
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	@Transactional(readOnly = false)
	public PerfilBean obterPorId(int id) throws BusinessException {
		try {
			return perfilDAO.obterPorId(id);
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	@Transactional
	public void remover(PerfilBean perfilBean) throws BusinessException {
		try {
			// if (equipeDAO.verificarPorFuncionarios(equipe)) {
			perfilDAO.remover(perfilBean);
			// } else {
			// throw new RegistroVinculadoException(
			// "Essa Equipe nÃƒÂ¯Ã‚Â¿Ã‚Â½o pode ser removida, pois possui vÃƒÆ’Ã‚Â­nculos
			// com
			// FuncionÃƒÂ¯Ã‚Â¿Ã‚Â½rios");
			// }
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	public boolean validarTipo(String tipo) {
		return (tipo.matches("[A-Za-z�-�0-9\\s]{4,30}"));
	}

}