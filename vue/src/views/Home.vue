<template>
  <div id="nav">
    <button id="btn-search">SEARCH</button>

    <button class="btn">
      <router-link id="btn-login" v-bind:to="{ name: 'login' }"
        >LOGIN</router-link
      >
    </button>

    <button class="btn">
      <router-link id="btn-register" :to="{ name: 'register' }"
        >REGISTER</router-link
      >
    </button>
  </div>
</template>

<script>
export default {
  name: "home",
};
</script>
<style scoped>
#nav {
  display: flex;
  flex-direction: column;
  align-items: center;
}
#nav button {
  width: 80%;
  background-color: #011627;
  color: #fdfffc;
  border: 2px solid #ff9f1c;
  font-family: "Bitter", serif;
  font-size: 1.3rem;
  margin-top: 35px;
  padding: 10px;
}
.btn > a {
  text-decoration: none;
  color: #fdfffc;
}
</style>
