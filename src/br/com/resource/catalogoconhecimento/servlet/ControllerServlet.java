package br.com.resource.catalogoconhecimento.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import br.com.resource.catalogoconhecimento.exceptions.BusinessException;
import br.com.resource.catalogoconhecimento.logica.Logica;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("/mvc")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerServlet() {
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String parametro = request.getParameter("logica");
		String nomeDaClasse = "br.com.resource.catalogoconhecimento.logica." + parametro;
		String logicaAtual = request.getParameter("logicaAtual");
		String pagina = "/mvc?logica="+logicaAtual;

		if(logicaAtual == null){
			pagina = "/index.jsp";
		}

		try {
			Class<?> classe = Class.forName(nomeDaClasse);

			Logica logica = (Logica) classe.newInstance();
			pagina = logica.executar(request, response);
		} catch (BusinessException  e) {
			request.setAttribute("msgErro", e.getMessage());
			e.printStackTrace();
		} catch(NullPointerException e){
			e.printStackTrace();
			request.setAttribute("msgErro", "Por favor, preencha todos os campos");
		}catch(SQLServerException e){
			e.printStackTrace();
			request.setAttribute("msgErro", "Falha na conex�o com o banco de dados");
		}catch(NumberFormatException e){
			request.setAttribute("msgErro", "Voc� digitou letra em campo num�rico!");
			e.printStackTrace();
		}catch(Exception e){
			request.setAttribute("msgErro", "Sistema indispon�vel no momento");
			e.printStackTrace();
		} finally {
			request.getRequestDispatcher(pagina).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
