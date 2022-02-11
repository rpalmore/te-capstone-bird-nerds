<template>
  <div id="list-detail-view">
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

<style scoped>
#list-detail-view {
    display: flex;
    flex-direction: column;
    margin-bottom: 50px;
}

</style>