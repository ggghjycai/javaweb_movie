package scnu.cn.edu.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Lunbo {
    @TableId(value = "lunbo_id")
    private int lunboId;
    private int movieId;
    private String picture;

}
