package br.com.resource.catalogoconhecimento.logica.busca;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.FuncionarioBean;
import br.com.resource.catalogoconhecimento.business.FuncionarioBusiness;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class ListarPorFuncionarioBuscaLogica implements Logica{

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String [] array = request.getParameter("filtro").split(",");
		
		if(array.length > 1){
			throw new BusinessException("S� � poss�vel buscar 1 funcion�rio por vez");
		}
		
		//List<FuncionarioBean>listaFuncionarios = new FuncionarioBusiness().listarPorNome(array[0]);
		
		//if(listaFuncionarios.isEmpty()){
			//throw new BusinessException("N�o h� funcion�rios com esse nome");
		//}
		
		//request.setAttribute("funcionarios", listaFuncionarios);
		
		return "WEB-INF/jsp/busca/listarBuscaFuncionario.jsp";
	}

}
