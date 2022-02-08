<template>
  <div id="add-bird-form">
    <form v-on:submit.prevent="addBird">
      <label for="birdImgURL"
        >Upload a photo of this bird from our friends at
        <a href="https://search.macaulaylibrary.org/catalog" target="_blank"
          >eBird.org and the Macaulay Library : </a>
      </label>
      
      <input
        id="birdImgURL"
        type="text"
        placeholder="Add an image URL here"
        v-model="newBird.birdImg"
      />
      <label for="birdName">Enter bird name:  </label>
      <input
        id="birdName"
        type="text"
        placeholder="Name"
        v-model="newBird.birdName"
      />
      <!-- get placeholder to be the user's zipcode -->
      <label for="zipcode">Zip code: </label>
      <input
        id="zipcode"
        type="text"
        placeholder="Zip code"
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

<style scoped>

#add-bird-form {
  display: flex;
  flex-direction: column;
  border: 4px solid #ff9f1c;
  padding: 25px;
  background-color: #011627;
  color: #FDFFFC;
}
form {
  display: flex;
  flex-direction: column;
  gap: 12px;
  align-items: center;
}
a {
  text-decoration: none;
  color: #FDFFFC;
}
label {
  font-family: "Bitter", serif;
  font-size: 1.3rem;
}
input, input[type="submit"] {
  padding: 8px;
  width: 60%;
  border-radius: 8px;
  font-size: 1rem;
  border: 1px solid #011627;
  border-left: 5px solid #FF9F1C;
  border-right: 5px solid #FF9F1C;
}
input[type="submit"] {
  margin-top: 25px;
  width: 35%;
  background-color: #E71D36;
  font-weight: bold;
}
</style>