import React, { Component } from 'react';
import {Layout, Affix, Drawer} from 'antd';
import './App.css';
import {HashRouter} from "react-router-dom";
import {QuestionContext} from "../component/context";
import {authHeader, busy, post} from "../component/common/network";
import {APaper, PaperList} from "../component/article/paper";
import {Footer} from "../footer";

function get_url_params() {
    var w_t =  window.location.href.split('/');
    var len = w_t.length - 1;
    var w_id = w_t[len];
    return w_id;
}

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
                let id =get_url_params();
                let idi = parseInt(id);
                if(isNaN(idi)) return 0;
                return idi;
            }catch (e) {
                return 0;
            }
        })()
    }
    changeUrl=(nid)=>{
        if(this.state.id!==nid) {
            window.location.href = window.location.origin + "/detail.html#/" + nid;
        }
    }
    render() {
    return (
        <QuestionContext.Provider value={this.state}>
            <Layout className={"layout"}>
                <HashRouter>
                    <Layout.Content>
                    <Qr/>
                    <Papers {...this.state} setId={(id)=>{this.setState({id:id});this.changeUrl(id);}}/>
                    <APaper id={this.state.id} onShow={()=>{this.state.setShowArticles(true)}} idChange={this.changeUrl}/>
                    <Qr/>
                    </Layout.Content>
                </HashRouter>
                <Footer/>
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
    <a href={"/index.html#/papers"}>
    <img src={"code-search.png"} className={"qr"}/>
    </a>
)
}

export default App;