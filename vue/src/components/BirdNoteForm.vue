<template>
  <main id="bird-note-form">
    <h2 class="pageHeader">Log a sighting</h2>
    <form id="loggingForm" v-on:submit.prevent="addNote">
      <label for="date-spotted">Date: </label>
      <input required type="date" v-model="birdNote.dateSpotted" />
      <div id="gender">
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
      <label for="feeder-type">Feeder Type:</label>
      <select id="feeder-type" v-model="birdNote.feederType">
        <option value="cylinder">Cylinder</option>
        <option value="hopper">Hopper-feeder</option>
        <option value="nectar">Nectar-feeder</option>
        <option value="seed-tube">Seed-Tube</option>
        <option value="suet-feeder">Suet-feeder</option>
        <option value="tray-feeder">Tray-feeder</option>
        <option value="peanut-feeder">Peanut-feeder</option>
      </select>
      <label for="food-blend">Food Blend:</label>
      <select id="food-blend" v-model="birdNote.foodBlend">
        <option value="live-mealworms">Live Mealworms</option>
        <option value="bark-butter">Bark-butter</option>
        <option value="suet-cake">Suet Cake</option>
        <option value="wildlife-blend">Wildlife-blend</option>
        <option value="sunflower-blend">Sunflower-blend</option>
        <option value="nutty-blend">Nutty-blend</option>
      </select>
      <label for="notes">Notes:</label>
      <textarea
        id="notesArea"
        wrap="soft"
        type="text"
        v-model="birdNote.notes"
      />
      <input type="submit" />
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

<style scoped>
#bird-note-form {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 35px;
  background-color: var(--rich-black);
  color: var(--baby-powder);
  margin-bottom: 50px;
}
.pageHeader {
  padding: 20px 0 20px 0;
  border-bottom: 1px solid var(--rose-madder);
}
#loggingForm {
  display: flex;
  flex-direction: column;
  gap: 12px;
}
#loggingForm > label {
  font-size: 1.3rem;
  text-align: left;
}
#gender {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
}
#numMales,
#numFemales {
  border-radius: 8px;
  font-size: 1.1rem;
  border: 1px solid var(--rich-black);
  border-left: 5px solid var(--orange-peel);
  border-right: 5px solid var(--orange-peel);
  width: 37px;
}
#numMales {
  margin-right: 8px;
}
#notesArea {
  height: 300px;
  word-wrap: break-word;
  word-break: break-word;
  border-left: 5px solid var(--orange-peel);
  border-right: 5px solid var(--orange-peel);
  border-radius: 8px;
}
input,
select,
textarea {
  width: 320px;
  padding: 8px;
  border-radius: 8px;
  font-size: 1.1rem;
  border: 1px solid var(--rich-black);
  border-left: 5px solid var(--orange-peel);
  border-right: 5px solid var(--orange-peel);
}
select {
  width: 348px;
}
input[type="submit"] {
  position: relative;
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
::placeholder {
  text-align: center;
}
</style>