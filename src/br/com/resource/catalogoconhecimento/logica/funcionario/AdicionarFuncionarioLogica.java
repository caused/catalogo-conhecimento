package br.com.resource.catalogoconhecimento.logica.funcionario;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.CargoBean;
import br.com.resource.catalogoconhecimento.bean.FuncionarioBean;
import br.com.resource.catalogoconhecimento.bean.TecnologiaBean;
import br.com.resource.catalogoconhecimento.business.CargoBusiness;
import br.com.resource.catalogoconhecimento.business.FuncionarioBusiness;
import br.com.resource.catalogoconhecimento.business.TecnologiaFuncionarioBusiness;
import br.com.resource.catalogoconhecimento.business.TecnologiaBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class AdicionarFuncionarioLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String nomeFuncionario = request.getParameter("nome");
		String telefone = request.getParameter("telefone");
		String nomeUsuario = request.getParameter("nomeUser");
		String email = request.getParameter("email");
		String[] tecnologias = request.getParameterValues("tecnologiasArray");
		String cargo = request.getParameter("cargo");
		String cpf = request.getParameter("cpf");
		String rg = request.getParameter("rg");
		String data = request.getParameter("dataNascimento");

		// CONVERTENDO A DATA
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		Date dataFormatada = formatador.parse(data);

		CargoBean cargoBean = new CargoBean();
		CargoBusiness cargoBusiness = new CargoBusiness();

		cargoBean = cargoBusiness.obterPorNome(cargo);

		List<TecnologiaBean> listaTecnologia = new ArrayList<>();
		TecnologiaBusiness tecnologiaBusiness = new TecnologiaBusiness();
		TecnologiaBean tecnologia;
		for (int i = 0; i < tecnologias.length; i++) {
			tecnologia = tecnologiaBusiness.obterPorNome(tecnologias[i]);
			listaTecnologia.add(tecnologia);
		}

		FuncionarioBean funcionario = new FuncionarioBean();
		funcionario.setNome(nomeFuncionario);
		funcionario.setEmail(email);
		funcionario.setNomeUser(nomeUsuario);
		funcionario.setTelefone(telefone);
		funcionario.setCargo(cargoBean);
		funcionario.setCpf(cpf);
		funcionario.setRg(rg);
		funcionario.setDataNascimento(dataFormatada);

		funcionario.setTecnologias(listaTecnologia);

		FuncionarioBusiness funcionarioBusiness = new FuncionarioBusiness();
		int id = funcionarioBusiness.inserir(funcionario);
		funcionario.setId(id);

		TecnologiaFuncionarioBusiness funcionariotecnologia = new TecnologiaFuncionarioBusiness();
		funcionariotecnologia.inserir(funcionario, listaTecnologia);

		return "mvc?logica=funcionario.ListarFuncionarioLogica";
	}

}
