package br.unb.cic.mhs

import br.unb.cic.mhs.ast.{ExpressaoE, ValorBooleano, ValorInteiro}
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by lucas-mac on 05/07/17.
  */
class ExpressaoETeste extends FlatSpec with Matchers {
    "Uma operacao E com true e false" should "levar ao valor false" in {
        val e = new ExpressaoE(new ValorBooleano(true), new ValorBooleano(false));
        e.avaliar().asInstanceOf[ValorBooleano].valor should be (false);
    }

    "Uma operacao E com false e false" should "levar ao valor false" in {
        val e = new ExpressaoE(new ValorBooleano(false), new ValorBooleano(false));
        e.avaliar().asInstanceOf[ValorBooleano].valor should be (false);
    }

    "Uma operacao E com true e true" should "levar ao valor true" in {
        val e = new ExpressaoE(new ValorBooleano(true), new ValorBooleano(true));
        e.avaliar().asInstanceOf[ValorBooleano].valor should be (true);
    }

}
