package br.unb.cic.mhs

import br.unb.cic.mhs.ast.{ExpressaoDivisao, ValorInteiro}
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by lucas-mac on 05/07/17.
  */
class ExpressaoDivisaoTeste extends FlatSpec with Matchers {
    "Uma divisao entre os valores 10 e 2" should "levar ao valor cinco" in {
        val divisao = new ExpressaoDivisao(new ValorInteiro(10), new ValorInteiro(2));
        divisao.avaliar().asInstanceOf[ValorInteiro].valor should be (5);
    }
}