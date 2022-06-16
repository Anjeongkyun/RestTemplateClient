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
          <v-card>
            <v-toolbar>
              <div class="pa-3">
                <i
                  class="mdi mdi-key"
                />
              </div>
              <div>  <v-toolbar-title v-text="selectDrawerItem" /></div>
            </v-toolbar>
            <div class="pa-3">
              <v-text-field
                v-model="userPw"
                label="현재 비밀번호 입력"
                type="password"
              />
              <v-text-field
                v-model="changePw"
                label="새 비밀번호 입력"
                type="password"
              />
              <v-text-field
                v-model="changePwRe"
                label="새 비밀번호(확인) 입력"
                type="password"
              />

              <v-btn
                class="mb-3"
                block
                large
                dark
                color="#e91e63 !important"
                depressed
                @click="
                  changePassword()
                "
                v-text="selectDrawerItem"
              />
              <br>
              <v-btn
                dark
                class="mb-3"
                block
                large
                depressed
                color="#4CAF50 !important"
                @click="
                  login()
                "
                v-text="`로그인 페이지로 이동`"
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
import { get, set, sync, call } from 'vuex-pathify'

export default {
  name: 'ChangePasswordView',
  data: () => ({

  }),
  computed: {
    ...get('appStore', [
      'selectDrawerItem'
    ]),
    ...sync('changePasswordStore',['userPw','changePw','changePwRe'])
  },
  methods: {
    changePassword:  call('changePasswordStore/changePassword'),
    login(){
      this.$router
        .push("/components/login/")
        .catch(err => {});

    }
  },
}

</script>
<style scoped>

</style>
