<!-- For displaying one "listcard" aka the basic details of a bird -->
<template>
  <div id='bird-detail'>
     {{bird}}
          <a
      id="edit-bird-button"
      href="#"
      v-if="showForm === false"
      v-on:click.prevent="showForm = true"
    >Edit</a>
    <form v-on:submit.prevent="updateBird" v-show="showForm === true">
        <label for="newName">Name:</label>
        <input id="newName" type="text" v-model="bird.birdName"/>
        <label for="newUrl">Image URL:</label>
        <input id="newURL" type="text"  v-model="bird.birdImg" />
        <label for="newZip">Zipcode:</label>
        <input id="newZip" type="text" v-model="bird.zipcode" />
        <input type="submit" value="Save" />
        <input type="button" value="Cancel" v-on:click.prevent="resetForm" />
    </form>
  </div>
</template>

<script>
import birdService from '../services/BirdService.js';

export default {
    name: "bird-detail",
    data() {
        return {
            showForm: false,
        }
    },
    computed: {
        bird() {
            return this.$store.state.birds.find(
                b => b.birdID = this.$store.state.activeBird
            );
        },
    },
    methods: {
        updateBird() {
            console.log(this.bird);
            birdService.editBird(this.bird).then(
                response => {
                    if (response.status == 201) {
                        this.$store.commit("EDIT_BIRD", this.bird);
                    }
                    }).catch((err) => {
                        console.error(err + " problem editing bird");
                })
            this.resetForm();
        },
        resetForm() {
            this.showForm = false;
        }
    }
}
</script>

<style>

</style>