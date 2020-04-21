import React, {useRef, useState,useEffect} from 'react'
import {Progress} from "antd";
import {QuestionContext} from "../context";

export function Bi() {
    return (
        <QuestionContext.Consumer>
            {
                (c)=> { return (<BusyIndicator2 busy={c.busy}/>)}
            }
        </QuestionContext.Consumer>
    )
}

function BusyIndicator2(props) {
    const [show,setShow]=useState(false)
    const [percentage,setPercentage]=useState(0)
    const ptRef=useRef(percentage);
    ptRef.current=percentage
    let th;
    useEffect(()=>{
        if(props.busy){
            setPercentage(0)
            th=setTimeout(handle,500)
            setShow(true)
        }else {
            setPercentage(100)
        }
    },[props.busy])
    const handle=()=>{
        if(ptRef.current>=100){
            clearTimeout(th)
            setShow(false)
            return;
        }
        let p=ptRef.current+10;
        if(p>99){
            p=99
        }
        setPercentage(p)
        th=setTimeout(handle,500)
    }
    if(!show){
        return null;
    }else{
        return (
            <Progress style={{position:"absolute",width:"100%",marginTop:"-15px",padding:0}}
                      strokeWidth={3} showInfo={false}
                      size={"small"} type={"line"} percent={percentage} status={"active"} trailColor={"#950"} strokeColor={"#f50"}/>
        )
    }

}