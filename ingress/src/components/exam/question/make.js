import {CtrlItem2, JPostData, post, Ta, Select} from "../../common/util";
import React, {Component} from "react";
import {withRouter} from "react-router-dom";
import {connect} from "react-redux";
import {createQuestion} from "../../../action/question";
import {RichEditor} from "../../common/RichEditor";
import {MultiLangEditor} from "../../common/CoderEditor";
import {Tag} from "./tag";
import {Vali} from "./vali";

export function getSourceObjFromArray(list) {
    let so={}
    for(let i=0;i<list.length;i++){
        let t=list[i]
        so[t.lang]=t.source
    }
    return so;
}

class Make extends Component {
    constructor(props) {
        super(props);
        this.state = {
            id: this.props.match.params.id || 0,
            name: '',
            title: '',
            source: '',
            vali: [],
            msg: '',
            lang: 'java',
            level: 1,
            tags: [],
            cat: '算法',
            sourceObj: {}
        }
    }

    componentWillMount() {
        if (this.state.id > 0) {
            post(new JPostData("/question/withvali/" + this.state.id, {}, (d) => {
                if (!!d) {
                    let so=getSourceObjFromArray(d.sourceList)
                    let ns={
                        name: d.name,
                        title: d.title,
                        source: d.source,
                        vali: d.vali,
                        tags: d.tags || [],
                        level: d.level || 0,
                        lang: d.lang || 'java',
                        cat: d.cat || '',
                        sourceObj: so
                    }
                    this.setState(ns)
                }
            }))
        }
    }

    save = () => {
        let t = {
            id: this.state.id,
            name: this.state.name,
            title: this.state.title,
            source: this.state.source,
            vali: this.state.vali,
            tags: this.state.tags,
            level: this.state.level,
            lang: this.state.lang,
            userId: this.props.ld.uid,
            cat: this.state.cat
        }
        let sourceList=[]
        for(var k in this.state.sourceObj){
            sourceList.push({qid:this.state.id,
                lang:k,
                source:this.state.sourceObj[k]
            })
        }
        t.sourceList=sourceList;
        this.props.createQuestion({
            data: t,
            callback: () => {
                this.props.history.push("/qlist")
            }
        })
    }
    change = (e, w) => {
        this.setState({
            [w]: e
        })
    }
    render() {
        return (
            <div className={"container-fluid"}>
                <div className={"row"}>
                    <div className={"col-sm-4 border-right"}>
                        <div>
                            <CtrlItem2 label={"试题名称"} value={this.state.name} id={'name'} change={(e)=>this.change(e.target.value,'name')}/>
                            <div className={"form-row  no-gutters"}>
                                <div className={"col"}>
                                    <Select title={"难度"} id={'level'} sel={this.state.level}
                                            options={{[1]: "容易", [3]: "中等", [5]: "较难"}} change={this.change}/>
                                </div>
                                <div className={"col"}>
                                    <Select title={"分类"} id={'cat'} sel={this.state.cat}
                                            options={{"算法": "算法", "数据结构": "数据结构", "应用框架": "应用框架", "其他": "其他"}}
                                            change={this.change}/>
                                </div>
                            </div>
                            <Tag value={this.state.tags} change={(e) => this.setState({tags: e})}/>
                            <div className={"p3"}>
                                <label>试题说明</label>

                                <RichEditor value={this.state.title} change={(e) => this.setState({title:e})}/>
                            </div>
                            <div className={"mt-3"}>
                                <Vali value={this.state.vali} change={(e) => this.setState({vali: e})} qid={this.state.id}/>
                            </div>
                        </div>
                    </div>
                    <div className={"col-sm-8"}>
                        <MultiLangEditor sourceList={this.state.sourceObj} sourceChange={(e)=>{this.setState({sourceObj:e})}}/>
                    </div>
                </div>
                <div style={{position:'fixed',right:'100px',bottom:'10px'}}>
                    <a className={"btn btn-outline-success"}
                       onClick={this.save}>保存</a>
                    <span>{this.state.msg}</span>
                </div>
            </div>

        )
    }
}

const mapStateToProps = (state, ownProps) => {
    const ld = state.login;

    return {ld}
}

export default withRouter(connect(mapStateToProps, {createQuestion})(Make))
