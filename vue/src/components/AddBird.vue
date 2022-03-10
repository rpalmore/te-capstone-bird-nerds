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
      <button id="submit">Save</button>
    </form>
  </div>
</template>

<script>
import birdService from "../services/BirdService";

export default {
  name: "add-bird",
  data() {
    return {
      newBird: {
        birdImg: "",
      },
      listId: this.$route.params.listId,
    };
  },
  methods: {
    addBird() {
      this.newBird.birdImg = "No photo";
      birdService.createBird(this.listId, this.newBird).then((response) => {
        if (response.status === 201) {
          this.$store.commit("ADD_BIRD", response.data);
        }
        this.newBird = {};
      });
    },
  },
  created() {
    this.$store.commit("SET_SOURCE_BIRD");
  },
};
</script>

<style>
#add-bird-form {
  display: flex;
  flex-direction: column;
  width: 75%;
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
#add-bird-form > form > label {
  font-family: "Bitter", serif;
  font-size: 1rem;
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
  width: 35%;
  background-color: var(--rose-madder);
  font-weight: bold;
  color: var(--baby-powder);
}
</style>