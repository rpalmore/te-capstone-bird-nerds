<template>
  <div id="no-birds">
    <h2>We need more bird nerds!</h2>
    <span id="no-sightings"
      >No recent sightings in zip code {{ this.$route.params.zipcode }}.</span
    >
    <img
      id="bird-silhouette"
      src="@/assets/BirdSilhouette.png"
      alt="A bird silhouette"
    />
  </div>
</template>

<script>
export default {
  name: "not-found",
};
</script>

<style scoped>
#no-birds {
  display: flex;
  flex-direction: column;
  width: 100%;
  align-items: center;
  color: var(--rich-black);
}
#no-birds > h2 {
  margin-top: 45px;
  margin-bottom: 35px;
  text-align: center;
}
#no-sightings {
  font-size: 1.3rem;
  text-align: center;
}
#bird-silhouette {
  margin-top: 35px;
}
@media only screen and (max-width: 768px) {
  #bird-silhouette {
    width: 65%;
  }
  #no-sightings {
    padding: 8px;
  }
}
</style>