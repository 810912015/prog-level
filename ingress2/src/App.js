import React, { Component } from 'react';
import {Button, Col, Layout, Row} from 'antd';
import './App.css';
import {QuestionBag} from "./component/question";
import {HashRouter} from "react-router-dom";

class App extends Component {
  render() {
    return (
        <Layout className={"layout"} style={{padding:"0px 10px"}}>
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
                        <Col xs={24} sm={24} md={8} ></Col>
                        <Col xs={24} sm={24} md={8} ><QuestionBag/></Col>
                        <Col xs={24} sm={24} md={8} ></Col>
                    </Row>

                </HashRouter>
            </Layout.Content>
            <Layout.Footer>
            </Layout.Footer>
        </Layout>

    );
  }
}

export default App;