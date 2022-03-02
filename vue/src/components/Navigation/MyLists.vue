<template>
  <router-link :to="{ name: 'list' }" class="rtr"
    ><button class="nav-btn">MY LISTS<span id="my-lists">{{ count }}</span></button>
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
  methods: {},
};
</script>

<style scoped>
/* CSS for component in LeftNav.vue */
</style>