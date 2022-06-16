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
  parkList : []
}

const mutations = {
  setParkList: function(state, payload){
    state.parkList = payload;
  }
}
const actions = {
  async parkListFetch({commit}, payload) {

    const url = `/park/list`
    await axios.post(url)
      .then(res => {
        // console.log(payload);
        commit('setParkList',res.data.data)
      })
      .catch(err => {
        alert('error 발생');
        console.log(err);
      })

  },

  //단지 정보 등록
  async parkInsert({ commit }, payload) {
    if (payload.aptNm == "" || payload.aptNm == null) {
      alert("아파트 이름을 입력해주세요");
      return;
    }

    const url = `/parks`;
    await axios
      .post(url, {
        aptNm: payload.aptNm,
        latitude: payload.latitude,
        longitude: payload.longitude,
        insId: payload.insId
      })
      .then(res => {})
      .catch(err => {
        alert("error 발생");
        console.log(err);
      });
  },

  //단지 업데이트
  async parkUpdate({ commit }, payload) {
    const url = `/parks`;
    await axios
      .put(url, {
        aptId: payload.aptId,
        aptNm: payload.aptNm,
        latitude: payload.latitude,
        longitude: payload.longitude,
        managerNm: payload.managerNm
      })
      .then(res => {})
      .catch(err => {
        alert("error 발생");
        console.log(err);
      });
  },

  //단지 정보 삭제
  async parkDelete({ commit }, payload) {
    const url = `/parks/` + payload.aptId;
    await axios
      .delete(url)
      .then(res => {
        alert("삭제 완료");
      })
      .catch(err => {
        alert("error 발생");
        console.log(err);
      });
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
