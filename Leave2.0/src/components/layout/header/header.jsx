import React, { Component } from 'react'
import { Layout, Menu ,Dropdown} from 'antd';
import { FullscreenOutlined, DownOutlined  } from '@ant-design/icons';
import "./header.css"
const { Header } = Layout;
const signOutMenu = (
    <Menu>
        <Menu.Item>
            <a target="_blank" rel="noopener noreferrer" href="#">
                退出登录
            </a>
        </Menu.Item>
    </Menu>
);

export default class HeaderBar extends Component {
    render() {
        return (
            <Header className="header">
                <div className="app-name">请假管理系统</div>
                <Menu className="app-nav" theme="dark" mode="horizontal" defaultSelectedKeys={['2']}>
                    <Menu.Item key="1">nav 1</Menu.Item>
                    <Menu.Item key="2">nav 2</Menu.Item>
                    <Menu.Item key="3">nav 3</Menu.Item>
                    <Menu.Item key="4"><FullscreenOutlined />开启全屏</Menu.Item>
                    <Menu.Item key="5">
                        <Dropdown overlay={signOutMenu}>
                            <a className="ant-dropdown-link" style={{ color: '#ccc' }} onClick={e => e.preventDefault()}>
                                Sign Out<DownOutlined />
                            </a>
                        </Dropdown>
                    </Menu.Item>
                </Menu>
            </Header>
        )
    }
}
