package br.com.resource.catalogoconhecimento.utils;

import br.com.resource.catalogoconhecimento.exceptions.AtributoNuloException;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;
import br.com.resource.catalogoconhecimento.exceptions.ConsultaNulaException;
import br.com.resource.catalogoconhecimento.exceptions.CpfInvalidoException;
import br.com.resource.catalogoconhecimento.exceptions.DataInvalidaException;
import br.com.resource.catalogoconhecimento.exceptions.EmailInvalidoException;
import br.com.resource.catalogoconhecimento.exceptions.NomeRepetidoException;
import br.com.resource.catalogoconhecimento.exceptions.QuantidadeTagException;
import br.com.resource.catalogoconhecimento.exceptions.RegistroVinculadoException;
import br.com.resource.catalogoconhecimento.exceptions.RgInvalidoException;
import br.com.resource.catalogoconhecimento.exceptions.TamanhoCampoException;
import br.com.resource.catalogoconhecimento.exceptions.UserInvalidoException;

public abstract class ExceptionUtil {

	public static BusinessException handleException(Exception e){
		
		String error = "Sistema indisponivel no momento!";
		
		if(e instanceof NomeRepetidoException){
			error = "Este nome j� existe na base de dados";
		}else if(e instanceof AtributoNuloException){
			error = "Por favor, digite todos os campos";
		}else if(e instanceof TamanhoCampoException){
			error = "Voc� excedeu o numero de caracteres";
		}else if(e instanceof ConsultaNulaException){
			error = "Sua busca n�o retornou nenhum resultado";
		}else if(e instanceof CpfInvalidoException){
			error = "Por favor, digite um cpf v�lido";
		}else if(e instanceof DataInvalidaException){
			error = "Por favor, digite uma data v�lida";
		}else if(e instanceof EmailInvalidoException){
			error = "Por favor, digite um email v�lido";
		}else if(e instanceof QuantidadeTagException){
			error = "Por favor, preencha os campos de busca";
		}else if(e instanceof RegistroVinculadoException){
			error = "N�o � poss�vel excluir esse registro";
		}else if(e instanceof RgInvalidoException){
			error = "Por favor, digite um rg v�lido";
		}else if(e instanceof UserInvalidoException){
			error = "Nome de usu�rio inv�lido";
		}
		
		return new BusinessException(error);
	}
}
