<template dark>
  <v-container id="dashboard-view" fluid tag="section">
    <v-row justify="center">
      <v-col cols="12" sm="6" md="4">
        <v-text-field
          v-model="search"
          append-icon="mdi-magnify"
          label="Search"
          single-line
          hide-details
        ></v-text-field>
      </v-col>
    </v-row>

    <v-data-table
      :headers="headers"
      :items="parkList"
      :items-per-page="listCount"
      class="row-pointer"
      :search="search"
      @click:row="fnRowClick"
    ></v-data-table>
  </v-container>
</template>

<script>
import { get, set, sync, call } from "vuex-pathify";

export default {
  components: {},
  mounted() {
    this.getParkList();
  },
  name: "ParkView",
  data: () => ({
    listCount: 10,
    search: "",
    headers: [
      { text: "아파트", value: "aptNm" },
      { text: "위도", value: "latitude" },
      { text: "적도", value: "longitude" },
      { text: "등록일자", value: "insDate" }
    ]
  }),
  computed: {
    ...get("parkStore", ["parkList"])
  },
  methods: {
    getParkList: function() {
      this.$store.dispatch("parkStore/parkListFetch", {}).then(res => {});
    },
    fnRowClick: function(dataObj) {
      console.log(dataObj);
      this.$router
        .push("/components/park-detail/" + dataObj.aptId)
        .catch(err => {});
    }
  }
};
</script>
<style lang="scss" scoped>
.row-pointer:hover {
  cursor: pointer;
}
</style>
