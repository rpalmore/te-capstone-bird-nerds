<template>
  <div id="header">
     <h2>Birds recently spotted in {{ this.$route.params.zipcode }} </h2>
    <div id="search-results">
    <div
      id="bird-card"
      v-for="bird in this.birdsByZip"
      v-bind:key="bird.birdId"
    >
      <!-- This first one should work but it's not -->
      <!-- <img id="bird-img" v-bind:src="bird.imgUrl"/> -->
      <!-- So tried these others ...  -->
      <!-- <img id="bird-img" v-bind:src="bird.imgUrl + '.jpg'"/> -->
      <!-- <img id="bird-img" v-bind:src="bird.imgUrl + '/embed/640'"/> -->
      <iframe
        width="320"
        height="342"
        v-bind:src="bird.imgUrl + '/embed/400'"
        frameborder="0"
        allowfullscreen
        style="width: 320px"
      ></iframe>
      <!-- <h2>{{ bird.name }}</h2> -->
      <div id="date">Date: {{ bird.dateSpotted }}</div>
    </div>
    </div>
  </div>
</template>

<script>
import birdService from "../services/BirdService.js";

export default {
  name: "search",
  data() {
    return {
      zipcode: this.$route.params.zipcode,
      birdsByZip: [],
    };
  },
  created() {
    birdService.getBirdByZip(this.zipcode).then((response) => {
      this.birdsByZip = response.data;
    });
  },
};
</script>

<style scoped>
#header {
  display: flex;
  flex-direction: column;
  width: 100%;
  align-items: center;
  border-bottom: 2px solid #fdfffc;
  padding-bottom: 18px;
}
#search-results {
  display: flex;
  flex-direction: column;
  width: 80%;
  align-items: center;
  background-color: #011627;
  color: #fdfffc;
  border-top: 1px solid #e71d36;
  border-right: 3px solid #e71d36;
  border-left: 3px solid #e71d36;
  border-top: 2px solid #e71d36;
}
#bird-card {
  display: flex;
  flex-direction: column;
  align-items: center;
  border-bottom: 3px solid #ff9f1c;
  margin-bottom: 15px;
  padding-top: 20px;
}
#date {
  padding: 10px;
}
</style>