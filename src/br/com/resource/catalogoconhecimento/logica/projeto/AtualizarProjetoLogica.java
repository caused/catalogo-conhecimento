package br.com.resource.catalogoconhecimento.logica.projeto;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.ClienteBean;
import br.com.resource.catalogoconhecimento.bean.EquipeBean;
import br.com.resource.catalogoconhecimento.bean.NegocioBean;
import br.com.resource.catalogoconhecimento.bean.ProjetoBean;
import br.com.resource.catalogoconhecimento.bean.TecnologiaBean;
import br.com.resource.catalogoconhecimento.business.ClienteBusiness;
import br.com.resource.catalogoconhecimento.business.EquipeBusiness;
import br.com.resource.catalogoconhecimento.business.NegocioBusiness;
import br.com.resource.catalogoconhecimento.business.ProjetoBusiness;
import br.com.resource.catalogoconhecimento.business.ProjetoEquipeBusiness;
import br.com.resource.catalogoconhecimento.business.ProjetoNegocioBusiness;
import br.com.resource.catalogoconhecimento.business.ProjetoTecnologiaBusiness;
import br.com.resource.catalogoconhecimento.business.TecnologiaBusiness;
import br.com.resource.catalogoconhecimento.exceptions.AtributoNuloException;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class AtualizarProjetoLogica implements Logica{


	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		int id = Integer.parseInt(request.getParameter("id"));

		String nomeProjeto = request.getParameter("nomeProjeto");
		String observacao = request.getParameter("observacao");
		int idCliente = Integer.parseInt(request.getParameter("cliente"));
		String[] negocios = request.getParameterValues("negociosArray[]");
		String[] tecnologias = request.getParameterValues("tecnologiasArray[]");
		String [] equipes = request.getParameterValues("equipesArray[]");

		if(nomeProjeto.trim().equals("")){
			throw new AtributoNuloException("Por favor, digite um nome v�lido!");
		} else if( observacao.trim().equals("")){
			throw new AtributoNuloException("Por favor, digite uma observa��o v�lida!");
		}

		//transferir da String para a lista
		List<NegocioBean> listaNegocio = new ArrayList<>();
		NegocioBusiness negocioBusiness = new NegocioBusiness();
		NegocioBean negocio;
		for(int i = 0 ; i < negocios.length ; i ++){
			negocio =  negocioBusiness.obterPorNome(negocios[i]);
			listaNegocio .add(negocio);
		}

		//transferir da String para a lista
		List<TecnologiaBean> listaTecnologia = new ArrayList<>();
		TecnologiaBusiness tecnologiaBusiness = new TecnologiaBusiness();
		TecnologiaBean tecnologia;
		for(int i = 0 ; i < tecnologias.length ; i ++){
			tecnologia =  tecnologiaBusiness.obterPorNome(tecnologias[i]);
			listaTecnologia.add(tecnologia);
		}
		//transferir da String para a lista
		List<EquipeBean> listaEquipe = new ArrayList<>();
		EquipeBusiness equipeBusiness = new EquipeBusiness();
		EquipeBean equipe;
		for(int i = 0 ; i < equipes.length ; i ++){
			equipe =  equipeBusiness.obterPorNome(equipes[i]);
			listaEquipe.add(equipe);
		}

		ClienteBusiness clienteBusiness = new ClienteBusiness();
		ClienteBean cliente = clienteBusiness.obterPorId(idCliente);

		ProjetoBusiness projetoBusiness = new ProjetoBusiness();

		ProjetoBean projeto = projetoBusiness.obterPorId(id);
		projeto.setCliente(cliente);
		projeto.setNome(nomeProjeto.trim());
		projeto.setObservacao(observacao.trim());


		projetoBusiness.atualizar(projeto);

		ProjetoNegocioBusiness projetoNegocio = new ProjetoNegocioBusiness();
		projetoNegocio.atualizar(projeto, listaNegocio);

		ProjetoTecnologiaBusiness projetoTecnologia = new ProjetoTecnologiaBusiness();
		projetoTecnologia.atualizar(projeto, listaTecnologia);
		
		ProjetoEquipeBusiness projetoEquipe = new ProjetoEquipeBusiness();
		projetoEquipe.atualizar(projeto, listaEquipe);


		return "mvc?logica=projeto.ListarProjetoLogica";
	}

}
