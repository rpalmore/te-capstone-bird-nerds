<template>
  <div id="profile">
    <img
      id="birdPlaceholder"
      src="..\assets\BirdSiloutette.png"
      alt="Bird silhouette"
    />
    <h2 class="welcome">Welcome, {{ this.$store.state.user.username }}! </h2>
    <a
      id="create"
      class="createProfile"
      v-show="formDisplay === false"
      v-on:click="displayForm"
      >{{
        this.$store.state.profile.favoriteBird === undefined
          ? "Create your profile"
          : "Edit your profile"
      }}</a
    >
    <ProfileForm
      v-show="
        formDisplay === true && this.$store.state.profile.favoriteBird === undefined
      "
    ></ProfileForm>

    <a id="cancel" v-show="formDisplay === true" v-on:click="cancelSubmit"
      >Cancel</a
    >

    <ProfileFormEdit
      v-show="
        formDisplay === true &&
        this.$store.state.profile.favoriteBird !== undefined
      "
    ></ProfileFormEdit>
    
    <ProfileDisplay v-show="profileDisplay === true"></ProfileDisplay>
  </div>
</template>

<script>
import ProfileForm from "../components/ProfileForm.vue";
import ProfileFormEdit from "../components/ProfileFormEdit.vue";
import ProfileDisplay from "../components/ProfileDisplay.vue";
export default {
  name: "profile",
  components: { ProfileForm, ProfileDisplay, ProfileFormEdit },
  data() {
    return {
      formDisplay: false,
      profileDisplay: true,
    };
  },
  methods: {
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

<style scoped>
#profile {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 2%;
  gap: 25px;
  margin-bottom: 75px;
}
.welcome {
  font-size: 1.5rem;
}
#create,
#cancel {
  font-family: "Bitter", serif;
  font-size: 1rem;
  color: #011627;
  width: 39%;
  background-color: #011627;
  color: #fdfffc;
  border: 4px solid #ff9f1c;
  font-family: "Bitter", serif;
  font-size: 1.3rem;
  padding: 10px;
  text-align: center;
}
#birdPlaceholder {
  border-radius: 50%;
  width: 25%;
  height: auto;
  padding: 10px;
  background-color: #ff9f1c;
  border: 8px solid #e71d36;
}
</style>