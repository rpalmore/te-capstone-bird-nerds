<template>
  <div>
    <form v-on:submit.prevent="addBird">
      <label for="birdImgURL"
        >Customize bird photo by adding an image URL.</label
      >
      <input
        id="birdImgURL"
        type="text"
        placeholder="Add an image URL here"
        v-model="newBird.birdImg"
      />
      <label for="birdName">Enter bird name: </label>
      <input
        id="birdName"
        type="text"
        placeholder="Name"
        v-model="newBird.birdName"
      />
      <!-- get placeholder to be the user's zipcode -->
      <label for="zipcode">Zipcode: </label>
      <input
        id="zipcode"
        type="text"
        placeholder="Zipcode"
        v-model="newBird.zipcode"
      />
      <input type="submit" />
    </form>
  </div>
</template>

<script>
import birdService from "../services/BirdService";

export default {
  name: "add-bird",
  data() {
    return {
      newBird: {},
      listId: this.$route.params.listId,
    };
  },
  methods: {
    addBird() {
      birdService.createBird(this.listId, this.newBird).then((response) => {
        if (response.status == 201) {
            this.$router.go(0);
        }
      });
    },
  },
};
</script>

<style>
</style>