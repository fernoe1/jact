package org.example.serverside.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.serverside.dao.impl.ProductDAO;
import org.example.serverside.model.Product;
import org.example.serverside.util.CorsUtil;
import org.example.serverside.util.JsonUtil;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "productServlet", value = "/products/*")
public class ProductServlet extends HttpServlet {
    ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CorsUtil.setCorsHeaders(resp);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        ObjectMapper objectMapper = JsonUtil.getMapper();
        Product product;

        try {
            product = objectMapper.readValue(req.getInputStream(), Product.class);
        } catch (IOException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\": \"Invalid JSON format\"}");
            return;
        }

        boolean flag = productDAO.addProduct(product);

        if (flag) {
            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.getWriter().write(objectMapper.writeValueAsString(product));
        } else {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\": \"Failed to create product\"}");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CorsUtil.setCorsHeaders(resp);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String pathInfo = req.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            handleGetAllProducts(req, resp);
        } else {
            Pattern pattern = Pattern.compile("^/(\\d+)/?$");
            Matcher matcher = pattern.matcher(pathInfo);

            if (matcher.matches()) {
                handleGetProductById(req, resp, Integer.parseInt(matcher.group(1)));
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().write("{\"error\": \"Invalid path\"}");
            }
        }
    }

    private void handleGetAllProducts(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = JsonUtil.getMapper();
        resp.getWriter().write(mapper.writeValueAsString(productDAO.getAllProducts()));
    }

    private void handleGetProductById(HttpServletRequest req, HttpServletResponse resp, int id) throws IOException {
        ObjectMapper mapper = JsonUtil.getMapper();
        resp.getWriter().write(mapper.writeValueAsString(productDAO.getProduct(id)));
    }
}
