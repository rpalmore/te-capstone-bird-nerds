<template>
  <div id="all-lists">
    <h2>My Lists</h2>
    <div
      id="listColumn"
      v-bind:list="list"
      v-for="list in lists"
      v-bind:key="list.listId"
    >
      <router-link
        id="anchor"
        :to="{ name: 'list-detail', params: { listId: list.listId } }"
        ><div id="listItem">
          {{ list.listName }}
        </div></router-link
      >
      <a id="delete" v-on:click="deleteList(list.listId)">&#10006;</a>
    </div>
  </div>
</template>


<script>
import listService from "../services/ListService.js";

export default {
  name: "list-of-lists",
  computed: {
    lists() {
      return this.$store.state.lists;
    },
  },
  methods: {
    deleteList(listId) {
      if (confirm("Are you sure you want to delete this list?")) {
        console.log(listId);
        listService
          .deleteList(listId)
          .then((response) => {
            if (response.status == 204) {
              this.$store.commit("DELETE_LIST", listId);
            }
          })
          .catch((err) => {
            console.error(err + " problem deleting list!");
          });
      }
    },
  },
};
</script>

<style>
#all-lists {
  display: flex;
  flex-direction: column;
  margin-top: 20px;
  gap: 21px;
  align-items: center;
}
#listColumn {
  display: flex;
  width: 100%;
}
#listItem {
  display: flex;
  background-color: #011627;
  color: #fdfffc;
  padding: 20px 20px 20px 30px;
  font-size: 1.5rem;
  border-radius: 20px;
  border-left: 5px solid #ff9f1c;
  border-right: 5px solid #ff9f1c;
}
#anchor {
  text-decoration: none;
  flex-grow: 1;
}
#delete {
  display: flex;
  position: relative;
  top: 19px;
  right: 70px;
  height: 22px;
  padding: 5px 10px;
  font-weight: bold;
  border-radius: 50%;
  color: #e71d36;
  background-color: #fdfffc;
  border: 2px solid #ff9f1c;
}
h2{
  font-size: 1.8rem;
}
</style>