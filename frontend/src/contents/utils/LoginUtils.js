import axios from 'axios';
import { LOGIN_SUCCESS, LOGIN_BEFORE } from '../../redux/login/loginTypes';
import { API_BASE_URL } from './Constant';

export const refreshLoginToken = async function (callback) {
  var getCookie = function (name) {
    var value = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
    return value ? value[2] : null;
  };

  console.log('ref');

  try {
    let data = getCookie('id');
    data = { id: data };
    if (data.id === null) {
      console.log('app silent requset fail');
      callback(LOGIN_BEFORE);
      return;
    }

    console.log(data);

    let result = await axios
      .post(API_BASE_URL + '/refreshLoginToken', JSON.stringify(data), {
        headers: {
          'Content-Type': `application/json`,
        },
        withCredentials: true,
      })
      .then((res) => {
        console.log('login success');
        axios.defaults.headers.common['Authorization'] = 'Bearer ' + res.data;

        if (callback) {
          callback(LOGIN_SUCCESS, data.id);
        }
        return 'success';
        //changeType(LOGIN_SUCCESS);

        // 자동 로그인 연장 처리 X
        // console.log("here");
        // setTimeout(function () {
        //     console.log("reload");
        //   refreshLoginToken(null);
        // }, 3 * 1000);
      })
      .catch((ex) => {
        console.log('app silent requset fail : ' + ex);

        if (callback) {
          callback(LOGIN_BEFORE);
        }
        return 'fail';
        //changeType(LOGIN_BEFORE);
      })
      .finally(() => {
        //console.log('login request end');
      });

     //console.log(result) + ' one';
      return result;

  } catch (e) {
    console.log(e);
  }
};
