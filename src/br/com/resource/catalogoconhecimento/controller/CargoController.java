package br.com.resource.catalogoconhecimento.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.resource.catalogoconhecimento.bean.CargoBean;
import br.com.resource.catalogoconhecimento.business.CargoBusiness;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;

@Controller
public class CargoController {

	@Autowired
	private CargoBusiness cargoBusiness;
	
	@RequestMapping(value = "formularioAdicionarCargo", method = RequestMethod.GET)
	public String formularioAdicionar() {
		return "cargo/formularioAdicionarCargo";
	}
	
	@RequestMapping(value = "adicionarCargo", method = RequestMethod.POST)
	public String adiciona(CargoBean cargoBean) throws BusinessException{
		cargoBusiness.adicionar(cargoBean);
		return "redirect:listarCargo";
	}
	
	@RequestMapping(value = "listarCargo", method = RequestMethod.GET)
	public String listarCargo(Model model) throws BusinessException{
		
		model.addAttribute("cargos", cargoBusiness.listar());
		return "cargo/listarCargos";
	}
	
	@RequestMapping(value = "formularioAlterarCargo", method = RequestMethod.GET)
	public String alterar(Model model, @RequestParam("idCargo") String id) throws BusinessException {
		int idCargo = Integer.parseInt(id);
		model.addAttribute("cargo", cargoBusiness.obterPorId(idCargo));
		return "cargo/formularioAlterarCargo";
	}
	
	@RequestMapping(value = "alterarCargo", method = RequestMethod.POST)
	public String alterar(CargoBean cargoBean) throws BusinessException{
		cargoBusiness.alterar(cargoBean);
		return "redirect:listarCargo";
	}
	
	@RequestMapping(value = "excluirCargo", method = RequestMethod.GET)
	public String excluir( @RequestParam("idCargo") String id, HttpServletRequest request) throws BusinessException{
		int idCargo = Integer.parseInt(id);
		cargoBusiness.remover(idCargo);
		return "redirect:listarCargo";
	}
	
	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public String exceptionHandler(Model model, BusinessException exception){
		model.addAttribute("msgErro", exception.getMessage());
		return "index";
	}
}