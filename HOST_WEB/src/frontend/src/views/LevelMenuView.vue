<template dark>
  <v-container
    id="dashboard-view"
    fluid
    tag="section"
  >
    <v-row>
      <v-col :cols="viewListCols">
        <material-card
          icon="mdi-account"
          icon-small
          :title="selectDrawerItem"
          color="accent"
        >
          <v-alert
            color="accent lighten-3"
            dark
            icon="mdi-yeast"
            border="left"
            class="pl-6 pr-6 ml-6 mr-6"
          >
            <v-row
              dense
              class="pl-6 pr-6"
            >
              <v-col
                class="d-flex justify-center mb-1"
                cols="10"
                sm="12"
                lg="10"
              />
              <v-col
                class="d-flex justify-center mb-1"
                cols="12"
                sm="12"
                lg="12"
              />
            </v-row>
          </v-alert>

          <v-row dense>
            <v-card-text>
              <v-data-table
                :headers="tableHeaderArr"
                :items="levelMenuList"
                class="elevation-1"
                @click:row="rowClick"
              >
                <!--   <template v-slot:item="{ item }">
                    <tr>
                      <v-chip

                        dark
                      >
                        {{ item.levelNm }}
                      </v-chip>
                    </tr>
                  </template> -->

                <!-- <template v-slot:item.level="{ item }">
                    <v-chip
                      :value="getColor(item.level)"
                      dark
                    >
                      {{ item.level }}
                    </v-chip>
                  </template> -->
              </v-data-table>
            </v-card-text>
          </v-row>
        </material-card>
      </v-col>
      <v-col
        v-show="viewInfoDisabled"
        cols="3"
      >
        <template>
          <v-form
            ref="form"
            v-model="valid"
            lazy-validation
          >
            <v-row>
              <v-col>
                <v-card>
                  <v-card-text>
                    <v-text-field
                      v-show="columnObj.level.tableShow"
                      v-model="columnObj.level.data"
                      :label="columnObj.level.text"
                      :rules="columnObj.level.rules"
                      :placeholder="columnObj.level.placeholder"
                      :counter="columnObj.level.counter"
                      :disabled="columnObj.level.disabled"
                      required
                    />


                    <v-text-field

                      v-model="columnObj.levelNm.data"
                      :label="columnObj.levelNm.text"
                      :rules="columnObj.level.rules"
                      :placeholder="columnObj.level.placeholder"
                      :counter="columnObj.level.counter"
                      :disabled="columnObj.level.disabled"
                      required
                    />


                    <div>
                      <v-checkbox
                        v-model="menuSelectedAll"
                        label="전체 선택"
                        @click="checkboxAllClick"
                      />
                    </div>

                    <div
                      v-for="(item, i) in menuListShow"
                      :key="`menuListShow-${i}`"
                    >
                      <v-checkbox
                        v-model="menuSelected"
                        :label="item.menuNm"
                        :value="item.menuId"
                      />
                    </div>

                    <v-text-field
                      v-model="columnObj.insId.data"
                      :label="columnObj.insId.text"
                      :placeholder="columnObj.insId.placeholder"
                      :disabled="columnObj.insId.disabled"
                    />

                    <v-text-field
                      v-model="columnObj.insDate.data"
                      :label="columnObj.insDate.text"
                      :placeholder="columnObj.insDate.placeholder"
                      :disabled="columnObj.insDate.disabled"
                    />

                    <v-text-field
                      v-model="columnObj.updId.data"
                      :label="columnObj.updId.text"
                      :placeholder="columnObj.updId.placeholder"
                      :disabled="columnObj.updId.disabled"
                    />

                    <v-text-field
                      v-model="columnObj.updDate.data"
                      :label="columnObj.updDate.text"
                      :placeholder="columnObj.updDate.placeholder"
                      :disabled="columnObj.updDate.disabled"
                    />

                    <v-divider class="mt-12" />
                    <v-row justify="space-between">
                      <v-col cols="4">
                        <v-btn
                          text
                          @click="cancelBtnCLick()"
                        >
                          취소
                        </v-btn>
                      </v-col>

                      <v-col cols="4">
                        <v-btn
                          color="secondary"
                          text
                          @click="saveBtnCLick()"
                        >
                          저장
                        </v-btn>
                      </v-col>
                    </v-row>
                  </v-card-text>
                </v-card>
              </v-col>
            </v-row>
          </v-form>
        </template>
        <!--   <userForm
  ref="userForm"
/> -->
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { get, set, sync, call } from "vuex-pathify";
import cmmnFnStore from '@/store/modules/cmmnFnStore' //store 와 관련된 데이터를 처리하는 공통 함수 파일

export default {
  name: "LevelMenuView",
  data: () => ({
    loginId: null,
    loginPw: null,

    loading: false,
    valid: true,
    regTypeBool: false,
    regType: null,
    errorMessages: "errorMessages 테스트",
    formHasErrors: false,

    viewListCols: 12,
    viewInfoCols: 3,
    viewInfoDisabled: false,

    menuSelectedAll: false,
    menuSelected: [],

    columnObj: {
      level: {
        text: "권한코드",
        value: "level",
        data: null,
        update: false,
        insert: false,
        tableShow: false,
        sortable: false,
        search: false,
        counter: 30,
        placeholder: " ",
        disabled: false,
        rules: [
          (v) => !!v || "필수 입력",
          (v) => (v && v.length <= 30) || "입력 글자 수 초과.",
        ],
      },
      levelNm: {
        text: "권한",
        value: "level",
        data: null,
        update: false,
        insert: false,
        tableShow: true,
        sortable: false,
        search: false,
        counter: 30,
        placeholder: " ",
        disabled: false,
        rules: [
          (v) => !!v || "필수 입력",
          (v) => (v && v.length <= 30) || "입력 글자 수 초과.",
        ],
      },
      menuList: {
        text: "메뉴",
        value: "menuList",
        data: null,
        update: true,
        insert: true,
        tableShow: true,
        sortable: false,
        search: false,
        counter: 30,
        placeholder: " ",
        disabled: true,
        rules: [
          (v) => !!v || "필수 입력",
          (v) => (v && v.length <= 30) || "입력 글자 수 초과.",
        ],
      },

      insId: {
        text: "등록자",
        value: "insId",
        data: null,
        update: false,
        insert: false,
        tableShow: false,
        sortable: false,
        search: false,
        counter: 5,
        placeholder: " ",
        disabled: true,
        rules: [
          //v => !!v || '필수 입력',
          //  v => (!v || v.length <= 5) || '입력 글자 수 초과.',
        ],
      },
      insDate: {
        text: "등록일",
        value: "insDate",
        data: null,
        update: false,
        insert: false,
        show: false,
        sortable: false,
        search: false,
        counter: 5,
        placeholder: " ",
        disabled: true,
        rules: [
          //v => !!v || '필수 입력',
          //  v => (!v || v.length <= 5) || '입력 글자 수 초과.',
        ],
      },
      updId: {
        text: "수정자",
        value: "updId",
        data: null,
        update: false,
        insert: false,
        tableShow: true,
        sortable: false,
        search: false,
        counter: 5,
        placeholder: " ",
        disabled: true,
        rules: [
          //v => !!v || '필수 입력',
          //  v => (!v || v.length <= 5) || '입력 글자 수 초과.',
        ],
      },
      updDate: {
        text: "수정일",
        value: "updDate",
        data: null,
        update: false,
        insert: false,
        tableShow: true,
        sortable: false,
        search: false,
        counter: 5,
        placeholder: " ",
        disabled: true,
        rules: [
          //v => !!v || '필수 입력',
          //  v => (!v || v.length <= 5) || '입력 글자 수 초과.',
        ],
      },
    },

    searchObj: {},
    dataObj: {
      id:  null
    },
    tableHeaderArr: [],
    cmmnList:{}
  }),
  computed: {
    ...get("appStore", ["selectDrawerItem"]),
    ...get("menuStore", ["menuList","menuListShow"]),
    ...get("levelMenuStore", ["levelMenuList"]),
    ...sync("userStore", ["parkSeq", "id", "parkItemSelected"]),
    ...get("cmmnStore", ["cmmnCodeItem", "useYnItem", "displayYnItem"]),
  },
  async created(){
    //  store.getters["cmmnStore/loginUserID"]
    this.setID();
    this.makeTableHeaderArr();
    this.makeSearchObj();

    this.cmmnList.PC04=await cmmnFnStore.fn.getCmmnCodeGroup("PC","04");
    //this.$store.dispatch("menuStore/listShow");
    await this.getMenuListShow();
    await this.searchBtnClick();
  },
  mounted() {
    //  this.$store.dispatch('userStore/park');
    //  this.$store.dispatch('cmmnStore/code');
    this.$nextTick(function () {
      // 모든 화면이 렌더링된 후 실행합니다.
    })
    /*   this.$store.dispatch('cmmnStore/code');
    this.$store.dispatch("menuStore/list");
    this.$store.dispatch("menuStore/listShow");
    this.$store.dispatch("levelMenuStore/list"); */
    
  //  this.loading = true; // 돔 그린 후 바인딩 하기 위해 처리
  },

  methods: {
    setID (){
      this.dataObj.id =this.$store.getters["cmmnStore/loginUserID"]
    },
    manageShow() {
      //화면 오른쪽에 폼을 표시
      this.reset();
      this.viewListCols = 9;
      this.viewInfoDisabled = true;
    },
    manageHide() {
      //화면 오른쪽의 폼을 숨김
      this.reset();
      this.viewListCols = 12;
      this.viewInfoDisabled = false;
    },

    makeTableHeaderArr() {
      // 테이블의 헤더를 만듦
    /*   this.tableHeaderArr.push({
        sortable: false,
        text: "권한",
        value: "level", // i ==columnObj[i].value
      }); */
      var columnObj = this.columnObj;
      for (var i in columnObj) {
        if (columnObj[i].tableShow) {
          this.tableHeaderArr.push({
            sortable: columnObj[i].sortable,
            text: columnObj[i].text,
            value: i, // i ==columnObj[i].value
          });

        }
      }
    },

    makeSearchObj() {
      //검색  객체를 만듦
      var columnObj = this.columnObj;
      for (var i in columnObj) {
        if (columnObj[i].search) {
          this.searchObj[i] = {
            text: columnObj[i].text,
            value: i,
            data: null,
          };
        }
      }
    },

    validate() {
      var chk = true;
      if (!this.$refs.form.validate()) {
        alert("필수항목과 글자수를 확인해 주세요.");
        chk = false;
      }
      return chk;
    },
    validate_old() {
      this.$refs.form.validate();
    },

    reset() {
      this.$refs.form.reset();
    },

    resetValidation() {
      this.$refs.form.resetValidation();
    },

    resetForm() {
      this.errorMessages = [];
      this.formHasErrors = false;
    },

    submit: call("levelMenuStore/submit"),

    async rowClick(event, { item }) {
      await this.manageShow();
      this.regTypeBool = true;
      this.regType = "change";
      for (var i in this.columnObj) {
        this.columnObj[i].disabled = !this.columnObj[i].update ? true : false;
        this.columnObj[i].data = item[i]; // i ==columnObj[i].value

        var arr = item.menuList.split(',')
        this.menuSelected= arr

      }

    },

    async searchBtnClick() {
      this.$store.dispatch("levelMenuStore/list");
      await this.manageHide();
    },

    async addBtnClick() {
      // 신규 버튼 클릭
      await this.manageShow();
      this.regTypeBool = false;
      this.regType = "add";
      for (var i in this.columnObj) {
        this.columnObj[i].disabled = !this.columnObj[i].insert ? true : false;
      }
    },
    getMenuListShow(){
      this.$store.dispatch("menuStore/listShow");
    },
    async saveBtnCLick() {
      if (!this.validate()) return;
      this.dataObj.regType = this.regType;
      this.columnObj.menuList.data =this.menuSelected.toString()
      for (var i in this.columnObj) {
        this.dataObj[i] = this.columnObj[i].data;
      }

      await this.submit(this.dataObj);
      await this.searchBtnClick(this.searchOj);
    },

    checkboxAllClick() {
      var _this= this;
      this.menuSelected = [];
      if (this.menuSelectedAll) {
        //전체선택이면 모든 메뉴 클릭 상태로
        //this.menuSelected = ['1','2','3']
        this.menuListShow.forEach( item =>{
          _this.menuSelected.push(item.menuId);
        });
      } else {
        this.menuSelected = [];
        //전체해제면 모든 메뉴 해제 상태로
      }
    },
    cancelBtnCLick() {
      this.userManageHide();
    },

    getColor (calories) {
      console.log(this.levelMenuList[0].level)
      //console.log(  this.columnObj.level)
      //this.columnObj.level.data= 1234
      this.levelMenuList[0].level = 1234
      console.log(calories)
      if (calories > 400) return 'red'
      else if (calories > 200) return 'orange'
      else return 'green'
    },

  },
};
</script>
<style scoped>
</style>
