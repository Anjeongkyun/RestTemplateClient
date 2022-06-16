// Pathify
import {
  get,
  sync,
  call,
  make
} from 'vuex-pathify'

import axios from 'axios'
// Data
const state = {
  parkList : []
}

const mutations = {
  setParkList: function(state, payload){
    state.parkList = payload;
  }
}
const actions = {
  async parkListFetch({commit}, payload) {

    const url = `/park/list`
    await axios.post(url)
      .then(res => {
        // console.log(payload);
        commit('setParkList',res.data.data)
      })
      .catch(err => {
        alert('error 발생');
        console.log(err);
      })
  }
}

const getters = {}

export default {
  namespaced: true,
  state,
  mutations,
  actions,
  getters,
}
