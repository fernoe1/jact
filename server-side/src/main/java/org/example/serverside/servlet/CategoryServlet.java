package org.example.serverside.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.serverside.dao.impl.CategoryDAO;
import org.example.serverside.model.Category;
import org.example.serverside.util.CorsUtil;
import org.example.serverside.util.JsonUtil;

import java.io.IOException;

@WebServlet(name = "categoryServlet", value = "/categories/*")
public class CategoryServlet extends HttpServlet {
    CategoryDAO categoryDAO = new CategoryDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CorsUtil.setCorsHeaders(resp);
        CorsUtil.setContentAndEncoding(resp);

        ObjectMapper objectMapper = JsonUtil.getMapper();
        Category category;

        try {
            category = objectMapper.readValue(req.getInputStream(), Category.class);
        } catch (IOException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\": \"Invalid JSON format\"}");
            return;
        }

        boolean flag = categoryDAO.addCategory(category);

        if (flag) {
            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.getWriter().write(objectMapper.writeValueAsString(category));
        } else {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\": \"Failed to create product\"}");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CorsUtil.setCorsHeaders(resp);
        CorsUtil.setContentAndEncoding(resp);

        ObjectMapper objectMapper = JsonUtil.getMapper();

        String pathInfo = req.getPathInfo();
        String parentId = req.getParameter("parentId");

        if (pathInfo == null) {
            if (parentId != null) {
                resp.getWriter().write(objectMapper.writeValueAsString(categoryDAO.getSubCategories(Integer.parseInt(parentId))));
                resp.setStatus(HttpServletResponse.SC_OK);
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().write("{\"error\": \"Invalid path\"}");
            }
        }
    }
}
