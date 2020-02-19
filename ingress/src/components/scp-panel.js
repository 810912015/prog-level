import React from 'react'
import '../css/bootstrap.css';
import {Link, Route} from 'react-router-dom'
import {WorkPannel} from "./cat/work-pannel";
import Sheets from "./sheet/sheet"
import Prs from "./prop/props"
import {Col2111Pannel} from "./containers"
import {Active} from "./common/active";

class Scp extends React.Component {
    constructor(props) {
        super(props)
    }

    render() {
        let s1 = this.props.calActive(1);
        let s2 = this.props.calActive(2);
        let s3 = this.props.calActive(3);
        let left = (
            <div className="bg-light border-light" id={"sidebar-wrapper"}>
                <div className="list-group list-group-flush">
                    <Link className={s1} to="/cat/rule" onClick={()=>this.props.onActive(1)}>规则</Link>
                    <Link className={s2} to="/cat/sheet" onClick={()=>this.props.onActive(2)}>单据</Link>
                    <Link className={s3} to="/cat/prop" onClick={()=>this.props.onActive(3)}>配置项</Link>
                </div>
            </div>
        )
        let right = (
            <div>
                <Route path="/cat/rule" component={WorkPannel}/>
                <Route path="/cat/sheet" component={Sheets}/>
                <Route path="/cat/prop" component={Prs}/>
            </div>
        )
        return (
            <Col2111Pannel left={left} right={right}/>
        )
    }
}

export default Active(Scp,{active:"list-group-item list-group-item-action bg-info",unactive:"list-group-item list-group-item-action bg-light"});
