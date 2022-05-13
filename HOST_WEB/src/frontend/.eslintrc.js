module.exports = {
  root: true,

  env: {
    node: true,
  },

  plugins: ['vuetify'],

   extends: [
     'plugin:vue/base',
     'plugin:vue/recommended',
     'plugin:vuetify/base'
   ],
  parserOptions: {
    parser: 'babel-eslint',
  },

  rules: {
    'no-console': 'off',
    'no-debugger': 'off',
    // https://github.com/babel/babel-eslint/issues/681#issuecomment-420663038
    'template-curly-spacing': 'off',
  //  indent: 'off',
    //
    'no-unused-vars': 'warn',
    'vuetify/no-deprecated-classes': 'error',
    'vuetify/no-legacy-grid': 'error',
    'indent': ["error", 2]
        
  },

  overrides: [
    {
      files: [
        '**/__tests__/*.{j,t}s?(x)',
        '**/tests/unit/**/*.spec.{j,t}s?(x)',
      ],
      env: {
        jest: true,
      },
    },
  ],
}
