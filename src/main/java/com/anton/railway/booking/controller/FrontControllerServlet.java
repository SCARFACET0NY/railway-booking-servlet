package com.anton.railway.booking.controller;

import com.anton.railway.booking.command.Command;
import com.anton.railway.booking.factory.CommandFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Front controller servlet which processes all requests
 */
@WebServlet("/")
public class FrontControllerServlet extends HttpServlet {
    private static final String JSP_PATH = "/WEB-INF/jsp/%s.jsp";

    /**
     * Processes get requests
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Processes post requests
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Gets path from URI to create {@code Command} and forwards request or redirects response
     * @param req - request
     * @param resp - response
     * @throws ServletException
     * @throws IOException
     */
    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getRequestURI()
                .substring(req.getRequestURI().indexOf("/") + 1);

        Command command = CommandFactory.getCommand(action);
        if (command == null) {
            resp.sendError(404);
        } else {
            String page = command.process(req, resp);

            if (page.startsWith("redirect:")) {
                resp.sendRedirect("/" + page.substring(page.indexOf(":") + 1));
            } else {
                req.getRequestDispatcher(String.format(JSP_PATH, page)).forward(req, resp);
            }
        }
    }
}
