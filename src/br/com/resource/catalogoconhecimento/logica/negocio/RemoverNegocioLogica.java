package br.com.resource.catalogoconhecimento.logica.negocio;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.resource.catalogoconhecimento.business.NegocioBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class RemoverNegocioLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("id"));

		NegocioBusiness negocioBusiness = new NegocioBusiness();
		negocioBusiness.remover(id);

		return "mvc?logica=negocio.ListarNegocioLogica";
	}

}
