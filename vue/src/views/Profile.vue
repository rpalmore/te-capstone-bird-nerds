<template>
  <div id="profile">
    <img
      id="birdPlaceholder"
      src="..\assets\BirdSiloutette.png"
      alt="Bird silhouette"
    />
    <span id="profile"> Welcome, bird nerd! </span>
    <a id="edit" class="editProfile" v-show="formDisplay === false" v-on:click="displayForm"
      >Create or edit your profile</a
    >
    <ProfileForm v-show="formDisplay === true"></ProfileForm>

    <a id="cancel" v-show="formDisplay === true" v-on:click="cancelSubmit">Cancel</a>
    
    <ProfileDisplay v-show="profileDisplay === true"></ProfileDisplay>

  </div>
</template>

<script>
import ProfileForm from "../components/ProfileForm.vue";
import ProfileDisplay from "../components/ProfileDisplay.vue";
export default {
  name: "profile",
  components: { ProfileForm, ProfileDisplay },
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
}
span,
#edit, #cancel {
  font-family: "Bitter", serif;
  font-size: 1.3rem;
  color: #011627;
}
#edit, #cancel {
  font-size: 1rem;
}
#edit.editProfile {
  width: 39%;
  background-color: #011627;
  color: #fdfffc;
  align-items: center;
  border: 4px solid #ff9f1c;
  font-family: "Bitter", serif;
  font-size: 1.3rem;
  margin-top: 10px;
  padding: 10px;
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