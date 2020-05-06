import React, {useEffect, useState} from 'react'
import {get, post} from "../common/network";
import {Row, Col, Affix,Drawer,Button} from "antd";
import ReactHtmlParser from "react-html-parser";
import {BarsOutlined} from "@ant-design/icons";
import {QuestionContext} from "../context";

export function PaperList(props) {
    let r=[]
    if(props.items){
        props.items.forEach(a=>{
            r.push(
                <div>
                    <a onClick={()=>props.click(a.id)}>{a.cName}</a>
                </div>
            )
        })
    }
    return (
        <div style={{textAlign:"right",paddingRight:"15px"}}>{r}</div>
    )
}
export function APaper(props) {
    const [item,setItem]=useState(null)
    useEffect(()=>{
        if(!props.id) return;
        get("/p/article/detail?id="+props.id,(d)=>{
            setItem(d.data)
        })
    },[props.id])

    return (
        <div style={{wordBreak:"break-all",wordWrap:"break-word",padding:"20px"}}>
            <div style={{display:"flex"}}>
                <a onClick={props.onShow} style={{width:"50px"}}><BarsOutlined style={{fontSize:"20px"}}/> </a>
                <span style={{flex:1}} >
                    <span></span>
                     <a style={{marginLeft:"10px"}} href={item&&item.sourceUrl} target={"_new"}>{item&&item.name}</a>
                </span>

            </div>
            <div className={"t-article"}>
                {item&&ReactHtmlParser(item.cHtml)}
            </div>

        </div>
    )
}

export function Papers() {

    return (
        <QuestionContext.Consumer>
            {
                (c)=><Papers2 {...c}/>
            }
        </QuestionContext.Consumer>
    )
}

function Papers2(props) {
    const [items,setItems]=useState([])
    const [cur,setCur]=useState(null)

    useEffect(()=>{
        post("/p/article/list",{size:100},(d)=>{
            setItems(d.data)
            setCur(d.data[0].id)
        })
    },[])

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

            <APaper id={cur} onShow={()=>props.setShowArticles(true)}/>
        </div>
    )
}