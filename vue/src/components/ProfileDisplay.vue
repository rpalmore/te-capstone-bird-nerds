<template>
  <div id="profileDisplay">
    <div id="image"> <img id= "image-link" v-bind:src="profile.profileImg"/></div>
    
     <p class="profileInfo"> My favorite bird : {{ profile.favoriteBird }} </p>
     <p class="profileInfo">Most common bird at my feeder : {{ profile.mostCommonBird }}</p>
     <p class="profileInfo"> My skill level : {{ profile.skillLevel }} </p>

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
    console.log(response.data);
    })
     .catch((err) => {
        console.error(err + " nothing returned");
      });

  }

}
</script>

<style scoped>

.profileInfo {
  justify-content: left;
  font-family: "Bitter", sans-serif;
  font-size: 1.6rem;
  font-weight: bold;
  
}

.response {
  justify-content: center;
  font-family: "Bitter", sans-serif;
  font-size: 1.5rem;
  
}

</style>