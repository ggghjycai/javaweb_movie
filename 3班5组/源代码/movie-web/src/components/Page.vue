<template>
  <!--分页-->
  <div class="container-fluid" id="page">
    <div class="row">
      <div class="col d-flex justify-content-center">
        <nav aria-label="Page navigation">
          <ul class="pagination">
            <li class="page-item">
              <a class="page-link" href="#" aria-label="First" @click.prevent="goto(1)" >&laquo;&laquo;</a>
            </li>
            <li class="page-item">
              <a class="page-link" href="#" aria-label="Previous" @click.prevent="goto(parseInt(pageNo)-1)" >&laquo;</a>
            </li>
            <li class="page-item" v-for="index in visiblePages" :key="index">
              <a class="page-link" href="#" @click.prevent="goto(index)" >{{ index === '...' ? '...' : index }}</a>
            </li>
            <li class="page-item">
              <a class="page-link" href="#" aria-label="Next" @click.prevent="goto(parseInt(pageNo)+1)" >&raquo;</a>
            </li>
            <li class="page-item">
              <a class="page-link" href="#" aria-label="Last" @click.prevent="goto(pageTotal)" >&raquo;&raquo;</a>
            </li>
          </ul>
        </nav>
      </div>
    </div>
  </div>
  <!--分页结束-->
</template>

<script>
export default {
  props:{
    visiblePages:{
      required: true
    },
    pageNo:{
      required: true
    },
    pageTotal:{
      required: true
    }
  },
  data(){

  },
  methods:{
    goto(i){
      console.log(i)
      if(i > 0 && i <= this.pageTotal){
        this.$emit("getPageNo", i);
      }
    }
  }
}
</script>

<style scoped>

</style>