<template>
  <div id="header-section">
    <div id="logo-img">
      <img src="@/assets/BirdNerdsLogo.png" alt="Official Bird Nerds logo" />
    </div>
    <div id="logo-text">
      <span>{{ logo }}</span>
      <a id="logout" v-show="$store.state.token != ''" v-on:click="logout">
        Logout
      </a>
    </div>
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
      this.$router.go(0);
    },
  },
};
</script>


<style scoped>
#header-section {
  display: flex;
  background-color: var(--rich-black);
  height: 150px;
}
#logo-img {
  display: flex;
  width: 20%;
  justify-content: center;
  background-color: var(--rose-madder);
  border: 1px solid var(--rich-black);
}
#logo-img > img {
  padding: 10px;
}
#logo-text {
  display: flex;
  flex-grow: 1;
  justify-content: space-around;
  align-items: center;
  border-left: 8px solid var(--baby-powder);
  color: var(--baby-powder);
}
#logo-text > span {
  font-size: 3rem;
}
a {
  padding: 18px;
  border-left: 1px solid var(--rose-madder);
}
a:hover {
  color: var(--orange-peel);
}
@media only screen and (max-width: 768px) {
  #header-section {
    height: 130px;
  }
  #logo-img {
    align-items: center;
  }
  #logo-img > img {
    width: 90%;
  }
  #logo-text > span {
    font-size: 2.5rem;
  }
  a {
  padding: 8px;
}
}
</style>