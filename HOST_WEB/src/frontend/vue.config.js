/*
module.exports = {
  devServer: {
    disableHostCheck: true,
  },

  transpileDependencies: ['vuetify'],
} */

// /api 요청이 있을 때 해당 요청을 backend 쪽의 /api로 돌려주는 proxy 설정을 추가
module.exports = {
  transpileDependencies: ['vuetify'],
  outputDir: '../main/resources/static',
  devServer: {
    disableHostCheck: true,
    overlay: false,
    historyApiFallback: true,
    proxy: {
      '': {
        target: 'https://localhost:5656', // '' 로 들어오면 포트 5656(스프링 서버)로 보낸다
        changeOrigin: true, // cross origin 허용
      },
    },
  },
  // outputDir: './../backend/public',
  // outputDir: './dist',
}
