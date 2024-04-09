package scnu.cn.edu.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Type {
    @TableId
    private Integer movieId;
    private String type;
}
