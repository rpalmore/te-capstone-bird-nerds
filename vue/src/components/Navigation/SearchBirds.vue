<template>
  <div id="search" class="nav-form-display">
    <form class="nav-form-body" @submit.prevent="search">
      <span class="helper-text">Recently spotted ...</span>
      <input
        type="text"
        required
        id="zipcode"
        placeholder="Zip code"
        v-model="zipcode"
      />
      <input type="submit" />
    </form>
  </div>
</template>

<script>
import birdService from "../../services/BirdService";

export default {
  name: "search-birds",
  data() {
    return {
      zipcode: "",
      birds: [],
    };
  },
  methods: {
    search() {
      birdService
        .getBirdByZip(this.zipcode)
        .then((response) => {
          if (response.status == 200) {
            this.birds = response.data;
            if (this.birds.length > 0) {
              this.$router.push({
                name: "search",
                params: { zipcode: this.zipcode },
              });
            } else {
              this.$router.push({
                name: "not-found",
                params: {zipcode: this.zipcode },
              })
            }
            this.$store.commit("TOGGLE_SEARCH");
            this.zipcode = "";
          }
        })
        .catch((err) => {
          alert.error(err + " problem retrieving data!");
        });
    },
  },
};
</script>

<style scoped>
/* CSS for component in LeftNav.vue */
</style>