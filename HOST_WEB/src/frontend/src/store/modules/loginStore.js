// Pathify
import { make } from 'vuex-pathify'

import axios from 'axios'
import cmmnFnStore from './cmmnFnStore' //store 와 관련된 데이터를 처리하는 공통 함수 파일
import router from "@/router";

// Data
const state = {
}

const mutations = make.mutations(state)  //sync 사용가능

const actions = {
  async login({
    state, // eslint-disable-line no-unused-vars
    commit, // eslint-disable-line no-unused-vars
  }, dataObj) { // eslint-disable-line no-unused-vars
    // state 값을 사용하기 때문에 인자사용,  로직을 실행하기위해 commit 사용, 그리고 그외 인자값


    //    console.log(state)
    //    console.log(commit)
    //    commit('loginError')

    const url = '/internal/davis/web/login/login'
    var _this = this
    await axios.post(url, {
      id: dataObj.loginId,
      password: dataObj.loginPw,
    })
      .then(res => {
        cmmnFnStore.res(res);

        //  sessionStorage.setItem("loginUser", dataObj.loginId);
        localStorage.setItem("loginUserID", dataObj.loginId);
        localStorage.setItem("loginUserYN", true);
        //console.log(this)
        //  console.log(router)
        //  router.push("/components/notice");
        //this.$router.push("/login");
      })
      .then(()=> {
        return router.push('/')
      })

      .catch(err => {
        cmmnFnStore.err(err);
      })

  /*   router.push({
      name: "NoticeView"
    }) */
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
