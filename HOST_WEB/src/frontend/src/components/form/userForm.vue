<template>
  <v-container>
    {{ loading1 }}
    <div v-if="loading1">
      <v-row>
        <v-col>
          <v-card
            ref="form"
            v-model="valid"
            lazy-validation
          >
            <v-card-text>
              <v-text-field
                v-model="regTypeBool"
                label="regTypeBool"
                counter="5"
                readonly
              />
              <v-text-field
                v-model="regType"
                label="regType"
                counter="5"
                readonly
              />

              <v-text-field
                v-model="columnObj.loginId.data"

                :label="columnObj.loginId.text"
                placeholder=" "
                required
              />


              <v-text-field

                v-model="columnObj"
                label="columnObj"
                placeholder=" "
                required
              />


              <v-text-field
                v-model="columnObj"
                label="이름"
                placeholder=" "
                required
              />

              <v-text-field
                v-model="columnObj"
                label="핸드폰번호"
                placeholder=" "
                required
              />


              <v-text-field
                v-model="columnObj"
                label="blueId"
                placeholder=" "
                required
              />
              <v-text-field
                v-model="columnObj"
                label="carNo"
                placeholder=" "
                required
              />

              <v-text-field
                v-model="columnObj"
                label="carType"
                placeholder=" "
                required
              />




              <v-text-field
                v-model="columnObj"
                label="updDate"
                placeholder=" "
                readonly
              />

              <v-text-field
                v-model="columnObj"
                label="insId"
                placeholder=" "
                readonly
              />
              <v-text-field
                v-model="columnObj"
                label="insDate"
                placeholder=" "
                readonly
              />


              <v-text-field
                v-model="columnObj"
                label="updId"
                placeholder=" "
                readonly
              />
            </v-card-text>

            <v-divider class="mt-12" />
            <v-row justify="space-between">
              <v-col cols="4">
                <v-btn

                  text
                  @click="cancelBtnCLick"
                >
                  취소
                </v-btn>
              </v-col>

              <v-col cols="4">
                <v-btn
                  v-show="regTypeBool"
                  color="error"
                  text
                  @click="deleteBtnCLick"
                >
                  삭제
                </v-btn>
              </v-col>
              <v-col cols="4">
                <v-btn
                  color="secondary"
                  text
                  @click="saveBtnCLick"
                >
                  저장
                </v-btn>
              </v-col>
            </v-row>
          </v-card>
        </v-col>
      </v-row>
    </div>
  </v-container>
</template>
<script>
import { get, set, sync, call } from 'vuex-pathify'
import cmmnFnStore from '../../store/modules/cmmnFnStore' //store 와 관련된 데이터를 처리하는 공통 함수 파일


export default {
  name:'UserForm',

  //props: ['item'],
  data: () => ({
    loading1: false,
    valid: true,
    columnObj:{},

    regTypeBool:false,
    regType:null,

    errorMessages: 'errorMessages 테스트',
    formHasErrors: false,


    rules: {
      aptId: [
        v => !!v || 'aptId is required',
        v => (v && v.length <= 5) || 'aptId must be less than 10 characters',
      ],


      blueId: [
        v => !!v || 'aptId is required',
        v => (v && v.blueId <= 50) || 'aptId must be less than 10 characters',
      ],
      carNo: [
        v => !!v || 'carNo is required',
        v => (v && v.length <= 50) || 'aptId must be less than 10 characters',
      ],
      carType: [
        v => !!v || 'carType is required',
        v => (v && v.length <= 50) || 'aptId must be less than 10 characters',
      ],
      dong: [
        v => !!v || 'dong is required',
        v => (v && v.length <= 50) || 'aptId must be less than 10 characters',
      ],
      ho: [
        v => !!v || 'ho is required',
        v => (v && v.length <= 50) || 'aptId must be less than 10 characters',
      ],
      householdYn: [
        v => !!v || 'householdYn is required',
        v => (v && v.length <= 50) || 'aptId must be less than 10 characters',
      ],
      insDate: [
        v => !!v || 'insDate is required',
        v => (v && v.length <= 50) || 'aptId must be less than 10 characters',
      ],
      insId: [
        v => !!v || 'insId is required',
        v => (v && v.length <= 50) || 'aptId must be less than 10 characters',
      ],
      loginId: [
        v => !!v || 'loginId is required',
        v => (v && v.length <= 50) || 'aptId must be less than 10 characters',
      ],
      loginPw: [
        v => !!v || 'loginPw is required',
        v => (v && v.length <= 50) || 'aptId must be less than 10 characters',
      ],
      memberMobile: [
        v => !!v || 'memberMobile is required',
        v => (v && v.length <= 50) || 'aptId must be less than 10 characters',
      ],
      memberNm: [
        v => !!v || 'memberNm is required',
        v => (v && v.length <= 50) || 'aptId must be less than 10 characters',
      ],
      updDate: [
        v => !!v || 'updDate is required',
        v => (v && v.length <= 50) || 'aptId must be less than 10 characters',
      ],
      updId: [
        v => !!v || 'updId is required',
        v => (v && v.length <= 50) || 'aptId must be less than 10 characters',
      ],
      userActive: [
        v => !!v || 'userActive is required',
        v => (v && v.length <= 50) || 'aptId must be less than 10 characters',
      ],

    }
  }),

  computed: {


  },


  watch: {
    name () {
      this.errorMessages = ''
    },
  },

  mounted(){

    this.loading1 = true;
  },
  methods: {

    validate () {
      this.$refs.form.validate()
    },
    reset () {
      this.$refs.form.reset()
    },
    resetValidation () {
      this.$refs.form.resetValidation()
    },
    changeRegTypeBool(b){
      //양방향 바인딩이 아니라서 함수처리함
      this.regTypeBool = b
    },

    changeColumnObj(b){
      //양방향 바인딩이 아니라서 함수처리함
      this.columnObj = b
    },

    resetForm () {
      this.errorMessages = []
      this.formHasErrors = false

    },
    saveBtnCLick () {

      var dataObj = {
        regType: this.regType,
        aptId: this.aptId,
        blueId: this.blueId,
        carNo: this.carNo,
        carType: this.carType,
        dong: this.dong,
        ho: this.ho,
        householdYn: this.householdYn,
        insDate: this.inDate,
        insId: this.insId,
        loginId: this.loginId,
        loginPw: this.loginPw,
        memberMobile: this.memberMobile,
        memberNm: this.memberNm,
        updDate: this.updDate,
        updId: this.updId,
        userActive: this.userActive,
      }
      //  this.submit(dataObj)
      this.submit(dataObj);
    },
    cancelBtnCLick(){

      var userStore =   this.$store.state.userStore;
      userStore.viewListCols = 12;
      userStore.viewInfoDisabled= false;

    },

    deleteBtnCLick(){
      this.regType= "delete"
      //  this.submit()
      this.submit();
    },

    submit: call('userStore/submit'),
  },
}
</script>

<style scoped>



</style>
