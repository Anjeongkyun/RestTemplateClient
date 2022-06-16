<template>
  <v-list
    expand
    nav
    v-bind="$attrs"
    v-on="$listeners"
  >
    <template
      v-for="(item, i) in items"
    >
      <default-list-group
        v-if="item.items"
        :key="`group-${i}`"
        :item="item"
      />


      <default-list-item
        v-else
        :key="`item-${i}`"
        :item="item"
      />
    </template>
  </v-list>
</template>

<script>
import { get, set, sync, call } from 'vuex-pathify'

export default {
  name: 'DefaultList',
  components: {
    DefaultListGroup: () => import('./ListGroup'),
    DefaultListItem: () => import('./ListItem'),
  },

  props: {
    items: {
      type: Array,
      default: () => ([]),
    },
  },
  data() {
    return {
      data: 1,

    }
  },
  computed: {

  },
  mounted() {


  },


  methods:{


  },

}
</script>
