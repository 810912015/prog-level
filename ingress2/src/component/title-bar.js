import React from 'react'
import {Layout} from "antd";
import {Link} from "react-router-dom";

export function TitleBar() {
   return (
       <div style={{display:"flex"}}>
           <div  className={"main-brand"}>
               <a href={"#"}> 分码网</a>

           </div>
           <div className={"brand"}>
               一个你可以写代码的地方
           </div>
           <div className={"shortcut"}>
               <Link to={"/login"}>登录</Link>
               <Link to={"/register"} style={{marginLeft:"20px"}}>注册</Link>
           </div>
       </div>
   )
}

