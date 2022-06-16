// Pathify
import {
  make
} from 'vuex-pathify'

import axios from 'axios'

import cmmnFnStore from './cmmnFnStore' //store 와 관련된 데이터를 처리하는 공통 함수 파일

// Data
const state = {
  levelMenuList: [],
  userMenuList: [],
}

const mutations = {
  ...make.mutations(state),
  getLevelMenuList(state, payload) {
    state.levelMenuList = payload;
  } ,
  getLevelMenuMapping(state, payload) {
    var cmmnCode = this.getters["cmmnStore/cmmnCode"];
    var menuListShow = this.state.menuStore.menuListShow;

    state.levelMenuList.forEach(element => {
      element.levelList = []
      if (cmmnCode == null) return;
      //PC0401 을 시스템 관리자로 매핑
      cmmnCode.forEach(element1 => {
        if (element.level == element1.cmCd) {
          element.levelNm = element1.cdNm
        }
      });

      //  console.log("element.levelList")
      //  console.log(element.menuList)

      var arr_menuList = (element.menuList).split(",");
      arr_menuList.forEach(element1 => {

        menuListShow.forEach(element2 => {
        //  console.log("element2")
        //  console.log(element2)
          if (element1 == element2.menuId) {
            element.levelList.push(element2);
          }
        });




      });

    });
  //  console.log("state.levelMenuList")
  //  console.log(state.levelMenuList)
  },
}

const actions = {
  cmmnLevelMenulist({
    state, // eslint-disable-line no-unused-vars
    commit, // eslint-disable-line no-unused-vars
  }, dataObj) {

    const url = '/internal/davis/web/level/menu/list';
    console.log("url: " + url)
    return axios.post(url, {})
      .then(res => {

        cmmnFnStore.res(res);
        if (res.data.resultCd != "00") return; // 정상이 아니면 return
        this.dispatch('cmmnStore/setCmmnLevelMenu', res.data.list)
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

    const url = '/internal/davis/web/level/menu/list';
    console.log("url: " + url)
    return axios.post(url, { })
      .then( res => {

        cmmnFnStore.res(res);
        commit('getLevelMenuList', res.data.list);
        commit('getLevelMenuMapping', {})

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
