<template>
  <router-link :to="{ name: 'list' }"
    ><button id="btn-lists">MY LISTS: {{ count }}</button>
  </router-link>
</template>

<script>
export default {
  name: "my-lists",
  computed: {
    count() {
      return this.$store.state.lists.length;
    },
  },
  methods: {
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
  margin-top: 70px;
  padding: 10px;
  border-radius: 0 !important;
}
a {
  display: flex;
  width: 100%;
  justify-content: center;
}
</style>