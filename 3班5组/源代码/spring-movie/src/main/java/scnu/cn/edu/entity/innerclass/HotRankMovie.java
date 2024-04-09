package scnu.cn.edu.entity.innerclass;

import lombok.Data;

@Data
public class HotRankMovie {
    private Integer movieId;         //电影id
    private String movieName;        //电影名
    private float rating;            //评分

    public HotRankMovie() {
    }

    public HotRankMovie(Integer movieId, String movieName, float rating) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.rating = rating;
    }
}
