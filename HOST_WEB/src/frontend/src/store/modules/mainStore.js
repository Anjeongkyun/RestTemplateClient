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
  }
}

const actions = {
  async login({
    state, // eslint-disable-line no-unused-vars
    commit, // eslint-disable-line no-unused-vars
  }, dataObj) { // eslint-disable-line no-unused-vars
    // state 값을 사용하기 때문에 인자사용,  로직을 실행하기위해 commit 사용, 그리고 그외 인자값

    //초기로그인 체크

    //    console.log(state)
    //    console.log(commit)
    //    commit('loginError')

    const url = '/internal/davis/web/login/login';
    console.log(url)
    var _this = this;
    await axios.post(url, {
      id: dataObj.loginId,
      password: dataObj.loginPw,
    })
      .then(res => {
        cmmnFnStore.res(res);

        if (res.data.resultCd != "00") return;



        var levelMenuList = this.state.levelMenuStore.levelMenuList;
        var menuList = this.state.menuStore.menuListShow;

        //초기로그인 체크
        if (res.data.firstRun == "Y") {
          alert("초기 비밀번호 변경이 필요합니다.");
          router.push('/main/change-password')
          return;
        };

        //로그인 사용자의 권한 체크
        levelMenuList.forEach(element => {
          if (res.data.levelCd == element.level) {
            res.data.level = (element.menuList).split(",")
          }
        });

        var arr = [];


        menuList.forEach(element => {
          if (!res.data.level) return;
          res.data.level.forEach(element1=> {
            if (element.menuId == element1) {

              arr.push(element)
            }

          });
        });

        commit('loginUserMenu', arr)

        if (res.data.levelCd == "Y") {
          alert("사용자의 권한");
          router.push('/components/change-password');
          return;
        };




        router.push("/");
        //  this.$router.push("/");
        return;

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
