package br.com.resource.catalogoconhecimento.logica.busca;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.FuncionarioBean;
import br.com.resource.catalogoconhecimento.bean.ProjetoBean;
import br.com.resource.catalogoconhecimento.bean.TecnologiaBean;
import br.com.resource.catalogoconhecimento.business.FuncionarioBusiness;
import br.com.resource.catalogoconhecimento.business.ProjetoBusiness;
import br.com.resource.catalogoconhecimento.business.TecnologiaBusiness;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class ListarPorTecnologiaBuscaLogica implements Logica{

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String [] array = request.getParameter("filtro").split(",");
		ProjetoBean projetoBean = new ProjetoBean();
		
	
		
		

		String nomeTecnologias = "";
		TecnologiaBusiness tecnologiaBusiness = new TecnologiaBusiness();
		
		for(int i = 0; i < array.length; i ++){
			if(tecnologiaBusiness.obterPorNome(array[i]) != null){
				if(nomeTecnologias.isEmpty()){
					nomeTecnologias += "'"+array[i]+"'";
				}else{
					nomeTecnologias += ",'"+array[i]+"'";
				}
			}
				
			}
		
		if(nomeTecnologias.isEmpty()){
			throw new BusinessException("Pesquisa inv�lida! Este nome de tecnologia n�o existe");
		}
	
	
		
		
		List<ProjetoBean> projetos = new ProjetoBusiness().obterPorTecnologias(nomeTecnologias);
		
		
		List<FuncionarioBean> listaFuncionario = new FuncionarioBusiness().listarPorTecnologias(nomeTecnologias);
		
		
		request.setAttribute("projetos", projetos);
		request.setAttribute("funcionarios", listaFuncionario);
		
		return "WEB-INF/jsp/busca/listarBuscaTecnologia.jsp";
	}
	
	

}
