import { CHANGE_HEADER_INVERSE } from './viewTypes';

const intialViewState = {
  isMain: true,
};

const viewReducer = (state = intialViewState, action) => {
  switch (action.type) {
    case CHANGE_HEADER_INVERSE:
      let isNowMain = false;
      const currentUrl = decodeURI(window.location.href);

      if (currentUrl === 'http://localhost:3000/') isNowMain = true;
      else isNowMain = false;
  
      return {
        ...state,
        isMain: isNowMain,
      };
    default:
      return state;
  }
};
export default viewReducer;
