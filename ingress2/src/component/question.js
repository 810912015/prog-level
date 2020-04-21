import React,{useState,useEffect} from 'react'
import {post,get} from "./common/network";
import {Link} from "react-router-dom";
import {Badge,Card,Input} from "antd";
import {DesktopOutlined} from "@ant-design/icons";
import {QuestionContext} from "./context";

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
        <QuestionContext.Consumer>
            {
                (c) => {
                    return (
                        <a onClick={() => {
                            post("question/bytag",
                                {meta: props, bound: {size: 100}},
                                (d) => {
                                    c.setList(d.data)
                                })
                        }}>
                            <Card>
                                <div>{props.name}</div>
                                <div>{props.count}</div>
                                <div>{props.desc}</div>
                            </Card>
                        </a>
                    )
                }
            }

        </QuestionContext.Consumer>
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
    let level;
    if(!props.lang){
        level=props.level>3?"困难":props.level<3?"简单":"中等"
    }else{
        level=props.lang;
    }
    let s=level==="简单"?"#558":level==="中等"?"#585":"#855";

    return (
        <span style={{padding:"0 20px",display:"inline-block",lineHeight:"30px"}}>
                <span style={{fontSize:"18px"}}>
                    <Link to={"/pass/"+props.id} >
                     {props.name}
                      </Link>
         </span>
            <span style={{marginLeft:"10px",display:"inline-block"}}>
                 <Badge count={level}   offset={[0,-10]} style={{backgroundColor:s}}/>
            </span>

        </span>

    )
}



export function QuestionBag(props) {
    const [sk,setSk]=useState("")
    useEffect(()=>{
        getData({size:100})
        return ()=>{
            props.setList([])
        }
    },[])
    const getData=(b)=>{
        post("question/all",b,
            (d)=>{
            let o=props.list||[]
            o.push.apply(o,d||[])
            props.setList(o)
            })
    }
    const loadMore=()=>{
        let bound={
            size:50,
            minId:100000000,
            maxId:0,
            forward:true
        }
        props.list.forEach(a=>{
            if(bound.maxId<a.id) bound.maxId=a.id;
            if(bound.minId>a.id) bound.minId=a.id;
        })
        getData(bound)
    }
    const search=()=>{
        if(!sk) {
            getData(100)
        }else {
            get("/s/search?s=100&&k=" + sk, (d) => {
                if (d && d.data && d.data.content) {
                    props.setList(d.data.content)
                }
            })
        }
    }
    let qa=[]
    if(props.list){
        qa=props.list.map(a=>(<AQuestion {...a} key={a.id}/>))
    }
    return (
        <Card type={"inner"} title={
            <div style={{display:"flex"}}>
                <DesktopOutlined />
                <span>习题列表</span>
                <Input.Search onSearch={search}
                              onChange={(e)=>{
                    setSk(e.target.value)
                }} style={{marginLeft:"30px",flex:1}}/>
            </div>
        }>
            {qa}
            <a onClick={loadMore}>更多</a>
        </Card>
    )
}

