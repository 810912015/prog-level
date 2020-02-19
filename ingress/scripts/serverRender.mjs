import React from'react'
import ReactDOMServer from 'react-dom/server'




var App = React.createFactory(import('../src/App'))


console.log(ReactDOMServer.renderToString(App))
