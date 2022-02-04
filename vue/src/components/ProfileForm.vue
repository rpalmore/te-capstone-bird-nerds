<template>
  <div id="profile-form">
    <form v-on:submit.prevent="updateProfile">
      <!-- make this so it can swap the image dynamically when they upload -->
      <img v-bind:src="profile.profileImg" id="profile-img" />
      <input id="img-url" type="text" v-model="profile.profileImg" />
      <button id="upload-profile-image" v-on:click="updateImage">Upload</button>

      <label for="zip-code">Zipcode: </label>
      <input id="zip-code" type="text" v-model="profile.zipCode" />

      <label for="fav-bird">Favorite Bird: </label>
      <input id="fav-bird" type="text" v-model="profile.favoriteBird" />
      <p>
        <label for="skill-lvl">Skill Level: </label>
        <select id="skill-lvl" size="3" v-model="profile.skillLevel">
          <option value="beginner">Beginner</option>
          <option value="intermediate">Intermediate</option>
          <option value="advanced">Advanced</option>
        </select>
      </p>
      <p>
        <label for="most-common-bird">Most commonly spotted bird: </label>
        <input
          type="text"
          id="most-common-bird"
          v-model="profile.mostCommonBird"
        />
      </p>
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
    };
  },
  methods: {
    updateImage() {},
    updateProfile() {
      console.log(this.profile);
      this.$store.commit("SET_PROFILE", this.profile);
      profileService
        .updateProfile(this.profile)
        .then((response) => {
          if (response.status === 200) {
            alert("profile created!");
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
}
</style>