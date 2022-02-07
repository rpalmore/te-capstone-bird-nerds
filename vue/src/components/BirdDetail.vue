<!-- For displaying one "listcard" aka the basic details of a bird -->
<template>
  <div id='bird'>
      {{this.bird}}
  </div>
</template>

<script>

export default {
    name: "bird-detail",
    props: ["bird"],
}
</script>

<style>

</style>