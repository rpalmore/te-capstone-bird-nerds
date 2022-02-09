<template>
  <main id="wholePage">
    <h2 id="notesHeader">Bird Notes</h2>
    <div id="formDiv">
    <form id="wholeForm" v-on:submit.prevent="addNote">
      <div class="formItem">
      <label for="date-spotted" class="birdNotes">Date: </label><div></div>
      <input required
        id="date-spotted"
        type="date"
        v-model="birdNote.dateSpotted"
      />
      </div>
      <div class="formItem">
      <label for="num-males" class="birdNotes">Males: </label><div></div>
      <input id="num-males" type="text" placeholder="# of Males Spotted" v-model="birdNote.numMales" />
      </div>
      <div class="formItem">
      <label for="num-females" class="birdNotes">Females: </label><div></div>
      <input id="num-females" type="text" placeholder="# of Females Spotted" v-model="birdNote.numFemales" />
      </div>
      <div class="formItem">
      <label for="feeder-type" class="birdNotes">Feeder Type: </label><div></div>
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
      <label for="food-blend" class="birdNotes">Food Blend: </label><div></div>
      <select id="food-blend" v-model="birdNote.foodBlend">
        <option value="live-mealworms">Live Mealworms</option>
        <option value="bark-butter">Bark-butter</option>
        <option value="suet-cake">Suet Cake</option>
        <option value="wildlife-blend">Wildlife-blend</option>
        <option value="sunflower-blend">Sunflower-blend</option>
        <option value="nutty-blend">Nutty-blend</option>
      </select>
      </div>
      <div class="formItem">
        <label for="notes" class="birdNotes">Notes: </label><div></div>
        <input id="notes" type="text" v-model="birdNote.notes" />
      </div>
      <br>
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

#formDiv {
  margin-right: auto;
  margin-left: auto;
}
 
h2 {
  font-size: 3.5rem;
  text-align: center;
  
}

.formItem{
  padding-bottom: 30px;
  margin-left: auto;
  margin-right: auto;
}

#wholeForm {
  border: 4px solid #ff9f1c;
  outline: 8px solid #ec0f0f;
  padding: 25px;
  background-color: #011627;
  color: #FDFFFC;
  padding-left: 100px;
  padding-right: 100px;
}

select {
  width: 275px;
  font-size: 1rem;
  padding-top: 9px;
  padding-bottom: 9px;
  border-radius: 6px;
  border-left: 5px solid #FF9F1C;
  border-right: 5px solid #FF9F1C;
}

label {
  font-family: "Bitter", serif;
  font-size: 1.6rem;
  
  text-align: center;
}
input {
  padding: 8px;
  border-radius: 8px;
  font-size: 1.1rem;
  border: 1px solid #011627;
  border-left: 5px solid #FF9F1C;
  border-right: 5px solid #FF9F1C;
  width: 250px;
}

#submit {
  width: 100px;
  background-color: #FF9F1C;
  font-weight: bold;
  color:#011627;
  text-align: center;
  display: block;
  margin-right: auto;
  margin-left: auto;
}
::placeholder {
  text-align: center;
}

</style>