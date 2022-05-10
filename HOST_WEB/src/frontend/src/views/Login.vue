<template dark>
  <v-container>
    <v-row 
      justify="center"
    >
      <v-col
        cols="4"
        sm="12"
        md="8"
      >
        <v-card>
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
              label="아이디 입력"
            />
            <v-text-field
              v-model="loginPw"
              label="비밀번호 입력"
              type="password"
            />
            <v-btn
              class="mb-3"
              block
              large
              depressed
              color="primary"
              @click="
                login({
                  loginId, // v-model로 지정된 login_id text
                  loginPw, // v-model로 지정된 login_pw text
                })
              "
              v-text="selectDrawerItem"
            />
          </div>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { get, set, sync, call } from 'vuex-pathify'

export default {
  name: 'Login',
  data: () => ({
    loginId: null,
    loginPw: null,
  }),
  computed: {  
    ...get('app', [
      'selectDrawerItem',
      'drawerImage'
    ]),
    ...get('login', [
      'data',
    ]),
  },
  methods: {
    //  ...mapActions(['login_axios']), // login(){}를 사용하는것과 같음
    login: call('login/login'),
  },
}
</script>

<style scoped>

</style>
