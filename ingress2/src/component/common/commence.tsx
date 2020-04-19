import * as React from "react";

const {useEffect, useRef, useState}=React
export interface SetTip {
    (a:string):void
}
export interface Start {
    ():void
}
export interface CommenceProps {
    can:boolean,
    tip:string,
    call(a:SetTip):void,
    children(a:boolean,b:string,c:Start):any,
    step?:number,
    total?:number
}
//倒计时器
export const Commence=(props:CommenceProps)=>{
    const [can,setCan]=useState(false)
    const [sec,setSec]=useState(-1)
    const [tip,setTip]=useState("")
    const ref=useRef(sec)
    ref.current=sec;
    const makeTip=()=>{
        let sendTip
        if(sec>0){
            sendTip=tip+sec+"秒后重新"+props.tip;
        }else{
            sendTip=props.tip;
        }
        return sendTip;
    }
    useEffect(()=>{
        setCan(props.can)
    },[props.can])
    let st:any;
    const handle=()=>{
        if(ref.current<=0){
            clearTimeout(st)
            setCan(true)
            setTip("")
            setSec(-1)
            return;
        }
        clearTimeout(st)
        setSec(ref.current-1)
        st=setTimeout(handle,(props.step||1)*1000)
    }
    const sendConfirm=()=>{
        if(!can) return;
        setCan(false)
        setSec(props.total||30)
        props.call(setTip);
        st=setTimeout(handle,(props.step||1)*1000)
    }
    return (
        <>
        {props.children(can,makeTip(),sendConfirm)}
       </>
    )
}
