<template>
  <div id="nav-well">
    <router-view />
  </div>
</template>

<script>
export default {
  name: "left-nav",
  data() {
    return {
      // clicked: false,
    };
  },
  methods: {},
};
</script>

<style scoped>
#nav-well {
  display: flex;
  flex-direction: column;
  width: 20%;
  border: 2px solid #011627;
  background-color: #2ec4b6;
  margin-top: 8px;
  height: 90%;
}

</style>