module.exports = function(app) {

	var LogoutController = {

		login:function(req, res){
			 res.render('login');
		},

		logout: function(req, res){
			 req.logout();
	  		 res.redirect('/login');
		},

		error_login:function(req, res){
			res.render('error_auth');
		},

		index:function(req, res){
			res.render('index');
		},

		verificaConexao: function autenticacao(req, res, next) {  
	    if (!req.isAuthenticated()) {
	      return next()
	    }
	    res.render('index');
		}
	};

	return LogoutController;
};