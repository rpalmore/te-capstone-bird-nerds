<template>
  <div id="nav">
    <button id="btn-search">SEARCH</button>
    <button id="btn-login" v-on:click="toggleBtn($event)">
      <router-link v-bind:to="{ name: 'login' }">{{
        clicked == false ? "LOGIN" : "WELCOME BACK"
      }}</router-link>
    </button>
    <router-view />
    <!-- <Login /> -->
    <!-- </router-view> -->
    <button
      id="btn-register"
      v-bind:class="{ show: clicked === false }"
      v-on:click="toggleBtn($event)"
    >
      <router-link :to="{ name: 'register' }">REGISTER</router-link>
    </button>
  </div>
</template>

<script>
export default {
  name: "left-nav",
  data() {
    return {
      clicked: false,
      message: "",
    };
  },
  methods: {
    toggleBtn(event) {
      console.log("EVENT: " + event.target.id); // not returning any data
      if (this.clicked == false) {
        this.clicked = true;
      } else {
        this.clicked = false;
        this.$router.push("/");
      }
    },
  },
};
</script>

<style scoped>
#nav {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 20%;
  border: 2px solid #011627;
  background-color: #2ec4b6;
  margin-top: 8px;
  height: 90%;
}
#nav > button {
  width: 80%;
  background-color: #011627;
  color: #fdfffc;
  border: 2px solid #ff9f1c;
  font-family: "Bitter", serif;
  font-size: 1.3rem;
  margin-top: 35px;
  /* gap: 15px; */
  padding: 10px;
}
#btn-login > a,
#btn-register > a {
  text-decoration: none;
  color: #fdfffc;
}
.show {
  /* display: none; */
}
</style>