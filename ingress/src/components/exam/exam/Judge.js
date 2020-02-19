import React from 'react'
import PropTypes from 'prop-types'
import {post, get} from "../../common/util";
import {BsInlineForm, Ctrl} from "../../common/bsInlineForm";

export class JudgeQuestion extends React.Component {
    render() {
        let q = this.props.question;
        if (!q) {
            return (
                <div></div>
            )
        }
        return (
            <div className={"border-bottom"}>
                <div className={"m-3 shadow"}>试题信息</div>
                <div className={"p-1"}>{q.name}</div>
                <div className={"p-1"}>{q.title}</div>
            </div>
        )
    }
}

export class JudgePass extends React.Component {
    state={
        msg:''
    }
    submit = (e) => {
        let t={
            score:e.score,
            reason:e.scoreReason,
            gid:this.props.pass[0].groupId
        }
        post({
            url:"/pass/score/update",
            data:t,
            func:(d)=>{
                this.setState({msg:d.success?"保存成功":"保存失败"})
            }
        })
    }

    render() {
        let p = this.props.pass;
        if (!p || p.length == 0) {
            return (
                <div></div>
            )
        }
        let rl = [];
        p.forEach((a, i) => {
            rl.push(
                <div key={a.id} className={"p-1"}>
                    <span>{i + 1}.</span>
                    <span>{a.success === 'T' ? '正确' : '错误'}</span>
                    <span className={"ml-1"}>{a.msg}</span>
                </div>
            )
        })
        let s = p[0].source

        return (
            <div className={"row"}>
                <div className={"col-sm-6"}>
                    <div className={"m-3 shadow"}>考生源码</div>
                    <pre className={"border-right p-1"}>{s}</pre>
                </div>
                <div className={"col-sm-6"}>
                    {this.props.children}
                    <div className={"m-3 shadow"}>自动评分结果</div>
                    {rl}
                    <div className={"border-top p-3"}>
                        <BsInlineForm ctrls={
                            [
                                new Ctrl("score", "分数",(e)=>{
                                    let p=parseInt(e);
                                    if(isNaN(p)||p<0||p>100){
                                        return "只能是0-100范围内的整数";
                                    }
                                },"text",p[0].score),
                                new Ctrl("scoreReason", "理由", (e)=>{
                                    if(e&&e.length>200){
                                        return "200字以内";
                                    }
                                },"textarea",p[0].scoreReason)
                            ]
                        } submit={this.submit}/>
                        <div>{this.state.msg}</div>
                    </div>
                </div>
            </div>
        )
    }
}

export class Judge extends React.Component {
    state = {
        idl: [],
        cur: 0,
        pass: {}
    }

    componentDidMount() {
        let qs = this.props.location.search;
        if (!qs) return;
        let qsa = qs.split('&');
        let r = {};
        qsa.forEach((a, i) => {
                let t = a.split('=');
                let k = t[0];
                if (i === 0) {
                    k = k.substr(1);
                }
                r[k] = t[1]
            }
        )
        post({
            url: "/pass/judge",
            data: r,
            func: (d) => {
                r.idl = d.data;
                this.setState(r, () => {
                    this.getOne(0)
                });
            }

        })


    }

    getOne = (cur) => {
        if(!this.state.idl||!this.state.idl[cur]){
            return;
        }
        get({
            url: "/pass/source/" + this.state.idl[cur],
            func: (sd) => {
                this.setState({
                    cur: cur,
                    pass: sd
                })
            }
        })
    }
    page = (next) => {
        let c = this.state.cur + next;
        if (c < 0 || c > this.state.idl.length - 1) {
            return;
        }

        this.setState({cur: c}, () => this.getOne(c))
    }
    jump = (e) => {
        let v = e.target.value;
        let vv = parseInt(v);
        console.log(vv)
        if (isNaN(vv) || vv < 0 || vv > this.state.idl.length - 1) {
            return;
        }
        this.setState({cur: vv})
    }

    render() {
        if(!this.state.idl||this.state.idl.length===0){
            return (
                <div className={"container-fluid"}>
                    <div className={"p-3 shadow text-center"}>
                        看起来好像是没人交卷还...停一会再试试...
                    </div>
                </div>
            )
        }
        return (
            <div className={"container-fluid"}>
                <div className={"text-right"}>
                    共{this.state.idl.length}份试卷,
                    <span className={"p-1"}>
                        当前是<input type={"text"} className={"form-control d-inline-block"} value={this.state.cur + 1}
                                  style={{width: '40px'}} onChange={this.jump}/>
                        <button className={"btn btn-link btn-sm"}
                                onClick={() => this.getOne(this.state.cur)}>跳转</button>
                    </span>
                    <button className={"btn btn-link btn-sm"} onClick={() => this.page(-1)}>上一份</button>
                    <button className={"btn btn-link btn-sm"} onClick={() => this.page(1)}>下一份</button>


                </div>
                <JudgePass pass={this.state.pass.p}>
                    <JudgeQuestion question={this.state.pass.q}/>
                </JudgePass>
            </div>
        )
    }
}
