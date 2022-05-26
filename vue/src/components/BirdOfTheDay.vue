<template>
  <div id="dailyBird">
    <img v-bind:src="imgUrl" rel="preload" as="image" />
    <div id="caption">
      <span id="BODlabel">Bird of the Day</span>
      <span id="birdName">{{ name }}</span>
    </div>
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
  mounted() {
    birdService
      .getRandomBird()
      .then((response) => {
        if (response.status == 200) {
          this.imgUrl = response.data.imgUrl;
          this.name = response.data.name;
        }
      })
      .catch((err) => {
        alert(err + " problem generating image!");
      });
  },
};
</script>

<style>
#dailyBird {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 45px;
  height: 400px;
  width: 600px;
  background-color: var(--rich-black);
}
#dailyBird > img {
  border: 1px solid var(--rich-black);
  height: 398px;
  max-width: 600px;
  object-fit: cover;
  overflow: hidden;
}
#caption {
  display: flex;
  margin-top: -7%;
  margin-left: 3%;
}
#caption > span {
  font-size: 1rem;
}
#BODlabel {
  background-color: var(--orange-peel);
  color: var(--rich-black);
  border-top: 1px solid var(--rich-black);
  border-bottom: 1px solid var(--rich-black);
  border-left: 1px solid var(--rich-black);
  padding: 5px;
}
#birdName {
  background-color: var(--rich-black);
  color: var(--baby-powder);
  border-top: 1px solid var(--orange-peel);
  border-right: 1px solid var(--orange-peel);
  border-bottom: 1px solid var(--orange-peel);
  padding: 5px;
  min-width: 100px;
}
@media only screen and (max-width: 768px) {
  #caption {
    display: flex;
    justify-content: center;
    margin-top: 3%;
    margin-bottom: 3%;
  }
  #dailyBird {
    width: 85%;
    overflow: hidden;
  }
}
</style>