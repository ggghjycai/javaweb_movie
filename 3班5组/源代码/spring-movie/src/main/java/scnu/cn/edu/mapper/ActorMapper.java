package scnu.cn.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import scnu.cn.edu.entity.Actor;

import java.util.List;

@Mapper
public interface ActorMapper extends BaseMapper<Actor> {
    @Select("select main_actor from actor where movie_id = #{movieId}")
    List<String> selectById(int movieId);

    @Insert("insert into actor (movie_id, main_actor) values(#{movieId},#{actor})")
    Integer insertIntoActor(@Param("movieId") int movieId, @Param("actor") String actor);
 }
