import React, {useEffect, useState} from 'react'
import {get, post} from "../common/network";
import {Drawer,Button} from "antd";
import {BarsOutlined} from "@ant-design/icons";
import {QuestionContext} from "../context";
import RawHtml from "react-raw-html"

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
export function PaperBrief(props) {
    let url=props.thumbnail||"https://progprac.obs.cn-south-1.myhuaweicloud.com:443/268-3.jpg";
    return (
        <div style={{display:'flex',boxShadow:"0px 0px  5px #aaa",cursor:"pointer",padding:"10px"}}>
            <div style={{width:"100px"}}>
                <img src={url} style={{width:"100px",height:"100%"}}/>
            </div>
            <div style={{flex:1,marginLeft:"10px"}}>
                <b>{props.cName}</b>
                <div className={"line-el-2"}>
                    <i>{props.auth}</i><i style={{paddingLeft:"10px"}}>{props.pubTime}</i>
                </div>
                <div className={"line-el-2"}>{props.brief}</div>

            </div>
        </div>
    )
}
export function PaperList2(props) {
   
    let r=[]
    if(props.items){
        props.items.forEach(a=>{
            r.push(
                <div key={a.id} style={{paddingTop:"5px"}} onClick={()=>props.click(a.id)}>
                   <PaperBrief {...a}/>
                </div>
            )
        })
    }
    let t=null;
    if(props.tip.indexOf("更多")>-1){
        t=(
            <Button type="link" onClick={props.more}>{props.tip}</Button>
        )
    }else{
        t=(
            <span>{props.tip}</span>
        )
    }
    return (
        <div className={"vertical-center-box"}>
            <div className={"vertical-center"}>
                <div>
                    {r}
                </div>
                <div style={{padding:"10px",marginLeft:"100px"}}>
                    {t}
                </div>
            </div>

        </div>
    )
}

export function APaper(props) {
    const [item,setItem]=useState(null)
    const [version,setVersion]=useState("c")


    useEffect(()=>{
        //if(!props.id) return;
        get("/p/article/detail?id="+props.id+"&version="+version,(d)=>{
            setItem(d.data)
            if(typeof props.idChange==='function'){
                props.idChange(d.data.id)
            }
        })
    },[props.id,version])
    let r;
    if(item) {
        r=<RawHtml.div>{item.cHtml}</RawHtml.div>
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
    const [listTip,setListTip]=useState("更多")

    useEffect(()=>{
        getList()
    },[])
    const getList=()=>{
        let b={size:20}
        if(items&&items.length){
            let min=Number.MAX_SAFE_INTEGER;
            items.forEach(a=>{
                if(a.id<min){
                    min=a.id
                }
            })
            b.minId=min;
        }
        post("/p/article/list",b,(d)=>{
             if(d.data&&d.data.length){
                 let d1=[]
                 items.forEach(a=>d1.push(a))
                 d.data.forEach(a=>d1.push(a))
                 setItems(d1)
                 if(d.data.length<20){
                     setListTip("真没有了")
                 }
             }else{
                 setListTip("真没有了")
             }
        })
    }
    const listClick=(id)=>{
        props.setShowArticles(false)
        setCur(id)
        window.location.href=window.location.origin+"/index.html#/papers/"+id;
    }
    let c;
    if(!cur){
        c=(
            <PaperList2 st={{padding:"10px"}} items={items} click={listClick} more={getList} tip={listTip}/>
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
                <PaperList items={items} click={listClick}/>
            </Drawer>
            <a href={"/detail.html#/"+cur}>
                <img src={"https://progprac.obs.cn-south-1.myhuaweicloud.com:443/code-search19.png"} className={"qr"} alt={"扫描关注"}/>
            </a>
            {c}
        </div>
    )
}
