import { MENU_SELECT, LOGIN_ABOUT} from './action-types'

export const menu_select=(defaultOpenKeys,defaultSelectedKeys)=>({
    type:MENU_SELECT,
    defaultOpenKeys,
    defaultSelectedKeys
})

export const login_about = (loginInfo) => ({
    type: LOGIN_ABOUT,
    loginInfo
})
