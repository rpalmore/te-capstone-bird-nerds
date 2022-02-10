<template>
  <div id="register" class="text-center">
    <form class="form-register" @submit.prevent="register">
      <span>Create an account</span>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
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
      <input
        type="password"
        id="confirmPassword"
        class="form-control"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      />
      <input type="submit" />
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "register",
  data() {
    return {
      user: {
        username: "",
        password: "",
        confirmPassword: "",
        role: "user",
      },
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user.",
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password & Confirm Password do not match.";
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: "/login",
                query: { registration: "success" },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = "Bad Request: Validation Errors";
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    },
  },
};
</script>

<style scoped>
#register {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 80%;
}
.form-register {
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
#register button,
input[type] {
  width: 80%;
  background-color: #011627;
  color: #fdfffc;
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
#confirmPassword {
  font-size: 1.1rem;
  padding: 12px;
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