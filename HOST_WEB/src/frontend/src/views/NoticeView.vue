<template>
  <v-container
    id="regular-tables-view"
    fluid
    tag="section"
  >
    <view-intro
      heading="공지사항"
    />    

    <div class="selectWrap">                
      <v-btn 
        class = "selectBtn" 
        large
        depressed        
        outlined color = "blue"
        @click="listFetch"
        >조회</v-btn>    
    </div>

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
              제목
            </th>
            <th class="primary--text">
              내용
            </th>
          </tr>
        </thead>

        <tbody>
          <tr v-for="(list,listKey) in noticeList" 
          :key="listKey" 
          @click="rowClick">
            <td>{{list.noticeNo}}</td>
            <td>{{list.noticeTitle}}</td>
            <td>{{list.noticeContent}}</td>
          </tr>
        </tbody>
      </v-simple-table>
    </material-card>
  </v-container>
</template>

<script>
import axios from 'axios'
import { get, set, sync, call } from 'vuex-pathify'

export default {
  mounted() {
    this.listFetch()    
  },
  methods:{
    listFetch : call('notice/listFetch'),
    rowClick(row,column, event){
      console.log(row);
      console.log(column);
      console.log(event);
    }
  },
  
  name: 'RegularTablesView',
  data: () => ({
  }),
  components: {
  },
  computed: {
    ...get('notice', [
      'noticeList'
    ])
  }
}
</script>
<style lang = "scss" scoped>
.selectWrap{
}
</style>
