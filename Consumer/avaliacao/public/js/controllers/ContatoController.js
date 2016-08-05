var app = angular.module('mainCtrl', []);

app.controller('ContatoController', function($scope,$http){
	$scope.contatos = [];
	$scope.titulo = "Movimentação de Contatos";
	$scope.nome = '';
	$scope.telefone = '';
	$scope._id = '';

	$scope.carregarContatos = function(){
		$http({
			url: '/api/obtercontatos',
			method: "GET"
		}).success(function(data){
			console.log(data);
			$scope.contatos = data;
		}).error(function(data){
			console.log(data);
			console.error("Houve um erro ao obter os dados");
		});
	};

	$scope.submitForm = function(contato) {
		if ($scope._id != '') {
			$scope.atualizarContato(contato);
		} else {
			$scope.salvarContato(contato);
		}
	};
	$scope.salvarContato = function(contato){
		$http({
			url: '/api/salvarcontato',
			method: "POST",
			contentType: "application/json",
			data:contato
		}).success(function(data){
			console.log(data);
			$scope.carregarContatos();
		}).error(function(data){
			console.log(data);
			//console.error("Houve um erro ao Salvar os dados");
		});
	};

	$scope.excluirContato = function (contatos) {
		$scope.contatos = contatos.filter(function(contato){
			if (!contato.selecionado) {
				return contato;
			} else {
				$http({
					url: '/api/excluircontato/'+contato._id+'/'+contato.nome+'/'+contato.telefone,
					method: "DELETE",
					contentType: "application/json",
					data:contato
				}).success(function(data){
					console.log(data);
					$scope.carregarContatos();
				}).error(function(data){
					console.log(data);
					//console.error("Houve um erro ao Salvar os dados");
				});
			}
		});
	};
	$scope.atualizarContato = function (contato) {
		//$scope.contatos = contatos.filter(function(contato){
		if (typeof contato.nome == 'undefined')
			contato.nome = $scope.nome;
		if (typeof contato.telefone == 'undefined')
			contato.telefone = $scope.telefone;
			if (!contato) {
				return contato;
			} else {
				$http({
					url: '/api/atualizarcontato/'+$scope._id+'/'+contato.nome+'/'+contato.telefone,
					method: "PUT",
					contentType: "application/json",
					data:contato
				}).success(function(data){
					console.log(data);
					$scope.carregarContatos();
				}).error(function(data){
					console.log(data);
					//console.error("Houve um erro ao Salvar os dados");
				});
			}
		//});
	};
	$scope.isContatoSelecionado = function(contatos) {
		return contatos.some(function(contato){
			if (contato.selecionado) {
				$scope.nome = contato.nome;
				$scope._id = contato._id;
				$scope.telefone = contato.telefone;
			} else {
				$scope.nome = '';
				$scope.telefone = '';
				$scope._id = '';
			}
			return contato.selecionado;
		});
	};


});