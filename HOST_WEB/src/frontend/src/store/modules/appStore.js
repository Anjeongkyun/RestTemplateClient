// Pathify
import { make } from 'vuex-pathify'

//아이콘 참고
//https://pictogrammers.github.io/@mdi/font/1.1.34/

// Data
const state = {
  drawer: null,
  drawerImage: true,
  mini: false,
  selectDrawerItem: "",
  items: [
    { // . 메인
      title: '메인',
      icon: 'mdi-view-dashboard',
      to: '/',
    },
    { // 로그인
      title: '로그인',
      icon: 'mdi-login',
      to: '/components/login/',
    },
    { // 비밀번호 변경
      title: '비밀번호변경',
      icon: 'mdi-key',
      to: '/components/change-password/',
    },

    { // 현장 상태 조회
      title: '현장상태조회',
      icon: 'mdi-laptop',
      to: '/components/site-status/',
    },
    { // 공지사항
      title: '공지사항',
      icon: 'mdi-bell',
      to: '/components/notice/',
    },
    { // 공지사항 상세조회
      title: '공지사항 상세조회',
      icon: 'mdi-bell',
      to: '/components/notice-detail',
    },
    { // 사용자
      title: '사용자',
      icon: 'mdi-account',
      to: '/components/user/',
    },
    { // 단지
      title: '단지',
      icon: 'mdi-city',
      to: '/components/park/',
    },
    { // 메뉴
      title: '메뉴',
      icon: 'mdi-view-list',
      to: '/components/menu/',
    },
    { // 권한별메뉴
      title: '권한별메뉴',
      icon: 'mdi-view-list',
      to: '/components/level-menu/',
    },
    {
      title: 'Dashboard',
      icon: 'mdi-view-dashboard',
      to: '/components/dashboard/',
    },
    {
      title: 'User Profile',
      icon: 'mdi-account',
      to: '/components/profile/',
    },
    {
      title: 'Regular Tables',
      icon: 'mdi-clipboard-outline',
      to: '/tables/regular/',
    },
    {
      title: 'Typography',
      icon: 'mdi-format-font',
      to: '/components/typography/',
    },
    {
      title: 'Icons',
      icon: 'mdi-chart-bubble',
      to: '/components/icons/',
    },
    {
      title: 'Google Maps',
      icon: 'mdi-map-marker',
      to: '/maps/google/',
    },
    {
      title: 'Notifications',
      icon: 'mdi-bell',
      to: '/components/notifications/',
    },
  ],
}

const mutations = make.mutations(state)

const actions = {
  ...make.actions(state),
  init: async ({ dispatch }) => {
    //

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
