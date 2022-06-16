<template dark>
  <div>
    <v-container
      fluid
      tag="section"
    >
      <v-row
        align="center"
      >
        <v-col
          cols="3"
          align-self="center"
        />
        <v-col
          cols="6"
          align-self="center"
        >
          <v-card class="pa-3">
            <v-toolbar>
              <div class="pa-3">
                <i
                  class="mdi mdi-login"
                />
              </div>
              <div>  <v-toolbar-title v-text="selectDrawerItem" /></div>
            </v-toolbar>
            <div class="pa-3">
              <v-text-field
                v-model="loginId"
                class="pa-3"
                label="아이디 입력"
              />
              <v-text-field
                v-model="loginPw"
                label="비밀번호 입력"
                type="password"
              />
              <v-btn
                dark
                class="mb-3"
                block
                large
                depressed
                color="#e91e63 !important"
                @click="
                  fnLoginBtnClick()
                "
                v-text="selectDrawerItem"
              />
            </div>
          </v-card>
        </v-col>
        <v-col
          cols="3"
          align-self="center"
        />
      </v-row>
    </v-container>
  </div>
</template>

<script>
import '@/styles/overrides.sass'
import { get, set, sync, call } from 'vuex-pathify'

export default {
  name: 'LoginView',
  data: () => ({
    loginId: null,
    loginPw: null,
    alignments: [
      'start',
      'center',
      'end',
    ],
  }),
  computed: {
    ...get('appStore', [
      'selectDrawerItem',
      'drawerImage',
      'items',
      'version',
    ]),
    ...get('loginStore', [
      'data',
    ]),
    ...get('themeStore', [
      'dark',
      'gradient',
      'image',
    ]),

    ...sync('appStore', [
      'drawer',
      'drawerImage',
      'mini'
    ]),
  },
  created(){

  },
  mounted() {


  //  this.fnSetCmmnCode();
    //this.$nextTick(function () {   }); // 모든 화면이 렌더링된 후 실행
  },

  methods: {
    fnSetCmmnCode(){ // 공통으로 필요한 사전 데이터 조회
      this.$store.dispatch("menuStore/listShow");
      this.$store.dispatch("levelMenuStore/list");
    },
    //  ...mapActions(['login_axios']), // login(){}를 사용하는것과 같음
    //  fnLogin: call('loginStore/login'),
    fnLoginBtnClick(){
      this.$store
        .dispatch("loginStore/login",{
          loginId:  this.loginId, // v-model로 지정된 login_id text
          loginPw:  this.loginPw, // v-model로 지정된 login_pw text
        })
        .then(res => {
          if(!res) return;
          this.$router
            .push("/" )
            .catch(err => {
              alert(err);
              console.log(err)
            });
        });

    }

  },
}
</script>

<style scoped>

</style>
