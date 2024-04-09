package scnu.cn.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import scnu.cn.edu.entity.NewMovie;
import scnu.cn.edu.entity.innerclass.SearchMovieClass;
import scnu.cn.edu.service.MovieService;
import scnu.cn.edu.service.TypeService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "*")
@Controller
public class RankController {
    @Autowired
    private MovieService movieService;

    @Autowired
    private TypeService typeService;

/*
    @RequestMapping("/index/rank")
    public String rank(
            @RequestParam(name = "pageNo", defaultValue = "1")Integer pageNo,
            @RequestParam(name = "pageSize", defaultValue = "24")Integer pageSize,
            @RequestParam(name = "type", defaultValue = "全部")String type,
            @RequestParam(name = "area", defaultValue = "全部")String area,
            @RequestParam(name = "p", defaultValue = "总")String rank,

            Model model
    ){
        List<String> types = typeService.getType();
        List<String> areas = movieService.getAreas();

        Map<String, Object> result = movieService.queryRank(type, area, rank, pageNo, pageSize);

        int count = (int)result.get("count");
        int pageCount = (count % pageSize == 0) ? (count / pageSize) : (count / pageSize + 1);
        model.addAttribute("pageCount", pageCount);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("types", types);
        model.addAttribute("areas", areas);
        model.addAttribute("newMovies", result.get("movies"));
        model.addAttribute("type", type);
        model.addAttribute("area", area);
        model.addAttribute("rank", rank);
        return "rank";
    }
*/



    @RequestMapping(path = "/index/rank",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> rank(
            @RequestParam(name = "pageNo", defaultValue = "1")Integer pageNo,
            @RequestParam(name = "pageSize", defaultValue = "24")Integer pageSize,
            @RequestParam(name = "type", defaultValue = "全部")String type,
            @RequestParam(name = "area", defaultValue = "全部")String area,
            @RequestParam(name = "p", defaultValue = "总")String rank,

            Model model
    ){
        Map<String,Object> map=new HashMap<>();
        Map<String, Object> result = movieService.queryRank(type, area, rank, pageNo, pageSize);
        int count = (int)result.get("count");
        int pageTotal = (count % pageSize == 0) ? (count / pageSize) : (count / pageSize + 1);

        List<NewMovie> movieList= (List<NewMovie>) result.get("movies");
        List<SearchMovieClass> searchMovieClassList=new ArrayList<>();
        for (NewMovie movie : movieList) {
            SearchMovieClass searchMovieClass=new SearchMovieClass(movie.getMovieId(),movie.getMovieName(),movie.getGenre(),
                    movie.getProductionPlace(),movie.dateToYear(),movie.getRating(),movie.getPicture(),movie.getIntroduction(),
                    movie.getVideoVip(),movie.getActorList()
            );
            searchMovieClassList.add(searchMovieClass);
        }
        map.put("data",searchMovieClassList);
        map.put("pageTotal",pageTotal);
        return  map;
    }
}
