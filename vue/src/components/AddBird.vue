<template>
  <div id="add-bird-form">
    <form v-on:submit.prevent="addBird">
      <label for="birdName">What bird did you see?</label>
      <input
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
      <label for="birdImgURL"
        ><a id="add-bird" v-on:click="useCloudinary()"
          >Click here to upload a picture</a
        >
        of the bird you spotted.</label
      >
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
        birdImg: "",
      },
      listId: this.$route.params.listId,
      addBirdTest: false,
    };
  },
  methods: {
    useCloudinary() {
      photoService.myWidget.open();
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
  created() {
      this.$store.commit("SET_SOURCE_BIRD");
  }
};
</script>

<style>
#add-bird-form {
  display: flex;
  flex-direction: column;
  border: 4px solid var(--orange-peel);
  background-color: var(--rich-black);
  color: var(--baby-powder);
  padding: 25px;
}
#add-bird-form > form {
  display: flex;
  flex-direction: column;
  gap: 12px;
  align-items: center;
}
a {
  text-decoration: none;
  color: var(--baby-powder);
}
a:hover {
  color: var(--orange-peel);
}
#add-bird-form > label {
  font-family: "Bitter", serif;
  font-size: 1.3rem;
  text-align: center;
}
#add-bird-form input,
#add-bird-form button#submit {
  padding: 8px;
  margin-bottom: 10px;
  width: 60%;
  border-radius: 8px;
  font-size: 1rem;
  font-family: "Bitter", serif;
  border: 1px solid var(--rich-black);
  border-left: 5px solid var(--orange-peel);
  border-right: 5px solid var(--orange-peel);
} 
#add-bird-form button#submit {
  margin-top: 25px;
  width: 35%;
  background-color: var(--rose-madder);
  font-weight: bold;
  color: var(--baby-powder);
}
</style>