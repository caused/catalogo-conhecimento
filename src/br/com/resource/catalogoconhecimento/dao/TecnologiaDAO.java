package br.com.resource.catalogoconhecimento.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




import br.com.resource.catalogoconhecimento.bean.TecnologiaBean;
import br.com.resource.catalogoconhecimento.factory.ConnectionFactory;

public class TecnologiaDAO {

	Connection conexao = null;

	// CRIA
	public void adicionar(TecnologiaBean tecnologia) throws ClassNotFoundException, SQLException {
		Connection conexao = ConnectionFactory.createConnection();
		String sql = "INSERT INTO Tecnologia(nomeTecnologia, ativo) VALUES(?, ?)";
		PreparedStatement st = conexao.prepareStatement(sql);

		st.setString(1, tecnologia.getNome());
		st.setString(2, "s");

		st.executeUpdate();
		conexao.close();
	}

	// LISTA
	public List<TecnologiaBean> listar() throws SQLException, ClassNotFoundException {
		Connection conexao = ConnectionFactory.createConnection();

		String sql = "SELECT * FROM Tecnologia where ativo = ?";
		
		ArrayList<TecnologiaBean> tecnologias = new ArrayList<TecnologiaBean>(); 
		TecnologiaBean tecnologia;

		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, "s");
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			int id = rs.getInt("idTecnologia");
			String nome = rs.getString("nomeTecnologia");
			
			tecnologia = new TecnologiaBean();
			tecnologia.setId(id);
			tecnologia.setNome(nome);
			tecnologias.add(tecnologia);
		}

		conexao.close();
		return tecnologias;
	}
	
	// LISTA POR ID
	public TecnologiaBean obterPorId(int idTecnologia) throws SQLException, ClassNotFoundException {
		Connection conexao = ConnectionFactory.createConnection();
		String sql = "SELECT * FROM Tecnologia WHERE idTecnologia = '" + idTecnologia + "'";
		PreparedStatement ps = conexao.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		
		TecnologiaBean tecnologia = null;
		while (rs.next()) {
			tecnologia = new TecnologiaBean();
			tecnologia.setId(rs.getInt("idTecnologia"));
			tecnologia.setNome(rs.getString("nomeTecnologia"));
		}
		conexao.close();
		return tecnologia;
	}
	
	public TecnologiaBean obterPorNome(String nome) throws ClassNotFoundException, SQLException {
		Connection conexao = ConnectionFactory.createConnection();

		String sql = "SELECT * FROM Tecnologia WHERE nomeTecnologia = ?";
		
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, nome);
		ResultSet rs = ps.executeQuery();

		TecnologiaBean tecnologia = null;
		
		while (rs.next()) {
			int id = rs.getInt("idTecnologia");
			String nomeTec = rs.getString("nomeTecnologia");
			
			tecnologia = new TecnologiaBean();
			tecnologia.setId(id);
			tecnologia.setNome(nomeTec);
		}

		conexao.close();
		return tecnologia;
	}

	// ATUALIZA
	public void alterar(TecnologiaBean tecnologia) throws ClassNotFoundException, SQLException {
		Connection conexao = ConnectionFactory.createConnection();
		String sql = "UPDATE Tecnologia SET nomeTecnologia = ? WHERE idTecnologia = ?";
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, tecnologia.getNome());
		ps.setInt(2, tecnologia.getId());

		ps.executeUpdate();
		conexao.close();
	}

	// DELETA
	public void remover(int id) throws SQLException, ClassNotFoundException {
		Connection conexao = ConnectionFactory.createConnection();
		String sql = "update Tecnologia set ativo = ? WHERE idTecnologia = ?";
		PreparedStatement ps = conexao.prepareStatement(sql);

		ps.setString(1, "n");
		ps.setInt(2, id);
		ps.executeUpdate();
		conexao.close();
	}
	
	public TecnologiaBean obterNomeDesativado(TecnologiaBean tecnologiaBean) throws SQLException, ClassNotFoundException{
		
		Connection conexao = ConnectionFactory.createConnection();

		String sql = "SELECT * FROM Tecnologia WHERE nomeTecnologia = ? and ativo  = ?";
		
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, tecnologiaBean.getNome());
		ps.setString(2, "n");
		
		ResultSet rs = ps.executeQuery();

		TecnologiaBean tecnologia = null;
		
		while (rs.next()) {
			int id = rs.getInt("idTecnologia");
			String nomeTec = rs.getString("nomeTecnologia");
			
			tecnologia = new TecnologiaBean();
			tecnologia.setId(id);
			tecnologia.setNome(nomeTec);
		}

		conexao.close();
		return tecnologia;
		
	}
	
	public void reativar(TecnologiaBean tecnologia) throws SQLException, ClassNotFoundException{
		Connection conexao = ConnectionFactory.createConnection();
		
		String sql = "UPDATE Tecnologia SET ativo = ? WHERE nomeTecnologia = ?";
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1,"s");
		ps.setString(2, tecnologia.getNome());

		ps.executeUpdate();
		conexao.close();
	}

}
