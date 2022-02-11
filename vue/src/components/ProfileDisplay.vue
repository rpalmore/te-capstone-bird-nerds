<template>
  <div id="profileDisplay">
    <!-- <div id="image">
      <img id="image-link" v-bind:src="profile.profileImg" />
    </div> -->
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
        console.error(err + " nothing returned");
      });
  },
};
</script>

<style scoped>  

#profileDisplay {
  background-color: #011627;
  color: #fdfffc;
  border: 4px solid #ff9f1c;
  padding: 0 25px 25px 25px;
  font-family: "Bitter", serif;
  font-size: 1.3rem;
  width: 75%;
  text-align: center;
}
#profile-box {
  padding-bottom: 25px;
}
.question {
  padding-top: 25px;
}
.response {
  padding-bottom: 10px;
  color: #ff9f1c;
  border-bottom: 2px solid #e71d36;
  font-style: italic;
}
</style>