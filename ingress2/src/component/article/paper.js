import React, {useEffect, useRef, useState} from 'react'
import {get, post} from "../common/network";
import {Row, Col, Affix,Drawer,Button} from "antd";
import ReactHtmlParser from "react-html-parser";
import {BarsOutlined} from "@ant-design/icons";
import {QuestionContext} from "../context";

export function PaperList(props) {
    let s=props.st||{textAlign:"right",paddingRight:"15px"}
    let r=[]
    if(props.items){
        props.items.forEach(a=>{
            r.push(
                <div key={a.id}>
                    <a onClick={()=>props.click(a.id)}>{a.cName}</a>
                </div>
            )
        })
    }
    return (
        <div style={s}>{r}</div>
    )
}
export function APaper(props) {
    const [item,setItem]=useState(null)
    const [version,setVersion]=useState("c")

    useEffect(()=>{
        if(!props.id) return;
        get("/p/article/detail?id="+props.id+"&version="+version,(d)=>{
            setItem(d.data)
        })
    },[props.id,version])
    let r;
    if(item) {
        r = ReactHtmlParser(item.cHtml)
    }
    return (
        <div style={{wordBreak:"break-all",wordWrap:"break-word",padding:"20px"}}>
            <div style={{display:"flex"}}>
                <Button type={"link"} onClick={props.onShow}><BarsOutlined style={{fontSize:"20px"}}/></Button>
                <span style={{flex:1}} >
                     <Button type={"link"} onClick={()=>setVersion("c")}>中文</Button>
                     <Button type={"link"} onClick={()=>setVersion("e")}>英文</Button>
                     <Button type={"link"} onClick={()=>setVersion("m")}>中英</Button>
                    <a href={item&&item.sourceUrl} target={"_new"}>原文</a>
                </span>
            </div>
            <div className={"t-article"}>
                {r}
            </div>

        </div>
    )
}

export function Papers(props) {

    return (
        <QuestionContext.Consumer>
            {
                (c)=><Papers2 {...c} {...props}/>
            }
        </QuestionContext.Consumer>
    )
}

function Papers2(props) {
    const [items,setItems]=useState([])
    const [cur,setCur]=useState(props.match.params.id||0)

    useEffect(()=>{
        post("/p/article/list",{size:100},(d)=>{
            setItems(d.data)
        })
    },[])
    let c;
    if(!cur){
        c=(

            <PaperList st={{padding:"10px"}} items={items} click={(id)=>{
                props.setShowArticles(false)
                setCur(id)
                props.history.push("papers/"+id)
            }}/>

        )
    }else{
        c=( <APaper id={cur||(items&&items.length&&items[0].id)} onShow={()=>props.setShowArticles(true)}/>)
    }
    return (
        <div>
            <Drawer title="文章列表"
                    placement={window.innerWidth<800?"top":"left"}
                    closable={true}
                    onClose={()=>props.setShowArticles(false)}
                    mask={false}
                    width={"400px"}
                    visible={props.showArticles}>
                <PaperList items={items} click={(id)=>{
                    props.setShowArticles(false)
                    setCur(id)
                }}/>
            </Drawer>
            {c}
        </div>
    )
}