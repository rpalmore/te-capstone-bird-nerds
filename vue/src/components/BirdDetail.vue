<!-- For displaying one "listcard" aka the basic details of a bird -->
<template>
  <div id='bird-detail' v-show="showForm === false">
        <div id="birdPic">
    <iframe
        width="250"
        height="250"
        v-bind:src="bird.birdImg + '/embed/800'"
        frameborder="0"
        allowfullscreen
        style="width: 300px"
        scrolling="no"
      ></iframe>
      <div id="birdInfo">
          <h2>{{bird.birdName}}</h2>
          <p>Spotted {{numSightings}} times</p>
          <p>Most recently seen on  {{bird.mostRecentSighting}}</p>
          <p>Zipcode: {{bird.zipcode}}</p>
      </div>
      <button v-on:click="showForm = true">Edit bird details</button>
  </div>
     
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