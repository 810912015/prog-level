import React from 'react'


 export function Tb(props) {

   return (

       <div style={{display:"flex"}}>
           <div  className={"main-brand"}>
               <a href={"/index.html"}> 分码编程</a>

           </div>
           <div className={"brand"}>
               编程入门和提高社区
           </div>
           <div className={"shortcut"}>
               <a href={"index.html#/papers"}>文章</a>
               <a href={"/index.html"}>习题</a>
           </div>
       </div>

   )
}


