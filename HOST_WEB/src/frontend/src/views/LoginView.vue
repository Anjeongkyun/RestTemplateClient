<template dark>
  <div>
    <v-container
      align="center"
      class="grey lighten-5 mb-6"
    >
      <div>
        <v-row
          align="center"
          class="pa-12"
        >
          <v-col
            class="pa-12"
            cols="3"
            align-self="center"
          />
        </v-row>  <v-row
          align="center"
          class="pa-12"
        >
          <v-col
            class="pa-12"
            cols="3"
            align-self="center"
          />
        </v-row>
        <v-row
          align="center"
          class="pa-12"
        >
          <v-col
            class="pa-12"
            cols="3"
            align-self="center"
          />
        </v-row>
      </div>
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

  methods: {
    //  ...mapActions(['login_axios']), // login(){}를 사용하는것과 같음
    login: call('loginStore/login'),

  },
}
</script>

<style scoped>

</style>
