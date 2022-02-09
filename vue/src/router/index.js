import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Profile from '../views/Profile.vue'
import store from '../store/index'
import List from '../views/List.vue'
import BirdDetail from '../views/Bird.vue'
import ListDetail from '@/views/ListDetailView'
import Search from '../views/Search.vue'
import NotFound from '../components/NotFound.vue'

Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'Home',
      alias: '/login',
      component: Home,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: '/search/:zipcode',
      name: 'search',
      component: Search,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: '/search/:zipcode',
      name: "not-found",
      component: NotFound,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/profile",
      name: "profile",
      component: Profile,
      meta: {
        requiresAuth: true
      }
    },
    {
      // create a new list or get all lists
      path: "/lists",
      name: "list",
      component: List,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/list/:listId",
      name: "list-detail",
      component: ListDetail,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/list/:listId/bird/:birdId",
      name: "bird-detail",
      component: BirdDetail,
      props: true,
      meta: {
        requiresAuth: true
      }
    },
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  //If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    window.alert("You must be logged in to view this page");
    next("/");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
