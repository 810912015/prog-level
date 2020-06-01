import React from 'react'
import {Button,message} from "antd";
import {Link} from "react-router-dom";

import {QuestionContext} from "../../component/context";
import {LogoutOutlined,LoginOutlined,RetweetOutlined} from "@ant-design/icons";
import {post} from "../../component/common/network";


function Tb(props) {
    const logout=()=>{
       post("/admin/auth/logout",{},(d)=>{
           message.info("已注销")
           props.setLoginName("")
       })
    }
    let c;
    if(props.loginName){
        c= (<>{props.loginName}
        <Button type={"link"} onClick={logout} title={"注销"}><LogoutOutlined /></Button>
        </>)
    }else{
        c=(
            <>
            <Link to={"/login"} title={"登录"}><LoginOutlined/></Link>
            <Link to={"/register"} style={{marginLeft:"20px"}} title={"注册"}><RetweetOutlined/></Link>
                </>
        )
    }
   return (

       <div style={{display:"flex"}}>
           <div  className={"main-brand"}>
               <a href={"/default.html"}> 分码编程-文章编辑</a>

           </div>
           <div className={"brand"}>
               编程练习和文档翻译
           </div>
           <div className={"shortcut"}>
               <Link to={"/"} title={"编辑"} style={{padding:"0px 10px"}}>{"编辑"}</Link>
               <Link to={"/translate"} title={"翻译"} style={{padding:"0px 10px"}}>{"翻译"}</Link>
               <Link to={"/upload"} title={"上传"} style={{padding:"0px 10px"}}>{"上传"}</Link>
              {c}
           </div>
       </div>

   )
}

export function TitleBar2() {
   return (
       <QuestionContext.Consumer>
           {
               (c)=><Tb {...c}/>
           }
       </QuestionContext.Consumer>
   )
}
