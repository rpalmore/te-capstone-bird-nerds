<template>
  <div id="app">
    <SiteHeader />
    <div id="container-main">
      <LeftNav />
      <ContentMain />
    </div>
    <div id="container-footer">
      <Footer />
    </div>
  </div>
</template>

<script>
import SiteHeader from "./components/SiteHeader.vue";
import LeftNav from "./components/LeftNav.vue";
import ContentMain from "./components/ContentMain.vue";
import Footer from "./components/Footer.vue";

export default {
  components: { SiteHeader, LeftNav, ContentMain, Footer },
  onIdle() {
    this.$store.commit("LOGOUT");
    if (this.$route.name != "Home") {
      this.$router.push({ name: "Home" });
    }
  },
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
@media only screen and (max-width: 768px) {
  #container-main {
    flex-direction: column;
    align-items: center;
    background-color: var(--tiffany-blue);
    border-right: 1px solid var(--rich-black);
    border-bottom: 1px solid var(--rich-black);
    border-left: 1px solid var(--rich-black);
  }
}
</style>
