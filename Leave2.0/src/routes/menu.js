import React from 'react'
import { HomeOutlined, UserAddOutlined, UnorderedListOutlined, 
         SettingOutlined, SnippetsOutlined, ClearOutlined, 
    HddOutlined, BlockOutlined, SwitcherOutlined,} from '@ant-design/icons';
         
const menuConfig = [
    {
        title: '首页',
        key: '/',
        icon: <HomeOutlined />,
    },
    {
        title: '学生',
        key: '/student',
        icon: <UserAddOutlined />,
        children: [
            {
                title: '假条申请',
                key: '/student/stuApply',
                icon: <SnippetsOutlined />
            },
            {
                title: '假条注销',
                key: '/student/leaveDel',
                icon: <ClearOutlined />
            },
            {
                title: '假条模板',
                key: '/student/stuModern',
                icon: <HddOutlined />,
            },
        ]
    },
    {
        title: '教师',
        key: '/teacher',
        icon: <UserAddOutlined />,
        children: [
            {
                title: '假条申请',
                key: '/teacher/teacherApply',
                icon: <SnippetsOutlined />
            },
            {
                title: '假条注销',
                key: '/teacher/leaveDel',
                icon: <ClearOutlined />
            },
            {
                title: '假条模板',
                key: '/teacher/teacherModern',
                icon: <HddOutlined />,
            },
        ]
    },
    {
        title: '管理员',
        key: '/admin',
        icon: <UserAddOutlined />,
        children: [
            {
                title: '假条申请',
                key: '/admin/stuApply',
                icon: <SnippetsOutlined />
            },
            {
                title: '假条注销',
                key: '/admin/leaveDel',
                icon: <ClearOutlined />
            },
            {
                title: '假条模板',
                key: '/admin/stuModern',
                icon: <HddOutlined />,
            },
        ]
    },
    {
        title: '请假审批状态',
        key: '/leaveStatus',
        icon: <UnorderedListOutlined />,
        children:[
            {
                'title':'待审核',
                 key:'/leaveStatus/waitCheck',
                 icon: <BlockOutlined />
            },
            {
                'title': '审核中',
                key: '/leaveStatus/beingCheck',
                icon: <BlockOutlined />
            },
            {
                'title': '已审核',
                key: '/leaveStatus/doneCheck',
                icon: <BlockOutlined />
            }
        ]
    },
    {
        title: '学生信息',
        key: '/studentInfo',
        icon: <SwitcherOutlined />,
    },
    {
        title: '教师信息',
        key: '/teacherInfo',
        icon: <SwitcherOutlined />,
    },
    {
        title: '级别信息',
        key: '/gradeInfo',
        icon: <SwitcherOutlined />,
    },
    {
        title: '账户设置',
        key: '/accountSet',
        icon: <SettingOutlined />,
    }
];

export default menuConfig;