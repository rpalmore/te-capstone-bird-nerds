<template>
  <main>
      <h2>Create List</h2>
      <form v-on:submit.prevent="createList">
      <br/>

      <label for="create-list">List Name: </label>
      <input id="create-list" type="text" v-model="list.listName"/>
      <br/>
      <input type="submit" />
     </form> 
  </main>
</template>

<script>
import listService from '../services/ListService';
export default {
    
    data() {
        return {
            list: {
                 //username: this.$store.state.user.username,
                 userId: 0,
            }
        }
    },
methods: {    
createList() {
        console.log(this.list);
        listService.createList(this.list).then((response) => {

            if (response.status === 200) {
                alert("list created!");
            }
        })
        .catch((err) => {
            console.error(err + " problem creating list!");
        });
    },
}
};

</script>

<style>

</style>