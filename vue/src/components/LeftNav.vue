<template>
  <div id="nav">
    <button id="btn-search">SEARCH</button>
    <button id="btn-login">
      <router-link v-bind:to="{ name: 'login' }"> LOGIN</router-link>
    </button>
    <button id="btn-register">REGISTER</button>
  </div>
</template>

<script>
export default {
  name: "left-nav",
};
</script>

<style scoped>
#nav {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 20%;
  border: 2px solid #011627;
  background-color: #2ec4b6;
  margin-top: 8px;
  height: 90%;
}
#nav > button {
  width: 80%;
  background-color: #011627;
  color: #fdfffc;
  border: 2px solid #ff9f1c;
  font-family: "Bitter", serif;
  font-size: 1.3rem;
  margin-top: 35px;
  /* gap: 15px; */
  padding: 10px;
}
#btn-login > a {
    text-decoration: none;
    color: #fdfffc;
}
</style>