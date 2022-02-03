<template>
  <div id="login" class="text-center">
    <form class="form-signin" @submit.prevent="login">
      <!-- <h1 class="h3 mb-3 font-weight-normal">Please Sign In</h1> -->
      <div class="alert alert-danger" role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >
        Thank you for registering, please sign in.
      </div>
      <!-- <label for="username" class="sr-only"></label> -->
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <!-- <label for="password" class="sr-only">Password</label> -->
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <button type="submit">Sign in</button>
      <!-- <router-link :to="{ name: 'register' }">Need an account?</router-link> -->
    </form>
  </div>
</template>

<script>
import authService from "../../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
      invalidCredentials: false,
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
  },
};
</script>

<style scoped>
.form-signin {
  display: flex;
  flex-direction: column;
  gap: 15px;
  margin-top: 15px;
  background-color: #011627;
  color: #fdfffc;
  border: 2px solid #ff9f1c;
  font-family: "Bitter", serif;
  font-size: 1.3rem;
}
input[type] {
  text-align: center;
}
</style>
