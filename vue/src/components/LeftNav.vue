<template>
  <div id="nav-well">
    <!-- SEARCH appears whether or not user is logged in -->
    <button id="btnSearch" class="btn" v-on:click="toggleView($event)">
      SEARCH
    </button>
    <SearchBirds v-if="searchClick === true"></SearchBirds>

    <!-- LOGIN btn appears if user IS NOT logged in -->
    <button
      v-if="$store.state.token === ''"
      class="btn"
      id="btnLogin"
      v-on:click="toggleView($event)"
    >
      LOGIN
    </button>

    <!-- LOGIN form appears if click is true AND user IS NOT logged in 
         OR if user has just successfully registered -->
    <Login
      v-if="
        (loginClick === true && $store.state.token === '') ||
        this.$route.query.registration
      "
    ></Login>

    <!-- REGISTER btn appears if user IS NOT logged in -->
    <button
      v-if="$store.state.token === ''"
      class="btn"
      id="btnRegister"
      v-on:click="toggleView($event)"
    >
      REGISTER
    </button>
    <Register
      v-if="registerClick === true && $store.state.token === ''"
    ></Register>

    <!-- MY LISTS appears if user IS logged in -->
    <MyLists v-if="$store.state.token != ''"></MyLists>

    <!-- GO TO PROFILE appears if user IS logged in -->
    <GoToProfile v-if="$store.state.token != ''"></GoToProfile>

    <!-- HOME appears only if user IS logged in -->
    <button
      v-if="$store.state.token != ''"
      class="btn"
      v-on:click="goHome"
    >
      HOME
    </button>
    
  </div>
</template>

<script>
import Login from "./Navigation/Login.vue";
import SearchBirds from "./Navigation/SearchBirds.vue";
import Register from "../views/Register.vue";
import MyLists from "./Navigation/MyLists.vue";
import GoToProfile from "./Navigation/GoToProfile.vue";

export default {
  name: "left-nav",
  components: { Login, SearchBirds, Register, MyLists, GoToProfile },
  data() {
    return {
      loginClick: false,
      registerClick: false,
      searchClick: false,
    };
  },
  methods: {
    goHome() {
      this.$router.push('/');
    },
    toggleView(event) {
      if (event.target.id === "btnLogin") {
        this.loginClick === false
          ? (this.loginClick = true)
          : (this.loginClick = false);
      }
      if (event.target.id === "btnRegister") {
        this.registerClick === false
          ? (this.registerClick = true)
          : (this.registerClick = false);
      }
      if (event.target.id === "btnSearch") {
        this.searchClick === false
          ? (this.searchClick = true)
          : (this.searchClick = false);
      }
    },
  },
  created() {
    this.loginClick = false
    this.registerClick = false
    this.searchClick = false
  }
};
</script>

<style scoped>
#nav-well {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 20%;
  border: 2px solid #011627;
  background-color: #2ec4b6;
  padding: 2px;
}
.btn {
  width: 80%;
  background-color: #011627;
  color: #fdfffc;
  border: 2px solid #ff9f1c;
  border: 2px solid #e71d36;
  font-family: "Bitter", serif;
  font-size: 1.3rem;
  margin-top: 45px;
  padding: 10px;
  border-radius: 0 !important;
}
a {
  text-decoration: none;
}
</style>