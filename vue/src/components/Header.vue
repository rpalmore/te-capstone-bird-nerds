<template>
  <div id="main">
    <div id="logo-img">
      <img src="..\assets\placeholderLogo.png" alt="Placeholder logo" />
    </div>
    <div id="logo-text">
      <span>{{ logo }}</span>
        <router-link
          v-bind:to="{ name: 'logout' }"
          v-if="$store.state.token != ''"
          >Logout</router-link
        >
    </div>
  </div>
</template>

<script>
export default {
  name: "header",
  data() {
    return {
      logo: "BIRD NERDS",
    };
  },
};
</script>


<style scoped>
#main {
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
  justify-content: space-between;
  align-items: center;
  padding-left: 18%;
  padding-right: 5%;
  border-left: 8px solid #fdfffc;
  color: #fdfffc;
  font-family: "Bitter", serif;
}
#logo-text > span {
  font-size: 3rem;
}
#logout {
  background-color: #ff9f1c;
  padding: 5px 8px 5px 8px;
  color: #011627;
  border: 1px solid #fdfffc;

}
a {
  text-decoration: none;
  color: #fdfffc;
  padding: 18px;
  border-left: 1px solid #E71D36;
}
</style>