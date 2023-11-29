package com.example.assignment;

import com.example.assignment.service.UserService;
import com.example.assignment.service.VideoService;
import com.example.assignment.service.impl.UserServiceImpl;
import com.example.assignment.service.impl.VideoServiceImpl;
//import com.example.assignment.service.impl.UserServiceMockImpl;
//import com.example.assignment.service.impl.VideoServiceMockImpl;

public class ServiceFactory {
    static VideoService videoService = new VideoServiceImpl();
    static UserService userService = new UserServiceImpl();

    public static VideoService getVideoService(){
        return videoService;
    }

    public static UserService getUserService(){
        return userService;
    }
}
