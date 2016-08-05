
var app = angular.module("listaTelefonica",['ui.router','mainCtrl']);

app.config(function($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise("/");
    $stateProvider
        .state('inicial', {
          url: "/"
        })
        .state('contato', {
          url: "/contato",
          controller: 'ContatoController',
          templateUrl: 'view/contato.html'
        });
    });

    app.run(function($rootScope, $state){
	$state.go("inicial");
});
		