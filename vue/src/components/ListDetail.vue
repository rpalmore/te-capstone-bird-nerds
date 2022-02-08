<!-- This component is for display a specific list + birds in that list (non-detailed view) -->
<template>
  <div id="list-detail">
      <h1>{{ list.listName }}</h1>
      <add-bird /> <br><br>
      <div class="birdInList" v-for="bird in this.$store.state.birds" v-bind:key="bird.birdID">
          <div class="numSightingsCircle">{{ bird.numSightings }} </div>
          <router-link :to="{name: 'bird-detail', params: {listId: listId, birdId: bird.birdID}}" >
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
    computed: {
        listId() {
            return this.$store.state.activeList
        },
        list() {
            return this.$store.state.lists.find(
                l => l.listId = this.$store.state.activeList
            );
        }
    },
    created() {
        birdService.getBirdsInList(this.listId)
            .then( response => {
                this.$store.commit("SET_BIRDS", response.data)
            });
    }

}
</script>

<style scoped>
#list-detail {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-top: 35px;
}

</style>