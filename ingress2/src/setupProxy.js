const proxy=require('http-proxy-middleware')

module.exports=function (app) {
    app.use(
        proxy("/question/",{
            target:"http://localhost:8080"
        })
    )
    app.use(
        proxy("/auth/",{
            target:"http://localhost:8080"
        })
    )
}