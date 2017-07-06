package br.unb.cic.mhs.ast

import br.unb.cic.mhs.memoria.AmbienteExpressao
import br.unb.cic.mhs.visitors.MHSVisitor

/**
  * Created by lucas-mac on 04/07/17.
  */
class DecLambda(val arg : String, val tipoRetorno : Tipo, val corpo : Expressao, val tipoCorpo : Tipo) extends Valor{
    override def avaliar(): Valor = this


    override def aceitar[T](visitor: MHSVisitor[T]): T = visitor.visitar(this)
}
