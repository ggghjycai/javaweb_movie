package scnu.cn.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import scnu.cn.edu.entity.Movie;
import scnu.cn.edu.entity.NewMovie;
import scnu.cn.edu.entity.Type;
import scnu.cn.edu.entity.innerclass.SearchMovieClass;
import scnu.cn.edu.service.MovieService;
import scnu.cn.edu.service.TypeService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "*")
@Controller
public class SearchController {
    @Autowired
    private TypeService typeService;

    @Autowired MovieService movieService;

    //查找类型
    @RequestMapping(path = "/index/type",method = RequestMethod.GET)
    @ResponseBody
    public List<String> gettypes(){
       return typeService.getType();
    }

   //查找地区
    @RequestMapping(path="/index/area",method = RequestMethod.GET)
    @ResponseBody
    public List<String> getareas(){
        return movieService.getAreas();
    }

    //查找基因
    @RequestMapping(path = "/index/genre",method = RequestMethod.GET)
    @ResponseBody
    public List<String> getgenres(){
        return movieService.getGenre();
    }

    //搜索
    @RequestMapping(value = "/index/search",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> index(
            @RequestParam(name = "tag", defaultValue = "0") Integer tag,
            @RequestParam(name = "text", defaultValue = "") String text,
            @RequestParam(name = "pageNo", defaultValue = "1")Integer pageNo,
            @RequestParam(name = "pageSize", defaultValue = "24")Integer pageSize,
            @RequestParam(name = "type", defaultValue = "全部")String type,
            @RequestParam(name = "area", defaultValue = "全部")String area,
            @RequestParam(name = "g", defaultValue = "全部")String g,
            @RequestParam(name = "p", defaultValue = "最热")String p
     ){

        Map<String,Object> map=new HashMap<>();
        Map<String, Object> result = movieService.queryPage(tag, text, pageNo, pageSize, type, area, g, p);
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
        return map;
    }
}
