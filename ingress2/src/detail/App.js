import React, { Component } from 'react';
import { Layout, Affix} from 'antd';
import './App.css';
import {HashRouter} from "react-router-dom";
import {QuestionContext} from "../component/context";
import {authHeader,busy} from "../component/common/network";
import {APaper} from "../component/article/paper";


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
                    <APaper id={217}/>
                </HashRouter>
            </Layout>
        </QuestionContext.Provider>
    );
  }
}

export default App;