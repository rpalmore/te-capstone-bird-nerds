<template>
  <router-link :to="{ name: 'list' }"
    ><button id="btn-add-list">ADD LIST</button></router-link
  >
</template>

<script>
export default {
  name: "add-list",
  methods: {},
};
</script>

<style scoped>
#btn-add-list {
  margin-bottom: 35px;
  width: 80%;
  background-color: #011627;
  color: #fdfffc;
  border: 2px solid #ff9f1c;
  border: 2px solid #e71d36;
  font-size: 1.3rem;
  margin-top: 35px;
  padding: 10px;
}
a {
  display: flex;
  width: 100%;
  justify-content: center;
}
</style>