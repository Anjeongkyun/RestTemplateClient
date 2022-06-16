<template>
  <v-menu
    bottom
    left
    min-width="200"
    offset-y
    origin="top right"
    transition="scale-transition"
  >
    <template v-slot:activator="{ attrs, on }">
      <v-btn
        class="ml-2"
        min-width="0"
        text
        v-bind="attrs"
        v-on="on"
      >
        <v-icon>mdi-account</v-icon>
      </v-btn>
    </template>

    <v-list
      :tile="false"
      flat
      nav
    >
      <template v-for="(p, i) in profile">
        <v-divider
          v-if="p.divider"
          :key="`divider-${i}`"
          class="mb-2 mt-2"
        />

        <app-bar-item
          v-else
          :key="`item-${i}`"
          :to="p.to"
        >
          <v-list-item-title v-text="p.title" />
        </app-bar-item>
      </template>
    </v-list>
  </v-menu>
</template>

<script>
import { get, set, sync, call } from 'vuex-pathify'

export default {
  name: 'DefaultAccount',

  data: () => ({
    profile: [],
  }),
  mounted(){
    var loginUserYN = this.$store.getters["cmmnStore/loginUserYN"]

    if(loginUserYN ) {
      this.profile= [
        { title: ' 비밀번호 변경' ,to:"/components/change-password/"},
        { divider: true },
        { title: '로그아웃' ,to:"/components/login/" },
      ]
    }else{
      this.profile= [
        { title: ' 로그인' ,to:"/components/login/"},
      ]
    }

  },
  methods: {
    //  ...mapActions(['login_axios']), // login(){}를 사용하는것과 같음
    logout_old(){
      console.log(this)
    },
    async logout() {
      await this.signOut();

      this.$toast.info(`로그아웃 하였습니다.`);

      this.$router.push("/");


    },
  },
}
</script>
