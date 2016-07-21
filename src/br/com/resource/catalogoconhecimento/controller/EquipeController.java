package br.com.resource.catalogoconhecimento.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.resource.catalogoconhecimento.bean.EquipeBean;
import br.com.resource.catalogoconhecimento.bean.FuncionarioBean;
import br.com.resource.catalogoconhecimento.bean.TecnologiaBean;
import br.com.resource.catalogoconhecimento.business.EquipeBusiness;
import br.com.resource.catalogoconhecimento.business.EquipeFuncionarioBusiness;
import br.com.resource.catalogoconhecimento.business.FuncionarioBusiness;
import br.com.resource.catalogoconhecimento.business.TecnologiaBusiness;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;

@Controller
@RequestMapping("/equipe")
public class EquipeController {

	@Autowired
	private EquipeFuncionarioBusiness equipeFuncionarioBusiness;

	@Autowired
	private EquipeBusiness equipeBusiness;

	@Autowired
	private FuncionarioBusiness funcionarioBusiness;

	@Autowired
	private TecnologiaBusiness tecnologiaBusiness;

	@RequestMapping(value = "adicionar", method = RequestMethod.GET)
	public String formularioAdicionar() {
		return "equipe/adicionarEquipe";
	}

	@RequestMapping(value = "adicionarEquipe", method = RequestMethod.POST)
	public String adicionar(EquipeBean equipe, @RequestParam("ativo") String ativo) throws BusinessException {
		equipe.setAtivo(ativo.charAt(0));
		equipeBusiness.adicionar(equipe);
		return "redirect:listar";
	}

	@RequestMapping(value = "listar", method = { RequestMethod.GET, RequestMethod.POST })
	public String listarEquipe(Model model) throws BusinessException {
		model.addAttribute("equipes", equipeBusiness.listar());
		return "equipe/listarEquipe";
	}

	@RequestMapping(value = "alterar", method = RequestMethod.GET)
	public String formularioAlterar(Model model, @RequestParam("idEquipe") String id) throws BusinessException {
		int idEquipe = Integer.parseInt(id);
		model.addAttribute("equipe", equipeBusiness.obterPorId(idEquipe));
		return "equipe/alterarEquipe";
	}

	@RequestMapping(value = "alterarEquipe", method = RequestMethod.POST)
	public String alterarEquipe(EquipeBean equipe, @RequestParam("idEquipe") String id) throws BusinessException {
		equipe.setId(Integer.parseInt(id));
		equipeBusiness.alterar(equipe);
		return "redirect:listar";
	}

	@RequestMapping(value = "excluirEquipe", method = RequestMethod.GET)
	public String remover(@RequestParam("idEquipe") String id, @RequestParam("ativo") String ativo,
			HttpServletRequest request) throws BusinessException {
		int idEquipe = Integer.parseInt(id);
		EquipeBean equipe = equipeBusiness.obterPorId(idEquipe);
		equipe.setAtivo(ativo.charAt(0));
		equipeBusiness.remover(equipe);
		return "redirect:listar";
	}

	@RequestMapping(value = "deletarFuncionarioPorEquipe", method = RequestMethod.GET)
	public String deletarFuncionarioPorEquipe(@RequestParam("idEquipe") String idEq,
			@RequestParam("idFuncionario") String idFunc, HttpServletRequest request) throws BusinessException {

		int idEquipe = Integer.parseInt(idEq);
		int idFuncionario = Integer.parseInt(idFunc);
		equipeBusiness.removerPorEquipe(idEquipe, idFuncionario);
		request.setAttribute("idEquipe", idEq);

		return "forward:listarFuncionarioPorEquipe";
	}

	@RequestMapping(value = "listarFuncionarioPorEquipe", method = { RequestMethod.GET, RequestMethod.POST })
	public String listarFuncionarioPorEquipe(Model model, @RequestParam("idEquipe") String idEquipe)
			throws BusinessException {

		List<FuncionarioBean> listaFuncionario = funcionarioBusiness.listar();
		List<FuncionarioBean> funcionarioEquipe = funcionarioBusiness.listarPorEquipe(Integer.parseInt(idEquipe));
		EquipeBean equipe = equipeBusiness.obterPorId(Integer.parseInt(idEquipe));
		model.addAttribute("funcionarios", listaFuncionario);
		model.addAttribute("funcionarioEquipe", funcionarioEquipe);
		model.addAttribute("equipe", equipe);

		return "equipe/listarFuncionariosPorEquipe";
	}

	@RequestMapping(value = "adicionarFuncionarioNaEquipe", method = RequestMethod.POST)
	public String adicionarFuncionarioNaEquipe(@RequestParam("idEquipe") String idEq,
			@RequestParam("idFuncionario") String idFunc, HttpServletRequest request) throws Exception {

		int idEquipe = Integer.parseInt(idEq);
		int idFuncionario = Integer.parseInt(idFunc);
		FuncionarioBean funcionario = funcionarioBusiness.obterPorId(idFuncionario);
		equipeFuncionarioBusiness.inserirPorEquipe(idEquipe, funcionario);
		request.setAttribute("idEquipe", idEq);

		return "forward:listarFuncionarioPorEquipe";
	}

	@ExceptionHandler(BusinessException.class)
	public String exceptionHandler(BusinessException exception, Model model) {
		model.addAttribute("msgErro", exception.getMessage());
		return "forward:listarEquipe";
	}

	@RequestMapping(value = "buscarTecnologiaPorFuncionario", method = RequestMethod.POST)
	public @ResponseBody List<TecnologiaBean> buscarTecnologiaPorFuncionario(@RequestParam("idFuncionario") String id)
			throws BusinessException {
		int idFuncionario = Integer.parseInt(id);
		return tecnologiaBusiness.obterPorFuncionario(idFuncionario);
	}

}
