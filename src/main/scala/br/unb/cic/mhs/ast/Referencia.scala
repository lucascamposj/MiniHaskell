package br.unb.cic.mhs.ast

import br.unb.cic.mhs.memoria.AmbienteExpressao
import br.unb.cic.mhs.visitors.MHSVisitor

class Referencia(val id: String) extends Expressao {
   override def avaliar() : Valor = AmbienteExpressao.pesquisar(id).avaliar()
   
   override def verificarTipo() : Tipo = {
     val exp = AmbienteExpressao.pesquisar(id)
     if(exp != null) 
       exp.verificarTipo()
     else
       TErro
   }
   
   override def aceitar[T](visitor : MHSVisitor[T]) : T =  visitor.visitar(this)
     
}