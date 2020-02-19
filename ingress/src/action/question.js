import {JPostData, post} from "../components/common/util";

export const ALL_QUESTION = "all_question"

export const allQuestion = (data) => (dispatch, getState) => {

    post(new JPostData("/question/all", data.data, (d) => {
        let ad = {
            type: ALL_QUESTION,
            pager: d
        }
        dispatch(ad)


    }))
}

export const MY_QUESTION="my_question"

export const myQuestion=(data)=>(dispatch,getState)=>{
    post(new JPostData("/question/mine",data.data,(d)=>{
        let ad={
            type:MY_QUESTION,
            uid:getState().login.uid,
            list:d
        }
        dispatch(ad)
    }))
}

export const CREATE_QUESTION="create_question"

export const createQuestion=(data)=>(dispatch,getState)=>{

    post(new JPostData("/question/create",data.data,(d)=>{

        dispatch({
            type:CREATE_QUESTION,
            data:d
        })

        data.callback();
    }))
}

export const DEL_QUESTION="del_question"

export const delQuestion=(id)=>(dispatch,getState)=>{
    post(new JPostData("/question/delete/"+id,{},(d)=>{
        dispatch({
            type:DEL_QUESTION,
            id:id
        })
    }))
}
