var passport = require('passport');
var flash = require('connect-flash');

module.exports = function (app) {

	var apiMiddleware = app.controllers.apiMiddleware;
	var conexao = app.controllers.login;

	app.get('/', conexao.verificaConexao, conexao.login);

	app.get('/error_auth', conexao.error_login);

	app.get('/logout', conexao.logout);

	app.get('/index', apiMiddleware.middleware, conexao.index);
	
	app.get('/auth/facebook', passport.authenticate('facebook'));

	app.get('/auth/facebook/callback', passport.authenticate('facebook', { successRedirect: '/index', failureRedirect: '/error_auth', failureFlash: true }));

	app.post('/login', passport.authenticate('local', { successRedirect: '/index', failureRedirect: '/error_auth', failureFlash: true }));

	app.get('*', apiMiddleware.middleware, conexao.index);
};	