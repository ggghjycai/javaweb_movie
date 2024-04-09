package scnu.cn.edu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scnu.cn.edu.entity.Type;
import scnu.cn.edu.mapper.TypeMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class TypeService extends ServiceImpl<TypeMapper, Type> {
    @Autowired
    private TypeMapper typeMapper;


    public List<String> getType() {
        QueryWrapper<Type> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("distinct type");
        List<Type> movieTypes = typeMapper.selectList(queryWrapper);
        List<String> types = new ArrayList<>();
        for(Type each: movieTypes){
            types.add(each.getType());
        }
        return types;
    }

    public List<String> findMovieTypeByid(Integer movieId){
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("movie_id",movieId);
        List<Type> types = (List<Type>) typeMapper.selectList(queryWrapper);
        List<String> typeList=new ArrayList<>();
        for (Type type : types) {
            typeList.add(type.getType());
        }
        return typeList;
    }

}
