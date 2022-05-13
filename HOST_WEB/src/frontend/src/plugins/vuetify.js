// Vuetify Documentation https://vuetifyjs.com

import Vue from 'vue'
import Vuetify from 'vuetify/lib/framework'
import ripple from 'vuetify/lib/directives/ripple'

Vue.use(Vuetify, { directives: { ripple } })

const theme = {
  primary: '#E91E63', //pink
  secondary: '#9C27b0', //purple
  accent: '#e91e63',  //pink
  info: '#00CAE3',
  success: '#4CAF50', //green
  warning: '#FB8C00', //orange darken-1
  error: '#FF5252', //red accent-2
}

const theme1 = {
  primary: '#ff0000',
  secondary: '#ff0000',
  accent: '#ff0000',
  info: '#ff0000',
  success: '#ff0000',
  warning: '#FB8C00',
  error: '#FF5252',
}

export default new Vuetify({
  breakpoint: { mobileBreakpoint: 960 },
  icons: {
    values: { expand: 'mdi-menu-down' },
  },
  theme: {
    themes: {
      dark: theme,
      light: theme,
    },
  },
})
