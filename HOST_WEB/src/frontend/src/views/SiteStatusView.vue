<template>
  <div>   
    
  <v-row class="justify-end">
    <v-btn 
        class="mr-4 px-16"
        large
        depressed        
        color="primary"
        @click="fnRefresh"
    >새로고침</v-btn> 
  </v-row>
<br>
    <v-expansion-panels hover>
    <v-expansion-panel>
      <v-expansion-panel-header disable-icon-rotate>
        <v-row no-gutters>
          <v-col cols="4">
            광명역세권 태영데시앙
          </v-col>
          <v-col
            cols="8"
            class="text--secondary"
          >
            <v-fade-transition leave-absolute>
              <v-row
                no-gutters
                style="width: 100%"
              >
                <v-col cols="6">
                  OLD : {{ trip.start || '2022-05-24 12:01:23' }}
                </v-col>
                <v-col cols="6">
                  NEW : {{ trip.end || '2022-05-24 13:06:23' }}
                </v-col>
              </v-row>
            </v-fade-transition>
          </v-col>    
        </v-row>        
          <template v-slot:actions>
            <v-icon color="error">
              mdi-alert-circle
            </v-icon>
          </template>
      </v-expansion-panel-header>
      <v-expansion-panel-content>
          [Request - Response Time]
          <br><br>
          local Request: 2022-05-24 12:01:23 
          <br>
          Center Response: 2022-05-24 13:06:23
          <br><br>
          DateDiff : 01:05:00
      </v-expansion-panel-content>
    </v-expansion-panel>

    <v-expansion-panel>
      <v-expansion-panel-header disable-icon-rotate >
        <v-row no-gutters>
          <v-col cols="4">
            강릉 파인아트라벨
          </v-col>
          <v-col
            cols="8"
            class="text--secondary"
          >
            <v-fade-transition leave-absolute>
              <v-row
                no-gutters
                style="width: 100%"
              >
                <v-col cols="6">
                  OLD : {{ trip.start || '2022-05-23 13:01:23' }}
                </v-col>
                <v-col cols="6">
                  NEW : {{ trip.end || '2022-05-24 13:06:23' }}
                </v-col>
              </v-row>
            </v-fade-transition>
          </v-col>    
        </v-row>        
          <template v-slot:actions>
            <v-icon color="error">
              mdi-alert-circle
            </v-icon>
          </template>
      </v-expansion-panel-header>
      <v-expansion-panel-content>
          [Request - Response Time]
          <br><br>
          local Request: 2022-05-23 13:01:23 
          <br>
          Center Response: 2022-05-24 13:06:23
          <br><br>
          DateDiff : 24:05:00
      </v-expansion-panel-content>
    </v-expansion-panel>

    <v-expansion-panel>
      <v-expansion-panel-header disable-icon-rotate >
        <v-row no-gutters>
          <v-col cols="4">
            원주 평생교육학습관
          </v-col>
          <v-col
            cols="8"
            class="text--secondary"
          >
            <v-fade-transition leave-absolute>
              <v-row
                no-gutters
                style="width: 100%"
              >
                <v-col cols="6">
                  OLD : {{ trip.start || '2022-05-24 13:01:23' }}
                </v-col>
                <v-col cols="6">
                  NEW : {{ trip.end || '2022-05-24 13:06:23' }}
                </v-col>
              </v-row>
            </v-fade-transition>
          </v-col>    
        </v-row>        
          <template v-slot:actions>
            <v-icon color="teal">
              mdi-check
            </v-icon>
          </template>
      </v-expansion-panel-header>
      <v-expansion-panel-content>
          [Request - Response Time]
          <br><br>
          local Request: 2022-05-24 13:01:23 
          <br>
          Center Response: 2022-05-24 13:06:23
          <br><br>
          DateDiff : 00:05:00
      </v-expansion-panel-content>
    </v-expansion-panel>

    <v-expansion-panel>
      <v-expansion-panel-header disable-icon-rotate >
        <v-row no-gutters>
          <v-col cols="4">
            판교 알파돔 6-2
          </v-col>
          <v-col
            cols="8"
            class="text--secondary"
          >
            <v-fade-transition leave-absolute>
              <v-row
                no-gutters
                style="width: 100%"
              >
                <v-col cols="6">
                  OLD : {{ trip.start || '2022-05-24 13:01:23' }}
                </v-col>
                <v-col cols="6">
                  NEW : {{ trip.end || '2022-05-24 13:06:23' }}
                </v-col>
              </v-row>
            </v-fade-transition>
          </v-col>    
        </v-row>        
          <template v-slot:actions>
            <v-icon color="teal">
              mdi-check
            </v-icon>
          </template>
      </v-expansion-panel-header>
      <v-expansion-panel-content>
          [Request - Response Time]
          <br><br>
          local Request: 2022-05-24 13:01:23 
          <br>
          Center Response: 2022-05-24 13:06:23
          <br><br>
          DateDiff : 00:05:00
      </v-expansion-panel-content>
    </v-expansion-panel>

    </v-expansion-panels>
  </div>
</template>

<script>

import { get, set, sync, call } from 'vuex-pathify'

export default {
  name: 'SiteStatusView',
  mounted() {

  },
  data: () => ({
    date: null,
    trip: {
      name: '',
      location: null,
      start: null,
      end: null,
    },
  }),
  computed: {
     ...get('siteStatusStore', [
      'siteStatusList'
    ])
  },
  methods: {
    siteStatusFetch : call('siteStatusStore/siteStatusFetch'),
    fnRefresh: function() {
      this.siteStatusFetch();
      }    
  },
}
</script>
<style scoped>

</style>
