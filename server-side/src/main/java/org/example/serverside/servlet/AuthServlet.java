package org.example.serverside.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.serverside.util.CorsUtil;

import java.io.IOException;

@WebServlet(name = "authServlet", value = "/auth/*")
public class AuthServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CorsUtil.setCorsHeaders(resp);
        CorsUtil.setContentAndEncoding(resp);

        String pathInfo = req.getPathInfo();

        try {
            switch (pathInfo) {
                case "/register":
                    handleRegister(req, resp);
                case "/login":
                    handleLogin(req, resp);
                default:
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (IOException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
    }

    private void handleLogin(HttpServletRequest req, HttpServletResponse resp) {

    }

    private void handleRegister(HttpServletRequest req, HttpServletResponse resp) {

    }
}
