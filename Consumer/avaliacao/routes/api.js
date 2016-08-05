module.exports = function (app) {

    var apiContatos = app.controllers.apiContatos;

	app.get('/api/obtercontatos', apiContatos.obtercontatos);

    app.post('/api/salvarcontato', apiContatos.salvarcontato);

    app.delete('/api/excluircontato/:_id/:nome/:telefone', apiContatos.excluircontato);

    app.put('/api/atualizarcontato/:_id/:nome/:telefone', apiContatos.atualizarcontato);


};