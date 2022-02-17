<template>
  <div id="content-main">

    <router-view />
    
  </div>
</template>

<script>
export default {
  name: 'content-main',

};
</script>

<style>
#content-main {
  display: flex;
  width: 80%;
  justify-content: center;
  margin-left: 8px;
  background-color: var(--tiffany-blue);
  border: 2px solid var(--rich-black);
}
</style>