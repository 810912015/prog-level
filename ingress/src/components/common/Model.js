import React from 'react';
import PropTypes from 'prop-types';
import classNames from 'classnames';

let modalsShowing = 0;

function modalWillShow() {
    if (modalsShowing === 0 && document) {
        document.body.classList.add('modal-open');
    }

    modalsShowing += 1;
}

function modalWillHide() {
    modalsShowing -= 1;

    if (modalsShowing === 0 && document) {
        document.body.classList.remove('modal-open');
    }
}

class Modal extends React.Component {
    static propTypes = {
        children: PropTypes.node.isRequired,
        onClickBackdrop: PropTypes.func,
        visible: PropTypes.bool.isRequired,
        wrapperProps: PropTypes.object, // eslint-disable-line react/forbid-prop-types
        className: PropTypes.string,
        dialogClassName: PropTypes.string,
        fade: PropTypes.bool,
    };

    static defaultProps = {
        onClickBackdrop: null,
        wrapperProps: null,
        className: null,
        dialogClassName: null,
        fade: true,
    };

    constructor(props) {
        super(props);

        this.state = {
            visible: this.props.visible,
            modalIndex: 0,
        };
    }

    componentWillMount = () => {
        if (this.props.visible) {
            modalWillShow();
        }
    }

    // Shenanigans to allow the CSS fade to happen before we stop rendering the dialog or divs
    componentDidUpdate = (prevProps) => {
        if (this.props.visible !== prevProps.visible) {
            if (this.props.visible) {
                modalWillShow();
            } else {
                modalWillHide();
            }

            if (this.props.fade) {
                this.setState({ transitioning: true, modalIndex: modalsShowing }, () => {
                    window.setTimeout(() => {
                        this.setState({ visible: this.props.visible }, () => {
                            window.setTimeout(() => { this.setState({ transitioning: false }); }, 150);
                        });
                    }, 16); // I don't like this magic number but I haven't found a better way
                });
            } else {
                this.setState({ visible: this.props.visible });
            }
        }
    }

    componentWillUnmount = () => {
        if (this.props.visible) {
            modalWillHide();
        }
    }

    stopPropagation = (event) => {
        event.stopPropagation();
    }

    renderBackdrop = () => {
        if (this.state.visible || this.state.transitioning) {
            return (
                <div
                    className={classNames('modal-backdrop', { show: this.state.visible, fade: this.props.fade })}
                    onClick={this.props.onClickBackdrop}
                    role="presentation"
                    style={{ zIndex: 1040 + this.state.modalIndex }}
                />
            );
        }

        return null;
    }

    render = () => {
        const {
            wrapperProps,
            className,
            dialogClassName,
            visible,
            onClickBackdrop,
            children,
            fade,
            ...other
        } = this.props;

        return (
            <div
                {...wrapperProps}
            >
                <div
                    className={classNames('modal', { show: this.state.visible, fade: this.props.fade }, className)}
                    style={{
                        display: ((this.state.visible || this.state.transitioning) ? 'block' : 'none'),
                        zIndex: 1040 + this.state.modalIndex + 1,
                    }}
                    role="dialog"
                    aria-hidden={!this.state.visible}
                    tabIndex="-1"
                    onClick={onClickBackdrop}
                    {...other}
                >
                    <div className={classNames('modal-dialog', dialogClassName)} role="document" onClick={this.stopPropagation}>
                        <div className="modal-content">
                            {children}
                        </div>
                    </div>
                </div>
                {this.renderBackdrop()}
            </div>
        );
    }
}

export class SimpleModal extends React.Component{
    state={
        visible:false
    }

    hide=()=>this.setState({visible:false})
    show=()=>this.setState({visible:true})
    doAct=()=>{
        if(typeof this.props.action==='function'){
            //action返回true表示关闭框,否则不关
            let r=this.props.action();
            if(!r){
                this.hide();
            }
        }else{
            this.hide();
        }
    }
    render() {
        return (
            <div className={"d-inline-block"}>
                <button className={this.props.cmdClass} onClick={this.show}>{this.props.cmdTip}</button>
            <Modal visible={this.state.visible} onClickBackdrop={this.hide}>
                <div className="modal-header">
                    <h5 className="modal-title">{this.props.title}</h5>
                    <button type="button" className="close" onClick={this.hide}>
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div className="modal-body">
                    {this.props.children}
                </div>
                <div className="modal-footer">
                    <button type="button" className="btn btn-secondary" onClick={this.hide}>{this.props.cancelTip}</button>
                    <button type="button" className="btn btn-primary" onClick={this.doAct}>
                        {this.props.actionTip}
                    </button>
                </div>
            </Modal>
            </div>
        )
    }

    static propTypes={
        //打开modal的按钮样式
        cmdClass:PropTypes.string,
        //打开按钮文字
        cmdTip:PropTypes.string,
        //modal标题
        title:PropTypes.string.isRequired,
        //取消按钮文字
        cancelTip:PropTypes.string,
        //确认按钮文字
        actionTip:PropTypes.string,
        //执行的操作.返回值为false表示关闭modal
        action:PropTypes.func
    }

    static defaultProps={
        cancelTip:'取消',
        actionTip:'确认',
        cmdClass:'btn btn-sm btn-outline-danger',
        cmdTip:'删除'
    }
}

export class ModalTest extends React.Component{
    render = () => (
            <SimpleModal title={"test"}>
                <div>tests</div>
            </SimpleModal>
    )
}
