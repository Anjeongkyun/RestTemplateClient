<template dark>
  <v-container
    id="dashboard-view"
    fluid
    tag="section"
  >
    <div v-if="loading">
      <v-row>
        <v-col
          :cols="viewListCols"
        >
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
                  cols="2"
                  sm="12"
                  lg="2"
                >
                  <v-btn
                    class="mb-3"
                    block
                    depressed
                    color="salary"
                    @click="addBtnClick({ })"
                  >
                    신규
                  </v-btn>
                </v-col>
              </v-row>
            </v-alert>

            <v-row
              dense
            >
              <v-card-text>
                <v-data-table
                  :headers="tableHeaderArr"
                  :items="menuList"
                  @click:row="rowClick"
                />
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
                        v-show="columnObj.menuId.tableShow"
                        v-model="columnObj.menuId.data"
                        :label="columnObj.menuId.text"
                        :rules="columnObj.menuId.rules"
                        :placeholder="columnObj.menuId.placeholder"
                        :counter="columnObj.menuId.counter"

                        :disabled="columnObj.menuId.disabled"
                        required
                      />

                      <v-text-field
                        v-model="columnObj.menuNm.data"
                        :label="columnObj.menuNm.text"
                        :rules="columnObj.menuNm.rules"
                        :placeholder="columnObj.menuNm.placeholder"
                        :counter="columnObj.menuNm.counter"
                        :disabled="columnObj.menuNm.disabled"
                        required
                      />

                      <v-text-field
                        v-model="columnObj.description.data"
                        :label="columnObj.description.text"
                        :rules="columnObj.description.rules"
                        :placeholder="columnObj.description.placeholder"
                        :counter="columnObj.description.counter"
                        :disabled="columnObj.description.disabled"
                        required
                      />




                      <v-select
                        v-model="columnObj.menuShowYn.data"
                        :label="columnObj.menuShowYn.text"

                        :rules="columnObj.menuShowYn.rules"
                        :items="displayYnItem"
                        item-text="textItem"
                        item-value="valueItem"
                        required
                      />


                      <v-text-field
                        v-model="columnObj.menuOrder.data"
                        :label="columnObj.menuOrder.text"
                        :rules="columnObj.menuOrder.rules"
                        :placeholder="columnObj.menuOrder.placeholder"
                        :counter="columnObj.menuOrder.counter"
                        :disabled="columnObj.menuOrder.disabled"
                        required
                      />

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
                    </v-card-text>

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
                          v-show="regTypeBool"
                          color="error"
                          text
                          @click="deleteBtnCLick()"
                        >
                          삭제
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
    </div>
  </v-container>
</template>

<script>

import { get, set, sync, call } from 'vuex-pathify'

export default {
  name: 'MenuView',
  data: () => ({
    loginId: null,
    loginPw: null,

    loading: false,
    valid: true,
    regTypeBool:false,
    regType:null,
    errorMessages: 'errorMessages 테스트',
    formHasErrors: false,

    viewListCols: 12,
    viewInfoCols:3,
    viewInfoDisabled:false,

/*

private String menuId;
private String menuNm;
private String description;
private String menuIcon;
private String menuRouterTo;
private String menuShowYn;
private String menuOrder;
private String menuRouteName;
private String menuType; */
    columnObj: {
      menuId : {
        text:"ID",
        value:"loginId",
        data:null,
        update:false,
        insert:false,
        tableShow:false,
        sortable: false,
        search:true,
        counter:5,
        placeholder:" ",
        disabled:true,
        rules: [
        //  v => !!v || '필수 입력',
        //  v => (v && v.length <= 5) || '입력 글자 수 초과.',
        ],
      },
	    menuNm: {
        text:"메뉴명",
        value:"menuNm",
        data:null,
        update:true,
        insert:true,
        tableShow:true,
        sortable: false,
        search:false,
        counter:30,
        placeholder:" ",
        disabled:true,
        rules: [
          v => !!v || '필수 입력',
          v => (v && v.length <= 30) || '입력 글자 수 초과.',
        ],
      },
	    description: {
        text:"메뉴설명",
        value:"description",
        data:null,
        update:true,
        insert:true,
        tableShow:true,
        sortable: false,
        search:true,
        counter:50,
        placeholder:" ",
        disabled:true,
        rules: [
        //  v => !!v || '필수 입력',
          v => (!v || v.length <= 50) || '입력 글자 수 초과.',
        ],
      },
	    menuIcon: {
        text:"아이콘",
        value:"menuIcon",
        data:null,
        update:true,
        insert:true,
        tableShow:false,
        sortable: false,
        search:false,
        counter:50,
        placeholder:" ",
        disabled:true,
        rules: [
          //v => !!v || '필수 입력',
          v => (!v || v.length <= 50) || '입력 글자 수 초과.',
        ],
      },
	    menuRouterTo: {
        text:"요청 라우터",
        value:"menuRouterTo",
        data:null,
        update:true,
        insert:true,
        tableShow:false,
        sortable: false,
        search:false,
        counter:30,
        placeholder:" ",
        disabled:true,
        rules: [
          //v => !!v || '필수 입력',
          v => (!v || v.length <= 30) || '입력 글자 수 초과.',
        ],
      },
	    menuShowYn: {
        text:"표시여부",
        value:"menuShowYn",
        data:null,
        update:true,
        insert:true,
        tableShow:true,
        sortable: false,
        search:false,
        counter:1,
        placeholder:" ",
        disabled:true,
        rules: [
          v => !!v || '필수 입력',
        //  v => (!v || v.length <= 1) || '입력 글자 수 초과.',
        ],
      },
	    menuOrder: {
        text:"표시순서",
        value:"menuOrder",
        data:null,
        update:true,
        insert:true,
        tableShow:true,
        sortable: false,
        search:false,
        counter:2,
        placeholder:" ",
        disabled:true,
        rules: [
          v => !!v || '필수 입력',
        //  v => (v && v.length <= 6) || '입력 글자 수 초과.',
        ],
      },
	    menuRouteName: {
        text:"Vue페이지명",
        value:"menuRouteName",
        data:null,
        update:true,
        insert:true,
        tableShow:false,
        sortable: false,
        search:false,
        counter:30,
        placeholder:" ",
        disabled:true,
        rules: [
          v => !!v || '필수 입력',
          v => (v && v.length <= 30) || '입력 글자 수 초과.',
        ],
      },

        menuType: {
        text:"메뉴 타입",
        value:"menuType",
        data:null,
        update:true,
        insert:true,
        tableShow:false,
        sortable: false,
        search:false,
        counter:30,
        placeholder:" ",
        disabled:true,
        rules: [
          v => !!v || '필수 입력',
          v => (v && v.length <= 30) || '입력 글자 수 초과.',
        ],
      },
	    insId: {
        text:"등록자",
        value:"insId",
        data:null,
        update:false,
        insert:false,
        tableShow:false,
        sortable: false,
        search:false,
        counter:5,
        placeholder:" ",
        disabled:true,
        rules: [
          //v => !!v || '필수 입력',
        //  v => (!v || v.length <= 5) || '입력 글자 수 초과.',
        ],
      },
	    insDate: {
        text:"등록일",
        value:"insDate",
        data:null,
        update:false,
        insert:false,
        show:false,
        sortable: false,
        search:false,
        counter:5,
        placeholder:" ",
        disabled:true,
        rules: [
          //v => !!v || '필수 입력',
        //  v => (!v || v.length <= 5) || '입력 글자 수 초과.',
        ],
      },
	    updId: {
        text:"수정자",
        value:"updId",
        data:null,
        update:false,
        insert:false,
        tableShow:true,
        sortable: false,
        search:false,
        counter:5,
        placeholder:" ",
        disabled:true,
        rules: [
          //v => !!v || '필수 입력',
        //  v => (!v || v.length <= 5) || '입력 글자 수 초과.',
        ],
      },
	    updDate: {
        text:"수정일",
        value:"updDate",
        data:null,
        update:false,
        insert:false,
        tableShow:true,
        sortable: false,
        search:false,
        counter:5,
        placeholder:" ",
        disabled:true,
        rules: [
          //v => !!v || '필수 입력',
        //  v => (!v || v.length <= 5) || '입력 글자 수 초과.',
        ],
      },
    },

    searchObj: {},
    dataObj:{
      id: localStorage.getItem("loginUserID"),
    },
    tableHeaderArr:[],
  }),
  computed: {
      ...get('appStore', [
      'selectDrawerItem'
    ]),
      ...get('menuStore', [
      'menuList'
    ]),

    ...sync('userStore',[
      'parkSeq','id','parkItemSelected',
    ]),
    ...get('cmmnStore', [
      'cmmnCodeItem', 'useYnItem' , 'displayYnItem'
  ]),
  },


  mounted(){
  //  this.$store.dispatch('userStore/park');
  //  this.$store.dispatch('cmmnStore/code');
    this.$store.dispatch('menuStore/list');
    this.makeTableHeaderArr();
    this.makeSearchObj();
    this.loading = true;  // 돔 그린 후 바인딩 하기 위해 처리
  },

  methods: {

    manageShow() { //화면 오른쪽에 폼을 표시
      this.reset();
      this.viewListCols =  9;
      this.viewInfoDisabled =  true;
    },
      manageHide() { //화면 오른쪽의 폼을 숨김
      this.reset();
      this.viewListCols = 12;
      this.viewInfoDisabled= false;
    },


  makeTableHeaderArr(){  // 테이블의 헤더를 만듦

      var columnObj = this.columnObj;
      for(var i in  columnObj){
        if(columnObj[i].tableShow){

          this.tableHeaderArr.push({
            sortable: columnObj[i].sortable,
            text: columnObj[i].text,
            value: i,   // i ==columnObj[i].value
          })
        }
      }

    },

    makeSearchObj(){  //검색  객체를 만듦
      var columnObj = this.columnObj;
      for(var i in  columnObj){
        if(columnObj[i].search){
          this.searchObj[i] = {
            text:columnObj[i].text,
            value:i,
            data:null,
          }

        }
      }

    },

    validate () {
      var chk = true;
      if(!this.$refs.form.validate()){
        alert("필수항목과 글자수를 확인해 주세요.");
        chk = false;
      }
      return chk;
    },
    validate_old () {
      this.$refs.form.validate()
    },

    reset () {
      this.$refs.form.reset()
    },

    resetValidation () {
      this.$refs.form.resetValidation()
    },

    resetForm () {
      this.errorMessages = []
      this.formHasErrors = false
    },
submit: call('menuStore/submit'),

      async  rowClick  (event, { item } ) {
      await   this.manageShow();
      this.regTypeBool = true
      this.regType= "change";
      for(var i in  this.columnObj){
        this.columnObj[i].disabled= (!this.columnObj[i].update)?true :false
        this.columnObj[i].data =item[i]   // i ==columnObj[i].value
      }
    },

  async searchBtnClick(){

      this.$store.dispatch('menuStore/list');
      await  this.manageHide();

    },
  async addBtnClick( ){  // 신규 버튼 클릭
      await  this.manageShow();
      this.regTypeBool = false;
      this.regType= "add";
      for(var i in  this.columnObj){
        this.columnObj[i].disabled= (!this.columnObj[i].insert)?true :false
      }
    },
    async  saveBtnCLick () {

      if(!this.validate()) return;
      this.dataObj.regType = this.regType
      for(var i in  this.columnObj){
        this.dataObj[i] = this.columnObj[i].data
      }

        await   this.submit(this.dataObj);
        await  this.searchBtnClick(this.searchOj);



    },
  cancelBtnCLick(){
      this.userManageHide();
    },

    async  deleteBtnCLick(){
      this.dataObj.regType = "delete"
      //  this.submit()
      for(var i in  this.columnObj){
        this.dataObj[i] = this.columnObj[i].data
      }
      this.submit(this.dataObj);
      await  this.searchBtnClick(this.searchOj);
    },
    searchBtnClick_old:  call('userStore/searchBtnClick'),

  },
}
</script>
<style scoped>

</style>
