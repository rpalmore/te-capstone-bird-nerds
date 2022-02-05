<!-- This component is for display a specific list + birds in that list (non-detailed view) -->
<template>
    <main>
        <h1>{{ list.name }}</h1>
        <div class="birdInList" v-for="bird in this.birds" v-bind:key="bird.birdId">
            <div class="numSightingsCircle">{{ bird }}</div>
            

        </div>
    </main>
</template>

<script>
import birdService from "../services/BirdService.js";

export default {
    name: "listDetail",
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