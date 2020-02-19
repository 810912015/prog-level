import React, {useState} from 'react'
import PropTypes from 'prop-types'


export function Ctrl(
    fid,
    display,
    valiFunc = null,
    type = "text",
    initValue = '',
    options = [],
    name = ''
) {
    return {
        fid: fid,
        display: display,
        type: type,
        valiFunc: valiFunc,
        initValue: initValue,
        options: options,
        name: name
    }
}

export class Tv extends React.Component {
    static propTypes = {
        ctrl: PropTypes.object.isRequired,
        value: PropTypes.oneOfType([PropTypes.string, PropTypes.number]),
        change: PropTypes.func.isRequired,
        vld: PropTypes.bool,
        msg: PropTypes.string
    }
    state = {
        vld: true,
        msg: '',
        value: this.props.value
    }

    componentWillReceiveProps(nextProps, nextContext) {
        if (nextProps.msg && nextProps.msg.length > 0) {
            this.setState({
                vld: false,
                msg: nextProps.msg || ''
            })
        }
        if(nextProps.value){
            this.setState({value:nextProps.value})
        }

    }

    validate = (e) => {
        let v = e.target.value;
        this.setState({value: v})
        if (typeof this.props.ctrl.valiFunc === 'function') {
            let m = this.props.ctrl.valiFunc(v);
            let vl = !m;

            this.setState({
                msg: m,
                vld: vl
            })
        }

        this.props.change(v, this.props.ctrl.fid)
    }

    render() {
        let c = this.props.ctrl;
        let cn = this.state.vld ? "form-control" : "form-control is-invalid";
        let mc = null;
        if (!this.state.vld) {
            mc = (<div className={"invalid-feedback"}>{this.state.msg}</div>)
        }
        return (
            <Inline label={c.display}>
                {this.props.render(this.state.value,this.validate,cn)}
                {mc}
            </Inline>
        )
    }
}

export class Tb extends React.Component {
    static propTypes = {
        ctrl: PropTypes.object.isRequired,
        value: PropTypes.oneOfType([PropTypes.string, PropTypes.number]),
        change: PropTypes.func.isRequired,
        vld: PropTypes.bool,
        msg: PropTypes.string
    }
    rf=(v,c,cn)=>{
        return (
            <input type={'text'} className={cn}
                   style={{minWidth: '160px', maxWidth: '400px'}}
                   value={v}
                   onChange={c}
            />
        )}
    render() {
        return <Tv render={this.rf} {...this.props}/>;
    }
}

export class Ta extends React.Component{
    static propTypes = {
        ctrl: PropTypes.object.isRequired,
        value: PropTypes.oneOfType([PropTypes.string, PropTypes.number]),
        change: PropTypes.func.isRequired,
        vld: PropTypes.bool,
        msg: PropTypes.string
    }
    rf=(v,c,cn)=>{
        return (
            <textarea className={cn} style={{minWidth:'380px'}} value={v} onChange={c}
                      rows={5} cols={20}
            />
        )
    }
    render() {
        return <Tv render={this.rf} {...this.props}/>
    }
}

export class Inline extends React.Component {
    static propTypes = {
        label: PropTypes.string.isRequired
    }

    render() {
        return (
            <>
                <div className={"m-1"}>
                    <span style={{
                        position: 'absolute',
                        top: '5px',
                        left: '3px',
                        paddingLeft: '10px'
                    }}>{this.props.label}</span>
                    <div style={{display: 'inline-block', marginLeft: '60px'}}>
                        {this.props.children}
                    </div>
                </div>

            </>
        )
    }
}

export class BsInlineForm extends React.Component {
    static propTypes = {
        ctrls: PropTypes.arrayOf(Ctrl).isRequired,
        submit: PropTypes.func.isRequired,
        cmd: PropTypes.string
    }
    static defaultProps = {
        cmd: '保存'
    }

    constructor(props) {
        super(props)
        let st = {}
        props.ctrls.forEach(c => {
            if (c.name) {
                st[c.name] = {value: c.initValue, vld: true, msg: ''}
            } else {
                st[c.fid] = {value: c.initValue, vld: true, msg: ''}
            }
        })
        this.state = st;
    }
    componentWillReceiveProps(nextProps, nextContext) {
        if(nextProps.ctrls&&nextProps.ctrls.length>0){
            let props=nextProps;
            let st = {}
            props.ctrls.forEach(c => {
                if (c.name && ! c.name in st) {
                    st[c.name] = {value: c.initValue, vld: true, msg: ''}
                } else if(!(c.fid in st) || !st[c.fid]){
                    st[c.fid] = {value: c.initValue, vld: true, msg: ''}
                }
            })
            this.setState(st)
        }
    }

    change = (v, w) => {

        this.setState({[w]: {value: v}})
    }
    makeSel = (c) => {
        let ol = []
        ol.push(<option key={"select-guard"}>请选择</option>)
        c.options.forEach(o => {
            ol.push(<option key={o.value} value={o.value}>{o.display}</option>)
        })
        return (
            <div key={c.fid} className={"col-auto"}>
                <Inline label={c.display}>
                    <select id={c.fid} className={"custom-select"}
                            value={this.state[c.fid].value || ''}
                            onChange={e => this.change(e.target.value, c.fid)}>
                        {ol}
                    </select>
                </Inline>
            </div>

        )
    }
    makeCb = (c) => {
        return (
            <div key={c.fid} className={"col-auto"}>
                <div className="form-check form-check-inline m-1">
                    <input className={"form-check-input"} type={c.type}
                           checked={this.state[c.fid].value || false}
                           value={this.state[c.fid].value || false}
                           name={c.fid}
                           onChange={(e) => {
                               this.change(e.target.checked, c.fid)
                           }}
                    />
                    <label className="form-check-label">{c.display}</label>
                </div>
            </div>
        )
    }
    makeRadio = (c) => {
        return (
            <div key={c.fid} className={"col-auto"} style={{paddingLeft: '20px', marginTop: '3px', marginLeft: '20px'}}>
                <input className="form-check-input"
                       type={"radio"} id={c.display}
                       value={c.fid}
                       defaultChecked={this.state[c.name].value === c.fid}
                       name={c.name}
                       onChange={(e) => {
                           this.change(e.target.value, c.name)
                       }}/>
                <label className="form-check-label" htmlFor={c.display}>
                    {c.display}
                </label>
            </div>
        )
    }
    makeTb = (c) => {
        return (
            <div key={c.fid} className={"col-auto"}>
                <Tb ctrl={c}
                    change={this.change}
                    value={this.state[c.fid].value || ''}
                    vld={this.state[c.fid].vld}
                    msg={this.state[c.fid].msg || ''}
                />
            </div>
        )
    }
    makeTa=(c)=>{
        return (
            <div key={c.fid} className={"col-auto"}>
                <Ta ctrl={c}
                    change={this.change}
                    valu value={this.state[c.fid].value || ''}
                    vld={this.state[c.fid].vld}
                    msg={this.state[c.fid].msg || ''}
                    />
            </div>
        )
    }
    makeOne = (c) => {
        if (c.type === "select") {
            return this.makeSel(c)
        } else if (c.type === 'checkbox') {
            return this.makeCb(c);
        } else if (c.type === "radio") {
            return this.makeRadio(c);
        } else if(c.type==="textarea"){
            return this.makeTa(c);
        } else{
            return this.makeTb(c);
        }

    }
    submit = () => {
        let valid = true;
        this.props.ctrls.forEach(c => {
            if (typeof c.valiFunc === 'function') {
                let m = c.valiFunc(this.state[c.fid].value)
                let vl = !m;
                if (!vl) {
                    this.setState({
                        [c.fid]: {
                            vld: vl,
                            msg: m,
                        }
                    })
                    valid = false;
                }

            }
        })

        if (!valid) {
            return;
        }
        let p = {}
        for (let k in this.state) {
            p[k] = this.state[k].value
        }
        this.props.submit(p)
    }

    render() {
        let {ctrls, submit, ...rest} = this.props

        let cl = [];
        ctrls.forEach(c => {
            cl.push(this.makeOne(c))
        })
        return (
            <div {...rest}>
                <div {...rest} className={"form-row"}>
                    {cl}
                    <div className={"col-auto"}>

                        <button className={"btn btn-success m-1"}
                                onClick={this.submit}>{this.props.cmd}
                        </button>
                        {this.props.children}
                    </div>
                </div>

            </div>
        )
    }
}

export class MyformExample extends React.Component {
    render() {
        let ca = [];
        let oa = []
        for (let i = 0; i < 2; i++) {
            let c = new Ctrl("fid" + i, "Ctrl-" + i,
                (e) => {
                    let t = parseInt(e);
                    if (isNaN(t)) {
                        return "必须是数字";
                    }
                    if (t < 100) {
                        return "必须大于100"
                    }
                });

            ca.push(c)
            oa.push({display: 'o' + i, value: i})
        }
        ca.push(new Ctrl("fs",
            'select',
            null,
            'select',
            1,
            oa
        ))
        ca.push(new Ctrl("cb", "checkbox", null, "checkbox"))
        ca.push(new Ctrl("cb1", "checkbox1", null, "checkbox"))
        let r1 = new Ctrl("r1", "r1", null, "radio");
        let r2 = new Ctrl("r2", "r2", null, "radio");
        //对于radio来说,name相同标志为一组,只能多选一
        r1.name = r2.name = "radio"
        ca.push(r1)
        ca.push(r2)

        let r11 = new Ctrl("r11", "r11", null, "radio");
        let r21 = new Ctrl("r21", "r21", null, "radio");
        r11.name = r21.name = "radio1"
        ca.push(r11)
        ca.push(r21)

        return (
            <BsInlineForm ctrls={ca} className={"border p-2"}
                          submit={(e) => {
                              console.log("fuck", e)
                          }}/>
        )
    }
}

function Kv(k, v) {
    this.k = k;
    this.v = v;
}

export class Pager extends React.Component {
    static propTypes = {
        total: PropTypes.number.isRequired,
        cur: PropTypes.number.isRequired,
        size: PropTypes.number.isRequired,
        select: PropTypes.func.isRequired
    }

    render() {
        let t = this.props.total;
        let c = this.props.cur;
        let s = this.props.size;
        let p = Math.ceil(t / s);

        let from = c - 5;
        if (from < 1) {
            from = 1
        }
        let to = c + 5;
        if (to > p) {
            to = p;
        }
        let pbs = [];

        function putInto(k, v) {
            pbs.push(new Kv(k, v))
        }

        putInto("<<", 1);
        putInto("<", c - 1 < 1 ? 1 : c - 1);
        for (let i = from; i <= to; i++) {
            putInto(i, i);
        }

        putInto(">", c + 1 > p ? p : c + 1);
        putInto(">>", p);

        let al = []
        for (let i = 0; i < pbs.length; i++) {
            let pb = pbs[i]
            let cn = "page-item";
            if (c === pb.v) {
                cn = "page-item disabled"
            }
            al.push(
                <li key={i} className={cn}>
                    <button className={"page-link"}
                            onClick={() => this.props.select(pb.v)}

                    >{pb.k}</button>
                </li>
            )
        }

        return (
            <div>

                <ul className={"pagination pagination-sm"}>
                    {al}
                    <li className={"page-item"}>
                        <span style={{
                            display: 'inline-block',
                            padding: '4px'
                        }}>共{p}页</span>
                    </li>
                </ul>
            </div>
        )
    }
}

export function TableHeader(cid, header,formatter=null) {
    return {
        cid: cid,
        header: header,
        formatter:formatter
    }
}

export class Table extends React.Component {
    static propTypes = {
        header: PropTypes.arrayOf(TableHeader).isRequired,
        items: PropTypes.arrayOf(PropTypes.object).isRequired,
        makeCmd:PropTypes.func
    }

    render() {
        let hasCmd=typeof this.props.makeCmd==='function'

        let {header, items,makeCmd, ...ta} = this.props
        let h = header;
        let l = items;
        let hl = []
        let rl = []
        h.forEach(a => {
            let {cid, header,formatter, ...rest} = a;
            hl.push(<th key={a.cid} {...rest}>

                {a.header}

            </th>)
        })
        if(hasCmd){
            hl.push(<th key={"op"}>操作</th>)
        }
        l.forEach((a, i) => {
            let dl = [];
            h.forEach(b => {
                let v=a[b.cid];
                if(typeof b.formatter==='function'){
                    v=b.formatter(v);
                }
                dl.push(<td key={b.cid}>
                    {v}
                </td>)
            })
            if(hasCmd){
                dl.push(<td key={"op"}>{this.props.makeCmd(a)}</td>)
            }

            rl.push(<tr key={i}>{dl}</tr>)
        })

        return (
            <div className="table-responsive">
                <table className={"table table-bordered"} {...ta}>
                    <thead>
                    <tr>
                        {hl}
                    </tr>
                    </thead>
                    <tbody>
                    {rl}
                    </tbody>
                </table>
            </div>
        )
    }
}

export class BsList extends React.Component {
    static propTypes = {
        ctrl: PropTypes.arrayOf(Ctrl).isRequired,
        pager: PropTypes.bool.isRequired,
        header: PropTypes.arrayOf(TableHeader).isRequired,
        getData: PropTypes.func.isRequired,
        size: PropTypes.number.isRequired,
        makeCmd:PropTypes.func,
        initTotal:PropTypes.number,
        initCur:PropTypes.number
    }
    state = {
        form: {},
        total: this.props.initTotal||1,
        cur: this.props.initCur||1,
        size: this.props.size,
        items: this.props.initItems||[],
        header: {},
        selected: 0
    }
    componentWillReceiveProps(nextProps, nextContext) {
        if(nextProps.initItems){
            this.setState({items:nextProps.initItems,total:nextProps.initTotal,cur:nextProps.initCur})
        }
    }

    queryByForm = (form) => {

        this.setState({form: form}, this.doQuery)
    }
    query = (page) => {
        this.setState({selected: page}, this.doQuery)

    }
    doQuery = () => {
        let d = {
            query: this.state.form,
            pager: {
                cur: this.state.selected || 1,
                size: this.state.size
            }
        }
        this.props.getData(d, (rd) => {
            this.setState({
                items: rd.data,
                total: rd.total,
                cur: rd.cur
            })
        });

    }

    render() {
        let p = null;
        if (this.props.pager) {
            p = (<Pager total={this.state.total}
                        cur={this.state.cur}
                        size={this.state.size}
                        select={this.query}/>)
        }
        let f = null
        if (this.props.ctrl && this.props.ctrl.length > 0) {
            f = (<BsInlineForm ctrls={this.props.ctrl} submit={this.queryByForm} cmd={"查询"}/>)
        }
        return (
            <>
                <div>
                    {f}
                </div>
                <div>
                    <Table header={this.props.header} items={this.state.items} makeCmd={this.props.makeCmd}/>
                </div>
                <div>
                    {p}
                </div>
            </>

        )
    }
}

export class BsListExample extends React.Component {
    constructor(props) {
        super(props)
        let l = [];
        for (let i = 0; i < 100; i++) {
            let t = {}
            t.id = i
            for (let j = 0; j < 10; j++) {
                t["p" + j] = "p" + i + "_" + j
            }
            l.push(t);
        }
        this.state = {
            items: l
        }
    }

    getData = (d, f) => {

        let query = d.query, pager = d.pager;
        let t = [], start = (pager.cur - 1) * pager.size, end = pager.cur * pager.size
        if (start < 0) start = 0;
        if (end > this.state.items.length) end = this.state.items.length;

        for (let i = start; i < end; i++) {
            t.push(this.state.items[i]);
        }
        f({
            data: t,
            total: this.state.items.length,
            cur: pager.cur
        })
    }

    render() {
        let cl = []
        cl.push(new Ctrl("p0", "属性1"))
        cl.push(new Ctrl("p1", "属性2"))

        let hl = []
        hl.push(new TableHeader("id", '编号'))
        for (let i = 0; i < 10; i++) {
            hl.push(new TableHeader("p" + i, "属性" + i))
        }
        return (
            <BsList ctrl={cl} pager={true} header={hl} getData={this.getData} size={10}/>
        )
    }
}
