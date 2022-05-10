// Pathify
import {
  get,
  sync,
  call,
  make
} from 'vuex-pathify'

import axios from 'axios'
// Data
const state = {
  data:'1'
}

const mutations = make.mutations(state)

const actions = {
  login({
    state, // eslint-disable-line no-unused-vars
    commit, // eslint-disable-line no-unused-vars
  }, dataObj) { // eslint-disable-line no-unused-vars
    // state 값을 사용하기 때문에 인자사용,  로직을 실행하기위해 commit 사용, 그리고 그외 인자값

    
    //    console.log(state)
    //    console.log(commit)
    //    commit('loginError')

    const url = '/internal/davis/web/login/login'
    console.log("dataObj")
    console.log(dataObj)
    axios.post(url, {
      id: dataObj.loginId,
      password: dataObj.loginPw,
    //  loginId: dataObj.loginId,
    //  loginPw: dataObj.loginPw
    })
      .then(res => {
      //   data: { 
      //   firstRun: ""
      //   level: ""
      //   resetPassword: ""
      //   resultCd: "00"
      //   resultMsg: "조회 성공했습니다."
      // }
        console.log('axios.post res')
        console.log(res)
        if (res.status == 200) { 
          alert(res.data.resultMsg)

          if (res.data.resultCd == "00") {
            alert(res.data.resultMsg)
          } else { 
            alert(res.data.resultMsg)
          }
          
        } else if(res.status == 400) { 

        }
      })
      .catch(err => {
        console.log('axios.post err')
        console.log(err)
        alert(err)
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
