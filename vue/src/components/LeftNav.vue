<template>
  <div id="nav">
    <button id="btn-search">SEARCH</button>

    <button
      class="btn"
      v-bind:class="{ toggleDisplay: hideLogin === true }"
      v-on:click="toggleBtn($event)"
    >
      <router-link id="btn-login" v-bind:to="{ name: 'login' }">{{
        clicked == false ? "LOGIN" : "WELCOME BACK"
      }}</router-link>
    </button>
    <router-view />
    <!-- <Login /> -->
    <!-- </router-view> -->
    <button
      class="btn"
      v-bind:class="{ toggleDisplay: hideReg === true }"
      v-on:click="toggleBtn($event)"
    >
      <router-link id="btn-register" :to="{ name: 'register' }"
        >REGISTER</router-link
      >
    </button>
  </div>
</template>

<script>
export default {
  name: "left-nav",
  data() {
    return {
      clicked: false,
      hideReg: false,
      hideLogin: false,
      message: "",
    };
  },
  methods: {
    toggleBtn(event) {
      if (event.target.id === "btn-login") {
        if (this.hideReg === false) {
          this.hideReg = true;
        }
        if (this.clicked === false) {
          this.clicked = true;
        } else {
          this.clicked = false;
          this.hideReg = false;
          this.$router.push("/");
        }
      } else {
        if (event.target.id === "btn-register") {
          if (this.hideLogin === false) {
            this.hideLogin = true;
          }
          if (this.clicked === false) {
            this.clicked = true;
          } else {
            this.clicked = false;
            this.hideLogin = false;
            this.$router.push("/");
          }
        }
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
.btn > a {
  text-decoration: none;
  color: #fdfffc;
}
.toggleDisplay {
  display: none;
}
</style>