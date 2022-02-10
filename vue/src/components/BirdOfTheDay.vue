<template>
  <div id="dailyBird">
    <iframe
        width="800"
        height="578"
        v-bind:src="imgUrl + '/embed/800'"
        frameborder="0"
        allowfullscreen
        style="width: 800px"
      ></iframe>
  </div>
</template>

<script>
import birdService from '../services/BirdService';
export default {
  name: "bird-of-the-day",
  data() {
    return {
      imgUrl: "",
    };
  },
  created() {
    birdService.getRandomBird().then((response) => {
      if (response.status == 200) {
        this.imgUrl = response.data.imgUrl;
      }
    }).catch((err) => {
      console.error(err + " problem generating a random bird!");
    });
  },
};
</script>

<style scoped>
iframe {
  margin-top: 50px;
  padding: 8px 8px 0 8px;
  background-color: white;
  border: 3px solid #011627;
  border: 3px solid #FF9F1C;
  margin-bottom: 50px;
}
</style>