<!-- For displaying one "listcard" aka the basic details of a bird -->
<template>
  <div id='bird-detail'>
      {{bird}}
  </div>
</template>

<script>

export default {
    name: "bird-detail",
    computed: {
        bird() {
            return this.$store.state.birds.find(
                b => b.birdID = this.$store.state.activeBird
            );
        },

    }
}
</script>

<style>

</style>