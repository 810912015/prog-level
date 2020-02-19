import React, {Component} from 'react'
import {Link, withRouter} from "react-router-dom";
import {connect} from "react-redux";
import {register} from "../../action/cat";
import {Welcome,Kaptcha} from "./login";
import {JPostData,post} from "../common/util";
import {Tabs,Tab} from "react-bootstrap";

export const  mailReg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
const confirmStage={
    [0]:'可发',
    [1]:'发送中',
    [2]:'发送完成',
    [3]:'计时中',
    could:(i)=>i===0
}
class SimpleRegister extends Component{
    state={
        name:'',
        pwd:'',
        pwd2:'',
        email:'simple'
    }
    change = (e, w) => {
        this.setState({
            [w]: e.target.value,
            msg: ''
        })
    }
    tip=(m)=>this.setState({msg:m})
    validate=()=>{
        if(!this.state.name ||( !!this.state.name&&this.state.name.length>20)){
            this.tip("昵称不能为空,不能超过20位");
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
        return true;
    }
    save=()=>{
        console.log("fuck");
        if(!this.validate()) return;
        console.log("fuck2");
        post({
            url:"/register",
            data: {
                name: this.state.name,
                pwd: this.state.pwd,
                kapcha: '',
                email: this.state.email,
                confirm: ''
            },
            func:(dd)=>{
                if(!dd.success){
                    this.tip(dd.msg)
                }else{
                    window.history.go(-1);
                }
            }
        })
    }
    render() {
        return (
            <div>
                <div className={"form-row mt-3"}>
                    <div className={"col-2"}>
                        <label>用户名</label>
                    </div>

                    <div className={"col-6"}>
                        <input className={"form-control"} onChange={(e) => this.change(e, 'name')}/>
                    </div>
                </div>
                <div className={"form-row mt-3"}>
                    <div className={"col-2"}>
                        <label>输入密码</label>
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
                    <div className={"col-2"}></div>
                    <div className={"col-6 col-offset-4"} style={{color:'red'}}>
                        {this.state.msg}
                    </div>
                </div>
                <div className={"form-row mt-3"}>
                    <div className={"col-2"}></div>
                    <div className={"col-6 col-offset-4"}>
                        <button className={"btn btn-outline-info ml-3"} onClick={this.save}>注册</button>
                        <Link to={"/login"} className={"btn btn-link ml-3"}>登录</Link>
                        <Link to={"/reset"} className={"btn btn-link ml-3"}>忘记密码?</Link>
                    </div>
                </div>
            </div>
        )
    }
}

class EmailRegister extends Component{
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
                       },1000)
                   }
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
        if(!!this.state.name&&this.state.name.length>20){
            this.tip("昵称不能超过20位");
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
            this.tip("验证码错误")
            return false;
        }
        return true;
    }
    save=()=>{

       if(!this.validate()) return;
       this.tip('')

       post({
           url:"/register",
           data: {
               name: this.state.name,
               pwd: this.state.pwd,
               kapcha: this.state.kaptcha,
               email: this.state.email,
               confirm: this.state.confirm
           },
           func:(dd)=>{
               if(!dd.success){
                   this.tip(dd.msg)
               }else{
                   window.history.go(-1);
               }
           }
       })

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
            <>
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
                        <label>输入密码</label>
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
                        <label>昵称</label>
                    </div>

                    <div className={"col-6"}>
                        <input className={"form-control"} onChange={(e) => this.change(e, 'name')}/>
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
                        <button className={"btn btn-outline-info ml-3"} onClick={this.save}>注册</button>
                        <Link to={"/login"} className={"btn btn-link ml-3"}>登录</Link>
                        <Link to={"/reset"} className={"btn btn-link ml-3"}>忘记密码?</Link>
                    </div>
                </div>
            </>
        )
    }
}

class Register extends Component{
    render() {
        return (
            <Welcome tip={"请输入注册信息"}>
                <Tabs id={"registerTabs"} defaultActiveKey={"email"}>
                    <Tab title={"邮件注册"} eventKey={"email"}>
                        <EmailRegister/>
                    </Tab>
                    <Tab title={"用户名注册"} eventKey={"userName"}>
                        <SimpleRegister/>
                    </Tab>
                </Tabs>


            </Welcome>
        )
    }
}

const mapStateToProps=(state,ownProps)=>{
    const ld=state.login;
    return {ld}
}

export default withRouter(connect(mapStateToProps,{register})(Register))
