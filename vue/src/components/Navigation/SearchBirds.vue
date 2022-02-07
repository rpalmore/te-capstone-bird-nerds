<template>
  <div id="search">
    <form id="searchByZip" @submit.prevent="search">
      <input type="text" required id="zipcode" v-model="zipcode" />
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
        .getBirdByZip(this.zipcode).then((response) => {
          if (response.status == 200) {
            this.birds = response.data[0];
            console.log(response);
            console.log("********************");
            console.log(this.birds);
            this.$router.push({ name: 'search', params: { zipcode: this.zipcode } });
          }
        })
        .catch((err) => {
          console.error(err + " problem retrieving data!");
        });
    },
  },
};
</script>

<style>
#btn-search {
  margin-bottom: 35px;
  width: 80%;
  background-color: #011627;
  color: #fdfffc;
  border: 2px solid #ff9f1c;
  border: 2px solid #e71d36;
  font-family: "Bitter", serif;
  font-size: 1.3rem;
  margin-top: 35px;
  padding: 10px;
}
</style>