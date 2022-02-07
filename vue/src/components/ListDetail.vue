<!-- This component is for display a specific list + birds in that list (non-detailed view) -->
<template>
  <div>
      <!-- idk how to get ListName in as a prop, might resort to backend -->
      <h1>{{ listName }}</h1>
      <add-bird /> <br><br>
      <div class="birdInList" v-for="bird in this.birds" v-bind:key="bird.birdId">
          <div class="numSightingsCircle">{{ bird.numSightings }} </div>
          <router-link :to="{name: 'bird-detail', params: {listId: bird.listId, birdId: bird.birdId} }" >
              <div class="birdInfoBox">
              {{ bird.birdName }} 
              <div class="dateBox" v-show="bird.mostRecentSighting != null" >{{ bird.mostRecentSighting }}</div>
          </div>
          </router-link>
      </div>
  </div>
</template>

<script>
import birdService from '../services/BirdService.js';
import AddBird from './AddBird'

export default {
    name: "list-detail",
    components: { AddBird },
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