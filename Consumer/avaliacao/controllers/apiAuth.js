//Modulo que efetua a conexão com o banco de dados e efetua a conexão com o facebook
module.exports = function(app) {
    var passport = require('passport');
    var LocalStrategy = require('passport-local').Strategy;
    var FacebookStrategy = require('passport-facebook').Strategy;
    var flash = require('connect-flash');
    var mysql      = require('mysql');
    var connection = mysql.createConnection({
        host     : '127.0.0.1',
        user     : 'root',
        password : '123456',
        database : 'listatelefonica'
    });
    connection.connect(function(err){
        if(!err) {
            console.log("Database is connected ...");
        } else {
            console.log("Error connecting database ..."+err);
        }
    });

    passport.use(new FacebookStrategy({
            clientID: '839055946224652',
            clientSecret: 'd89b30455ba74511a2bb8003d2615a0b',
            callbackURL: "http://localhost:3000/auth/facebook/callback"
        },
        function(accessToken, refreshToken, profile, done) {
                //campo ID é Unique
                connection.query('INSERT INTO listatelefonica.usuarios (id) VALUES ('+profile.id+')', function(err,res){
                    if(!err) {
                        console.log("Arquivo Inserido com Sucesso");
                    } else {
                        if (err.code === "ER_DUP_ENTRY") {
                            console.log("Usuário já cadastrado");
                        } else {
                            console.log("Ocorreu um erro ao acessar o banco de dados");
                        }
                    }
                });
                connection.end();
                return done(null, profile);
        }
    ));

    passport.use(new LocalStrategy(
        function(username, password, done) {
            //campo ID é Unique
            connection.query('SELECT * FROM listatelefonica.usuarios WHERE username="'+username+'" AND password="'+password+'"',
                function(err,rows,fields){
                    if(err) {
                        console.log("Houve um erro ao obter um arquivo");
                        return done(null, false, { message: 'Usuário ou senha incorretas' });
                    } else {
                        if (rows.length > 0) {
                            connection.end();
                            return done(null,rows[0]);
                        }
                        return done(null, false, { message: 'Usuário ou senha incorretas' });
                    }
            });
        }
    ));
    // serialize and deserialize
    passport.serializeUser(function(user, done) {
        done(null, user);
    });
    passport.deserializeUser(function(obj, done) {
        done(null, obj);
    });


}