import React, {Component} from 'react';
import {Link, withRouter} from "react-router-dom";
import {bound} from "../exam/exam";
import {Scroller} from "../../common/scroller";
import {connect} from "react-redux";
import {allQuestion} from "../../../action/question";
import {QItem,Qi2} from "./qlist";
import PropTypes from 'prop-types'

export class Sign extends Component{
    static propTypes={
        threme:PropTypes.string,
        cat:PropTypes.string,
        list:PropTypes.arrayOf(PropTypes.string)
    }
    render (){

        let btn='btn disabled btn-outline-'+this.props.threme;
        let b=[]
        this.props.list.forEach(a=>{
            b.push(
                <button className={btn} key={a}>{a}</button>
            )
        })
        return (
            <div className={"card"} style={{border:'0'}}>
                <div className={"card-header"} style={{border:'0'}}>{this.props.cat}</div>
                <div className={"card-text"}>
                {b}
                </div>
            </div>
        )
    }
}

class LimitQList extends Component {
    constructor(props) {
        super(props)
        this.state = {
            questions: this.props.qs.all || []
        }

        if (!this.props.qs.all) {
            this.props.allQuestion({data: {size: 50}})
        }
    }

    componentWillReceiveProps(nextProps, nextContext) {
        this.setState({
            questions: nextProps.qs.all || {}
        })
    }


    render() {
        let p = []
        for (let i = 0; i < this.state.questions.length; i++) {
            let t = this.state.questions[i];
            p.push(
                <Link to={"/pass/" + t.id} key={t.id} className={"btn"} style={{display: 'inline-block'}}>
                    <Qi2 name={t.name} id={t.id} level={t.level}>
                    </Qi2>
                </Link>
            )
        }
        let bid = bound(this.state.questions)
        return (
            <div className={"container-fluid"}>
                <div className={"row no-gutters"}>

                    <div className={"col-sm-12"}>


                        <Scroller height={600} more={p.length > 9}
                                  onBottom={() => this.props.allQuestion({
                                      data: {
                                          size: 10,
                                          maxId: bid.maxId,
                                          minId: bid.minId
                                      }
                                  })}>
                            <span>
                                {p}
                            </span>
                        </Scroller>

                        <div>

                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

const mapStateToProps = (state, ownProps) => {
    const ld = state.login;
    const qs = state.questions || {}

    return {ld, qs}
}

export default withRouter(connect(mapStateToProps, {allQuestion})(LimitQList))
