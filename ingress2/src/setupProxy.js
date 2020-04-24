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
    app.use(
        proxy("/exam/",{
            target:"http://localhost:8080"
        })
    )
    app.use(
        proxy("/p/",{
            target:"http://localhost:8080"
        })
    )
    app.use(
        proxy("/s/",{
            target:"http://localhost:8082"
        })
    )
    app.use(
        proxy("/admin/",{
            target:"http://localhost:8081"
        })
    )
}