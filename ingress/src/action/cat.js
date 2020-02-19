import {JPostData, post} from "../components/common/util";

export const LOGIN = "login_act"

export const login = (data,cb) => {
    return (dispatch, getState) => {
        post(new JPostData(data.url, data.ca, (d) => {
            if (d.success) {
                let ad = {
                    type: LOGIN,
                    data: d.data
                }

                dispatch(ad)
                if(typeof cb==='function'){
                    cb();
                }
            }else{
                let t=Object.assign({},d.data)
                t.msg=d.msg;
                let ad={
                    type:LOGIN,
                    data:t
                }
                dispatch(ad)
            }
        }))
    }
}

export const LOGOUT="logout_cat"

export const logout=(data)=>{
    return (dispatch,getState)=>{

        post(new JPostData(data.url,data.data,(d)=>{

            if(d.success){
                let ad={
                    type:LOGOUT,
                    data:d.data
                }
                dispatch(ad)
            }
        }))
    }
}

export const REGISTRE="register_act"

export const register=(data)=>{
    return (dispatch,getState)=>{

        post(new JPostData(data.url,data.data,(d)=>{
            if(d.success){
                let ad={
                    type:REGISTRE,
                    data:d.data
                }
                dispatch(ad)
            }else{
                let t=Object.assign({},d.data);
                t.msg=d.msg;
                let ad={
                    type:REGISTRE,
                    data:t
                }
                dispatch(ad)
            }
        }))
    }
}

export const RESET="reset_act"

export const reset=(data)=>(dispatch,getState)=>{
    post(new JPostData(data.url,data.data,(d)=>{
        if(d.success){
            let ad={
                type:RESET,
                data:d.data
            }
            dispatch(ad)
        }else{
            let t=Object.assign({},d.data);
            t.msg=d.msg;
            let ad={
                type:RESET,
                data:t
            }
            dispatch(ad)
        }
    }))
}

