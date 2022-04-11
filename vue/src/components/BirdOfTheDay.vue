<template>
  <div id="dailyBird" v-show="imgUrl != ''">
    <img v-bind:src="imgUrl" />
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
  created() {
    birdService
      .getRandomBird()
      .then((response) => {
        if (response.status == 200) {
          this.imgUrl = response.data.imgUrl;
          this.name = response.data.name;
        }
      })
      .catch((err) => {
        alert(err + " problem generating a random bird!");
      });
  },
};
</script>

<style>
#dailyBird {
  position: absolute;
  display: flex;
  flex-direction: column;
  align-items: center;
}
#dailyBird > img {
  width: 85%;
  margin-top: 45px;
  border: 1px solid var(--rich-black);
}
#caption {
  position: absolute;
  top: 65px;
  right: 10%;
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
}
@media only screen and (max-width: 768px) {
  #caption {
    display: flex;
    margin-top: 10px;
    position: revert;
  }
  #dailyBird {
    position: revert;
  }
}
</style>