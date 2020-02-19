import React, {Component} from 'react'
import {withRouter,Link} from "react-router-dom";
import {connect} from "react-redux";
import {login} from "../../action/cat";
import {Logo} from "../common/icon";

class Login extends Component {
    state = {
        name: '',
        pwd: '',
        kapcha: '',
        msg: this.props.ld.msg,
        needCap:false
    }
    change = (e, w) => {
        this.setState({
            [w]: e.target.value,
            msg: ''
        })
    }

    componentWillReceiveProps(nextProps, nextContext) {
        let n=nextProps.ld.uid==="-1";
        this.setState({
            msg: nextProps.ld.msg,
            needCap:n
        })
    }

    save = () => {
        let url=this.props.url||'/';
        this.props.login({
            url: '/login',
            ca: {
                name: this.state.name,
                pwd: this.state.pwd,
                kapcha: this.state.kapcha
            }
        },()=>{
            console.log("login",this.props.history)
            //这里判断是否是个人入口,是则跳转.原因:个人入口不需要登录
            if(window.location.href.indexOf("/#/")>0){
                this.props.history.push("/score")
            }
        })
    }
    keyUp=(e)=>{
        if(e.key==='Enter'){
            this.save();
        }
    }

    render() {
        let c=null;
        if(this.state.needCap){
            c=(
                <div className={"form-row mt-3"}>
                    <div className={"col-12 col-sm-1"}>
                        <label>验证码</label>
                    </div>

                    <div className={"col-12 col-sm-4"}>
                        <input className={"form-control"} onChange={(e) => this.change(e, 'kapcha')} onKeyUp={this.keyUp}/>
                    </div>
                    <div className={"col-12 col-sm-4"}>
                        <Kaptcha/>
                    </div>
                </div>
            )
        }

        return (
            <Welcome tip={"欢迎使用分码网"}>
                <div className={"form-row mt-3"}>
                    <div className={"col-12 col-sm-1"}>
                        <label>用户名</label>
                    </div>

                    <div className={"col-12 col-sm-4"}>
                        <input className={"form-control"} onChange={(e) => this.change(e, 'name')} onKeyUp={this.keyUp}/>
                    </div>

                </div>
                <div className={"form-row mt-3"}>
                    <div className={"col-12 col-sm-1"}>
                        <label>密码</label>
                    </div>

                    <div className={"col-12 col-sm-4"}>
                        <input type={"password"} className={"form-control"} onChange={(e) => this.change(e, 'pwd')} onKeyUp={this.keyUp}/>
                    </div>
                </div>
                {c}
                <div className={"form-row m-3"}>
                    <div className={"col-1"}></div>
                    <div>{this.state.msg}</div>
                </div>
                <div className={"form-row mt-3"}>
                    <div className={"col-sm-4 col-12"}>
                        <button className={"btn btn-outline-info"} onClick={this.save}>登录</button>
                    </div>
                    <div className={"col-sm-4 col-12"}>
                        <Link to={"/register"} className={"btn btn-link"}>注册</Link>
                        <Link to={"/reset"} className={"btn btn-link"}>忘记密码?</Link>
                    </div>

                </div>
            </Welcome>
        )
    }
}
export class Kaptcha extends Component{
    state={
        ts: new Date().getTime()
    }
    refresh = () => {
        this.setState({
            ts: new Date().getTime()
        })
    }
    render() {
        let url = "/captcha/" + this.state.ts;
        return (
            <div className={"p-1"}>
            <img src={url} title={"点击刷新"} alt={"验证码"} onClick={this.refresh}/>
            </div>
        )
    }
}

export class Welcome extends Component {
    render() {
        return (
            <div className={"container"}>
                <div className={"card mb-3 border-0"}>
                    <Logo height={"100px"} width={"108px"}/>
                    <div className={"card-body"}>
                        <h5>{this.props.tip}</h5>
                        <div>
                            {this.props.children}
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}


const mapStateToProps = (state, ownProps) => {
    const ld = state.login;
    return {ld}
}

export default withRouter(connect(mapStateToProps, {login})(Login))
