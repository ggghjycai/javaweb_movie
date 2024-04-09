package scnu.cn.edu.entity.innerclass;

import lombok.Data;

import java.util.List;

@Data
public class MovieDetail {
    private Integer movieId;         //电影id
    private String movieName;        //电影名
    private String genre;            //基因
    private String date;               //日期
    private float rating;            //评分
    private String picture;         //图片
    private String introduction;    //简介
    private String videoUrl;        //电影路径
    private Integer videoVip;      //是否为vip电影
    private List<String> actorList;
    private List<String> typeList;   //类型列表
    private String productionPlace;  //地区
    private boolean limitation;

    public MovieDetail(Integer movieId, String movieName,
                       String genre, String date, float rating,
                       String picture, String introduction, String videoUrl,
                       Integer videoVip, List<String> actorlist, List<String> typeList, String productionPlace, boolean limitation ) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.genre = genre;
        this.date = date;
        this.rating = rating;
        this.picture = picture;
        this.introduction = introduction;
        this.videoUrl = videoUrl;
        this.videoVip = videoVip;
        this.actorList = actorlist;
        this.typeList=typeList;
        this.productionPlace=productionPlace;
        this.limitation = limitation;
    }


}
