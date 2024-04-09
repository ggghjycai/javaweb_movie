package scnu.cn.edu.entity.innerclass;

import lombok.Data;

import java.util.List;

@Data
public class SearchMovieClass {
    private Integer movieId;         //电影id
    private String movieName;        //电影名
    private String genre;            //基因
    private String production_place;  //地区
    private String date;               //日期
    private float rating;            //评分
    private String picture;         //图片
    private String introduction;    //简介
    private Integer video_vip;      //是否为vip电影
    private List<String> actorList;

    public SearchMovieClass(Integer movieId, String movieName,
                            String genre, String production_place, String date,
                            float rating, String picture, String introduction,
                            Integer video_vip, List<String> actorList) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.genre = genre;
        this.production_place = production_place;
        this.date = date;
        this.rating = rating;
        this.picture = picture;
        this.introduction = introduction;
        this.video_vip = video_vip;
        this.actorList = actorList;
    }
}
