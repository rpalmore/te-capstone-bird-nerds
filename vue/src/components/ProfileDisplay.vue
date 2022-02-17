<template>
  <div class="profile-content-container">
    <div id="profile-box">
      <p class="question">My favorite bird:</p>
      <span class="response">{{ profile.favoriteBird }}</span>
      <p class="question">Most common bird at my feeder:</p>
      <span class="response">{{ profile.mostCommonBird }}</span>
      <p class="question">My skill level:</p>
      <span class="response">{{ profile.skillLevel }}</span>
      <p class="question">My zip code:</p>
      <span class="response">{{ profile.zipCode }}</span>
    </div>
  </div>
</template>

<script>
import profileService from "../services/ProfileService.js";
export default {
  data() {
    return {
      profile: [],
    };
  },
  created() {
    profileService
      .getProfile()
      .then((response) => {
        this.profile = response.data;
      })
      .catch((err) => {
        alert.error(err + " nothing returned");
      });
  },
};
</script>

<style scoped>
/* CSS for component in Profile.vue */
</style>