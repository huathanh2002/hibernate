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

@WebServlet("/detail")
public class DetailServlet extends HttpServlet {
    VideoService service = ServiceFactory.getVideoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Video video = service.findById(id);
        req.setAttribute("video", video);
        req.getRequestDispatcher("/view/detail.jsp").forward(req, resp);
    }
}
