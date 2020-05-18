import React, { Component } from 'react';
import {Layout, Affix, Drawer} from 'antd';
import './App.css';
import {HashRouter} from "react-router-dom";
import {QuestionContext} from "../component/context";
import {authHeader, busy, post} from "../component/common/network";
import {APaper, PaperList} from "../component/article/paper";


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
        height:0,
        showArticles:false,
        setShowArticles:(s)=>{
            if(s&&this.state.items.length===0) {
                post("/p/article/list",{size:50},(d)=>{
                    this.setState({items:d.data})
                })
            }

            this.setState({showArticles:s})
        },
        items:[],
        id:(()=>{
            try {
                let s = window.location.search;
                let id = s.substr(4);
                let idi = parseInt(id);
                return idi;
            }catch (e) {
                return 217;
            }
        })()
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
                    <Qr/>
                    <Papers {...this.state} setId={(id)=>{this.setState({id:id})}}/>
                    <APaper id={this.state.id} onShow={()=>{this.state.setShowArticles(true)}}/>
                    <Qr/>
                </HashRouter>
            </Layout>
        </QuestionContext.Provider>
    );
  }
}

export function Papers(props) {
  return (
      <Drawer title="文章列表"
              placement={window.innerWidth<800?"top":"left"}
              closable={true}
              onClose={()=>props.setShowArticles(false)}
              mask={false}
              width={"400px"}
              visible={props.showArticles}>
          <PaperList items={props.items} click={(id)=>{
              props.setShowArticles(false)
              props.setId(id)
          }}/>
      </Drawer>
  )
}

export function Qr() {
return (
    <img src={"code-search.png"} style={{width:"100%",height:"auto"}}/>
)
}

export default App;