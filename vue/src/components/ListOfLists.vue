<template>
  <div id="all-lists">
       <div v-bind:list="list" v-for="list in $store.state.lists" v-bind:key="list.id">
       <h2>{{ list.listName }}</h2>
       </div>
    
  </div>
</template>


<script>
export default {
  name: "list-of-lists",
};
</script>

<style>
</style>