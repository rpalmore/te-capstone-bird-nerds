import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
Vue.config.devtools = true

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token');
const currentUser = JSON.parse(localStorage.getItem('user'));

if (currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    profile: JSON.parse(localStorage.getItem('profile') || {}),
    lists: JSON.parse(localStorage.getItem('lists') || {}),
    birds: JSON.parse(localStorage.getItem('birds') || "{}"),
    bird: JSON.parse(localStorage.getItem('bird')),
    activeBird: 0,
    notes: JSON.parse(localStorage.getItem('notes') || "{}"),
    birdPhoto: false
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
      localStorage.setItem('profile', JSON.stringify(state.profile));
    },
    SET_LISTS(state, payload) {
      state.lists = payload;
      localStorage.setItem("lists", JSON.stringify(state.lists));
    },
    ADD_LIST(state, list) {
      state.lists.push(list);
      localStorage.setItem("lists", JSON.stringify(state.lists));
    },
    SET_BIRDS(state, birds) {
      state.birds = birds;
      localStorage.setItem("birds", JSON.stringify(state.birds));
    },
    SET_SOURCE_BIRD(state, payload) {
      state.birdPhoto = true;
      state.bird.birdId = payload;
    },
    SET_SOURCE_PROFILE(state) {
      state.birdPhoto = false;
    },
    ADD_BIRD(state, bird) {
      state.birds.push(bird);
      localStorage.setItem("bird", JSON.stringify(bird));
      localStorage.setItem("birds", JSON.stringify(state.birds));
    },
    SET_ACTIVE_BIRD(state, birdId) {
      state.activeBird = birdId;
    },
    SET_NOTES(state, notes) {
      state.notes = notes;
      localStorage.setItem("notes", JSON.stringify(state.notes));
    },
    ADD_NOTE(state, note) {
      state.notes.unshift(note);
      localStorage.setItem("notes", JSON.stringify(state.notes));
    },
    DELETE_LIST(state, listId) {
      state.lists = state.lists.filter((list) => list.listId != listId);
      localStorage.setItem("lists", JSON.stringify(state.lists));
    },
    DELETE_BIRD(state, birdId) {
      state.birds = state.birds.filter((bird) => bird.birdID != birdId);
      localStorage.setItem("birds", JSON.stringify(state.birds));
    },
    DELETE_NOTE(state, noteId) {
      state.notes = state.notes.filter((note) => note.noteId != noteId);
      localStorage.setItem("notes", JSON.stringify(state.notes));
    },
    EDIT_LIST(state, list) {
      state.lists = state.lists.map(l => l.listId != list.listId ? l : list);
      localStorage.setItem("lists", JSON.stringify(state.lists));
    },
    EDIT_BIRD(state, bird) {
      state.birds = state.birds.map(b => b.birdID != bird.birdID ? b : bird);
      localStorage.setItem("birds", JSON.stringify(state.birds));
      localStorage.setItem("bird", JSON.stringify(bird));
    },
    EDIT_NOTE(state, note) {
      state.notes = state.notes.map(n => n.noteId != note.noteId ? n : note);
      localStorage.setItem("notes", JSON.stringify(state.notes));
    }
  }
})
