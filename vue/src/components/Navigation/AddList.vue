<template>
  <button id="btn-add-list" v-on:click="createList">ADD LIST</button>
</template>

<script>
export default {
  name: "add-list",
  methods: {
    createList() {
      this.$router.push("/lists");
    }
  }
};
</script>

<style>
#btn-add-list {
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