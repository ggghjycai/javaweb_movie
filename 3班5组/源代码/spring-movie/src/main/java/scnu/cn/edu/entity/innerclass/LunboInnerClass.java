package scnu.cn.edu.entity.innerclass;

import lombok.Data;

@Data
public class LunboInnerClass {
    private int movieId;
    private String picture;
 public LunboInnerClass(){}
    public LunboInnerClass(int movieId, String picture) {
        this.movieId = movieId;
        this.picture = picture;
    }
  public void setAll(int movieId, String picture){
      this.movieId = movieId;
      this.picture = picture;
  }
}
