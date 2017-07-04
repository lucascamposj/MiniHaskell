package br.unb.cic.mhs.ast

import br.unb.cic.mhs.memoria.AmbienteExpressao
import br.unb.cic.mhs.visitors.MHSVisitor

/**
  * Created by lucas-mac on 04/07/17.
  */
class ExpressaoLambda(val arg : String, val corpo : Expressao) extends Valor{
    override def avaliar(): Valor = this

    override def verificarTipo(): Tipo = TErro

    override def aceitar[T](visitor: MHSVisitor[T]): T = visitor.visitar(this)
}
