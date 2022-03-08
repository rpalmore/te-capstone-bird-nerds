<template>
  <router-link :to="{ name: 'list' }" class="router"
    ><button class="nav-btn">LISTS<span id="my-lists">{{ count }}</span></button>
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
};
</script>

<style scoped>
/* CSS for component in LeftNav.vue */
</style>