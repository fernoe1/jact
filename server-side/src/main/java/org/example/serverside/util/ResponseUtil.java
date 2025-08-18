package org.example.serverside.util;

import jakarta.servlet.http.HttpServletResponse;

public class ResponseUtil {
    public static void setContentAndEncoding(HttpServletResponse resp) {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
    }
}
