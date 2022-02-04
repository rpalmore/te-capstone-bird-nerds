<template>
  <div id="app">
    <SiteHeader />
    <LeftNav />
    <div id="main-content" v-if="$store.state.token != ''">
      <router-view />
    </div>
  </div>
</template>

<script>
import SiteHeader from "./components/SiteHeader.vue";
import LeftNav from "./components/LeftNav.vue";
export default {
  components: { SiteHeader, LeftNav },
};
</script>

<style>
#app {
  height: 100vh;
  background-color: #fdfffc;
}
#main-content {
  display: flex;
}
</style>
