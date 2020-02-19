import {createStore, applyMiddleware} from 'redux'
import thunk from 'redux-thunk'
import rootReducer from '../reducer/root'


export const disStore = preloadedState => {
    return createStore(
        rootReducer,
        preloadedState,
        applyMiddleware(thunk)
    )
}

export const LOGIN_KEY="4d3ff3a69ef0458c905277607c9e2f2a";

export const preExam = () => {
    let p=null;
    try{
        let s=window.sessionStorage.getItem(LOGIN_KEY);
        p=JSON.parse(s)

    }catch (e) {

    }

    if(p==null) p={}

    return {
        login: {
            authed: p.authed|| false,
            uid:
                p.uid || '',
            uname:
                p.uname || '',
            sid:
                p.sid || '',
            pers:
                [],
            msg:
                ''
        }
    }
}

export const createExamStore = () => {

    return disStore(preExam())
}

