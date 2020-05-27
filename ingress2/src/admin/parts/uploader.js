import React,{useEffect,useState} from 'react';
import Dropzone from "react-dropzone";
import PropTypes from 'prop-types'
import {upload} from "../../component/common/network"

/*
上传器:上传一个资源(使用类别+id标志),返回一个资源链接
 */
export function Uploader(props) {
    const [msg,setMsg]=useState("")
    const [url,setUrl]=useState(props.url||"")
    useEffect(()=>{
        if(props.url&&props.url!==url){
            setUrl(props.url)
        }
    })
    const afterPost=(d1) => {
        if(d1.code==="1"){
            setUrl(d1.url)
            props.change(d1.url)
        }else{
            setMsg(d1.msg)
        }
    };
    const dropImage=(fs)=>{
        upload("/admin/upload/files",fs[0],afterPost,console.log)
    }

    let content=(
        <>
            <div >{"选择或拖动"+(props.title||"")+"到这里"}</div>
            <div>{msg}</div>
        </>
    )

    if(!props.notImg&&url){
        content=(
            <img src={url} style={{width:"100px"}}/>
        )
    }else if(props.notImg&&url){
        content=(
            <>{url}
            </>
        )
    }
    return (
        <Dropzone multiple={false} accept={props.accept||""} onDrop={dropImage}>
            {({getRootProps, getInputProps}) => (
                <div>
                    <div {...getRootProps()} className={"btn btn-outline-info"}>
                        <input {...getInputProps()} />
                        {content}
                    </div>
                </div>
            )}
        </Dropzone>
    );
}
Uploader.propTypes={
    url:PropTypes.string,
    id:PropTypes.any.isRequired,
    onUpload:PropTypes.func,
    change:PropTypes.func.isRequired,
    title:PropTypes.string.isRequired,
    notImg:PropTypes.bool
}

export function UploaderExample() {
   return (
       <Uploader id={"123"} change={(e)=>{
       console.log(e)
       }} title={"img"}/>
   )
}