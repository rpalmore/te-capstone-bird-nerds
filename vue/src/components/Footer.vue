<template>
  <div id="footer-content">
    <div id="copyright">&copy; 2022 Bird Nerds</div>
    <a
      id="github-link"
      href="https://github.com/rpalmore/te-capstone-bird-nerds"
      target="_blank"
    >
      <img id="github" src="@/assets/GitHub-Mark-Light-32px.png" />
    </a>
  </div>
</template>

<script>
export default {
  name: "footer",
};
</script>

<style>
#footer-content {
  display: flex;
  justify-content: center;
  background-color: var(--rich-black);
  border-top: 1px solid var(--rich-black);
  margin-top: 8px;
  color: var(--baby-powder);
  align-items: center;
}
#copyright {
  font-size: 1.1rem;
  padding: 6px;
}
#github {
  border-radius: 50%;
}
#github:hover {
  background-color: var(--rose-madder);
}
</style>