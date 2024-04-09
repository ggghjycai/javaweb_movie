package scnu.cn.edu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import scnu.cn.edu.entity.Actor;
import scnu.cn.edu.entity.Movie;
import scnu.cn.edu.entity.NewMovie;
import scnu.cn.edu.entity.Type;
import scnu.cn.edu.entity.innerclass.HotRankMovie;
import scnu.cn.edu.entity.innerclass.MovieinnerClass;
import scnu.cn.edu.mapper.ActorMapper;
import scnu.cn.edu.mapper.MovieMapper;
import scnu.cn.edu.mapper.TypeMapper;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class MovieService extends ServiceImpl<MovieMapper, Movie> {
    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private ActorMapper actorMapper;

    @Autowired
    private RedisTemplate redisTemplate;

   //使用指定分隔符的方法将newMovie中的演员名称列表进行格式转换.
    public String parseActorList(List<String> actorlist){
        StringJoiner joiner = new StringJoiner(" ");
        actorlist.forEach(joiner::add);
        return joiner.toString();
    }

    //根据各种排序规则可以抽取出来的部分代码:MovieinnerSlass
    public MovieinnerClass[] getByrulesforMovieinnerSlass(QueryWrapper queryWrapper, Integer pageNo, Integer pageSize){
        Page<Movie> page=new Page<>(pageNo,pageSize);
        movieMapper.selectPage(page,queryWrapper);
        List<Movie> movieList = page.getRecords();
        MovieinnerClass[] movieinnerClasses =new MovieinnerClass[movieList.size()];
        int index=0;
        List<NewMovie> newMovies = getNewMovies(movieList);
        String actor="";
        for (NewMovie newMovie : newMovies) {
            actor=parseActorList(newMovie.getActorList());
            movieinnerClasses[index]=new MovieinnerClass(newMovie.getMovieId(),newMovie.getMovieName(),newMovie.getRating(),newMovie.getPicture(),newMovie.getVideoVip(),actor);
            index++;
        }
        return movieinnerClasses;
    }

    //最新影片所需要的数据
    public MovieinnerClass[] getlatestdata(Integer pageNo, Integer pageSize){
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.orderByDesc("date");
        return getByrulesforMovieinnerSlass(queryWrapper,pageNo,pageSize);
    }

  //每周热播 影片所需要的数据
    public MovieinnerClass[] getweekhotdata(Integer pageNo, Integer pageSize) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.orderByDesc("week_hits");
        return getByrulesforMovieinnerSlass(queryWrapper,pageNo,pageSize);
    }

    //vip影片所需要的资源
    public MovieinnerClass[] getvipmoviedata(Integer pageNo, Integer pageSize) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("video_vip",0);
        return getByrulesforMovieinnerSlass(queryWrapper,pageNo,pageSize);
    }

    //所有影片需要的数据

    public MovieinnerClass[] getallmoviedata(Integer pageNo, Integer pageSize) {
        return  getByrulesforMovieinnerSlass(null,pageNo,pageSize);
    }

    //根据各种排序规则可以抽取出来的部分代码:HotRankMovie
    public HotRankMovie[] getByrulesforHotRankMovie(QueryWrapper queryWrapper,Integer pageNo,Integer pageSize){
        Page<Movie> page=new Page<>(pageNo,pageSize);
        movieMapper.selectPage(page,queryWrapper);
        List<Movie> movieList = page.getRecords();
        HotRankMovie[] hotRankMovies =new HotRankMovie[movieList.size()];
        int index=0;
        for (Movie movie : movieList) {
            hotRankMovies[index]=new HotRankMovie(movie.getMovieId(),movie.getMovieName(),movie.getRating());
            index++;
        }
        return hotRankMovies;
    }
    //人气排行
    public HotRankMovie[] gethotrankdata(Integer pageNo, Integer pageSize) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.orderByDesc("all_hits");
      return getByrulesforHotRankMovie(queryWrapper,pageNo,pageSize);
    }

    //好评排行
    public HotRankMovie[] getgoodrankdata(Integer pageNo, Integer pageSize) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.orderByDesc("rating");
        return getByrulesforHotRankMovie(queryWrapper,pageNo,pageSize);
    }



    public Map<String, Object> queryPage(Integer tag, String text, Integer pageNo, Integer pageSize, String type, String area, String g, String p) {
        QueryWrapper<Movie> queryWrapper = new QueryWrapper<>();    //搜索条件
        //根据类型查找符合的电影id
        List<Integer> movie_type_id = getMovieIdsByType(type);
        queryWrapper.in("movie_id", movie_type_id);
        //根据地区查找符合的电影
        if(area != null && !area.isEmpty() && !area.equals("全部")){
            queryWrapper.eq("production_place",area);
        }
      //根据基因查找符合的电影
        if(g != null && !g.isEmpty() && !g.equals("全部")){
            queryWrapper.eq("genre",g);
        }

        if(!text.isEmpty()){    //判断是否有用搜索功能
            switch (tag){
                case 0:{
                    queryWrapper.like("movie_name", text);      //根据电影名称检索
                    break;
                }
                case 1:{
                    QueryWrapper<Actor> actorQuery = new QueryWrapper<>();
                    actorQuery.like("main_actor", text);        //根据主演检索
                    List<Actor> actorSelect = actorMapper.selectList(actorQuery);
                    List<Integer> movie_actor_id = new ArrayList<>();
                    for(Actor each: actorSelect){
                        movie_actor_id.add(each.getMovieId());
                    }
                    queryWrapper.in("movie_id", movie_actor_id);   //根据这些主演对应的电影id检索
                    break;
                }
            }
        }

        //页数查询
        int count = Math.toIntExact(movieMapper.selectCount(queryWrapper));

        //排行
        if(p != null && !p.isEmpty()){
            queryWrapper.orderByDesc(getName(p));
        }

        //搜索
        Page<Movie> page = new Page<Movie>(pageNo, pageSize);
        Page<Movie> result = movieMapper.selectPage(page, queryWrapper);
        List<Movie> movies = result.getRecords();

        //movie和actor封装
        List<NewMovie> newMovies = getNewMovies(movies);

        Map<String, Object> map = new HashMap<>();
        map.put("count", count);
        map.put("movies", newMovies);
        return map;
    }




    /**
     * @说明 通过type查询type相关的movie的movieId
     * @param type  类型
     * @return  一个包含movieId的list
     */
    public List<Integer> getMovieIdsByType(String type){
        QueryWrapper<Type> typeQueryWrapper = new QueryWrapper<>();
        if(type != null && !type.isEmpty()){
            if(type.equals("全部")){
                typeQueryWrapper.select("distinct movie_id");
            }
            else {
                typeQueryWrapper.like("type",type);
            }
        }
        //类型判断
        List<Type> movieTypes = typeMapper.selectList(typeQueryWrapper);
        List<Integer> movie_type_id = new ArrayList<>();
        for(Type each: movieTypes){
            movie_type_id.add(each.getMovieId());
        }
        return movie_type_id;
    }

    /**
     * @说明 根据每个movies的movieId查询actor，并统一封装为newMovies
     * @param movies    一个含有movie的list
     * @return  一个封装好的newMovieList
     */
    private List<NewMovie> getNewMovies(List<Movie> movies) {
        QueryWrapper<Actor> actorQueryWrapper = new QueryWrapper<>();
        List<Actor> actorsList = actorMapper.selectList(actorQueryWrapper);
        Map<Integer, List<String>> actorMap = new HashMap<>();
        //将movieId——mainActor存入map，将所有actor和movieId连接起来
        for(Actor each: actorsList){
            Integer movieId = each.getMovieId();
            if (actorMap.containsKey(movieId)){
                actorMap.get(movieId).add(each.getMainActor());
//                actorMap.put(movieId, actorMap.get(movieId) + " " + each.getMainActor());
            }
            else {
                List<String> newList = new ArrayList<>();
                newList.add(each.getMainActor());
                actorMap.put(movieId, newList);
            }
        }
        List<NewMovie> newMovieList = new ArrayList<>();
        for(Movie m: movies){   //如果该电影有主演，则存入actorStringList为主演，没有，则为空；为了actor和movie顺序一样
            NewMovie newMovie = new NewMovie(m, actorMap.getOrDefault(m.getMovieId(), new ArrayList<>()), new ArrayList<>());
            newMovieList.add(newMovie);
        }
        return newMovieList;
    }

    /**
     * @说明 获取所有的area
     * @return 一个包含所有area的list
     */
    public List<String> getAreas() {
        QueryWrapper<Movie> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("distinct production_place");
        List<Movie> movieAreas = movieMapper.selectList(queryWrapper);
        List<String> areas = new ArrayList<>();
        for(Movie each: movieAreas){
            areas.add(each.getProductionPlace());
        }
        return areas;
    }

    /**
     * @说明 获取所有的genre
     * @return 一个包含所有genre的list
     */
    public List<String> getGenre() {
        QueryWrapper<Movie> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("distinct genre");
        List<Movie> movieAreas = movieMapper.selectList(queryWrapper);
        List<String> genres = new ArrayList<>();
        for(Movie each: movieAreas){
            genres.add(each.getGenre());
        }
        return genres;
    }

    public Map<String, Object> queryRank(String type, String area, String rank, Integer pageNo, Integer pageSize) {
        QueryWrapper<Movie> queryWrapper = new QueryWrapper<>();
        //type
        List<Integer> movie_type_id = getMovieIdsByType(type);
        queryWrapper.in("movie_id", movie_type_id);
        //area
        if(area != null && !area.isEmpty() && !area.equals("全部")){
            queryWrapper.eq("production_place",area);
        }

        //查询页数
        int count = Math.toIntExact(movieMapper.selectCount(queryWrapper));

        //rank
        queryWrapper.orderByDesc(getName(rank));

        //搜索
        Page<Movie> page = new Page<Movie>(pageNo, pageSize);
        Page<Movie> result = movieMapper.selectPage(page, queryWrapper);
        List<Movie> movies = result.getRecords();

        //封装为NewMovie
        List<NewMovie> newMovies =  getNewMovies(movies);

        Map<String, Object> map = new HashMap<>();
        map.put("count", count);
        map.put("movies", newMovies);

        return map;
    }

    public String getName(String str){
        switch (str){
            case "2": return "month_hits";
            case "3": return "week_hits";
            case "最好":
            case "4": return "rating";
            case "最新":return "date";
            case "1":
            case "最热":
            default:return "all_hits";
        }
    }

    public NewMovie findMovieById(Integer movieId) {
        // 先从Redis缓存中查询数据
        Object object = redisTemplate.opsForValue().get("movie_" + movieId);
        if(object != null){
            return (NewMovie) object;
        }

        // 如果缓存中没有数据，则去数据库中读取
        Movie movie = movieMapper.selectById(movieId);

        QueryWrapper<Type> typeQueryWrapper = new QueryWrapper<>();
        typeQueryWrapper.eq("movie_id", movieId);
        List<Type> types = typeMapper.selectList(typeQueryWrapper);

        QueryWrapper<Actor> actorQueryWrapper = new QueryWrapper<>();
        actorQueryWrapper.eq("movie_id", movieId);
        List<Actor> actors = actorMapper.selectList(actorQueryWrapper);
        List<String> list1 = new ArrayList<>();
        for(Type each: types){
            list1.add(each.getType());
        }
        List<String> list2 = new ArrayList<>();
        for(Actor each: actors){
            list2.add(each.getMainActor());
        }

        NewMovie newMovie = new NewMovie(movie, list2, list1);

        // 并且将查询结果进行保存，设置有效期为1天
        redisTemplate.opsForValue().set("movie_" + movieId, newMovie, 1, TimeUnit.DAYS);
        return newMovie;
    }



}
