package at.wtfp.servlets;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet(asyncSupported = true, description = "Entry Point", urlPatterns = { "/*" })
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Path filePath = Paths.get("/resources/");
			
		System.out.println("Request Pathinfo: "+request.getPathInfo());
		String url = request.getPathInfo();
		String[] url_split = url.split("/");
		
		if(url_split[0].startsWith("/"));
			url_split[0].
		
		if(!urlurl_split.length > 1) 
		String vorname = url_split[1];
		
		Map<String, String[]> parameterMap = request.getParameterMap();
		for(Entry<String, String[]> entry : parameterMap.entrySet()) {
			System.out.println("Entry key: "+entry.getKey());
			System.out.println("Entry value(s): ");
			for(String str : entry.getValue()) {
				System.out.println("\tValue: "+str);
			}
		}
		response.setContentType("text/html");
		response.getWriter().println("<html><head></head><body><h1>Hallo <% name %></h1></html>".replace("<% name %>", newChar));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
