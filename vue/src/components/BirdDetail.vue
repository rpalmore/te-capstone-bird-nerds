<template>
  <div id="bird-detail">
    <div id="bird-pic">
      <img v-bind:src="bird.birdImg" alt="" id="bird-img" />
    </div>
    <div id="bird-info" v-show="showForm === false">
      <h2>{{ bird.birdName }}</h2>
      <p>
        Spotted:
        {{
          bird.numSightings > 1 || bird.numSightings === 0
            ? bird.numSightings + " times"
            : bird.numSightings + " time"
        }}
      </p>
      <p>Last spotted: {{ bird.mostRecentSighting }}</p>
      <p>Zip code: {{ bird.zipcode }}</p>
      <button v-on:click="showForm = true">Edit</button>
    </div>
    <form
      id="log-sighting"
      v-on:submit.prevent="updateBird"
      v-show="showForm === true"
    >
      <label for="newName">Name:</label>
      <input id="newName" type="text" v-model="bird.birdName" />
      <label for="birdImgURL"
        ><a id="add-bird" v-on:click="useCloudinary()"
          >Upload a picture</a
        ></label
      >
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
import photoService from "../services/PhotoService.js";

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
    useCloudinary() {
      photoService.myWidget.open();
      this.$store.commit("SET_SOURCE_BIRD");
    },
    updateBird() {
      this.bird.birdImg = this.$store.state.birdImg;
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
  display: flex;
  margin-top: 45px;
  align-items: center;
  background-color: var(--rich-black);
  border: 1px solid var(--rich-black);
}
#bird-img {
  width: 100%;
}
#bird-pic {
  display: flex;
  width: 50%;
}
form,
#bird-info {
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
  width: 50%;
  color: var(--baby-powder);
}
button {
  min-width: 65px;
  background-color: #ff9f1c;
  font-weight: bold;
  color: #011627;
  text-align: center;
  border: 1px solid #fdfffc;
  margin-top: 15px;
  margin-bottom: 15px;
}
#log-sighting {
  display: flex;
  flex-direction: column;
  gap: 5px;
}
</style>