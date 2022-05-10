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
              depressed
              color="primary"
              @click="
                changePassword({
                  userPw, 
                  changePw, 
                  changePwRe
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
  name: 'ChangePassword',
  data: () => ({
    userPw:null, 
    changePw:null, 
    changePwRe:null
  }),
  computed: {
    ...get('app', [
      'selectDrawerItem'
    ]),
  },
  methods: {
    changePassword:  call('changePassword/changePassword'),
  },
}

</script>
<style scoped>

</style>
