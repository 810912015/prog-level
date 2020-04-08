const proxy=require('http-proxy-middleware')

module.exports=function (app) {
    app.use(
        proxy("/store/",{
            target:"http://localhost:8080"
        })
    )
}