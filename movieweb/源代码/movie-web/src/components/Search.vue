<template>
  <MyNav @changeTag="changeTag" @changeText="changeText" :old-text="text" :old-tag="tag" ></MyNav>
  <link type = "text/css" rel = "stylesheet" href = "/static/css/search.css" >
  <div id="top"></div>
  <div class = "box">
    <div class = "container">
      <div id = "content">
        <!--分类栏目-->
        <section id = "mod_index" class = "mod search-index mt-4">
          <div class="container-fluid">
            <div class="row row-cols-1 row-cols-sm-2 row-cols-md-4 row-cols-lg-12 type-div">
              <span class="col-sm-12 col-md-12 col-lg-1 search-index-L">
                类型
                <b class="mao-hao">：</b>
                <button class="btn btn-primary d-flex align-items-center d-md-none" id="btnClpType" type="button" data-bs-toggle="collapse" data-bs-target="#typeCollapse" aria-expanded="false">
                  <i class="bi bi-caret-down-fill text-black"></i>
                </button>
              </span>
              <div class="col-sm-12 col-md-12 col-lg-11 d-md-block search-index-R collapse" id="typeCollapse">
                <div class="row justify-content-start">
                  <div class="col-6 col-sm-3 col-md-2 col-lg-1">
                    <div class="text-center type-a">
                      <a href="javascript:void(0);" class="text-decoration-none" :class="{'type-a-new': selectedType === '全部' }" @click="changeType" >全部</a>
                    </div>
                  </div>
                  <div class="col-6 col-sm-3 col-md-2 col-lg-1" v-for="(item, index) in typeList" :key="index">
                    <div class="text-center type-a">
                      <a href="javascript:void(0);" class="text-decoration-none" :class="{'type-a-new': selectedType === item }" @click="changeType" >{{ item }}</a>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="row row-cols-1 row-cols-sm-2 row-cols-md-4 row-cols-lg-12 type-div">
              <span class="col-sm-12 col-md-12 col-lg-1 search-index-L">
                地区
                <b class="mao-hao">：</b>
                <button class="btn btn-primary d-flex align-items-center d-md-none" id="btnClpArea" type="button" data-bs-toggle="collapse" data-bs-target="#areaCollapse" aria-expanded="false" aria-controls="collapseExample">
                  <i class="bi bi-caret-down-fill text-black"></i>
                </button>
              </span>
              <div class="col-sm-12 col-md-12 col-lg-11 collapse d-md-block search-index-R" id="areaCollapse">
                <div class="row justify-content-start">
                  <div class="col-6 col-sm-3 col-md-2 col-lg-1" >
                    <div class="text-center type-a">
                      <a href="javascript:void(0);" class="text-decoration-none" :class="{'area-a-new': selectedArea === '全部' }" @click="changeArea" >全部</a>
                    </div>
                  </div>
                  <div class="col-6 col-sm-3 col-md-2 col-lg-1" v-for="(item, index) in areaList" :key="index" >
                    <div class="text-center type-a">
                      <a href="javascript:void(0);" class="text-decoration-none" :class="{'area-a-new': selectedArea === item }" @click="changeArea" >{{ item }}</a>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="row row-cols-1 row-cols-sm-2 row-cols-md-4 row-cols-lg-12 type-div">
              <span class="col-sm-12 col-md-12 col-lg-1 search-index-L">
                基因
                <b class="mao-hao">：</b>
                <button class="btn btn-primary d-flex align-items-center d-md-none" id="btnClpGenre" type="button" data-bs-toggle="collapse" data-bs-target="#genreCollapse" aria-expanded="false" aria-controls="collapseExample">
                  <i class="bi bi-caret-down-fill text-black"></i>
                </button>
              </span>
              <div class="col-sm-12 col-md-12 col-lg-11 collapse d-md-block search-index-R" id="genreCollapse">
                <div class="row justify-content-start">
                  <div class="col-6 col-sm-3 col-md-2 col-lg-1" >
                    <div class="text-center type-a">
                      <a href="javascript:void(0);" class="text-decoration-none" :class="{'g-a-new': selectedGenre === '全部' }" @click="changeGenre" >全部</a>
                    </div>
                  </div>
                  <div class="col-6 col-sm-3 col-md-2 col-lg-1" v-for="(item, index) in genreList" :key="index" >
                    <div class="text-center type-a">
                      <a href="javascript:void(0);" class="text-decoration-none" :class="{'g-a-new': selectedGenre === item }" @click="changeGenre" >{{ item }}</a>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="row row-cols-1 row-cols-sm-2 row-cols-md-4 row-cols-lg-12 type-div">
              <span class="col-sm-12 col-md-12 col-lg-1 search-index-L">
                排行
                <b class="mao-hao">：</b>
                <button class="btn btn-primary d-flex align-items-center d-md-none" id="btnClpRank" type="button" data-bs-toggle="collapse" data-bs-target="#rankCollapse" aria-expanded="false" aria-controls="collapseExample">
                  <i class="bi bi-caret-down-fill text-black"></i>
                </button>
              </span>
              <div class="col-sm-12 col-md-12 col-lg-11 collapse d-md-block search-index-R" id="rankCollapse">
                <div class="row justify-content-start">
                  <div class="col-6 col-sm-3 col-md-2 col-lg-1" >
                    <div class="text-center type-a">
                      <a href="javascript:void(0);" class="text-decoration-none" :class="{'p-a-new': selectedRank === '最热' }" @click="changeRank" >最热</a>
                    </div>
                  </div>
                  <div class="col-6 col-sm-3 col-md-2 col-lg-1" >
                    <div class="text-center type-a">
                      <a href="javascript:void(0);" class="text-decoration-none" :class="{'p-a-new': selectedRank === '最新' }" @click="changeRank" >最新</a>
                    </div>
                  </div>
                  <div class="col-6 col-sm-3 col-md-2 col-lg-1" >
                    <div class="text-center type-a">
                      <a href="javascript:void(0);" class="text-decoration-none" :class="{'p-a-new': selectedRank === '最好' }" @click="changeRank" >最好</a>
                    </div>
                  </div>
                </div>
              </div>

            </div>
          </div>
        </section>
        <!--分类栏目结束-->
        <!--分页-->
        <Page @getPageNo="getPageNo" :page-no="pageNo" :page-total="pageTotal" :visible-pages="visiblePages"></Page>
        <!--分页结束-->
        <!--搜索list-->
        <section class="mod row-search search-list">
          <div class="container-fluid">
            <div class="row w-100">
              <div class="list-box col-4 col-sm-4 col-md-3 col-lg-2" v-for="(item, index) in searchList" :key="index" >
                <a class="list-a" @click="toDetail(item.movieId)">
                  <img :src="`/static/img/movie_picture/${item.picture}`" class="bigImg">
                  <img v-if="item.video_vip === 1" src="/static/img/icon/VIP-rightTop.png" class="vipImg">
                  <h3>{{ item.movieName }}</h3>
                  <i>{{ item.rating }}</i>
                  <p class="p-none" >{{ item.introduction }}</p>
                </a>
              </div>
            </div>
          </div>
        </section>
        <!--搜索list结束-->
        <!--分页底部-->
        <Page @getPageNo="getPageNo" :page-no="pageNo" :page-total="pageTotal" :visible-pages="visiblePages"></Page>
        <!--分页底部结束-->
      </div>

    </div>

  </div>


</template>

<script>
import axios from "axios";
import Page from "@/components/Page.vue";
import router from "@/router/router.js";
import MyNav from "@/components/MyNav.vue";
export default {
  name: "Rank",
  components: {MyNav, Page},
  data(){
    return{
      typeList:[],
      areaList:[],
      genreList:[],
      searchList:[],
      selectedArea:"全部",
      selectedType:"全部",
      selectedGenre:"全部",
      selectedRank:"最热",
      tag:0,
      text:"",
      pageNo:1,
      pageSize:24,
      pageTotal:0
    }
  },
  created() {
    {
      if(this.$route.query.tag !== undefined){
        this.tag = this.$route.query.tag;
      }
      if(this.$route.query.text !== undefined){
        this.text = this.$route.query.text;
      }
      if (this.$route.query.area !== undefined){
        this.selectedArea = this.$route.query.area
      }
      if (this.$route.query.type !== undefined){
        this.selectedType = this.$route.query.type
      }
      if (this.$route.query.p !== undefined){
        this.selectedRank = this.$route.query.p
      }
      if (this.$route.query.g !== undefined){
        this.selectedGenre = this.$route.query.g
      }
      if (this.$route.query.pageNo !== undefined){
        this.pageNo = this.$route.query.pageNo
      }
      if (this.$route.query.pageSize !== undefined){
        this.pageSize = this.$route.query.pageSize
      }
    }
  },
  mounted() {
    axios
        .get("http://127.0.0.1:8090/index/type")
        .then(res=>{
          this.typeList = res.data
        })
    axios
        .get("http://127.0.0.1:8090/index/area")
        .then(res=>{
          this.areaList = res.data
        })
    axios
        .get("http://127.0.0.1:8090/index/genre")
        .then(res=>{
          this.genreList = res.data
        })

    axios
        .get("http://127.0.0.1:8090/index/search",{
          params:{
            tag:this.tag,
            text:this.text,
            pageNo:this.pageNo,
            pageSize:this.pageSize,
            type:this.selectedType,
            area:this.selectedArea,
            p:this.selectedRank,
            g:this.selectedGenre
          }
        })
        .then(res=>{
          this.searchList = res.data.data
          this.pageTotal = res.data.pageTotal
        })

  },
  methods:{
    changeTag(res){
      this.tag = res;
    },
    changeText(res){
      this.text = res;
    },
    changeArea(event){
      this.selectedArea = event.target.innerText;
      this.getData();
    },
    changeType(event){
      this.selectedType = event.target.innerText;
      this.getData();
    },
    changeGenre(event){
      this.selectedGenre= event.target.innerText;
      this.getData();
    },
    changeRank(event){
      this.selectedRank = event.target.innerText;
      this.getData();
    },
    toDetail(movieId){
      router.push({
        path: "/detail",
        query:{
          movieId:movieId
        }
      })
    },
    getData(pageNo = 1, pageSize = 24){
      router.push({
        path: "/search",
        query:{
          tag:this.tag,
          text:this.text,
          pageNo:pageNo,
          pageSize:pageSize,
          type:this.selectedType,
          area:this.selectedArea,
          g:this.selectedGenre,
          p:this.selectedRank
        }
      })
    },
    getPageNo(res){
      this.getData(res, 24);
    },
  },
  computed:{
    key() {
      return this.$route.fullPath
    },
    visiblePages: function() {
      const pages = [];
      // 如果总页数小于等于6，则直接显示所有页数
      if (this.pageTotal <= 6) {
        for (let i = 1; i <= this.pageTotal; i++) {
          pages.push(i);
        }
      } else {
        // 否则，显示前三个和后三个页数，中间显示省略号
        for (let i = this.pageNo; i <= 3; i++) {
          pages.push(i);
        }
        pages.push('...');
        for (let i = this.pageTotal - 2; i <= this.pageTotal; i++) {
          pages.push(i);
        }
      }
      return pages;
    }
  }
}
</script>

<style scoped>

</style>