<template>
  <div id="dailyBird">
    <!-- <img src="..\assets\eBirdHomePage.webp" alt="" class="responsive" /> -->
    <!-- src below will be replaced with: 
         v-bind:src="bird.imgUrl + '/embed/800'"
         -->
    <iframe
        width="800"
        height="578"
        src="https://macaulaylibrary.org/asset/408852991/embed/800"
        frameborder="0"
        allowfullscreen
        style="width: 800px"
      ></iframe>
  </div>
</template>

<script>
//import birdService from '../services/BirdService';
export default {
  name: "bird-of-the-day",
  // created() {
  //   //birdService.getRandomBird()???
  // }
};
</script>

<style scoped>
iframe {
  margin-top: 50px;
  padding: 8px 8px 0 8px;
  background-color: white;
  border: 3px solid #011627;
  border: 3px solid #FF9F1C;
}
/* .responsive {
    width: 100%;
    height: auto;
    border-bottom: 1px solid #011627;
} */
</style>