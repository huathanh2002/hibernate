//package com.example.assignment.service.impl;
//
//import com.example.assignment.entity.Video;
//import com.example.assignment.service.VideoService;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class VideoServiceMockImpl implements VideoService {
//    List<Video> movieList = new ArrayList<Video>(){{
//        add(new Video("t52kogxS5Uk", "Bắp ngô trái cây ngon ngọt, ăn luôn mà không cần luộc"));
//        add(new Video("ZbXoAHaoy5c", "Kỹ sư thiết kế vi mạch Việt Nam vươn tầm quốc tế"));
//        add(new Video("1b2I6VmRqtw", "Tranh cãi về xu thế làm việc từ xa sau đại dịch"));
//    }};
//
//    @Override
//    public Video findById(String id) {
//        for(Video m : movieList){
//            if(m.getId().equals(id)) return m;
//        }
//        return null;
//    }
//
//    @Override
//    public Video add(Video m) {
//        if(m.getId().isEmpty()){
//            throw new RuntimeException("Id cannot be blank");
//        }
//        if(findById(m.getId()) != null){
//            throw new RuntimeException("Movie with id '" + m.getId() + "' already exists");
//        }
//        movieList.add(m);
//        return m;
//    }
//
//    @Override
//    public Video update(Video m) {
//        if(findById(m.getId()) == null){
//            throw new RuntimeException("Movie with id '" + m.getId() + "' does not exist");
//        }
//        for(int i = 0; i < movieList.size(); i++){
//            if(movieList.get(i).getId().equals(m.getId())){
//                movieList.set(i, m);
//            }
//        }
//        return m;
//    }
//
//    @Override
//    public void deleteById(String id) {
//        int index = -1;
//        for(int i = 0; i < movieList.size(); i++) {
//            if (movieList.get(i).getId().equals(id)) {
//                index = i;
//                break;
//            }
//        }
//        if(index >= 0) movieList.remove(index);
//    }
//
//    @Override
//    public List<Video> findAll() {
//        return movieList;
//    }
//
//    @Override
//    public List<Video> search(String keyword) {
//        List<Video> items = new ArrayList<>();
//        for(Video m : movieList){
//            if(m.getTitle() == null) continue;
//            if(m.getTitle().toLowerCase().contains(keyword.toLowerCase())){
//                items.add(m);
//            }
//        }
//        return items;
//    }
//
//    @Override
//    public List<Video> searchFavorite(String userId, String keyword) {
//        List<Video> items = new ArrayList<>();
//        for(Video m : movieList){
//            if(m.getTitle() == null) continue;
//            if(m.getTitle().toLowerCase().contains(keyword.toLowerCase())){
//                items.add(m);
//            }
//        }
//        return items;
//    }
//}
