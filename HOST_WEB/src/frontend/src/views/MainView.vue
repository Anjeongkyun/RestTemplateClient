<template dark>
  <div>
    <v-container
      align="center"
      class="grey lighten-5 mb-6"
    >
      <div>
        <v-row>
          <v-col
            :cols="12"
          >
            <material-card
              icon="mdi-account"
              icon-small
              :title="selectDrawerItem"
              color="accent"
            />
          </v-col>
        </v-row>
      </div>
    </v-container>
  </div>
</template>

<script>
import { get, set, sync, call } from 'vuex-pathify'

export default {
  name: 'MainView',
  components: {


  },
  data: () => ({
    loginId: null,
    loginPw: null,
    loginUser : null
  }),
  computed: {
    ...get('appStore', [
      'selectDrawerItem'
    ]),
  },
  mounted(){
  },
  methods: {

  },
}
</script>
<style scoped>

</style>
