
module.exports = function(app) {
	var http = require('http');
	var util = require('util');
	var jsonCycle = require('json-cycle');
	var jsonStringify = require('stringify');

	var ApiContatos = {

	    obtercontatos: function (req, res) {
			var strJson = '';
			var parser = '';
			var url = 'http://127.0.0.1:8099/contatos/listar';

			http.get(url, function(res) {
				res.on('data', function(result) {
					strJson += result;
				});

				res.on('end', function(res) {
					parser = JSON.parse(strJson);
					alertFunc();
				});

			}).on('error', function(e) {
				console.log("Error: " + e.message);
			});

			function alertFunc() {
				res.json(parser);
			}
		},
		salvarcontato: function (contato, res) {
			// An object of options to indicate where to post to
			var data = JSON.stringify(contato.body);

			var options = {
				host: '127.0.0.1',
				port: '8099',
				path: '/contatos/inserir',
				method: 'POST',
				headers: {
					'Content-Type': 'application/json; charset=utf-8',
					'Content-Length': data.length
				}
			};

			var req = http.request(options, function(res) {
				var msg = '';

				res.setEncoding('utf8');
				res.on('data', function(chunk) {
					msg += chunk;
				});
				res.on('end', function() {
					console.log("Fim Salvar Contato");
					alertFunc();
				});
			});

			function alertFunc() {
				res.json(data);
			}
			req.write(data);
			req.end();
		},
		excluircontato: function (contato, res) {
			// An object of options to indicate where to post to
			var data = JSON.stringify(contato.params);

			var options = {
				host: '127.0.0.1',
				port: '8099',
				path: '/contatos/excluir/',
				method: 'DELETE',
				headers: {
					'Content-Type': 'application/json; charset=utf-8',
					'Content-Length': data.length
				}
			};

			var req = http.request(options, function(res) {
				var msg = '';
				res.setEncoding('utf8');
				res.on('data', function(chunk) {
					msg += chunk;
				});
				res.on('end', function() {
					console.log("Fim Excluir Contato");
					alertFunc();
				});
			});

			function alertFunc() {
				res.json(data);
			}
			req.write(data);
			req.end();
		},
		atualizarcontato: function (contato, res) {
			// An object of options to indicate where to post to
			var data = JSON.stringify(contato.params);

			var options = {
				host: '127.0.0.1',
				port: '8099',
				path: '/contatos/atualizar/',
				method: 'PUT',
				headers: {
					'Content-Type': 'application/json; charset=utf-8',
					'Content-Length': data.length
				}
			};

			var req = http.request(options, function(res) {
				var msg = '';
				res.setEncoding('utf8');
				res.on('data', function(chunk) {
					msg += chunk;
				});
				res.on('end', function() {
					console.log("Fim Atualizar Contato");
					alertFunc();
				});
			});

			function alertFunc() {
				res.json(data);
			}
			req.write(data);
			req.end();
		}
  };

  return ApiContatos;

};
