// Pathify
import { make } from 'vuex-pathify'


import axios from 'axios'
import cmmnFnStore from './cmmnFnStore' //store 와 관련된 데이터를 처리하는 공통 함수 파일
import router from "@/router";

// Data
const state = {
  userPw: '',
  changePw: '',
  changePwRe: ''
}

const mutations = make.mutations(state)

const actions = {
  changePassword({
    state, // eslint-disable-line no-unused-vars
    commit, // eslint-disable-line no-unused-vars
  }, dataObj) {
    // state 값을 사용하기 때문에 인자사용,  로직을 실행하기위해 commit 사용, 그리고 그외 인자값\

    //    console.log(state)
    //    console.log(commit)
    //    commit('loginError')


    const url = '/internal/davis/web/change-password/change-password'


    //console.log("changePwRe:", changePwRe)
    //  console.log("state.changePwRe: ", state.changePwRe)

    //if (dataObj.changePw !== dataObj.changePwRe) {
    if (state.changePw !== state.changePwRe) {
      alert("새 비밀번호와 새 비밀번호(확인)이 일치하지 않습니다.");
      return;
    }

    axios.post(url, {
      id: "1",
      resetYn: "N",  // 비밀번호 변경시 N, 초기화면 Y
      password: state.userPw,
      newPassword: state.changePw,
    })
      .then(res => {
        cmmnFnStore.res(res);
        
        router.push({
          name: "MainView"
        })
      })
      .catch(err => {
        cmmnFnStore.err(err);
      })

  }
}

const getters = {}

export default {
  namespaced: true,
  state,
  mutations,
  actions,
  getters,
}
