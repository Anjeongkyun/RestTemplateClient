// Imports
import Vue from "vue";
import Router from "vue-router";
import { trailingSlash } from "@/util/helpers";
import { layout, route } from "@/util/routes";
import store from "@/store";

Vue.use(Router);

const rolesPC0401 = "PC0401" // 시스템관리자  //[]; //아무도 못봄
const rolesPC0402 = "PC0402" // 일반관리자
const rolesPC0403 = "PC0403" // 사용자
const rolesPC0404 = "PC0404" // 비로그인

const router = new Router({
  mode: "history", //URL에 해시를 제거하여 API를 이용하여 페이지를 다시 로드하지 않고도 URL 변경
  base: process.env.BASE_URL, //vue.config.js 파일에 설정된 vue-cli 의 publicPath
  scrollBehavior: (to, from, savedPosition) => {
    if (to.hash) return { selector: to.hash };
    if (savedPosition) return savedPosition;

    return { x: 0, y: 0 };
  },
  routes: [
    layout("Main", [
      route("MainView", null, "", {
        roles: [rolesPC0401, rolesPC0402, rolesPC0403, rolesPC0404,null], //requiresAuth로 로그인 판단 후에 권한 판단
      }),  // 최상위로 올라와야함
      /*   route("MainLoginView", null, "/main/login/", {
        roles: [ ],
        requiresAuth:true  // true: 로그인해야 접근가능
      }), */
    /*   route("MainChangePasswordView", null, "/main/change-password/", {
        roles: [rolesPC0401, rolesPC0402, rolesPC0403],
        requiresAuth: true // true: 로그인해야 접근가능
      }) */
      //  route("MainView", null, "/main"),
    ]),

    layout("Default", [
      route('LoginView', null, '/components/login', {
        roles: [rolesPC0401, rolesPC0402, rolesPC0403, rolesPC0404,null],
      }),
      route("ChangePasswordView", null, "/components/change-password/", {
        roles: [rolesPC0401, rolesPC0402, rolesPC0403],
      }),
      route("MainDashBoardView", null, "/components/main-dashboard", {
        roles: [rolesPC0401, rolesPC0402, rolesPC0403],
      }),
      //  route("MainView", null, "/main"),
      route("SiteStatusView", null, "/components/site-status/", {
        roles: [rolesPC0401, rolesPC0402, rolesPC0403],
      }),
      route("NoticeView", null, "/components/notice/", {
        roles: [rolesPC0401, rolesPC0402, rolesPC0403],
      }),
      route("NoticeDetailView", null, "/components/notice-detail/:noticeNo", {
        roles: [rolesPC0401, rolesPC0402, rolesPC0403],
      }),
      route("UserView", null, "/components/user/", {
        roles: [rolesPC0401, rolesPC0402, rolesPC0403],
      }),
      route("ParkView", null, "/components/park/", {
        roles: [rolesPC0401, rolesPC0402, rolesPC0403],
      }),
      route("MenuView", null, "/components/menu/", {
        roles: [rolesPC0401, rolesPC0402, rolesPC0403],
      }),
      route("LevelMenuView", null, "/components/level-menu/", {
        roles: [rolesPC0401, rolesPC0402, rolesPC0403],
      }),
      // Pages
      route("Dashboard", null, "/components/dashboard/", {
        roles: [rolesPC0401, rolesPC0402, rolesPC0403, null],
      }),
      route("UserProfile", null, "/components/profile/", {
        roles: [rolesPC0401, rolesPC0402, rolesPC0403, null],
      }),

      // Components
      route("Notifications", null, "/components/notifications/", {
        roles: [rolesPC0401, rolesPC0402, rolesPC0403, null],

      }),
      route("Icons", null, "/components/icons/", {
        roles: [rolesPC0401, rolesPC0402, rolesPC0403, null],

      }),
      route("Typography", null, "/components/typography/", {
        roles: [rolesPC0401, rolesPC0402, rolesPC0403, null],
      }),

      // Tables
      route("RegularTables", null, "/tables/regular/", {
        roles: [rolesPC0401, rolesPC0402, rolesPC0403, null],
      }),

      // Maps
      route("GoogleMaps", null, "/maps/google/", {
        roles: [],
      }),
      //route("MainView", null, "/"),
      //  route("MainView", null, "/"),
      //  route('LoginView', null, 'components/login'),
    ]),
    //  route("MainLoginView", null, "/components/login/"),
    //  route("MainView", null, "/main/"),
    //route("MainChangePasswordView", null, "/main/change-password/")

    /*   {
      path: '/*',
      redirect: '/'
    }, */
  ]
});

router.beforeEach
( async  (to, from, next) => {



  if (to.path == "/components/login/") {
    await store.dispatch("cmmnStore/resetData"); //공통 데이터 및 로그인 정보 초기화
    await store.dispatch("cmmnStore/setCmmnData");  // 공통 정보 불러오기
  }
  var loginUserYN = store.getters["cmmnStore/loginUserYN"]
  var loginUserLevel = store.getters["cmmnStore/loginUserLevel"]
  var menuList = store.getters["cmmnStore/cmmnMenu"]
  var levelMenuList = store.getters["cmmnStore/cmmnLevelMenu"]
  console.log("loginUserYN: ", loginUserYN)
  console.log("loginUserLevel: ", loginUserLevel)
  console.log("menuList: ", menuList)
  console.log("levelMenuList: ", levelMenuList)

  if (loginUserYN) {
    //  console.log("loginUserYN: ", loginUserYN)
  } else {
    //  console.log("loginUserYN: ", loginUserYN)

  }

  var setLoginUserMenu = [];
  levelMenuList.forEach(e => {
    if (e.level == loginUserLevel) {

      var arrList = (e.menuList).split(",");  //1,2,3,4,5
      arrList.forEach(e1 => {  // 사용자가 볼수 있는 메뉴
        menuList.forEach(e2 => {  // 전체 메뉴
          if (e2.menuShowYn == "Y") {  // 화면에 표시가능한 메뉴
            if (e1 == e2.menuId) {  // 메뉴 ID가 같다면 현재 사용자가 볼수 있는 화면
              setLoginUserMenu.push(e2);
            }  
      
          }
        });

      
      });

      
    }
  });


  store.dispatch("cmmnStore/setLoginUserMenu", await setLoginUserMenu); // 공통 정보 불러오기


  var loginUserMenu = store.getters["cmmnStore/loginUserMenu"]
  
  /*   //현재 사용자의 권한에 따라 페이지 접근 제한
  if (to.meta.roles && !to.meta.roles.includes(loginUserLevel)) {
  //  alert('해당 페이지에 접근 권한이 없습니다.\n로그인 후 이용해주세요.')
  //  return next(from)
  } else {

    var loginUserYN = store.getters["cmmnStore/loginUserYN"]
    
  

    return next()
  } */

  //현재 선택된 페이지의 이름을 화면에 매핑
  //  var menuList = store.state.cmmnStore.cmmnMenu;
  var menuList = store.getters["cmmnStore/cmmnMenu"]
  for (var i in menuList) {
    if (to.path == menuList[i].menuRouterTo) { //  if (to.path == menuList[i].to) {
      //console.log(menuList[i].menuNm)
      //  store.state.appStore.selectDrawerItem = menuList[i].menuNm;
      store.dispatch("appStore/setSelectDrawerItem", menuList[i].menuNm);
    }
  }
  return next()
  //router.go();
//  return to.path.endsWith("/") ? next() : next(trailingSlash(to.path));
});

export default router;
