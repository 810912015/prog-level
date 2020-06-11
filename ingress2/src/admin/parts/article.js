import React,{useState,useEffect} from 'react'
import {get, post} from "../../component/common/network";
import {Row, Col, Button} from 'antd'
import RawHtml from "react-raw-html"
import {Uploader} from "./uploader";

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
    const [thumbnail,setThumbnail]=useState("")
    const [brief,setBrief]=useState("")
    const [line,setLine]=useState(-1)
    const [h,setH]=useState("")
    useEffect(()=>{
        setData(props.cText)
        setBrief(props.brief||"")
        setThumbnail(props.thumbnail||"")
    },[props])
    const del=()=>{
        post("/admin/article/del?id="+props.id,{},(d)=>{
            props.onDel(props.id);
        })
    }
    const save=()=>{
        post("/admin/article/save",{
            id:props.id,
            cText:data,
            useLevel:line,
            thumbnail:thumbnail,
            brief:brief
        },(d)=>{})
    }
    if(!props.id){
        return (
            <div style={{display:"flex",alignItems:"center",justifyContent:"center",paddingTop:"230px"}}>
                <h2>请选择要编辑的文章</h2>
            </div>
        )
    }
    let wh=((window.innerHeight-255))+"px";
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
           <div style={{padding:"8px 0px"}}>
               <h5>说明：1.奇数行是英文，偶数行是中文；2.pre标签会分别包含在中英文中；3.pre内可包含img；4.前3行（6行，中英文各3行）分别是标题，作者，时间</h5>
               <Row style={{padding:"10px",border:"1px dashed #ddd"}}>
                   <Col span={1} style={{textAlign:"right"}}>
                       <span>标志图</span>
                   </Col>
                   <Col span={8} style={{border:"1px dashed #ddd",paddingLeft:"10px"}}>
                       <input style={{width:"100%"}} type={'text'} value={thumbnail} onChange={(e)=>setThumbnail(e.target.value)}/>
                       <Uploader id={"tn"} url={thumbnail} change={setThumbnail} title={"图片"}/>
                   </Col>
                   <Col span={1} style={{textAlign:"right"}}>
                       <span>简介</span>
                   </Col>
                   <Col span={14}>
                       <textarea value={brief} style={{width:"100%",height:"100px"}} onChange={(e)=>setBrief(e.target.value)}/></Col>


               </Row>
           </div>
           <div>
               <Row style={{height:wh, borderBottom:"1px solid #f50"}}>
                   <Col span={12} style={{height:wh,overflowY:"auto",border:"1px solid #f50"}}>
                       <RawHtml.div>{h}</RawHtml.div>
                   </Col>
                   <Col span={12} style={{height:wh,overflowY:"auto"}}>
                     <textarea value={data} style={{width:"100%",height:"100%",border:"1px solid #f50"}} className={"no-scrollbar"}
                               onChange={(e)=>setData(e.target.value)}
                     />
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
                <div style={{paddingRight:"10px"}}>
                    <ArticleList items={list} click={setId}/>
                </div>

            </Col>
            <Col span={20}>
                <ArticleDetail {...cur} onDel={onDel}/>
            </Col>
        </Row>
    )
}
