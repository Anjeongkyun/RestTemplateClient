// Pathify
import { make } from 'vuex-pathify'

import axios from 'axios'

const getters = {}

// Data
const state = {
  siteStatusList : []
}

const mutations = {
  setSiteStatusList: function(state, payload){
    state.siteStatusList = payload;
  }
}

const actions = {

  async siteStatusFetch({commit}, payload) {

    alert("현장 상태 가져오기");
    return;
    const url = `/site/status`
    await axios.post(url)
    .then(res => {             
        console.log(payload);
        commit('setSiteStatusList',res.data.data) 
    })
    .catch(err => {
        alert('error 발생');
        console.log(err);
    })
  }
}


export default {
  namespaced: true,
  state,
  mutations,
  actions,
  getters,
}
