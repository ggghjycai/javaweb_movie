package scnu.cn.edu.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewMovie extends Movie implements Serializable{

    private static final long serialVersionUID = 1L;
    private List<String> actorList;


    public NewMovie(){}
    public NewMovie(Movie movie, List<String> actorList, List<String> typeList){
        super(movie);
        this.actorList = actorList;
    }

    public List<String> getActorList() {
        return actorList;
    }

    public void setActorList(List<String> actorList) {
        this.actorList = actorList;
    }


}
