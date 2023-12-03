package com.example.assignment.controller.admin;

import com.example.assignment.ServiceFactory;
import com.example.assignment.entity.Video;
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
        String username = (String) req.getSession().getAttribute("username");
        if(username == null) {
            resp.sendRedirect("login");
            return;
        }
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
            add(req, resp);
        } else if (uri.contains("/video-update")) {
            update(req, resp);
        } else if (uri.contains("/video-delete")) {
            delete(req, resp);
        } else if (uri.contains("/video")) {
            viewVideo(req, resp);
        }
    }
    void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Video v=new Video();
        String id=req.getParameter("id");
        String title=req.getParameter("title");
        String poster=req.getParameter("poster");

        v.setId(id);
        v.setTitle(title);
        v.setPoster(poster);
        service.add(v);
        resp.sendRedirect("video");
    }
    void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id=req.getParameter("id");
        String title=req.getParameter("title");
        String poster=req.getParameter("poster");

        Video v=service.findById(id);

        v.setId(id);
        v.setTitle(title);
        v.setPoster(poster);
        service.add(v);
        service.update(v);
        resp.sendRedirect("video");
    }
    void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id=req.getParameter("id");
        service.deleteById(id);
        resp.sendRedirect("video");
    }
}
