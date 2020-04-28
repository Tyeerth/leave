import React, { Component } from 'react'
import {Route,Redirect,Switch} from 'react-router-dom'
import MyRouter from './routes/router'
import Login from './pages/login/login';

export default class App extends Component {
  render() {
    return (
      <div className="App">
        <Switch>
          <Route path="/login" component={Login} />
          <Route path="/" component={MyRouter} />
          <Redirect to="/" />
        </Switch>
      </div>
    )
  }
}



