package br.unb.cic.mhs.ast

import br.unb.cic.mhs.memoria.AmbienteDecFuncao
import br.unb.cic.mhs.memoria.AmbienteExpressao
import br.unb.cic.mhs.visitors.MHSVisitor

class Aplicacao (val nome: String, val args: Expressao*) extends Expressao{
  override def avaliar(): Valor = {
    val funcao = AmbienteDecFuncao.pesquisar(nome)
    for(i <- 0 until funcao.args.size){
      AmbienteExpressao.associar(funcao.args(i), args(i))
    }
    funcao.corpo.avaliar()
  }
  
  override def verificarTipo() : Tipo = TErro
  
  override def aceitar[T](visitor : MHSVisitor[T]) : T =  visitor.visitar(this)

}