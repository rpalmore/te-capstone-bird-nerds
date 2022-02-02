<template>
  <div id="app">
    <Header />
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
    <router-view />
  </div>
</template>

<script>
import Header from './components/Header.vue';
export default {
  components: { Header },
}
</script>

<style>
#app {
  height: 100vh;
  background-color: #FDFFFC;
}

</style>
