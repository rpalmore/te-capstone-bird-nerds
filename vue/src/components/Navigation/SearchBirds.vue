<template>
  <div id="search">
    <form class="form-search" @submit.prevent="search">
      <span>Recently spotted ...</span>
      <input type="text" required id="zipcode" placeholder="Zip code" v-model="zipcode" />
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
          if (response.status == 200 && response.data.length != 0) {
            this.birds = response.data;
            this.$router.push({ name: 'search', params: { zipcode: this.zipcode } });
            this.$router.go(0);
          }
          else {
            this.$router.push({ name: 'not-found', params: { zipcode: this.zipcode } });
          }
        })
        .catch((err) => {
          console.error(err + " problem retrieving data!");
        });
    },
  },
};
</script>

<style scoped>
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
#search {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 80%;
}
.form-search {
  display: flex;
  flex-direction: column;
  width: 100%;
  align-items: center;
  padding: 18px 0 35px 0;
  background-color: #ff9f1c;
  border: 1px solid #e71d36;
}
input[type] {
  text-align: center;
  border: 2px solid #e71d36;
}
button,
input[type] {
  width: 80%;
  background-color: #011627;
  color: #fdfffc;
  border: 2px solid #e71d36;
  font-family: "Bitter", serif;
  font-size: 1.3rem;
  margin-top: 35px;
  padding: 10px;
}
input[type="text"] {
  width: 70%;
}
span {
  color: #011627;
  font-family: "Bitter", serif;
  font-size: 1.3rem;
  font-weight: bold;
  border-bottom: 2px solid #fdfffc;
  padding-bottom: 18px;
  text-align: center;
}
</style>