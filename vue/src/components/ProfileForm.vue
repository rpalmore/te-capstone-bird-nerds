<template>
  <div v-show="displayForm === true" id="profile-form">
    <form v-on:submit.prevent="updateProfile">
      <!-- to do: make this so it can swap the image dynamically when they upload -->
      <!-- <img v-bind:src="profile.profileImg" id="profile-img" /> -->
      <label for="img-url"
        >Customize your profile photo by adding an image URL.</label
      >
      <input
        id="img-url"
        placeholder=" Add an image URL here."
        type="text"
        v-model="profile.profileImg"
      />
      <!-- <button id="upload-profile-image" v-on:click="updateImage">Upload</button> -->
      <label for="fav-bird">What is your favorite bird?</label>
      <input id="fav-bird" type="text" v-model="profile.favoriteBird" />

      <label for="most-common-bird"
        >Tell us what bird you most commonly spot.</label
      >
      <input
        type="text"
        id="most-common-bird"
        v-model="profile.mostCommonBird"
      />

      <label for="zip-code">Please enter your zip code:</label>
      <input id="zip-code" type="text" v-model="profile.zipCode" />

      <label for="skill-lvl">What is your skill level?</label>
      <select id="skill-lvl" size="3" v-model="profile.skillLevel">
        <option value="beginner">Beginner</option>
        <option value="intermediate">Intermediate</option>
        <option value="advanced">Advanced</option>
      </select>

      <input type="submit" />
      
    </form>
  </div>
</template>


<script>
import profileService from "../services/ProfileService";
export default {
  data() {
    return {
      profile: {
        username: this.$store.state.user.username,
      },
      displayForm: true,
    };
  },
  methods: {
    // cancelSubmit() {
    //   this.profile = {},
    //   this.displayForm = false;
    // },
    updateImage() {},
    updateProfile() {
      console.log(this.profile);
      this.$store.commit("SET_PROFILE", this.profile);
      this.displayForm = false;
      profileService
        .updateProfile(this.profile)
        .then((response) => {
          if (response.status === 200) {
            alert("Profile created!");
          }
        })
        .catch((err) => {
          console.error(err + " problem updating profile!");
        });
    },
  },
};
</script>

<style scoped>
#profile-form {
  border: 1px solid #011627;
  padding: 25px;
}
form {
  display: flex;
  flex-direction: column;
  gap: 12px;
  align-items: center;
}
label {
  font-family: "Bitter", serif;
}
</style>