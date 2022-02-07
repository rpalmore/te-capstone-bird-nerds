<template>
  <div id="search-results">
    <div
      id="bird-card"
      v-for="bird in this.birdsByZip"
      v-bind:key="bird.birdId"
    >
    <h2>{{ bird.name }}</h2>
    <!-- <img
      v-bind:src="
        'http://covers.openlibrary.org/b/isbn/' + bird. + '-M.jpg'
      "
    /> -->
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
    birdService.getBirdByZip(this.zipcode).then( response => {
      this.birdsByZip = response.data;
    })
  }
};
</script>

<style>
</style>