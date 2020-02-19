import React, {Component} from 'react'
import {withRouter} from "react-router-dom";
import {connect} from "react-redux";
import {reset} from "../../action/cat";
import {Welcome,Kaptcha} from "./login";
import {JPostData,post} from "../common/util";

const  mailReg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
const confirmStage={
    [0]:'可发',
    [1]:'发送中',
    [2]:'发送完成',
    [3]:'计时中',
    could:(i)=>i===0
}
class Reset extends Component{
    state={
        name:'',
        email:'',
        pwd:'',
        kaptcha:'',
        pwd2:'',
        confirm:'',
        count:-1,
        msg:'',
        stage:0,
        confirmMsg:''
    }
    componentWillReceiveProps(nextProps, nextContext) {
        this.setState({
            msg: nextProps.ld.msg
        })
    }
    sendConfirm=()=>{
        if(!confirmStage.could(this.state.stage)){
            return;
        }
        if(!this.validateEmail()){
            return;
        }
        if(this.state.count>-1){
            return;
        }
        this.setState({stage:1,confirmMsg:''})
        post(new JPostData("/confirm",
            {email:this.state.email},
            (d)=>{
                this.setState({stage:2})

                this.setState({
                        confirmMsg:d.msg,
                        count:60,
                        stage:2
                    },()=>{
                        let ir=setInterval(()=>{
                            if(this.state.count>-1) {
                                this.setState({count: this.state.count - 1,stage:3})
                            }else{
                                clearInterval(ir);
                                this.setState({stage:0})
                            }
                        },1000)}
                )

            }))

        this.setState({count:60},()=>{
            let ir=setInterval(()=>{
                if(this.state.count>-1) {
                    this.setState({count: this.state.count - 1})
                }else{
                    clearInterval(ir);
                }
            },1000)
        })
    }

    change = (e, w) => {
        this.setState({
            [w]: e.target.value,
            msg: ''
        })
    }
    tip=(m)=>this.setState({msg:m})
    validateEmail=()=>{
        if(this.state.email==null
            ||this.state.email.length===0
            ||!mailReg.test(this.state.email)){
            this.tip("请输入合法的邮件地址")
            return false
        }
        return true
    }

    validate=()=>{
        if(!this.validateEmail()){
            return false;
        }
        if(this.state.pwd==null||this.state.pwd.length<8){
            this.tip("密码至少8位")
            return false;
        }
        if(this.state.pwd!==this.state.pwd2){
            this.tip("2次输入密码不一致");
            return false;
        }
        if(this.state.confirm==null||this.state.confirm.length<4){
            this.tip("激活码至少4位")
            return false;
        }
        if(this.state.kaptcha==null||this.state.kaptcha.length<4){
            console.log(this.state.kaptcha)
            this.tip("验证码错误")
            return false;
        }
        return true;
    }
    save=()=>{
        console.log("save",1)
        if(!this.validate()) return;
        console.log("save",2)
        let d= {
            url:"/reset",
            data: {
                name: this.state.name,
                pwd: this.state.pwd,
                kapcha: this.state.kaptcha,
                email: this.state.email,
                confirm: this.state.confirm
            }
        }
        this.props.reset(d);
    }
    render() {
        let ct=null;
        if(this.state.count>-1){
            ct=(
                <div className={"m-1"}>
                    <span>{this.state.confirmMsg}</span>
                    <span style={{color:'blue'}}> {this.state.count}</span>
                    <span>秒后可重发</span>
                </div>
            )
        }else{
            ct=(
                <button className={"btn btn-link "} onClick={this.sendConfirm}><span>发送激活码</span></button>
            )
        }
        return (
            <Welcome tip={"修改密码"}>
                <div className={"form-row mt-3"}>
                    <div className={"col-2"}>
                        <label>电子邮箱</label>
                    </div>

                    <div className={"col-6"}>
                        <input className={"form-control"} onChange={(e) => this.change(e, 'email')}/>
                    </div>
                    <div className={"col-4"}>
                        {ct}
                    </div>

                </div>
                <div className={"form-row mt-3"}>
                    <div className={"col-2"}>
                        <label>激活码</label>
                    </div>

                    <div className={"col-6"}>
                        <input className={"form-control"} onChange={(e) => this.change(e, 'confirm')}/>
                    </div>
                </div>
                <div className={"form-row mt-3"}>
                    <div className={"col-2"}>
                        <label>输入新密码</label>
                    </div>

                    <div className={"col-6"}>
                        <input type={"password"} className={"form-control"} onChange={(e) => this.change(e, 'pwd')}/>
                    </div>
                </div>
                <div className={"form-row mt-3"}>
                    <div className={"col-2"}>
                        <label>再次输入密码</label>
                    </div>

                    <div className={"col-6"}>
                        <input type={"password"} className={"form-control"} onChange={(e) => this.change(e, 'pwd2')}/>
                    </div>
                </div>
                <div className={"form-row mt-3"}>
                    <div className={"col-2"}>
                        <label>验证码</label>
                    </div>

                    <div className={"col-4"}>
                        <input className={"form-control"} onChange={(e) => this.change(e, 'kaptcha')}/>
                    </div>
                    <div className={"col ml-6"}>
                        <Kaptcha />
                    </div>

                </div>
                <div className={"form-row mt-3"}>
                    <div className={"col-2"}></div>
                    <div className={"col-6 col-offset-4"} style={{color:'red'}}>
                        {this.state.msg}
                    </div>
                </div>
                <div className={"form-row mt-3"}>
                    <div className={"col-2"}></div>
                    <div className={"col-6 col-offset-4"}>
                        <button className={"btn btn-outline-info ml-3"} onClick={this.save}>保存</button>
                    </div>
                </div>
            </Welcome>
        )
    }
}

const mapStateToProps=(state,ownProps)=>{
    const ld=state.login;
    return {ld}
}

export default withRouter(connect(mapStateToProps,{reset})(Reset))
