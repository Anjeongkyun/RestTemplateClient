// Pathify
import { make } from 'vuex-pathify'

import axios from 'axios'
import cmmnStore from './cmmnStore' //store 와 관련된 데이터를 처리하는 공통 함수 파일
// Data
const state = {
}

const mutations = make.mutations(state)  //sync 사용가능

const actions = {
  login({
    state, // eslint-disable-line no-unused-vars
    commit, // eslint-disable-line no-unused-vars
  }, dataObj) { // eslint-disable-line no-unused-vars
    // state 값을 사용하기 때문에 인자사용,  로직을 실행하기위해 commit 사용, 그리고 그외 인자값


    //    console.log(state)
    //    console.log(commit)
    //    commit('loginError')

    const url = '/internal/davis/web/login/login'
    console.log("dataObj")
    console.log(dataObj)
    axios.post(url, {
      id: dataObj.loginId,
      password: dataObj.loginPw,
    })
      .then(res => {
        cmmnStore.res(res);
      })
      .catch(err => {
        cmmnStore.err(err);
      })
  },

}

const getters = {}

export default {
  namespaced: true,
  state,
  mutations,
  actions,
  getters,
}
