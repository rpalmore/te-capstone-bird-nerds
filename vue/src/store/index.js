import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if (currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    profile: {
      username: currentUser,
      profileImg: "",
      favoriteBird: "",
      mostCommonBird: "",
      zipCode: "",
      skillLevel: ""
    },
    // use localStorage to prevent data from resetting after refreshing page
    lists: JSON.parse(localStorage.getItem('lists') || "{}"),
    birds: [],
    activeBird: 0,
    activeList: 0,
    notes: []
  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user', JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },
    SET_PROFILE(state, payload) {
      state.profile = payload;
    },
    SET_LIST(state, payload) {
      state.lists = payload;
      localStorage.setItem("lists",JSON.stringify(state.lists));
    },
    ADD_LIST(state, list) {
      state.lists.shift(list);
      localStorage.setItem("lists",JSON.stringify(state.lists));
    },
    SET_BIRDS(state, birds) {
      state.birds = birds;
    },
    ADD_BIRD(state, bird) {
      state.birds.shift(bird);
    },
    SET_ACTIVE_LIST(state, listId) {
      state.activeList = listId;
    },
    SET_ACTIVE_BIRD(state, birdId) {
      state.activeBird = birdId;
    },
    SET_NOTES(state, notes) {
      state.notes = notes;
    },
    ADD_NOTE(state, note) {
      state.notes.shift(note);
    }
  }
})
