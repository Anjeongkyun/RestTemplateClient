const fs = require("fs");
module.exports = {
  devServer: {
    disableHostCheck: true,
    https: {
      key: fs.readFileSync('./privkey.pem'),
      cert: fs.readFileSync('./cert.pem'),
      ca: fs.readFileSync('./chain.pem'),
    }
  }
}
//출처: https: //koogood.tistory.com/36 [개발자 되버리기:티스토리]
