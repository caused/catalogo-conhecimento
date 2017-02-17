package br.com.resource.catalogoconhecimento.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.resource.catalogoconhecimento.bean.UsuarioBean;
import br.com.resource.catalogoconhecimento.utils.SessionUtil;

@Controller
@RequestMapping("home")
public class HomeController {

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) {
		HttpSession session = SessionUtil.getInstance(request);
		UsuarioBean usuario = (UsuarioBean) session.getAttribute("usuario");

		if (usuario != null)
			return new ModelAndView("index");
		else
			return new ModelAndView("redirect:/");
	}

}