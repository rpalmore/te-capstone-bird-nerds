<template>
  <div id="login" class="text-center">
    
    <button id="btn-search">SEARCH</button>
    <form class="form-signin" @submit.prevent="login">
      <span>Welcome back!</span>
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
      <input type="submit" />
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
            this.$router.push("/profile");
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
#login {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.form-signin {
  display: flex;
  flex-direction: column;
  /* gap: 30px; */
  /* margin-top: 30px; */
  align-items: center;
}
input[type] {
  text-align: center;
  border: 2px solid #ff9f1c;
  /* background-color: red; */
}
#login button,
input[type] {
  width: 80%;
  background-color: #011627;
  color: #fdfffc;
  border: 2px solid #ff9f1c;
  font-family: "Bitter", serif;
  font-size: 1.3rem;
  margin-top: 35px;
  padding: 10px;
}
input[type=text], input[type=password] {
  width: 70%;
}
#btn-search {
  margin-bottom: 35px;
}
span {
  color: #011627;
  font-family: "Bitter", serif;
  font-size: 1.3rem;
}
</style>
