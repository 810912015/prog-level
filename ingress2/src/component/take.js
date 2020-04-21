import React,{useState,useEffect} from "react";
import {Row,Col,Affix,Button,Drawer,message,Tabs} from "antd"
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

export function AResult(props) {
    return (
       <div>
           <div>{props.tip}{props.success?"成功":"失败"}</div>
           <pre>{props.msg}</pre>
       </div>
    )
}

export function Results(props) {
    let r;
    if(props.test&&props.test.length>0){
        r=(
            <>
                <div>执行</div>
                {
                    props.test.map((a,i)=><AResult {...a} tip={i} key={i}/>)
                }
            </>
        )
    }
    let c;
    if(props.compile){
        c=(
            <>
                <AResult {...props.compile} tip={"编译"}/>
                </>
        )
    }
    return (
        <>
            {c}
            {r}
        </>
    )
}

export function PassResultItem(props) {
    return (
        <div style={{}} key={props.id}>
            <div style={{display:"flex",borderBottom:"1px solid #d50"}}>
                <span style={{flex:1}}>{props.remarks==="F"?"编译":"执行"}</span>
                <span style={{flex:1}}>{props.qid}</span>
                <span style={{flex:1}}>{props.lang}</span>
                <span style={{flex:5}}>{props.dclt}</span>
            </div>
            <div >
                <pre className={"no-scrollbar"}>{props.source}</pre>
                <pre  className={"no-scrollbar"}>{props.output}</pre>
            </div>

        </div>
    )
}

export function Prs(props) {
    if(!props.items||props.items.length<1) {
        return null;
    }
    return (
        <>
            {props.items.map((a)=>{
                return (<PassResultItem {...a}/>)
            })}
            </>
    )
}

export function Take(props) {
    const [item,setItem]=useState({id:props.match.params.id||props.id||0})
    const [so,setSo]=useState({})
    const [cur,setCur]=useState({})
    const [lw,setLw]=useState({})
    const [qid,setQid]=useState(parseInt(props.match.params.id)||props.id||0)
    const [cr,setCr]=useState({})
    const [show,setShow]=useState(false)
    const [pass,setPass]=useState([])
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
                    lang:d.s[0].lang,
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
            lang:l,l:l})
        console.log("change",e,l,cur)
    }
    const compile=()=>{
        let payload={...item}

        payload.lang=cur.lang||cur.l;
        payload.source=cur.skeleton
        console.log(cur,so,payload)
        post("/exam/pass",payload,(d)=>{
            console.log(JSON.stringify(d))
            setCr(d)
            setShow(true)
        })
    }
    const save=()=>{
        post("/exam/done/"+cr.gid,{},(d)=>{
            message.info(d.msg)
        })
    }
    const changeTab=(k)=>{
        console.log("change tab",k,k==2)
        if(k==2){
            post("/exam/pass-mine",{qid:qid,size:10},(d)=>{
                setPass(d)
            })
        }
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
    let lb;

    if(cr.compile&&!show){
        lb=(
            <Button type={"link"} onClick={()=>setShow(true)}>最近</Button>
        )
    }
    let sb;
    if(cr.gid){
        sb=(
           <Button type={"link"} onClick={save}>{"保存"}</Button>
       )
    }
    return (
        <div>
            <div className={"my-row"}>
                <div className={"no-scrollbar"}
                     style={{paddingLeft:"10px",width:leftWidth,height:height,overflowY:"auto"}}>
                    <Tabs defaultActiveKey="1" onChange={changeTab}>
                        <Tabs.TabPane tab="题目详情" key="1">
                            {item.id}
                            <span>{item.name}</span>
                            <div>
                                {ReactHtmlParser(item.title)}
                            </div>
                        </Tabs.TabPane>
                        <Tabs.TabPane tab="我的提交" key="2">
                            {<Prs items={pass}/>}
                        </Tabs.TabPane>

                    </Tabs>

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
            <Drawer
                title="运行结果"
                placement={window.innerWidth<800?"bottom":"left"}
                closable={true}
                onClose={()=>{setShow(false)}}
                visible={show}
                mask={false}
                height={400}
                width={window.innerWidth/2-5}
            >
               <Results {...cr}/>
            </Drawer>
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
                        <Button type={"primary"} onClick={compile}>运行</Button>
                        {lb}
                        <div style={{width:"6px"}}/>
                        {sb}
                        <div style={{flex:4}}/>
                    </div>
                </div>
            </Affix>
        </div>
    )
}