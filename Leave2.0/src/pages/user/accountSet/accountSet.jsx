import React, { Component } from 'react'
import { Form, Input, Button, Select, Radio } from 'antd';
import { UserOutlined } from '@ant-design/icons'
const { Option } = Select;
const layout = {
    labelCol: {
        span: 3,
    },
    wrapperCol: {
        span: 14,
    },
};
const tailLayout = {
    wrapperCol: {
        offset: 3,
        span: 16,
    },
};


export default class AccountSet extends Component {
    state = {
        value: 1,
    };
    formRef = React.createRef();

    onChange = e => {
        console.log('radio checked', e.target.value);
        this.setState({
            value: e.target.value,
        });
    };

    onFinish = values => {
        console.log(values);
    };

    onReset = () => {
        this.formRef.current.resetFields();
    };

    onFill = () => {
        this.formRef.current.setFieldsValue({
            note: 'Hello world!',
            gender: 'male',
        });
    };
    render() {
      
        return (
            <div>
                <h1><UserOutlined style={{paddingRight:10}}/>账户设置</h1>
                <Form style={{paddingTop:30}} {...layout} ref={this.formRef} name="control-ref" onFinish={this.onFinish}>
                    <Form.Item
                        label="姓名"
                        name="username"
                        rules={[{ required: true, message: 'Please input your username!' }]}>
                        <Input />
                    </Form.Item>

                    <Form.Item 
                        label="性别"
                        name="gender"
                        rules={[{required:true,message:'Please select you gender!'}]}>
                        <Radio.Group onChange={this.onChange} value={this.state.value}>
                            <Radio value={1}>男</Radio>
                            <Radio value={2}>女</Radio>
                        </Radio.Group>
                    </Form.Item>
                    
                    <Form.Item
                        label="班级"
                        name="stuClass"
                        rules={[{ required: true, message: 'Please input your class!' }]}>
                        <Input />
                    </Form.Item>

                    <Form.Item
                        label="一卡通号"
                        name="stuID"
                        rules={[{ required: true, message: 'Please input your IdCard!' }]}>
                        <Input />
                    </Form.Item>

                    <Form.Item
                        name="password"
                        label="原密码"
                        rules={[
                            {
                                required: true,
                                message: 'Please input your old password!',
                            },
                        ]}
                        hasFeedback>
                        <Input.Password />
                    </Form.Item>

                    <Form.Item
                        name="password"
                        label="新密码"
                        rules={[
                            {
                                required: true,
                                message: 'Please input your new password!',
                            },
                        ]}
                        hasFeedback>
                        <Input.Password />
                    </Form.Item>

                    <Form.Item
                        name="confirm"
                        label="确认新密码"
                        dependencies={['password']}
                        hasFeedback
                        rules={[
                            {
                                required: true,
                                message: 'Please confirm your new password!',
                            },
                            ({ getFieldValue }) => ({
                                validator(rule, value) {
                                    if (!value || getFieldValue('password') === value) {
                                        return Promise.resolve();
                                    }
                                    return Promise.reject('The two passwords that you entered do not match!');
                                },
                            }),
                        ]} >
                        <Input.Password />
                    </Form.Item>
             
                    <Form.Item {...tailLayout}>
                        <Button type="primary" htmlType="submit" style={{marginRight:8}}>
                            Submit
                        </Button>
                        <Button htmlType="button" onClick={this.onReset}>
                            Reset
                        </Button>
                    </Form.Item>
                </Form>
            </div>
        )
    }
}
