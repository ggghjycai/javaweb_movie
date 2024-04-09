package scnu.cn.edu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@TableName(value = "movie")
public class ManageMovie implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    private Integer movieId;         //电影id
    private String movieName;        //电影名
    private String genre;            //基因
    private String productionPlace;  //地区
    private Date date;               //日期
    private float rating;            //评分
    private int weekHits;             //周排行
    private int monthHits;          //月排行
    private int allHits;             //全部排行
    private String picture;         //图片
    private String introduction;    //简介
    private String videoUrl;        //电影路径
    private Integer videoVip;      //是否为vip电影

    @TableField(exist = false)
    private List<String> actorList;

    @TableField(exist = false)
    private List<String> typeList;

}
