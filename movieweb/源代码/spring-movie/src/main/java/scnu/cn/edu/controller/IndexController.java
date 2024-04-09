package scnu.cn.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import scnu.cn.edu.entity.innerclass.HotRankMovie;
import scnu.cn.edu.entity.innerclass.MovieinnerClass;
import scnu.cn.edu.entity.innerclass.LunboInnerClass;
import scnu.cn.edu.service.LunboService;
import scnu.cn.edu.service.MovieService;
import scnu.cn.edu.service.TypeService;

import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "*")
@Controller
public class IndexController {
    @Autowired
    private MovieService movieService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private LunboService lunboService;

    //轮播
    @RequestMapping(path = "/index/lunbo",method = RequestMethod.GET)
    @ResponseBody
   public LunboInnerClass[] getlunboData
    (
    @RequestParam(name="pageNo", defaultValue="1")Integer pageNo,
    @RequestParam(name="pageSize",defaultValue = "5") Integer pageSize
    ){
    return   lunboService.getdata(pageNo,pageSize);
    }


    //最新影片
    @RequestMapping(path = "/index/latest",method = RequestMethod.GET)
    @ResponseBody
    public MovieinnerClass[] getLatestMovieData(
            @RequestParam(name="pageNo",defaultValue = "1")Integer pageNo,
            @RequestParam(name="pageSize",defaultValue = "8")Integer pageSize
            ){
        return movieService.getlatestdata(pageNo,pageSize);
    }

   //每周热播
    @RequestMapping(path="/index/weekHot",method = RequestMethod.GET)
    @ResponseBody
    public MovieinnerClass[] getweekHotMovieData(
            @RequestParam(name="pageNo",defaultValue = "1")Integer pageNo,
            @RequestParam(name="pageSize",defaultValue = "6")Integer pageSize
    ){
        return movieService.getweekhotdata(pageNo,pageSize);
    }

  //vip影片
    @RequestMapping(path = "/index/vipMovie",method = RequestMethod.GET)
    @ResponseBody
    public MovieinnerClass[] getvipMovieData(
            @RequestParam(name="pageNo",defaultValue = "1")Integer pageNo,
            @RequestParam(name="pageSize",defaultValue = "6")Integer pageSize
    ){
          return movieService.getvipmoviedata(pageNo,pageSize);
    }

    //所有影片
    @RequestMapping(path = "/index/allMovie",method = RequestMethod.GET)
   @ResponseBody
    public MovieinnerClass[] getallMovieData(
            @RequestParam(name="pageNo",defaultValue = "1")Integer pageNo,
            @RequestParam(name="pageSize",defaultValue = "24")Integer pageSize
    ){
        return movieService.getallmoviedata(pageNo,pageSize);
    }

    //人气排行
    @RequestMapping(path = "/index/hotRank",method = RequestMethod.GET)
    @ResponseBody
    public HotRankMovie[] gethotRankMovieData(
            @RequestParam(name="pageNo",defaultValue = "2")Integer pageNo,
            @RequestParam(name="pageSize",defaultValue = "10")Integer pageSize
    ){
        return movieService.gethotrankdata(pageNo,pageSize);
    }

    //好评排行
    @RequestMapping(path="/index/goodRank",method = RequestMethod.GET)
    @ResponseBody
    public HotRankMovie[] getgoodRankMovieData(
            @RequestParam(name="pageNo",defaultValue = "2")Integer pageNo,
            @RequestParam(name="pageSize",defaultValue = "10")Integer pageSize
    ){
        return movieService.getgoodrankdata(pageNo,pageSize);
    }



}
