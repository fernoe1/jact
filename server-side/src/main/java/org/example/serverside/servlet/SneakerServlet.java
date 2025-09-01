package org.example.serverside.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.serverside.dao.impl.SneakerDAO;
import org.example.serverside.model.Sneaker;
import org.example.serverside.util.JsonUtil;
import org.example.serverside.util.ResponseUtil;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "sneakerServlet", value="/sneaker/*")
public class SneakerServlet extends HttpServlet {
    private final SneakerDAO sneakerDAO = new SneakerDAO();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponseUtil.setContentAndEncoding(resp);
        String pathInfo = req.getPathInfo();
        switch (pathInfo) {
            case null:
                handleGetAllSneakers(req, resp);
            default:
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponseUtil.setContentAndEncoding(resp);
        String pathInfo = req.getPathInfo();
        switch (pathInfo) {
            case null:
                handlePostSneaker(req, resp);
                break;
            default:
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void handlePostSneaker(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = JsonUtil.getMapper();
        Sneaker sneaker = mapper.readValue(req.getReader(), Sneaker.class);

        if (sneakerDAO.addSneaker(sneaker)) {
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write(mapper.writeValueAsString(sneaker));
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void handleGetAllSneakers(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = JsonUtil.getMapper();
        List<Sneaker> sneakers = sneakerDAO.getSneakers();

        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().write(mapper.writeValueAsString(sneakers));
    }
}
