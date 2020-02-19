import Pass from "./pass";
import {InviteHeader} from "../nav/header";
import React from 'react';
import BusyIndicator from '../../common/busyIndicator'
import {post,JPostData} from "../../common/util";
import {Link} from 'react-router-dom'

export class Passes extends React.Component{
    state={
        ps:window.cur,
        cur:0,
        msg: window.msg,
        eiid:window.eiid
    }
    done=()=>{
        if(this.state.cur<this.state.ps.length){
            this.setState({cur:this.state.cur+1})
        }
    }
    move=(step)=>{
        let c=this.state.cur+step;
        if(c<0||c>=this.state.ps.length){
            return;
        }
        this.setState({cur:c})
    }
    render() {
        let t=null;
        if(this.state.msg&&this.state.msg.length>0){
            t=(
                <div className={"container"}>
                    <h2>{this.state.msg}</h2>
                </div>
            )
        }else if(this.state.cur===this.state.ps.length){
            t=(
                <div className={"container"}>
                    <h2>题目已做完!</h2>
                </div>
            )
        }else{
            t=(
                <div className={"container"}>
                    <h4>
                        共{this.state.ps.length}题,当前是第{this.state.cur+1}题
                        <button className={"btn btn-link"} onClick={()=>this.move(-1)}>上一题</button>
                        <button className={"btn btn-link"} onClick={()=>this.move(1)}>下一题</button>
                    </h4>
                    <Pass eiid={window.eiid} id={this.state.ps[this.state.cur]} done={this.done}/>
                </div>
            )
        }
        return (
            <div>
                <InviteHeader/>
                <BusyIndicator/>
                {t}
            </div>
        )
    }

}

export class Passes2 extends React.Component{
    state={
        eid:this.props.match.params.eid,
        ps:[],
        cur:0,
        msg: ''
    }
    componentWillMount() {
        post(new JPostData("/getbyeid/"+this.state.eid,{},(d)=>{
            if(d&&d.length>0){
                this.setState({
                    ps:d,
                    msg:''
                })
            }else{
                this.setState({msg:'未找到题目'})
            }
        }))
    }

    done=()=>{
        if(this.state.cur<this.state.ps.length){
            this.setState({cur:this.state.cur+1})
        }
    }
    render() {
        let t=null;
        if(this.state.msg&&this.state.msg.length>0){
            t=(
                <div>
                    <h2>{this.state.msg}</h2>
                </div>
            )
        }else if(this.state.cur===this.state.ps.length){
            t=(
                <div>
                    <h2>题目已做完!</h2>
                    <Link to={"/score"}>查看成绩</Link>
                </div>
            )
        }else{
            t=(
                <div >
                    <h4>
                        共{this.state.ps.length}题,当前是第{this.state.cur+1}题
                    </h4>
                    <Pass id={this.state.ps[this.state.cur]} done={this.done}/>
                </div>
            )
        }
        return (
            <div className={"container"}>
                <div>{this.props.match.params.ename}</div>
                {t}
            </div>
        )
    }

}

