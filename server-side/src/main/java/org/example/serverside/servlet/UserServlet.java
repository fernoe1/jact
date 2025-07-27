package org.example.serverside.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet(name = "userServlet", value = "/users")
public class UserServlet extends HttpServlet {

}
