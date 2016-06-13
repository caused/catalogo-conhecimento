package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.resource.catalogoconhecimento.bean.ConcorrenteBean;
import br.com.resource.catalogoconhecimento.bean.ConcorrenteClienteBean;
import br.com.resource.catalogoconhecimento.dao.ConcorrenteDAO;
import br.com.resource.catalogoconhecimento.exceptions.AtributoNuloException;
import br.com.resource.catalogoconhecimento.exceptions.ConsultaNulaException;
import br.com.resource.catalogoconhecimento.exceptions.NomeRepetidoException;
import br.com.resource.catalogoconhecimento.exceptions.RegistroVinculadoException;
import br.com.resource.catalogoconhecimento.exceptions.TamanhoCampoException;

@Component
public class ConcorrenteBusiness {

	private ConcorrenteDAO concorrenteDao;

	public ConcorrenteBusiness() {
		concorrenteDao = new ConcorrenteDAO();
	}

	public void adicionar(ConcorrenteBean concorrenteBean) throws SQLException, ClassNotFoundException,
			TamanhoCampoException, NomeRepetidoException, AtributoNuloException {
		ConcorrenteBean concorrenteClone = this.obterPorNome(concorrenteBean.getNome());

		if (concorrenteBean.getDescricao().equals("")) {
			concorrenteBean.setDescricao("-");
		}

		if (!validarNome(concorrenteBean.getNome())) {
			throw new AtributoNuloException("Por favor, digite um nome v�lido!");
		} else if (concorrenteBean.getNome().length() > 100) {
			throw new TamanhoCampoException("Nome: N�mero limite de caracteres excedido(m�x.50)");
		} else if (concorrenteBean.getDescricao().length() > 255) {
			throw new TamanhoCampoException("Descri��o: N�mero limite de caracteres excedido(m�x.255)");
		} else if (concorrenteClone != null && concorrenteClone.getId() != concorrenteBean.getId()) {
			throw new NomeRepetidoException("Este nome j� est� cadastrado!");
		} else {
			concorrenteDao.adicionar(concorrenteBean);
		}
	}

	public void adicionarConcorrenteCliente(ConcorrenteClienteBean concorrenteClienteBean)
			throws ClassNotFoundException, SQLException, AtributoNuloException {
		if (concorrenteClienteBean.getCliente() == null) {
			throw new AtributoNuloException("Cliente Inv�lido!");
		} else if (concorrenteClienteBean.getConcorrente() == null) {
			throw new AtributoNuloException("Concorrente Inv�lido!");
		} else {
			concorrenteDao.adicionarConcorrenteCliente(concorrenteClienteBean);
		}
	}

	public List<ConcorrenteBean> listar() throws SQLException, ClassNotFoundException, ConsultaNulaException {
		List<ConcorrenteBean> listaConcorrente = concorrenteDao.listar();

		if (listaConcorrente.isEmpty()) {
			throw new ConsultaNulaException("N�o h� concorrentes cadastrados!");
		} else {
			return listaConcorrente;
		}
	}

	public List<ConcorrenteClienteBean> listarPorConcorrente(int idConcorrente)
			throws SQLException, ClassNotFoundException, ConsultaNulaException {
		List<ConcorrenteClienteBean> listaConcorrenteCliente = concorrenteDao.listarPorConcorrente(idConcorrente);

		return listaConcorrenteCliente;
	}

	public List<ConcorrenteClienteBean> listarPorNomeCliente(String nomeCliente)
			throws ClassNotFoundException, SQLException {
		return concorrenteDao.listarPorNomeCliente(nomeCliente);
	}

	public ConcorrenteBean obterPorId(int idConcorrente) throws SQLException, ClassNotFoundException {
		return concorrenteDao.obterPorId(idConcorrente);
	}

	public ConcorrenteBean obterPorNome(String nomeConcorrente) throws ClassNotFoundException, SQLException {
		return concorrenteDao.obterPorNome(nomeConcorrente);
	}

	public boolean existe(ConcorrenteBean concorrenteBean) throws SQLException, ClassNotFoundException {
		return concorrenteDao.existe(concorrenteBean);
	}

	public List<ConcorrenteClienteBean> listarPorCliente(int idCliente)
			throws ClassNotFoundException, SQLException, ConsultaNulaException {
		List<ConcorrenteClienteBean> listaConcorrenteCliente = concorrenteDao.listarPorCliente(idCliente);

		return listaConcorrenteCliente;
	}

	public void alterar(ConcorrenteBean concorrenteBean) throws SQLException, ClassNotFoundException,
			TamanhoCampoException, NomeRepetidoException, AtributoNuloException {
		ConcorrenteBean concorrenteClone = this.obterPorNome(concorrenteBean.getNome());
		if (concorrenteBean.getNome().equals("")) {
			throw new AtributoNuloException("Por favor, digite um nome v�lido!");
		} else if (!validarNome(concorrenteBean.getNome())) {
			throw new AtributoNuloException("Por favor, digite um nome v�lido!");
		} else if (concorrenteBean.getNome().length() > 50) {
			throw new TamanhoCampoException("N�mero limite de caracteres excedido(m�x.50)");
		} else if (concorrenteBean.getDescricao().length() > 255) {
			throw new TamanhoCampoException("Descri��o: N�mero limite de caracteres excedido(m�x.255)");
		} else if (concorrenteClone != null && concorrenteClone.getId() != concorrenteBean.getId()) {
			throw new NomeRepetidoException("Este nome j� est� cadastrado!");
		} else {
			ConcorrenteDAO concorrenteDao = new ConcorrenteDAO();
			concorrenteDao.alterar(concorrenteBean);
		}
	}

	public void remover(int idConcorrente) throws ClassNotFoundException, SQLException, RegistroVinculadoException {
		if (!concorrenteDao.verificarPorCliente(idConcorrente)) {
			concorrenteDao.remover(idConcorrente);
		} else {
			throw new RegistroVinculadoException("Registro n�o pode ser removido pois possui v�nculos");
		}
	}
	
	public void removerConcorrenteCliente(int idCliente, int idConcorrente) throws ClassNotFoundException, SQLException {
		concorrenteDao.removerConcorrenteCliente(idCliente, idConcorrente);
	}

	public boolean validarNome(String nome) {
		return (nome.matches("[A-Za-z�-�0-9+'\\-\\s]{2,100}"));
	}

}
