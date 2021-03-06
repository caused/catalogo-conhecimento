package br.com.resource.catalogoconhecimento.logica.concorrente;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.ClienteBean;
import br.com.resource.catalogoconhecimento.bean.ConcorrenteBean;
import br.com.resource.catalogoconhecimento.bean.ConcorrenteClienteBean;
import br.com.resource.catalogoconhecimento.business.ClienteBusiness;
import br.com.resource.catalogoconhecimento.business.ConcorrenteBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class ListarClientePorConcorrenteLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<ConcorrenteClienteBean> listaConcorrenteCliente = new ConcorrenteBusiness()
				.listarPorConcorrente(Integer.parseInt(request.getParameter("id")));
		
		ConcorrenteBean concorrenteBean = new ConcorrenteBusiness().obterPorId(Integer.parseInt(request.getParameter("id")));
		List<ClienteBean> listaCliente = new ClienteBusiness().listar();
		
		request.setAttribute("listaConcorrenteCliente", listaConcorrenteCliente);
		request.setAttribute("concorrenteBean", concorrenteBean);
		request.setAttribute("listaCliente", listaCliente);
		
		return "/WEB-INF/jsp/concorrente/listarClientePorConcorrente.jsp";
	}
}
