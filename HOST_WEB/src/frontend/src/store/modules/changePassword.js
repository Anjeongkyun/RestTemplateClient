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
    console.log("dataObj")
    console.log(dataObj)

    
    if (dataObj.userPw !== dataObj.changePw) { 
      alert("새 비밀번호와 새 비밀번호(확인)이 일치하지 않습니다.")
    }
    
    axios.post(url, {
      id: "1",
      resetYn: "Y",
      password: dataObj.userPw,
      newPassword: dataObj.changePw,

    })
      .then(res => {
        console.log('axios.post res')
        console.log(res)
        if (res.status == 200) {
          alert(res.data.message)

          // if (res.data.status == 200) {
          //   alert(res.data.message)
          // } else if(res.status == 400) { 

          // }

        } else if (res.status == 400) {

        }
      })
      .catch(err => {
        console.log('axios.post err')
        console.log(err)
        alert(err)
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
