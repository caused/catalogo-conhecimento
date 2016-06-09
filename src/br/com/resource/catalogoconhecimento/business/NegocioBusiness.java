package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;
import br.com.resource.catalogoconhecimento.bean.NegocioBean;
import br.com.resource.catalogoconhecimento.dao.NegocioDAO;
import br.com.resource.catalogoconhecimento.exceptions.AtributoNuloException;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;
import br.com.resource.catalogoconhecimento.exceptions.ConsultaNulaException;
import br.com.resource.catalogoconhecimento.exceptions.NomeRepetidoException;
import br.com.resource.catalogoconhecimento.exceptions.RegistroVinculadoException;
import br.com.resource.catalogoconhecimento.exceptions.TamanhoCampoException;
import br.com.resource.catalogoconhecimento.utils.ExceptionUtil;

public class NegocioBusiness {

	public void inserir(NegocioBean negocioBean) throws BusinessException {
		try {

			NegocioDAO negocioDao = new NegocioDAO();
			NegocioBean negocioDesativado = this.obterNomeDesativado(negocioBean);
			NegocioBean negocioClone = this.obterPorNome(negocioBean.getAreaAtuacao());

			if (!validarAreaAtuacao(negocioBean.getAreaAtuacao())) {
				throw new TamanhoCampoException("Por Favor, digite uma �rea de atua��o v�lida!");
			} else if (negocioDesativado != null) {
				this.reativar(negocioBean);
			} else if (negocioClone != null) {
				throw new NomeRepetidoException("Este nome j� exite na base de dados.");
			} else {
				negocioDao.adicionar(negocioBean);
			}

		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	public List<NegocioBean> listar() throws ClassNotFoundException, SQLException, ConsultaNulaException {
		NegocioDAO negocioDao = new NegocioDAO();
		List<NegocioBean> listaNegocio = negocioDao.listar();

		if (listaNegocio.isEmpty()) {
			throw new ConsultaNulaException("N�o h� neg�cios cadastrados");
		} else {
			return listaNegocio;
		}

	}

	public NegocioBean obterPorId(int id) throws ClassNotFoundException, SQLException {
		NegocioDAO negociodao = new NegocioDAO();

		return negociodao.obterPorId(id);
	}

	public NegocioBean obterPorNome(String areaAtuacao) throws BusinessException {
		try {
			NegocioDAO negociodao = new NegocioDAO();

			return negociodao.obterPorNome(areaAtuacao);

		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	public NegocioBean obterNomeDesativado(NegocioBean negocioBean) throws ClassNotFoundException, SQLException {
		NegocioDAO negocioDao = new NegocioDAO();

		return negocioDao.obterNomeDesativado(negocioBean);
	}

	public void alterar(NegocioBean negocioBean) throws BusinessException {
		try {

			NegocioDAO negocioDao = new NegocioDAO();
			NegocioBean negocioClone = this.obterPorNome(negocioBean.getAreaAtuacao());

			if (!validarAreaAtuacao(negocioBean.getAreaAtuacao())) {
				throw new TamanhoCampoException("Por Favor, digite uma �rea de atua��o v�lida!");
			} else if (negocioClone != null && negocioClone.getId() != negocioBean.getId()) {
				throw new NomeRepetidoException("Este nome j� exite na base de dados");
			} else {
				negocioDao.alterar(negocioBean);
			}

		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	public void remover(int id) throws ClassNotFoundException, SQLException, RegistroVinculadoException {
		NegocioDAO negocioDao = new NegocioDAO();
		if (negocioDao.verificarPorProjeto(id)) {
			negocioDao.remover(id);
		} else {
			throw new RegistroVinculadoException("Registro n�o pode ser removido pois possui v�nculos");
		}
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
		return (areaAtuacao.matches("[A-Za-z�-�0-9'\\s]{1,50}"));
	}

}
