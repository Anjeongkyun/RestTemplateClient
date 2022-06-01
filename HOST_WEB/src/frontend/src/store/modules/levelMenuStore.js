// Pathify
import {
  make
} from 'vuex-pathify'

import axios from 'axios'

import cmmnFnStore from './cmmnFnStore' //store 와 관련된 데이터를 처리하는 공통 함수 파일

// Data
const state = {
  levelMenuList: [],
}

const mutations = {
  ...make.mutations(state),
  getLevelMenuList(state, payload) {

    state.levelMenuList = payload;
  }
}

const actions = {
  list({
    state, // eslint-disable-line no-unused-vars
    commit, // eslint-disable-line no-unused-vars
  }, dataObj) {
    console.log("==================1")
    const url = '/internal/davis/web/level/menu/list'
    axios.post(url, {

    })
      .then(res => {

        cmmnFnStore.res(res);

        var cmmnCode = this.state.cmmnStore.cmmnCodeItem;
        var list = res.data.list

        commit('getLevelMenuList', list)
        list.forEach(element => {
          element.levelNm = element.level

          //PC0401 을 시스템 관리자로 매핑
          cmmnCode.forEach(element1 => {
            if (element.level == element1.cmCd) {
              element.levelNm = element1.cdNm
            }


          });
        });

      })
      .catch(err => {
        cmmnFnStore.err(err);
        //commit('axiosError', err)
      })

  },

  submit({
    state, // eslint-disable-line no-unused-vars
    commit, // eslint-disable-line no-unused-vars
  }, dataObj) {

    const url = '/internal/davis/web/level/menu/manage'

    console.log(url)
    console.log(dataObj)
    axios.post(url, dataObj)
      .then(res => {
        cmmnFnStore.res(res);
        //store.dispatch('userStore/searchBtnClick')
        console.log("=====submit ok")
      })
      .catch(err => {
        cmmnFnStore.err(err);
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
