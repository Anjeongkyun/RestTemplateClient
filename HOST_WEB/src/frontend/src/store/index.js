// Vue
import Vue from "vue";
import Vuex from "vuex";
import pathify from "@/plugins/vuex-pathify";
import axios from "axios";
import createPersistedState from 'vuex-persistedstate';
import VueMarkdownEditor from "@kangc/v-md-editor";
import "@kangc/v-md-editor/lib/style/base-editor.css";
import vuepressTheme from "@kangc/v-md-editor/lib/theme/vuepress.js";

// Modules
import * as modules from "./modules";

Vue.prototype.$http = axios;

VueMarkdownEditor.use(vuepressTheme);

Vue.use(VueMarkdownEditor);

Vue.use(Vuex);
const state = {};
const getters = {};
const mutations = {};
const actions = {};

const store = new Vuex.Store({
  modules,
  state,
  getters,
  mutations,
  actions,
  plugins: [
    pathify.plugin,
    createPersistedState({ //vuex-persistedstate 사용
      whiteList: ["scalar", "person.name"],
      paths: ["cmmnStore"] , // 필요한 데이터만 관리
      key: 'vuexStore', // storate의 item 이름 설정
      storage: window.sessionStorage, // localStorage(default) 또는 sessionStorage 중 선택
    })
  ]
});

store.dispatch("appStore/init");

export default store;

export const ROOT_DISPATCH = Object.freeze({ root: true });
