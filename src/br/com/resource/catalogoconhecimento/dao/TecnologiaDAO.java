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
	public void inserir(TecnologiaBean tecnologia) throws ClassNotFoundException, SQLException {
		Connection conexao = ConnectionFactory.createConnection();
		String sql = "INSERT INTO Tecnologia(nomeTecnologia) VALUES(?)";
		PreparedStatement st = conexao.prepareStatement(sql);

		st.setString(1, tecnologia.getNomeTecnologia());

		st.executeUpdate();
		conexao.close();
	}

	// LISTA
	public List<TecnologiaBean> listar() throws SQLException, ClassNotFoundException {
		Connection conexao = ConnectionFactory.createConnection();

		String sql = "SELECT * FROM CatalogoConhecimentos.dbo.Tecnologia";
		
		ArrayList<TecnologiaBean> tecnologias = new ArrayList<TecnologiaBean>(); 
		TecnologiaBean tecnologia;

		PreparedStatement ps = conexao.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			int idTecnologia = rs.getInt("idTecnologia");
			String nomeTecnologia = rs.getString("nomeTecnologia");
			
			tecnologia = new TecnologiaBean();
			tecnologia.setIdTecnologia(idTecnologia);
			tecnologia.setNomeTecnologia(nomeTecnologia);
			tecnologias.add(tecnologia);
		}

		conexao.close();
		return tecnologias;
	}

	// ATUALIZA
	public void atualizar(TecnologiaBean tecnologia) throws ClassNotFoundException, SQLException {
		Connection conexao = ConnectionFactory.createConnection();
		String sql = "UPDATE Tecnologia SET nomeTecnologia = ? WHERE idTecnologia = ?";
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, tecnologia.getNomeTecnologia());
		ps.setInt(2, tecnologia.getIdTecnologia());

		ps.executeUpdate();
		conexao.close();
	}

	// DELETA
	public void deletar(int id) throws SQLException, ClassNotFoundException {
		Connection conexao = ConnectionFactory.createConnection();
		String sql = "DELETE FROM Tecnologia WHERE idTecnologia = ?";
		PreparedStatement ps = conexao.prepareStatement(sql);

		ps.setInt(1, id);
		ps.executeUpdate();
		conexao.close();
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
			tecnologia.setIdTecnologia(rs.getInt("idTecnologia"));
			tecnologia.setNomeTecnologia(rs.getString("nomeTecnologia"));
		}
		conexao.close();
		return tecnologia;
	}

}
