import PropTypes from "prop-types";
import {CtrlItem2} from "../../common/util";
import React, {Component} from "react";

export class Vali extends Component {
    static propTypes = {
        value: PropTypes.array.isRequired,
        change: PropTypes.func.isRequired
    }
    state = {
        vali: this.props.value || [],
        curId: 0,
        curIn: '',
        curOut: '',
        curWeight: '',
    }

    componentWillReceiveProps(nextProps, nextContext) {
        this.setState({vali: nextProps.value || []})
    }

    newVali = () => {
        this.setState({
            curId: 0,
            curIn: '',
            curOut: '',
            curWeight: ''
        })
    }
    saveVali = () => {
        if (this.state.curIn === '') return;
        let tv = {
            vid: this.state.id,
            input: this.state.curIn,
            output: this.state.curOut,
            weight: this.state.curWeight
        }
        let vl = this.state.vali;
        let index = -1;
        for (let i = 0; i < vl.length; i++) {
            if (tv.id > 0 && vl[i].vid === tv.vid) {
                index = i;
                break;
            }
        }
        if (index > -1) {
            vl[index] = tv;
        } else {
            vl.push(tv);
        }

        this.setState({vali: vl});
        this.props.change(vl)
    }
    delVali = (k) => {
        let t = []
        for (let i = 0; i < this.state.vali.length; i++) {
            let v = this.state.vali[i];
            if (i === k) {
                continue;
            } else {
                t.push(v)
            }
        }

        this.setState({vali: t})
    }
    selVali = (k) => {
        console.log(this.state.vali)
        let t = this.state.vali[k]
        this.setState({
            curIn: t.input,
            curOut: t.output,
            curId: t.id,
            curWeight: t.weight
        })

    }
    change = (e, w) => {
        if (w === "curWeight") {
            let n = parseInt(e.target.value)
            if (!isNaN(n)) {
                this.setState({
                    curWeight: n,
                    msg: ''
                })
            } else {
                this.setState({
                    msg: "分值只能为数字",
                    curWeight: 0
                })
            }
            return;
        }
        this.setState({
            [w]: e.target.value
        })
    }

    render() {
        let va = [];

        for (let v = 0; v < this.state.vali.length; v++) {
            let t = this.state.vali[v];
            let vi = v;
            va.push(
                <tr key={vi + 1}>
                    <td>{t.input}</td>
                    <td>{t.output}</td>
                    <td>{t.weight}</td>
                    <td>
                        <div className={"btn-group btn-group-sm"}>
                            <a className={"btn btn-outline-success"}
                               onClick={() => this.selVali(vi)}
                            >修改</a>
                            <a className={"btn btn-outline-danger"}
                               onClick={() => this.delVali(vi)}
                            >删除</a>
                        </div>
                    </td>
                </tr>
            )
        }
        if (va.length > 0) {
            va.splice(0, 0, (
                <tr key={0}>
                    <td>输入</td>
                    <td>输出</td>
                    <td>分值</td>
                    <td width="120px"></td>
                </tr>
            ))
        }

        let pt = "做题";
        if (this.props.qid > 0) {
            pt = (
                <a href={"#/pass/" + (this.props.qid || 0)}>做题</a>
            )
        }
        return (
            <div>
                <div>
                    验证数据
                    <span className={"btn-group ml-3"}>
                    <a className={"btn btn-outline-success btn-sm"}
                       onClick={this.newVali}
                    >新建</a>

                    <a className={"btn btn-outline-success btn-sm"}
                       onClick={this.saveVali}
                    >保存</a>
                        </span>
                    <span style={{fontSize: '13px', display: 'inline-block', fontStyle: 'oblique'}}>
                        <p>
                        验证输入将会在验证时作为字符串类型的参数调用main函数,即main参数args.以空格隔开会作为多个参数传递给args.
                        </p>
                        <p>
                        一般是组织成一个字符串,然后在main中进行解析.
                        </p>
                        <p>
                        请确保参数的组织和解析正确.可以通过在{pt}时进行调整编译的正确性,也可在<a href={"#/play"}>练习</a>中进行验证.
                        </p>
                    </span>
                </div>

                <hr/>
                <div className={"row no-gutters"}>
                    <div className={"col-sm-5"}>
                        <CtrlItem2 label={"输入"} value={this.state.curIn} id={'curIn'}
                                   change={this.change}
                        />
                    </div>
                    <div className={"col-sm-4"}>
                        <CtrlItem2 label={"输出"} value={this.state.curOut} id={'curOut'}
                                   change={this.change}
                        />
                    </div>
                    <div className={"col-sm-3"}>
                        <CtrlItem2 label={"分值"} value={this.state.curWeight} id={'curWeight'}
                                   change={this.change}
                        />
                    </div>
                </div>
                <table className={"table table-sm table-bordered"}>
                    <tbody>
                    {va}
                    </tbody>
                </table>
            </div>
        )
    }
}
