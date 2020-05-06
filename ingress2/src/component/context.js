import React from 'react'

export const QuestionContext=React.createContext({
    list:[],
    setList:()=>{},
    loginName:null,
    setLoginName:()=>{},
    busy:false,
    setBusy:()=>{},
    showArticles:false,
    setShowArticles:()=>{}
})

