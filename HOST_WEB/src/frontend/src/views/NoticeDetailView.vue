<template>
   <v-container fluid>
     <h3>번호</h3>
      <v-row>
        <v-col
          cols="12"
          sm="6"
          md="1"
        >
          <v-text-field
            label="aa"
            v-model="noticeNo"
            v-text="detailContents.noticeNo"
            readonly
          ></v-text-field>
        </v-col>
    </v-row>

    <h3>제목</h3>
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
            v-text="detailContents.noticeTitle"
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
            v-text="detailContents.noticeContent"
          ></v-textarea>
        </v-col>
      </v-row>
    
    <v-btn 
        class = "updateBtn" 
        large
        depressed        
        outlined color = "blue"
        @click="fnUpdate"
        >수정
    </v-btn>    

    <v-btn 
        class = "updateBtn" 
        large
        depressed        
        outlined color = "blue"    
        @click="fnDelete(detailContents.noticeNo)"
        >삭제
    </v-btn>    
  </v-container>
</template>

<script>
import { get, set, sync, call } from 'vuex-pathify'

export default {
  mounted() {
  },
  methods:{
    fnDelete: function(noticeNo){
        this.$store.dispatch('noticeStore/noticeDelete',{
        noticeNo : noticeNo
      });
      this.$router.push("/components/notice/");
      alert('완료');
    },
   fnUpdate: function(dataObj){
      //   this.$store.dispatch('noticeStore/noticeUpdate',{
      //   noticeNo : dataObj.noticeNo
      // });
      this.$router.push("/components/notice/");
      alert('완료');

    }
  },
  
  name: 'RegularTablesView',
  data: () => ({
    noticeNo: ''
  }),
  components: {
  },
  computed: {
    ...get('noticeStore', [
      'detailContents'
    ])
  }
}
</script>
<style lang = "scss" scoped>
</style>
