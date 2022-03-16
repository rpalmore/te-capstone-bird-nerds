<template>
  <div id="search-results">
    <div v-show="results" id="results-found">
      <h2>Birds recently spotted in {{ this.zipcode }}</h2>
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
  </div>
</template>

<script>
import birdService from "../services/BirdService.js";

export default {
  name: "search",
  data() {
    return {
      birdsByZip: [],
      results: false,
    };
  },
  computed: {
    zipcode() {
      return this.$route.params.zipcode;
    },
  },
  created() {
    birdService.getBirdByZip(this.zipcode).then((response) => {
      if (response.data.length != 0) {
        this.birdsByZip = response.data;
        this.results = true;
      }
    });
  },
};
</script>

<style scoped>
#results-found {
  display: flex;
  flex-direction: column;
  width: 100%;
  align-items: center;
  padding-bottom: 50px;
}
#results-found > h2 {
  margin-top: 45px;
  margin-bottom: 35px;
  text-align: center;
  color: var(--rich-black);
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
  width: 100%;
}
#birds {
  display: flex;
  justify-content: space-evenly;
  flex-wrap: wrap;
}
img {
  width: 75%;
  height: auto;
}
#date {
  padding: 10px;
}
@media only screen and (max-width: 420px) {
  #search-results {
    width: 88%;
  }
  img {
    width: 80%;
  }
}
</style>