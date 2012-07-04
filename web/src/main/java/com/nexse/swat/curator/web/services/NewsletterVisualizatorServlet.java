package com.nexse.swat.curator.web.services;

import com.nexse.swat.curator.persistence.domain.NewsletterData;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: germano
 * Date: 21/06/12
 * Time: 18:16
 * To change this template use File | Settings | File Templates.
 */
public class NewsletterVisualizatorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getParameter("token");
        NewsletterData newsletterData = NewsletterData.findNewsletterByToken(token);
        resp.setContentType("text/html; charset=utf-8");
        resp.getWriter().print(newsletterData.getBody());
    }

}
