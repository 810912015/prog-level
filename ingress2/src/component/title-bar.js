import React from 'react'
import {Button,message} from "antd";
import {Link} from "react-router-dom";
import {QuestionContext} from "./context";
import {LogoutOutlined,LoginOutlined,RetweetOutlined} from "@ant-design/icons";
import {post} from "./common/network";


function Tb(props) {
    const logout=()=>{
       post("/auth/logout",{},(d)=>{
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
               <a href={"#"}> 分码网</a>

           </div>
           <div className={"brand"}>
               一个你可以写代码的地方
           </div>
           <div className={"shortcut"}>
               <Link to={"/papers"} style={{paddingRight:"10px"}} onClick={()=>{props.setShowArticles(!props.showArticles)}}>文章</Link>
              {c}
           </div>
       </div>

   )
}

export function TitleBar() {
   return (
       <QuestionContext.Consumer>
           {
               (c)=><Tb {...c}/>
           }
       </QuestionContext.Consumer>
   )
}
