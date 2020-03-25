import React, {Component} from "react";
import {Link, withRouter} from 'react-router-dom'
import {myQuestion, delQuestion} from "../../../action/question";
import {connect} from "react-redux";
import {bound} from "../exam/exam";
import ReactHtmlParser from 'react-html-parser'
import {Scroller} from "../../common/scroller";
import {SimpleModal} from "../../common/Model";

export function limitTitle(title) {
    let c=[];
    let p= ReactHtmlParser(title);

    if (p && p.length > 3) {
        for(let i=0;i<3;i++){
            c.push(p[i])
        }
        c.push("...")
    }else{
        c=p;
    }
    return c;
}

function convertLevel(level){
    if(!level) level=1;
    let cn="badge float-right ";
    switch (level) {
        case 1:
            cn+="badge-success"
            return ["较易",cn];
        case 2:
            cn+="badge-info"
            return ["容易",cn];
        case 3:
            cn+="badge-primary"
            return ["中等",cn];
        case 4:
            cn+="badge-warning"
            return ["较难",cn];
        case 5:
            cn+="badge-danger"
            return ["难",cn];
        default:
            cn+="badge-secondary"
            return ["未知",cn];
    }
}

export function Qi2(props) {
    let c=limitTitle(props.title);
    let cl=null
    if(props.children){
        cl=(
            <div className={"card-footer"}>
                {props.children}
            </div>
        )
    }
    let cr=convertLevel(props.level);
    return (
            <div style={{border:"1px solid #bbb",padding:"10px 15px"}}>
                <span>{props.id}</span>
                <span style={{fontWeight:550}}>{props.name}</span>
                <span style={{marginLeft:"10px"}} className={cr[1]} title={"难度系数,从易到难依次为1-5"}>{cr[0]}</span>
            </div>
    )
}

export class QItem extends Component {
    render() {
        let c=limitTitle(this.props.title);
        let cl=null
        if(this.props.children){
            cl=(
                <div className={"card-footer"}>
                    {this.props.children}
                </div>
            )
        }
        let cr=convertLevel(this.props.level);

        return (
            <div className={"card mt-3 w-100"}>
                <div className={"card-header"}>
                    {this.props.id} {this.props.name}
                    <span className={cr[1]}
                          title={"难度系数,从易到难依次为1-5"}>{cr[0]}</span>
                </div>
                <div className={"card-body"} title={this.props.title}>
                    {c}
                </div>
                {cl}
            </div>
        )
    }
}

class QList extends Component {
    constructor(props) {
        super(props)
        this.state = {
            questions: this.props.qs.mine || []
        }

        if (!this.props.qs.mine) {
            this.props.myQuestion({data: {size: 10,uid:this.props.ld.uid}})
        }
    }

    componentWillReceiveProps(nextProps, nextContext) {
        this.setState({
            questions: nextProps.qs.mine || {}
        })
    }

    del = (id) => {
        this.props.delQuestion(id)
    }

    render() {

        let p = []
        for (let i = 0; i < this.state.questions.length; i++) {
            let t = this.state.questions[i];
            p.push(
                <QItem key={t.id+10000} name={t.name} title={t.title} id={t.id} level={t.level}>

                        <Link to={"/pass/" + t.id} className={"btn btn-sm btn-outline-primary"}>做题</Link>
                        <Link to={"/make/" + t.id} className={"btn btn-sm btn-outline-info"}>修改</Link>
                        <SimpleModal title={"删除确认"} action={()=>this.del(t.id)}>
                            <div>确认删除试题{t.name}吗?</div>
                        </SimpleModal>

                </QItem>
            )
        }
        let bid=bound(this.state.questions)
        return (

            <div className={"container"}>
                <div className={"float-sm-right"}>
                    <Link to="/make/0" className={"btn btn-link"} key={"question"}>我要出题</Link>
                </div>
                <Scroller onBottom={() => this.props.myQuestion({data:{size:10,maxId:bid.maxId,minId:bid.minId,uid:this.props.ld.uid}})} more={p.length>9}>
                    <div className={"card-columns"}>
                    {p}
                    </div>
                </Scroller>
            </div>
        )
    }
}

const mapStateToProps = (state, ownProps) => {
    const ld = state.login;
    const qs = state.questions || {}

    return {ld, qs}
}

export default withRouter(connect(mapStateToProps, {myQuestion, delQuestion})(QList))
