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

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    VideoService service = ServiceFactory.getVideoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        if(keyword == null) keyword = "";

        System.out.println("In home, keyword=" + keyword);

        List<Video> items = service.search(keyword);
        req.setAttribute("requestURI", req.getRequestURI());
        req.setAttribute("items", items);
        req.getRequestDispatcher("/view/home.jsp").forward(req, resp);
    }
}
