package scnu.cn.edu.entity.innerclass;

import lombok.Data;

@Data
public class MovieinnerClass {
    private Integer movieId;
    private String movieName;
    private float rating;
    private String picture;
    private Integer videoVip;
    private String actor;
 public MovieinnerClass(){};
    public MovieinnerClass(Integer movieId, String movieName, float rating, String picture, Integer videoVip, String actor) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.rating = rating;
        this.picture = picture;
        this.videoVip = videoVip;
        this.actor = actor;
    }
}
