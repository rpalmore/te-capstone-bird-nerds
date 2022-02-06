<template>
  <button id="btn-home" v-on:click="goHome">HOME</button>
</template>

<script>
export default {
  name: "go-home",
  methods: {
    goHome() {
      this.$router.push("/");
    }
  }
};
</script>

<style>
#btn-home {
  margin-bottom: 35px;
  width: 80%;
  background-color: #011627;
  color: #fdfffc;
  border: 2px solid #ff9f1c;
  border: 2px solid #e71d36;
  font-family: "Bitter", serif;
  font-size: 1.3rem;
  margin-top: 35px;
  padding: 10px;
}
</style>