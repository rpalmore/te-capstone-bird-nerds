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
      <div v-if="this.$route.path == '/note'">
        <BirdNote />
      </div>
      <!-- RP commented this component out, below, since we don't have logic on when it should display yet.
          This should be handled with dynamic route matching -->
      <!-- <ListDetailView /> -->
    </div>
  </div>
</template>

<script>
import List from "../views/List.vue";
import Profile from "../views/Profile.vue";
import BirdOfTheDay from "./BirdOfTheDay.vue";
import BirdNote from "../views/BirdNote.vue";
// import ListDetailView from "../views/ListDetailView.vue";

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