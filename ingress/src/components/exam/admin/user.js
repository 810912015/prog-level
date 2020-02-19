import React from 'react'
import {get, post} from "../../common/util";
import {BsInlineForm, BsList, Ctrl, TableHeader} from "../../common/bsInlineForm";
import {Link, Route} from "react-router-dom";
import {mailReg} from "../../auth/Regsiter";
import {SimpleModal} from "../../common/Model";
import {Select} from "../../common/util";
import {SidebarContainer, Menu} from "../../common/menu";

export class UserList extends React.Component {
    constructor(props) {
        super(props)
        this.firmOptions = {}

        this.state = {
            initItems: [],
            firm: 0
        }

    }

    getData = (d, f) => {
        get({
            url: "/user/all", func: d => {
                f({data: d})
            }
        })
    }

    componentWillMount() {
        post({
            url: "/firm/list",
            data: {name: '', title: ''},
            func: (dd) => {
                let ol = {}
                ol[0] = "请选择"

                dd.forEach(a => {
                    ol[a.id] = a.name

                })
                this.firmOptions = ol;
                this.init();
            }
        })
    }

    init = () => {
        get({
            url: "/user/all",
            func: (d) => {

                this.setState({initItems: d})
            }
        })
    }
    changeFirm = (eid) => {
        post({
            url: "/user/updateFirm",
            data: {
                uid: parseInt(eid),
                firmId: parseInt(this.state.firm)
            },
            func: (dd) => {
                let ia = []
                this.state.initItems.forEach(a => {
                    if (a.id === eid) {
                        a.firmId = this.state.firm;
                    }
                    ia.push(a)
                })
                this.setState({initItems: ia})
            }
        })
    }
    del = (id) => {
        post({
            url: "/user/del/" + id,
            data: {},
            func: this.init
        })
    }
    makeCmd = (e) => {
        return (
            <div>
                <Link to={"/user/create/" + e.id}>修改</Link>
                <SimpleModal title={"确认删除"} cmdClass={"btn btn-link"} action={() => this.del(e.id)}>
                    确认删除{e.name}{e.email}吗?
                </SimpleModal>
                <SimpleModal title={"修改员工所属公司"} cmdClass={"btn btn-outline-success"} cmdTip={"修改公司"}
                             action={() => this.changeFirm(e.id)}
                >
                    <div>
                        <Select title={"选择公司"} id={"firm"} options={this.firmOptions} change={(e) => {
                            this.setState({firm: e})
                        }
                        }
                                sel={this.state.firm}/>
                    </div>
                </SimpleModal>
            </div>
        )
    }

    render() {
        let h = []
        let lid = new TableHeader("id", "编号")
        h.push(lid);
        h.push(new TableHeader("email", "电子邮件"))
        h.push(new TableHeader("nickname", "昵称"));
        h.push(new TableHeader("pswd", "密码"));
        h.push(new TableHeader("create_time", "建立时间", (t) => {
            return new Date(t).toLocaleString()
        }));
        h.push(new TableHeader("last_login_time", "登录时间", (t) => {
            return new Date(t).toLocaleString()
        }));
        h.push(new TableHeader("status", "状态"));
        h.push(new TableHeader("petname", "用户名"));
        h.push(new TableHeader("gender", "性别"));
        h.push(new TableHeader("birthday", "生日", (t) => {
            return new Date(t).toLocaleString()
        }));
        h.push(new TableHeader("photoUrl", "头像"));
        h.push(new TableHeader("intro", "简介"));
        h.push(new TableHeader("roles", "角色", (e) => {
            if (!e) return ''
            return roles[e] || '';
        }));
        h.push(new TableHeader("firmId", "公司", (e) => {
            if (!this.firmOptions) return e;
            if (e in this.firmOptions) {
                return this.firmOptions[e]
            }
            return e;
        }));
        let cl = []
        cl.push(new Ctrl("nickname", "用户名"))
        cl.push(new Ctrl("email", "电子邮件"))

        return (
            <BsList ctrl={cl} pager={false} header={h} getData={this.getData} initItems={this.state.initItems} size={10}
                    makeCmd={this.makeCmd}/>
        )
    }
}

const roles = {
    admin: '超级管理员',
    "firm-admin": '公司管理员',
    teacher: '教师',
    '': ''
}

export class UserItem extends React.Component {
    state = {
        item: {}
    }

    componentWillMount() {
        let id = this.props.match.params.id;
        if (id > 0) {
            get({
                url: "/user/get/" + id,
                func: (d) => {
                    this.setState({item: d})
                }
            })
        }
    }

    vali = (e) => {
        if (!e) {
            return "不能为空"
        }
        if (e.length < 6) {
            return "最少6位"
        }
    }
    submit = (e) => {
        e.id = this.props.match.params.id;
        post({
            url: "/user/create",
            data: e,
            func: (d) => {
                this.props.history.push("/user/all")
            }
        })
    }

    render() {
        let c1 = new Ctrl("email", "电子邮件", (e) => {
            if (!e || e.length === 0) {
                return "不能为空"
            }
            if (!mailReg.test(e)) {
                return "格式错误"
            }
        })
        let c2 = new Ctrl("nickname", "用户名", this.vali)
        let c3 = new Ctrl("pswd", "密码", this.vali);
        let c4 = new Ctrl("roles", "角色", () => {
        }, "select", "", [
            {value: 'admin', display: '超级管理员'},
            {value: 'firm-admin', display: '公司管理员'},
            {value: 'teacher', display: '教师'},
        ])
        let cl = [c1, c2, c3, c4]
        cl.forEach(a => a.initValue = this.state.item[a.fid])

        return (
            <div>
                <BsInlineForm ctrls={cl} submit={this.submit}/>
            </div>
        )
    }
}

export class UserMgr extends React.Component {

    render() {
        return (
            <div className={"container-fluid"}>
                <SidebarContainer left={
                    <Menu items={[
                        {
                            header:'人员',
                            items: [
                                {
                                    to: '/user/all',
                                    tip: '人员列表'
                                },
                                {
                                    sub:{
                                        header:'公司2',
                                        items: [
                                            {
                                                to: '/user/all',
                                                tip: '人员列表2'
                                            },
                                            {
                                                sub:{
                                                    header:'公司3',
                                                    items: [
                                                        {
                                                            to: '/user/all',
                                                            tip: '人员列表3'
                                                        },
                                                        {
                                                            to: '/user/create/0',
                                                            tip: '新建人员3'
                                                        }
                                                    ]
                                                }
                                            }
                                        ]
                                    }
                                }
                            ]
                        },
                        {
                            header:'公司1',
                            items: [
                                {
                                    to: '/user/all',
                                    tip: '人员列表1'
                                },
                                {
                                    to: '/user/create/0',
                                    tip: '新建人员1'
                                }
                            ]
                        }
                    ]}/>
                } right={(
                    <>
                        <Route path={"/user/all"} key={"list"} component={UserList}/>
                        <Route path={"/user/create/:id"} component={UserItem} key={"item"}/>
                    </>
                )}/>
            </div>
        )
    }

}
