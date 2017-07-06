package br.unb.cic.mhs.ast

import br.unb.cic.mhs.visitors.MHSVisitor

/**
  * Created by lucas-mac on 04/07/17.
  */
class ExpressaoFatorial(v : Expressao) extends ExpressaoUnitaria(v){
    override def avaliar(): Valor = {
        val v1 = v.avaliar().asInstanceOf[ValorInteiro]

        return fatorial(v1)
    }

    override def aceitar[T](visitor: MHSVisitor[T]): T = visitor.visitar(this)

    def fatorial(v : ValorInteiro) : Valor = {
        if(v.valor != 0)
            new ExpressaoMultiplica(fatorial(new ValorInteiro(v.valor - 1)), v).avaliar()
        else
            ValorInteiro(1)
    }
}
