package br.com.resource.catalogoconhecimento.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.resource.catalogoconhecimento.bean.ConcorrenteBean;
import br.com.resource.catalogoconhecimento.bean.ConcorrenteClienteBean;
import br.com.resource.catalogoconhecimento.dao.ConcorrenteDAO;
import br.com.resource.catalogoconhecimento.exceptions.AtributoNuloException;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;
import br.com.resource.catalogoconhecimento.exceptions.ConsultaNulaException;
import br.com.resource.catalogoconhecimento.exceptions.NomeRepetidoException;
import br.com.resource.catalogoconhecimento.exceptions.TamanhoCampoException;
import br.com.resource.catalogoconhecimento.utils.ExceptionUtil;

@Component
public class ConcorrenteBusiness {

	@Autowired
	private ConcorrenteDAO concorrenteDao;

	@Transactional
	public void adicionar(ConcorrenteBean concorrenteBean) throws BusinessException {
		ConcorrenteBean concorrenteClone = this.obterPorNome(concorrenteBean.getNome());
		try {
			if (concorrenteBean.getDescricao().equals("")) {
				concorrenteBean.setDescricao("-");
			}
			if (concorrenteBean.getNome().equals("")) {
				throw new AtributoNuloException("Por favor, digite um nome v�lido");
			} else if (!validarNome(concorrenteBean.getNome())) {
				throw new AtributoNuloException("Por favor, digite um nome sem caracteres especiais");
			} else if (concorrenteBean.getNome().length() > 100) {
				throw new TamanhoCampoException("N�mero limite de caracteres excedido (m�x.50)");
			} else if (concorrenteBean.getDescricao().length() > 255) {
				throw new TamanhoCampoException("Descri��o: N�mero limite de caracteres excedido (m�x.255)");
			} else if (concorrenteClone != null && concorrenteClone.getId() != concorrenteBean.getId()) {
				throw new NomeRepetidoException("Este nome j� est� cadastrado");
			} else {
				concorrenteDao.adicionar(concorrenteBean);
			}
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	@Transactional
	public void adicionarConcorrenteCliente(ConcorrenteClienteBean concorrenteClienteBean) throws BusinessException {
		try {
			if (concorrenteClienteBean.getCliente() == null) {
				throw new AtributoNuloException("Cliente inv�lido");
			} else if (concorrenteClienteBean.getConcorrente() == null) {
				throw new AtributoNuloException("Concorrente inv�lido");
			} else {
				concorrenteDao.adicionarConcorrenteCliente(concorrenteClienteBean);
			}
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	@Transactional
	public List<ConcorrenteBean> listar() throws BusinessException {
		try {
			List<ConcorrenteBean> listaConcorrente = concorrenteDao.listar();

			if (listaConcorrente.isEmpty()) {
				throw new ConsultaNulaException("N�o h� concorrentes cadastrados");
			} else {
				return listaConcorrente;
			}
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	@Transactional
	public List<ConcorrenteClienteBean> listarPorConcorrente(int idConcorrente) throws BusinessException {
		try {
			List<ConcorrenteClienteBean> listaConcorrenteCliente = concorrenteDao.listarPorConcorrente(idConcorrente);
			return listaConcorrenteCliente;
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	@Transactional
	public List<ConcorrenteClienteBean> listarPorNomeCliente(String nomeCliente) throws BusinessException {
		try {
			return concorrenteDao.listarPorNomeCliente(nomeCliente);
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	@Transactional
	public List<ConcorrenteClienteBean> listarPorCliente(int idCliente) throws BusinessException {
		try {
			List<ConcorrenteClienteBean> listaConcorrenteCliente = concorrenteDao.listarPorCliente(idCliente);
			return listaConcorrenteCliente;
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	@Transactional
	public ConcorrenteBean obterPorId(int idConcorrente) throws BusinessException {
		try {
			return concorrenteDao.obterPorId(idConcorrente);
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	@Transactional
	public ConcorrenteBean obterPorNome(String nomeConcorrente) throws BusinessException {
		try {
			return concorrenteDao.obterPorNome(nomeConcorrente);
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	@Transactional
	public void alterar(ConcorrenteBean concorrenteBean) throws BusinessException {
		try {
			ConcorrenteBean concorrenteClone = this.obterPorNome(concorrenteBean.getNome());
			if (concorrenteBean.getNome().equals("")) {
				throw new AtributoNuloException("Por favor, digite um nome v�lido");
			} else if (!validarNome(concorrenteBean.getNome())) {
				throw new AtributoNuloException("Por favor, digite um nome v�lido");
			} else if (concorrenteBean.getNome().length() > 50) {
				throw new TamanhoCampoException("N�mero limite de caracteres excedido (m�x.50)");
			} else if (concorrenteBean.getDescricao().length() > 255) {
				throw new TamanhoCampoException("Descri��o: N�mero limite de caracteres excedido (m�x.255)");
			} else if (concorrenteClone != null && concorrenteClone.getId() != concorrenteBean.getId()) {
				throw new NomeRepetidoException("Este nome j� est� cadastrado");
			} else {
				concorrenteDao.alterar(concorrenteBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.handleException(e);
		}
	}

	@Transactional
	public void remover(ConcorrenteBean concorrenteBean) throws BusinessException {
		try {
			// if (!concorrenteDao.verificarPorCliente(idConcorrente)) {
			concorrenteDao.remover(concorrenteBean);
			// } else {
			// throw new RegistroVinculadoException("Registro n�o pode ser
			// removido pois possui v�nculos");
			// }
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	@Transactional
	public void removerConcorrenteCliente(int idCliente, int idConcorrente) throws BusinessException {
		try {
			concorrenteDao.removerConcorrenteCliente(idCliente, idConcorrente);
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	@Transactional
	public boolean validarNome(String nome) {
		return (nome.matches("[A-Za-zÀ-ú0-9+'\\-\\s]{2,100}"));
	}

}
