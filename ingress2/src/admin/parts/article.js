import React,{useState,useEffect} from 'react'
import {get, post} from "../../component/common/network";
import {Row,Col} from 'antd'
import ReactHtmlParser from 'react-html-parser'

export function AArticle(props) {
    return (
        <div>
            <a onClick={()=>props.click(props.id)}>{props.name}</a>
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
   return (
       <div>
           <div>{props.id} {props.name}</div>
           <Row>
               <Col span={12}>
                   <pre>{props.text}</pre>
               </Col>
               <Col span={12}>
                   <div>{
                       ReactHtmlParser(props.html)
                   }</div>
               </Col>
           </Row>


       </div>
   )
}

export function Article() {
    const [list,setList]=useState([])
    const [cur,setCur]=useState({})
    useEffect(()=>{
        post("/admin/article/list",{},(d)=>{
            setList(d.data)
        })
    },[])

    return (
        <Row>
            <Col span={4}>
                <ArticleList items={list} click={(id)=>{
                    get("/admin/article/detail?id="+id,(d)=>{
                        setCur(d.data)
                    })
                }}/>
            </Col>
            <Col span={20}>
                <ArticleDetail {...cur}/>
            </Col>
        </Row>
    )
}