<template>
  <v-list-group
    :color="gradient !== 1 ? 'white' : undefined"
    :group="group"
    :prepend-icon="item.icon"
    eager
    v-bind="$attrs"
  />
</template>

<script>
// Utilities
import { get } from 'vuex-pathify'

export default {
  name: 'DefaultListGroup',

  components: {
  //  DefaultListItem: () => import('./ListItem'),
  },

  props: {
    item: {
      type: Object,
      default: () => ({}),
    },
  },

  computed: {
    gradient: get('themeStore/drawer@gradient'),
    group () {
      return this.genGroup(this.item.items)
    },
    title () {
      const matches = this.item.title.match(/\b(\w)/g)

      return matches.join('')
    },
  },

  methods: {
    genGroup (items) {
      return items.reduce((acc, cur) => {
        if (!cur.to) return acc

        acc.push(
          cur.items
            ? this.genGroup(cur.items)
            : cur.to.slice(1, -1),
        )

        return acc
      }, []).join('|')
    },
  },
}
</script>
