<template dark>
  <v-container id="dashboard-view" fluid tag="section">
    <v-row>
      <v-col cols="5">
        <material-card :heading="heading" color="accent">
          <v-card-text>
            <v-sheet>
              <iframe
                :src="`https://www.google.com/maps/embed?${iframe}`"
                allowfullscreen
                frameborder="0"
                height="500"
                style="border:0"
                width="100%"
              />
            </v-sheet>
          </v-card-text>
        </material-card>
      </v-col>

      <v-data-table
        style="width:50%"
        :headers="headers"
        :items="parkList"
        :items-per-page="5"
      ></v-data-table>
    </v-row>
  </v-container>
</template>

<script>
import { get, set, sync, call } from "vuex-pathify";

export default {
  mounted() {
    this.getParkList();
  },
  name: "ParkView",
  data: () => ({
    headers: [
      { text: "아파트", value: "aptNm" },
      { text: "위도", value: "latitude" },
      { text: "적도", value: "longitude" },
      { text: "등록일자", value: "insDate" }
    ],
    heading: "Google Map",
    iframe:
      "pb=!1m18!1m12!1m3!1d193595.15831228695!2d-74.11976206978034!3d40.697663747508045!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x89c24fa5d33f083b%3A0xc80b8f06e177fe62!2sNew+York%2C+NY!5e0!3m2!1sen!2sus!4v1566158235149!5m2!1sen!2sus"
  }),
  computed: {
    ...get("parkStore", ["parkList"])
  },
  methods: {
    getParkList: function() {
      this.$store.dispatch("parkStore/parkListFetch", {}).then(res => {});
    }
  }
};
</script>
<style scoped></style>
