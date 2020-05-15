import React, { Component } from 'react';
import { Layout, Affix} from 'antd';
import './App.css';
import {HashRouter} from "react-router-dom";

import {QuestionContext} from "../component/context";
import {TitleBar2} from "./parts/title-bar2";
import {authHeader,busy} from "../component/common/network";
import {Bi} from "../component/common/busy-indicator"

import {Pannel} from "./parts/Pannel";

class App extends Component {
    state={
        list:[],
        setList:(d)=>this.setState({list:d}),
        loginName:authHeader.getName(),
        setLoginName:(n)=>{
            authHeader.setName(n)
            this.setState({loginName:n})
        },
        busy:false,
        setBusy:(b)=>{
            this.setState({busy:b})
        },
        height:0
    }

     resize=()=>{
        this.setState({height:window.innerHeight-50})
     }
     componentDidMount() {
         busy.register(this.state.setBusy)
        this.setState({height:window.innerHeight-50})
        window.addEventListener("resize",this.resize)
     }
     componentWillUnmount() {
        window.removeEventListener("resize",this.resize)
     }

    render() {
        let h=this.state.height+"px"
    return (
        <QuestionContext.Provider value={this.state}>
            <Layout className={"layout"}>
                <HashRouter>
                    <Affix offsetTop={0}>
                        <Layout.Header style={{backgroundColor:"#eee"}}>
                            <TitleBar2/>
                        </Layout.Header>
                        <Bi/>
                    </Affix>
                    <Layout.Content style={{minHeight:h}}>
                        <Pannel/>
                    </Layout.Content>
                </HashRouter>
                <Layout.Footer style={{padding:0}}>
                    <div style={{textAlign:"center",backgroundColor:"#888",color:"#eee",padding:"2px"}}>
                        <div className={"space"}>分码网 编程入门社区 联系请邮件810912015@qq.com</div>
                        <div className={"space"}>
                            <a href={"http://www.beian.miit.gov.cn/"}>沪ICP备20013508号-1</a>
                            </div>
                    </div>
                </Layout.Footer>
            </Layout>
        </QuestionContext.Provider>
    );
  }
}

export default App;