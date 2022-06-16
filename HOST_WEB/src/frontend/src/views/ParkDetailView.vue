<template>
  <v-container fluid>
    <div>
      <KakaoMap
        :center="[detailContents.latitude, detailContents.longitude]"
        :width="mapWidth"
        :height="mapHeight"
      ></KakaoMap>
    </div>

    <h3>아파트 이름</h3>
    <v-row>
      <v-col cols="12" sm="6" md="5">
        <v-text-field solo v-model="detailContents.aptNm"></v-text-field>
      </v-col>
    </v-row>

    <h3>위도</h3>
    <v-row>
      <v-col cols="12" md="6">
        <v-text-field solo v-model="detailContents.latitude"></v-text-field>
      </v-col>
    </v-row>

    <h3>경도</h3>
    <v-row>
      <v-col cols="12" md="6">
        <v-text-field solo v-model="detailContents.longitude"></v-text-field>
      </v-col>
    </v-row>

    <h3>관리자 이름</h3>
    <v-row>
      <v-col cols="12" md="6">
        <v-text-field solo v-model="detailContents.managerNm"></v-text-field>
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
import KakaoMap from "../components/KaKaoMap";

export default {
  mounted() {
    var aptId = this.$route.params.aptId;
    this.fnRefresh(aptId);
  },
  methods: {
    fnRefresh: function(aptId) {
      this.$store.dispatch("parkStore/parkDetail", {
        aptId: aptId
      });
    },
    fnDelete: function(dataObj) {
      this.$store
        .dispatch("parkStore/parkDelete", {
          aptId: dataObj.detailContents.aptId
        })
        .then(res => {
          this.$router.push("/components/park/");
        });
    },
    fnUpdate: function(dataObj) {
      this.$store
        .dispatch("parkStore/parkUpdate", {
          aptId: dataObj.detailContents.aptId,
          aptNm: dataObj.detailContents.aptNm,
          latitude: dataObj.detailContents.latitude,
          longitude: dataObj.detailContents.longitude,
          managerNm: dataObj.detailContents.managerNm
        })
        .then(res => {
          this.$router.push("/components/park/");
        });
    },
    fnMoveNoticeView: function() {
      this.$router.push("/components/park/");
    }
  },

  name: "RegularTablesView",
  data: () => ({
    center: [37.484456, 126.878974],
    mapWidth: 500,
    mapHeight: 400,
    latitude: "",
    longitude: ""
  }),
  components: {
    KakaoMap
  },
  computed: {
    ...get("parkStore", ["detailContents"])
  }
};
</script>
<style lang="scss" scoped></style>
