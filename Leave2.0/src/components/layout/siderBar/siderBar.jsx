import React, { Component } from 'react'
import { Layout, Menu} from 'antd';
import {HomeOutlined} from '@ant-design/icons';
import menuConfig from '../../../routes/menu'
import {NavLink} from 'react-router-dom'

import { connect } from 'react-redux'
import {menu_select} from '../../../redux/actions'

const { SubMenu } = Menu;
const { Sider } = Layout;

class SiderBar extends Component {
    state={
        menuList:[],
    }


    componentWillMount() {
        this.defaultSelectAndOpen();
        const menuList = this.handleMenuList(menuConfig);
        this.setState({ menuList });
        //console.log("siderbar props:",this.props);
    }

    //处理菜单列表
    handleMenuList=(menuList)=>{
        return menuList.map((item)=>{
            if(item.children){ //如果有二级路由，使用SubMenu标签
                return (
                <SubMenu key={item.key} title={<span>{item.icon}{item.title}</span>}>
                       {this.handleMenuList(item.children)} {/*递归处理二级路由菜单*/}
                 </SubMenu>
                )
            }
            else{
                return (
                   <Menu.Item title={item.title} key={item.key}>
                       <NavLink to={item.key}>
                         {item.icon}
                         {item.title}
                       </NavLink>
                   </Menu.Item>
                )
            }
        })
    }

    //处理侧边栏菜单默认选中和默认展开
    defaultSelectAndOpen=()=>{
        let menuConfig_Keys = [];
        menuConfig.forEach((item)=>{
            menuConfig_Keys.push(item.key);//item.key：一级路由的key
            if(item.children){
                item.children.forEach((subitem)=>{
                    menuConfig_Keys.push(subitem.key);
                })
            }
        });
        console.log("menuConfig_Keys:",menuConfig_Keys);//21条
        const pathname=window.location.pathname; // pathname:如 /student/leaveApply
        //console.log("pathname:",pathname);
        const currentKey='/'+pathname.split('/')[1]; //currentKey:如 /student
        //console.log("currentKey:", currentKey);
        if(menuConfig_Keys.indexOf(currentKey)>=1){
            //console.log("匹配");
            this.props.menu_select([currentKey],[pathname])
        }
    }

    //点击菜单
    clickSider=(e)=>{
        //console.log("点击菜单栏：",e);
        //console.log(e.keyPath[0]); // 如：/student/leaveApply
        //console.log(e.keyPath[1]); // 如：/student
        if(e.keyPath.length===2){ //有二级路由时
            this.props.menu_select([e.keyPath[1]] , [e.keyPath[0]])
        }
        else{
            this.props.menu_select([e.keyPath[0]])
        }
    }

    render() {
        return (
            <div>
                <Sider width={200} style={{ background: '#fff'}}>
                    <Menu
                         onClick={this.clickSider}
                         defaultSelectedKeys={this.props.defaultSelectedKeys}
                         defaultOpenKeys={this.props.defaultOpenKeys}
                         mode="inline">
                       {this.state.menuList}
                    </Menu>
                </Sider>
            </div>
        )
    }
}


export default connect(
    state => ({ 
        defaultOpenKeys: state.Menu.defaultOpenKeys,
        defaultSelectedKeys:state.Menu.defaultSelectedKeys
     }), //defaultOpenKeys: ["/"], defaultSelectedKeys:['/']

    { menu_select} //menu_select:f()
)(SiderBar)
