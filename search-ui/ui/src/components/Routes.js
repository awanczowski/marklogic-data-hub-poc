import React from 'react';
import { Switch, Route, Redirect } from 'react-router-dom';
import queryString from 'query-string';
import AssetDetailTemplate from './AssetDetailTemplate';
import AssetSearchResult from './AssetSearchResult';

import {
  SearchContainer,
  DetailContainer,
  CreateContainer,
  LoginContainer
} from 'grove-core-react-redux-containers';

const PrivateRoute = ({
  component: Component,
  render,
  isAuthenticated,
  ...rest
}) => {
  return (
    <Route
      {...rest}
      render={props =>
        isAuthenticated ? (
          render ? (
            render(props)
          ) : (
            <Component {...props} />
          )
        ) : (
          <Redirect
            to={{
              pathname: '/login',
              state: { from: props.location }
            }}
          />
        )
      }
    />
  );
};

const Routes = ({ isAuthenticated }, ...rest) => {
  return (
    <Switch>
      <Route
        exact
        path="/login"
        render={props => {
          return isAuthenticated ? (
            <Redirect
              to={(props.location.state && props.location.state.from) || '/'}
            />
          ) : (
            <LoginContainer />
          );
        }}
      />
      <PrivateRoute
        isAuthenticated={isAuthenticated}
        exact
        path="/"
        render={() => <SearchContainer resultComponent={AssetSearchResult} />}
      />
      <PrivateRoute
        isAuthenticated={isAuthenticated}
        exact
        path="/detail"
        render={props => {
          // Prefer to get id from the state
          const id =
            (props.location.state && props.location.state.id) ||
            queryString.parse(props.location.search).id;
          return <DetailContainer template={AssetDetailTemplate} id={id} />;
        }}
      />
      <PrivateRoute
        isAuthenticated={isAuthenticated}
        exact
        path="/create"
        render={() => <CreateContainer redirectPath="/detail" />}
      />
    </Switch>
  );
};

export default Routes;
