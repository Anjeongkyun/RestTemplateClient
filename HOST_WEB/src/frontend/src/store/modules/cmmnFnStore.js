//store 와 관련된 데이터를 처리하는 공통 함수 파일

import store from "@/store";

var res = async (res) => {
//  console.log('axios.post res ==================')
  console.log(  await res)

  var resultStatus = res.status
  var resultStstusText = res.statusText
  var msgStatus = resultStatus + ": " + resultStstusText;

  if (res.status == 200) {

    var resultCd = res.data.resultCd;
    var resultMsg = res.data.resultMsg;
    var resultError = res.data.error
    var msgResult = resultCd + ": " + resultMsg;

    if (resultCd == "00") {
      //  alert(msgResult)
      console.log(msgResult)
    } else if (resultCd == "20") {
      alert(msgResult)
      console.log(msgResult)
    } else if (resultCd == "30") {
      alert(msgResult + "(콘솔을 확인하세요.)");
      console.log(msgResult + "(콘솔을 확인하세요.)");
      for (var i in resultError) {
        console.log(resultError[i].field + ": " + resultError[i].message);
      }
    } else {
      alert(msgResult);
    }

  } else if (res.status == 400) {
    alert(msgStatus)
  }
};


var err = async  (err) => {
  console.log('axios.post err ==================')
  console.log(await err)
  alert(err)
};

var fn = {
  code: async function (list, code) {
    var arr = [];
    for (var i in list) {

      if (list[i].grCd == code) {
        arr.push(list[i])
      }
    }

    return await arr;
  },
  async getCmmnCodeGroup(grCd, subKindCd) {
    //cmmnFnStore.fn.getCmmnCodeGroup("PC","04");
    var list = store.getters["cmmnStore/cmmnCode"]

    var arr = [];
    for (var i in list) {
      if (list[i].grCd == grCd && list[i].subKindCd) {
        arr.push(list[i])
      }
    }

    return await arr;
  },

  async getCmmnCodeNameMapping(list, code) {
    list.forEach(element => {
    //  element.levelNm = element.levelCd;

      if (code == "cmmnCode") { 
        var mapping = store.getters["cmmnStore/cmmnCode"]
        //PC0401 을 시스템 관리자로 매핑
        mapping.forEach(element1 => {
          if (element.levelCd == element1.cmCd) {
            element.levelNm = element1.cdNm;
          }
        });

      }
      

      if (code == "cmmnUseYn") {
        //사용 여부 매핑
        var mapping = store.getters["cmmnStore/cmmnUseYn"]
        cmmnUseYn.forEach(element1 => {
          if (element.useYn == element1.valueItem) {
            element.useNm = element1.textItem
          }
        });
      }
      if (code == "cmmnCode") { }
    

      //사용자 매핑
      list.forEach(element1 => {
        if (element.updId == element1.loginId) {
          element.updNm = element1.memberNm;
        }
        if (element.insId == element1.loginId) {
          element.insNm = element1.memberNm

        }
      });
    });

    return await arr;
  },
  test() {
    return 12354
  }

}

export default {
  res,
  err,
  fn
}
