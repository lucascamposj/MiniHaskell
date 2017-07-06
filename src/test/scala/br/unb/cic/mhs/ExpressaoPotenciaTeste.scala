package br.unb.cic.mhs

import br.unb.cic.mhs.ast.{ExpressaoPotencia, ValorInteiro}
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by lucas-mac on 05/07/17.
  */
class ExpressaoPotenciaTeste extends FlatSpec with Matchers {
    "3 ^ 4" should "levar ao valor 81" in {
        val soma = new ExpressaoPotencia(new ValorInteiro(3), new ValorInteiro(4));
        soma.avaliar().asInstanceOf[ValorInteiro].valor should be (81);
    }
}