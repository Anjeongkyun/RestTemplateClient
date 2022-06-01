// Imports
import Vue from "vue";
import Router from "vue-router";
import { trailingSlash } from "@/util/helpers";
import { layout, route } from "@/util/routes";
import store from "@/store";

Vue.use(Router);

const router = new Router({
  mode: "history",
  base: process.env.BASE_URL,
  scrollBehavior: (to, from, savedPosition) => {
    if (to.hash) return { selector: to.hash };
    if (savedPosition) return savedPosition;

    return { x: 0, y: 0 };
  },
  routes: [
    layout("Default", [
      route("MainView", null, ""),
      //  route('LoginView', null, 'components/login'),
      route("ChangePasswordView", null, "/components/change-password/"),
      route("SiteStatusView", null, "/components/site-status/"),
      route("NoticeView", null, "/components/notice/"),
      route("NoticeDetailView", null, "/components/notice-detail/:noticeNo"),
      route("UserView", null, "/components/user/"),
      route("ParkView", null, "/components/park/"),
      route("MenuView", null, "/components/menu/"),
      route("LevelMenuView", null, "/components/level-menu/"),

      // Pages
      route("Dashboard", null, "/components/dashboard/"),
      route("UserProfile", null, "/components/profile/"),

      // Components
      route("Notifications", null, "/components/notifications/"),
      route("Icons", null, "/components/icons/"),
      route("Typography", null, "/components/typography/"),

      // Tables
      route("Regular Tables", null, "/tables/regular/"),

      // Maps
      route("Google Maps", null, "/maps/google/")
    ]),

    route("LoginView", null, "/components/login/") //

    /*   {
      path: '/*',
      redirect: '/'
    }, */
  ]
});

router.beforeEach((to, from, next) => {
  if (to.path == "/login/") {
    localStorage.removeItem("loginUserID");
    localStorage.removeItem("loginUserYN");
  }

  var localStorageLoginUserID = localStorage.getItem("loginUserID");
  var localStorageLoginUserYN = localStorage.getItem("loginUserYN");
  console.log("loginUser:" + localStorageLoginUserYN);

  var items = store.state.appStore.items;
  for (var i in items) {
    if (to.path == items[i].to) {
      store.state.appStore.selectDrawerItem = items[i].title;
    }
  }

  if (!localStorageLoginUserYN) {
    console.log("로그인 필요" + localStorageLoginUserYN);
  } else {
    console.log("로그인 중" + localStorageLoginUserYN);
  }

  return to.path.endsWith("/") ? next() : next(trailingSlash(to.path));
});

export default router;
