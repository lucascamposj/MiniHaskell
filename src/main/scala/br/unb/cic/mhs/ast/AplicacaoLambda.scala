package br.unb.cic.mhs.ast

import br.unb.cic.mhs.memoria.{AmbienteExpressao, AmbienteExpressaoLambda}
import br.unb.cic.mhs.visitors.MHSVisitor

/**
  * Created by lucas-mac on 04/07/17.
  */
class AplicacaoLambda(val expLambda : Expressao, val valor : Expressao) extends Expressao{
    override def avaliar(): Valor = {
        val lambda = expLambda.avaliar().asInstanceOf[ExpressaoLambda]

        AmbienteExpressaoLambda.associar(lambda.arg,valor)
        lambda.corpo.avaliar()
    }

    override def verificarTipo(): Tipo = TErro

    override def aceitar[T](visitor: MHSVisitor[T]): T = visitor.visitar(this)
}
