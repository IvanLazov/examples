package com.clouway.gwt.bank.server;

import com.google.inject.Singleton;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
@Singleton
public class MainPageServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    req.getSession().getId();

    PrintWriter writer = resp.getWriter();
    resp.setContentType("text/html");
    writer.println(
            "<html>\n" +
                    "<head>\n" +
                    "    <title>Bank</title>\n" +
                    "    <link type=\"text/css\" rel=\"stylesheet\" href=\"Bank.css\">\n" +
                    "    <script type=\"text/javascript\" language=\"javascript\" src=\"Bank/Bank.nocache.js\"></script>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "\n" +
                    "</body>\n" +
                    "</html>\n"
    );

    writer.flush();
  }
}
