// Vue
import Vue from 'vue'
import Vuex from 'vuex'
import pathify from '@/plugins/vuex-pathify'
import axios from 'axios'

// Modules
import * as modules from './modules'

Vue.prototype.$http = axios

Vue.use(Vuex)
const state = {}
const getters = {}
const mutations = {}
const actions = {}

const store = new Vuex.Store({
  modules,
  state,
  getters,
  mutations,
  actions,
  plugins: [
    pathify.plugin,
  ],
})

// store.subscribe(mutation => {
//   if (!mutation.type.startsWith('themeStore/'))

//     return
//   store.dispatch('themeStore/update', mutation)
//   // store.dispatch('test/login_axios', mutation)
// })

store.dispatch('appStore/init')

export default store

export const ROOT_DISPATCH = Object.freeze({ root: true })
