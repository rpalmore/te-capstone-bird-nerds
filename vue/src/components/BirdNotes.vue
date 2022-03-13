<template>
  <div id="notesContainer">
    <div class="note" v-for="note in notes" v-bind:key="note.noteId">
      <div id="noteItself" v-show="showForm === false">
        <div class="row-data">
          <span class="label">Date of sighting:</span>
          <span class="note-data">{{ note.dateSpotted }}</span>
        </div>
        <div class="row-data">
          <span class="label" v-show="note.numMales != null && note.numMales != 0">Males:</span>
          <span class="note-data" v-show="note.numMales != null && note.numMales != 0">{{
            note.numMales
          }}</span>
          <span class="label" v-show="note.numFemales != null && note.numFemales != 0">Females:</span>
          <span class="note-data" v-show="note.numFemales != null && note.numFemales != 0">{{
            note.numFemales
          }}</span>
        </div>
        <div class="row-data">
          <span class="label" v-show="note.feederType != null"
            >Feeder type:</span
          >
          <span class="note-data" v-show="note.feederType != null">{{
            note.feederType
          }}</span>
        </div>
        <div class="row-data">
          <span class="label" v-show="note.foodBlend != null">Food blend:</span>
          <span class="note-data" v-show="note.foodBlend != null">{{
            note.foodBlend
          }}</span>
        </div>
        <div class="row-data" v-show="note.notes != null">
          <span class="label">Notes:</span>
          <span class="note-data">{{ note.notes }}</span>
        </div>
        
        <div class="row-data">
          <input v-if="showForm === false" v-on:click="showForm = true" type="submit" value="Edit" />
          <input type="button" value="Delete" v-on:click="deleteNote(note.noteId)" />
        </div>
      </div>

      <main id="bird-note-form-edit" v-show="showForm === true">
        <h2 class="pageHeader">Edit this sighting</h2>
      <form id="loggingForm"
        v-on:submit.prevent="updateNote(note.noteId)"
      >

          <label for="date-spotted">Date: </label>
          <input
            required
            id="date-spotted"
            type="date"
            v-model="note.dateSpotted"
          />
        
        <div class="row-space-between">
          <label for="num-males">Males: </label>
          <input
            id="numMales"
            type="text"
            v-model="note.numMales"
          />
          <label for="num-females">Females: </label>
          <input
            id="numFemales"
            type="text"
            v-model="note.numFemales"
          />
          </div>
        
          <label for="feeder-type">Feeder type: </label>
          <select id="feeder-type" v-model="note.feederType">
            <option value="Cylinder">Cylinder</option>
            <option value="Hopper">Hopper</option>
            <option value="Nectar">Nectar</option>
            <option value="Seed tube">Seed tube</option>
            <option value="Suet feeder">Suet</option>
            <option value="Tray feeder">Tray</option>
            <option value="Peanut feeder">Peanut</option>
          </select>

          <label for="food-blend">Food blend: </label>
          <select id="food-blend" v-model="note.foodBlend">
            <option value="Live mealworms">Live mealworms</option>
            <option value="Bark butter">Bark butter</option>
            <option value="Suet cake">Suet cake</option>
            <option value="Wildlife blend">Wildlife blend</option>
            <option value="Sunflower blend">Sunflower blend</option>
            <option value="Nutty blend">Nutty blend</option>
          </select>
    
          <label for="notes">Notes: </label>
          <textarea id="notesArea" type="text" v-model="note.notes" />
        <div class="row-data">
        <input class="btn" type="submit" value="Save" />
        <input class="btn" type="button" value="Cancel" v-on:click.prevent="resetForm" />
        </div>
      </form>
      </main>
    </div>
</template>

<script>
import noteService from "../services/BirdNoteService.js";

export default {
  name: "bird-notes",
  params: ["bird"],
  data() {
    return {
      birdId: this.$route.params.birdId,
      showForm: false,
    };
  },
  computed: {
    notes() {
      return this.$store.state.notes;
    },
    bird() {
      return this.$store.state.birds.find(
        (b) => b.birdID == this.$route.params.birdId
      );
    },
  },
  methods: {
    deleteNote(noteId) {
      if (confirm("Are you sure you want to delete this note?")) {
        noteService
          .deleteNote(noteId)
          .then((response) => {
            if (response.status == 204) {
              this.$store.commit("DELETE_NOTE", noteId);
              const bird = this.bird;
              bird.numSightings--;
              this.$store.commit("EDIT_BIRD", bird);
            }
          })
          .catch((err) => {
            alert.error(err + " problem deleting note!");
          });
      }
    },
    updateNote(noteId) {
      let note = {};
      for (const n of this.notes) {
        if (n.noteId == noteId) note = n;
      }
      noteService
        .editNote(note)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("EDIT_NOTE", note);
          }
        })
        .catch((err) => {
          alert.error(err + " problem editing note");
        });
      this.resetForm();
    },
    resetForm() {
      this.showForm = false;
    },
  },
  created() {
    noteService.getNotes(this.birdId).then((response) => {
      this.$store.commit("SET_NOTES", response.data);
    });
  },
};
</script>

<style>
#notesContainer {
  display: flex;
  flex-direction: column;
  justify-content: center;
  font-family: "Bitter", serif;
  font-size: 1.3rem;
}
.note {
  background-color: var(--rich-black);
  color: var(--baby-powder);
  margin-top: 45px;
}
#noteItself {
  display: flex;
  flex-direction: column;
  padding: 50px;
  gap: 12px;
}
.row-data {
  display: flex;
  flex-direction: row;
  gap: 5px;
}
.column-data {
  display: flex;
  flex-direction: column;
  gap: 5px;
}
.label {
  color: var(--orange-peel);
}
#noteItself input[type="submit"],
#noteItself input[type="button"] {
  background-color: var(--orange-peel);
  border: 1px solid var(--rose-madder);
  border-right: 5px solid var(--rose-madder);
  border-left: 5px solid var(--rose-madder);
  border-radius: 8px;
  width: 15%;
  font-weight: bold;
  color: var(--rich-black);
  margin-top: 10px;
  padding: 3px 0 3px 0;
}
/* Additional styles for updating these notes 
   included in BirdNoteForm.vue */
#bird-note-form-edit {
  display: flex;
  flex-direction: column;
  width: 100%;
  align-items: center;
  background-color: var(--rich-black);
  color: var(--baby-powder);
}
@media only screen and (max-width: 768px) {
  #noteItself input[type="submit"],
  #noteItself input[type="button"] {
    width: auto;
    padding: 8px;
  }
}
</style>