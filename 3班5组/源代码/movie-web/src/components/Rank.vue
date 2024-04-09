<template>
  <MyNav></MyNav>
  <link type = "text/css" rel = "stylesheet" href = "/static/css/rank.css" >
  <div class="box-10px"></div>
  <div class="box">
    <div class="container">
      <div class="row rank-box">
        <div class="col-12 col-lg-2 rank-left-box">
          <ul class="boxbg">
            <!-- 地区 -->
            <li class="line-bott text-center">
              <h3 class="area rank-h3" id="area">地区</h3>
              <div class="container-fluid">
                <div class="row">
                  <div class="col-6 text-center"><a href="javascript:void(0);" :class="{nowBA: selectedArea === '全部' }" @click="changeArea"  >全部</a></div>
                  <div class="col-6 text-center" v-for="(item, index) in areaList" :key="index" >
                    <a href="javascript:void(0);" :class="{nowBA: selectedArea === item }" @click="changeArea" >{{ item }}</a>
                  </div>
                </div>
              </div>
            </li>
            <!-- 类型 -->
            <li class="line-top text-center">
              <h3 class="type rank-h3">类型</h3>
              <div class="container-fluid">
                <div class="row">
                  <div class="col-6 text-center"><a href="javascript:void(0);" :class="{nowBA: selectedType === '全部' }" @click="changeType" >全部</a></div>
                  <div class="col-6 text-center" v-for="(item, index) in typeList" :key="index" >
                    <a href="javascript:void(0);" :class="{nowBA: selectedType === item }" @click="changeType" >{{ item }}</a>
                  </div>
                </div>
              </div>
            </li>
          </ul>
        </div>
        <div class="col-12 col-lg-10 rank-right-box">
          <div class="right-box-content">
            <div class="col-12 d-lg-none top-box" ref="topBox">
              <button type="button" class="btn btn-custom btn-total" data-bs-toggle="modal" data-bs-target="#filterModal">
                筛选
                <i class="bi bi-caret-down-fill"></i>
              </button>
              <div class="modal fade" id="filterModal" tabindex="-1" aria-labelledby="filterModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                  <div class="modal-content">
                    <div class="modal-header">
                      <h5 class="modal-title" id="filterModalLabel">筛选</h5>
                      <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                      <!-- 地区 -->
                      <div class="mb-3">
                        <label for="regionSelect" class="form-label">地区：</label>
                        <select class="form-select" id="regionSelect" ref="area">
                          <option value="全部">全部</option>
                          <option v-for="(item, index) in areaList" :key="index" :value="item">{{ item }}</option>
                        </select>
                      </div>

                      <div class="mb-3">
                        <label for="typeSelect" class="form-label">类型：</label>
                        <select class="form-select" id="typeSelect" ref="type">
                          <option value="全部">全部</option>
                          <option v-for="(item, index) in typeList" :key="index" :value="item">{{ item }}</option>
                        </select>
                      </div>
                      <div class="mb-3">
                        <label for="rankSelect" class="form-label">排行：</label>
                        <select class="form-select" id="rankSelect" ref="rank">
                          <option value="总">总榜</option>
                          <option value="月">月榜</option>
                          <option value="周">周榜</option>
                          <option value="好评">好评</option>
                        </select>
                      </div>
                    </div>
                    <div class="modal-footer">
                      <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">关闭</button>
                      <button type="button" class="btn btn-primary" @click="filter()">应用</button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <h5 class="duFONT">
              <div class="duFONT-nav text-center align-middle">
                <a href="javascript:void(0);" :class="{'m-1':true, nowBA: selectedRank === '总' }" @click="changeRank">总</a>
                <a href="javascript:void(0);" :class="{'m-1':true, nowBA: selectedRank === '月' }" @click="changeRank" >月</a>
                <a href="javascript:void(0);" :class="{'m-1':true, nowBA: selectedRank === '周' }" @click="changeRank"  >周</a>
                <a href="javascript:void(0);" :class="{'m-1':true, nowBA: selectedRank === '好评' }" @click="changeRank"  >好评</a>
              </div>
            </h5>
            <div class="table-responsive">
              <table class="table table-bordered table-hover text-center">
                <thead class="title table-light">
                <tr>
                  <th scope="col" class="col-1">排行</th>
                  <th scope="col" class="col-5">电影名</th>
                  <th scope="col" class="col-4">主演</th>
                  <th scope="col" class="col-2">评分</th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="(item, index) in rankList" :key="index">
                  <td class="li01 ta_c"><b class="ptnob">{{ (pageNo-1) * pageSize + index+1 }}</b></td>
                  <td class="li02 ta_c oh">
                    <a @click="toDetail(item.movieId)" style="cursor: pointer" >{{ item.movieName }}</a>
                  </td>
                  <td class="li03 ta_c oh">
                    <span>
                      <a @click="search(1, each)" v-for="(each, i) in item.actorList" :key="i" style="cursor: pointer" >{{ each + ' ' }}</a>
                    </span>
                  </td>
                  <td class="li04 ta_c">
                    <span>{{ item.rating }}</span>
                  </td>
                </tr>
                </tbody>
              </table>
              <Page @getPageNo="getPageNo" :page-no="pageNo" :page-total="pageTotal" :visible-pages="visiblePages"></Page>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

</template>

<script>
import axios from "axios";
import MyNav from "@/components/MyNav.vue";
import Page from "@/components/Page.vue";
import router from "@/router/router.js";
export default {
  name: "Rank",
  components: {MyNav, Page},
  data(){
    return{
      typeList:[],
      areaList:[],
      rankList:[],
      selectedArea:"全部",
      selectedType:"全部",
      selectedRank:"总",
      pageNo:1,
      pageSize:24,
      pageTotal:0
    }
  },
  mounted() {
    {
      if (this.$route.query.area !== undefined){
        this.selectedArea = this.$route.query.area
      }
      if (this.$route.query.type !== undefined){
        this.selectedType = this.$route.query.type
      }
      if (this.$route.query.p !== undefined){
        this.selectedRank = this.$route.query.p
      }
      if (this.$route.query.pageNo !== undefined){
        this.pageNo = this.$route.query.pageNo
      }
      if (this.$route.query.pageSize !== undefined){
        this.pageSize = this.$route.query.pageSize
      }
    }
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
        .get("http://127.0.0.1:8090/index/rank",{
          params:{
            pageNo:this.pageNo,
            pageSize:this.pageSize,
            type:this.selectedType,
            area:this.selectedArea,
            p:this.selectedRank
          }
        })
        .then(res=>{
          this.rankList = res.data.data
          this.pageTotal = res.data.pageTotal
        })
  },
  methods:{
    getPageNo(res){
        this.getData(res, 24);
    },
    getData(pageNo = 1, pageSize = 24){
      router.push({
        path: "/rank",
        query:{
          pageNo:pageNo,
          pageSize:pageSize,
          type:this.selectedType,
          area:this.selectedArea,
          p:this.selectedRank
        }
      })
    },
    toDetail(movieId){
      router.push({
        path: "/detail",
        query:{
          movieId:movieId
        }
      })
    },
    search(
        tag = 0,
        text = "",
        type = "全部",
        area = "全部",
        g = "全部",
        p = "最热"
    ){
      const pageNo = 1;
      const pageSize = 24;
      router.push({
        path: "/search",
        query:{
          tag:tag,
          text:text,
          pageNo:pageNo,
          pageSize:pageSize,
          type:type,
          area:area,
          g:g,
          p:p
        }
      })
    },
    changeArea(event){
      this.selectedArea = event.target.innerText;
      this.getData();
    },
    changeType(event){
      this.selectedType = event.target.innerText;
      this.getData();
    },
    changeRank(event){
      this.selectedRank = event.target.innerText;
      this.getData();
    },
    filter(){
      this.selectedArea = this.$refs.area.value
      this.selectedType = this.$refs.type.value
      this.selectedRank = this.$refs.rank.value
      console.log(this.selectedRank)
      // this.$refs.topBox.hidden;
      this.getData();
    }
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