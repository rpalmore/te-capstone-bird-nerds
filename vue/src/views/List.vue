<template>
  <div id="list-view">
    <ListForm></ListForm>
    <ListOfLists></ListOfLists>
  </div>
</template>

<script>
import ListForm from "../components/ListForm.vue";
import ListOfLists from "../components/ListOfLists.vue";
export default {
  name: "list",
  components: { ListForm, ListOfLists },
};
</script>

<style>
#list-view {
  display: flex;
  flex-direction: column;
  position: absolute;
  width: 48%;
  margin-bottom: 35px;
}
@media only screen and (max-width: 768px) {
  #list-view {
    position: revert;
    width: 80%;
  }
}
</style>