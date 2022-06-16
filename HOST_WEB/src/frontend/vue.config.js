module.exports = {
  devServer: {
    disableHostCheck: true
  },
  configureWebpack: {
    devtool: "source-map"
  },
  transpileDependencies: ["vuetify"]
};

// /api 요청이 있을 때 해당 요청을 backend 쪽의 /api로 돌려주는 proxy 설정을 추가
module.exports = {
  outputDir: "../main/resources/static",
  devServer: {
    overlay: false,
    historyApiFallback: true,
    proxy: {
      "": {
        target: "http://localhost:5656",
        changeOrigin: true
      }
    }
  }
  // outputDir: './../backend/public',
  // outputDir: './dist',
};
