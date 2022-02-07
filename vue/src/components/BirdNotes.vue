<template>
  <div id="notesContainer">
    <div class="note" v-for="note in this.notes" v-bind:key="note.noteId">
      <div class="DateStuff">
        Date:
        <p class="infoBox">{{ note.dateSpotted }}</p>
      </div>
      <br />
      <div class="genderStuff">
        <div class="males">
          males
          <p class="infoBox">{{ note.numMales }}</p>
        </div>
        <div class="females">
          females
          <p class="infoBox">{{ note.numFemales }}</p>
        </div>
      </div>
      <div class="feeder">
        <p class="infoBox">{{ note.feederType }}</p>
      </div>
      <div class="foodBlend">
        <p class="infoBox">{{ note.foodBlend }}</p>
      </div>
      <div class="writtenNotes">
        Notes:
        <p class="noteField">{{ note.notes }}</p>
      </div>
    </div>
  </div>
</template>

<script>
import noteService from "../services/BirdNoteService.js";

export default {
  name: "bird-notes",
  data() {
    return {
      birdId: this.$route.params.birdId,
      notes: [],
    };
  },
  created() {
    noteService.getNotes(this.birdId).then((response) => {
      this.notes = response.data;
    });
  },
};
</script>

<style>
#notesContainer {
 display: flex;
 flex-direction: column;
 width: 80%;
 border: 4px solid whitesmoke;
}
</style>