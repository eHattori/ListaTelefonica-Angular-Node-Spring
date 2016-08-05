//módulos
var express = require('express');
var load = require('express-load');
var bodyParser = require('body-parser');
var app = express();
var childProcess = require("child_process");
childProcess.spawn = require('cross-spawn');
var path = require('path');
var util = require('util');
var json = require('json-cycle');
var jsonStringify = require('stringify');
var favicon = require('serve-favicon');
var logger = require('morgan');
var cookieParser = require('cookie-parser');
var server = require('http').Server(app);
var io = require('socket.io')(server);
var passport = require('passport');
var localstrategy = require('passport-local');
var flash = require('connect-flash');
var session = require('express-session');
var sessionStore = new session.MemoryStore;

var cors = require('cors')
app.use(cors())

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');

// uncomment after placing your favicon in /public
//app.use(favicon(path.join(__dirname, 'public', 'favicon.ico')));
app.use(function(req, res, next) {
    res.header("Access-Control-Allow-Origin", "*");
    res.header("credentials", true);
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
    res.header("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE");
    next();
});
app.use(logger('dev'));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

// Configurações de autenticação
app.use(cookieParser('secret'));
app.use(session({
    cookie: { maxAge: 900000 },
    store: sessionStore,
    saveUninitialized: true,
    resave: true,
    secret: 'secret'
}));

app.use(flash());
app.use(passport.initialize());
app.use(passport.session());

load('models')
    .then('controllers')
    .then('routes')
    .into(app);

server.listen(3000);
console.log('App no ar ...');

module.exports = app;