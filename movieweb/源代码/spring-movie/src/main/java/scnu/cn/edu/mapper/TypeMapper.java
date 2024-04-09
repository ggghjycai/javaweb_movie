package scnu.cn.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import scnu.cn.edu.entity.Type;

import java.util.List;

@Mapper
public interface TypeMapper extends BaseMapper<Type> {
    @Select("select type from type where movie_id = #{movieId}")
    List<String> selectById(int movieId);

    @Insert("insert into type (movie_id,type) values (#{movieId},#{type})")
    Integer insertIntoType(@Param("movieId") int movieId, @Param("type") String type);

}
