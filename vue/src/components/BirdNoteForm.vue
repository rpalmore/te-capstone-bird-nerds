<template>
  <main id="bird-note-form">
    <h2 class="pageHeader">Log a sighting</h2>
    <form id="loggingForm" v-on:submit.prevent="addNote">
      <label for="date-spotted">Date: </label>
      <input required type="date" v-model="birdNote.dateSpotted" />
      <div class="row-space-between">
        <label for="num-males">Males:</label>
        <input
          id="numMales"
          type="number"
          min="0"
          placeholder="#"
          v-model="birdNote.numMales"
        />
        <label for="num-females">Females:</label>
        <input
          id="numFemales"
          type="number"
          min="0"
          placeholder="#"
          v-model="birdNote.numFemales"
        />
      </div>
      <label for="feeder-type">Feeder type:</label>
      <select id="feeder-type" v-model="birdNote.feederType">
        <option value="cylinder">Cylinder</option>
        <option value="hopper">Hopper</option>
        <option value="nectar">Nectar</option>
        <option value="seed-tube">Seed tube</option>
        <option value="suet-feeder">Suet</option>
        <option value="tray-feeder">Tray</option>
        <option value="peanut-feeder">Peanut</option>
      </select>
      <label for="food-blend">Food blend:</label>
      <select id="food-blend" v-model="birdNote.foodBlend">
        <option value="live-mealworms">Live mealworms</option>
        <option value="bark-butter">Bark butter</option>
        <option value="suet-cake">Suet cake</option>
        <option value="wildlife-blend">Wildlife blend</option>
        <option value="sunflower-blend">Sunflower blend</option>
        <option value="nutty-blend">Nutty blend</option>
      </select>
      <label for="notes">Notes:</label>
      <textarea
        id="notesArea"
        wrap="soft"
        type="text"
        v-model="birdNote.notes"
      />
      <div class="row-data">
      <input type="submit" />
      </div>
    </form>
  </main>
</template>

<script>
import birdNoteService from "../services/BirdNoteService";
export default {
  data() {
    return {
      birdNote: {
        birdId: this.$route.params.birdId,
      },
    };
  },
  computed: {
    bird() {
      return this.$store.state.birds.find(
        (b) => b.birdID == this.$route.params.birdId
      );
    },
  },
  methods: {
    addNote() {
      birdNoteService
        .addNote(this.birdNote)
        .then((response) => {
          if (response.status === 201) {
            const bird = this.bird;
            bird.numSightings++;
            this.$store.commit("EDIT_BIRD", bird);
            this.$router.go(0);
          }
        })
        .catch((err) => {
          alert(err + " problem adding bird note!");
        });
    },
  },
};
</script>

<style>
#bird-note-form {
  display: flex;
  flex-direction: column;
  margin-top: 45px;
  width: 100%; 
  align-items: center;
  background-color: var(--rich-black);
  color: var(--baby-powder);
  margin-bottom: 50px;
}
.pageHeader {
  padding: 20px 0 20px 0;
  border-bottom: 1px solid var(--rose-madder);
  font-size: 1.5rem;
}
#loggingForm {
  display: flex;
  flex-direction: column;
  gap: 12px;
  width: 52%;
}
#loggingForm label {
  font-size: 1.3rem;
  text-align: left;
}
.row-space-between {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
}
#numMales,
#numFemales {
  width: 37px;
}
#notesArea {
  height: 300px;
  word-wrap: break-word;
  word-break: break-word;
}
#loggingForm input,
#loggingForm textarea {
  padding: 8px;
  border-radius: 8px;
  font-size: 1.1rem;
  border: 1px solid var(--rich-black);
  border-left: 5px solid var(--orange-peel);
  border-right: 5px solid var(--orange-peel);
}
#loggingForm input[type="submit"], #loggingForm input[type="button"] {
  width: 33%;
  left: 33%;
  background-color: var(--orange-peel);
  border: 1px solid var(--rose-madder);
  border-right: 5px solid var(--rose-madder);
  border-left: 5px solid var(--rose-madder);
  border-radius: 8px;
  font-weight: bold;
  color: var(--rich-black);
  margin-top: 10px;
  margin-bottom: 25px;
}
#loggingForm > .row-data {
  justify-content: center;
}
</style>