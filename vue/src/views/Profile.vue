<template>
  <div id="profileForm">
    <img id="birdPlaceholder" src="..\assets\BirdSiloutette.png" alt="Bird silhouette" />
    <span id="profile">Welcome, bird nerd!</span>
    <ProfileForm />
  </div>
</template>

<script>
import ProfileForm from "../components/ProfileForm.vue";
export default {
  name: "profile",
  components: { ProfileForm },
};
</script>

<style scoped>
#profileForm {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 2%;
  gap: 25px;
}
span {
  font-family: "Bitter", serif;
  font-size: 1.3rem;
}
#birdPlaceholder {
  border-radius: 50%;
  width: 25%;
  height: auto;
  padding: 10px;
  background-color: #FF9F1C;
  border: 8px solid #E71D36;

}
</style>