<template>
  <div id="nav-well">
    <div id="sticky">
      <!-- Appears whether or not user is logged in -->
      <button id="btnSearch" class="nav-btn" v-on:click="toggleView($event)">
        SEARCH
      </button>
      <SearchBirds v-show="searchClick === true"></SearchBirds>

      <!-- Appear when user is not logged in -->
      <button
        v-if="$store.state.token === ''"
        class="nav-btn"
        id="btnLogin"
        v-on:click="toggleView($event)"
      >
        LOGIN
      </button>

      <Login
        v-if="
          (loginClick === true && $store.state.token === '') ||
          this.$route.query.registration
        "
      ></Login>

      <button
        v-if="$store.state.token === ''"
        class="nav-btn"
        id="btnRegister"
        v-on:click="toggleView($event)"
      >
        REGISTER
      </button>
      <Register
        v-if="registerClick === true && $store.state.token === ''"
      ></Register>

      <!-- Appear when user is logged in -->

      <MyLists v-if="$store.state.token != ''"></MyLists>

      <GoToProfile v-if="$store.state.token != ''"></GoToProfile>

      <button
        v-if="$store.state.token != ''"
        class="nav-btn"
        v-on:click="goHome"
      >
        HOME
      </button>
    </div>
  </div>
</template>

<script>
import Login from "./Navigation/Login.vue";
import SearchBirds from "./Navigation/SearchBirds.vue";
import Register from "./Navigation/Register.vue";
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
      this.$router.push("/");
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
    this.loginClick = false;
    this.registerClick = false;
    this.searchClick = false;
  },
};
</script>

<style>
#nav-well {
  display: flex;
  flex-direction: column;
  width: 20%;
  border: 1px solid var(--rich-black);
  background-color: var(--tiffany-blue);
  padding: 2px;
}
#sticky {
  display: flex;
  flex-direction: column;
  position: sticky;
  top: 0;
  align-items: center;
}
.nav-btn {
  width: 80%;
  background-color: var(--rich-black);
  color: var(--baby-powder);
  border-top: 3px solid var(--orange-peel);
  border-left: 2px solid var(--rose-madder);
  border-right: 2px solid var(--rose-madder);
  border-bottom: 2px solid var(--rose-madder);
  font-family: "Bitter", serif;
  font-size: var(--nav-btn);
  margin-top: 45px;
  padding: 10px;
}
.router {
  display: flex;
  width: 100%;
  justify-content: center;
}
.nav-form-display {
  display: flex;
  width: 80%;
}
.nav-form-body {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 18px 0 35px 0;
  background-color: var(--orange-peel);
  border-right: 2px solid var(--rose-madder);
  border-bottom: 2px solid var(--rose-madder);
  border-left: 2px solid var(--rose-madder);
}
.nav-form-body > input {
  width: 80%;
  margin-top: 35px;
  padding: 10px;
  text-align: center;
  border: 2px solid var(--rose-madder);
  background-color: var(--rich-black);
  color: var(--baby-powder);
  font-family: "Bitter", serif;
  font-size: var(--nav-btn);
  cursor: pointer;
}
.nav-form-body > input[type="text"],
input[type="password"] {
  width: 68%;
}
#confirmPassword {
  font-size: 0.95rem;
  padding: 12px;
}
.alert {
  text-align: center;
  padding-top: 10px;
}
span#my-lists {
  color: var(--rose-madder);
  padding-left: 10px;
  font-weight: normal;
  font-size: var(--nav-button);
}
@media only screen and (max-width: 768px) {
  #nav-well,
  #sticky {
    flex-direction: row;
    width: 100%;
    background-color: var(--tiffany-blue);
    padding: 0;
  }
  .nav-btn {
    margin-top: 0;
    width: 100%;
    cursor: pointer;
  }
  .nav-form-display {
    position: absolute;
    top: 96px;
    width: 100%;
    height: 100vh;
    justify-content: center;
    background-color: var(--rich-black);
    border-top: 1px solid var(--rich-black);
  }
}
@media only screen and (max-width: 420px) {
  #nav-well,
  #sticky {
    flex-wrap: wrap;
  }
  .nav-form-display {
    top: 160px;
  }
}
</style>