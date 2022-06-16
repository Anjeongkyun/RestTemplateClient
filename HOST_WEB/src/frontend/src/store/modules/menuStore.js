// Pathify
import { make } from 'vuex-pathify'

import axios from 'axios'

import cmmnFnStore from './cmmnFnStore' //store 와 관련된 데이터를 처리하는 공통 함수 파일

// Data
const state = {
  menuList: [],
  menuListShow: [],
  menuListUser: [],
}

const getters = {
  ...make.getters(state),
}

const mutations = {
  ...make.mutations(state),
  getMenuList(state, payload) {
    state.menuList = payload;
  },
  getMenuListShow(state, payload) {
    state.menuListShow = payload;
  },
  getMenuListUser(state, payload) {
    state.menuListUser = payload;
  },
  getListMapping(state, payload) {
    var cmmnCode = this.getters["cmmnStore/cmmnCode"];
    var useYnItem = this.getters["cmmnStore/useYnItem"];
    var displayYnItem = this.getters["cmmnStore/displayYnItem"];

    var menuListShow = this.state.menuStore.menuListShow;

    state.menuList.forEach(element => {
      element.levelList = []
    
      displayYnItem.forEach(element1 => {
        if (element.menuShowYn == element1.valueItem) {
          element.menuShowNm= element1.textItem
        }
      });
      
  

    });
    //  console.log("state.levelMenuList")
    //  console.log(state.levelMenuList)
  },
}

const actions = {
  cmmnMenulist({
    state, // eslint-disable-line no-unused-vars
    commit, // eslint-disable-line no-unused-vars
  }, dataObj) {

    const url = '/internal/davis/web/menu/list';
    console.log("url: " + url)

    return axios.post(url, {})
      .then(res => {
        cmmnFnStore.res(res);
        if (res.data.resultCd != "00") return; // 정상이 아니면 return
        this.dispatch('cmmnStore/setCmmnMenu', res.data.list)

      //  commit('getMenuList', res.data.list)
      })
      .catch(err => {
        cmmnFnStore.err(err);
        //commit('axiosError', err)
      })

  },
  list({
    state, // eslint-disable-line no-unused-vars
    commit, // eslint-disable-line no-unused-vars
  }, dataObj) {

    const url = '/internal/davis/web/menu/list';
    console.log("url: " + url)

    return axios.post(url, {
    })
      .then(  res => {
        cmmnFnStore.res(res);
        if (res.data.resultCd != "00") return; // 정상이 아니면 return
        //  this.dispatch('cmmnStore/setCmmnMenu', res.data.list)

        commit('getMenuList', res.data.list)
        commit('getListMapping', {})
      })
      .catch(err => {
        cmmnFnStore.err(err);
        //commit('axiosError', err)
      })

  },

  listShow({
    state, // eslint-disable-line no-unused-vars
    commit, // eslint-disable-line no-unused-vars
  }, dataObj) {
    const url = '/internal/davis/web/menu/list/show'
    console.log("url: " + url)
    return axios.post(url, {})
      .then(res => {

        cmmnFnStore.res(res);
        commit('getMenuListShow', res.data.list)
        //  commit('getMenuListUser', res.data.list)

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

    const url = '/internal/davis/web/menu/manage'
    console.log(url)
    axios.post(url, dataObj)
      .then(res => {
        cmmnFnStore.res(res);
      })
      .catch(err => {
        cmmnFnStore.err(err);
      })

  },
}



export default {
  namespaced: true,
  state,
  mutations,
  actions,
  getters,
}
