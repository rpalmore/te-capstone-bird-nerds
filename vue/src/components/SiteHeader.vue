<template>
  <div id="header">
    <div id="logo-img">
      <img src="..\assets\BirdNerdsLogo.png" alt="Placeholder logo" />
    </div>
    <div id="logo-text">
      <span>{{ logo }}</span>
    </div>
        <a id="logout" v-show="$store.state.token != ''" v-on:click="logout">
        Logout
      </a>
  </div>
</template>

<script>
export default {
  name: "site-header",
  data() {
    return {
      logo: "BIRD NERDS",
    };
  },
  methods: {
    logout() {
      this.$store.commit("LOGOUT");
      this.$router.push({ name: "Home" });
      // this will refresh page to collapse expanded left nav buttons/forms but creates a slight delay
      // this.$router.go(0);
    },
  },
};
</script>


<style scoped>
#header {
  display: flex;
  background-color: #011627;
  height: 150px;
}
#logo-img {
  display: flex;
  width: 20%;
  justify-content: center;
  background-color: #e71d36;
  border: 2px solid #011627;
}
#logo-img > img {
  padding: 10px;
}
#logo-text {
  display: flex;
  flex-grow: 1;
  justify-content: center;
  align-items: center;
  border-left: 8px solid #fdfffc;
  color: #fdfffc;
  font-family: "Bitter", serif;
}
#logo-text > span {
  font-size: 3rem;
  font-weight: normal !important;
}
a {
  display: flex;
  position: absolute;
  right: 25px;
  top: 57px;
  align-items: center;
  text-decoration: none;
  color: #fdfffc;
  padding: 18px;
  border-left: 1px solid #e71d36;
}
a:hover {
  color: #ff9f1c;
}
</style>