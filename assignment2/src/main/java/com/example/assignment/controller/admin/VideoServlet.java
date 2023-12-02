package com.example.assignment.controller.admin;

import com.example.assignment.ServiceFactory;
import com.example.assignment.service.VideoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet({"/video", "/video-add", "/video-update", "/video-delete"})
public class VideoServlet extends HttpServlet {
    VideoService service = ServiceFactory.getVideoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("/video-add")) {
            viewAdd(req, resp);
        } else if (uri.contains("/video-update")) {
            viewUpdate(req, resp);
        } else if (uri.contains("/video-delete")) {
            viewDelete(req, resp);
        } else if (uri.contains("/video")) {
            viewVideo(req, resp);
        }

    }

    void viewVideo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("list", service.findAll());
        req.getRequestDispatcher("/video/hien-thi.jsp").forward(req, resp);
    }

    void viewAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/video/formAdd.jsp").forward(req, resp);
    }

    void viewUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        req.setAttribute("v", service.findById(id));
        req.getRequestDispatcher("/video/formUpdate.jsp").forward(req, resp);
    }

    void viewDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        req.setAttribute("v", service.findById(id));
        req.getRequestDispatcher("/video/formDelete.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("/video-add")) {
            viewAdd(req, resp);
        } else if (uri.contains("/video-update")) {
            viewUpdate(req, resp);
        } else if (uri.contains("/video-delete")) {
            viewDelete(req, resp);
        } else if (uri.contains("/video")) {
            viewVideo(req, resp);
        }
    }
    void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        req.setAttribute("v", service.findById(id));
        req.getRequestDispatcher("/video/formDelete.jsp").forward(req, resp);
    }
}
