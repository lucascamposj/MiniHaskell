package br.unb.cic.mhs.ast

import br.unb.cic.mhs.visitors.MHSVisitor

/**
  * Created by lucas-mac on 04/07/17.
  */
class ExpressaoNegativa(v : Expressao) extends ExpressaoUnitaria(v){
    override def avaliar(): Valor = {
        val v1 = v.avaliar().asInstanceOf[ValorBooleano]

        return new ValorBooleano(!v1.valor)
    }

    override def aceitar[T](visitor: MHSVisitor[T]): T = visitor.visitar(this)
}
