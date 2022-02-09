<template>
  <main id="wholePage">
    <h2 id="notesHeader">Bird Notes</h2>
    <form id="wholeForm" v-on:submit.prevent="addNote">
      <div>
      <label for="date-spotted" class="birdNotes">Date: </label><div></div>
      <input required
        id="date-spotted"
        type="date"
        v-model="birdNote.dateSpotted"
      />
      </div>
      <div>
      <label for="num-males" class="birdNotes">Males: </label><div></div>
      <input id="num-males" type="text" v-model="birdNote.numMales" />
      </div>
      <div>
      <label for="num-females" class="birdNotes">Females: </label><div></div>
      <input id="num-females" type="text" v-model="birdNote.numFemales" />
      </div>
      <div>
      <label for="feeder-type" class="birdNotes">Feeder Type: </label><div></div>
      <select id="feeder-type" size="7" v-model="birdNote.feederType">
        <option value="cylinder">Cylinder</option>
        <option value="hopper">Hopper-feeder</option>
        <option value="nectar">Nectar-feeder</option>
        <option value="seed-tube">Seed-Tube</option>
        <option value="suet-feeder">Suet-feeder</option>
        <option value="tray-feeder">Tray-feeder</option>
        <option value="peanut-feeder">Peanut-feeder</option>
      </select>
      </div>
      <div>
      <label for="food-blend" class="birdNotes">Food Blend: </label><div></div>
      <select id="food-blend" size="6" v-model="birdNote.foodBlend">
        <option value="live-mealworms">Live Mealworms</option>
        <option value="bark-butter">Bark-butter</option>
        <option value="suet-cake">Suet Cake</option>
        <option value="wildlife-blend">Wildlife-blend</option>
        <option value="sunflower-blend">Sunflower-blend</option>
        <option value="nutty-blend">Nutty-blend</option>
      </select>
      </div>
      <div>
        <label for="notes" class="birdNotes">Notes: </label><div></div>
        <input id="notes" type="text" v-model="birdNote.notes" />
      </div>
      <br>
      <input id="submit" type="submit" />
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
  methods: {
    addNote() {
      console.log(this.birdNote);
      birdNoteService
        .addNote(this.birdNote)
        .then((response) => {
          if (response.status === 201) {
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

<style>
 #content-main {
  display: flex;
  align-items: center;
} 
h2 {
 
  font-size: 3.5rem;
  
}

#wholeForm {
  border: 4px solid #ff9f1c;
  padding: 25px;
  background-color: #011627;
  color: #FDFFFC;
  width: 100%;
  align-items: center;
  
}

label {
  font-family: "Bitter", serif;
  font-size: 1.3rem;
  justify-items: center;
  align-items: center;
}
input {
  padding: 8px;
  border-radius: 8px;
  font-size: 1rem;
  border: 1px solid #011627;
  border-left: 5px solid #FF9F1C;
  border-right: 5px solid #FF9F1C;
}

#submit {
  border-left: 0;
  border-right: 0;
  align-items: center;
}


#wholePage {
  justify-content: center;
  align-items: center;
}  
/* #notesHeader {
  font-family: "Bitter", sans-serif;
  font-size: 3em;
}
#wholeForm {
  font-family: "Bitter", sans-serif;
  font-size: 25px;
  font-weight: 1000;
  flex-direction: column;
  margin-top: 10px;
} */
</style>