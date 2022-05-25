<template dark>
  <v-container
    id="dashboard-view"
    fluid
    tag="section"
  >
    메인
  </v-container>
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
  },
  mounted(){

  },
  methods: {

  },
}
</script>
<style scoped>

</style>
