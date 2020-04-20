import React, { Component ,useEffect,useState} from 'react';
import {Button, Col, Layout, Row,Affix} from 'antd';
import './App.css';
import {QuestionBag, RecommendBag, TagBag} from "./component/question";
import {HashRouter, Route,withRouter,Link} from "react-router-dom";
import {Ing} from "./component/index-main";
import {QuestionContext,questionStore} from "./component/context";
import {Take} from "./component/take";
import {TitleBar} from "./component/title-bar";

class App extends Component {
    state={
        list:[],
        setList:(d)=>this.setState({list:d}),
        loginName:null,
        setLoginName:(n)=>this.setState({loginName:n}),
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
                <HashRouter>
                    <Affix offsetTop={0}>
                        <Layout.Header style={{backgroundColor:"#eee"}}>
                            <TitleBar/>
                        </Layout.Header>
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