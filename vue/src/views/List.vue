<template>
  <div id="listForm">
      <ListForm></ListForm>
      <ListOfLists></ListOfLists>
  </div>    
</template>

<script>
import ListForm from '../components/ListForm.vue';
import ListOfLists from '../components/ListOfLists.vue';
export default {
    name: "list",
    components: { ListForm, ListOfLists },
}
</script>

<style>

</style>