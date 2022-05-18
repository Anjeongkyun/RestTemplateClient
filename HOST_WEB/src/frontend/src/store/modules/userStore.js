// Pathify
import {
  make
} from 'vuex-pathify'


const state = {
  parkSeq:'',
  id: '',

}

const mutations = make.mutations(state)

const actions = {
  user({
    state, // eslint-disable-line no-unused-vars
    commit, // eslint-disable-line no-unused-vars
  }, dataObj) {


    const url = '/internal/davis/web/park/list'
    console.log("dataObj")
    console.log(dataObj)
    axios.post(url, {
      id: dataObj.loginId,
      password: dataObj.loginPw,
    })
      .then(res => {
        cmmnStore.res(res);
      })
      .catch(err => {
        cmmnStore.err(err);
      })

  }
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
