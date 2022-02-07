<template>
    <div>
        <bird-detail />
        <bird-note-form />
        <bird-notes />
    </div>
  
</template>

<script>
import birdDetail from '../components/BirdDetail.vue';
import birdNotes from '../components/BirdNotes.vue';
import birdNoteForm from '../components/BirdNoteForm.vue';


export default {
    components: {birdDetail, birdNotes, birdNoteForm}

}
</script>

<style>

</style>