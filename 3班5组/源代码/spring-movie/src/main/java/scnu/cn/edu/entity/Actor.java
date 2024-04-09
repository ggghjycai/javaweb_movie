package scnu.cn.edu.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Actor {
    @TableId
    private Integer movieId;  //电影id
    private String mainActor;  //主演
}
