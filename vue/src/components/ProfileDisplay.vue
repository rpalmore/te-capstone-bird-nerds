<template>
  <div id="profileDisplay">
    <div id="image"> <img id= "image-link" v-bind:src="profile.profileImg"/></div>
    
     <p class="profileInfo"><strong>My favorite bird :</strong> {{ profile.favoriteBird }} </p>
     <p class="profileInfo"><strong>Most common bird at my feeder :</strong> {{ profile.mostCommonBird }}</p>
     <p class="profileInfo"><strong>My skill level :</strong> {{ profile.skillLevel }} </p>

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

#profileDisplay {
  border: 4px solid #ff9f1c;
  padding: 25px;
  background-color: #011627;
  color: #FDFFFC;
  padding-left: 100px;
  padding-right: 100px;

}

.profileInfo {
  justify-content: left;
  font-family: "Bitter", sans-serif;
  font-size: 1.6rem;
  
  
}

.response {
  justify-content: center;
  font-family: "Bitter", sans-serif;
  font-size: 1.5rem;
  
}

</style>