<template>
  <div id="profile-container">
    <!-- src="/img/BirdSilhouette.83f473b2.png" -->
    <img
      id="profile-image"
      v-bind:src="selectImg"
      alt="User profile image"
    />
    <button v-on:click="useCloudinary">Add a photo</button>

    <h2>Welcome, {{ this.username }}!</h2>

    <a
      id="create"
      class="edit-btn"
      v-show="formDisplay === false"
      v-on:click="displayForm"
      >{{
        this.$store.state.profile.favoriteBird === undefined ||
        this.$store.state.profile.favoriteBird === null
          ? "Create your profile"
          : "Edit your profile"
      }}</a
    >

    <a
      id="cancel"
      class="edit-btn"
      v-show="formDisplay === true"
      v-on:click="cancelSubmit"
      >Cancel</a
    >

    <ProfileForm v-show="formDisplay"></ProfileForm>

    <ProfileDisplay v-show="profileDisplay === true"></ProfileDisplay>
  </div>
</template>

<script>
import ProfileForm from "../components/ProfileForm.vue";
import ProfileDisplay from "../components/ProfileDisplay.vue";
import PhotoService from "../services/PhotoService.js";

export default {
  name: "profile",
  components: { ProfileForm, ProfileDisplay },
  data() {
    return {
      formDisplay: false,
      profileDisplay: true,
      username:
        this.$store.state.user.username.substring(0, 1).toUpperCase() +
        this.$store.state.user.username.substring(1).toLowerCase(),
        selectImg: this.$store.state.profile.profileImg === undefined ? "/img/BirdSilhouette.83f473b2.png" : this.$store.state.profile.profileImg,
    };
  },
  methods: {
    useCloudinary() {
      PhotoService.myWidget.open();
    },
    displayForm() {
      this.formDisplay = true;
      this.profileDisplay = false;
    },
    cancelSubmit() {
      this.formDisplay = false;
      this.profileDisplay = true;
    },
  },
};
</script>

<style>
#profile-container {
  display: flex;
  flex-direction: column;
  margin-top: 45px;
  align-items: center;
  gap: 25px;
  margin-bottom: 75px;
}
.edit-btn {
  width: 25%;
  background-color: var(--rich-black);
  color: var(--baby-powder);
  border-top: 3px solid var(--orange-peel);
  border-left: 2px solid var(--rose-madder);
  border-right: 2px solid var(--rose-madder);
  border-bottom: 2px solid var(--rose-madder);
  font-size: var(--edit-btn);
  text-align: center;
  padding: 10px;
}
.profile-content-container {
  border-top: 4px solid var(--orange-peel);
  border-right: 4px solid var(--rose-madder);
  border-bottom: 4px solid var(--rose-madder);
  border-left: 4px solid var(--rose-madder);
  padding: 25px 25px 35px 25px;
  background-color: var(--rich-black);
  color: var(--baby-powder);
  width: 65%;
}
#profile-image {
  border-radius: 50%;
  width: 25%;
  height: auto;
  padding: 10px;
  background-color: var(--orange-peel);
  border: 8px solid var(--rose-madder);
}
/* ProfileForm.vue CSS */
.profile-content-container form {
  display: flex;
  flex-direction: column;
  gap: 12px;
  align-items: center;
}
.profile-content-container label {
  font-size: var(--nav-btn);
}
.profile-content-container input[type="zip-code"] {
  width: 45%;
}
.profile-content-container input,
select {
  padding: 8px;
  border-radius: 8px;
  font-size: var(--edit-btn);
  border: 1px solid var(--rich-black);
  border-left: 5px solid var(--orange-peel);
  border-right: 5px solid var(--orange-peel);
}
.profile-content-container option {
  font-family: "Bitter", serif;
}
.profile-content-container input[type="submit"] {
  margin-top: 25px;
  /* width: 45%; */
  width: 25%;
  border: 1px solid var(--rich-black);
  background-color: var(--rose-madder);
  font-size: var(--edit-btn);
  font-weight: bold;
  font-family: "Bitter", serif;
  color: var(--baby-powder);
  border-radius: 8px;
  border-left: 5px solid var(--orange-peel);
  border-right: 5px solid var(--orange-peel);
}
/* ProfileDisplay.vue CSS */
#profile-box {
  text-align: center;
  padding-bottom: 25px;
}
#profile-box > p:first-child {
  padding-top: 0;
}
.question {
  padding-top: 25px;
  font-size: 1.3rem;
}
.response {
  padding-bottom: 10px;
  color: var(--orange-peel);
  border-bottom: 1px solid var(--rose-madder);
  font-style: italic;
  font-size: 1.3rem;
}
</style>