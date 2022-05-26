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
      show: true
    },
    { // 로그인
      title: '로그인',
      icon: 'mdi-login',
      to: '/components/login/',
      show:false
    },
    { // 로그인
      title: '로그인',
      icon: 'mdi-login',
      to: '/login/',
      show: false
    },
    { // 비밀번호 변경
      title: '비밀번호변경',
      icon: 'mdi-key',
      to: '/components/change-password/',
      show: false
    },

    { // 현장 상태 조회
      title: '현장상태조회',
      icon: 'mdi-laptop',
      to: '/components/site-status/',
      show: true
    },
    { // 공지사항
      title: '공지사항',
      icon: 'mdi-bell',
      to: '/components/notice/',
      show: true
    },
    { // 공지사항 상세조회
      title: '공지사항 상세조회',
      icon: 'mdi-bell',
      to: '/components/notice-detail',
      show: false
    },
    { // 사용자
      title: '사용자',
      icon: 'mdi-account',
      to: '/components/user/',
      show: true
    },
    { // 단지
      title: '단지',
      icon: 'mdi-city',
      to: '/components/park/',
      show: true
    },
    { // 메뉴
      title: '메뉴',
      icon: 'mdi-view-list',
      to: '/components/menu/',
      show: true
    },
    { // 권한별메뉴
      title: '권한별메뉴',
      icon: 'mdi-view-list',
      to: '/components/level-menu/',
      show: true
    },
    {
      title: 'Dashboard',
      icon: 'mdi-view-dashboard',
      to: '/components/dashboard/',
      show: true
    },
    {
      title: 'User Profile',
      icon: 'mdi-account',
      to: '/components/profile/',
      show: true
    },
    {
      title: 'Regular Tables',
      icon: 'mdi-clipboard-outline',
      to: '/tables/regular/',
      show: true
    },
    {
      title: 'Typography',
      icon: 'mdi-format-font',
      to: '/components/typography/',
      show: true
    },
    {
      title: 'Icons',
      icon: 'mdi-chart-bubble',
      to: '/components/icons/',
      show: true
    },
    {
      title: 'Google Maps',
      icon: 'mdi-map-marker',
      to: '/maps/google/',
      show: true
    },
    {
      title: 'Notifications',
      icon: 'mdi-bell',
      to: '/components/notifications/',
      show: true
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
