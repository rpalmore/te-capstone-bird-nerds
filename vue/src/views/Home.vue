<template>
  <div id="home">
    <bird-of-the-day />
  </div>
</template>

<script>
import BirdOfTheDay from "../components/BirdOfTheDay.vue";

export default {
  components: { BirdOfTheDay },
  name: "home",
};
</script>

<style>
#home {
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>
