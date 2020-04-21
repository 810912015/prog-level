import React, { Component } from 'react';
import { Layout, Affix} from 'antd';
import './App.css';
import {HashRouter} from "react-router-dom";
import {Ing} from "./component/index-main";
import {QuestionContext} from "./component/context";
import {TitleBar} from "./component/title-bar";
import {authHeader,busy} from "./component/common/network";
import {Bi} from "./component/common/busy-indicator"

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
                            <TitleBar/>
                        </Layout.Header>
                        <Bi/>
                    </Affix>

                    <Layout.Content style={{minHeight:h}}>
                        <Ing/>
                    </Layout.Content>
                </HashRouter>
                <Layout.Footer style={{padding:0}}>
                    <div style={{textAlign:"center",backgroundColor:"#888",color:"#eee",padding:"10px"}}>
                        <div className={"space"}>上海信息科技有限公司</div>
                        <div className={"space"}>沪icp备45862号</div>
                    </div>
                </Layout.Footer>
            </Layout>
        </QuestionContext.Provider>
    );
  }
}

export default App;