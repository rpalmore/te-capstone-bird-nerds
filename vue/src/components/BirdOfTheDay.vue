<template>
  <div id="dailyBird">
    <!-- <img src="..\assets\eBirdHomePage.webp" alt="" class="responsive" /> -->
    <iframe
        width="800"
        height="578"
        v-bind:src="https://macaulaylibrary.org/asset/408852991/embed/800"
        frameborder="0"
        allowfullscreen
        style="width: 800px"
      ></iframe>
  </div>
</template>


<script>
export default {
  name: "bird-of-the-day",
};
</script>

<style>
.responsive {
    width: 100%;
    height: auto;
    border-bottom: 1px solid #011627;
}
</style>