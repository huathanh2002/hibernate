package com.example.assignment;

import com.example.assignment.service.UserService;
import com.example.assignment.service.VideoService;
import com.example.assignment.service.impl.UserServiceMockImpl;
import com.example.assignment.service.impl.VideoServiceMockImpl;

public class ServiceFactory {
    static VideoService videoService = new VideoServiceMockImpl();
    static UserService userService = new UserServiceMockImpl();

    public static VideoService getVideoService(){
        return videoService;
    }

    public static UserService getUserService(){
        return userService;
    }
}
