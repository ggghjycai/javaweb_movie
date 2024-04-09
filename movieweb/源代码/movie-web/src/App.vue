<script setup>

</script>

<template>
<!--  <MyNav></MyNav>-->
<!--  <router-view>-->
<!--    <Index></Index>-->
  <router-view :key="$route.fullPath"></router-view>
</template>

