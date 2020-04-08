import React, { Component } from 'react';
import { Button,Layout } from 'antd';
import './App.css';

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

                    <Button type="primary">Button</Button>

            </Layout.Content>
            <Layout.Footer>
            </Layout.Footer>
        </Layout>

    );
  }
}

export default App;