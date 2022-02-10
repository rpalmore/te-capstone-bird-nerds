<template>
  <div id="notesContainer">
    <div class="note" v-for="note in notes" v-bind:key="note.noteId">
      <div class="dateStuff">
        <p class="label">Date:</p>
        <p class="infoBox">{{ note.dateSpotted }}</p>
      </div>
      <br />
      <div id="rowTwo">
        <div class="genderStuff">
          <div class="males">
            <p class="label">Males :</p>
            <p class="infoBox">{{ note.numMales }}</p>
          </div>
          <div class="females">
            <p class="label">Females :</p>
            <p class="infoBox">{{ note.numFemales }}</p>
          </div>
        </div>
        <div class="foodStuff">
        <div class="feeder">
          <p class="label">Feeder :</p>
          <p class="infoBox">{{ note.feederType }}</p>
        </div>
        <div class="foodBlend">
          <p class="label">Food Blend :</p>
          <p class="infoBox">{{ note.foodBlend }}</p>
        </div>
        </div>
      </div>
      <div class="writtenNotes">
        <p class="label">Notes :</p>
        <p class="infoBox">{{ note.notes }}</p>
      </div>

      <button v-if="showForm === false" v-on:click="showForm = true">
        Edit
      </button>
      <button v-on:click="deleteNote(note.noteId)">Delete</button>

      <form
        v-on:submit.prevent="updateNote(note.noteId)"
        v-show="showForm === true"
      >
        <div class="formItem">
          <label for="date-spotted" class="birdNotes">Date: </label>
          <div></div>
          <input
            required
            id="date-spotted"
            type="date"
            v-model="note.dateSpotted"
          />
        </div>
        <div class="formItem">
          <label for="num-males" class="birdNotes">Males: </label>
          <div></div>
          <input
            id="num-males"
            type="text"
            placeholder="# of Males Spotted"
            v-model="note.numMales"
          />
        </div>
        <div class="formItem">
          <label for="num-females" class="birdNotes">Females: </label>
          <div></div>
          <input
            id="num-females"
            type="text"
            placeholder="# of Females Spotted"
            v-model="note.numFemales"
          />
        </div>
        <div class="formItem">
          <label for="feeder-type" class="birdNotes">Feeder Type: </label>
          <div></div>
          <select id="feeder-type" v-model="note.feederType">
            <option value="cylinder">Cylinder</option>
            <option value="hopper">Hopper-feeder</option>
            <option value="nectar">Nectar-feeder</option>
            <option value="seed-tube">Seed-Tube</option>
            <option value="suet-feeder">Suet-feeder</option>
            <option value="tray-feeder">Tray-feeder</option>
            <option value="peanut-feeder">Peanut-feeder</option>
          </select>
        </div>
        <div class="formItem">
          <label for="food-blend" class="birdNotes">Food Blend: </label>
          <div></div>
          <select id="food-blend" v-model="note.foodBlend">
            <option value="live-mealworms">Live Mealworms</option>
            <option value="bark-butter">Bark-butter</option>
            <option value="suet-cake">Suet Cake</option>
            <option value="wildlife-blend">Wildlife-blend</option>
            <option value="sunflower-blend">Sunflower-blend</option>
            <option value="nutty-blend">Nutty-blend</option>
          </select>
        </div>
        <div class="formItem">
          <label for="notes" class="birdNotes">Notes: </label>
          <div></div>
          <input id="notes" type="text" v-model="note.notes" />
        </div>
        <input type="submit" value="Save" />
        <input type="button" value="Cancel" v-on:click.prevent="resetForm" />
      </form>
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
      showForm: false,
    };
  },
  computed: {
    notes() {
      return this.$store.state.notes;
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
            }
          })
          .catch((err) => {
            console.error(err + " problem deleting note!");
          });
      }
    },
    updateNote(noteId) {
      let note = {};
      for (const n of this.notes) {
        if (n.noteId == noteId) note = n;
      }
      console.log(note);
      noteService
        .editNote(note)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("EDIT_NOTE", note);
          }
        })
        .catch((err) => {
          console.error(err + " problem editing note");
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
.note {
  border: 4px solid #ff9f1c;
  outline: 8px solid #ec0f0f;
  padding: 25px;
  background-color: #011627;
  color: #fdfffc;
  padding-left: 100px;
  padding-right: 100px;
  margin: 25px 0px;
}

/* .note {
 display: flex;
 flex-direction: column;
 width: 80%;
 border: 1px solid #011627;
 outline: 3px solid #fdfffc;
 margin: 10px;
 border-radius: 10px;
 background-color: #2ec4b6;
} */

select {
  width: 275px;
  font-size: 1rem;
  padding-top: 9px;
  padding-bottom: 9px;
  border-radius: 6px;
  border-left: 5px solid #ff9f1c;
  border-right: 5px solid #ff9f1c;
}

.label {
  font-family: "Bitter", serif;
  font-size: 1rem;
  margin-bottom: -1px;
  padding-left: 6px;

}
input {
  padding: 8px;
  border-radius: 8px;
  font-size: 1.1rem;
  border: 1px solid #011627;
  border-left: 5px solid #ff9f1c;
  border-right: 5px solid #ff9f1c;
  width: 250px;
}

#rowTwo {
  display: flex;
  flex-direction: row;
  align-items: center;

}

.males,.females {
  min-width: 85px;
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
  margin-right: 100px;
}

.foodStuff {
  display: flex;
  flex-direction: row;
}

.foodBlend, .feeder {
  min-width: 130px;
}

.infoBox {
  padding: 8px;
  border-radius: 8px;
  font-size: 1.1rem;
  border: 1px solid #011627;
  border-left: 5px solid #ff9f1c;
  border-right: 5px solid #ff9f1c;
  background-color: white;
  color: black;
  margin: 5px 5px;
  max-width: 500px;
}

button {
  width: 100px;
  background-color: #FF9F1C;
  font-weight: bold;
  color:#011627;
  text-align: center;
  display: inline-block;
  margin-left: 10px;
}
</style>