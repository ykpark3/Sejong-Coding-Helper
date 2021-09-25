import { LOGIN,LOGIN_SUCCESS,LOGIN_FAIL } from "./loginTypes";


export const login = (id, pwd) => {

    if(id==='qwe'){
        return {
            type:LOGIN_SUCCESS,
            data:{id:id},
        };
    }

    return {
        type:LOGIN_FAIL,
        id:id,
    };
}