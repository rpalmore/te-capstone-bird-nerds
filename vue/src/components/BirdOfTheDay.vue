<template>
  <div id="dailyBird">
    <img v-bind:src="imgUrl" />
    <div id="caption">{{ name }}</div>
  </div>
</template>

<script>
import birdService from "../services/BirdService";
export default {
  name: "bird-of-the-day",
  data() {
    return {
      imgUrl: "",
    };
  },
  created() {
    birdService
      .getRandomBird()
      .then((response) => {
        if (response.status == 200) {
          // eslint-disable-next-line no-console
          console.log(response.data);
          this.imgUrl = response.data.imgUrl;
          this.name = response.data.name;
        }
      })
      .catch((err) => {
        alert.error(err + " problem generating a random bird!");
      });
  },
};
</script>

<style scoped>
/* iframe {
  margin-top: 45px;
  padding: 8px 8px 0 8px;
  background-color: white;
  border: 3px solid #011627;
  border: 3px solid #ff9f1c;
  margin-bottom: 50px;
} */
#dailyBird {
  display: flex;
  flex-direction: column;
  align-items: center;
}
img {
  width: 75%;
  margin-top: 45px;
  border: 3px solid #011627;
  border: 3px solid #ff9f1c;
  margin-bottom: 50px;
}
#caption {
  position: relative;
  left: -52%;
  background-color: var(--orange-peel);
  padding: 15px;
  border: 4px solid var(--baby-powder);
}
</style>