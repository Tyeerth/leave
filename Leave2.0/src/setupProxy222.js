//setupProxy.js
const proxy = require('http-proxy-middleware')

module.exports = function (app) {
    app.use(proxy('/api/sliderApi',
        {
            target: "https://c.y.qq.com/musichall/fcgi-bin/fcg_yqqhomepagerecommend.fcg?g_tk=1928093487&inCharset=utf-8&outCharset=utf-8&notice=0&format=jsonp&platform=h5&uin=0&needNewCode=1&jsonpCallback=__jp0",
            changeOrigin: true,
            pathRewrite: {
                "^/api": "/"
            }
        }))
}
