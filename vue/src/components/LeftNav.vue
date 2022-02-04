<template>
    <div id="nav-well">
      <!-- to do: search for birds by zip -->
      <SearchBirds />
      <button v-if="$store.state.token === ''" class="btn" id="btnLogin" v-on:click="toggleView($event)">
        LOGIN
      </button>
      <Login v-if="loginClick === true && $store.state.token === ''"></Login>
      <button v-if="$store.state.token === ''" class="btn" id="btnRegister" v-on:click="toggleView($event)">
        REGISTER
      </button>
      <Register v-if="registerClick === true && $store.state.token === ''"></Register>
    </div>
</template>

<script>
import Login from "./Navigation/Login.vue";
import SearchBirds from "./Navigation/SearchBirds.vue";
import Register from "../views/Register.vue";
export default {
  name: "left-nav",
  components: { Login, SearchBirds, Register },
  data() {
    return {
      loginClick: false,
      registerClick: false,
    };
  },
  methods: {
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
    },
  },
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
}
</style>