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
        :to="{ name: 'list-detail', params: { listId: list.listId } }"
        ><div id="listItem">{{ list.listName }}</div></router-link
      >
      <button v-on:click="deleteList(list.listId)">Delete</button>
    </div>
  </div>
</template>


<script>
import listService from '../services/ListService.js';

export default {
  name: "list-of-lists",
  computed: {
    lists() {
      return this.$store.state.lists;
    }
  },
  methods: {
    deleteList(listId) {
      
      if (confirm("Are you sure you want to delete this list?")) {
      listService.deleteList(listId)
        .then( response => {
          if (response.status == 204) {
            this.$store.commit("DELETE_LIST", listId);
          }
        }).catch((err) => {
          console.error(err + " problem deleting list!");
        });
      }
    }
  }
};
</script>

<style>
#all-lists {
  display: flex;
  flex-direction: column;
  margin-top: 40px;
  gap: 21px;
  align-items: center;
}
#listColumn {
  width: 100%;
  text-align: center;
  border-left: 5px solid #ff9f1c;
  border-right: 5px solid #ff9f1c;
  border-radius: 20px;
}
#listItem {
  background-color: #011627;
  color: #fdfffc;
  padding: 20px;
  font-size: 1.5rem;
  border-radius: 20px;
}
a {
  text-decoration: none;
}
#horizontal {
  /* height: auto; */
}
</style>