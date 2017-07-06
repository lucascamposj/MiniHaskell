package br.unb.cic.mhs.ast

import br.unb.cic.mhs.visitors.MHSVisitor

/**
  * Created by lucas-mac on 04/07/17.
  */
class ExpressaoOU (lhs : Expressao, rhs : Expressao) extends ExpressaoBinaria(lhs, rhs) {

    override def avaliar() : Valor = {
        val v1 = lhs.avaliar().asInstanceOf[ValorBooleano]
        val v2 = rhs.avaliar().asInstanceOf[ValorBooleano]

        return new ValorBooleano(v1.valor || v2.valor)
    }


    override def aceitar[T](visitor : MHSVisitor[T]) : T =  visitor.visitar(this)
}
