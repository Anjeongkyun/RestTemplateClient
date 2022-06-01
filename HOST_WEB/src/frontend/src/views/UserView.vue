<template dark>
  <v-container>
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
                  cols="4"
                  sm="12"
                  lg="4"
                >
                  <v-text-field
                    v-model="searchObj.loginId.data"
                    :label="searchObj.loginId.text"
                    placeholder=" "
                    required
                  />
                </v-col>

                <v-col
                  class="d-flex justify-center mb-1"
                  cols="4"
                  sm="12"
                  lg="4"
                >
                  <v-text-field
                    v-model="searchObj.memberNm.data"
                    :label="searchObj.memberNm.text"
                    placeholder=" "
                    required
                  />
                </v-col>
                <!--

                <v-col
                  class="d-flex justify-center mb-1"
                  cols="2"
                  sm="12"
                  lg="2"
                >
                  <v-select
                  v-model="parkItemSelected"
                  :items="parkItem"
                  label="Item"
                  required
                  item-text="aptNm"
                  item-value="aptId"
                />

                <v-select
                  v-model="parkItemSelected"
                  light
                  solo
                  dense
                  label="주차장 식별을 위한 고유 값"
                  color="pink"
                  background-color="accent lighten-5"
                  :items="parkItem"
                  item-text="aptNm"
                  item-value="aptId"
                  :hint1="`${parkItemSelected.aptNm}, ${parkItemSelected.aptId}`"
                  hint="주차장식별을 위한 고유 값"
                  persistent-hint
                  return-object
                  single-line
                />
                </v-col>
                -->

                <v-col
                  class="d-flex justify-center mb-1"
                  cols="2"
                  sm="12"
                  lg="2"
                >
                  <v-btn
                    class="mb-3 "
                    block
                    depressed
                    color="primary "
                    @click="searchBtnClick(searchObj) "
                  >
                    검색
                  </v-btn>
                </v-col>

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
                  :items="tableData"
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
                        v-model="columnObj.loginId.data"
                        :label="columnObj.loginId.text"
                        :rules="columnObj.loginId.rules"
                        :placeholder="columnObj.loginId.placeholder"
                        :counter="columnObj.loginId.counter"
                        :disabled="columnObj.loginId.disabled"
                        required
                      />

                      <v-text-field
                        v-model="columnObj.memberNm.data"
                        :label="columnObj.memberNm.text"
                        :rules="columnObj.memberNm.rules"
                        :placeholder="columnObj.memberNm.placeholder"
                        :counter="columnObj.memberNm.counter"
                        :disabled="columnObj.memberNm.disabled"
                        required
                      />

                      <!-- <v-text-field
                        v-model="columnObj.memberRole.data"
                        :label="columnObj.memberRole.text"
                        :rules="columnObj.memberRole.rules"
                        :placeholder="columnObj.memberRole.placeholder"
                        :counter="columnObj.memberRole.counter"
                        :disabled="columnObj.memberRole.disabled"
                        required
                      /> -->

                      <v-text-field
                        v-model="columnObj.memberPhone.data"
                        :label="columnObj.memberPhone.text"
                        :rules="columnObj.memberPhone.rules"
                        :placeholder="columnObj.memberPhone.placeholder"
                        :counter="columnObj.memberPhone.counter"
                        :disabled="columnObj.memberPhone.disabled"
                        required
                      />

                      <!-- <v-text-field
                        v-model="columnObj.useYn.data"
                        :label="columnObj.useYn.text"
                        :rules="columnObj.useYn.rules"
                        :placeholder="columnObj.useYn.placeholder"
                        :counter="columnObj.useYn.counter"
                        :disabled="columnObj.useYn.disabled"
                        required
                      /> -->

                      <v-select
                        v-model="columnObj.useYn.data"
                        :label="columnObj.useYn.text"

                        :rules="columnObj.useYn.rules"
                        :items="useYnItem"
                        item-text="textItem"
                        item-value="valueItem"
                        required
                      />
                      <!-- <v-text-field
                        v-model="columnObj.levelCd.data"
                        :label="columnObj.levelCd.text"
                        :rules="columnObj.levelCd.rules"
                        :placeholder="columnObj.levelCd.placeholder"
                        :counter="columnObj.levelCd.counter"
                        :disabled="columnObj.levelCd.disabled"
                        required
                      /> -->

                      <!-- <v-select
                        v-model="parkItemSelected"
                        :items="parkItem"
                        label="Item"
                        required
                        item-text="aptNm"
                        item-value="aptId"
                      /> -->

                      <v-select
                        v-model="columnObj.levelCd.data"
                        :items="cmmnCodeItem"
                        :rules="columnObj.levelCd.rules"
                        :label="columnObj.levelCd.text"
                        item-text="cdNm"
                        item-value="cmCd"
                        required
                      />

                      <!-- <v-select
                        v-model="columnObj.levelCd.data"
                        :items="cmmnCodeItem"
                        :rules="[v => !!v || 'Item is required']"
                        label="Item"
                        required
                      /> -->
                      <!-- <v-text-field
                        v-model="columnObj.firstRun.data"
                        :label="columnObj.firstRun.text"
                        :rules="columnObj.firstRun.rules"
                        :placeholder="columnObj.firstRun.placeholder"
                        :counter="columnObj.firstRun.counter"
                        required
                      /> -->

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
  name: 'UserView',

  components: {
    //userForm: () => import('@/components/form/userForm'),
  },

  data: () => ({
    valid: true,
    regTypeBool:false,
    regType:null,
    items: ['Foo', 'Bar', 'Fizz', 'Buzz'],
    errorMessages: 'errorMessages 테스트',
    formHasErrors: false,

    viewListCols: 12,
    viewInfoCols:3,
    viewInfoDisabled:false,

    loading: false,

    columnObj: {
      loginId : {
        text:"ID",
        value:"loginId",
        data:null,
        update:false,
        insert:true,
        tableShow:true,
        sortable: false,
        search:true,
        counter:5,
        placeholder:" ",
        disabled:true,
        rules: [
          v => !!v || '필수 입력',
          v => (v && v.length <= 5) || '입력 글자 수 초과.',
        ],
      },
	    loginPw: {
        text:"비밀번호",
        value:"loginPw",
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
          v => !!v || '필수 입력',
          v => (v && v.length <= 5) || '입력 글자 수 초과.',
        ],
      },
	    memberNm: {
        text:"이름",
        value:"memberNm",
        data:null,
        update:true,
        insert:true,
        tableShow:true,
        sortable: false,
        search:true,
        counter:5,
        placeholder:" ",
        disabled:true,
        rules: [
          v => !!v || '필수 입력',
          v => (v && v.length <= 5) || '입력 글자 수 초과.',
        ],
      },
	    memberRole: {
        text:"memberRole",
        value:"memberRole",
        data:null,
        update:true,
        insert:true,
        tableShow:false,
        sortable: false,
        search:false,
        counter:5,
        placeholder:" ",
        disabled:true,
        rules: [
          //v => !!v || '필수 입력',
          v => (!v || v.length <= 5) || '입력 글자 수 초과.',
        ],
      },
	    memberPhone: {
        text:"전화번호",
        value:"memberPhone",
        data:null,
        update:true,
        insert:true,
        tableShow:true,
        sortable: false,
        search:false,
        counter:20,
        placeholder:" ",
        disabled:true,
        rules: [
          //v => !!v || '필수 입력',
          v => (!v || v.length <= 20) || '입력 글자 수 초과.',
        ],
      },
	    useYn: {
        text:"사용",
        value:"useYn",
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
	    levelCd: {
        text:"권한",
        value:"levelCd",
        data:null,
        update:true,
        insert:true,
        tableShow:true,
        sortable: false,
        search:false,
        counter:6,
        placeholder:" ",
        disabled:true,
        rules: [
          v => !!v || '필수 입력',
        //  v => (v && v.length <= 6) || '입력 글자 수 초과.',
        ],
      },
	    firstRun: {
        text:"초기실행여부",
        value:"firstRun",
        data:null,
        update:false,
        insert:false,
        show:false,
        sortable: false,
        search:false,
        counter:1,
        placeholder:" ",
        disabled:true,
        rules: [
          v => !!v || '필수 입력',
          v => (v && v.length <= 1) || '입력 글자 수 초과.',
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
    ...get('userStore', [
      'parkItem','tableData'
    ]),
    ...sync('userStore',[
      'parkSeq','id','parkItemSelected',
    ]),
    ...get('cmmnStore', [
      'cmmnCodeItem', 'useYnItem'

      // cdNm: "사용자"
      // cmCd: "PC0403"
      // grCd: "PC"
      // subCd: "03"
      // subKindCd: "04"
    ]),
  },

  mounted(){
    //this.$store.dispatch('userStore/park');
    this.$store.dispatch('cmmnStore/code');
    this.makeTableHeaderArr();
    this.makeSearchObj();
    this.loading = true;  // 돔 그린 후 바인딩 하기 위해 처리
  },

  methods: {

    test_col(){
      /*
login_id
login_pw
member_nm
member_role
member_phone
use_yn
ins_id
ins_date
upd_id
upd_date
level_cd
first_run

loginId
loginPw
memberNm
memberRole
memberPhone
useYn
levelCd
firstRun
insId
insDate
updId
updDate
*/
    },
    //함수=======================================
    userManageShow() { //화면 오른쪽에 폼을 표시
      this.reset();
      this.viewListCols =  9;
      this.viewInfoDisabled =  true;
    },

    userManageHide() { //화면 오른쪽의 폼을 숨김
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

    submit: call('userStore/submit'),
    idCheck: call('userStore/idCheck'),

    //이벤트=====================================
    async  rowClick  (event, { item } ) {
      await   this.userManageShow();
      this.regTypeBool = true
      this.regType= "change";
      for(var i in  this.columnObj){
        this.columnObj[i].disabled= (!this.columnObj[i].update)?true :false
        this.columnObj[i].data =item[i]   // i ==columnObj[i].value
      }
    },

    async searchBtnClick(){

      this.$store.dispatch('userStore/searchBtnClick',this.searchObj);
      await  this.userManageHide();

    },

    async addBtnClick( ){  // 신규 버튼 클릭
      await  this.userManageShow();
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
      var chk = true;
      if(this.regType == "add"){  // 신규일때만 ID 중복 검사

        chk =  await this.idCheck(this.dataObj);
      }

      if( await chk) {
        await   this.submit(this.dataObj);
        await  this.searchBtnClick(this.searchOj);

      }

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

div >>> .v-alert .v-input__control .v-messages__message{
  color: #ffffff
}

</style>
