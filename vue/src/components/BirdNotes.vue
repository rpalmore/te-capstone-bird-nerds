<template>
  <div id="notesContainer">
    <div class="note" v-for="note in notes" v-bind:key="note.noteId">
      {{note}}
      <div class="dateStuff">
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
      <button v-on:click="deleteNote(note.noteId)">Delete</button>
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
    };
  },
  computed: {
    notes() {
      return this.$store.state.notes;
    }
  },
  methods: {
    deleteNote(noteId) {
      if (confirm("Are you sure you want to delete this note?")) {
        noteService.deleteNote(noteId)
        .then( response => {
          if (response.status == 204) {
            this.$store.commit("DELETE_NOTE", noteId);
          }
        }).catch((err) => {
          console.error(err + " problem deleting note!");
        });
      }
    }
  },
  created() {
    noteService.getNotes(this.birdId).then((response) => {
      this.$store.commit("SET_NOTES", response.data);
    });
  },
};
</script>

<style>
.note {
 display: flex;
 flex-direction: column;
 width: 80%;
 border: 1px solid #011627;
 outline: 3px solid #fdfffc;
 margin: 10px;
 border-radius: 10px;
 background-color: #2ec4b6;
}

.dateStuff {
  display: inline-block;
  align-items: center;
  margin-left: 5px;
  margin-top: 5px;
}

.genderStuff {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-evenly;
}

.infoBox {
  background-color: #f7e1d7;
}


</style>