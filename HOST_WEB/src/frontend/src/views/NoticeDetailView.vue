<template>
  <v-container fluid>
    <h3>제목</h3>
    <v-row>
      <v-col cols="12" sm="6" md="5">
        <v-text-field solo v-model="detailContents.noticeTitle"></v-text-field>
      </v-col>
    </v-row>

    <h3>작성자</h3>
    <v-row>
      <v-col cols="12" md="6">
        <v-text-field
          solo
          readonly
          v-model="detailContents.insId"
        ></v-text-field>
      </v-col>
    </v-row>

    <h3>작성일자</h3>
    <v-row>
      <v-col cols="12" md="6">
        <v-text-field
          solo
          readonly
          v-model="detailContents.insDate"
        ></v-text-field>
      </v-col>
    </v-row>

    <h3>내용</h3>
    <v-row>
      <v-col cols="12" sm="6" md="5">
        <EditorTiptap
          :options="options"
          :contents="detailContents.noticeContent"
          v-model="detailContents.noticeContent"
        />

        <!-- <v-textarea
          solo
          v-model="detailContents.noticeContent"
          counter
          maxlength="2000"
          rows="20"
          row-height="30"
        ></v-textarea> -->
      </v-col>
    </v-row>

    <v-btn
      class="mr-5"
      large
      depressed
      outlined
      color="blue"
      @click="fnUpdate({ detailContents })"
      >수정
    </v-btn>

    <v-btn
      class="mr-5"
      large
      depressed
      outlined
      color="red"
      @click="fnDelete({ detailContents })"
      >삭제
    </v-btn>

    <v-btn
      class="mr-2"
      large
      depressed
      outlined
      color="green"
      @click="fnMoveNoticeView"
      >목록으로
    </v-btn>
  </v-container>
</template>

<script>
import { get, set, sync, call } from "vuex-pathify";
import store from "@/store";

export default {
  mounted() {
    var noticeNo = this.$route.params.noticeNo;
    this.fnRefresh(noticeNo);
  },
  methods: {
    fnRefresh: function(noticeNo) {
      this.$store.dispatch("noticeStore/noticeDetail", {
        noticeNo: noticeNo
      });
    },
    fnDelete: function(dataObj) {
      this.$store
        .dispatch("noticeStore/noticeDelete", {
          noticeNo: dataObj.detailContents.noticeNo
        })
        .then(res => {
          this.$router.push("/components/notice/");
        });
    },

    fnUpdate: function(dataObj) {
      this.$store
        .dispatch("noticeStore/noticeUpdate", {
          noticeNo: dataObj.detailContents.noticeNo,
          noticeTitle: dataObj.detailContents.noticeTitle,
          noticeContent: this.options.content,
          noticeWriter: dataObj.detailContents.noticeWriter,
          updId: dataObj.detailContents.updId
        })
        .then(res => {
          this.$router.push("/components/notice/");
        });
    },
    fnMoveNoticeView: function() {
      this.$router.push("/components/notice/");
    }
  },

  name: "RegularTablesView",
  data: () => ({
    options: {
      editable: false,
      supportImage: true
    },
    contents: ""
  }),
  components: {
    EditorTiptap: () => import("../components/EditorTiptap")
  },
  computed: {
    ...get("noticeStore", ["detailContents"])
  }
};
</script>
<style lang="scss" scoped></style>
