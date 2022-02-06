<template>
  <button id="btn-lists">MY LISTS: {{ count }}</button>
</template>

<script>
export default {
  name: "my-lists",
  computed: {
    count() {
      return this.$store.state.lists.length;
    },
  },
};
</script>

<style scoped>
#btn-lists {
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