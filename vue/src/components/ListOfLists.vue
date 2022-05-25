<template>
  <div id="list-of-lists">
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
        listService
          .deleteList(listId)
          .then((response) => {
            if (response.status == 204) {
              this.$store.commit("DELETE_LIST", listId);
            }
          })
          .catch((err) => {
            alert(err + " problem deleting list!");
          });
      }
    },
  },
  created() {
    listService.getAllLists().then((response) => {
      if (response.status == 200) {
        this.$store.commit("SET_LISTS", response.data);
      }
    });
  },
};
</script>

<style>
#list-of-lists {
  display: flex;
  flex-direction: column;
  margin-top: 20px;
  gap: 21px;
  align-items: center;
}
#listColumn {
  /* display: flex;
  width: 100%;
  align-items: center; */

  display: flex;
  /* margin-top: 35px; */
  align-items: center;
  width: 100%;
  background-color: var(--rich-black);
  color: var(--baby-powder);
  padding: 15px 15px 15px 25px;
  border-radius: 12px;
  border-left: 5px solid var(--orange-peel);
  border-right: 5px solid var(--orange-peel);
}
#listItem {
  font-size: 1.3rem;
}
#anchor {
  text-decoration: none;
  flex-grow: 1;
}
#delete {
  display: flex;
  position: relative;
  top: 0;
  padding: 5px 10px;
  font-weight: bold;
  border-radius: 50%;
  color: var(--rose-madder);
  background-color: var(--baby-powder);
  border: 2px solid var(--orange-peel);
}
</style>