import React from 'react';
import {Provider} from 'react-redux';
import {HashRouter} from 'react-router-dom';
import AdminEntry from "./components/exam/entry/admin-entry";
import "@babel/polyfill";
import 'whatwg-fetch'
import {post} from "./components/common/util";


export class Builder extends React.Component{
    state={
        src:'',
        name:'',
        result:"",
        msg:""
    }
    build=()=>{
        post({
            url:"/tools/jsonbuild",
            data:{
                json:this.state.src,
                name:this.state.name
            },
            func:(dd)=>{
                this.setState({
                    msg:dd.msg,
                    result:dd.data||""
                })
            }
        })
    }
    render() {
        return (
            <div className={"container-fluid"}>
                <div className={"p-3"}>
                    根据json字符串生成java源文件
                </div>
            <div className={"row"}>
                <div className={"col"}>
                    <div className={"m-3"}>要生成的java的类名</div>
                    <input className={"form-control m-3"} value={this.state.name} onChange={(e)=>this.setState({name:e.target.value})}/>
                    <div className={"m-3"}>json字符串</div>
                    <textarea className={"form-control m-3"} style={{width:"100%",height:"700px"}}
                              value={this.state.src} onChange={(e)=>this.setState({src:e.target.value})}>

                    </textarea>

                </div>
                <div className={"col ml-3"}>
                    <div className={"m-3"}>{this.state.msg}</div>
                    <div className={"m-3"}>
                        <button className={"btn btn-primary"} onClick={this.build}>生成java源文件</button>
                    </div>
                    <pre className={"m-3 border p-3"}>
                        {this.state.result}
                    </pre>
                </div>
            </div>
            </div>
        )
    }
}


class ToolsApp extends React.Component{
    render(){
        return (
            <Provider store={this.props.store}>
                <HashRouter>
                    <Builder/>
                </HashRouter>
            </Provider>
        )
    }

}


export default ToolsApp;
