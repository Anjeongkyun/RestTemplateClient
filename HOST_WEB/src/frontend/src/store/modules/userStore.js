// Pathify
import {
  make
} from 'vuex-pathify'

import axios from 'axios'
import cmmnFnStore from './cmmnFnStore' //store 와 관련된 데이터를 처리하는 공통 함수 파일

const state = {
  parkSeq:'AAA01',
  id: null,
  parkItem: null,
  parkItemAll: {
    aptNm: '전체',
    aptId: 'AAA01'
  },
  parkItemSelected: {
    aptNm: '전체',
    aptId: 'AAA01'
  },

  tableData: [],

}

//const mutations = make.mutations(state)

const mutations = {
  ...make.mutations(state),
  axiosSuccess(state, payload) {
    cmmnFnStore.res(payload);
  },
  axiosError(state, payload) {
    cmmnFnStore.err(payload);
  },
  getList (state, payload){
    state.parkItem = payload;
  }
}

const actions = {
  ...make.actions(state),
  park({
    state, // eslint-disable-line no-unused-vars
    commit, // eslint-disable-line no-unused-vars
  }, dataObj) {

    const url = '/internal/davis/web/park/list'
    axios.post(url, {
      parkSeq: state.parkSeq,
    })
      .then(res => {
        var list = [state.parkItemAll].concat(res.data.list);
        //  commit('axiosSuccess', res)
        cmmnFnStore.res(res);
        commit('getList', list)


      })
      .catch(err => {
        cmmnFnStore.err(err);
        //commit('axiosError', err)
      })

  },

  searchBtnClick({
    state, // eslint-disable-line no-unused-vars
    commit, // eslint-disable-line no-unused-vars
  }, dataObj) {
    console.log(this)
    const url = '/internal/davis/web/user/list'
    console.log("dataObj")
    console.log(dataObj)

    axios.post(url, {

      loginId: dataObj.loginId.data,
      memberNm: dataObj.memberNm.data
    })
      .then( res => {
        cmmnFnStore.res(res);

        var list =  res.data.list;
        state.tableData =  list
        console.log("=====searchBtnClick ok")
      })
      .catch(err => {
        cmmnFnStore.err(err);
      })

  },

  submit({
    state, // eslint-disable-line no-unused-vars
    commit, // eslint-disable-line no-unused-vars
  }, dataObj) {

    const url = '/internal/davis/web/user/manage'


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
  async idCheck({
    state, // eslint-disable-line no-unused-vars
    commit, // eslint-disable-line no-unused-vars
  }, dataObj) {

    const url = '/internal/davis/web/user/check'
    var result = await  axios.post(url, dataObj)
      .then(res => {
      //  cmmnFnStore.res(res);
      //  console.log(res.data.list)
        if (res.data.list == undefined) {
          return true
        } else {
          alert("이미 중복된 ID가 있습니다.")
          return false
        }
        console.log("=====idCheck ok")
      })
      .catch(err => {
        cmmnFnStore.err(err);
        return false;
      })

    return result;
  },


}

const getters = {

}

export default {
  namespaced: true,
  state,
  mutations,
  actions,
  getters,
}
