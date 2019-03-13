package org.mcc.demo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GuestBook
 */
@WebServlet(
		urlPatterns = { "/GuestBook" }, 
		initParams = { 
				@WebInitParam(name = "fileName", value = "/Users/Payne/gb.txt")
		})
public class GuestBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<String> entries;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuestBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		this.entries = Collections.synchronizedList(new ArrayList<String>());
		String fileName = config.getInitParameter("fileName");
		LineNumberReader in;
		try {
			in = new LineNumberReader(new FileReader(fileName));
			String line;
			while (null != (line = in.readLine())) {
				this.entries.add(line);
			}
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServletException(String.format("Problem reading file '%s'", fileName), e);
		}
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuilder entriesHtml = new StringBuilder("<ol>\n");
		for (String entry : entries) {
			entriesHtml.append(String.format("<li>%s</li>\n", entry));
		}
		response.getWriter().append("Served at: ").append(request.getContextPath()).append(entriesHtml.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
