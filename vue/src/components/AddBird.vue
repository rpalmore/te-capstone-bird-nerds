<template>
  <div id="add-bird-form">
    <form v-on:submit.prevent="addBird">
      <label for="birdImgURL"
        >Upload a photo of this bird from our friends at
        <a href="https://search.macaulaylibrary.org/catalog" target="_blank"
          >eBird.org and the Macaulay Library</a
        >.</label
      >
      <input
        id="birdImgURL"
        type="text"
        placeholder="Add an image URL here"
        v-model="newBird.birdImg"
      />
      <br/><br/>
      <label for="birdName">Enter bird name:  </label>
      <input
        id="birdName"
        type="text"
        placeholder="Name"
        v-model="newBird.birdName"
      />
      <br/> <br/>
      <!-- get placeholder to be the user's zipcode -->
      <label for="zipcode">Zipcode:  </label>
      <input
        id="zipcode"
        type="text"
        placeholder="Zip code"
        v-model="newBird.zipcode"
      />
      <input type="submit" size=10 />
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

<style scoped>
#add-bird-form {
  display: flex;
  flex-direction: column;
}
form {
  display: flex;
  flex-direction: column;
  gap: 15px;
}
a {
  text-decoration: none;
  color: #011627;
}