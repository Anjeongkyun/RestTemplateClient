<template>
  <div v-if="item.menuShowYn">
    <v-list-item
      :href="item.href"
      :rel="item.href ? 'nofollow' : undefined"
      :target="item.href ? '_blank' : undefined"
      :to="item.menuRouterTo"
      active-class="primary white--text"
      link
      class="py-1"
      v-bind="$attrs"
      v-on="$listeners"
    >
      <v-list-item-icon
        v-if="!item.menuIcon"
        class="text-caption text-uppercase justify-center ml-1 my-2 align-self-center"
      />

      <v-list-item-avatar v-if="item.avatar">
        <v-img :src="item.avatar" />
      </v-list-item-avatar>

      <v-list-item-icon
        v-if="item.menuIcon"
        class="my-2 align-self-center"
      >
        <v-icon v-text="item.menuIcon" />
      </v-list-item-icon>

      <v-list-item-content v-if="item.menuNm">
        <v-list-item-title v-text="item.menuNm" />
      </v-list-item-content>
    </v-list-item>
  </div>
</template>

<script>
export default {
  name: 'DefaultListItem',

  props: {
    item: {
      type: Object,
      default: () => ({}),
    },
  },

  computed: {
    title () {
      const matches = this.item.title.match(/\b(\w)/g)

      return matches.join('')
    },
  },
}
</script>
