// Pathify
import { get, sync, call, make } from "vuex-pathify";

import axios from "axios";
// Data
const state = {
  parkList: [],
  detailContents: []
};

const mutations = {
  setParkList: function(state, payload) {
    state.parkList = payload;
  },

  setDetailContents: function(state, payload) {
    state.detailContents = payload;
  }
};
const actions = {
  async parkListFetch({ commit }, payload) {
    const url = `/parks`;
    await axios
      .get(url)
      .then(res => {
        commit("setParkList", res.data.data);
      })
      .catch(err => {
        alert("error 발생");
        console.log(err);
      });
  },
  //상세조회 (key : aptId)
  async parkDetail({ commit }, payload) {
    const url = `/parks/` + payload.aptId;
    await axios
      .get(url)
      .then(res => {
        console.log(res.data.data);
        commit("setDetailContents", res.data.data);
      })
      .catch(err => {
        alert("error 발생");
        console.log(err);
      });
  },

  //단지 정보 등록
  async parkInsert({ commit }, payload) {
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

  //공지사항 업데이트
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
  }
};

const getters = {};

export default {
  namespaced: true,
  state,
  mutations,
  actions,
  getters
};
