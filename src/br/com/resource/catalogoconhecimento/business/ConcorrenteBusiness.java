package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;
import br.com.resource.catalogoconhecimento.bean.ConcorrenteBean;
import br.com.resource.catalogoconhecimento.bean.ConcorrenteClienteBean;
import br.com.resource.catalogoconhecimento.dao.ConcorrenteDAO;
import br.com.resource.catalogoconhecimento.exceptions.AtributoNuloException;
import br.com.resource.catalogoconhecimento.exceptions.ConsultaNulaException;
import br.com.resource.catalogoconhecimento.exceptions.NomeRepetidoException;
import br.com.resource.catalogoconhecimento.exceptions.TamanhoCampoException;

public class ConcorrenteBusiness {

	public void adicionar(ConcorrenteBean concorrenteBean) throws SQLException, ClassNotFoundException,
			TamanhoCampoException, NomeRepetidoException, AtributoNuloException {
		ConcorrenteDAO concorrenteDao = new ConcorrenteDAO();
		if (concorrenteBean.getNome().equals("")) {
			throw new AtributoNuloException("Por favor, digite um nome v�lido!");
		} else if (concorrenteBean.getNome().length() > 50) {
			throw new TamanhoCampoException("N�mero limite de caracteres excedido(m�x.50)");
		} else if (this.existe(concorrenteBean)) {
			this.reativar(concorrenteBean);
		} else {
			concorrenteDao.adicionar(concorrenteBean);
		}
	}

	public List<ConcorrenteBean> listar() throws SQLException, ClassNotFoundException, ConsultaNulaException {
		ConcorrenteDAO concorrenteDao = new ConcorrenteDAO();
		List<ConcorrenteBean> listaConcorrente = concorrenteDao.listar();
		if (listaConcorrente.isEmpty()) {
			throw new ConsultaNulaException("N�o h� concorrentes cadastrados!");
		} else {
			return listaConcorrente;
		}
	}

	public List<ConcorrenteClienteBean> listarConcorrenteCliente(int idConcorrente)
			throws SQLException, ClassNotFoundException {
		ConcorrenteDAO concorrenteDao = new ConcorrenteDAO();
		return concorrenteDao.listarConcorrenteCliente(idConcorrente);
	}

	public ConcorrenteBean obterPorId(int idConcorrente) throws SQLException, ClassNotFoundException {
		ConcorrenteDAO concorrenteDao = new ConcorrenteDAO();
		return concorrenteDao.obterPorId(idConcorrente);
	}

	public ConcorrenteBean obterPorNome(String nomeConcorrente) throws ClassNotFoundException, SQLException {
		ConcorrenteDAO concorrenteDao = new ConcorrenteDAO();
		return concorrenteDao.obterPorNome(nomeConcorrente);
	}

	public boolean existe(ConcorrenteBean concorrenteBean)
			throws SQLException, ClassNotFoundException {
		ConcorrenteDAO concorrenteDao = new ConcorrenteDAO();
		return concorrenteDao.existe(concorrenteBean);
	}

	public List<ConcorrenteClienteBean> obterPorCliente(int idCliente) throws ClassNotFoundException, SQLException {
		ConcorrenteDAO concorrenteDao = new ConcorrenteDAO();
		return concorrenteDao.obterPorCliente(idCliente);
	}

	public void alterar(ConcorrenteBean concorrenteBean) throws SQLException, ClassNotFoundException,
			TamanhoCampoException, NomeRepetidoException, AtributoNuloException {
		ConcorrenteBean concorrenteClone = this.obterPorNome(concorrenteBean.getNome());
		if (concorrenteBean.getNome().equals("")) {
			throw new AtributoNuloException("Por favor, digite um nome v�lido!");
		} else if (concorrenteBean.getNome().length() > 50) {
			throw new TamanhoCampoException("N�mero limite de caracteres excedido(m�x.50)");
		} else if (this.existe(concorrenteBean)) {
			this.reativar(concorrenteBean);
		} else if (concorrenteClone != null && concorrenteClone.getId() != concorrenteBean.getId()) {
			throw new NomeRepetidoException("Este nome j� est� cadastrado!");
		} else {
			ConcorrenteDAO concorrenteDao = new ConcorrenteDAO();
			concorrenteDao.alterar(concorrenteBean);
		}
	}

	public void remover(int idConcorrente) throws ClassNotFoundException, SQLException {
		ConcorrenteDAO concorrenteDao = new ConcorrenteDAO();
		concorrenteDao.remover(idConcorrente);
	}

	public void reativar(ConcorrenteBean concorrenteBean) throws SQLException, ClassNotFoundException {
		ConcorrenteDAO concorrenteDao = new ConcorrenteDAO();
		concorrenteDao.reativar(concorrenteBean);
	}
}
