<template>
  <div>
    <form>
        <label for="birdImgURL">Customize bird photo by adding an image URL.</label>
        <input id='birdImgURL' type='text' placeholder="Add an image URL here" v-model="newBird.birdImg" />
    </form>
  </div>
</template>

<script>
import birdService from '../services/BirdService';

export default {
    name: "add-bird",
    data() {
        return {
            newBird: {},
            listId: this.$route.params.listId
        }
    },
    methods: {
        addBird() {
            birdService.createBird(this.listId, newBird)
                .then( response => {
                    if (response.status == 201) {
                        this.$router.push({ name: "list-detail", params: { listId: this.listId } });
                    }
                })
        }
    }
};
</script>

<style>
</style>