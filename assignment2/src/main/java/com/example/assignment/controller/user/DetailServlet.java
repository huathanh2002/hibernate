package com.example.assignment.controller.user;

import com.example.assignment.ServiceFactory;
import com.example.assignment.entity.Favorite;
import com.example.assignment.entity.User;
import com.example.assignment.entity.Video;
import com.example.assignment.service.FavoriteService;
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
    FavoriteService favoriteService=new FavoriteService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Video video = service.findById(id);
        req.setAttribute("video", video);

        String userId = (String) req.getSession().getAttribute("username");
//        String videoId="dLP92VYsjB4";
//        System.out.println(userId+"+id+"+id+"+idvideo");
        boolean check=favoriteService.isFavorite(userId,id);
        if (check){
            req.getSession().setAttribute("check","ok");
        }else{
            req.getSession().setAttribute("check","kook");
        }

        req.getRequestDispatcher("/view/detail.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action"); // Lấy tham số action từ request để xác định hành động người dùng (like hoặc unlike)

        if ("like".equals(action)) {
            String videoId = req.getParameter("videoId"); // Lấy ID của video từ request
            String userId = (String) req.getSession().getAttribute("username"); // Lấy ID của người dùng từ session (giả sử đã được lưu trong session)
//            System.out.println(videoId+"+id_video");
//            System.out.println(userId+"+id_user");
            // Kiểm tra nếu videoId và userId không null và không rỗng
            if (videoId != null && !videoId.isEmpty() && userId != null && !userId.isEmpty()) {
                // Tạo một đối tượng Favorite mới
                Favorite favorite = new Favorite();

                // Đặt thông tin User và Video cho đối tượng Favorite
                User user = new User();
                user.setId(userId);
                favorite.setUser(user);

                Video video = new Video();
                video.setId(videoId);
                favorite.setVideo(video);

                // Gọi service để thêm Favorite vào cơ sở dữ liệu
                favoriteService.create(favorite);

                resp.sendRedirect("favorite");
                return;
            }
        }else if ("unlike".equals(action)){
            String id = req.getParameter("videoId");
            favoriteService.deleteByVideoId(id);
            resp.sendRedirect("home");
        }
    }
}
