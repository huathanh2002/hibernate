package com.example.assignment.controller.user;

import com.example.assignment.ServiceFactory;
import com.example.assignment.entity.Video;
import com.example.assignment.service.VideoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/favorite")
public class FavoriteServlet extends HttpServlet {
    VideoService service = ServiceFactory.getVideoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("requestURI", req.getRequestURI());
        String username = (String) req.getSession().getAttribute("username");
        if(username == null) {
            resp.sendRedirect("login");
            return;
        }
        String keyword = req.getParameter("keyword");
        if(keyword == null) keyword = "";

        System.out.println("In favorite, keyword=" + keyword);

        List<Video> items = service.searchFavorite(username, keyword);
        req.setAttribute("items", items);
        req.getRequestDispatcher("/view/favorite.jsp").forward(req, resp);
    }
}
