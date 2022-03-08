<template>
  <div id="content-main">
    <router-view />
  </div>
</template>

<script>
export default {
  name: "content-main",
};
</script>

<style>
#content-main {
  display: flex;
  width: 80%;
  justify-content: center;
  margin-left: 8px;
  background-color: var(--tiffany-blue);
  border: 1px solid var(--rich-black);
}
@media only screen and (max-width: 768px) {
  #content-main {
    flex-direction: row;
    width: 100%;
    background-color: var(--tiffany-blue);
    margin: 0;
    border: 0;
  }
}
</style>