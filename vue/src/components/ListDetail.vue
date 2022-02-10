<!-- This component is for display a specific list + birds in that list (non-detailed view) -->
<template>
  <div id="list-detail">
      <h1>{{ list.listName }}</h1>
      <a
      id="rename-button"
      href="#"
      v-if="showForm === false"
      v-on:click.prevent="showForm = true"
    >Rename</a>
    <form v-on:submit.prevent="updateName" v-show="showForm === true">
        <label for="newName">Enter new name:</label>
        <input id="newName" type="text" v-model="list.listName" />
        <input type="submit" value="Save" />
        <input type="button" value="Cancel" v-on:click.prevent="resetForm" />
    </form><br><br>

    <add-bird />
    <div
      class="birdInList"
      v-for="bird in this.$store.state.birds"
      v-bind:key="bird.birdID"
    >
      <router-link
        :to="{
          name: 'bird-detail',
          params: { listId: listId, birdId: bird.birdID },
        }"
      >
        <div class="birdInfoBox">
          {{ bird.birdName }}
          <div class="dateBox" v-show="bird.mostRecentSighting != null">
            {{ bird.mostRecentSighting }}
          </div>
        </div>
      </router-link>
      <div id="deleteButton">
        <button v-on:click="deleteBird(bird.birdID)">X</button>
      </div>
    </div>
  </div>
</template>

<script>
import listService from "../services/ListService.js";
import birdService from "../services/BirdService.js";
import AddBird from "./AddBird";

export default {
  name: "list-detail",
  components: { AddBird },
  data() {
    return {
      showForm: false,
    };
  },
  computed: {
    listId() {
      return this.$store.state.activeList;
    },
    list() {
      return this.$store.state.lists.find(
        (l) => (l.listId = this.$store.state.activeList)
      );
    },
  },
  created() {
    birdService.getBirdsInList(this.listId).then((response) => {
      this.$store.commit("SET_BIRDS", response.data);
    });
  },
  methods: {
    deleteBird(birdId) {
      if (confirm("Are you sure you want to delete this bird?")) {
        birdService.deleteBird(birdId).then((response) => {
          if (response.status == 204) {
            this.$store.commit("DELETE_BIRD", birdId);
          }
        });
      }
    },
    updateName() {
      listService
        .editList(this.list)
        .then((response) => {
          if (response.status == 201) {
            this.$store.commit("EDIT_LIST", this.list);
          }
        })
        .catch((err) => {
          console.error(err + " problem editing list");
        });
      this.resetForm();
    },
    resetForm() {
      this.showForm = false;
    },
  },
};
</script>

<style>
#list-detail {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 10px;
}

h1 {
  font-size: 45px;
}
.birdInList {
  font-size: 20px;
  background-color: #ff9f1c;
  color: #fdfffc;
  border: 4px solid #ff9f1c;
  padding-left: auto;
  padding-right: auto;
  font-family: "Bitter", serif;
  font-size: 1.3rem;
  width: 30%;
  text-align: center;
  margin-bottom: 25px;
  margin-top: 25px;
}
#deleteButton {
  display: flex;
  /* position: relative; */
  top: 19px;
  right: 370px;
  height: 15px;
  padding: 5px 10px;
  font-weight: bold;
  border-radius: 50%;
  color: #e71d36;
  background-color: #fdfffc;
  border: 2px solid #ff9f1c;
}
</style>