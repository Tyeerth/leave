import React, { Component } from 'react';
import { Form, Row,Col,Input, Button,Select} from 'antd';
import Axios from '../../utils/request';
import { Redirect } from 'react-router-dom';
import axios from 'axios'
import {login_about} from '../../redux/actions'
import {connect} from 'react-redux'

import './login.css';
const { Option } = Select;

class Login extends Component {
    state={
        captchaImg:'data:image/jpeg;base64,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',
        userName: '',
        password: '',
        userRole: '',
    }

    componentDidMount() {
        console.log("login props:",this.props.loginInfo); 
        //获取图形验证码
        console.log('获取验证码');
        axios.get('/student/checkCode')
            .then((res) => {
                console.log(res);
            })
            .catch((err) => {
                console.log(err);
            })
    }

    //更换验证码
    changeCaptcha=()=>{
        console.log('更换验证码');
        axios.get('/student/checkCode')
        .then((res)=>{
            console.log(res);
        })
        .catch((err)=>{
            console.log(err);
        })
    }

    //登录
    handleSubmit = e => {
        alert("提交事件");
        const loginInfo={
            userName:this.state.userName,
            password:this.state.password,
            userRole:this.state.userRole
        }
        this.props.login_about(loginInfo)
        // axios.post('/student/login',{
        //     params: {
        //        username:this.state.username,
        //        password:this.state.password,
        //        captcha:''
        //     }
        // })
        // .then((res)=>{
        //     console.log('登录成功:',res);
        // })
        // .catch((err)=>{
        //     console.log('登录失败:',err);
        // })
    };

    uNameInput=(e)=>{
        console.log("用户名是",e.target.value.trim());
        const userName=e.target.value.trim();
        this.setState({userName})
    }
    pwdInput=(e)=>{
        console.log('密码是:',e.target.value.trim());
        const password=e.target.value.trim();
        this.setState({password})
    }
    onGenderChange=(e)=>{
        console.log('身份是:',e);
        this.setState({
            userRole:e
        })
    }

    render() {
        return (
          <div className="login-container">
            <Form className="login-form">
              <div className="sub-title">登 录</div>

                 <Form.Item label="Username"
                            name="username"
                            rules={[{ required: true, message: 'Please input your username!' }]} >
                       <Input placeholder="Input your username" onChange={this.uNameInput} />
                 </Form.Item>

                 <Form.Item 
                          label="Password"
                          name="password"
                          rules={[{ required: true, message: 'Please input your password!' }]}>
                        <Input.Password placeholder="Input your password" style={{marginLeft:5}} onChange={this.pwdInput}/>
                 </Form.Item>

                 <Form.Item name="role" label="Role" rules={[{ required: true }]}>
                      <Select
                           style={{paddingLeft:36}}
                           placeholder="Select a role"
                           onChange={this.onGenderChange}
                           allowClear>
                           <Option value="student">学生</Option>
                           <Option value="teacher">教师</Option>
                           <Option value="admin">管理员</Option>
                       </Select>
                 </Form.Item>

                 <Form.Item label="Captcha" name="captcha" rules={[{ required: true }]} extra="We must make sure that your are a human.">
                     <Row gutter={12}>
                         <Col span={12}>
                             <Form.Item
                                 name="captcha"
                                 noStyle
                                 rules={[{ required: true, message: 'Please input the captcha you got!' }]}>
                                 <Input style={{ marginLeft: 13 }}/>
                             </Form.Item>
                          </Col>
                         <Col span={1}>
                             <div style={{ marginLeft: 20 }} onClick={this.getCaptcha}>
                                 <img style={{ height: 32,width:110}} src={this.state.captchaImg} alt="" />
                             </div>
                              {/* <Button style={{marginLeft:20}} onClick={this.getCaptcha}>Get captcha</Button> */}
                         </Col>
                     </Row>
                 </Form.Item>

                 <Form.Item >
                      <Button onClick={this.handleSubmit} type="primary" htmlType="submit" className="login-form-button">登录</Button>
                 </Form.Item>
            </Form>
          </div>
        )
    }
}

export default connect(
    state => ({
        userName: state.Login.loginInfo.userName,
        password: state.Login.loginInfo.password,
        userRole: state.Login.loginInfo.userRole
    }), 

    { login_about } //login_about:f()
)(Login)
