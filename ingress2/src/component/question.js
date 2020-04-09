import React,{useState,useEffect} from 'react'
import {post,get} from "./common/network";
import {Link} from "react-router-dom";
import {Badge,Card,Input} from "antd";
import {DesktopOutlined,UnorderedListOutlined} from "@ant-design/icons";

function RecommendItem(props) {
    return (
        <Card type={"inner"} title={
            <div>{props.name}</div>
        }>
            <div>{props.list.map(a=>(<div key={a.id}>
                <Link to={"/pass/"+a.id}>{a.name}</Link></div>))}</div>
        </Card>
    )
}

export function RecommendBag() {
    const [recommend,setRecommend]=useState([])
    useEffect(()=>{
        get("question/recommend",(d)=>setRecommend(d.data))
    },[])
    let ta=[]
    if(recommend){
        ta=recommend.map(a=>(<RecommendItem {...a} key={a.name}/>))
    }
    return (
        <div>
            {ta}
        </div>
    )
}

function Tag(props) {
    return (
        <Card type={"inner"} title={props.name}>
            <div>{props.count}</div>
            <div>{props.desc}</div>
        </Card>
    )
}
export function TagBag(){
    const [tags,setTags]=useState([])
    useEffect(()=>{
        get("question/tags",(d)=>{
            setTags(d.data)
        })
    },[])
    let ta=[];
    if(tags){
        ta=tags.map(a=>(<Tag {...a} key={a.name}/>))
    }
   return (
        <div style={{verticalAlign:"right"}}>
            {ta}
        </div>
   )
}

function AQuestion(props) {
    let s=props.lang==="简单"?"#558":props.lang==="中等"?"#585":"#855";

    return (
        <span style={{padding:"0 20px",display:"inline-block",lineHeight:"30px"}}>
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
        post("question/all",{size:100},(d)=>{
            setBag(d)
        })
    },[])
    const loadMore=()=>{

    }
    let qa=[]
    if(bag){
        qa=bag.map(a=>(<AQuestion {...a} key={a.id}/>))
    }
    return (
        <Card type={"inner"} title={
            <div style={{display:"flex"}}>
                <DesktopOutlined />
                <span>习题列表</span>
                <Input.Search style={{marginLeft:"30px",flex:1}}/>
            </div>

        }>
            {qa}
            <a onClick={loadMore}>更多</a>
        </Card>
    )
}

