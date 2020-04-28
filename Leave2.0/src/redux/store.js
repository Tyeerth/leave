import { createStore, applyMiddleware,compose} from 'redux'
import thunk from 'redux-thunk'
import { composeWithDevTools } from 'redux-devtools-extension'
import reducers from './reducers'
//import reducers from '../redux/reducers/combineReducers'

const composeEnhancer = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;

const store = createStore(
    reducers,
    composeEnhancer(
        applyMiddleware(thunk), 
    )
);
export default store;

