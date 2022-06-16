// Pathify
import { make } from 'vuex-pathify'

import axios from 'axios'
import cmmnFnStore from './cmmnFnStore' //store 와 관련된 데이터를 처리하는 공통 함수 파일
import router from "@/router";

// Data
const state = {
  items: []
}

const mutations = {
  ...make.mutations(state), //sync 사용가능
  loginUserMenu(state, payload) {
    state.items = payload
  },

}

const actions = {
  async login({
    state, // eslint-disable-line no-unused-vars
    commit, // eslint-disable-line no-unused-vars
  }, dataObj) { // eslint-disable-line no-unused-vars
    // state 값을 사용하기 때문에 인자사용,  로직을 실행하기위해 commit 사용, 그리고 그외 인자값

    const url = '/internal/davis/web/login/login';
    console.log(url)
    var _this = this;
    return await  axios.post(url, {
      id: dataObj.loginId,
      password: dataObj.loginPw,
    })
      .then(res => {
        cmmnFnStore.res(res);

        if (res.data.resultCd != "00") return false;  // 정상이 아니면 return

        
        this.dispatch("cmmnStore/setLoginUser", {
          loginUserYN:false,
          loginUserID: dataObj.loginId,  // 사용자 ID만 넣음.
          loginUserNM: null,
          loginUserLevel: "PC0404",
        });

        //초기로그인 체크
        if (res.data.list.firstRun == "Y") {
          alert("초기 비밀번호 변경이 필요합니다.");
          router.push('/main/change-password')
          return false;
        };

        this.dispatch("cmmnStore/setLoginUser", {
          loginUserYN: true,
          loginUserID: res.data.list.loginId,
          loginUserNM: res.data.list.memberMm,
          loginUserLevel: res.data.list.levelCd,
        });


        //  router.push("/");
        return true;

      })

      .catch(err => {
        cmmnFnStore.err(err);
        return err
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
