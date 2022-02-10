<template>
  <router-link :to="{ name: 'profile' }"
    ><button id="btn-profile">PROFILE</button>
  </router-link>
</template>

<script>
export default {
  name: "go-to-profile",
  methods: {
  },
};
</script>

<style scoped>
#btn-profile {
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
  border-radius: 0 !important;
}
a {
  display: flex;
  width: 100%;
  justify-content: center;
  text-decoration: none;
}
</style>