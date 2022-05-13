// import axios from 'axios'

// export default {
//     // module
//     namespaced: true,

//     // data
//     state: () => ({
//         noticeList: []
//     }),

//     // computed 계산
//     getters: {
//     },

//     // methods
//     //mutations에서만 데이터를 변경할 수 있다.
//     mutations: {
//         updateState(state, payload) {

//             Object.keys(payload).forEach(key => {

//                 // 위의 방식을 아래처럼 변환 가능
//                 state[key] = payload[key]
//             })

//         },
//         resetMovies(state){
//             state.noticeList = [] //변수 값 초기화
//         }
//     },
//     //비동기로 동작
//     actions: {
//         async searchMovies({commit, state}, payload) {
//             // const {title, type, number, year} = payload;

//             const res = await axios.post(`http://localhost:8080/notice/list`);
//             const { Search, totalResults} = res.data;
//             commit('updateState', {
//                 movies: _uniqBy(Search, 'imdbID')
//             })
//             console.log(res.data);
//             console.log(totalResults);
//             console.log(typeof totalResults);

//             const total = parseInt(totalResults,10);
//             const pageLength = Math.ceil(total /10);

//             //추가 요청
//             if(pageLength > 1) {
//                 for(let page = 2; page <= pageLength; page += 1){                    
//                     if(page > (number / 10)) break;

//                     const res = await axios.get(`http://localhost:8080/notice/list`);
//                     const { Search } = res.data;
//                     commit('updateState', {
//                         movies: [
//                             ...state.noticeList, 
//                             ..._uniqBy(Search,'list')] //전개 연산자
//                     })
//                 }
//             }
//         }
//     }
// }