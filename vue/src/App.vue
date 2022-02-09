<template>
  <div id="app">
    <SiteHeader />
    <div id="horizontal">
      <LeftNav />
      <ContentMain />
    </div>
  </div>
</template>

<script>
import SiteHeader from "./components/SiteHeader.vue";
import LeftNav from "./components/LeftNav.vue";
import ContentMain from "./components/ContentMain.vue";
export default {
  components: { SiteHeader, LeftNav, ContentMain },
};
</script>

<style>
#app {
  background-color: #fdfffc;
  font-family: "Bitter", serif;
  display: flex;
  flex-direction: column;
}
#main-content {
  display: flex;
}
#horizontal {
  display: flex;
  margin-top: 8px;
  min-height: 100vh;
  height: auto;
}
</style>
