import React,{useState,useEffect} from 'react'
import {post} from "./common/network";
import {Link} from "react-router-dom";
import {Badge,Card} from "antd";
import {DesktopOutlined} from "@ant-design/icons";

function AQuestion(props) {
    let s=props.lang==="简单"?"#558":props.lang==="中等"?"#585":"#855";

    return (
        <span key={props.id} style={{padding:"0 20px",display:"inline-block",lineHeight:"30px"}}>
                <span style={{fontSize:"18px"}}>
                    <Link to={"/pass/"+props.id} >
                     {props.name}
                      </Link>
         </span>
            <span style={{marginLeft:"10px",display:"inline-block"}}>
                 <Badge count={props.lang}   offset={[0,-10]} style={{backgroundColor:s}}/>
            </span>

        </span>

    )
}

export function QuestionBag() {
    const [bag,setBag]=useState([])
    useEffect(()=>{
        post("question/all",{size:50},(d)=>{
            setBag(d)
        })
    },[])
    const loadMore=()=>{

    }
    let qa=[]
    if(bag){
        qa=bag.map(a=>(<AQuestion {...a}/>))
    }
    return (
        <Card type={"inner"} title={
            <div>
                <DesktopOutlined />
                <span>习题列表</span>
            </div>

        }>
            {qa}
            <a onClick={loadMore}>更多</a>
        </Card>
    )
}

