<template>
  <div id="content-main">
    <div v-if="$store.state.token === ''">
      <BirdOfTheDay />
    </div>
    <div v-if="$store.state.token != ''">
      <div v-if="this.$route.path == '/profile'">
        <Profile />
      </div>
      <div v-if="this.$route.path == '/lists'">
        <List />
      </div>
      <div v-if="this.$route.path == '/note'"><BirdNote /></div>
    </div>
  </div>
</template>

<script>
import List from "../views/List.vue";
import Profile from "../views/Profile.vue";
import BirdOfTheDay from "./BirdOfTheDay.vue";
import BirdNote from "../views/BirdNote.vue";
export default {
  components: { BirdOfTheDay, Profile, List, BirdNote },
};
</script>

<style>
#content-main {
  display: flex;
  width: 80%;
  justify-content: center;
  background-color: #2ec4b6;
  margin-left: 8px;
  border: 1px solid #011627;
}
</style>