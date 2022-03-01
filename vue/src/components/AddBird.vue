<template>
  <div id="add-bird-form">
    <form v-on:submit.prevent="addBird">
      <label for="birdName">What bird did you see?</label>
      <input
        id="birdName"
        type="text"
        required
        placeholder="Name"
        v-model="newBird.birdName"
      />
      <label for="zipcode">Zip code where you spotted this bird: </label>
      <input
        id="zipcode"
        type="text"
        required
        pattern="[0-9]{5}"
        title="Numbers only, please!"
        placeholder="#####"
        v-model="newBird.zipcode"
      />
      <label for="birdImgURL">Upload a picture of the bird you spotted.</label>
      <a id="add-bird" v-on:click="useCloudinary()">Add photo</a>
      <button id="submit">Save</button>
    </form>
  </div>
</template>

<script>
import birdService from "../services/BirdService";
import photoService from "../services/PhotoService";

export default {
  name: "add-bird",
  data() {
    return {
      newBird: {
        birdImg: ''
      },
      listId: this.$route.params.listId,
      addBirdTest: false,
    };
  },
  methods: {
    useCloudinary() {
      photoService.myWidget.open();
      this.$store.commit("SET_SOURCE_BIRD");
    },
    addBird() {
      this.newBird.birdImg = this.$store.state.birdImg;
      birdService.createBird(this.listId, this.newBird).then((response) => {
        if (response.status === 201) {
          this.$store.commit("ADD_BIRD", response.data);
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
  color: #fdfffc;
  margin-top: 10px;
  width: 80%;
}
form {
  display: flex;
  flex-direction: column;
  gap: 12px;
  align-items: center;
}
a {
  text-decoration: none;
  color: #fdfffc;
}
a:hover {
  color: #ff9f1c;
}
label {
  font-family: "Bitter", serif;
  font-size: 1.3rem;
  text-align: center;
}
input,
button#submit {
  padding: 8px;
  margin-bottom: 10px;
  width: 60%;
  border-radius: 8px;
  font-size: 1rem;
  border: 1px solid #011627;
  border-left: 5px solid #ff9f1c;
  border-right: 5px solid #ff9f1c;
}
button#submit {
  margin-top: 25px;
  width: 35%;
  background-color: #e71d36;
  font-weight: bold;
  color: var(--baby-powder);
}
</style>