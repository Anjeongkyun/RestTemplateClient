//store 와 관련된 데이터를 처리하는 공통 함수 파일

var res = (res) => {
  console.log('axios.post res ==================')
  console.log(res)

  var resultStatus = res.status
  var resultStstusText = res.statusText
  var msgStatus = resultStatus + ": " + resultStstusText;

  if (res.status == 200) {

    var resultCd = res.data.resultCd;
    var resultMsg = res.data.resultMsg;
    var resultError = res.data.error
    var msgResult = resultCd + ": " + resultMsg;

    if (resultCd == "00") {
      alert(msgResult)
    } else if (resultCd == "20") {
      alert(msgResult)
    } else if (resultCd == "30") {
      alert(msgResult + "(콘솔을 확인하세요.)");
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


var err = (err) => {
  console.log('axios.post err ==================')
  console.log(err)
  alert(err)
};


export default {
  res,
  err
}
