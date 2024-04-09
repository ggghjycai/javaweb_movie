package scnu.cn.edu.controller;

import net.sf.jsqlparser.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import scnu.cn.edu.entity.ManageMovie;
import scnu.cn.edu.entity.Movie;
import scnu.cn.edu.service.ManageService;
import scnu.cn.edu.service.MovieService;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@Controller
public class ManageController {
    @Autowired
    private ManageService manageService;

    @RequestMapping("/manage/add")
    @ResponseBody
    public String addMovie(
            @RequestParam(name="movieName")String movieName,
            @RequestParam(name="genre")String genre,
            @RequestParam(name="productionPlace")String productionPlace,
            @RequestParam(name="date")Date date,
            @RequestParam(name="picture")String picture,
            @RequestParam(name="introduction")String introduction,
            @RequestParam(name="videoUrl")String videoUrl,
            @RequestParam(name="videoVip")Integer videoVip,
            @RequestParam(name="actorList") List<String> actorList,
            @RequestParam(name="typeList")List<String> typeList
    ){
        Movie movie = manageService.findMovieMaxId();
        Integer movieId = movie.getMovieId()+1;
        ManageMovie manageMovie = new ManageMovie();
        manageMovie.setMovieId(movieId);
        manageMovie.setMovieName(movieName);
        manageMovie.setGenre(genre);
        manageMovie.setProductionPlace(productionPlace);
        manageMovie.setDate(date);
        manageMovie.setRating(0);
        manageMovie.setWeekHits(0);
        manageMovie.setMonthHits(0);
        manageMovie.setAllHits(0);
        manageMovie.setPicture(picture);
        manageMovie.setIntroduction(introduction);
        manageMovie.setVideoUrl(videoUrl);
        manageMovie.setVideoVip(videoVip);
        manageMovie.setActorList(actorList);
        manageMovie.setTypeList(typeList);
        return manageService.addMovie(manageMovie);
    }

    //通过id删除电影
//    @RequestMapping("/manage/delete")
//    @ResponseBody
//    public String deleteMovie(int movie_id){
//        Movie movie = manageService.findMoiveById(movie_id);
//        if(movie != null){
//            manageService.deleteMovieById(movie_id);
//            return "删除成功";
//        }
//        return "删除失败";
//    }

    @RequestMapping("/manage/delete")
    @ResponseBody
    public String deleteMovie(Integer movieId){
        boolean flag = manageService.deleteMovieById(movieId);
        if(flag){
//            manageService.deleteMovieById(movieId);
            return "{ \"result\":\"删除成功\" }";
        }
        return "{ \"result\":\"删除失败\" }";
    }

    @RequestMapping("/manage/update")
    @ResponseBody
    public String updateMovie(
            @RequestParam(name="movieId")Integer movieId,
            @RequestParam(name="movieName")String movieName,
            @RequestParam(name="genre")String genre,
            @RequestParam(name="productionPlace")String productionPlace,
            @RequestParam(name="date")Date date,
            @RequestParam(name="rating")Float rating,
            @RequestParam(name="weekHits")Integer weekHits,
            @RequestParam(name="monthHits")Integer monthHits,
            @RequestParam(name="allHits")Integer allHits,
            @RequestParam(name="picture")String picture,
            @RequestParam(name="introduction")String introduction,
            @RequestParam(name="videoUrl")String videoUrl,
            @RequestParam(name="videoVip")Integer videoVip,
            @RequestParam(name="actorList") List<String> actorList,
            @RequestParam(name="typeList")List<String> typeList
            ){
        ManageMovie manageMovie = new ManageMovie();
        manageMovie.setMovieId(movieId);
        manageMovie.setMovieName(movieName);
        manageMovie.setGenre(genre);
        manageMovie.setProductionPlace(productionPlace);
        manageMovie.setDate(date);
        manageMovie.setRating(rating);
        manageMovie.setWeekHits(weekHits);
        manageMovie.setMonthHits(monthHits);
        manageMovie.setAllHits(allHits);
        manageMovie.setPicture(picture);
        manageMovie.setIntroduction(introduction);
        manageMovie.setVideoUrl(videoUrl);
        manageMovie.setVideoVip(videoVip);
        manageMovie.setActorList(actorList);
        manageMovie.setTypeList(typeList);
        return manageService.updateMovie(manageMovie);
    }

    @RequestMapping("/manage/update/request")
    @ResponseBody
    public ManageMovie updateRequest(int movie_id){
        return manageService.findMoiveById(movie_id);
    }
}
