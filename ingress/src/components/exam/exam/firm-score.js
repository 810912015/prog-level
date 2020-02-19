import React from 'react'
import {get,post} from "../../common/util";
import {BsList, TableHeader,Ctrl} from "../../common/bsInlineForm";

export class FirmScore extends React.Component {
    state = {
        items: [],
        cur:1,
        size:10,
        total:0
    }

    componentWillMount() {
        post({
            url: '/firm/score',
            data:{
                query:{
                    iname:'',
                    ename:''
                },
                pager:{
                    cur:1,
                    size:10
                }
            },
            func: (dd) => {
                this.setState({items: dd.data, cur:dd.pager.cur,
                    size:dd.pager.size,
                    total:dd.pager.total})
            }
        })
    }
    getData=(e,f)=>{
        post({
            url:'/firm/score',
            data:e,
            func:(dd)=>{
                console.log(dd);
                f({
                    data:dd.data,
                    cur:dd.pager.cur,
                    size:dd.pager.size,
                    total:dd.pager.total
                })
            }
        })
    }

    render() {
        return (
            <div className={"container-fluid"}>
                <div>
                    <BsList ctrl={[
                        new Ctrl("ename",'考试名称'),
                        new Ctrl("iname",'姓名')
                    ]}
                            pager={true}
                            header={
                        [
                            new TableHeader("ename", "考试名称"),
                            new TableHeader("iname", "姓名"),
                            new TableHeader("email", "邮箱"),
                            new TableHeader("weight", "自动评分"),
                            new TableHeader("score", "人工评分"),
                            new TableHeader("dclt", '时间', (e) => new Date(e).toLocaleString())
                        ]
                    } getData={this.getData} size={10} initItems={this.state.items} initCur={this.state.cur} initTotal={this.state.total}/>
                </div>
            </div>
        )
    }
}
