<template>
  <div id="login" class="text-center">
    <form class="form-signin" @submit.prevent="login">
      <span>{{ this.$route.query.registration ? "Please sign in" : "Welcome back!" }}</span>
      <div class="alert alert-danger" role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <input
        type="password"
        id="password"
        class="form-control"
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
import listService from "../../services/ListService";

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
            listService.getAllLists().then((response) => {
              if (response.status == 200) {
                this.$store.commit("SET_LIST", response.data);
              }
            });
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
#login {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 80%;
}
.form-signin {
  display: flex;
  flex-direction: column;
  width: 100%;
  align-items: center;
  padding: 18px 0 35px 0;
  background-color: #ff9f1c;
  border: 2px solid #e71d36;

}
input[type] {
  text-align: center;
  border: 2px solid #ff9f1c;
  border: 2px solid #e71d36;
}
#login button,
input[type] {
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
input[type="text"],
input[type="password"] {
  width: 70%;
}
span {
  color: #011627;
  font-family: "Bitter", serif;
  font-size: 1.3rem;
  font-weight: bold;
  border-bottom: 2px solid #fdfffc;
  padding-bottom: 18px;
  text-align: center;
}
.alert {
  text-align: center;
  padding-top: 10px;
}
</style>
