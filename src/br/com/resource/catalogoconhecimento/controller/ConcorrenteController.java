package br.com.resource.catalogoconhecimento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.resource.catalogoconhecimento.bean.ConcorrenteBean;
import br.com.resource.catalogoconhecimento.bean.ConcorrenteClienteBean;
import br.com.resource.catalogoconhecimento.business.ClienteBusiness;
import br.com.resource.catalogoconhecimento.business.ConcorrenteBusiness;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;

@Controller
public class ConcorrenteController {

	@Autowired
	private ClienteBusiness clienteBusiness;

	@Autowired
	private ConcorrenteBusiness concorrenteBusiness;

	@RequestMapping(value = "formularioAdicionarConcorrente", method = RequestMethod.GET)
	public String formularioAdicionarConcorrente(Model model) throws BusinessException {
		model.addAttribute("listaCliente", clienteBusiness.listar());
		return "concorrente/formularioAdicionarConcorrente";
	}

	@RequestMapping(value = "adicionarConcorrente", method = RequestMethod.POST)
	public String adicionarConcorrente(ConcorrenteBean concorrenteBean) throws BusinessException {
		concorrenteBusiness.adicionar(concorrenteBean);
		if (concorrenteBean.getListaClientes() != null) {
			for (ConcorrenteClienteBean cliente : concorrenteBean.getListaClientes()) {
				concorrenteBusiness.adicionarConcorrenteCliente(cliente);
			}
		}
		return "redirect:listarConcorrente";
	}

	@RequestMapping(value = "adicionarClienteNoConcorrente", method = RequestMethod.POST)
	public String adicionarClienteNoConcorrente(Model model, ConcorrenteBean concorrenteBean) throws BusinessException {
		if (concorrenteBean.getListaClientes() != null) {
			for (ConcorrenteClienteBean cliente : concorrenteBean.getListaClientes()) {
				concorrenteBusiness.adicionarConcorrenteCliente(cliente);
			}
		}
		model.addAttribute("listaConcorrenteCliente",
				concorrenteBusiness.listarPorConcorrente(concorrenteBean.getId()));
		model.addAttribute("concorrenteBean", concorrenteBean);
		model.addAttribute("listaCliente", clienteBusiness.listar());

		return "redirect:listarClientePorConcorrente";
	}

	@RequestMapping(value = "formularioAlterarConcorrente", method = RequestMethod.GET)
	public String formularioAlterarConcorrente(Model model, @RequestParam("idConcorrente") String idConcorrenteParam)
			throws BusinessException {
		int idConcorrente = Integer.parseInt(idConcorrenteParam);
		model.addAttribute("concorrente", concorrenteBusiness.obterPorId(idConcorrente));
		return "concorrente/formularioAlterarConcorrente";
	}

	@RequestMapping(value = "alterarConcorrente", method = RequestMethod.POST)
	public String alterarConcorrente(ConcorrenteBean concorrenteBean) throws BusinessException {
		concorrenteBusiness.alterar(concorrenteBean);
		return "redirect:listarConcorrente";
	}

	@RequestMapping(value = "listarConcorrente", method = RequestMethod.GET)
	public String listarConcorrente(Model model) throws BusinessException {
		model.addAttribute("concorrentes", concorrenteBusiness.listar());
		return "concorrente/listarConcorrente";
	}

	@RequestMapping(value = "listarClientePorConcorrente", method = RequestMethod.GET)
	public String listarClientePorConcorrente(Model model, @RequestParam("idConcorrente") String idConcorrenteParam)
			throws BusinessException {

		model.addAttribute("listaConcorrenteCliente",
				concorrenteBusiness.listarPorConcorrente(Integer.parseInt(idConcorrenteParam)));
		model.addAttribute("concorrenteBean", concorrenteBusiness.obterPorId(Integer.parseInt(idConcorrenteParam)));
		model.addAttribute("listaCliente", clienteBusiness.listar());

		return "concorrente/listarClientePorConcorrente";
	}

	@RequestMapping(value = "removerConcorrente", method = RequestMethod.GET)
	public String removerConcorrente(ConcorrenteBean concorrenteBean) throws BusinessException {
		concorrenteBusiness.remover(concorrenteBean);
		return "redirect:listarConcorrente";
	}

	@RequestMapping(value = "removerClientedoConcorrente", method = RequestMethod.GET)
	public String removerClientedoConcorrente(Model model, @RequestParam("idCliente") String idClienteParam,
			@RequestParam("idConcorrente") String idConcorrenteParam) throws BusinessException {

		int idCliente = Integer.parseInt(idClienteParam);
		int idConcorrente = Integer.parseInt(idConcorrenteParam);

		concorrenteBusiness.removerConcorrenteCliente(idCliente, idConcorrente);

		model.addAttribute("listaConcorrenteCliente", concorrenteBusiness.listarPorCliente(idConcorrente));
		model.addAttribute("concorrenteBean", concorrenteBusiness.obterPorId(idConcorrente));
		model.addAttribute("listaCliente", clienteBusiness.listar());

		return "redirect:listarClientePorConcorrente";
	}

	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public String exceptionHandler(Model model, BusinessException exception) {
		model.addAttribute("msgErro", exception.getMessage());
		return "index";
	}
}