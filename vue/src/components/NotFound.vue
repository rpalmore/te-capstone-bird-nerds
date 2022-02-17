<template>
  <div id="not-found">
     <h2>We need more bird nerds!</h2>
     <span>No recent sightings in zip code {{ this.$route.params.zipcode }}.</span>
     <img src="@/assets/BirdSilhouette.png" alt="" id="bird">
  </div>
</template>

<script>

export default {
  name: "not-found"
};
  
</script>

<style scoped>
#not-found {
    display: flex;
    flex-direction: column;
    margin-top: 35px;
    align-items: center;
}
span {
    font-size: 1.3rem;
}
img {
    width: 65%;
    margin-top: 35px;
}

</style>