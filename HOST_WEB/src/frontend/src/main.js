import Vue from 'vue'
import App from './App.vue'
//import i18n from './i18n'
import router from './router'
import vuetify from './plugins/vuetify'
import './plugins'
import store from './store'
import { sync } from 'vuex-router-sync'
import axios from 'axios'

Vue.prototype.$http = axios
Vue.prototype.$axios = axios
Vue.config.productionTip = false
sync(store, router)

new Vue({
  //i18n,
  router,
  vuetify,
  store,
  render: h => h(App),
}).$mount('#app')
