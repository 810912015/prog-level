import React,{useState,useEffect} from "react";
import {Row,Col} from "antd"
import {post} from "./common/network";
import ReactHtmlParser from 'react-html-parser'
import {MultiLangEditor} from "./common/CoderEditor";

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
    useEffect(()=>{
        let id=props.match.params.id||props.id||0;
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
    },[])
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
    return (
        <div className={"my-row"}>
            <div style={{paddingLeft:"10px",width:leftWidth}}>
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
            <div style={{width:rightWidth}}>
                <MultiLangEditor sourceList={so}
                                 qid={item.id}
                                 langInSource={true}
                                 curSourceChange={(e,l)=>{change(e,l)}}
                />
            </div>
        </div>
    )
}