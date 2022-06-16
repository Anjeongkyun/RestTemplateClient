<template>
  <v-fade-transition mode="out-in">
    <router-view />
  </v-fade-transition>
</template>

<script>
// Styles
import '@/styles/overrides.sass'
import { get, set, sync, call } from 'vuex-pathify'

export default {
  name: 'App',
  components: {

  },
  data(){
    return{

    }
  },
  computed: {
    ...get('appStore', [
      'selectDrawerItem',
      'drawerImage',
      //  'items',
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

  async mounted(){

  },
  metaInfo: {
    title: 'App',
    titleTemplate: 'DAVIS',
    htmlAttrs: { lang: 'en' },
    meta: [
      { charset: 'utf-8' },
      { name: 'viewport', content: 'width=device-width, initial-scale=1' },
    ],
  },
}
</script>
