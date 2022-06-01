import axios from 'axios'
// Pathify
import {get, sync, call, make} from 'vuex-pathify'

    // Data
    let state = {
        noticeList: [],
        detailContents: []
    }

    // computed 계산
    const getters = {}

    const mutations = {
        setNoticeList: function(state, payload){
            state.noticeList = payload;
        },

        setDetailContents: function(state, payload){
            state.detailContents = payload;
        }
    }

    //비동기로 동작
    const actions = {

        //리스트 전체조회
        async listFetch({commit}) {
            const url = `/notice/list`
            await axios.post(url)
            .then(res => {
                commit('setNoticeList',res.data.data)
            })
            .catch(err => {
                alert('error 발생');
                console.log(err);
            })
        },

        //상세조회 (key : noticeNo)
        async noticeDetail({commit}, payload) {
            const url = `/notice/detail`;
            await axios.post(url, {
                noticeNo : payload.noticeNo
            })
            .then(res => {
                commit('setDetailContents',res.data.data)
            })
            .catch(err => {
                alert('error 발생');
                console.log(err);
            })
        },

        //공지사항 등록
        async noticeInsert({commit}, payload) {

            if(payload.noticeTitle == '' || payload.noticeTitle == null){
                alert("제목을 입력해주세요");
                return;
            }
            const url = `/notice/insert`;
            await axios.post(url, {
                noticeNo : payload.noticeNo,
                aptId: payload.aptId,
                noticeTitle : payload.noticeTitle,
                noticeContent : payload.noticeContent,
                noticeWriter : payload.noticeWriter,
                remark : payload.remark,
                insId : payload.insId,
            })
            .then(res => {
            })
            .catch(err => {
                alert('error 발생');
                console.log(err);
            })
        },

        //공지사항 업데이트
        async noticeUpdate({commit}, payload) {
            alert('update');
            const url = `/notice/update`;
            await axios.post(url, {
                noticeNo : payload.noticeNo,
                noticeTitle : payload.noticeTitle,
                noticeContent : payload.noticeContent,
                remark : payload.remark,
                updId : payload.updId
            })
            .then(res => {
            })
            .catch(err => {
                alert('error 발생');
                console.log(err);
            })
        },

        //공지사항 삭제 (key : noticeNo)
        async noticeDelete({commit}, payload) {
            const url = `/notice/delete`;
            await axios.post(url, {
                noticeNo : payload.noticeNo
            })
            .then(res => {
            })
            .catch(err => {
                alert('error 발생');
                console.log(err);
            })
        }
    }



export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters,
  }
