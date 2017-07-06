package br.unb.cic.mhs.ast

import br.unb.cic.mhs.memoria.{AmbienteExpressao, AmbienteExpressaoLambda}
import br.unb.cic.mhs.visitors.MHSVisitor

/**
  * Created by lucas-mac on 04/07/17.
  */
class ReferenciaLambda(val id: String) extends Expressao {
    override def avaliar() : Valor = AmbienteExpressaoLambda.pesquisar(id).avaliar()



    override def aceitar[T](visitor : MHSVisitor[T]) : T =  visitor.visitar(this)
}
