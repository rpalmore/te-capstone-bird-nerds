<!-- This component is for display a specific list + birds in that list (non-detailed view) -->
<template>
  <div id="list-detail">
    <span id="add-bird">Add a bird to: {{ list.listName }}</span>
    <a
      id="rename-list"
      href="#"
      v-if="showForm === false"
      v-on:click.prevent="showForm = true"
      >Rename</a
    >
    <form
      class="new-name"
      v-on:submit.prevent="updateName"
      v-show="showForm === true"
    >
      <label for="newName">New name: &nbsp;</label>
      <input id="newName" type="text" v-model="list.listName" />
      <input type="submit" value="Save" />
      <input type="button" value="Cancel" v-on:click.prevent="resetForm" />
    </form>

    <add-bird />
    <div
      class="birdInList"
      v-for="bird in this.$store.state.birds"
      v-bind:key="bird.birdID"
    >
      <router-link
        id="birdAnchor"
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
      <a id="deleteBird" v-on:click="deleteBird(bird.birdID)">&#10006;</a>
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
          if (response.status == 200) {
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

<style scoped>
#list-detail {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 10px;
}
#add-bird {
  font-size: 35px;
  font-weight: bold;
  margin-top: 35px;
}
#rename-list,
.new-name {
  margin-bottom: 25px;
  margin-top: 25px;
  padding: 8px;
  background-color: #011627;
  color: #fdfffc;
  border-right: 4px solid #ff9f1c;
  border-left: 4px solid #ff9f1c;
  text-decoration: none;
  font-size: 1.3rem;
}
.new-name {
  display: flex;
  gap: 10px;
}
#birdAnchor {
  text-decoration: none;
  flex-grow: 1;
  color: #fdfffc;
}
.birdInList {
  display: flex;
  margin-top: 35px;
  align-items: center;
  width: 100%;
  background-color: #011627;
  color: #fdfffc;
  padding: 15px 15px 15px 25px;
  font-size: 1.5rem;
  border-radius: 20px;
  border-left: 5px solid #ff9f1c;
  border-right: 5px solid #ff9f1c;
}
#deleteBird {
  display: flex;
  position: relative;
  top: 0;
  right: 30px;
  padding: 5px 10px;
  font-weight: bold;
  border-radius: 50%;
  color: #e71d36;
  background-color: #fdfffc;
  border: 2px solid #ff9f1c;
}
</style>