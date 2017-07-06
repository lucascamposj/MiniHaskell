package br.unb.cic.mhs

import br.unb.cic.mhs.ast.{ExpressaoSubtracao, ValorInteiro}
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by lucas-mac on 05/07/17.
  */
class ExpressaoSubtracaoTeste extends FlatSpec with Matchers {
    "Uma subtracao entre os valores 3 e 4" should "levar ao valor sete" in {
        val soma = new ExpressaoSubtracao(new ValorInteiro(3), new ValorInteiro(4));
        soma.avaliar().asInstanceOf[ValorInteiro].valor should be (-1);
    }
}