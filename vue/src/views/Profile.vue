<template>
  <div id="profile-container">
    <div id="image-section">
      <img id="profile-image" v-bind:src="selectImg" alt="User profile image" />
      <div id="image-overlay" v-on:click="useCloudinary">
        <a id="cloudinary-link">Update image</a>
      </div>
    </div>
    <h2>Welcome, {{ this.username }}!</h2>
    <a
      id="create"
      class="edit-btn"
      v-show="this.formDisplay === false"
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
      v-show="this.formDisplay === true"
      v-on:click="cancelSubmit"
      >Cancel</a
    >
    <ProfileForm v-show="this.formDisplay === true"></ProfileForm>

    <ProfileDisplay v-show="this.profileDisplay === true"></ProfileDisplay>
  </div>
</template>

<script>
import ProfileForm from "../components/ProfileForm.vue";
import ProfileDisplay from "../components/ProfileDisplay.vue";
import photoService from "../services/PhotoService.js";

export default {
  name: "profile",
  components: { ProfileForm, ProfileDisplay },
  data() {
    return {
      username:
        this.$store.state.user.username.substring(0, 1).toUpperCase() +
        this.$store.state.user.username.substring(1).toLowerCase(),
    };
  },
  computed: {
    selectImg() {
      return this.$store.state.profile.profileImg === undefined ||
        this.$store.state.profile.profileImg === null
        ? "/img/BirdSilhouette.83f473b2.png"
        : this.$store.state.profile.profileImg;
    },
    formDisplay() {
      return this.$store.state.formDisplay;
    },
    profileDisplay() {
      return this.$store.state.profileDisplay;
    },
  },
  methods: {
    useCloudinary() {
      photoService.myWidget.open();
      this.$store.commit("SET_SOURCE_PROFILE");
    },
    displayForm() {
      this.$store.state.formDisplay = true;
      this.$store.state.profileDisplay = false;
    },
    cancelSubmit() {
      this.$store.state.formDisplay = false;
      this.$store.state.profileDisplay = true;
    },
  },
};
</script>

<style>
#profile-container {
  display: flex;
  flex-direction: column;
  width: 90%;
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
#image-section {
  display: flex;
  justify-content: center;
  align-items: center;
}
#profile-image {
  border-radius: 50%;
  transition: 0.5s ease;
  width: 38%;
  height: auto;
  padding: 5px;
  background-color: var(--orange-peel);
  border: 4px solid var(--rose-madder);
}
#image-overlay {
  position: absolute;
}
#image-section:hover #profile-image {
  opacity: 0.5;
}
#cloudinary-link {
  transition: 0.5s ease;
  opacity: 0;
  padding: 15px;
  background-color: var(--rich-black);
  border: 1px solid var(--rose-madder);
  border-top: 1px solid var(--orange-peel);
  color: var(--baby-powder);
}
#image-section:hover #cloudinary-link {
  opacity: 1;
}
/* ProfileForm.vue CSS */
.profile-content-container form {
  display: flex;
  flex-direction: column;
  gap: 15px;
  padding: 25px;
  align-items: center;
}
.profile-content-container label {
  font-size: var(--nav-btn);
  text-align: center;
}
.profile-content-container input,
select {
  width: 80%;
  padding: 8px;
  border-radius: 8px;
  font-size: var(--edit-btn);
  border: 1px solid var(--rich-black);
  border-left: 5px solid var(--orange-peel);
  border-right: 5px solid var(--orange-peel);
}
.profile-content-container #skill-lvl,
#zip-code {
  width: 40%;
}
.profile-content-container option {
  font-family: "Bitter", serif;
}
.profile-content-container button#submit {
  margin-top: 25px;
  width: 25%;
  padding: 5px;
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
@media only screen and (max-width: 768px) {
  #profile-image {
    width: 60%;
  }
  .edit-btn {
    width: auto;
  }
  .profile-content-container form {
    padding: 5px;
  }
}
@media only screen and (max-width: 420px) {
  #profile-image {
    width: 75%;
  }
  .profile-content-container #skill-lvl,
  #zip-code {
    width: 75%;
  }
  .profile-content-container button#submit {
    width: auto;
  }
}
</style>