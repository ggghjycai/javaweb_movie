<template>
  <MyNav></MyNav>
  <link type="text/css" rel="stylesheet" href="/static/css/index.css">
  <div class="container  index-box">
    <Carousel></Carousel>
    <MerryGoRound></MerryGoRound>
    <div class="row mt-4">
      <MovieList title="每日热播" request="weekHot"></MovieList>
      <Rank title="人气排行" request="goodRank"></Rank>
    </div>
    <div class="row mt-4">
      <MovieList title="VIP影院" request="vipMovie"></MovieList>
      <Rank title="好评排行" request="goodRank"></Rank>
    </div>
    <AllMovieList></AllMovieList>
  </div>
</template>

<script setup>
import MyNav from "@/components/MyNav.vue";
import Carousel from "@/components/Carousel .vue";
import MerryGoRound from "@/components/MerryGoRound.vue";
import MovieList from "@/components/MovieList.vue";
import Rank from "@/components/RankList.vue";
import AllMovieList from "@/components/AllMovieList.vue";
</script>


<style scoped>

</style>