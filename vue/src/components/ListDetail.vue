<!-- This component is for display a specific list + birds in that list (non-detailed view) -->
<template>
    <div>
        <h1>woo2 {{ list.name }}</h1>
        <div class="birdInList" v-for="bird in this.birds" v-bind:key="bird.birdId">
            <div class="numSightingsCircle">{{ bird }}</div>

        </div>
    </div>
</template>

<script>
import birdService from "../services/BirdService.js";

export default {
    data() {
        return {
            listId: 1,
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