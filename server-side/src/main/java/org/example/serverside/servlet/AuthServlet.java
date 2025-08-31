package org.example.serverside.servlet;

import com.fasterxml.jackson.databind.JsonNode;
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
                    break;
                case "/login":
                    handleLogin(req, resp);
                    break;
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

            User existingUser = userDAO.getUserByEmail(user.getEmail());
            if (existingUser != null && user.getEmail().equals(existingUser.getEmail())) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\": \"User with that email already exists\"}");
                return;
            }

            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            user.setPassword(hashedPassword);

            if (userDAO.addUser(user)) {
                resp.setStatus(HttpServletResponse.SC_OK);
                String token = JwtUtil.generateToken(user);
                resp.getWriter().write("{\"token\": \"" + token + "\"}");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void handleLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
      ObjectMapper mapper = JsonUtil.getMapper();
      JsonNode jsonNode = mapper.readTree(req.getReader());

      try {
            String email = jsonNode.get("email").asText();
            String password = jsonNode.get("password").asText();

            if (email == null || email.trim().isEmpty() ||
                password == null || password.trim().isEmpty()) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\": \"Email and password are required\"}");
                return;
            }

            User user = userDAO.getUserByEmail(email);

            if (user == null || !BCrypt.checkpw(password, user.getPassword())) {
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                resp.getWriter().write("{\"error\": \"Either username or password are invalid\"}");
                return;
            }

            String token = JwtUtil.generateToken(user);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("{\"token\": \"" + token + "\"}");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
