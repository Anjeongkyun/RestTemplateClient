// Pathify
import {
  make
} from 'vuex-pathify'

import cmmnFnStore from './cmmnFnStore' //store 와 관련된 데이터를 처리하는 공통 함수 파일



import axios from 'axios'

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
  },
  getUserList(state, payload) {
    state.tableData = payload
    state.userList = payload;
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

  async searchBtnClick({
    state, // eslint-disable-line no-unused-vars
    commit, // eslint-disable-line no-unused-vars
  }, dataObj) {
    const url = '/internal/davis/web/user/list'
    console.log(url)
    return axios.post(url, {

      loginId: dataObj.loginId.data,
      memberNm: dataObj.memberNm.data
    })
      .then(async res => {

        cmmnFnStore.res(res);
        var list = res.data.list;

        var cmmnCode = this.getters["cmmnStore/cmmnCode"];
        var cmmnUseYn = this.getters["cmmnStore/useYnItem"] //this.state.cmmnStore.useYnItem;

        list.forEach(element => {
        //  element.levelNm = element.levelCd;

          //PC0401 을 시스템 관리자로 매핑
          cmmnCode.forEach(element1 => {
            if (element.levelCd == element1.cmCd) {
              element.levelNm = element1.cdNm;
            }
          });

          //사용 여부 매핑
          cmmnUseYn.forEach(element1 => {
            if (element.useYn == element1.valueItem) {
              element.useNm = element1.textItem
            }
          });

          //사용자 매핑
          list.forEach(element1 => {
            if (element.updId == element1.loginId) {
              element.updNm = element1.memberNm;
            }
            if (element.insId == element1.loginId) {
              element.insNm = element1.memberNm

            }
          });
        });
        commit('getUserList',  await list)


        //var list =  res.data.list;
        //state.tableData =  list
      })
      .catch(err => {
        cmmnFnStore.err(err);
      })

  },

  async submit({
    state, // eslint-disable-line no-unused-vars
    commit, // eslint-disable-line no-unused-vars
  }, dataObj) {

    const url = '/internal/davis/web/user/manage'
    console.log(url)

    return  axios.post(url, dataObj)
      .then(res => {
        cmmnFnStore.res(res);
        return res.data.resultCd
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
