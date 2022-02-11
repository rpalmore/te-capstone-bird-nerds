<template>
    <div id="bird-vue">
        <bird-detail />
        <bird-note-form v-bind:bird="bird"/>
        <bird-notes v-bind:bird="bird"/>
    </div>
</template>

<script>
import birdDetail from '../components/BirdDetail.vue';
import birdNotes from '../components/BirdNotes.vue';
import birdNoteForm from '../components/BirdNoteForm.vue';


export default {
    components: {birdDetail, birdNotes, birdNoteForm},
    computed: {
        bird() {
            return this.$store.state.birds.find(
                b => b.birdID == this.$route.params.birdId
            );
        },
    },
    created() {
        this.$store.commit("SET_ACTIVE_BIRD", this.$route.params.birdId);
    }

}
</script>


<style scoped>
#bird-vue {
    display: flex;
    flex-direction: column;
}

</style>