<template>
  <v-app-bar
    id="default-app-bar"
    app
    absolute
    class="v-bar--underline"
    color="transparent"
    :clipped-left="$vuetify.rtl"
    :clipped-right="!$vuetify.rtl"
    height="70"
    flat
  >
    <v-app-bar-nav-icon
      class="hidden-md-and-up"
      @click="drawer = !drawer"
    />

    <default-drawer-toggle class="hidden-sm-and-down" />
    
    <v-toolbar-title
      class="font-weight-light text-h5"
      v-text="selectDrawerItem"
    />


    <v-spacer />

    <default-account />
  </v-app-bar>
</template>

<script>
// Utilities
import { get, sync } from 'vuex-pathify'

export default {
  name: 'DefaultBar',

  components: {
    DefaultAccount: () => import(
      /* webpackChunkName: "default-account" */
      '../widgets/Account'
    ),
    DefaultDrawerToggle: () => import(
      /* webpackChunkName: "default-drawer-toggle" */
      '../widgets/DrawerToggle'
    ),

  },
  data(){
    return{

    }
  },
  computed: {
    ...sync('appStore', [
      'drawer',
      'mini',
    ]),
    ...get('appStore', [
      'selectDrawerItem'
    ]),
    //  name: get('route/name'),

  },
  method:{

  },
  mounted() {

  },
}
</script>
