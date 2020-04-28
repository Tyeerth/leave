import { MENU_SELECT, LOGIN_ABOUT} from './action-types'
import { combineReducers } from 'redux'

const initMenu={
    defaultOpenKeys:['/'],
    defaultSelectedKeys:['/']
}

const initLogin={
    loginInfo:{
        userName: '',
        password: '',
        userRole: ''
    }
}

function Menu(state=initMenu,action){
    console.log("Menu-reducer生成(state,action):",state,action);
    switch(action.type){
        case MENU_SELECT:
            return {
                defaultOpenKeys:action.defaultOpenKeys,
                defaultSelectedKeys:action.defaultSelectedKeys
            }
        default:
            return state;
    }
}

function Login(state=initLogin,action){
    //console.log("reducer生成用户角色(state,action):",state,action);
    switch(action.type){
        case LOGIN_ABOUT:
            return {
               loginInfo:action.loginInfo
            }
        default:
            return state;
    }
}

const reducers = combineReducers({
    Menu,
    Login
})
export default reducers