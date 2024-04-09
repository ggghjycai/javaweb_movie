import { createApp } from 'vue'
// import VueRouter from 'vue-router'
import App from './App.vue'
import router from '@/router/router.js'

import VideoPlayer from "vue-video-player";
import 'vue3-video-play/dist/style.css' // 引入css
// 引入bootstrap5
// import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-icons/font/bootstrap-icons.css'
// import 'bootstrap/dist/js/bootstrap.min.js'
import '@/js/bootstrap.bundle.min.js'
// import 'bootstrap/dist/js/bootstrap.bundle.js'

const app = createApp(App);
app.use(VideoPlayer)
app.use(router);
app.mount('#app')
