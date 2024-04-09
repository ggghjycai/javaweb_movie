package scnu.cn.edu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.lang.annotation.Inherited;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Data
public class Movie{
    @TableId(type = IdType.AUTO)
    private Integer movieId;         //电影id
    private String movieName;        //电影名
    private String genre;            //基因
    private String productionPlace;  //地区
    private Date date;               //日期
    private float rating;            //评分
    private int weekHits;             //周排行
    private int monthHits;          //月排行
    private int allHits;             //全部排行
    private String picture;         //图片
    private String introduction;    //简介
    private String videoUrl;        //电影路径
    private Integer videoVip;      //是否为vip电影

//    @TableField(exist = false)
//    private List<Actor> actorList;
//
//    @TableField(exist = false)
//    private List<Type> typeList;

    public Movie(){}
    public Movie(Movie movie) {       //全部电影的构造方法
        this.movieId = movie.getMovieId();
        this.movieName = movie.getMovieName();
        this.genre = movie.getGenre();
        this.productionPlace = movie.getProductionPlace();
        this.date = movie.getDate();
        this.rating = movie.getRating();
        this.weekHits = movie.getWeekHits();
        this.monthHits = movie.getMonthHits();
        this.allHits = movie.getAllHits();
        this.picture = movie.getPicture();
        this.introduction = movie.getIntroduction();
        this.videoUrl = movie.getVideoUrl();
        this.videoVip = movie.videoVip;
    }
     //将日期进行格式转换
    public String dateToYear(){
        return new SimpleDateFormat("yyyy").format(date);
    }
}
