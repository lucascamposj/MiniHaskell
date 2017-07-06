package br.unb.cic.mhs

import br.unb.cic.mhs.ast.{ExpressaoOU, ValorBooleano}
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by lucas-mac on 05/07/17.
  */
class ExpressaoOUTeste extends FlatSpec with Matchers {
    "Uma operacao OU com true e false" should "levar ao valor true" in {
        val ou = new ExpressaoOU(new ValorBooleano(true), new ValorBooleano(false));
        ou.avaliar().asInstanceOf[ValorBooleano].valor should be (true);
    }

    "Uma operacao OU com false e false" should "levar ao valor false" in {
        val ou = new ExpressaoOU(new ValorBooleano(false), new ValorBooleano(false));
        ou.avaliar().asInstanceOf[ValorBooleano].valor should be (false);
    }

}