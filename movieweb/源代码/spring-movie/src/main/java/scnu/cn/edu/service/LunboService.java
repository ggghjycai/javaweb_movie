package scnu.cn.edu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scnu.cn.edu.entity.Lunbo;
import scnu.cn.edu.entity.innerclass.LunboInnerClass;
import scnu.cn.edu.mapper.LunboMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@Service
public class LunboService extends ServiceImpl<LunboMapper, Lunbo> {
    @Autowired
    LunboMapper lunboMapper;

   /*轮播所需要的数据*/
    public LunboInnerClass[] getdata(Integer pageNo, Integer pageSize) {
        Page<Lunbo> page = new Page<>(pageNo,pageSize);

        lunboMapper.selectPage(page, null);
        List<Lunbo> lunboList = page.getRecords();
        System.out.println(lunboList);
        LunboInnerClass[] lunboInnerClasses = new LunboInnerClass[lunboList.size()];
        int index = 0;
        for (Lunbo lunbo : lunboList) {
            lunboInnerClasses[index]=new LunboInnerClass(lunbo.getMovieId(),lunbo.getPicture());
            index++;
        }
        return lunboInnerClasses;
    }
}
