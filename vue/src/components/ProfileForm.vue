<template>
  <main>
      <h2>Update Profile</h2>
      <form>
           <!-- make this so it can swap the image dynamically when they upload -->
          <img v-bind:src='profile.imgUrl' id="profile-img" />
          <input id='img-url' type='text' v-model='profile.imgUrl' />
          <button id='upload-profile-image' v-on:click='updateImage'>Upload</button> <br>

          <label for='zip-code'>Zipcode: </label>
          <input id='zip-code' type='text' v-model='profile.zipcode' /> <br>

          <label for='fav-bird'>Favorite Bird: </label>
          <input id='fav-bird' type='text' v-model='profile.favoriteBird' /> <br>

          <label for='skill-lvl'>Skill Level: </label>
          <select id='skill-lvl' size='3' v-model='profile.skillLevel' multiple>
              <option value="beginner">Beginner</option>
              <option value="intermediate">Intermediate</option>
              <option value="advanced">Advanced</option>
          </select> <br>

          <label for='most-common-bird'>Most commonly spotted bird: </label>
          <select id='most-common-bird' type='text' v-model='profile.commonBird' /> <br>
      </form>
  </main>
</template>


<script>

export default {
    data() {
        return {
            newProfile: {}
        }
    },
    methods: {
        updateImage() {

        }
    },

}
</script>

<style>

</style>