package br.unb.cic.mhs.ast

import br.unb.cic.mhs.memoria.Ambiente
import br.unb.cic.mhs.memoria.AmbienteExpressao
import br.unb.cic.mhs.visitors.MHSVisitor

/**
 * Classe que representa uma expressao do tipo Let. 
 * Permite escrever algo como let x = 10 in x + 1, o que levaria 
 * ao valor 11. 
 */
class ExpressaoLet(val id : String , val expNomeada: Expressao, val corpo: Expressao) extends Expressao {
  
  override def avaliar() : Valor = {
    var actualExp : Expressao = null
    try{
      actualExp = AmbienteExpressao.pesquisar(id)
      AmbienteExpressao.associar(id, expNomeada)
    }catch{
      case e : java.util.NoSuchElementException => AmbienteExpressao.associar(id, expNomeada)
    }

    val c =  corpo.avaliar()
    AmbienteExpressao.associar(id, actualExp)
    return c
  }
  
  override def verificarTipo : Tipo = 
    if(expNomeada.verificarTipo().equals(TErro))
      TErro
    else  corpo.verificarTipo()

  override def aceitar[T](visitor : MHSVisitor[T]) : T = visitor.visitar(this)
}