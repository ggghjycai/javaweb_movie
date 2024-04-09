package scnu.cn.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import scnu.cn.edu.entity.ManageMovie;
import scnu.cn.edu.entity.Movie;

import java.util.Date;
import java.util.List;

@Mapper
public interface ManageMapper extends BaseMapper<ManageMovie> {
    @Select("select * from movie where movie_id = #{movieId}")
    @Results (
            {
                    @Result(column = "movie_id", property = "movieId"),
                    @Result(column = "movie_name", property = "movieName"),
                    @Result(column = "genre", property = "genre"),
                    @Result(column = "production_place", property = "productionPlace"),
                    @Result(column = "date", property = "date"),
                    @Result(column = "rating", property = "rating"),
                    @Result(column = "week_hits", property = "weekHits"),
                    @Result(column = "month_hits", property = "monthHits"),
                    @Result(column = "all_hits", property = "allHits"),
                    @Result(column = "picture", property = "picture"),
                    @Result(column = "introduction", property = "introduction"),
                    @Result(column = "video_url", property = "videoUrl"),
                    @Result(column = "video_vip", property = "videoVip"),
                    @Result(column = "movie_id", property = "actorList", javaType = List.class,
                    many = @Many(select = "scnu.cn.edu.mapper.ActorMapper.selectById")
                    ),
                    @Result(column = "movie_id", property = "typeList", javaType = List.class,   //string
                            many = @Many(select = "scnu.cn.edu.mapper.TypeMapper.selectById")
                    )
            }
    )
    ManageMovie selectMovieWithActorsAndTypes(int movieId);

    @Insert("insert into movie (movie_id, movie_name,genre,production_place,date,picture,introduction,video_url,video_vip)" +
            " values(#{movieId},#{movieName},#{genre},#{productionPlace},#{date},#{picture},#{introduction},#{videoUrl},#{videoVip})")
//    Integer insertIntoMovie(String movieName, String genre, String productionPlace, Date date,
//                            String picture, String introduction, String videoUrl, Integer videoVip);
    void insertIntoMovie(
            @Param("movieId") Integer movieId,
            @Param("movieName") String movieName, @Param("genre") String genre,
            @Param("productionPlace") String productionPlace, @Param("date") Date date,
            @Param("picture") String picture, @Param("introduction") String introduction,
            @Param("videoUrl") String videoUrl, @Param("videoVip") Integer videoVip);

}
