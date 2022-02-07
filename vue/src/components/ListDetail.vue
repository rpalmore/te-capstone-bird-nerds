<!-- This component is for display a specific list + birds in that list (non-detailed view) -->
<template>
  <div>
      <!-- List Name must be sent in as a param -->
      <h1>List Name</h1>
      <add-bird />
      <div class="birdInList" v-for="bird in this.birds" v-bind:key="bird.birdId">
          {{ bird }}<br><br>

          <div class="numSightingsCircle">{{ bird.numSightings }} </div>
          <div class="birdInfoBox">
              {{ bird.birdName }} 
              <div class="dateBox" v-show="bird.mostRecentSighting != null" >{{ bird.mostRecentSighting }}</div>
          </div>
      </div>
  </div>
</template>

<script>
import birdService from '../services/BirdService.js';

export default {
    name: "list-detail",
    data() {
        return {
            listId: this.$route.params.listId,
            birds: []
        }
    },
    created() {
        birdService.getBirdsInList(this.listId)
            .then( response => {
                this.birds = response.data;
            });
    }

}
</script>

<style>

</style>