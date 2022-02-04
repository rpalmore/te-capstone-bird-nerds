<template>
  <div id="nav-well" v-if="$store.state.token === ''">
    <router-view />
  </div>
  <!-- use v-if or v-show depending on current page (which is in store) -->
  <!-- when a component loads, on the created() we set an attribute in the store -->
  <!-- nav: if this page is loaded, display the following options -->
  <!-- each component has its own nav with its own router links -->
</template>

<script>
export default {
  name: "left-nav",
};
</script>

<style scoped>
#nav-well {
  display: flex;
  flex-direction: column;
  width: 20%;
  border: 2px solid #011627;
  background-color: #2ec4b6;
  margin-top: 8px;
  height: 90%;
}
</style>