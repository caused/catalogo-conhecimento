package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;
import org.springframework.stereotype.Component;
import br.com.resource.catalogoconhecimento.bean.NegocioBean;
import br.com.resource.catalogoconhecimento.bean.ProjetoBean;
import br.com.resource.catalogoconhecimento.dao.NegocioDAO;
import br.com.resource.catalogoconhecimento.exceptions.AtributoNuloException;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;
import br.com.resource.catalogoconhecimento.exceptions.ConsultaNulaException;
import br.com.resource.catalogoconhecimento.exceptions.NomeRepetidoException;
import br.com.resource.catalogoconhecimento.exceptions.RegistroVinculadoException;
import br.com.resource.catalogoconhecimento.exceptions.TamanhoCampoException;
import br.com.resource.catalogoconhecimento.utils.ExceptionUtil;

@Component
public class NegocioBusiness {

	public void adicionar(NegocioBean negocioBean) throws BusinessException {
		try {
			NegocioDAO negocioDao = new NegocioDAO();
			NegocioBean negocioDesativado = this.obterNomeDesativado(negocioBean);
			NegocioBean negocioClone = this.obterPorNome(negocioBean.getAreaAtuacao());

			if (!validarAreaAtuacao(negocioBean.getAreaAtuacao())) {
				throw new AtributoNuloException("Por Favor, digite uma area de ataucao valida!");
			} else if (negocioBean.getAreaAtuacao().length() > 80) {
				throw new TamanhoCampoException("Numero limite de caracteres excedido.");
			} else if (negocioDesativado != null) {
				this.reativar(negocioBean);
			} else if (negocioClone != null && negocioClone.getId() != negocioBean.getId()) {
				throw new NomeRepetidoException("Este nome ja exite na base de dados.");
			} else {
				negocioDao.adicionar(negocioBean);
			}

		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	public List<NegocioBean> listar() throws BusinessException {
		try {
			NegocioDAO negocioDao = new NegocioDAO();
			List<NegocioBean> listaNegocio = negocioDao.listar();

			if (listaNegocio.isEmpty()) {
				throw new ConsultaNulaException("Nao ha negocios cadastrados");
			} else {
				return listaNegocio;
			}

		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	public void alterar(NegocioBean negocioBean) throws BusinessException {
		try {
			NegocioDAO negocioDao = new NegocioDAO();
			NegocioBean negocioClone = negocioDao.obterPorNome(negocioBean.getAreaAtuacao());

			if (negocioBean.getAreaAtuacao().equals("")) {
				throw new AtributoNuloException("Por favor, digite uma area de atuacao valida!");
			} else if (negocioBean.getAreaAtuacao().length() > 80) {
				throw new TamanhoCampoException("Numero limite de caracteres excedido.");
			} else if (negocioClone != null && negocioClone.getId() != negocioBean.getId()) {
				throw new NomeRepetidoException("Este nome ja exite na base de dados");
			}  else {
				negocioDao.alterar(negocioBean);
			}

		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	public void remover(int id) throws BusinessException {
		try {
			NegocioDAO negocioDao = new NegocioDAO();
			if (negocioDao.verificarPorProjeto(id)) {
				negocioDao.remover(id);
			} else {
				throw new RegistroVinculadoException("Registro nao pode ser removido pois possui vinculos");
			}

		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	public NegocioBean obterPorId(int id) throws BusinessException {
		try {
			NegocioDAO negocioDao = new NegocioDAO();
			return negocioDao.obterPorId(id);
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	public NegocioBean obterPorNome(String areaAtuacao) throws BusinessException {
		try {
			NegocioDAO negocioDao = new NegocioDAO();
			return negocioDao.obterPorNome(areaAtuacao);
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	public NegocioBean obterNomeDesativado(NegocioBean negocioBean) throws ClassNotFoundException, SQLException {
		NegocioDAO negocioDao = new NegocioDAO();
		return negocioDao.obterNomeDesativado(negocioBean);
	}

	public List<NegocioBean> obterPorFuncionario(int id) throws ClassNotFoundException, SQLException {
		NegocioDAO negocioDao = new NegocioDAO();
		List<NegocioBean> listaNegocios = negocioDao.obterPorFuncionario(id);
		return listaNegocios;
	}

	public void reativar(NegocioBean negocioBean) throws ClassNotFoundException, SQLException {
		NegocioDAO negocioDao = new NegocioDAO();

		negocioDao.reativar(negocioBean);
	}

	public boolean validarAreaAtuacao(String areaAtuacao) {
		return (areaAtuacao.matches("[A-Za-z�-�0-9'\\s]{2,50}"));
	}

	public List<NegocioBean> obterPorProjeto(ProjetoBean projetoBean) throws BusinessException {
		try {
			return new NegocioDAO().obterPorProjeto(projetoBean);
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}
}
