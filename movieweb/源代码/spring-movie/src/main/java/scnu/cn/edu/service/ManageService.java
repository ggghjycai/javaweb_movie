package scnu.cn.edu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scnu.cn.edu.entity.Actor;
import scnu.cn.edu.entity.ManageMovie;
import scnu.cn.edu.entity.Movie;
import scnu.cn.edu.entity.Type;
import scnu.cn.edu.mapper.ActorMapper;
import scnu.cn.edu.mapper.ManageMapper;
import scnu.cn.edu.mapper.MovieMapper;
import scnu.cn.edu.mapper.TypeMapper;

import java.util.ArrayList;
import java.util.List;


@Service
public class ManageService extends ServiceImpl<ManageMapper, ManageMovie> {

    @Autowired
    private ManageMapper manageMapper;

    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private ActorMapper actorMapper;

    @Autowired
    private TypeMapper typeMapper;

    //通过id删除电影
//    public void deleteMovieById(int movie_id) {
//        manageMapper.deleteById(movie_id);
//    }
//
    public ManageMovie findMoiveById(int movie_id) {
        return manageMapper.selectMovieWithActorsAndTypes(movie_id);
    }

    public String addMovie(ManageMovie manageMovie) {
        manageMapper.insertIntoMovie(manageMovie.getMovieId(), manageMovie.getMovieName(),
                manageMovie.getGenre(), manageMovie.getProductionPlace(),
                manageMovie.getDate(), manageMovie.getPicture(), manageMovie.getIntroduction(),
                manageMovie.getVideoUrl(), manageMovie.getVideoVip());
//        QueryWrapper<ManageMovie> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("introduction",manageMovie.getIntroduction());
//        queryWrapper.last("limit 1");
        int movieId = manageMovie.getMovieId();
        List<String> actorList = manageMovie.getActorList();
        List<String> typeList = manageMovie.getTypeList();
        boolean flag = true;
        for(String actor:actorList){
            Actor actor1 = new Actor();
            actor1.setMovieId(movieId);
            actor1.setMainActor(actor);
            if(actorMapper.insert(actor1) == 0){
                flag = false;
            }
        }
        for(String type:typeList){
            Type type1 = new Type();
            type1.setMovieId(movieId);
            type1.setType(type);
            if(typeMapper.insert(type1) == 0){
                flag = false;
            }
        }

        if(flag){
//            return "{ \"result\":\"添加成功\" }";
            return "{ \"result\":\"添加成功\" }";
        }
        else{
            return "{ \"result\":\"添加失败\" }";
        }
    }

    public ManageMovie findMovieById(Integer movieId) {
        QueryWrapper<ManageMovie> wrapper = new QueryWrapper<>();
        wrapper.eq("movie_id", movieId);
        return manageMapper.selectOne(wrapper);
    }

    public boolean deleteMovieById(Integer movieId) {
        QueryWrapper<Movie> wrapper = new QueryWrapper<>();
        wrapper.eq("movie_id", movieId);
        List<Movie> movies = movieMapper.selectList(wrapper);
//        boolean flag = true;
//        for(Movie movie:movies){
//            int flag1 = movieMapper.delete(wrapper);
//            int flag2 = actorMapper.deleteById(movie.getMovieId());
//            int flag3 = typeMapper.deleteById(movie.getMovieId());
//            if((flag1&flag2&flag3) == 0){
//                flag = false;
//            }
//        }
        boolean flag = true;
        int flag1 = movieMapper.delete(wrapper);
        int flag2 = actorMapper.deleteById(movieId);
        int flag3 = typeMapper.deleteById(movieId);
        if ((flag1 + flag2 + flag3) == 0){
            flag = false;
        }

        return flag;
    }

    public String updateMovie(ManageMovie manageMovie) {
        int movie_id = manageMovie.getMovieId();
        //修改movie表
        Movie movie = new Movie();
        movie.setMovieName(manageMovie.getMovieName());
        movie.setGenre(manageMovie.getGenre());
        movie.setProductionPlace(manageMovie.getProductionPlace());
        movie.setDate(manageMovie.getDate());
        movie.setRating(manageMovie.getRating());
        movie.setWeekHits(manageMovie.getWeekHits());
        movie.setMonthHits(manageMovie.getMonthHits());
        movie.setAllHits(manageMovie.getAllHits());
        movie.setPicture(manageMovie.getPicture());
        movie.setIntroduction(manageMovie.getIntroduction());
        movie.setVideoUrl(manageMovie.getVideoUrl());
        movie.setVideoVip(manageMovie.getVideoVip());

        UpdateWrapper<Movie> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("movie_id", movie_id);

        int flag = movieMapper.update(movie, updateWrapper);
        if(flag == 0){
            return "{ \"result\":\"修改失败\" }";
        }
        //修改actor表
        List<String> actorList = manageMovie.getActorList();
        actorMapper.deleteById(movie_id);
        for(String actor:actorList){
            Actor tmp = new Actor();
            tmp.setMovieId(movie_id);
            tmp.setMainActor(actor);

            flag = actorMapper.insert(tmp);
            if(flag == 0){
                return "{ \"result\":\"修改失败\" }";
            }
        }
        //修改type表
        List<String> typeList = manageMovie.getTypeList();
        typeMapper.deleteById(movie_id);
        for(String type:typeList){
            Type tmp = new Type();
            tmp.setType(type);
            tmp.setMovieId(movie_id);
            flag = typeMapper.insert(tmp);
            if(flag == 0){
                return "{ \"result\":\"修改失败\" }";
            }
        }

        return "{ \"result\":\"修改成功\" }";
    }

    public Movie findMovieMaxId() {
        QueryWrapper<Movie> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("movie_id");
        queryWrapper.last("limit 1");
//        List<Movie> movieList = movieMapper.selectList(queryWrapper);
        return movieMapper.selectOne(queryWrapper);
    }
}
