<template>
  <div id="bird-vue">
    <bird-detail />
    <div id="list-link">
      Back to list:
      <router-link
        id="list-anchor"
        :to="{ name: 'list-detail', params: { activeList } }"
      > 
        {{ list.listName }}
      </router-link>
    </div>
    <bird-notes v-bind:bird="bird" />
    <bird-note-form v-bind:bird="bird" />
  </div>
</template>

<script>
import birdDetail from "../components/BirdDetail.vue";
import birdNotes from "../components/BirdNotes.vue";
import birdNoteForm from "../components/BirdNoteForm.vue";

export default {
  components: { birdDetail, birdNotes, birdNoteForm },
  data() {
    return {
      activeList: this.$route.params.listId,
    };
  },
  computed: {
    bird() {
      return this.$store.state.birds.find(
        (b) => b.birdID == this.$route.params.birdId
      );
    },
    lists() {
      return this.$store.state.lists;
    },
    listId() {
      return this.activeList;
    },
    list() {
      return this.lists.find((l) => l.listId == this.activeList);
    },
  },
  created() {
    this.$store.commit("SET_ACTIVE_BIRD", this.$route.params.birdId);
  },
};
</script>


<style>
#bird-vue {
  display: flex;
  flex-direction: column;
  width: 80%;
}
#list-link {
  display: flex;
  margin-top: 35px;
  color: var(--rich-black);
  font-size: 1.3rem;
}
#list-anchor {
  padding: 0 5px 0 5px;
}
#list-anchor:hover {
  background-color: var(--rich-black);
}
</style>