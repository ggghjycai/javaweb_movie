package scnu.cn.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import scnu.cn.edu.entity.Member;
import scnu.cn.edu.entity.NewMovie;
import scnu.cn.edu.entity.innerclass.MovieDetail;
import scnu.cn.edu.service.MovieService;
import scnu.cn.edu.service.TypeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@CrossOrigin(origins = "*")
@Controller
public class DetailController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private TypeService typeService;


    @RequestMapping(path = "/index/detail",method = RequestMethod.GET)
    @ResponseBody
    public MovieDetail getdetail(
            Integer movieId,
            HttpServletRequest request,
            HttpSession session
    ){
        if(movieId==0)return null;
           NewMovie movie=movieService.findMovieById(movieId);
           boolean limitation=true;
           MovieDetail movieDetail=new MovieDetail(
                   movie.getMovieId(),movie.getMovieName(),movie.getGenre(),
                   movie.dateToYear(),movie.getRating(),movie.getPicture(),
                   movie.getIntroduction(),movie.getVideoUrl(),movie.getVideoVip(),
                   movie.getActorList(),typeService.findMovieTypeByid(movieId),movie.getProductionPlace(),limitation
           );
        Member member=(Member)request.getServletContext().getAttribute("memberLogin");
        if(member!=null){
            if(member.getPm()<movieDetail.getVideoVip()){
                movieDetail.setVideoUrl("");
                movieDetail.setLimitation(false);
            }
        }else if(movieDetail.getVideoVip()>0){
            movieDetail.setLimitation(false);
        }
        return movieDetail;
    }

}
