package org.example.serverside.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.serverside.dao.impl.UserDAO;
import org.example.serverside.model.User;
import org.example.serverside.util.JsonUtil;
import org.example.serverside.util.JwtUtil;
import org.example.serverside.util.ResponseUtil;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "authServlet", value = "/auth/*")
public class AuthServlet extends HttpServlet {
    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponseUtil.setContentAndEncoding(resp);

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
            throw new RuntimeException(e);
        }
    }

    private void handleRegister(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = JsonUtil.getMapper();

        try {
            User user = mapper.readValue(req.getReader(), User.class);

            if (user.getName() == null || user.getName().trim().isEmpty() ||
                user.getEmail() == null || user.getEmail().trim().isEmpty() ||
                user.getPassword() == null || user.getPassword().trim().isEmpty()) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\": \"Name, email and password are required\"}");
                return;
            }

            if (user.getEmail().equals(userDAO.getUserByEmail(user.getEmail()).getEmail()) ) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\": \"User with that email already exists\"}");
                return;
            }

            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            user.setPassword(hashedPassword);

            if (userDAO.addUser(user)) {
                resp.setStatus(HttpServletResponse.SC_CREATED);
                resp.getWriter().write("{\"message\": \"User created successfully\"}");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void handleLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = JsonUtil.getMapper();

        try {
            User loginRequest = mapper.readValue(req.getReader(), User.class);

            if (loginRequest.getName() == null || loginRequest.getName().trim().isEmpty() ||
                loginRequest.getPassword() == null || loginRequest.getPassword().trim().isEmpty()) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\": \"Username and password are required\"}");
                return;
            }

            User user = userDAO.getUserByName(loginRequest.getName());

            if (user == null || !BCrypt.checkpw(loginRequest.getPassword(), user.getPassword())) {
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                resp.getWriter().write("{\"error\": \"Either username or password are invalid\"}");
                return;
            }

            String token = JwtUtil.generateToken(user);

            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("message", "Login successful");
            responseBody.put("token", token);
            responseBody.put("user", Map.of(
                    "id", user.getId(),
                    "name", user.getName(),
                    "email", user.getEmail()
            ));

            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write(mapper.writeValueAsString(responseBody));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
