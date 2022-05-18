<template>
   <v-container fluid>
     <h3>이름</h3>
      <v-row>
        <v-col
          cols="12"
          sm="6"
          md="3"
        >
          <v-text-field
            label="Solo"
            placeholder="Placeholder"
            solo
          ></v-text-field>
        </v-col>
    </v-row>

    <h3>작성자</h3>
      <v-row>
        <v-col
          cols="12"
          sm="6"
          md="3"
        >
          <v-text-field
            label="Solo"
            placeholder="Placeholder"
            solo
          ></v-text-field>
        </v-col>
      </v-row>

    <h3>내용</h3>
      <v-row>
        <v-col
          cols="12"
          md="6"
        >
          <v-textarea
            solo
            name="input-7-4"
            label="Solo textarea"
          ></v-textarea>
        </v-col>
      </v-row>
    
    <v-btn 
        class = "updateBtn" 
        large
        depressed        
        outlined color = "blue"
        @click="listFetch"
        >수정
    </v-btn>    

    <v-btn 
        class = "updateBtn" 
        large
        depressed        
        outlined color = "blue"
        @click="listFetch"
        >삭제
    </v-btn>    
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
    listFetch : call('notice/listFetch')
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
