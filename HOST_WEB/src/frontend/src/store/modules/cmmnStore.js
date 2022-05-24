// Pathify
import {
  make
} from 'vuex-pathify'

import axios from 'axios'
import cmmnFnStore from './cmmnFnStore' //store 와 관련된 데이터를 처리하는 공통 함수 파일

const state = {
  cmmnCodeItem: null,
  useYnItem :
    [{
      textItem: "사용",
      valueItem: "Y",
    }, {
      textItem: "미사용",
      valueItem: "N",
    }]

}

//const mutations = make.mutations(state)

const mutations = {
  ...make.mutations(state),
  example(state, payload) {
    cmmnFnStore.res(payload);

  },

  getList(state, payload) {
    state.cmmnCodeItem = payload;
  }
}

const actions = {
  ...make.actions(state),
  code({
    state, // eslint-disable-line no-unused-vars
    commit, // eslint-disable-line no-unused-vars
  }, dataObj) {


    const url = '/internal/davis/web/cmmn/code'
    axios.post(url, {

    })
      .then(res => {
        cmmnFnStore.res(res);

        var arr = cmmnFnStore.fn.code(res,"PC");

        console.log(arr)
        commit('getList', arr)
      })
      .catch(err => {
        cmmnFnStore.err(err);
        //commit('axiosError', err)
      })

  },

}

const getters = {
  ...make.getters(state),
}

export default {
  namespaced: true,
  state,
  mutations,
  actions,
  getters,
}
