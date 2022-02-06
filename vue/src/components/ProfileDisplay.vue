<template>
  <div id="profileDisplay">
    <div id="image"> <img id= "image-link" v-bind:src="profile.profileImg"/></div>
    <div id="profileInfo">
    My favorite bird:  {{ profile.favoriteBird }} <br/>
    Most common bird(s) at my feeder:  {{ profile.mostCommonBird }} <br>
    My skill level: {{ profile.skillLevel }}
    </div>
  </div>
</template>

<script>
import profileService from '../services/ProfileService.js';
export default {
  data(){
    return {
      profile: []
    }
  },

  created(){
    profileService.getProfile().then((response) => {
    this.profile = response.data
    })
     .catch((err) => {
        console.error(err + " nothing returned");
      });

  }

}
</script>

<style>

#profileDisplay {
  display: flex;
  justify-content: center;
  font-family: "Arial Black", Gadget, sans-serif

}

</style>