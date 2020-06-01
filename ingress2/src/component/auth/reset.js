import {Button, Col, Form, Input, Row} from "antd";
import React, {useState} from "react";
import {formItemLayout,tailFormItemLayout} from "./register";
import {CountDown} from "./register";
import {post} from "../common/network";

export const Reset=(props)=>{
    const [form]=Form.useForm();
    const [can,setCan]=useState(false)
    const [msgs,setMsgs]=useState({})

    const onFinish = values => {
        var ro={
            pwd:form.getFieldValue("pwd"),
            email:form.getFieldValue("email"),
            kapcha:"",
            confirm:form.getFieldValue("confirm")
        }
        post(props.authUrl||"/auth/reset",ro,(d)=>{
            if(!d.success){
                setMsgs(d.msgs)
            }else{
                props.history.push("/login")
            }
        })
    };
    const valueChange=(changed,all)=>{
        let key="email"
        if(!(key in changed)) return;
        let e=form.getFieldError(key);
        let c=e.length===0
        if(c!==can){
            setCan(c)
        }
    }
    const getVStatus=(key)=>{
        if(msgs&&(key in msgs)) return "error";
        return "success";
    }
    const getVHelp=(key)=>{
        if(msgs&&(key in msgs)) return msgs[key]
        return null;
    }
    return (
        <Form
            {...formItemLayout}
            form={form}
            name="register"
            onFinish={onFinish}
            scrollToFirstError
            hideRequiredMark
            initialValues={{agreement:true}}
            onValuesChange={valueChange}
        >
            <Form.Item
                name="email"
                label="电子邮件"
                validateStatus={getVStatus("email")}
                help={getVHelp("email")}
                rules={[
                    {
                        type: 'email',
                        message: '电子邮件格式错误',
                    },
                    {
                        required: true,
                        message: '电子邮件不能为空',
                    },
                ]}
            >
                <Input />
            </Form.Item>
            <Form.Item label="邮箱验证码" extra="填入邮箱收到的验证码">
                <Row gutter={8}>
                    <Col span={12}>
                        <Form.Item
                            name="confirm"
                            validateStatus={getVStatus("confirm")}
                            help={getVHelp("confirm")}
                            rules={[
                                {
                                    required: true,
                                    message: '填入邮箱收到的验证码',
                                },
                            ]}
                        >
                            <Input />
                        </Form.Item>
                    </Col>
                    <Col span={12}>
                        <CountDown can={can} email={form.getFieldValue("email")||""}/>
                    </Col>
                </Row>
            </Form.Item>
            <Form.Item
                name="pwd"
                label="新密码"
                rules={[
                    {
                        required: true,
                        message: '密码不能为空',
                    },
                ]}
                hasFeedback
            >
                <Input.Password />
            </Form.Item>

            <Form.Item
                name="pwd2"
                label="确认密码"
                dependencies={['pwd']}
                hasFeedback
                rules={[
                    {
                        required: true,
                        message: '密码不能为空',
                    },
                    ({ getFieldValue }) => ({
                        validator(rule, value) {
                            if (!value || getFieldValue('pwd') === value) {
                                return Promise.resolve();
                            }

                            return Promise.reject('密码不一致');
                        },
                    }),
                ]}
            >
                <Input.Password />
            </Form.Item>
            <Form.Item {...tailFormItemLayout}>
                <Button type="primary" htmlType="submit">
                    重置密码
                </Button>
                <a href={"#/login"} style={{marginLeft:"100px"}}>登录</a>
            </Form.Item>
        </Form>
    )
}