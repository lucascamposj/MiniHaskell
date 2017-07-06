package br.unb.cic.mhs.ast

class DecFuncao (val nome: String, val args: List[String],val tipoArg : List[Tipo],val corpo: Expressao, val tipoRetorno : Tipo){}