<template>
  <div id="main">
    <div id="logo-img">
      <img src="..\assets\placeholderLogo.png" alt="Placeholder logo" />
    </div>
    <div id="logo-text">
      <span>{{ logo }}</span>
    </div>
  </div>
</template>

<script>
export default {
  name: "header",
  data() {
    return {
      logo: "BIRD NERDS",
    };
  },
};
</script>


<style>
#main {
  display: flex;
  background-color: #011627;
  height: 150px;
}
#logo-img {
  display: flex;
  width: 20%;
  justify-content: center;
  background-color: #e71d36;
  border: 2px solid #011627;
}
#logo-img > img {
  padding: 10px;
}
#logo-text {
  display: flex;
  flex-grow: 1;
  border-left: 8px solid #fdfffc;
  color: #fdfffc;
  align-items: center;
  font-family: "Bitter", serif;
}
span {
  margin-left: 15%;
  font-size: 3rem;
}
</style>