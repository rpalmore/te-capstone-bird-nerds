<template>
  <main id="list-form">
    <h2>Create a List</h2>
    <form v-on:submit.prevent="createList">
      <input
        id="create-list"
        type="text"
        placeholder="Add a list name"
        required
        v-model="list.listName"
      />
      <button id="submit">Go</button>
    </form>
  </main>
</template>

<script>
import listService from "../services/ListService";
export default {
  data() {
    return {
      list: {
        userId: 0,
      },
    };
  },
  methods: {
    createList() {
      listService
        .createList(this.list)
        .then((response) => {
          if (response.status == 201) {
            this.$store.commit("ADD_LIST", response.data);
          }
          // Clear the form after we commit the data submitted
          this.list = {};
        })
        .catch((err) => {
          alert(err + " problem creating list!");
        });
    },
  },
};
</script>

<style>
main#list-form {
  display: flex;
  flex-direction: column;
  align-items: center;
}
main#list-form > h2 {
  margin-top: 45px;
}
main#list-form > form {
  display: flex;
  width: 100%;
  justify-content: space-between;
}
main#list-form input {
  width: 90%;
  padding: 8px;
  border-radius: 8px;
  font-size: 1.2rem;
  font-family: "Bitter", serif;
  border: 1px solid var(--rich-black);
  border-left: 5px solid var(--orange-peel);
  border-right: 5px solid var(--orange-peel);
}
main#list-form button#submit {
  margin-left: 7px;
  border: 1px solid var(--rich-black);
  background-color: var(--rose-madder);
  font-size: var(--edit-btn);
  font-weight: bold;
  font-family: "Bitter", serif;
  color: var(--baby-powder);
  border-radius: 8px;
  border-left: 5px solid var(--orange-peel);
  border-right: 5px solid var(--orange-peel);
}
</style>