import React from 'react'
import { connect, useDispatch } from 'react-redux';
import { Route, Router } from 'react-router'
import { Redirect } from 'react-router'

const SignupRoute = ({signupAuth, component: Component, ...rest }) => {
    return (
        <Route {...rest} render={props => {

            if (signupAuth) {
                return <Component {...props} />
            }
            else {
                return <Redirect to="/" />
            }
        }} />
    );
}

const mapStateToProps = ({ login }) => {
    return {
        signupAuth: login.signupAuth,
    };
};

const mapDispatchToProps = (dispatch) => {
    return {
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(SignupRoute);