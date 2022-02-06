<template>
  <main>
    <h2>Create List</h2>
    <form v-on:submit.prevent="createList">
      <br />

      <label for="create-list">List Name: </label>
      <input id="create-list" type="text" required v-model="list.listName" />
      <br />
      <input type="submit" />
    </form>
  </main>
</template>

<script>
import listService from "../services/ListService";
export default {
  data() {
    return {
      list: {
        // userId is handled in the backend -- we are passing in zero for now
        userId: 0,
      },
    };
  },
  methods: {
    createList() {
      console.log(this.list);
      listService.createList(this.list).then((response) => {
          if (response.status === 201) {
            this.$store.commit("ADD_LIST", this.list);
            console.log(this.$store.state.lists.length);
          }
        })
        .catch((err) => {
          console.error(err + " problem creating list!");
        });
    },
  },
};
</script>

<style>
</style>