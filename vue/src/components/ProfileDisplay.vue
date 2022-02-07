<template>
  <div id="profileDisplay">
    <div id="image"> <img id= "image-link" v-bind:src="profile.profileImg"/></div>
    
     <div class="profileInfo"> My favorite bird : </div> {{ profile.favoriteBird }} 
     <div class="profileInfo">Most common bird(s) at my feeder : </div> {{ profile.mostCommonBird }} 
     <div class="profileInfo"> My skill level : </div> {{ profile.skillLevel }} 

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

.profileInfo {
  justify-content: center;
  font-family: "Bitter", serif;
  font-size: 1.5rem;

}

</style>