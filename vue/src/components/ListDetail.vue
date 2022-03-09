<template>
  <div id="list-detail">
    <h2 id="add-bird">Add a bird to: {{ list.listName }}</h2>
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
      activeList: this.$route.params.listId,
    };
  },
  computed: {
    lists() {
      return this.$store.state.lists;
    },
    listId() {
      return this.activeList;
    },
    list() {
      return this.lists.find((l) => l.listId == this.activeList);
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
          alert(err + " problem editing list");
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
}
#add-bird {
  margin-top: 45px;
}
#rename-list,
.new-name {
  margin-bottom: 35px;
  padding: 3px 8px 3px 8px;
  background-color: var(--rich-black);
  color: var(--baby-powder);
  border-right: 4px solid var(--orange-peel);
  border-left: 4px solid var(--orange-peel);
  border-radius: 8px;
  text-decoration: none;
}
.new-name {
  display: flex;
  gap: 10px;
  border-radius: 8px;
}
.new-name input[type="submit"],
.new-name input[type="button"] {
  background-color: var(--orange-peel);
  border: 1px solid var(--rose-madder);
  border-right: 5px solid var(--rose-madder);
  border-left: 5px solid var(--rose-madder);
  border-radius: 8px;
  font-weight: bold;
  color: var(--rich-black);
}
#birdAnchor {
  text-decoration: none;
  flex-grow: 1;
  color: var(--baby-powder);
}
.birdInList {
  display: flex;
  margin-top: 35px;
  align-items: center;
  width: 80%;
  background-color: var(--rich-black);
  color: var(--baby-powder);
  padding: 15px 15px 15px 25px;
  border-radius: 12px;
  border-left: 5px solid var(--orange-peel);
  border-right: 5px solid var(--orange-peel);
}
#deleteBird {
  display: flex;
  position: relative;
  top: 0;
  right: 30px;
  padding: 5px 10px;
  font-weight: bold;
  border-radius: 50%;
  color: var(--rose-madder);
  background-color: var(--baby-powder);
  border: 2px solid var(--orange-peel);
}
@media only screen and (max-width: 768px) {
  #deleteBird {
    position: revert;
  }
}
</style>