<template>
  <v-container>
    <v-row align="center">
      <!-- <v-col
        v-for="(link, i) in links"
        :key="i"
        class="text-center"
        cols="6"
        md="auto"
      >
        <a
          :href="link.href"
          class="text-decoration-none text-uppercase text-caption font-weight-regular"
          rel="noopener"
          target="_blank"
          v-text="link.text"
        />
      </v-col> -->

      <v-spacer class="hidden-sm-and-down" />

      <v-col
        cols="12"
        md="auto"
      >
        <!-- <div class="text-body-1 font-weight-light pt-6 pt-md-0 text-center">
          &copy; {{ (new Date()).getFullYear() }}, Made by <v-icon>mdi-vuetify</v-icon>
          <a
            href="https://vuetifyjs.com/en/about/meet-the-team/#company"
            class="text-decoration-none"
          >Vuetify</a>
        </div> -->
        <div align="right">
          서울시 금천구 가산디지털1로 219 벽산디지털밸리 6차 512호 상호명: 주식회사 다온텍<br>
          사업자등록번호: 899-81-00506 대표번호: 02-3283-3030 팩스: 02-3283-3030<br>
          Copyright ⓒ DAONTEC co.,ltd. All Rights Reserved. (DAVIS 1.0.0)<br>
        </div>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: 'Links',

  data: () => ({
    links: [
      {
        href: 'https://vuetifyjs.com/',
        text: 'Vuetify Docs',
      },
      {
        href: '#',
        text: 'About Us',
      },
      {
        href: '#',
        text: 'Blog',
      },
      {
        href: '#',
        text: 'Licenses',
      },
    ],
  }),
}
</script>

<style lang="sass" scoped>
  a
    color: inherit !important
</style>
