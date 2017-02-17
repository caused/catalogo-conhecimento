package br.com.resource.catalogoconhecimento.controller;

import java.beans.PropertyEditorSupport;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.resource.catalogoconhecimento.bean.CargoBean;
import br.com.resource.catalogoconhecimento.bean.FuncionarioBean;
import br.com.resource.catalogoconhecimento.bean.NegocioBean;
import br.com.resource.catalogoconhecimento.bean.TecnologiaBean;
import br.com.resource.catalogoconhecimento.business.CargoBusiness;
import br.com.resource.catalogoconhecimento.business.EquipeBusiness;
import br.com.resource.catalogoconhecimento.business.FuncionarioBusiness;
import br.com.resource.catalogoconhecimento.business.NegocioBusiness;
import br.com.resource.catalogoconhecimento.business.TecnologiaBusiness;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;

@Controller
@RequestMapping(value = "/funcionario")
public class FuncionarioController {

	@Autowired
	FuncionarioBusiness funcionarioBusiness;

	@Autowired
	TecnologiaBusiness tecnologiaBusiness;

	@Autowired
	CargoBusiness cargoBusiness;

	@Autowired
	NegocioBusiness negocioBusiness;

	@Autowired
	EquipeBusiness equipeBusiness;

	@RequestMapping(value = "/adicionar", method = RequestMethod.GET)
	public String formularioAdicionar(Model model) throws BusinessException {
		List<TecnologiaBean> listaTecnologia = tecnologiaBusiness.listar();
		List<CargoBean> listaCargo = cargoBusiness.listar();
		List<NegocioBean> listaNegocio = negocioBusiness.listar();

		model.addAttribute("listaTecnologia", listaTecnologia);
		model.addAttribute("cargos", listaCargo);
		model.addAttribute("listaNegocio", listaNegocio);
		return "funcionarios/adicionar";
	}

	@RequestMapping(value = "listar", method = RequestMethod.GET)
	public String listar(Model model) throws BusinessException {
		model.addAttribute("listaFuncionario", funcionarioBusiness.listar());
		return "funcionarios/listar";
	}

	@RequestMapping(value = "adicionarFuncionario", method = RequestMethod.POST)
	public String adicionarFuncionario(FuncionarioBean funcionarioBean) throws BusinessException {
		funcionarioBusiness.adicionar(funcionarioBean);

		return "redirect:listar";
	}

	@RequestMapping(value = "alterar", method = RequestMethod.GET)
	public String formularioAlterar(Model model, @RequestParam("idFuncionario") String id) throws BusinessException {
		List<TecnologiaBean> listaTecnologia = tecnologiaBusiness.listar();
		List<CargoBean> listaCargo = cargoBusiness.listar();
		List<NegocioBean> listaNegocio = negocioBusiness.listar();
		int idFuncionario = Integer.parseInt(id);

		model.addAttribute("funcionario", funcionarioBusiness.obterPorId(idFuncionario));
		model.addAttribute("listaTecnologia", listaTecnologia);
		model.addAttribute("cargos", listaCargo);
		model.addAttribute("listaNegocio", listaNegocio);

		return "funcionarios/alterar";
	}

	@RequestMapping(value = "alterarFuncionario", method = RequestMethod.POST)
	public String alterar(FuncionarioBean funcionarioBean) throws BusinessException {
		funcionarioBusiness.alterar(funcionarioBean);
		
		return "redirect:listar";
	}

	@RequestMapping(value = "removerFuncionario", method = RequestMethod.GET)
	public String remover(@RequestParam("idFuncionario") String id) throws BusinessException {
		int idFuncionario = Integer.parseInt(id);

		funcionarioBusiness.remover(idFuncionario);

		return "redirect:listar";
	}

	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public String exceptionHandler(Model model, BusinessException exception) {
		model.addAttribute("msgErro", exception.getMessage());
		return "redirect:listar";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(FuncionarioBean.class, "listaNegocio", new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) throws NumberFormatException {
				String[] ids = text.split(",");
				FuncionarioBean funcionario = null;
				for (String id : ids) {
					if (funcionario == null)
						funcionario = new FuncionarioBean();
					NegocioBean negocio = null;

					try {
						negocio = negocioBusiness.obterPorId(new Integer(id));
					} catch (BusinessException e) {
						e.printStackTrace();
					}
					if (negocio != null)
						funcionario.getListaNegocio().add(negocio);

				}

				if (funcionario != null) {
					setValue(funcionario);
				}
			}
		});

		binder.registerCustomEditor(FuncionarioBean.class, "listaTecnologia", new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) throws NumberFormatException {
				String[] ids = text.split(",");
				FuncionarioBean funcionario = null;
				for (String id : ids) {
					if (funcionario == null)
						funcionario = new FuncionarioBean();
					TecnologiaBean tecnologia = null;

					try {
						tecnologia = tecnologiaBusiness.obterPorId(new Integer(id));
					} catch (BusinessException e) {
						e.printStackTrace();
					}

					if (tecnologia != null)
						funcionario.getListaTecnologia().add(tecnologia);
				}

				if (funcionario != null) {
					setValue(funcionario);
				}
			}
		});
	}

}
