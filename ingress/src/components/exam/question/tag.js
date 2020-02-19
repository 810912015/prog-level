import PropTypes from "prop-types";
import React, {Component} from "react";


export class Tag extends Component {
    static propTypes = {
        value: PropTypes.array.isRequired,
        change: PropTypes.func.isRequired
    }
    constructor(props){
        super(props)
        this. state = {
            tag: '',
            tags: props.value || []
        }
    }
    componentWillReceiveProps(nextProps, nextContext) {
        this.setState({
            tags:nextProps.value
        })
    }

    change = (e, w) => {
        this.setState({
            [w]: e.target.value
        })
    }
    keyUp = (e, w) => {
        if (w === 'tag' && e.key === 'Enter') {
            this.add()
        }
    }
    add = () => {
        if (this.state.tags.indexOf(this.state.tag) < 0) {
            let t = this.state.tags
            t.push(this.state.tag)
            this.setState({tags: t, tag: ''})
            this.props.change(t)
        }
    }
    delTag = (t) => {
        let i = this.state.tags.indexOf(t);
        if (i > -1) {
            let s = this.state.tags;
            s.splice(i, 1)
            this.setState({tags: s})
            this.props.change(s);
        }
    }

    render() {
        let ta = [];
        for (let i = 0; i < this.state.tags.length; i++) {
            let t = this.state.tags[i]

            ta.push(
                <span key={t} className={"m-1 p-2 border d-inline-block"}>
                <span>{t}</span>
                    <span className={"ml-2"} style={{cursor: 'pointer'}} onClick={() => this.delTag(t)}>X</span>
                </span>
            )
        }
        return (
            <div className={"form-row"}>
                <div className={"col-4"}>
                    <label>标签</label>
                    <div className={"input-group"}>
                        <input value={this.state.tag} onChange={(e) => this.change(e, 'tag')}
                               className={"form-control"} onKeyUp={(e) => this.keyUp(e, 'tag')}
                        />
                        <div className="input-group-append">
                            <button className="btn btn-outline-secondary" type="button" onClick={this.add}>+
                            </button>
                        </div>
                    </div>

                </div>
                <div className={"col-8"}>
                    <div className={"p-2"}>
                        {ta}
                    </div>
                </div>
            </div>
        )
    }
}
