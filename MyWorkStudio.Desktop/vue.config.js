module.exports = {
    devServer: {
        open: true,
        port: 8888,
        proxy: {
            '/api': {
                target: 'http://localhost:8088', //目标接口域名
                changeOrigin: true, //是否跨域
                pathRewrite: {
                    '^/api': '' //重写接口
                }
            }
        },
    }
}