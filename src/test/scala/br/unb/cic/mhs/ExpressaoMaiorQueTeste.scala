package br.unb.cic.mhs

import br.unb.cic.mhs.ast.{ExpressaoMaiorQue, ValorBooleano, ValorInteiro}
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by lucas-mac on 05/07/17.
  */
class ExpressaoMaiorQueTeste extends FlatSpec with Matchers {
    "3 > 4" should "levar ao valor false" in {
        val mq = new ExpressaoMaiorQue(new ValorInteiro(3), new ValorInteiro(4));
        mq.avaliar().asInstanceOf[ValorBooleano].valor should be(false);
    }

    "4 > 3" should "levar ao valor true" in {
        val mq = new ExpressaoMaiorQue(new ValorInteiro(4), new ValorInteiro(3));
        mq.avaliar().asInstanceOf[ValorBooleano].valor should be(true);
    }
}
