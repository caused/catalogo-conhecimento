package br.com.resource.catalogoconhecimento.logica.tecnologia;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.TecnologiaBean;
import br.com.resource.catalogoconhecimento.business.TecnologiaBusiness;
import br.com.resource.catalogoconhecimento.exceptions.AtributoNuloException;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class AdicionarTecnologiaLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String nome = request.getParameter("nome");
		
		if(nome.trim().equals("")){
			throw new AtributoNuloException("Por favor, digite um nome v�lido!");
		} else {
		TecnologiaBean tecnologia = new TecnologiaBean();
		tecnologia.setNome(nome.trim());
		
		TecnologiaBusiness tecnologiaBusiness = new TecnologiaBusiness();
		tecnologiaBusiness.adicionar(tecnologia);
		}
		return "mvc?logica=tecnologia.ListarTecnologiaLogica";
	}

}