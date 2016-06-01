package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;

import br.com.resource.catalogoconhecimento.bean.FuncionarioBean;
import br.com.resource.catalogoconhecimento.dao.FuncionarioDAO;
import br.com.resource.catalogoconhecimento.exceptions.ConsultaNulaException;

public class FuncionarioBusiness {

	private FuncionarioDAO funcionarioDao;

	public FuncionarioBusiness() {

		funcionarioDao = new FuncionarioDAO();
	}

	/**
	 * Adiciona um novo funion�rio na base
	 * 
	 * @param funcionarioBean
	 * @return id, criado no bd, do novo funcion�rio adicionado
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int adicionar(FuncionarioBean funcionarioBean) throws ClassNotFoundException, SQLException {

		int id = funcionarioDao.adicionar(funcionarioBean);
		return id;
	}

	/**
	 * Lista todos os funcion�rios ativos
	 * 
	 * @return Lista de funcion�rios
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<FuncionarioBean> listar() throws ClassNotFoundException, SQLException {
		return funcionarioDao.listar();
	}
	
	/**
	 * Lista todos os funcion�rios que possuem as tecnologias especificadas
	 * 
	 * @param nomeTecnologias
	 * @return List<FuncionarioBean>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ConsultaNulaException
	 */
	public List<FuncionarioBean> listarPorTecnologias(String nomeTecnologias) throws ClassNotFoundException, SQLException, ConsultaNulaException {
		List<FuncionarioBean> listaFuncionario = funcionarioDao.listarPorTecnologias(nomeTecnologias);
		
		if (listaFuncionario == null) {
			throw new ConsultaNulaException("N�o h� funcion�rios cadastrados");
		} else {
			return listaFuncionario;
		}
	}

	/**
	 * Obtem todas informa��es de um funcin�rio por id
	 * 
	 * @param idFuncionario
	 * @return informa��es de um funcin�rio
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public FuncionarioBean obterPorId(int idFuncionario) throws ClassNotFoundException, SQLException {

		return funcionarioDao.obterPorId(idFuncionario);
	}

	/**
	 * Atualiza informa��es de um funcion�rio
	 * 
	 * @param funcionario
	 * @return boolean
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean atualizar(FuncionarioBean funcionarioBean) throws ClassNotFoundException, SQLException {

		FuncionarioBean funcionarioAux = funcionarioDao.obterPorId(funcionarioBean.getId());

		if (funcionarioAux == null) {
			return true;
		} else {
			funcionarioDao.alterar(funcionarioBean);
			return false;
		}
	}

	/**
	 * Remove logicamente um funcion�rio
	 * 
	 * @param id
	 * @return boolean
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean deletar(int id) throws ClassNotFoundException, SQLException {

		FuncionarioBean funcionarioAux = funcionarioDao.obterPorId(id);
		if (funcionarioAux == null) {
			return true;
		} else {
			funcionarioDao.remover(id);
			return false;
		}
	}

	/**
	 * Obtem todas informa��es de um funcion�rio pelo nome
	 * 
	 * @param nome
	 * @return todas informa��es de um funcion�rio
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public FuncionarioBean obterPorNome(String nome) throws ClassNotFoundException, SQLException {

		return funcionarioDao.obterPorNome(nome);
	}

	/**
	 * Obtem informa��es espec�ficas de um funcion�rio pelo idEquipe
	 * 
	 * @param id
	 * @return informa��es espec�ficas de um funcion�rio
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<FuncionarioBean> obterPorEquipe(int id) throws ClassNotFoundException, SQLException {

		return funcionarioDao.obterPorEquipe(id);
	}

	public List<FuncionarioBean> listarPorNegocio(String nomeNegocio) throws ClassNotFoundException, SQLException{
		return funcionarioDao.listarPorNegocio(nomeNegocio);
	}
}
