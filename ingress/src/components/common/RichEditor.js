import React from 'react'
import {
    EditorState, Editor, RichUtils, getDefaultKeyBinding, ContentState,
    convertToRaw, convertFromRaw, convertFromHTML,CompositeDecorator
} from 'draft-js'
import {stateToHTML} from 'draft-js-export-html';

import {
    BoldIcon,
    H1Icon,
    H2Icon,
    H3Icon,
    LinkIcon,
    ImageIcon,
    ItalicIcon,
    OlIcon,
    UlIcon,
    UnderlineIcon, BlockquoteIcon, CodeIcon
} from "./icon";





const init = (o,d=null) => {
    let o1 = null;
    try {
        o1 = convertFromHTML(o);
    } catch (e) {

    }

    let es = null;
    if (o1 != null && o1.contentBlocks != null && o1.entityMap != null) {
        let state = ContentState.createFromBlockArray(o1.contentBlocks,
            o1.entityMap)
        es = EditorState.createWithContent(state,d)
    }

    if (es == null) {
        es = EditorState.createEmpty(d);
    }
    return es;
}

const fire = (e, f) => {
    if (typeof f === 'function') {
        let html = stateToHTML(e.getCurrentContent());
        f(html)
    }
}


function findLinkEntities(contentBlock, callback, contentState) {
    contentBlock.findEntityRanges(
        (character) => {
            const entityKey = character.getEntity();
            return (
                entityKey !== null &&
                contentState.getEntity(entityKey).getType() === 'LINK'
            );
        },
        callback
    );
}

const Link = (props) => {
    const {url} = props.contentState.getEntity(props.entityKey).getData();
    return (
        <a href={url}>
            {props.children}
        </a>
    );
};
const Image = (props) => {
    return <img src={props.src} />;
};

function ImageBlockRenderer(block) {
    if (block.getType() === 'atomic') {
        return {
            component: Image,
            editable: false,
        };
    }
    if(block.getType()==='blockquote') {
        return 'RichEditor-blockquote';
    }

    return null;
}

const decorator = new CompositeDecorator([
    {
        strategy: findLinkEntities,
        component: Link,
    },
]);

export class RichEditor extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            editorState: init(this.props.value,decorator),
            inited: false,
            cmd: {
                name: '',
                count: '',
                value: '',
            }
        };


        this.focus = () => this.refs.editor.focus();

        this.handleKeyCommand = this._handleKeyCommand.bind(this);
        this.mapKeyToEditorCommand = this._mapKeyToEditorCommand.bind(this);
        this.toggleBlockType = this._toggleBlockType.bind(this);
        this.toggleInlineStyle = this._toggleInlineStyle.bind(this);
    }

    componentWillReceiveProps(nextProps, nextContext) {
        if (!this.state.inited)
            this.setState({editorState: init(nextProps.value,decorator), inited: true})
    }

    onChange = (editorState) => {
        fire(editorState, this.props.change)
        this.setState({editorState});
    }

    _handleKeyCommand(command, editorState) {
        const newState = RichUtils.handleKeyCommand(editorState, command);
        if (newState) {
            this.onChange(newState);
            return true;
        }
        return false;
    }

    _mapKeyToEditorCommand(e) {
        if (e.keyCode === 9 /* TAB */) {
            const newEditorState = RichUtils.onTab(
                e,
                this.state.editorState,
                4, /* maxDepth */
            );
            if (newEditorState !== this.state.editorState) {
                this.onChange(newEditorState);
            }
            return;
        }
        return getDefaultKeyBinding(e);
    }

    _toggleBlockType(blockType) {
        this.onChange(
            RichUtils.toggleBlockType(
                this.state.editorState,
                blockType
            )
        );
    }

    _toggleInlineStyle(inlineStyle) {
        this.onChange(
            RichUtils.toggleInlineStyle(
                this.state.editorState,
                inlineStyle
            )
        );
    }

    onCmd = (cmd) => {
        let oc=Object.assign({}, this.state.cmd);
        if(oc.name===cmd){
            oc.count+=1;
            if(oc.count>1) oc.count=0;
        }else{
            oc.name=cmd;
            oc.count=1;
            oc.value='';
        }
        if(cmd==="链接"){
            const selection=this.state.editorState.getSelection();
            if(!selection.isCollapsed()){
                const cs=this.state.editorState.getCurrentContent();
                const sk=selection.getStartKey();
                const so=selection.getStartOffset();
                const b=cs.getBlockForKey(sk);
                const lk=b.getEntityAt(so);
                if(lk){
                    const li=cs.getEntity(lk)
                    oc.value=li.getData().url;
                }
            }
        }
        this.setState({cmd:oc})
    }
    onConfirm = () => {
        const es=this.state.editorState;
        const uv=this.state.cmd.value;
        if(uv.length>0) {
            const cs = es.getCurrentContent();
            const e = cs.createEntity('LINK', 'MUTABLE', {url: uv});
            const ek = e.getLastCreatedEntityKey();
            const nes = EditorState.set(es, {currentContent: e})
            const fes = RichUtils.toggleLink(nes, nes.getSelection(), ek);
            this.setState({editorState: fes},
                () => setTimeout(() => this.refs.editor.focus(), 0))
        }else{
            this.setState({
                editorState:RichUtils.toggleLink(es,es.getSelection(),null)})
        }

    }

    onCmdChange = (e) => {
        let oc=Object.assign({}, this.state.cmd);
        oc.value=e.target.value;
        this.setState({cmd:oc});
    }

    render() {
        const {editorState} = this.state;

        // If the user changes block type before entering any text, we can
        // either style the placeholder or hide it. Let's just hide it now.
        let className = 'RichEditor-editor';
        var contentState = editorState.getCurrentContent();
        if (!contentState.hasText()) {
            if (contentState.getBlockMap().first().getType() !== 'unstyled') {
                className += ' RichEditor-hidePlaceholder';
            }
        }
        let cb = null;
        if (this.state.cmd.count === 1) {
            cb = (
                <CmdBody value={this.state.cmd.value} change={this.onCmdChange}
                         confirm={this.onConfirm}
                         tag={this.state.cmd.name}
                />
            )
        }
        return (
            <div className="container border">
                <BlockStyleControls
                    editorState={editorState}
                    onToggle={this.toggleBlockType}
                />
                <InlineStyleControls
                    editorState={editorState}
                    onToggle={this.toggleInlineStyle}
                />
                <CmdControls onCmd={this.onCmd}/>
                {cb}
                <div className={"border-top p-3"} onClick={this.focus}>
                    <Editor
                        blockStyleFn={ImageBlockRenderer}
                        customStyleMap={styleMap}
                        editorState={editorState}
                        handleKeyCommand={this.handleKeyCommand}
                        keyBindingFn={this.mapKeyToEditorCommand}
                        onChange={this.onChange}

                        ref="editor"
                        spellCheck={false}
                    />
                </div>
            </div>
        );
    }
}

// Custom overrides for "code" style.
const styleMap = {
    CODE: {
        backgroundColor: 'rgba(0, 0, 0, 0.05)',
        fontFamily: '"Inconsolata", "Menlo", "Consolas", monospace',
        fontSize: 16,
        padding: 2,
    },
};

function getBlockStyle(block) {
    switch (block.getType()) {
        case 'blockquote':
            return 'RichEditor-blockquote';
        default:
            return null;
    }
}

class StyleButton extends React.Component {
    constructor() {
        super();
        this.onToggle = (e) => {
            e.preventDefault();
            this.props.onToggle(this.props.style);
        };
    }

    render() {
        let svg = null;
        switch (this.props.label) {
            case "标题一":
                svg = (<H1Icon/>)
                break;
            case "标题二":
                svg = (<H2Icon/>)
                break;
            case "标题三":
                svg = (<H3Icon/>)
                break;
            case "无序列表":
                svg = (<UlIcon/>)
                break;
            case "有序列表":
                svg = (<OlIcon/>)
                break;
            case "Blockquote":
                svg = (<BlockquoteIcon/>)
                break;
            case "Code Block":
                svg = (<CodeIcon/>)
                break;
            case "粗体":
                svg = (<BoldIcon/>)
                break;
            case "斜体":
                svg = (<ItalicIcon/>)
                break;
            case "下划线":
                svg = (<UnderlineIcon/>)
                break;
            case "链接":
                svg = (<LinkIcon/>)
                break;
            case "图片":
                svg = (<ImageIcon/>)
                break;
            default:
                break;
        }

        let className = 'btn btn-link';
        if (this.props.active) {
            className += ' border ';
        }

        return (
            <span className={className}
                  onMouseDown={this.onToggle} title={this.props.label}>
                {svg}
            </span>
        );
    }
}

const BLOCK_TYPES = [
    {label: '标题一', style: 'header-one'},
    {label: '标题二', style: 'header-two'},
    {label: '标题三', style: 'header-three'},
    {label: 'Blockquote', style: 'blockquote'},
    {label: '无序列表', style: 'unordered-list-item'},
    {label: '有序列表', style: 'ordered-list-item'},
    {label: 'Code Block', style: 'code-block'},
];

const BlockStyleControls = (props) => {
    const {editorState} = props;
    const selection = editorState.getSelection();
    const blockType = editorState
        .getCurrentContent()
        .getBlockForKey(selection.getStartKey())
        .getType();

    return (
        <div className="btn-group">
            {BLOCK_TYPES.map((type) =>
                <StyleButton
                    key={type.label}
                    active={type.style === blockType}
                    label={type.label}
                    onToggle={props.onToggle}
                    style={type.style}
                />
            )}
        </div>
    );
};

var INLINE_STYLES = [
    {label: '粗体', style: 'BOLD'},
    {label: '斜体', style: 'ITALIC'},
    {label: '下划线', style: 'UNDERLINE'}
];

const InlineStyleControls = (props) => {
    const currentStyle = props.editorState.getCurrentInlineStyle();

    return (
        <div className="btn-group">
            {INLINE_STYLES.map((type) =>
                <StyleButton
                    key={type.label}
                    active={currentStyle.has(type.style)}
                    label={type.label}
                    onToggle={props.onToggle}
                    style={type.style}
                />
            )}
        </div>
    );
};

const CMDS = [
    {label: '链接', cmd: 'link'},
    {label: '图片', cmd: 'img'}
]

const CmdBody = (props) => {
    return (
        <div className={"input-group input-group-sm"}>
            <div className="input-group-prepend">
                <span className="input-group-text" id="inputGroup-sizing-sm">{props.tag}</span>
            </div>

            <input className={"form-control"} value={props.value} onChange={props.change}/>

            <div className={"input-group-append"}>

                <button className={"btn btn-sm btn-outline-info"}
                        onClick={props.confirm} title={"确认"}>√
                </button>
            </div>
        </div>
    )
}

const CmdControls = (props) => {
    let ba = []
    CMDS.map((cmd) => {
            ba.push(
                <StyleButton key={cmd.label}
                             active={false}
                             onToggle={() => props.onCmd(cmd.label)}
                             label={cmd.label}
                />
            );
        }
    )
    return (
        <div className={"btn-group"}>
            {ba}
        </div>
    )
}

