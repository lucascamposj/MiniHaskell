package br.unb.cic.mhs

import br.unb.cic.mhs.ast.{ExpressaoMenorQue, ValorBooleano, ValorInteiro}
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by lucas-mac on 05/07/17.
  */
class ExpressaoMenorQueTeste extends FlatSpec with Matchers {
    "3 < 4" should "levar ao valor true" in {
        val mq = new ExpressaoMenorQue(new ValorInteiro(3), new ValorInteiro(4));
        mq.avaliar().asInstanceOf[ValorBooleano].valor should be(true);
    }

    "4 < 3" should "levar ao valor false" in {
        val mq = new ExpressaoMenorQue(new ValorInteiro(4), new ValorInteiro(3));
        mq.avaliar().asInstanceOf[ValorBooleano].valor should be(false);
    }
}