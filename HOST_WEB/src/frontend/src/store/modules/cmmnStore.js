// Pathify
import {
  make
} from 'vuex-pathify'

import axios from 'axios'
import cmmnFnStore from './cmmnFnStore' //store 와 관련된 데이터를 처리하는 공통 함수 파일


//vue 의 데이터와 비슷,
//예) router:store.state.cmmnStore.cmmnMenu
const state = {
  cmmnCodeItem: null,
  useYnItem :
    [{
      textItem: "사용",
      valueItem: "Y",
    }, {
      textItem: "미사용",
      valueItem: "N",
    }],
  displayYnItem: [
    {
      textItem: "표시",
      valueItem: "Y",
    }, {
      textItem: "미표시",
      valueItem: "N",
    }
  ],

  loginUserYN: null, // 실제 로그인 여부
  loginUserID: null, // 사용자 ID, 새로고침시 id를 유지하기 위해 사용
  loginUserNM: null, // 사용자 이름,
  loginUserLevel: "PC0404",  //  기본 비로그인
  loginUserMenu:[],
  cmmnCode: null,
  cmmnMenu: null,
  cmmnLevelMenu:null,
  test: null
}

//vue의  computed와 비슷. 단순 값 반환으로 사용, state를 변경하는 곳이 아님. 정책위반)
//예)router:store.getters["cmmnStore/cmmnCode"]
//예)store:this.getters["cmmnStore/cmmnCode"]
//예)this.$store.getters["cmmnStore/loginUserYN"]
const getters = {
  ...make.getters(state),

};

//const mutations = make.mutations(state)
//vue의 methods와 비슷.동기적, state를 수정할 때 사용 commit
//예) store.commit('cmmnStore/test', {})
const mutations = {
  ...make.mutations(state),

  async setCmmnCode(state, payload) {
    state.cmmnCode = await payload
  },
  setCmmnMenu(state, payload) {
    state.cmmnMenu = payload
  },
  setCmmnLevelMenu(state, payload) {
    state.cmmnLevelMenu = payload
  },
  setLoginUser(state, payload) {
    state.loginUserYN = payload.loginUserYN // 실제 로그인 여부
    state.loginUserID = payload.loginUserID // 사용자 ID, 새로고침시 id를 유지하기 위해 사용
    state.loginUserNM = payload.loginUserNM // 사용자 이름,
    state.loginUserLevel = payload.loginUserLevel
  },
  resetData(state, payload) {  // 로그인 사용자의 정보와 공통 데이터를 초기화
    state.loginUserYN = null; // 실제 로그인 여부
    state.loginUserID = null; // 사용자 ID, 새로고침시 id를 유지하기 위해 사용
    state.loginUserNM = null; // 사용자 이름,
    state.loginUserLevel = "PC0404"; // 기본 비로그인
    state.loginUserMenu = [];
    state.cmmnCode= null;
    state.cmmnMenu = null;
    state.cmmnLevelMenu = null;

  },
  setLoginUserMenu(state, payload) { // 로그인 사용자의 정보와 공통 데이터를 초기화
    state.loginUserMenu = payload

  },
  test(state, payload) { // 로그인 사용자의 정보와 공통 데이터를 초기화
    state.test = 1234; // 실제 로그인 여부

  },
/*
  async  getList(state, payload) {
    var arr = await cmmnFnStore.fn.code(payload, "PC");
    state.cmmnCodeItem = arr;
  } */
};

//비동기를 사용할 때, 또는 여러 mutations을 동작(연달아 실행)시킬때(dispatch)
//예) router:store.dispatch("appStore/selectDrawerItem", menuList[i].menuNm);
//예) store:this.dispatch("menuStore/list");
//예) vue:this.$store.dispatch("menuStore/listShow");
const actions = {
  ...make.actions(state),

  code({
    state, // eslint-disable-line no-unused-vars
    commit, // eslint-disable-line no-unused-vars
  }, dataObj) {

    const url = '/internal/davis/web/cmmn/code'
    console.log("url: " + url)
    return  axios.post(url, {

    })
      .then( res => {
        cmmnFnStore.res(res);

        commit('setCmmnCode', res.data.list)
      //  commit('getList', res)
      })
      .catch(err => {
        cmmnFnStore.err(err);
        //commit('axiosError', err)
      })

  },
  getCodeGroup({
    state, // eslint-disable-line no-unused-vars
    commit, // eslint-disable-line no-unused-vars
  }, dataObj) {
    var arr =  cmmnFnStore.fn.code(this.cmmnCode, dataObj);
    state.cmmnCodeItem = arr;
    this.cmmnCode
  },
  setCmmnMenu({
    state, // eslint-disable-line no-unused-vars
    commit, // eslint-disable-line no-unused-vars
  }, dataObj) {
    commit('setCmmnMenu', dataObj)
  },
  setCmmnLevelMenu({
    state, // eslint-disable-line no-unused-vars
    commit, // eslint-disable-line no-unused-vars
  }, dataObj) {
    commit('setCmmnLevelMenu', dataObj)
  },
  setLoginUser({
    state, // eslint-disable-line no-unused-vars
    commit, // eslint-disable-line no-unused-vars
  }, dataObj) {
    commit('setLoginUser', dataObj)
  },
  resetData({
    state, // eslint-disable-line no-unused-vars
    commit, // eslint-disable-line no-unused-vars
  }, dataObj) {
    commit('resetData', dataObj)
  },
  async setCmmnData({
    state, // eslint-disable-line no-unused-vars
    commit, // eslint-disable-line no-unused-vars
  }, dataObj) {
    await this.dispatch("cmmnStore/code");
    await this.dispatch("menuStore/cmmnMenulist");
    await this.dispatch("levelMenuStore/cmmnLevelMenulist");
  },

  setLoginUserMenu({
    state, // eslint-disable-line no-unused-vars
    commit, // eslint-disable-line no-unused-vars
  }, dataObj) {
    commit('setLoginUserMenu', dataObj)
  },
}



export default {
  namespaced: true,
  state,
  mutations,
  actions,
  getters,
}
