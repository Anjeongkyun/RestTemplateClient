<template>
   <v-container fluid>
     <h3>게시글 번호</h3>
      <v-row>
        <v-col
          cols="12"
          sm="6"
          md="2"
        >
          <v-text-field
            solo
            v-model="detailContents.noticeNo"
            readonly            
          ></v-text-field>
        </v-col>
    </v-row>

    <h3>제목</h3>
      <v-row>
        <v-col
          cols="12"
          sm="6"
          md="5"
        >
          <v-text-field
            solo
            v-model="detailContents.noticeTitle"          
          ></v-text-field>
        </v-col>
      </v-row>

    <h3>작성자</h3>
      <v-row>
        <v-col
          cols="12"
          md="6"
        >
          <v-text-field
            solo
            v-model="detailContents.noticeWriter"
          ></v-text-field>
        </v-col>
      </v-row>

    <h3>수정자</h3>
      <v-row>
        <v-col
          cols="12"
          md="6"
        >
          <v-text-field
            solo
            v-model="detailContents.updId"
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
            v-model="detailContents.noticeContent"
          ></v-textarea>
        </v-col>
      </v-row>
    
    <v-btn 
        class = "updateBtn" 
        large
        depressed        
        outlined color = "blue"
        @click="fnUpdate({detailContents})"
        >수정
    </v-btn>    

    <v-btn 
        class = "updateBtn" 
        large
        depressed        
        outlined color = "blue"    
        @click="fnDelete({detailContents})"
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
    fnDelete: function(dataObj){
        this.$store.dispatch('noticeStore/noticeDelete',{
        noticeNo : dataObj.detailContents.noticeNo
      });
      this.$router.push("/components/notice/");
      alert('완료');
    },
    
   fnUpdate: function(dataObj){
        this.$store.dispatch('noticeStore/noticeUpdate',{
        noticeNo : dataObj.detailContents.noticeNo,
        noticeTitle : dataObj.detailContents.noticeTitle,
        noticeContent : dataObj.detailContents.noticeContent,
        noticeWriter : dataObj.detailContents.noticeWriter,
        updId : dataObj.detailContents.updId
      })
      .then(res => {
        this.$router.push("/components/notice/");
        alert('완료');
      });

    }
  },
  
  name: 'RegularTablesView',
  data: () => ({
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
