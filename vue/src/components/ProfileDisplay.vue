<template>
  <div class="profile-content-container">
    <div id="profile-box">
      <p class="question">My favorite bird:</p>
      <span class="response">{{ profile.favoriteBird }}</span>
      <p class="question">Bird I most commonly spot:</p>
      <span class="response">{{ profile.mostCommonBird }}</span>
      <p class="question">My skill level:</p>
      <span class="response">{{ profile.skillLevel }}</span>
      <p class="question">My zip code:</p>
      <span class="response">{{
        profile.zipCode === 0 ? " " : profile.zipCode
      }}</span>
    </div>
  </div>
</template>

<script>
import profileService from "../services/ProfileService.js";
export default {
  computed: {
    profile() {
      return this.$store.state.profile;
    }
  },
  created() {
    profileService
      .getProfile()
      .then((response) => {
        this.profile = response.data;
      })
      .catch((err) => {
        alert(err + " nothing returned");
      });
  },
};
</script>

<style scoped>
/* CSS for component in Profile.vue */
</style>