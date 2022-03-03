<template>
  <div id="header">
    <h2>Birds recently spotted in {{ this.$route.params.zipcode }}</h2>
    <div id="search-results">
      <div id="birds">
        <div
          id="bird-card"
          v-for="bird in this.birdsByZip"
          v-bind:key="bird.birdId"
          v-show="bird.imgUrl != 'No photo'"
        >
          <img v-bind:src="bird.imgUrl" />
          <div id="date">{{ bird.name }}: {{ bird.dateSpotted }}</div>
        </div>
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
  padding-bottom: 18px;
}
#header > h2 {
  margin-top: 45px;
  margin-bottom: 35px;
  text-align: center;
}
#search-results {
  display: flex;
  flex-direction: column;
  width: 80%;
  align-items: center;
  color: var(--baby-powder);
}
#bird-card {
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: var(--rich-black);
  margin-bottom: 5px;
  padding-top: 20px;
}
#birds {
  display: flex;
  justify-content: space-evenly;
  flex-wrap: wrap;
}
img {
  width: 40%;
  height: auto;
}
#date {
  padding: 10px;
}
</style>