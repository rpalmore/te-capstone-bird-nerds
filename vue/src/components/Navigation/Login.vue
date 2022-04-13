<template>
  <div class="nav-form-display">
    <form class="nav-form-body" @submit.prevent="login">
      <span class="helper-text">{{
        this.$route.query.registration ? "Please sign in" : "Welcome back!"
      }}</span>
      <div class="alert alert-danger" role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <input
        type="text"
        id="username"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <input
        type="password"
        id="password"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <input type="submit" />
    </form>
  </div>
</template>

<script>
import authService from "../../services/AuthService";
import profileService from "../../services/ProfileService";

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
            profileService.getProfile().then((response) => {
              if (response.status == 200) {
                this.$store.commit("SET_PROFILE", response.data);
                if (this.$store.state.profile.favoriteBird != undefined) {
                  this.$router.push("/lists");
                } else {
                  this.$router.push("/profile");
                }
              }
            });
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
/* CSS for component in LeftNav.vue */
</style>
