<template dark>
  <v-container
    id="dashboard-view"
    fluid
    tag="section"
  />
</template>

<script>
import { get, set, sync, call } from 'vuex-pathify'

export default {
  name: 'MainView',
  data: () => ({
    loginId: null,
    loginPw: null,
  }),
  computed: {
  },
  methods: {

  },
}
</script>
<style scoped>

</style>
