import React,{useState,useEffect} from "react";
import {Row,Col,Affix,Button} from "antd"
import {post} from "./common/network";
import ReactHtmlParser from 'react-html-parser'
import {MultiLangEditor} from "./common/CoderEditor";
import {Link} from "react-router-dom";

export function getSourceObjFromArray(list) {
    let so={}
    for(let i=0;i<list.length;i++){
        let t=list[i]
        so[t.lang]=t.source
    }
    return so;
}

export function Take(props) {
    const [item,setItem]=useState({id:props.match.params.id||props.id||0})
    const [so,setSo]=useState({})
    const [cur,setCur]=useState({})
    const [lw,setLw]=useState({})
    const [qid,setQid]=useState(parseInt(props.match.params.id)||props.id||0)
    useEffect(()=>{
        let id=qid;
        post("/question/get/"+id,{},
            (d)=>{
            if(!lw.w) {
                setLw({w: window.innerWidth / 2})
            }
            window.addEventListener("resize",resize)
                let so=getSourceObjFromArray(d.s);
                setCur({
                    skeleton:d.s[0].source,
                    l:d.s[0].lang
                })
               setItem(d.q)
                setSo(so);
        }
            );
        return ()=>{
            window.removeEventListener("resize",resize)
        }
    },[qid])
    const resize=()=>{
        setLw({w: window.innerWidth / 2})
    }
    const change=(e,l)=>{
        setCur({ skeleton:e,
            lang:l})
    }
    let leftWidth=(lw.w||window.innerWidth/2)+"px"
    let rightWidth=((window.innerWidth-lw.w-10)||window.innerWidth/2-10)+"px"
    if(window.innerWidth<600){
        leftWidth="100%"
        rightWidth="100%"
    }
    const pageOver=(num)=>{
        let fn=qid+num
        setQid(fn);
        props.history.push("/pass/"+fn)
    }
    let height=window.innerHeight -80;
    return (
        <div>
            <div className={"my-row"}>
                <div className={"no-scrollbar"}
                     style={{paddingLeft:"10px",width:leftWidth,height:height,overflowY:"auto"}}>
                    {item.id}
                    <span>{item.name}</span>
                    <div>
                        {ReactHtmlParser(item.title)}
                    </div>
                </div>
                <div className={"my-gutter"}
                     onDrag={(e)=>{
                         let n=Object.assign({},lw)
                         if(n.s===0){
                             setLw({
                                 w:lw.w,
                                 s:e.pageX
                             })
                             return;
                         }
                         let d=0;
                         if(n.s){
                             d=e.pageX-lw.s;
                             if(Math.abs(d)>10) {
                                 if(Math.abs(d)>300){
                                     setLw({
                                         w:lw.w,
                                         s:e.pageX
                                     })
                                     return;
                                 }
                                 if (!n.w) {
                                     n.w = window.innerWidth / 2 - 20
                                 }
                                 n.w-=d*(-1);
                                 n.s=e.pageX;
                             }

                         }else {
                             n.s=e.pageX;
                         }
                         setLw(n)
                         e.preventDefault()

                     }}
                     onDrop={(e)=>{
                         setLw({
                             w:lw.w,
                             s:0
                         })
                         e.preventDefault()
                     }}
                />
                <div className={"no-scrollbar"}
                     style={{width:rightWidth,height:height,overflowY:"auto"}}>
                    <MultiLangEditor sourceList={so}
                                     qid={item.id}
                                     langInSource={true}
                                     curSourceChange={(e,l)=>{change(e,l)}}
                    />
                </div>
            </div>
            <Affix  offsetBottom={0}>
                <div className={"bottom-bar"}>
                    <div style={{flex:1,display:"flex",alignItems:"center",paddingRight:"15px"}}>
                        <div style={{flex:4}}/>
                        <Button onClick={()=>pageOver(-1)} >上一题</Button>
                        <div style={{width:"30px"}}/>
                        <Button onClick={()=>pageOver(1)} >下一题</Button>
                        <div style={{flex:4}}/>
                    </div>

                    <div style={{flex:1,display:"flex",alignItems:"center",paddingLeft:"15px"}}>
                        <div style={{flex:4}}/>
                        <Button>编译运行</Button>
                        <div style={{width:"30px"}}/>
                        <Button>保存</Button>
                        <div style={{flex:4}}/>
                    </div>
                </div>
            </Affix>
        </div>
    )
}