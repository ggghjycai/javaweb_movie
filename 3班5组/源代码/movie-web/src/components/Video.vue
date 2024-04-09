<template>
  <video-player
      ref="videoPlayer"
      class="video-player vjs-big-play-centered"
      :src="videoSrc"
      poster=""
      crossorigin="anonymous"
      playsinline
      controls
      :volume="0.6"
      :height="320"
      :playback-rates="[0.7, 1.0, 1.5, 2.0]"
      aspectRatio="16:9"
  />
</template>

<script>
import { VideoPlayer } from '@videojs-player/vue'
import 'video.js/dist/video-js.css'

export default {
  name: 'video--player',
  title: 'Basic player (Vue)',
  components: {
    VideoPlayer
  },
  props:{
    videoSrc:{
      type:String,
      required:true
    }
  },
  data(){
  },
  setup() {
  }
}
</script>

<style scoped>
.video-player {
  background-color: black;
  width: 100%;
}
</style>