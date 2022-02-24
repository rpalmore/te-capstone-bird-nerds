<!-- For displaying one "listcard" aka the basic details of a bird -->
<template>
  <div id="bird-detail">
    <div id="birdPic">
      <iframe
        id="iframeBird"
        width="320"
        height="343"
        v-bind:src="bird.birdImg + '/embed/320'"
        frameborder="0"
        allowfullscreen
        style="width: 320px"
        scrolling="no"
      ></iframe>
    </div>
    <div id="birdInfo" v-show="showForm === false">
      <h2>{{ bird.birdName }}</h2>
      <p>
        Spotted:
        {{
          bird.numSightings > 1 || bird.numSightings === 0
            ? bird.numSightings + " times"
            : bird.numSightings + " time"
        }}
      </p>
      <p>Most recently seen on: {{ bird.mostRecentSighting }}</p>
      <p>Zip code: {{ bird.zipcode }}</p>
      <button v-on:click="showForm = true">Edit</button>
    </div>
    <form v-on:submit.prevent="updateBird" v-show="showForm === true">
      <label for="newName">Name:</label>
      <input id="newName" type="text" v-model="bird.birdName" />
      <label for="newUrl">Image URL:</label>
      <input id="newURL" type="text" v-model="bird.birdImg" />
      <label for="newZip">Zipcode:</label>
      <input id="newZip" type="text" v-model="bird.zipcode" />
      <div id="buttons">
        <input class="button" type="submit" value="Save" />
        <input
          type="button"
          value="Cancel"
          class="button"
          v-on:click.prevent="resetForm"
        />
      </div>
    </form>
  </div>
</template>

<script>
import birdService from "../services/BirdService.js";

export default {
  name: "bird-detail",
  data() {
    return {
      showForm: false,
    };
  },
  computed: {
    bird() {
      return this.$store.state.birds.find(
        (b) => b.birdID == this.$route.params.birdId
      );
    },
  },
  methods: {
    updateBird() {
      birdService
        .editBird(this.bird)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("EDIT_BIRD", this.bird);
          }
        })
        .catch((err) => {
          alert.error(err + " problem editing bird");
        });
      this.resetForm();
    },
    resetForm() {
      this.showForm = false;
    },
  },
};
</script>

<style scoped>
#bird-detail {
  background-color: #011627;
  display: flex;
  margin-top: 35px;
  align-items: center;
}
#birdPic {
  display: flex;
}
#iframeBird {
  padding-left: 5px white;
}

form,
#birdInfo {
  margin-left: 25px;
  color: #fdfffc;
  padding-left: 50px;
  padding-right: 50px;
}

.button,
button {
  min-width: 65px;
  background-color: #ff9f1c;
  font-weight: bold;
  padding: 5px;
  color: #011627;
  text-align: center;
  margin-top: 10px;
  border: 1px solid #fdfffc;
}

form {
  display: flex;
  flex-direction: column;
}
</style>