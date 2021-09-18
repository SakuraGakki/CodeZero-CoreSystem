// see http://vuejs-templates.github.io/webpack for documentation.
var path = require('path')

var config = {
  build: {
    env: require('./prod.env'),
    port: 9000,
    index: path.resolve(__dirname, '../dist/index.html'),
    assetsRoot: path.resolve(__dirname, '../dist'),
    assetsSubDirectory: 'static',
    assetsPublicPath: '/',
    baseServerUrl: 'http://59.110.218.235:8000',
    productionSourceMap: true,
    // Gzip off by default as many popular static hosts such as
    // Surge or Netlify already gzip all static assets for you.
    // Before setting to `true`, make sure to:
    // npm install --save-dev compression-webpack-plugin
    productionGzip: false,
    productionGzipExtensions: ['js', 'css'],
    // Run the build command with an extra argument to
    // View the bundle analyzer report after build finishes:
    // `npm run build --report`
    // Set to `true` or `false` to always turn it on or off
    bundleAnalyzerReport: process.env.npm_config_report
  },
  dev: {
    env: require('./dev.env'),
    port: 9999,
    autoOpenBrowser: true,
    assetsSubDirectory: 'static',
    assetsPublicPath: '/',
    baseServerUrl: 'http://localhost:8000',
    proxyTable: {},
    // CSS Sourcemaps off by default because relative paths are "buggy"
    // with this option, according to the CSS-Loader README
    // (https://github.com/webpack/css-loader#sourcemaps)
    // In our experience, they generally work as expected,
    // just be aware of this issue when enabling this option.
    cssSourceMap: false
  }
}

// 需要代理的接口
var proxyList = [
  '/cms'
]

const targetPath = config.dev.baseServerUrl // 服务器的地址 可以使www.xx.com

for (let i = 0; i < proxyList.length; i++) {
  config.dev.proxyTable[proxyList[i]] = {
    target: targetPath,
    secure: false,
    changeOrigin: true
  }
}
// console.info(Object.keys(config.dev.proxyTable))
module.exports = config
