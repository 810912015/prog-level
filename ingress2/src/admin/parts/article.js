import React,{useState,useEffect} from 'react'
import {get, post} from "../../component/common/network";
import {Row, Col, Button} from 'antd'
import RawHtml from "react-raw-html"

export function AArticle(props) {
    return (
        <div style={{padding:"5px"}}>
            <a onClick={()=>props.click(props.id)}>
                {props.id}
                <span style={{marginLeft:"10px",wordBreak:"break-all"}}>
                     {props.cName||props.name}
                </span>

            </a>
        </div>
    )
}

export function ArticleList(props) {
    let r=[]
    if(props.items){
        r=props.items.map(a=><AArticle {...a} click={props.click} key={a.id}/>)
    }
    return (
        <div>{r}</div>
    )
}

export function ArticleDetail(props) {
    const [data,setData]=useState("")
    const [line,setLine]=useState(-1)
    const [h,setH]=useState("")
    useEffect(()=>{
        setData(props.cText)
    },[props.id,props.cText])
    useEffect(()=>{
        setH(props.html)
    },[props.id,props.html])
    useEffect(()=>{
        setLine(props.useLevel||-1)
    },[props.id,props.useLevel])
    const del=()=>{
        post("/admin/article/del?id="+props.id,{},(d)=>{
            props.onDel(props.id);
        })
    }
    const save=()=>{
        post("/admin/article/save",{
            id:props.id,
            cText:data,
            useLevel:line
        },(d)=>{})
    }
    let wh=((window.innerHeight-120)/2)+"px";
   return (
       <div>
           <div style={{padding:"10px 0px"}}>
               <span style={{paddingRight:"20px"}}>{props.id} {props.cName||props.name}</span>
               <a  target={"_new"} href={props.sourceUrl}>
                   原文{props.name}
               </a>
               <span>
                   <Button type={"link"} onClick={()=>{
                       setLine(line*(-1))
                   }}>{line>0?"下线":"上线"}</Button>
                   <Button type={"link"} onClick={save}>保存</Button>
                   <Button type={"link"} onClick={del}>删除</Button>
                   <a href={"/index.html#/papers/"+props.id}>预览</a>
               </span>

           </div>
           <div>
               <Row style={{height:wh,overflowY:"auto",borderBottom:"1px solid #f50"}}>
                   <Col span={12} style={{height:wh,overflowY:"auto"}}>
                       <RawHtml.div>{h}</RawHtml.div>
                   </Col>
                   <Col span={12} style={{height:wh,overflowY:"auto"}}>
                       <pre>{h}</pre>
                   </Col>
               </Row>
               <Row  style={{height:wh,overflowY:"auto"}}>
                   <Col span={12}>
                   <textarea value={data} style={{width:"100%",height:"100%"}} className={"no-scrollbar"}
                             onChange={(e)=>setData(e.target.value)}
                   />
                   </Col>
                   <Col span={12}>
                   <pre style={{paddingLeft:"5px"}}>
                       <RawHtml.div>{data}</RawHtml.div>
                   </pre>
                   </Col>
               </Row>
           </div>
       </div>
   )
}

export function Article(props) {
    const [list,setList]=useState([])
    const [cur,setCur]=useState({})
    const [id,setId]=useState(props.match.params.id||0)
    useEffect(()=>{
        post("/admin/article/list",{},(d)=>{
            setList(d.data)
        })
    },[])
    useEffect(()=>{
        if(!id) return;
        get("/admin/article/detail?id="+id,(d)=>{
            setCur(d.data)
        })
    },[id])
    const onDel=(id)=>{
        let r=[]
        list.forEach((a)=>{
            if(a.id!=id){
                r.push(a)
            }
        })
        setList(r)
    }
    return (
        <Row>
            <Col span={4}>
                <ArticleList items={list} click={setId}/>
            </Col>
            <Col span={20}>
                <ArticleDetail {...cur} onDel={onDel}/>
            </Col>
        </Row>
    )
}