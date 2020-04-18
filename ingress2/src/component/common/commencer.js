import React, {useEffect, useRef, useState} from "react";
import PropTypes from 'prop-types'
//倒计时器
export const Commencer=(props)=>{
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
    let st;
    const handle=()=>{
        if(ref.current<=0){
            clearTimeout(st)
            setCan(true)
            setTip(null)
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
Commencer.propTypes={
    can:PropTypes.bool,//是否禁用
    tip:PropTypes.string.isRequired,//动作提示
    total:PropTypes.number,//总倒计时秒数
    step:PropTypes.number,//步长秒数
    call:PropTypes.func,//操作执行，参数是setTip用来设置操作提示
    children:PropTypes.func//render props：can-是否禁用，tip-计算好的动作提示，start-开始倒计时
}