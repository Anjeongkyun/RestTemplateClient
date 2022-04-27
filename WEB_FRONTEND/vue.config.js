
module.exports = {
  devServer: {
    disableHostCheck: true,
  },

  transpileDependencies: ['vuetify'],
}

// /api 요청이 있을 때 해당 요청을 backend 쪽의 /api로 돌려주는 proxy 설정을 추가
module.exports = {
  devServer: {
    historyApiFallback: true,
    proxy: {
      '/api': {
        target: 'http://localhost:3000/',
        changeOrigin: true,
        pathRewrite: {
          '^/api': '',
        },
      },
    },
  },
  outputDir: '../backend/public',
  // outputDir: './../backend/public',
  // outputDir: './dist',
}
