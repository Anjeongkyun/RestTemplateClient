<template>
  <v-container
    id="regular-tables-view"
    fluid
    tag="section"
  >
    <v-row justify="center">
      <v-col
        cols="12"
        sm="6"
        md="4"
      >
        <v-text-field
          v-model="search"
          append-icon="mdi-magnify"
          label="Search"
          single-line
          hide-details
        />
      </v-col>
    </v-row>
    <material-card
      icon="mdi-clipboard-text"
      icon-small
      :title="selectDrawerItem"
      color="accent"
    >
      <v-data-table
        :headers="headers"
        :items="noticeList"
        :items-per-page="listCount"
        class="row-pointer"
        :search="search"
        @click:row="rowClick"
      />

      <v-row justify="center">
        <v-dialog
          v-model="dialog"
          persistent
          max-width="800px"
        >
          <template v-slot:activator="{ on, attrs }">
            <v-btn
              color="primary"
              dark
              v-bind="attrs"
              v-on="on"
            >
              작성
            </v-btn>
          </template>
          <v-card>
            <v-card-title>
              <span class="text-h5">공지사항 작성</span>
            </v-card-title>
            <v-card-text>
              <v-container>
                <v-row>
                  <v-col
                    cols="12"
                  >
                    <h3>제목</h3>
                    <v-text-field
                      v-model="noticeTitle"
                      label="제목 입력*"
                    />
                  </v-col>
                  <v-col cols="12">
                    <h3>내용</h3>
                    <v-textarea
                      v-model="noticeContent"
                      outline
                      label="Outline textarea"
                      counter
                      maxlength="500"
                      full-width
                      rows="10"
                      row-height="30"
                    />
                  </v-col>
                </v-row>
              </v-container>
            </v-card-text>
            <v-card-actions>
              <v-spacer />
              <v-btn
                color="blue darken-1"
                text
                @click="dialog = false"
              >
                닫기
              </v-btn>
              <v-btn
                color="blue darken-1"
                text
                @click="fnWrite"
              >
                저장
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-row>
    </material-card>
  </v-container>
</template>

<script>
import { get, set, sync, call } from 'vuex-pathify'

export default {
  mounted() {
    this.listFetch();
  },
  methods:{
    listFetch : call('noticeStore/listFetch'),

    rowClick: function(dataObj) {
      this.$store.dispatch('noticeStore/noticeDetail',{
        noticeNo : dataObj.noticeNo
      });
      this.$router.push("/components/notice-detail/"+"202201283050")
    },
    fnWrite: function(){
      this.$store.dispatch('noticeStore/noticeInsert',{
        noticeNo : "202201283050",
        aptId: "ALD01",
        noticeTitle : this.noticeTitle,
        noticeContent : this.noticeContent,
        noticeWriter : "jkan",
        remark : "비고1",
        insId : "ohhj"
      })
        .then(res => {
          this.dialog = false;
          this.listFetch();
        })
    }
  },

  name: 'RegularTablesView',
  components: {
  },
  data: () => ({
    listCount: 10,
    search: '',
    headers: [
      {text: '제목', value: 'noticeTitle', width : "80%"},
      {text: '등록일자', value: 'insDate'}
    ],
    dialog : false,
    noticeContent : '',
    noticeTitle : ''
  }),
  computed: {
    ...get('appStore', [
      'selectDrawerItem'
    ]),
    ...get('noticeStore', [
      'noticeList'
    ])
  }
}
</script>

<style lang = "scss" scoped>
.row-pointer:hover {
  cursor: pointer;
}
</style>
