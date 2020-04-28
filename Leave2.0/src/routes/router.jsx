import React from 'react';
import { Layout, Breadcrumb } from 'antd';
import { Route, Switch} from 'react-router-dom';
import SiderBar from "../components/layout/siderBar/siderBar";
import HeaderBar from "../components/layout/header/header";
import Home from '../pages/home/home';
import NotFound from '../pages/404/404';
import AccountSet from '../pages/user/accountSet/accountSet';
import StuApply from '../pages/user/leaveMaster/leaveApply/stuApply';
import TeacherApply from '../pages/user/leaveMaster/leaveApply/teacherApply';
import LeaveDel from '../pages/user/leaveMaster/leaveDel/leaveDel';
import StuModern from '../pages/user/leaveMaster/leaveModern/stuModern';
import TeacherModern from '../pages/user/leaveMaster/leaveModern/teacherModern';
import LeaveStatus from '../pages/user/leaveStatus/leaveStatus';
import {connect} from 'react-redux'
import {menu_select} from '../redux/actions'
import UserInfo from '../pages/user/userinfo/userinfo'
import menuConfig from '../routes/menu';
const {Content, Footer } = Layout;

class MyRouter extends React.Component {
  componentDidMount(){
    console.log("router props:",this.props); 
  }
    render() {
        return (
          <Layout>
             <HeaderBar> </HeaderBar>
             <Content style={{ padding: '0 50px' }}>
               <Breadcrumb style={{ margin: '16px 0' }}>
                <Breadcrumb.Item >
                  {this.props.defaultSelectedKeys}
                </Breadcrumb.Item>
               </Breadcrumb>
              <Layout  style={{ padding: '24px 0', background: '#fff' }}>
                  <SiderBar></SiderBar>
                  <Content style={{ padding: '0 24px', minHeight: 280 }}>
                       <Switch>
                          <Route exact path="/" component={Home} />
                          <Route exact path="/accountSet" component={AccountSet} />
                          <Route exact path="/student/stuApply" component={StuApply} />
                          <Route exact path="/student/leaveDel" component={LeaveDel} />
                          <Route exact path="/student/stuModern" component={StuModern} />
                          <Route exact path="/teacher/teacherApply" component={TeacherApply} />
                          <Route exact path="/teacher/leaveDel" component={LeaveDel} />
                          <Route exact path="/teacher/teacherModern" component={TeacherModern} />
                          <Route exact path="/leaveStatus/waitCheck" component={LeaveStatus} />
                          <Route exact path="/leaveStatus/beingCheck" component={LeaveStatus} />
                          <Route exact path="/leaveStatus/doneCheck" component={LeaveStatus} />
                          <Route exact path="/studentInfo" component={UserInfo} />
                          <Route exact path="/teacherInfo" component={UserInfo} />
                          <Route exact path="/gradeInfo" component={UserInfo} />
                          <Route component={NotFound}></Route>
                       </Switch>    
                  </Content>
               </Layout>
             </Content>
             <Footer style={{ textAlign: 'center' }}>Ant Design ©2018 Created by Ant UED</Footer>
          </Layout>
        );
    }
}

export default connect(
  state => ({
    defaultOpenKeys: state.Menu.defaultOpenKeys,
    defaultSelectedKeys: state.Menu.defaultSelectedKeys
  }), //defaultOpenKeys: ["/"], defaultSelectedKeys:['/']

  { menu_select } //menu_select:f()
)(MyRouter)