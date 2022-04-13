import Vue from 'vue'
import IdleVue from 'idle-vue'
import App from './App.vue'
import router from './router/index'
import store from './store/index'
import axios from 'axios'

const eventsHub = new Vue()
 
Vue.use(IdleVue, { 
  eventEmitter: eventsHub,
  idleTime: 600000
 })

Vue.config.productionTip = false

axios.defaults.baseURL = process.env.VUE_APP_REMOTE_API;

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
