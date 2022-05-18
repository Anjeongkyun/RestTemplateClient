import axios from 'axios'
// Pathify
import {get, sync, call, make} from 'vuex-pathify'
import _uniqBy from 'lodash/uniqBy'

    // Data
    let state = {
        noticeList: []
    }    

    // computed 계산
    const getters = {}

    const mutations = make.mutations(state)
    
    //비동기로 동작
    const actions = {
        async listFetch() {
            const url = `http://localhost:8080/notice/list`;
            const res = await axios.post(url)
            .then(res => {                
                console.log(res.data.data);
                state.noticeList = res.data.data;                
            })
            .catch(err => {
                alert('error 발생');
                console.log(err);
            })
        },

        async listDetail({state,commit}, dataObj) {

            console.log(dataObj);

            const url = `http://localhost:8080/notice/detail`;
            const res = await axios.post(url, {
                noticeNo : dataObj.noticeNo
            })
            .then(res => {                
                state.noticeList = res.data.data;                
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