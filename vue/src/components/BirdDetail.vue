<template>
  <div id="bird-detail">
    <div id="bird-pic">
      <img v-bind:src="selectImg" alt="" id="bird-img" />
      <div id="image-overlay" v-on:click="useCloudinary">
        <a id="cloudinary-link">Update image</a>
      </div>
    </div>
    <div id="bird-info" v-show="showForm === false">
      <h2 id="bird-name">{{ bird.birdName }}</h2>
      <p>
        Spotted:
        {{
          bird.numSightings > 1 || bird.numSightings === 0
            ? bird.numSightings + " times"
            : bird.numSightings + " time"
        }}
      </p>
      <p v-show="bird.mostRecentSighting != null">
        Last spotted: {{ bird.mostRecentSighting }}
      </p>
      <p>Zip code: {{ bird.zipcode }}</p>
      <input
        class="button"
        type="button"
        value="Edit"
        v-on:click="showForm = true"
      />
    </div>
    <form
      id="log-sighting"
      v-on:submit.prevent="updateBird"
      v-show="showForm === true"
    >
      <label for="newName">Bird name:</label>
      <input id="newName" type="text" v-model="bird.birdName" />
      <label for="newZip">Zip code:</label>
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
    selectImg() {
      return this.bird.birdImg == "No photo"
        ? "/img/BirdSilhouette.83f473b2.png"
        : this.bird.birdImg;
    },
  },
  methods: {
    useCloudinary() {
      photoService.myWidget.open();
      this.$store.commit("SET_SOURCE_BIRD", this.$route.params.birdId);
    },
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
  display: flex;
  margin-top: 45px;
  align-items: center;
  background-color: var(--rich-black);
  border: 1px solid var(--rich-black);
}
#bird-name {
  padding-bottom: 5px;
  border-bottom: 1px solid var(--orange-peel);
}
#bird-img {
  width: 100%;
}
#bird-pic:hover #bird-img {
  opacity: 0.5;
}
#bird-pic:hover #cloudinary-link {
  opacity: 1;
}
#bird-pic {
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: var(--baby-powder);
}
form,
#bird-info {
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
  width: 50%;
  color: var(--baby-powder);
  margin-bottom: 15px;
  padding: 0 5px 0 5px;
}
#bird-info input.button {
  background-color: var(--orange-peel);
  border: 1px solid var(--rose-madder);
  border-right: 5px solid var(--rose-madder);
  border-left: 5px solid var(--rose-madder);
  border-radius: 8px;
  font-weight: bold;
  color: var(--rich-black);
  margin-top: 10px;
}
#log-sighting {
  gap: 9px;
  margin: 15px;
}
#log-sighting label,
input {
  font-size: 1.1rem;
}
#log-sighting input {
  width: 95%;
  padding: 1px;
  border-radius: 8px;
  border: 1px solid var(--rich-black);
  border-left: 5px solid var(--orange-peel);
  border-right: 5px solid var(--orange-peel);
}
#buttons {
  display: flex;
  gap: 5px;
  justify-content: center;
}
#buttons > input {
  background-color: var(--orange-peel);
  border: 1px solid var(--rose-madder);
  border-right: 5px solid var(--rose-madder);
  border-left: 5px solid var(--rose-madder);
  border-radius: 8px;
  font-weight: bold;
  color: var(--rich-black);
  padding: 0 3px 0 3px;
}
@media only screen and (max-width: 768px) {
  #bird-detail {
    flex-direction: column;
  }
}
</style>