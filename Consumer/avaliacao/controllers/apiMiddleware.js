module.exports = function(app) {

var ApiMiddleware = {

	middleware: function authenticationMiddleware (req, res, next) {  
	
	    if (req.isAuthenticated()) {
	      return next()
	    }
	    res.redirect('/')
	}
}

	return ApiMiddleware;
}