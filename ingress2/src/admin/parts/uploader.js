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
       setUrl(props.url)
    },[props.url])
    const afterPost=(d1) => {
        if(d1.data){
            setUrl(d1.data)
            props.change(d1.data)
        }else{
            setMsg(d1.message)
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
    const [url,setUrl]=useState(null)
   return (
       <div>
           <Uploader id={"123"} change={setUrl} title={"文件"}/>
           <div>{url}</div>
       </div>

   )
}
