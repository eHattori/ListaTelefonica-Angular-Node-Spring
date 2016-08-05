var mongoose = require('mongoose');

var Schema = mongoose.Schema;
var UserSchema = new Schema({
    nome: { type: String, required: true },
    telefone: {type: String, required: true },
    operadora:{
        nome: { type: String, required: true },
        codigo: { type: Number, default: '' },
        categoria: { type: String, default: '' }
    }
    },
    {
      collection: 'contatos'
    });

mongoose.model('Contatos', UserSchema);