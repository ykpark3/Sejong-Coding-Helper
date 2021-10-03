import React from 'react'
import VerticalHeader from './VerticalHeader';
import HorizontalHeader from './HorizontalHeader';
import { connect } from 'react-redux';
import { LOGIN_ORIGIN } from '../redux/login/loginTypes';
import { Redirect } from 'react-router'
import { useLocation } from 'react-router';

const Loading = ({ loginState,}) => {

    const location = useLocation();

    //console.log(location.state.path);
    //console.log("he");

    return (
        <>
            {loginState === LOGIN_ORIGIN ?
                <div>
                    <VerticalHeader />
                    <HorizontalHeader />
                </div> : '' }
        </>


    );
}

const mapStateToProps = ({ login }) => {
    return {
        loginState: login.type,
    };
};

const mapDispatchToProps = (dispatch) => {
    return {
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(Loading);
