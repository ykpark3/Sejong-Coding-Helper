import { CHANGE_HEADER_INVERSE,CHANGE_VER_NAV_ITEM,CHANGE_LOADING_STATE } from "./viewTypes"

export const headerViewer = () => {
    return {
        type: CHANGE_HEADER_INVERSE
    }
}

export const clickedNavItem = () => {
    return {
        type:CHANGE_VER_NAV_ITEM,
    }
}

export const changeLoadingState = (props) => {
    return {
        type: CHANGE_LOADING_STATE,
        data:{isLoading : props},
      };
}