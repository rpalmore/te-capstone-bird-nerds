<template>
  <div id="home">
    <bird-of-the-day />
  </div>
</template>

<script>
import BirdOfTheDay from '../components/BirdOfTheDay.vue';

export default {
  components: { BirdOfTheDay },
  name: "home",
};
</script>
<style scoped>
#home {
  display: flex;
  flex-direction: column;
  align-items: center;
}
#home button {
  width: 80%;
  background-color: #011627;
  color: #fdfffc;
  border: 2px solid #ff9f1c;
  font-family: "Bitter", serif;
  font-size: 1.3rem;
  margin-top: 35px;
  padding: 10px;
}
.btn > a {
  text-decoration: none;
  color: #fdfffc;
}
</style>
