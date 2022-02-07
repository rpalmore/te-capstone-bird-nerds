<template>
  <main>
    <h2>Create a List</h2>
    <form v-on:submit="createList">
      <label for="create-list">List Name: </label>
      <input id="create-list" type="text" required v-model="list.listName" />
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
      listService
        .createList(this.list)
        .then((response) => {
          if (response.status === 201) {
            // changed from "this.list"
            this.$store.commit("ADD_LIST", response.data);

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

<style scoped>
main {
  display: flex;
  flex-direction: column;
  align-items: center;
}
form {
  display: flex;
  padding-bottom: 35px;
  border-bottom: 3px solid #e71d36;
}
label {
  padding: 0 10px 0 10px;
  margin-right: 7px;
  display: flex;
  align-items: center;
  border: 1px solid #011627;
  border: 1px solid #e71d36;
}
input {
  padding: 8px;
}
input[type="submit"] {
  margin-left: 7px;
}
</style>