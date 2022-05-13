<template>
  <v-container
    id="regular-tables-view"
    fluid
    tag="section"
  >
    <view-intro
      heading="공지사항"
    />

    <material-card
      icon="mdi-clipboard-text"
      icon-small
      :title="selectDrawerItem"
      color="accent"
    >
      <v-simple-table>
        <thead>
          <tr>
            <th class="primary--text">
              번호
            </th>
            <th class="primary--text">
              작성자
            </th>
            <th class="primary--text">
              제목
            </th>
          </tr>
        </thead>

        <tbody>
          <tr>
            <td>1</td>
            <td>Dakota Rice</td>
            <td>Niger</td>
          </tr>

          <tr>
            <td>2</td>
            <td>Minverva Hooper</td>
            <td>Curaçao</td>
          </tr>

          <tr>
            <td>3</td>
            <td>Sage Rodriguez</td>
            <td>Netherlands</td>
          </tr>
        </tbody>
      </v-simple-table>
    </material-card>
  </v-container>
</template>

<script>
import { get, set, sync, call } from 'vuex-pathify'

export default {
  name: 'NoticeView',
  data: () => ({
  }),
  computed: {
    ...get('appStore', [
      'selectDrawerItem',
      'drawerImage'
    ]),

  },
  methods: {
  },
}
</script>
<style scoped>

</style>
