import React, { Component ,useEffect,useState} from 'react';
import {Button, Col, Layout, Row,Affix} from 'antd';
import './App.css';
import {QuestionBag, RecommendBag, TagBag} from "./component/question";
import {HashRouter, Route,withRouter,Link} from "react-router-dom";
import {Ing} from "./component/index-main";
import {QuestionContext,questionStore} from "./component/context";
import {Take} from "./component/take";

class App extends Component {
    state={
        list:[],
        setList:(d)=>this.setState({list:d}),
        height:0
    }
     resize=()=>{
        this.setState({height:window.innerHeight-50})
     }
     componentDidMount() {
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
            <Affix offsetTop={0}>
            <Layout.Header style={{backgroundColor:"#eee"}}>

                <div style={{display:"flex"}}>
                    <div  className={"main-brand"}>
                        <a href={"#"}> 分码网</a>

                    </div>
                    <div className={"brand"}>
                        一个你可以写代码的地方
                    </div>
                    <div className={"shortcut"}>
                        <span>登录/注册</span>
                    </div>
                </div>

            </Layout.Header>
            </Affix>
            <Layout.Content style={{minHeight:h}}>
                <HashRouter>
                    <Ing/>
                </HashRouter>
            </Layout.Content>
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