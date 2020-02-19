import React, {Component} from "react";
import {withRouter} from "react-router-dom";
import {connect} from "react-redux";
import {post, JPostData} from "../../common/util";
import {limitTitle} from "../question/qlist";
import Login from '../../auth/login'

class ScoreItem extends Component {
    render() {
        let d = limitTitle(this.props.qtitle);
        return (
            <div className={"card"}>
                <div className={"card-header"}>
                    <p className={"card-text"}>
                        <span className={"badge badge-info"} title={"得分" + this.props.score}>{this.props.score}</span>
                        <span className={"ml-1"}>{this.props.qname}</span>
                    </p>
                </div>
                <div className={"card-body"}>
                    <div className={"card-text"}>
                        {d}
                    </div>
                    <p className={"card-text"}>
                        {new Date(parseFloat(this.props.dclt)).toLocaleString()}
                    </p>
                </div>
                <div className={"card-footer"}>
                    <a className={"btn btn-sm btn-outline-primary"}
                       onClick={() => this.props.redo(this.props.qid)}>重做</a>
                </div>
            </div>
        )
    }
}

class Score extends Component {
    constructor(props) {
        super(props)
        this.state = {
            items: []
        }
    }

    componentWillMount() {
        post(new JPostData("/score/", {}, (d) => {
            this.setState({
                items: d || []
            })
        }))
    }

    redo = (id) => {
        this.props.history.push("/pass/" + id)
    }

    render() {
        let t = []
        for (let i = 0; i < this.state.items.length; i++) {
            let c = this.state.items[i];
            t.push(
                <ScoreItem {...c} key={i} redo={this.redo}/>
            );
        }
        return (
            <div className={"container"}>
                <div className={"card-columns"}>
                    {t}
                </div>
            </div>
        )
    }
}


const mapStateToProps = (state, ownProps) => {
    const ld = state.login;
    return {ld}
}

export default withRouter(connect(mapStateToProps, {})(Score))
