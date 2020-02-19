import React, {Component} from "react";
import {CtrlItem, JPostData, post} from "../../common/util";
import DatePicker from 'react-datepicker';
import {Link, withRouter} from "react-router-dom";
import {connect} from "react-redux";
import {allQuestion} from "../../../action/question";
import moment from 'moment'
import 'moment/locale/zh-cn'
import 'react-datepicker/dist/react-datepicker.css'
import {QItem} from "../question/qlist";
import {Scroller} from "../../common/scroller";

class Exam extends Component {
    constructor(props) {
        super(props)
        this.state = {
            id: this.props.match.params.id || 0,
            name: '',
            title: '',
            questions: this.props.qs.all || [],
            check: {},
            duration: '',
            start: new Date(),
            msg: '',
            cat: '',
            tag: ''
        }
        if (!this.props.qs.all) {
            this.props.allQuestion({url: "/question/all", data: {size: 10}})
        }
    }

    componentWillMount() {
        if (this.state.id > 0) {
            post(new JPostData("/exam/get/" + this.state.id, {}, (d) => {
                if (!!d) {
                    let c = {};
                    if (d.check && d.check.length) {
                        for (let i = 0; i < d.check.length; i++) {
                            let v = d.check[i]
                            c[v.id] = v.score
                        }
                    }
                    this.setState({
                        name: d.name,
                        title: d.title,
                        duration: d.duration,
                        start: d.start ? new Date(d.start) : new Date(),
                        cat: d.cat || '',
                        tag: d.tag || '',
                        check: c
                    })
                }
            }))
        }
    }

    componentWillReceiveProps(nextProps, nextContext) {
        this.setState({
            questions: nextProps.qs.all || []
        })
    }

    tip = (m) => {
        this.setState({
            msg: m
        })
    }
    save = () => {
        let pd = {
            id: this.state.id,
            name: this.state.name,
            title: this.state.title,
            duration: this.state.duration,
            start: this.state.start,
            check: [],
            tag: this.state.tag,
            cat: this.state.cat
        }

        if (pd.name == null || pd.name === '') {
            this.tip('名称不能为空');
            return;
        }
        if (pd.title == null || pd.title === '') {
            this.tip('说明不能为空')
            return;
        }
        if (isNaN(parseInt(pd.duration))) {
            this.tip('时长必须是数字')
            return;
        }

        for (let k in this.state.check) {
            pd.check.push({
                id: k,
                score: this.state.check[k] || 0
            })
        }
        post(new JPostData("/exam/create", pd, (d) => {
            this.props.history.push("/exams")
        }))
    }
    change = (e, w) => {
        this.setState({
            [w]: e.target.value,
            msg: ''
        })
    }
    check = (e) => {
        let w = e.target.value;
        let c = e.target.checked;
        let t = this.state.check;
        if (c) {
            t[w] = 0;
        } else {
            delete t[w]
        }
        this.setState({check: t})
    }
    scoreChange = (e, w) => {
        let t = this.state.check;
        t[w] = e.target.value;
        let p = parseInt(t[w])
        if (isNaN(p)) {
            this.setState({
                msg: '分值必须是整数'
            })
        }
        this.setState({check: t, msg: ''})
    }
    startChange = (d) => this.setState({start: new Date(d)})

    render() {

        let qa = [];
        for (let i = 0; i < this.state.questions.length; i++) {
            let t = this.state.questions[i]
            let index = i;
            let s = this.state.check[t.id] || '';
            let u=(t.id in this.state.check)||false;
            qa.push(
                <QItem key={t.id} name={t.name} title={t.title} id={t.id}>
                    <div className={"row"}>
                        <div className={"col"}>
                            <label>选用</label>
                            <input type="checkbox" className={"form-control"} value={t.id} onChange={this.check} checked={u}/>

                        </div>
                        <div className={"col"}>
                            <label>分值</label>
                            <input value={s} className={"form-control"} onChange={(e) => this.scoreChange(e, t.id)}/>
                        </div>
                    </div>
                </QItem>
            )
        }
        let bid = bound(this.state.questions)
        return (
            <div className={"container"}>
                <div className={"row"}>
                    <div className={"col col-sm-4"}>
                        <CtrlItem label={"试卷名称"} value={this.state.name} change={(e) => this.change(e, "name")}/>
                        <CtrlItem label={"试卷说明"} value={this.state.title} change={(e) => this.change(e, "title")}/>
                        <CtrlItem label={"时长(分钟)"} value={this.state.duration}
                                  change={(e) => this.change(e, "duration")}/>
                        <div className={"form-row"}>
                            <div className={"col"}>
                                <CtrlItem label={"分类"} value={this.state.cat} change={(e) => this.change(e, "cat")}/>
                            </div>
                            <div className={"col"}>
                                <CtrlItem label={"标签"} value={this.state.tag} change={(e) => this.change(e, "tag")}/>
                            </div>
                        </div>
                        <div className={"form-group"}>
                            <label>开始时间</label>
                            <DatePicker id="startPicker" className={"ml-3"}
                                        showTimeSelect
                                        timeCaption="时间"
                                        timeFormat='HH:mm'
                                        dateFormat="YYYY-MM-dd HH:mm"
                                        timeIntervals={5}
                                        isClearable={true}
                                        selected={this.state.start} onChange={this.startChange}
                                        onChangeRaw={(event) => this.startChange(event.target.value)}
                            />
                        </div>

                        <div className={"mt-3"}>
                            <a className={"btn btn-outline-primary"} onClick={this.save}>保存</a>
                            <span className={"ml-3"}>{this.state.msg}</span>
                        </div>

                    </div>
                    <div className={"col col-sm-8"}>
                        <div >
                            <span>请选择试题</span>
                            <span>
                           <Link to="/make/0" className={"btn btn-link ml-3"}
                                 key={"question"}>出题</Link>
                        </span>
                        </div>
                        <div className={"border"}>
                            <Scroller onBottom={() => this.props.allQuestion({
                                url: "/question/all",
                                data: {maxId: bid.maxId, minId: bid.minId, size: 10}
                            })} more={qa.length>9}>
                                {qa}
                            </Scroller>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

export function bound(l) {
    if (!l || !l.length) return {maxId: null, minId: null};
    let max = 0, min = Number.MAX_VALUE
    for (let i = 0; i < l.length; i++) {
        let t = l[i];
        if (max < t.id) {
            max = t.id;
        }
        if (min > t.id) {
            min = t.id
        }
    }
    return {maxId: max, minId: min}
}

const mapStateToProps = (state, ownProps) => {
    const ld = state.login;
    const qs = state.questions || {}

    return {ld, qs}
}

export default withRouter(connect(mapStateToProps, {allQuestion})(Exam))
