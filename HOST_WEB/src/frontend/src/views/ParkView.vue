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

    <v-col cols="9">
      <v-data-table
        :headers="tableHeaderArr"
        :items="parkList"
        :items-per-page="listCount"
        class="row-pointer"
        :search="search"
        @click:row="fnRowClick"
      ></v-data-table>
    </v-col>

    <v-row justify="center">
      <v-dialog v-model="dialog" persistent max-width="800px">
        <template v-slot:activator="{ on, attrs }">
          <v-btn color="primary" dark v-bind="attrs" v-on="on">
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
                <v-col cols="3">
                  <h3>아파트 ID*</h3>
                  <v-text-field v-model="newAptId"></v-text-field>
                </v-col>
                <v-col cols="12">
                  <h3>아파트 이름</h3>
                  <v-textarea
                    v-model="newAptNm"
                    outline
                    label="Outline textarea"
                    counter
                    maxlength="500"
                    full-width
                    rows="10"
                    row-height="30"
                  ></v-textarea>
                </v-col>
              </v-row>
            </v-container>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="blue darken-1" text @click="dialog = false">
              닫기
            </v-btn>
            <v-btn color="blue darken-1" text @click="fnInsert">
              저장
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>

    <v-col cols="3">
      <template>
        <v-form ref="form">
          <v-row>
            <v-col>
              <v-card>
                <v-card-text>
                  <v-text-field
                    v-model="columnObj.aptNm.data"
                    :label="columnObj.aptNm.text"
                    :placeholder="columnObj.aptNm.placeholder"
                    :disabled="columnObj.aptNm.disabled"
                    required
                  />
                  <v-text-field
                    v-model="columnObj.latitude.data"
                    :label="columnObj.latitude.text"
                    :placeholder="columnObj.latitude.placeholder"
                    :disabled="columnObj.latitude.disabled"
                    required
                  />

                  <v-text-field
                    v-model="columnObj.longitude.data"
                    :label="columnObj.longitude.text"
                    :placeholder="columnObj.longitude.placeholder"
                    :disabled="columnObj.longitude.disabled"
                    required
                  />

                  <v-text-field
                    v-model="columnObj.insDate.data"
                    :label="columnObj.insDate.text"
                    :placeholder="columnObj.insDate.placeholder"
                    :disabled="columnObj.insDate.disabled"
                    required
                  />
                </v-card-text>

                <v-row>
                  <v-col>
                    <v-btn @click="fnDetailMove">
                      설정하기
                    </v-btn>
                  </v-col>
                </v-row>
              </v-card>
            </v-col>
          </v-row>
        </v-form>
      </template>
    </v-col>
  </v-container>
</template>

<script>
import { get, set, sync, call } from "vuex-pathify";

export default {
  name: "ParkView",
  data: () => ({
    dialog: false,
    regTypeBool: true,
    listCount: 10,
    search: "",
    tableHeaderArr: [],
    newAptId: "",
    newAptNm: "",
    columnObj: {
      aptId: {
        text: "아파트 ID",
        value: "aptId",
        data: null,
        update: false,
        insert: true,
        tableShow: false,
        sortable: false,
        search: true,
        counter: 5,
        placeholder: " ",
        disabled: true
      },
      aptNm: {
        text: "아파트",
        value: "aptNm",
        data: null,
        update: false,
        insert: false,
        tableShow: true,
        sortable: false,
        search: true,
        placeholder: " ",
        disabled: true
      },
      latitude: {
        text: "위도",
        value: "latitude",
        data: null,
        update: false,
        insert: false,
        tableShow: true,
        sortable: false,
        search: false,
        placeholder: " ",
        disabled: true
      },
      longitude: {
        text: "경도",
        value: "longitude",
        data: null,
        update: false,
        insert: false,
        tableShow: true,
        sortable: false,
        search: false,
        placeholder: " ",
        disabled: true
      },
      insDate: {
        text: "등록일",
        value: "insDate",
        data: null,
        update: false,
        insert: false,
        tableShow: true,
        sortable: true,
        search: false,
        placeholder: " ",
        disabled: true
      }
    }
  }),
  components: {},
  mounted() {
    this.getParkList();
    this.makeTableHeaderArr();
  },
  computed: {
    ...get("parkStore", ["parkList"])
  },
  methods: {
    getParkList: function() {
      this.$store.dispatch("parkStore/parkListFetch", {}).then(res => {});
    },
    async fnRowClick(event, { item }) {
      for (var i in this.columnObj) {
        this.columnObj[i].disabled = !this.columnObj[i].update ? true : false;
        this.columnObj[i].data = item[i];
        this.aptId = item["aptId"];
      }
    },
    fnDetailMove: function() {
      if (!this.isRowSelectCheck()) return;

      this.$router
        .push("/components/park-detail/" + this.aptId)
        .catch(err => {});
    },
    isRowSelectCheck() {
      var isCheck = true;
      if (this.aptId == "" || this.aptId == undefined) {
        isCheck = false;
      }
      return isCheck;
    },
    fnInsert: function() {
      this.$store
        .dispatch("parkStore/parkInsert", {
          aptId: this.newAptId,
          aptNm: this.newAptNm,
          insId: "jkan"
        })
        .then(res => {
          this.dialog = false;
          this.getParkList();
        });
    },
    makeTableHeaderArr() {
      var columnObj = this.columnObj;
      for (var i in columnObj) {
        if (columnObj[i].tableShow) {
          this.tableHeaderArr.push({
            sortable: columnObj[i].sortable,
            text: columnObj[i].text,
            value: i
          });
        }
      }
    }
  }
};
</script>
<style lang="scss" scoped>
.row-pointer:hover {
  cursor: pointer;
}
</style>
