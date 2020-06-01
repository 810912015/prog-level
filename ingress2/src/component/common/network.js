export const busy = {
    cur: false,
    cba: []
}
busy.set = function (b) {
    busy.cur = b;
    for (let i = 0; i < busy.cba.length; i++) {
        busy.cba[i](busy.cur)
    }
}

export const authHeader={
    key:"authorization",
    setStr:(str)=>{
       window.localStorage.setItem(authHeader.key,str)
        if(str&&str.startsWith("00")){
            authHeader.setName("")
        }
    },
    getStr:()=>{
        return window.localStorage.getItem(authHeader.key)
    },
    setName:(name)=>{
        window.localStorage.setItem("name",name)
    },
    getName:()=>window.localStorage.getItem("name")
}

busy.register = (f) => {
    busy.cba.push(f);
}
const redirect=(res,m)=>{
    if(res.headers.has(authHeader.key)){
        authHeader.setStr(res.headers.get(authHeader.key))
    }
    return res.json();
}
export const get=(url,success,fail)=>{
    busy.set(true)
    fetch(url,{
        method:'GET',
        headers: new Headers({
            'Content-Type': 'application/json',
            [authHeader.key]:authHeader.getStr()
        })
    }).then(res=>{
        return redirect(res,"get")
    }).then(d1=>{
        success(d1);
        busy.set(false)
    })
        .catch(ex=>{
            if(typeof fail==='function') fail(ex);
            else console.log(ex);
            busy.set(false)
        })
}

const makeLoginUrl=()=>{
    let u=window.location.href;
    let i=u.indexOf("#/");
    if(i<0){
        window.location.href+="login"
    }else{
        let p=u.substr(0,i+2)+"login";
        window.location.href=p;
    }
}

export const post = (url,data,success,fail) => {
    busy.set(true)
    fetch(url, {
        method: "POST",
        body: JSON.stringify(data),
        headers: new Headers({
            'Content-Type': 'application/json',
            [authHeader.key]:authHeader.getStr()
        })
    }).then(res => {
        return redirect(res,'post')
    }).then(p => {
        if(p.code===401||p.code===403){
            makeLoginUrl();
            busy.set(false)
            return
        }
        success(p)
        busy.set(false)
    })
        .catch(ex => {
            if (fail) fail(ex);
            else console.log(ex);
            busy.set(false)
        })

}
export const upload = function (url, file, success, fail) {
    busy.set(true)
    let filedata = new FormData();
    filedata.append('file', file, file.name);
    filedata.append('path', file.path);

    fetch(url, {
        method: 'POST',
        body: filedata,
        headers:new Headers({
            [authHeader.key]:authHeader.getStr()
        })
    }).then(res =>{
        return redirect(res,"upload")
    })
        .then(result => {
            if(result.code===401||result.code===403){
                makeLoginUrl()
                busy.set(false)
                return
            }
            busy.set(false)
            success(result);

        }).catch((e)=>{
            busy.set(false)
        fail(e);
    })
}