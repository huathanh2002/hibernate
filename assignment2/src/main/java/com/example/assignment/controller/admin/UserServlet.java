package com.example.assignment.controller.admin;

import com.example.assignment.ServiceFactory;
import com.example.assignment.entity.User;
import com.example.assignment.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet({"/user",
        "/user-add",
        "/user-delete",
        "/user-update"})
public class UserServlet extends HttpServlet {
    UserService service = ServiceFactory.getUserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("/user-add")) {
            viewAdd(req, resp);
        } else if (uri.contains("/user-update")) {
            viewUpdate(req, resp);
        } else if (uri.contains("/user-delete")) {
            viewDelete(req, resp);
        } else if (uri.contains("/user")) {
            viewUser(req, resp);
        }

    }

    void viewUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("list", service.getAll());
        req.getRequestDispatcher("/user/hien-thi.jsp").forward(req, resp);
    }

    void viewAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/user/formAdd.jsp").forward(req, resp);
    }

    void viewUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        req.setAttribute("nv", service.getById(id));
        req.getRequestDispatcher("/user/formUpdate.jsp").forward(req, resp);
    }

    void viewDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        req.setAttribute("nv", service.getById(id));
        req.getRequestDispatcher("/user/formDelete.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("/user-add")) {
            add(req, resp);
        } else if (uri.contains("/user-update")) {
            update(req, resp);
        } else if (uri.contains("/user-delete")) {
            delete(req, resp);
        }
    }
    void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        service.deleteById(id);
        resp.sendRedirect("user");
    }
    void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String password = req.getParameter("password");
        String fullname = req.getParameter("fullname");
        String email = req.getParameter("email");
        String photo = req.getParameter("photo");
        String activated = req.getParameter("activated");
        String admin = req.getParameter("admin");

        User nv = service.getById(id);
        nv.setId(id);
        nv.setPassword(password);
        nv.setFullname(fullname);
        nv.setEmail(email);
        nv.setPhoto(photo);
        nv.setActivated(activated != null);
        nv.setAdmin(admin != null);
        service.update(nv);
        resp.sendRedirect("user");
    }

    void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User nv = new User();

        String id = req.getParameter("id");
        String password = req.getParameter("password");
        String fullname = req.getParameter("fullname");
        String email = req.getParameter("email");
        String photo = req.getParameter("photo");
        String activated = req.getParameter("activated");
        String admin = req.getParameter("admin");

        nv.setId(id);
        nv.setPassword(password);
        nv.setFullname(fullname);
        nv.setEmail(email);
        nv.setPhoto(photo);
        nv.setActivated(activated != null);
        nv.setAdmin(admin != null);
//        if ("1".equals(activated)){
//            nv.setActivated(true);
//        }else{
//            nv.setActivated(false);
//        }
//        if ("1".equals(admin)){
//            nv.setAdmin(true);
//        }else{
//            nv.setAdmin(false);
//        }

        service.create(nv);
        resp.sendRedirect("user");
    }
}
