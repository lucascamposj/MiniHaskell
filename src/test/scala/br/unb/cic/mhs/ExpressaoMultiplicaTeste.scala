package br.unb.cic.mhs

import br.unb.cic.mhs.ast.{ExpressaoMultiplica, ValorInteiro}
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by lucas-mac on 05/07/17.
  */
class ExpressaoMultiplicaTeste extends FlatSpec with Matchers {
    "Uma multiplicacao entre os valores 10 e 2" should "levar ao valor vinte" in {
        val soma = new ExpressaoMultiplica(new ValorInteiro(10), new ValorInteiro(2));
        soma.avaliar().asInstanceOf[ValorInteiro].valor should be (20);
    }
}
