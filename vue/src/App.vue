<template>
  <div id="app">
    <SiteHeader />
    <LeftNav />
    <div>
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <!-- <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link> -->
    </div>
    <router-view />
  </div>
</template>

<script>
import SiteHeader from './components/SiteHeader.vue';
import LeftNav from './components/LeftNav.vue';
export default {
  components: { SiteHeader, LeftNav },
}
</script>

<style>
#app {
  height: 100vh;
  background-color: #FDFFFC;
}

</style>
