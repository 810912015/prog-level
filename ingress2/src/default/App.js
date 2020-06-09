import React, { Component } from 'react';
import {Layout,Row,Col,Carousel,Typography } from 'antd';
import './App.css';
import {Footer} from "../footer";
import {Tb} from "./title-bar-default"
import {HashRouter} from "react-router-dom";
import {Summary} from "./summary";

class App extends Component {
    render() {
    return (
         <HashRouter>
            <Layout className={"layout"}>
                    <Layout.Header style={{backgroundColor:"#eee"}}>
                        <Tb/>
                    </Layout.Header>
                    <Layout.Content style={{minHeight:"600px"}}>
                        <Row>
                            <Col  xs={{span:22,offset:1}} sm={{span:22,offset:1}} md={{span: 14, offset: 5}}>
                                <Intro/>
                            </Col>
                        </Row>

                    </Layout.Content>
                <Footer/>
            </Layout>
         </HashRouter>
    );
  }
}

export function Intro() {
   return (
       <>
           <Carousel afterChange={console.log} autoplay>
               <div>
                   <a href={"/index.html"}>
                       <img  className={"carousel-img"}  src={"https://progprac.obs.cn-south-1.myhuaweicloud.com:443/dog.jpg"}/>
                   </a>
               </div>
               <div>
                   <a href={"index.html#/papers"}>
                       <img className={"carousel-img"} src={"https://progprac.obs.cn-south-1.myhuaweicloud.com:443/code-search19.png"}/>

                   </a>

               </div>
               <div>
                   <a href={"index.html#/263"}>
                       <img  className={"carousel-img"}  src={"https://progprac.obs.cn-south-1.myhuaweicloud.com:443/270-3.png"}/>
                   </a>

               </div>
               <div>
                   <a href={"detail.html#/papers/259"}>
                       <img  className={"carousel-img"}  src={"https://progprac.obs.cn-south-1.myhuaweicloud.com:443/network.jpg"}/>
                   </a>

               </div>
           </Carousel>
            <br/>
           <div style={{letterSpacing:"2px",lineHeight:"2em"}}>
           <h2>
               致力于编程入门和编程技术的传播
           </h2>
               <Summary/>
           <h3>
               主要包括文章和习题二部分：
           </h3>

           <h3>
               <a href={"/index.html#papers"}>文章</a>
               部分提供新发表的英文文档的中文版阅读，中英对照阅读等功能。同时提供自动翻译服务。自有微信公众号“中英对照的IT文档”，扫描可关注。
           </h3>

           <h3>
               <a href={"/index.html"}>习题</a>部分提供在线写代码，编译运行的功能，并记录编译运行结果。对于习题，包含习题的解答讨论，出题等功能。同时面向企业，提供编程水平的评级。
           </h3>

           <div>
               <p>请扫描或搜索以关注<a href={"/detail.html#/papers/259"}>本网站微信公众号</a></p>
               <Qs/>
           </div>
           <br/>
           <div>
               本网站由成熟团队开发维护，承接软件开发任务,保证质量和时间。欢迎垂询：电话15921462689，邮件810912015@qq.com
           </div>
           </div>
           </>
   )
}



export function Qr() {
return (
    <a href={"/index.html#/papers"}>
    <img src={"https://progprac.obs.cn-south-1.myhuaweicloud.com:443/qr.jpg"} className={"qr"} alt={"扫描关注"}/>
    </a>
)
}

export function Qs() {
    return (
        <a href={"/index.html#/papers"}>
            <img src={"https://progprac.obs.cn-south-1.myhuaweicloud.com:443/code-search19.png"} className={"qr"} alt={"扫描关注"}/>
        </a>
    )
}

export default App;
