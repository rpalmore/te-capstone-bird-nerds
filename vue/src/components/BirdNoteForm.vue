<template>
  <main>
      <h2>Bird Notes</h2>
      <form v-on:submit.prevent="addNote">
      <br/>

      <label for="date-spotted">Date: </label>
      <input id="date-spotted" type="datetime-local" v-model="birdNote.dateSpotted"/>

      <br/>
      <br/>

      <label for="num-males">Males: </label>
      <input id="num-males" type="text" v-model="birdNote.numMales"/>

      <br/>
      <br/>

      <label for="num-females">Females: </label>
      <input id="num-females" type="text" v-model="birdNote.numFemales"/>

      <br/>
      <br/>

      <label for="feeder-type">Feeder Type: </label>
      <select id="feeder-type" size="7" v-model="birdNote.feederType">
        <option value="cylinder">Cylinder</option>
        <option value="hopper">Hopper-feeder</option>
        <option value="nectar">Nectar-feeder</option>
        <option value="seed-tube">Seed-Tube</option>
        <option value="suet-feeder">Suet-feeder</option>
        <option value="tray-feeder">Tray-feeder</option>
        <option value="peanut-feeder">Peanut-feeder</option>
      </select>

      <br/>
      
      <label for="food-blend">Food Blend: </label>
      <select id="food-blend" size="6" v-model="birdNote.foodBlend">
        <option value="live-mealworms">Live Mealworms</option>
        <option value="bark-butter">Bark-butter</option>
        <option value="suet-cake">Suet Cake</option>
        <option value="wildlife-blend">Wildlife-blend</option>
        <option value="sunflower-blend">Sunflower-blend</option>
        <option value="nutty-blend">Nutty-blend</option>
      </select>
      <br/>
      <p>
      <label for="notes">Notes: </label>
      <input id="notes" type="text" v-model="birdNote.notes">
      </p>
      <br/>
      <input type="submit" />

  </form>
  </main>
</template>

<script>
import birdNoteService from "../services/BirdNoteService";
export default {
  data() {
    return {
      birdNote: {
        // username: this.$store.state.user.username,
      },
    };
  },
  methods: {
    addNote() {
        console.log(this.birdNote);
        birdNoteService.addNote(this.birdNote).then((response) => {

            if (response.status === 200) {
                alert("note created!");
            }
        })
        .catch((err) => {
            console.error(err + " problem adding bird note!");
        });
    },
  }
}
</script>

<style>

</style>