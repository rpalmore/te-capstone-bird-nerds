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
  height: 100vh;
  background-color: #fdfffc;
}
#main-content {
  display: flex;
}
#horizontal {
  display: flex;
  height: 100%;
  margin-top: 8px;
}
</style>
