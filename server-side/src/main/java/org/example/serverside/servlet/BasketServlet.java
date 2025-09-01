package org.example.serverside.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.serverside.util.ResponseUtil;

import java.io.IOException;

@WebServlet(name = "basketServlet", value = "/basket/*")
public class BasketServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponseUtil.setContentAndEncoding(resp);
        String pathInfo = req.getPathInfo();

        switch (pathInfo) {
            case null:
                break;
            default:
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
