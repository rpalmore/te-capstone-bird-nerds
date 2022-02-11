<template>
  <main id="logSighting">
    <span class="pageHeader">Log your sighting</span>
    <div id="formDiv">
      <form id="loggingForm" v-on:submit.prevent="addNote">
        <div class="formItem">
          <label for="date-spotted">Date: </label><br>
          <input
            required
            type="date"
            v-model="birdNote.dateSpotted"
          />
        </div>
        <div id="gender">
            <label for="num-males">Males: </label>
            <input
              id="numMales"
              type="number"
              min="0"
              placeholder="#"
              v-model="birdNote.numMales"
          />
          <label for="num-females">Females: </label>
          <input
            id="numFemales"
            type="number"
            min="0"
            placeholder="#"
            v-model="birdNote.numFemales"
          />
        </div>
        <div class="formItem">
          <label for="feeder-type">Feeder Type: </label> <br>
          <select id="feeder-type" v-model="birdNote.feederType">
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
          <label for="food-blend">Food Blend: </label> <br>
          <select id="food-blend" v-model="birdNote.foodBlend">
            <option value="live-mealworms">Live Mealworms</option>
            <option value="bark-butter">Bark-butter</option>
            <option value="suet-cake">Suet Cake</option>
            <option value="wildlife-blend">Wildlife-blend</option>
            <option value="sunflower-blend">Sunflower-blend</option>
            <option value="nutty-blend">Nutty-blend</option>
          </select>
        </div>
        <div class="notesArea">
          <label for="notes">Notes: </label> <br>
          <textarea
            id="notesArea"
            wrap="soft"
            type="text"
            v-model="birdNote.notes"
          />
        </div>
        <br />
        <input id="submit" type="submit" />
      </form>
    </div>
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
      return this.$store.state.birds.find(b => b.birdID == this.$route.params.birdId);
    }
  },
  methods: {
    addNote() {
      console.log(this.birdNote);
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
          console.error(err + " problem adding bird note!");
        });
    },
  },
};
</script>

<style scoped>
#logSighting {
  display: flex;
  flex-direction: column;
  margin-top: 35px;
  padding: 20px;
  background-color: #011627;
  color: #fdfffc;
  align-items: center;
  margin-bottom: 50px;
}
.pageHeader {
  font-size: 1.8rem;
  text-align: center;
  font-weight: normal;
  padding: 20px 0 20px 0;
  border-bottom: 1px solid #E71D36;
}
#formDiv {
  margin-top: 25px;
  display: flex;
}
#loggingForm {
  display: flex;
  flex-direction: column;
  gap: 12px;
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
  border: 1px solid #011627;
  border-left: 5px solid #ff9f1c;
  border-right: 5px solid #ff9f1c;
  width: 37px;
}
#numMales {
  margin-right: 8px;
}
#notesArea {
  height: 300px;
  word-wrap: break-word;
  word-break: break-word;
  border-left: 5px solid #ff9f1c;
  border-right: 5px solid #ff9f1c;
  border-radius: 8px;
}
label {
  font-size: 1.6rem;
}
input, select, textarea {
  margin-top: 10px;
  width: 320px;
  padding: 8px;
  border-radius: 8px;
  font-size: 1.1rem;
  border: 1px solid #011627;
  border-left: 5px solid #ff9f1c;
  border-right: 5px solid #ff9f1c;
}
select {
  width: 348px;
}
#submit {
  width: 33%;
  background-color: #ff9f1c;
  border: 1px solid #E71D36;
  font-weight: bold;
  color: #011627;
  margin-bottom: 25px;
  position: relative;
  left: 33%;
}
::placeholder {
  text-align: center;
}
</style>