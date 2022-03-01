<template>
  <div v-show="displayForm === true" class="profile-content-container">
    <form v-on:submit.prevent="selectUpdateMethod">
      <label for="fav-bird">What is your favorite bird?</label>
      <input
        required
        id="fav-bird"
        type="text"
        size="50"
        v-model="profile.favoriteBird"
      />

      <label for="most-common-bird"
        >Tell us what bird you most commonly spot.</label
      >
      <input
        required
        id="most-common-bird"
        type="text"
        size="50"
        v-model="profile.mostCommonBird"
      />

      <label for="zip-code">Please enter your zip code:</label>
      <input
        required
        id="zip-code"
        type="text"
        pattern="[0-9]{5}"
        title="Numbers only, please!"
        placeholder="#####"
        v-model="profile.zipCode"
      />

      <label for="skill-lvl">What is your skill level?</label>
      <select required id="skill-lvl" size="3" v-model="profile.skillLevel">
        <option value="beginner">Beginner</option>
        <option value="intermediate">Intermediate</option>
        <option value="advanced">Advanced</option>
      </select>
      <button id="submit">Update</button>
    </form>
  </div>
</template>


<script>
import profileService from "../services/ProfileService";
export default {
  name: "profile-form",
  data() {
    return {
      profile: {
        username: this.$store.state.user.username,
        profileImg: this.$store.state.profile.profileImg,
        favoriteBird: this.$store.state.profile.favoriteBird,
        mostCommonBird: this.$store.state.profile.mostCommonBird,
        zipCode: this.$store.state.profile.zipCode,
        skillLevel: this.$store.state.profile.skillLevel
      },
      displayForm: true,
    };
  },
  methods: {
    selectUpdateMethod() {
      if (this.$store.state.profile.favoriteBird === undefined) {
        this.displayForm = false;
        profileService
          .updateProfile(this.profile)
          .then((response) => {
            if (response.status === 201) {
              this.$store.commit("SET_PROFILE", this.profile);
              this.$router.go(0);
            }
          })
          .catch((err) => {
            alert.error(err + " problem updating profile!");
          });
      } else {
        this.displayForm = false;
        profileService
          .editProfile(this.profile)
          .then((response) => {
            if (response.status === 200) {
              this.$store.commit("SET_PROFILE", this.profile);
              this.$router.go(0);
            }
          })
          .catch((err) => {
            alert.error(err + " problem updating profile!");
          });
      }
    },
  },
};
</script>

<style scoped>
/* CSS for component in Profile.vue */
</style>