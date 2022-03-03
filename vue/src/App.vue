<template>
  <div id="app">
    <SiteHeader />
    <div id="container-main">
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
:root {
  /* Coolers palette: 
     https://coolors.co/palette/011627-fdfffc-2ec4b6-e71d36-ff9f1c */
  --rich-black: #011627;
  --baby-powder: #fdfffc;
  --tiffany-blue: #2ec4b6;
  --rose-madder: #e71d36;
  --orange-peel: #ff9f1c;
  --nav-btn: 1.3rem;
  --edit-btn: 1rem;
}
#app {
  background-color: var(--baby-powder);
  display: flex;
  flex-direction: column;
  font-family: "Bitter", serif;
}
a {
  text-decoration: none;
}
.helper-text {
  color: var(--rich-black);
  font-size: var(--nav-btn);
  font-weight: bold;
  border-bottom: 2px solid var(--baby-powder);
  padding-bottom: 18px;
  text-align: center;
}
#container-main {
  display: flex;
  margin-top: 8px;
  min-height: 100vh;
  height: auto;
}
input {
  font-family: "Bitter", serif;
}
</style>
