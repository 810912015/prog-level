import React, { Component } from 'react';
import {Button, Col, Layout, Row} from 'antd';
import './App.css';
import {QuestionBag, RecommendBag, TagBag} from "./component/question";
import {HashRouter} from "react-router-dom";


class App extends Component {
  render() {
    return (
        <Layout className={"layout"}>
            <Layout.Header style={{backgroundColor:"transparent"}}>
                <div style={{display:"flex"}}>
                    <div  className={"main-brand"}>
                        分码网
                    </div>
                    <div className={"brand"}>
                        一个你可以写代码的地方
                    </div>
                    <div className={"shortcut"}>
                        <span>登录/注册</span>
                    </div>
                </div>
            </Layout.Header>
            <Layout.Content>
                <HashRouter>
                    <Row>
                        <Col xs={24} sm={24} md={{span:3,offset:2}}><TagBag/></Col>
                        <Col xs={24} sm={24} md={13} ><QuestionBag/></Col>
                        <Col xs={24} sm={24} md={6} >
                            {<RecommendBag/>}
                        </Col>
                    </Row>

                </HashRouter>
            </Layout.Content>
            <Layout.Footer>
                <div style={{textAlign:"center",backgroundColor:"#888",color:"#eee",padding:"10px"}}>
                    <div className={"space"}>上海信息科技有限公司</div>
                    <div className={"space"}>沪icp备45862号</div>
                </div>
            </Layout.Footer>
        </Layout>

    );
  }
}

export default App;