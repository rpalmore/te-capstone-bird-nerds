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
  background-color: #2ec4b6;
  margin-left: 8px;
  border: 1px solid #011627;
}
</style>