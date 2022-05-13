<template dark>
  <v-container>
    <v-row>
      <v-col
        cols="12"
      >
        <material-card
          icon="mdi-account"
          icon-small
          :title="selectDrawerItem"
          color="accent"
        >
          <material-card
            color="white"
            border
            full-header
          >
            <template #heading>
              <div class="pa-3 white--text">
                <v-row
                  dense
                >
                  <v-col
                    class="d-flex justify-center mb-1"
                    cols="12"
                    sm="6"
                  >
                    <v-select
                      v-model="select"
                      light
                      solo
                      dense
                      label="주차장식별을 위한 고유 값"
                      color="pink"
                      background-color="pink lighten-5"
                      :items="items1"
                      item-text="state"
                      item-value="abbr"
                      :hint1="`${select.state}, ${select.abbr}`"
                      hint="주차장식별을 위한 고유 값"
                      persistent-hint
                      return-object
                      single-line
                    />
                  </v-col>
                </v-row>
              </div>
            </template>
            <v-card-text>
              <v-data-table
                :headers="headers"
                :items="items"
              />
            </v-card-text>
          </material-card>
        </material-card>
      </v-col>
      <v-col
        cols="12"
      >
        <material-card
          icon="mdi-account"
          icon-small
          :title="selectDrawerItem"
          color="accent"
        >
          <v-simple-table>
            <thead>
              <tr>
                <th class="primary--text">
                  주차장 고유번호
                </th>
                <th class="primary--text">
                  App 고유 ID(key)
                </th>
                <th class="primary--text">
                  휴대폰 번호
                </th>
                <th class="primary--text">
                  등록자
                </th>
                <th class="primary--text">
                  등록일시
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
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { get, set, sync, call } from 'vuex-pathify'

export default {
  name: 'User',
  data: () => ({
    select: { state: 'Florida', abbr: 'FL' },
    items1: [
      { state: 'Florida', abbr: 'FL' },
      { state: 'Georgia', abbr: 'GA' },
      { state: 'Nebraska', abbr: 'NE' },
      { state: 'California', abbr: 'CA' },
      { state: 'New York', abbr: 'NY' },
    ],

    headers: [
      {
        sortable: false,
        text: 'ID',
        value: 'id',
      },
      {
        sortable: false,
        text: 'Name',
        value: 'name',
      },
      {
        sortable: false,
        text: 'Salary',
        value: 'salary',
        align: 'right',
      },
      {
        sortable: false,
        text: 'Country',
        value: 'country',
        align: 'right',
      },
      {
        sortable: false,
        text: 'City',
        value: 'city',
        align: 'right',
      },
    ],
    items: [
      {
        id: 1,
        name: 'Dakota Rice',
        country: 'Niger',
        city: 'Oud-Tunrhout',
        salary: '$35,738',
      },
      {
        id: 2,
        name: 'Minerva Hooper',
        country: 'Curaçao',
        city: 'Sinaai-Waas',
        salary: '$23,738',
      },
      {
        id: 3,
        name: 'Sage Rodriguez',
        country: 'Netherlands',
        city: 'Overland Park',
        salary: '$56,142',
      },
      {
        id: 4,
        name: 'Philip Chanley',
        country: 'Korea, South',
        city: 'Gloucester',
        salary: '$38,735',
      },
      {
        id: 5,
        name: 'Doris Greene',
        country: 'Malawi',
        city: 'Feldkirchen in Kārnten',
        salary: '$63,542',
      },
    ],

  }),
  computed: {
    ...get('appStore', [
      'selectDrawerItem'
    ]),
    ...sync('userStore',['parkSeq','id'])
  },
  mounted(){
    console.log("mount")
    this.$store.dispatch('userStore/user')
  },
  methods: {
    user:  call('userStore/user'),
  },

}

</script>
<style scoped>

</style>
