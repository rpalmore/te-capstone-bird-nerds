<template>
  <div>
      <list-detail />
  </div>
</template>

<script>
import ListDetail from '../components/ListDetail';

export default {
    components: { 
        ListDetail 
    },
    computed: {
        list() {
            return this.$store.state.lists.find(
                l => l.listId == this.$store.state.activeList
            );
        }
    },
    created() {
        this.$store.commit("SET_ACTIVE_LIST", this.$route.params.listId);
    }
};
</script>

<style>

</style>